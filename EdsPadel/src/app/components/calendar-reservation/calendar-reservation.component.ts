import { Component, ViewChild, AfterViewInit,OnInit,TemplateRef } from "@angular/core";
import {
  DayPilot,
  DayPilotCalendarComponent,
  DayPilotMonthComponent,
  DayPilotNavigatorComponent
} from "daypilot-pro-angular";
import { CalendarService } from "../../services/calendar.service";
import { AddRdvComponent } from "../add-rdv/add-rdv.component";
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from "../../services/appwebservice.service";
import { NgbOffcanvas } from '@ng-bootstrap/ng-bootstrap';
interface Forecast {
  dt_txt: string;
  main: { temp_max: number; temp_min: number };
  weather: [{ icon: string }];
  wind: { speed: number };
}
@Component({
  selector: 'app-calendar-reservation',
  templateUrl: './calendar-reservation.component.html',
  styleUrls: ['./calendar-reservation.component.css'],
})
export class CalendarReservationComponent implements AfterViewInit,OnInit {
  @ViewChild("day") day!: DayPilotCalendarComponent;
  @ViewChild("week") week!: DayPilotCalendarComponent;
  @ViewChild("month") month!: DayPilotMonthComponent;
  @ViewChild("navigator") nav!: DayPilotNavigatorComponent;
  courts: any[] = [
    
  ];
  weatherData: any;
  forecastData: Forecast[] = [];
  reservations:any
  idClub:any
  nameCourt:any
  idCourt:any
  heureDebut:any
  heureFin:any
  membres: any[] = []; // Initialize with your members list
  selectedMembers: any[] = []; // Array to hold selected members
  selectedCourtCapacity: number=0;
  searchText:any
  preload:boolean=false
  timestamp:any
  duration:any
  userId:any
  role:any
  dateRes:any
  idRes:any
  idMatch:any
  heureRes:any
  dateDernierRes:any
  ngOnInit() {
    this.idClub = localStorage.getItem("idClub");
    this.userId=localStorage.getItem("userId")
    this.role=localStorage.getItem("role")
    this.service.getInfoClub(this.idClub).subscribe(data=>{
      this.service.getWeatherForecast(data.ville).subscribe(data => {
        this.forecastData = data.list.filter((forecast: Forecast) => forecast.dt_txt.includes('12:00:00')).slice(0, 7);
      });
      this.getWeather(data.ville);})
    this.service.getRessource(this.idClub).subscribe(res => {
    console.log(res)
      this.courts = res.map((court: any) => ({
        id: court.id,
        name: court.libelle,
        color: "rgb(25 135 84)",
        capacite:court.capacite,
        slots: this.generateSlots(court.plageHoraire.debutAct, court.plageHoraire.finAct, Number(court.plageHoraire.dureeAct)),
        reservations: [] 
      }));
      this.markExpiredSlots();

      // Fetch and process reservations
      this.courts.forEach(court => {
        this.service.getReservationsByCourt(court.id, this.date.toString()).subscribe(data => {
          court.reservations = data;
          this.updateSlotsWithReservations(court);
        });
      });
    });

    // Update reservations via WebSocket
    this.service.getReservationUpdates().subscribe(res => {
      res.forEach(update => {
        const court = this.courts.find(c => c.id === update.ressource.id);
        if (court) {
          court.reservations = res;
          this.updateSlotsWithReservations(court);
        }
      });
    });

    this.service.connectWebSocket();
  }
  getWeather(ville:any) {
    this.service.getWeather(ville).subscribe(
      data => {
        this.weatherData = data;
      },
      error => {
        console.error('Error fetching weather data', error);
      }
    );
  }
  formatTime(time: string): string {
    const [hours, minutes] = time.split(':');
    return `${hours.padStart(2, '0')}:${minutes.padStart(2, '0')}`;
  }
  openEnd(content: TemplateRef<any>, coutName: any, timestamp: any, duration: any, capacity: number,id:any) {
    this.selectedMembers = [];
    const startTime = new Date(timestamp);
    const endTime = new Date(startTime.getTime() + duration * 60000);
    this.heureDebut = this.formatTime(startTime.toTimeString().slice(0, 5));
    this.heureFin = this.formatTime(endTime.toTimeString().slice(0, 5));
    this.service.getMembersNonParticipe(this.idClub, this.heureDebut, this.date.toString()).subscribe(res => {
      console.log(res);
      this.membres = res;
  
      if (this.role === 'membre') {
        const userIndex = this.membres.findIndex(member => member.idUtilisateur === Number(this.userId));
        if (userIndex !== -1) {
          const user = this.membres.splice(userIndex, 1)[0];
          this.selectedMembers.push(user);
        }
      }
    });
    this.timestamp=timestamp
    this.duration=duration
    this.idCourt=id
    this.nameCourt = coutName;
    this.selectedCourtCapacity = Number(capacity);
    this.offcanvasService.open(content, { position: 'start', scroll: false });
  }
  openEndUpdate(content: TemplateRef<any>,id:any,dateRes:any) {
   this.selectedMembers=[];
  if(this.canDeleteReservation(dateRes)){
    this.service.getReservationByID(id).subscribe(res=>{
      this.nameCourt=res.ressource.libelle
      this.heureDebut=res.heureDebut
      this.heureFin=res.heureFin
      this.selectedMembers=res.match.membres
      this.idRes=res.id
      this.selectedCourtCapacity=res.ressource.capacite
      this.idMatch=res.match.id
      this.heureRes=res.heureRes
      this.dateDernierRes=res.dateDernierRes
      this.idCourt=res.ressource.id
      this.dateRes=dateRes
      this.service.getMembersNonParticipe(this.idClub, this.heureDebut, this.date.toString()).subscribe(res => {
        this.membres = res;
  
      });
    })
 
    this.offcanvasService.open(content, { position: 'start', scroll: false });
  }else{
    alert("tu ne peut pas modifier cette reservation")
  }
   
  }
  generateSlots(startTime: string, endTime: string, duration: number) {
    const slots = [];
    const [startHour, startMinute] = startTime.split(':').map(Number);
    const [endHour, endMinute] = endTime.split(':').map(Number);
  
    const start = new Date();
    start.setHours(startHour, startMinute, 0, 0);
  
    const end = new Date();
    end.setHours(endHour, endMinute, 0, 0);
  
    // Handle end time being on the following day
    if (end <= start) {
      end.setDate(end.getDate() + 1);
    }
  
    while (start < end) {
      const slotTime = start.toTimeString().substring(0, 5);
      slots.push({
        time: slotTime,
        timestamp: start.getTime(),
        start: slotTime,
        duration: duration,
        expired: false,
        match: false,
        players: [],
        dateRes:""
      });
  
      start.setMinutes(start.getMinutes() + duration);
    }
  
    return slots;
  }
  onMemberSelect(event: any, membre: any) {
    if (event.target.checked) {
      if (this.selectedMembers.length < this.selectedCourtCapacity) {
        this.selectedMembers.push(membre);
        this.membres = this.membres.filter(m => m.idUtilisateur !== membre.idUtilisateur);
      } else {
        event.target.checked = false; // Uncheck the checkbox if the capacity is exceeded
      }
    } else {
      this.selectedMembers = this.selectedMembers.filter(m => m.idUtilisateur !== membre.idUtilisateur);
      this.membres.push(membre);
    }
  }

