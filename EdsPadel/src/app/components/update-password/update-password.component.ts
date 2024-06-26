import { Component,OnInit,Renderer2,Inject,PLATFORM_ID,NgZone } from '@angular/core';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { NgToastService,ToastType } from 'ng-angular-popup';
import * as L from 'leaflet'; // Importation de Leaflet
import { isPlatformBrowser } from '@angular/common';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ResetPasswordComponent } from '../reset-password/reset-password.component';
interface Forecast {
  dt_txt: string;
  main: { temp_max: number; temp_min: number };
  weather: [{ icon: string }];
  wind: { speed: number };
}

interface ForecastData {
  list: Forecast[];
}
@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrl: './update-password.component.css'
})
export class UpdatePasswordComponent implements OnInit {
  constructor(private service:AppwebserviceService,private router:Router,private toast:NgToastService,private renderer: Renderer2,@Inject(PLATFORM_ID) private platformId: Object,
  private ngZone: NgZone,    private modalService: NgbModal,

){
    this.map = {} as L.Map;
  }
  private jwtHelper: JwtHelperService = new JwtHelperService();
  weatherData: any;
  forecastData: Forecast[] = [];
  city:any
  latitude: any;
  longitude: any;
  clubs:any;
  search:boolean=false
  nom:any
  token:any
  idClub:any
  username:any
  password:any
  logoApp:any
  nomClub:any
  adresseUrl:any
  bannerImage:any
  map: L.Map
  adresse:any
  horaireInfos:any
  preload:boolean=true
  themeDetail1:any
  themeDetail2:any
  preloadButton:boolean=false
  ngOnInit(): void {
    this.applyTheme('theme1')//important
    this.idClub=localStorage.getItem("idClub")
    this.service.getInfoClub(this.idClub).subscribe(data=>{
      this.city=data.ville
      this.service.getWeatherForecast(data.ville).subscribe(data => {
        this.forecastData = data.list.filter((forecast: Forecast) => forecast.dt_txt.includes('12:00:00')).slice(0, 7);
      });
      this.getWeather(data.ville);
      this.applyTheme(data.appWeb.couleurAppWeb);
        this.logoApp=data.appWeb.logoAppWeb
        this.nomClub=data.nomClub
        this.adresseUrl=data.appWeb.adresseUrl
        this.bannerImage=data.appWeb.bannerImage
        this.latitude=data.latitude
        this.longitude=data.longitude
        this.adresse=data.adresse
        this.horaireInfos=data.horaireInfos
        const themeColors = this.getThemeColors(data.appWeb.couleurAppWeb);
        if (themeColors) {
          this.themeDetail1 = themeColors.detail1;
          this.themeDetail2 = themeColors.detail2;
        }
        this.activateMode('color-mode', data.appWeb.mode);
        if(data.appWeb.mode=="dark"){
          this.applyDarkModeToTable(true)
          this.updateInputMode(true);
          this.updatPasswordInputMode(true)
        }else{
          this.applyDarkModeToTable(false)
          this.updateInputMode(false);
          this.updatPasswordInputMode(false)

        }
        setTimeout(() => {
          this.preload = false;
        },2000)
    })
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
  openModal() {
    const modalRef = this.modalService.open(ResetPasswordComponent, { size: 'xs', centered: true }); // Utilisez size: 'xl' pour un modal de grande taille
    modalRef.componentInstance.name = 'World';
    modalRef.result.then((result) => {
      console.log(result);
    }).catch((error) => {
      console.log(error);
    });
  }
  updateInputMode(isDark: boolean): void {
    const inputElement = document.querySelector('.form-control');
    if (inputElement) {
      if (isDark) {
        inputElement.classList.add('dark');
      } else {
        inputElement.classList.remove('dark');
      }
    }
  }
  updatPasswordInputMode(isDark: boolean): void {
    const inputElement = document.querySelector('.pwd');
    if (inputElement) {
      if (isDark) {
        inputElement.classList.add('dark');
      } else {
        inputElement.classList.remove('dark');
      }
    }
  }
  activateMode(setting: string, value: string): void {
    let detailObj = {};
  
    if (setting === 'color-mode') {
      detailObj = { dark: value };
      document.body.classList.add(value);
      this.applyDarkModeToTable(true); // Appliquer le mode sombre au tableau
      this.updateInputMode(true); // Mettre à jour le mode de l'élément input

    } else if (setting === 'light-mode') {
      detailObj = { light: value };
      document.body.classList.remove('dark');
      this.applyDarkModeToTable(false); // Appliquer le mode clair au tableau
      this.updateInputMode(false); // Mettre à jour le mode de l'élément input

    }
  
    const event = new CustomEvent("ChangeMode", { detail: detailObj });
    document.dispatchEvent(event);

  }
  
  applyDarkModeToTable(isDark: boolean): void {
    const table = document.getElementById('basic-table');
    if (table) {
      if (isDark) {
        table.classList.add('table-dark');
      } else {
        table.classList.remove('table-dark');
      }
    }
  }
  applyTheme(theme: string): void {
    this.ngZone.run(() => {
        console.log(`applyTheme called with theme: ${theme}`);
        const themeColors = this.getThemeColors(theme);
        if (themeColors) {
            this.customizerMode(themeColors.custombodyclass, themeColors.detail1, themeColors.detail2);
        }
    });
}
  getThemeColors(theme: string): { custombodyclass: string, detail1: string, detail2: string } | null {
    switch (theme) {
      case 'theme1': return { custombodyclass: 'theme-color-blue', detail1: '#00C3F9', detail2: '#573BFF' };
      case 'theme2': return { custombodyclass: 'theme-color-gray', detail1: '#91969E', detail2: '#FD8D00' };
      case 'theme3': return { custombodyclass: 'theme-color-red', detail1: '#DB5363', detail2: '#366AF0' };
      case 'theme4': return { custombodyclass: 'theme-color-yellow', detail1: '#EA6A12', detail2: '#6410F1' };
      case 'theme5': return { custombodyclass: 'theme-color-pink', detail1: '#E586B3', detail2: '#25C799' };
      case 'theme6': return { custombodyclass: 'theme-color-default', detail1: '#079aa2', detail2: '#0000FF' };
      default: return null;
    }
  }
  customizerMode(custombodyclass: string, detail1: string, detail2: string | null): void {
    if (isPlatformBrowser(this.platformId)) {
      const bodyClasses = document.body.classList;
      const themeClasses = Array.from(bodyClasses).filter(cls => cls.startsWith('theme-color-'));
      themeClasses.forEach(cls => bodyClasses.remove(cls));
      this.renderer.addClass(document.body, custombodyclass);
      const primaryColor = getComputedStyle(document.body).getPropertyValue('--bs-primary');
      document.documentElement.style.setProperty('--bs-info', detail2!);
      const color = sessionStorage.getItem('colorcustomchart-mode');
      const eventDetail1 = color && color !== 'null' && color !== '' ? color.trim() : detail1.trim();
      const event = new CustomEvent('ColorChange', { detail: { detail1: eventDetail1, detail2: detail2!.trim() } });
      document.dispatchEvent(event);
      const elements = document.querySelectorAll('[data-setting="color-mode1"][data-name="color"]');
      elements.forEach((mode: Element) => {
        const colorclass = mode.getAttribute('data-value');
        if (colorclass === custombodyclass) {
          mode.classList.add('active');
        } else {
          mode.classList.remove('active');
        }
      });
    }
  }
  private saveToken(token: string): void {
    localStorage.setItem('jwt_token', token);
  }

  getToken(): any {
    return localStorage.getItem('jwt_token');
  }

  getUsername(): string {
    const token = this.getToken();
    return this.jwtHelper.decodeToken(token).sub; 
  }

  getClubId(): number {
    const token = this.getToken();
    return this.jwtHelper.decodeToken(token).clubId;
  }
  getUserId(): number {
    const token = this.getToken();
    return this.jwtHelper.decodeToken(token).userId;
  }
  getRole():string{
    const token = this.getToken();
    return this.jwtHelper.decodeToken(token).role;
  }
  logout(): void {
    localStorage.removeItem('jwt_token');
  }

  isLoggedIn(): any {
    const token = this.getToken();
    return token && !this.jwtHelper.isTokenExpired(token);
  }
  updatePassword() {
    this.preloadButton = true;
    let formValid = true;

    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

    if (!this.password || !passwordRegex.test(this.password)) {
        formValid = false;
        document.getElementById('pwd')?.classList.add('error');
    } else {
        document.getElementById('pwd')?.classList.remove('error');
    }

    if (!formValid) {
        setTimeout(() => {
            this.preloadButton = false;
            this.toast.toast('Veuillez remplir un nouveau mot de passe avec au moins 8 caractères, une majuscule, une minuscule, un chiffre et un caractère spécial.', ToastType.DANGER, 'Erreur', 5000);
        }, 2000);
        return;
    }

    const data = {
        "userId": this.getUserId(),
        "role": this.getRole(),
        "idClub": this.idClub,
        "password": this.password
    };

    this.service.updatePassword(data).subscribe(data => {
        setTimeout(() => {
            this.preloadButton = false;
            this.router.navigateByUrl(this.adresseUrl + '/home');
        }, 2000);
    });
}


  goToCreateClub(){
    this.router.navigate(["/payement"])
  }
  getClubs(): void {
    this.service.getClubsProximete(this.latitude, this.longitude, 500.0).subscribe(data => {
      this.clubs = data.map((club:any) => {
        club.distance = this.calculateDistance(this.latitude, this.longitude, club.latitude, club.longitude);
        return club;
      });
      this.clubs.sort((a:any, b:any) => a.distance - b.distance);
      console.log(this.clubs);
    });
  }

  calculateDistance(lat1: number, lon1: number, lat2: number, lon2: number): number {
    const R = 6371; // Radius of the Earth in km
    const dLat = this.deg2rad(lat2 - lat1);
    const dLon = this.deg2rad(lon2 - lon1);
    const a = 
      Math.sin(dLat / 2) * Math.sin(dLat / 2) +
      Math.cos(this.deg2rad(lat1)) * Math.cos(this.deg2rad(lat2)) * 
      Math.sin(dLon / 2) * Math.sin(dLon / 2);
    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    const distance = R * c; // Distance in km
    return distance;
  }

  deg2rad(deg: number): number {
    return deg * (Math.PI / 180);
  }
  getClubsByNom(nom:any){
    this.search=true
    this.clubs=null;
    this.service.getClubsByNom(nom).subscribe(data => {
      this.clubs = data.map((club:any) => {
        club.distance = this.calculateDistance(this.latitude, this.longitude, club.latitude, club.longitude);
        return club;
      });
      this.clubs.sort((a:any, b:any) => a.distance - b.distance);
      console.log(this.clubs);
    });
  }
  closeList() {
    this.search = false; 
    this.clubs = null; 
  }
  onNomChange(nom: string): void {
    if (nom.trim().length > 0) {
      this.setCurrentLocation().then(() => {
      this.getClubsByNom(nom);
    });
    } else {
      this.search = false;
      this.clubs = null;
    }
  }
  getClubsByProximete(){
    this.search=true
    this.setCurrentLocation().then(() => {

      this.getClubs();

    });
  }
  private setCurrentLocation(): Promise<void> {
    return new Promise((resolve, reject) => {
      if ('geolocation' in navigator) {
        navigator.geolocation.getCurrentPosition(
          (position) => {
            this.latitude = position.coords.latitude;
            this.longitude = position.coords.longitude;
            resolve();
          },
          (error) => reject(error)
        );
      } else {
        reject('Geolocation not available');
      }
    });
  }
}
