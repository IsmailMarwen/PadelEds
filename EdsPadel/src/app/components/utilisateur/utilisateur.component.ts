import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AddUserComponent } from '../add-user/add-user.component';
import { EditUserComponent } from '../edit-user/edit-user.component';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { Component, OnInit, AfterViewInit,Renderer2, Inject, PLATFORM_ID, NgZone, ElementRef, TemplateRef } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { NgbOffcanvas } from '@ng-bootstrap/ng-bootstrap';
import Scrollbar from 'smooth-scrollbar';
import AOS from 'aos';
import { ImageBannerComponent } from '../image-banner/image-banner.component';
import { ChangeDetectorRef } from '@angular/core';

declare var $: any;
declare var window: any;
declare const Waypoint: any;

@Component({
  selector: 'app-utilisateur',
  templateUrl: './utilisateur.component.html',
  styleUrls: ['./utilisateur.component.css']
})
export class UtilisateurComponent implements AfterViewInit,OnInit {
  id:string='';
  image:string='';
  role: string = '';
  nom: string = '';
  prenom: string = '';
  email: string = '';
  phone: string = '';
  gender: string = '';
  username: string = '';
  password: string = '';
  confirmPassword: string = '';
  isModalVisible: boolean = false;
  searchQuery: string = '';
  clubId: number | null = null; // Add clubId as an optional parameter
  users: any[] = [];
  originalUsers: any[] = []; // Array to store original users before filtering
  selectedOption: string = '0';  options = [
    { value: '0', label: 'All Users' },
    { value: '1', label: 'Admin' },
    { value: '2', label: 'Membre' },
    { value: '3', label: 'Coach' }
  ];
  //
  selectedTheme: string = ''
  currentMode: string = '';
 currentSidebarColor: string = ''
 bannerImage:any
 nameApp:String=''
 logoApp:string=""

  constructor(private modalService: NgbModal, private userService: AppwebserviceService
    ,private renderer: Renderer2,
    @Inject(PLATFORM_ID) private platformId: Object,
    private ngZone: NgZone,
    private elRef: ElementRef,
    private offcanvasService: NgbOffcanvas,
    private cdr: ChangeDetectorRef,
    private service:AppwebserviceService
  ) {}

  ngAfterViewInit(): void {
    this.getUsers();
  }

 
  get selectedOptionLabel(): string {
    const selected = this.options.find(option => option.value === this.selectedOption);
    return selected ? selected.label : 'Select an option';
  }

  selectOption(option: { value: string, label: string }) {
    this.selectedOption = option.value;
  
    switch (option.value) {
      case '0':
        this.getUsers(); // Fetch all users
        break;
      case '1':
        this.getAdmins();
        break;
      case '2':
        this.getMembers();
        break;
      case '3':
        this.getCoaches();
        break;
      default:
        this.getUsers(); // Default to fetching all users
        break;
    }
  }
  
  getAdmins(): void {
    this.userService.getAdmins().subscribe(admins => {
      this.users = admins;
    });
  }
  
  getMembers(): void {
    this.userService.getMembers().subscribe(members => {
      this.users = members;
    });
  }
  
  getCoaches(): void {
    this.userService.getCoaches().subscribe(coaches => {
      this.users = coaches;
    });
  }
  
 

  getUsers(): void {
    this.userService.getAllUsers().subscribe(users => {
      this.users = users;
      this.originalUsers = users; // Store original users for filtering
    });
  }

  searchUsers(event: any): void {
    const searchTerm = event.target.value.toLowerCase();
    this.users = this.originalUsers.filter(user => {
      return Object.values(user).some((value: any) => 
        String(value).toLowerCase().includes(searchTerm)
      );
    });
  }
  
  deleteUser(userId: number, role: string): void {
    this.userService.deleteUser(userId, role).subscribe(() => {
      // Remove the deleted user from the users array
      this.users = this.users.filter(user => user.id !== userId);
  
      // Fetch the updated list of users from the backend
      this.getUsers();
    }, error => {
      console.error('Error deleting user:', error);
    });
  }
  

  showUserFormModal() {
    this.isModalVisible = true;
  }

  hideUserFormModal() {
    this.isModalVisible = false;
  }

  submitForm() {
    // Perform form submission logic here
    console.log({
      id:this.id,
      image:this.image,
      role: this.role,
      nom: this.nom,
      prenom: this.prenom,
      email: this.email,
      phone: this.phone,
      gender: this.gender,
      username: this.username,
      password: this.password,
      confirmPassword: this.confirmPassword
    });
    this.hideUserFormModal();
  }

  resetForm() {
    this.id='';
    this.image='';
    this.role = '';
    this.nom = '';
    this.prenom = '';
    this.email = '';
    this.phone = '';
    this.gender = '';
    this.username = '';
    this.password = '';
    this.confirmPassword = '';
  }

  openLargeModalAdd() {
    const modalRef = this.modalService.open(AddUserComponent, { size: 'xl', centered: true });
    modalRef.componentInstance.name = 'World';
    modalRef.result.then((result) => {
      if (result === 'saved') {
        // Handle success, e.g., refresh the user list
        this.getUsers();
      }
    }).catch((error) => {
      console.log(error);
    });
  }
  