  isMemberSelected(membre: any): boolean {
    return this.selectedMembers.some(m => m.idUtilisateur === membre.idUtilisateur);
  }
  filteredMembers(): any[] {
    if (!this.searchText) {
      return this.membres;
    }
    return this.membres.filter(membre =>
      membre.nom.toLowerCase().includes(this.searchText.toLowerCase()) ||
      membre.prenom.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }
  markExpiredSlots() {
    const now = new Date().getTime();
    this.courts.forEach(court => {
      court.slots.forEach((slot: any) => {
        if (slot.timestamp < now) {
          slot.expired = true;
        }
      });
    });
  }

  updateSlotsWithReservations(court: any) {
    court.slots.forEach((slot: any) => {
      const reservation = court.reservations.find((res: any) => {
        const start = res.heureDebut
        const end =res.heureFin
        return res && start == slot.start
      });
  

  
      if (reservation) {
        slot.match = reservation.match;
        slot.players = this.getReservationMembers(reservation);
        slot.dateRes=reservation.dateRes
        slot.idRes=reservation.id
      } else {
        slot.match = false;
        slot.players = [];
      }
    });
  }
  
  getReservationMembers(reservation: any) {
    console.log(reservation.match.membres)
    return reservation.match ? reservation.match.membres : [];
  }


 
 
  addReservation() {

    this.preload=true
    // Convert timestamp to a Date object
    const startTime = new Date(this.timestamp);
    // Calculate end time by adding duration (assuming duration is in minutes)
    const endTime = new Date(startTime.getTime() + this.duration * 60000);
  
    // Format the start and end time to HH:mm format
    const heureDebut = startTime.toTimeString().slice(0, 5);
    const heureFin = endTime.toTimeString().slice(0, 5);
    const heureRes = startTime.toTimeString().slice(0, 5); // Dynamic time for heureRes
    const dateRes = this.getCurrentDateTime();

    const reservation = {
      match: {
        membres: this.selectedMembers
      },
      reservation: {
        dateRes: dateRes,
        heureRes: heureRes,
        dateDernierRes: this.date.toString(),
        heureDebut: heureDebut,
        heureFin: heureFin,
        club: {
          idClub: this.idClub
        },
        ressource: {
          id: this.idCourt
        },
        membre: {
          idUtilisateur: this.userId
        }
      }
    };
  
    this.service.addReservation(reservation, this.idCourt, this.date.toString())
    this.offcanvasService.dismiss('Cross click');
    this.preload=false
  }
  updateReservation() {

    this.preload=true
    const reservation = {
      id:this.idRes,
      dateRes: this.dateRes,
      heureRes: this.heureRes,
      dateDernierRes: this.dateDernierRes,
      heureDebut: this.heureDebut,
      heureFin: this.heureFin,
      ressource: {
        id: this.idCourt
      },
      match: {
        id:this.idMatch,
        membres: this.selectedMembers,
        reservation:{id:this.idRes}
      },
     
    };
    console.log(reservation)
    this.service.updateReservation(reservation, this.idCourt, this.date.toString())
    this.offcanvasService.dismiss('Cross click');
    this.preload=false
  }
  testDelete(){
    this.service.deleteReservation(5,1,this.date.toString())

   
   }
  selectedCourtId: number | null = 1; // Track selected court
  events: DayPilot.EventData[] = [];
  date = DayPilot.Date.today();
  selectCourt(courtId: number) {
    this.selectedCourtId = courtId;
  }
  configNavigator: DayPilot.NavigatorConfig = {
    showMonths: 1,
    cellWidth: 50,
    cellHeight: 50,
    onVisibleRangeChanged: args => {
      this.loadEvents();
    }
  };

  configDay: DayPilot.CalendarConfig = {
    viewType: "Day",
    durationBarVisible: false,
    startDate: this.date,
    cellDuration:90,
    onTimeRangeSelected: this.onTimeRangeSelected.bind(this),
    onBeforeEventRender: this.onBeforeEventRender.bind(this),
    onEventClick: this.onEventClick.bind(this),
  };

  configWeek: DayPilot.CalendarConfig = {
    viewType: "Week",
    durationBarVisible: false,
    startDate: this.date,
    onTimeRangeSelected: this.onTimeRangeSelected.bind(this),
    onBeforeEventRender: this.onBeforeEventRender.bind(this),
    onEventClick: this.onEventClick.bind(this),
  };

  configMonth: DayPilot.MonthConfig = {
    startDate: this.date,
    onTimeRangeSelected: this.onTimeRangeSelected.bind(this),
    onEventClick: this.onEventClick.bind(this),
  };

  constructor(private ds: CalendarService ,private modalService: NgbModal,private service:AppwebserviceService,private offcanvasService:NgbOffcanvas) {
    this.viewDay();
  }

  ngAfterViewInit(): void {
  }

  loadEvents(): void {
    const from = this.nav.control.visibleStart();
    const to = this.nav.control.visibleEnd();
    this.ds.getEvents(from, to).subscribe(result => {
      this.events = result;
    });
  }

  viewDay(): void {
    this.configNavigator.selectMode = "Day";
    this.configDay.visible = true;
    this.configWeek.visible = false;
    this.configMonth.visible = false;
  }

  viewWeek(): void {
    this.configNavigator.selectMode = "Week";
    this.configDay.visible = false;
    this.configWeek.visible = true;
    this.configMonth.visible = false;
  }

  viewMonth(): void {
    this.configNavigator.selectMode = "Month";
    this.configDay.visible = false;
    this.configWeek.visible = false;
    this.configMonth.visible = true;
  }

  changeDate(direction: string): void {
    const days = direction === 'prev' ? -1 : 1;
    this.date = this.date.addDays(days);
    this.updateViewDate(this.date);
  }

  updateViewDate(date: DayPilot.Date): void {
    this.configDay.startDate = date;
    this.configWeek.startDate = date;
    this.configMonth.startDate = date;
    this.date=date
  }

  onBeforeEventRender(args: any) {
    // Custom rendering code here
  }

  async onTimeRangeSelected(args: any) {
    const modal = await DayPilot.Modal.prompt("Create a new event:", "Event 1");
    const dp = args.control;
    dp.clearSelection();
    if (!modal.result) { return; }
    dp.events.add(new DayPilot.Event({
      start: args.start,
      end: args.end,
      id: DayPilot.guid(),
      text: modal.result
    }));
  }

  async onEventClick(args: any) {
    const form = [
      {name: "Text", id: "text"},
      {name: "Start", id: "start", dateFormat: "MM/dd/yyyy", type: "datetime"},
      {name: "End", id: "end", dateFormat: "MM/dd/yyyy", type: "datetime"},
      {name: "Color", id: "backColor", type: "select", options: this.ds.getColors()},
    ];

    const data = args.e.data;
    const modal = await DayPilot.Modal.form(form, data);
    if (modal.canceled) { return; }
    const dp = args.control;
    dp.events.update(modal.result);
  }

  onViewModeChange(event: any): void {
    const viewMode = event.target.value;
    if (viewMode === 'Day') {
      this.viewDay();
    } else if (viewMode === 'Week') {
      this.viewWeek();
    } else if (viewMode === 'Month') {
      this.viewMonth();
    }
    this.updateViewDate(this.date);
  }

  // Helper method to convert DayPilot.Date to JavaScript Date
  convertToJSDate(dayPilotDate: DayPilot.Date): Date {
    return new Date(dayPilotDate.toString());
  }
  formatDateInFrench(date: Date): string {
    const daysOfWeek = ['dimanche', 'lundi', 'mardi', 'mercredi', 'jeudi', 'vendredi', 'samedi'];
    const months = ['janvier', 'février', 'mars', 'avril', 'mai', 'juin', 'juillet', 'août', 'septembre', 'octobre', 'novembre', 'décembre'];

    const dayName = daysOfWeek[date.getDay()];
    const day = date.getDate().toString().padStart(2, '0');
    const month = months[date.getMonth()];
    const year = date.getFullYear();

    return `${dayName} ${day} ${month} ${year}`;
  }
  openLargeModalEdit(user: any) {
    const modalRef = this.modalService.open(AddRdvComponent, { size: 'm', centered: true });
    modalRef.componentInstance.user = { ...user }; // Pass a copy of the user object
  
    modalRef.result.then((result) => {
      if (result === 'saved') {
        // Handle success, e.g., refresh the user list
      }
    }).catch((error) => {
      console.log(error);
    });
  }
  canDeleteReservation(dateRes: string): boolean {
    const reservationDate = new Date(dateRes);
    const now = new Date();
    const timeDifference = (reservationDate.getTime() - now.getTime()) / 60000;
    if (this.role === 'admin') {
      return true;
    }
      return timeDifference >= -100;
  }
  
  getCurrentDateTime(): string {
    const now = new Date();
    return now.toISOString(); 
  }
}