  openLargeModalEdit(user: any) {
    const modalRef = this.modalService.open(EditUserComponent, { size: 'xl', centered: true });
    modalRef.componentInstance.user = { ...user }; // Pass a copy of the user object
  
    modalRef.result.then((result) => {
      if (result === 'saved') {
        // Handle success, e.g., refresh the user list
        this.getUsers();
      }
    }).catch((error) => {
      console.log(error);
    });
  }
  
  ngOnInit() {
    this.nameApp='Padel Eds'
    this.applyTheme('theme2');
    this.scheduleScrollbarInit();
    this.initAOS();
   
    //this.activateMode('color-mode', 'dark');
    //this.currentMode = 'dark';
    this.changeSidebarColor('sidebar-color')
    this.service.bannerImage$.subscribe(image => {
      this.bannerImage = image;
   });
   this.service.logo$.subscribe(image => {
    this.logoApp = image;
 });
   this.bannerImage=localStorage.getItem("banner")
   if (window.counterUp !== undefined) {
    const counterUp = window.counterUp["default"];
    const counterElements = document.querySelectorAll('.counter');
    Array.from(counterElements).forEach((el: Element) => {
      if (typeof Waypoint !== 'undefined') {
        const waypoint = new Waypoint({
          element: el,
          handler: function () {
            counterUp(el, {
              duration: 1000,
              delay: 10,
            });
            this.destroy();
          },
          offset: "bottom-in-view"
        });
      }
    });
  

  }
 }
 changeLogo(event: any) {
  const file: File = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = (e: any) => {
        this.logoApp=e.target.result;
        this.service.setLogoAppWeb(e.target.result);
      

    };
    reader.readAsDataURL(file);
  }
}
 
  openLargeModal() {
    const modalRef = this.modalService.open(ImageBannerComponent, { size: 'xl', centered: true }); // Utilisez size: 'xl' pour un modal de grande taille
    modalRef.componentInstance.name = 'World';
    modalRef.result.then((result) => {
      console.log(result);
    }).catch((error) => {
      console.log(error);
    });
  }
  changeSidebarColor(color: string): void {
    console.log(color);
  
    // Retirer la classe 'active' des éléments de couleur de la barre latérale
    const sidebarColors = document.querySelectorAll('[data-setting="sidebar"][data-name="sidebar-color"]');
    Array.from(sidebarColors, (el) => {
      el.classList.remove('active');
    });
  
    // Retirer la classe de couleur actuelle de la barre latérale
    const sidebarDefault = document.querySelector('.sidebar-default');
    if (sidebarDefault) {
      const currentValue = this.currentSidebarColor;
      if (currentValue) {
        sidebarDefault.classList.remove(currentValue);
      }
    }
  
    // Ajouter la classe de la nouvelle couleur à la barre latérale
    if (sidebarDefault) {
      sidebarDefault.classList.add(color);
    }
  
    // Mettre à jour la couleur actuelle du sidebar
    this.currentSidebarColor = color;
  }
  
  
  private initAOS() {
    if (typeof AOS !== typeof undefined) {
      AOS.init({
        startEvent: 'DOMContentLoaded',
        disable: function () {
          const maxWidth = 996;
          return window.innerWidth < maxWidth;
        },
        throttleDelay: 10,
        once: true,
        duration: 700,
        offset: 10
      });
    }
  }
  private scheduleScrollbarInit() {
    if (isPlatformBrowser(this.platformId)) {
      window.addEventListener('load', () => {
        setTimeout(() => {
          this.initScrollbar();
        }, 100); // Adjust the delay as necessary
      });
    }
  }

  private initScrollbar() {
    const scrollbarEl = this.elRef.nativeElement.querySelector('.data-scrollbar');
    if (scrollbarEl) {
      Scrollbar.init(scrollbarEl, {
        continuousScrolling: false,
      });

      // Ensure the elements load by scrolling a bit
      setTimeout(() => {
        scrollbarEl.scrollTop = 1;
      }, 100);
    }
  }

  applyTheme(theme: string): void {
    this.ngZone.run(() => {
        console.log(`applyTheme called with theme: ${theme}`);
        const themeColors = this.getThemeColors(theme);
        if (themeColors) {
            this.customizerMode(themeColors.custombodyclass, themeColors.detail1, themeColors.detail2);
            this.selectedTheme = theme; // Mettre à jour le thème sélectionné
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

  openEnd(content: TemplateRef<any>) {
    this.offcanvasService.open(content, { position: 'end' });
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
    this.currentMode = value === 'dark' ? 'dark' : 'light';

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
  

  toggleDarkMode(): void {
    this.activateMode('color-mode', 'dark');
  }

  toggleLightMode(): void {
    this.activateMode('light-mode', 'light');
  }
  isModeActive(mode: string): boolean {
    return this.currentMode === mode;
  }
  updateInputMode(isDark: boolean): void {
    const inputElement = document.querySelector('.search-input input');
    if (inputElement) {
      if (isDark) {
        inputElement.classList.add('dark');
      } else {
        inputElement.classList.remove('dark');
      }
    }
  }
}
