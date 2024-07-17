import { Component, OnInit, Renderer2, Inject, PLATFORM_ID, NgZone, ElementRef, TemplateRef,AfterViewInit  } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { NgbOffcanvas } from '@ng-bootstrap/ng-bootstrap';
import Scrollbar from 'smooth-scrollbar';
import AOS from 'aos';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ImageBannerComponent } from '../image-banner/image-banner.component';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { ChangeDetectorRef } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { NgToastService,ToastType } from 'ng-angular-popup';
import { CreateTypeAbonnementClubComponent } from '../create-type-abonnement-club/create-type-abonnement-club.component';
import { UpdateTypeAbonnementClubComponent } from '../update-type-abonnement-club/update-type-abonnement-club.component';
import { JwtHelperService } from '@auth0/angular-jwt';

declare var window: any;
declare const Waypoint: any;
@Component({
  selector: 'app-type-abonnement-club',
  templateUrl: './type-abonnement-club.component.html',
  styleUrl: './type-abonnement-club.component.css'
})
export class TypeAbonnementClubComponent {
  selectedTheme: string = ''
  currentMode: string = '';
 currentSidebarColor: string = ''
 bannerImage:any
 nameApp:String=''
 logoApp:string=""
 idClub:any
 theme:any
 preload:boolean=true
 themeDetail1:any
 themeDetail2:any
 idAppWeb:any
 adresseUrl:any
 id:string='';
  libType:string='';
  nbMois:string='';
  nbJour:string='';
  forfait:string='';




  
  isModalVisible: boolean = false;
  searchQuery: string = '';
  typeAbonnementClubs: any[] = [];
  originaltypeAbonnementClubs: any[] = []; // Array to store original typeAbonnementClubs before filtering
  notificationCount: number = 0;
  private jwtHelper = new JwtHelperService();
  constructor(
    private renderer: Renderer2,
    @Inject(PLATFORM_ID) private platformId: Object,
    private ngZone: NgZone,
    private elRef: ElementRef,
    private offcanvasService: NgbOffcanvas,
    private modalService: NgbModal,
    private cdr: ChangeDetectorRef,
    private service:AppwebserviceService,
    private router: Router,
    private toast:NgToastService

  ) {}
  ngAfterViewInit() {
this.initAll()
  }
  logout(){
    localStorage.removeItem('jwt_token');
    this.router.navigate([this.adresseUrl+"/loginClub"])
  }
  ngOnInit() {
    const token = localStorage.getItem("jwt_token");
    if (token) {
      const decodedToken = this.jwtHelper.decodeToken(token);
      const role = decodedToken?.role;
      const idClub=localStorage.getItem("idClub")
      if (role === 'admin' || role === 'agent') {
        this.service.getAllUsersNotValidate(idClub).subscribe(notifications => {
          this.notificationCount = notifications.length;
        });
      }
    }
    this.gettypeAbonnementClubs();
    this.applyTheme('theme1')//important
    this.idClub=localStorage.getItem("idClub")
    this.service.getInfoClub(this.idClub).subscribe(data=>{
      
      this.adresseUrl=data.appWeb.adresseUrl
    this.idAppWeb=data.appWeb.idAppWeb      
    this.nameApp=data.appWeb.nomAppWeb
      this.applyTheme(data.appWeb.couleurAppWeb);
      const themeColors = this.getThemeColors(data.appWeb.couleurAppWeb);
      if (themeColors) {
        this.themeDetail1 = themeColors.detail1;
        this.themeDetail2 = themeColors.detail2;
      }
      this.changeSidebarColor(data.appWeb.couleurSideBar)
      this.activateMode('color-mode', data.appWeb.mode);
      if(data.appWeb.mode=="dark"){
        this.applyDarkModeToTable(true)
        this.updateInputMode(true);
      }else{
        this.applyDarkModeToTable(false)
        this.updateInputMode(false);
      }
      this.currentMode = data.appWeb.mode;
      this.changeSidebarColor(data.appWeb.couleurSideBar)

        this.service.setBannerImage(data.appWeb.bannerImage)
        this.service.bannerImage$.subscribe(image => {
          this.bannerImage = image;
       });
      
   
      this.logoApp=data.appWeb.logoAppWeb
      setTimeout(() => {
        this.preload = false;
        this.initSidebarToggle();
    this.initSidebarState();
    this.scheduleScrollbarInit();
   
      }, 2000);
    })
   

   
 
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
 initAll() {
  this.initSidebarToggle();
  this.initSidebarState();
  this.scheduleScrollbarInit();
 
}

changeLogo(event: any) {
  const file: File = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.logoApp = e.target.result;
        this.updateAppWeb();
        
      };
      reader.readAsDataURL(file);
    }

}

 initSidebarToggle(): void {
  const sidebarToggleBtn = document.querySelectorAll('[data-toggle="sidebar"]');
  const sidebar = document.querySelector('.sidebar-default');

  if (sidebar !== null) {
    const sidebarActiveItem = sidebar.querySelectorAll('.active');

    Array.from(sidebarActiveItem).forEach((elem: Element) => {
      if (!elem.closest('ul')?.classList.contains('iq-main-menu')) {
        const childMenu = elem.closest('ul');
        if (childMenu) {
          childMenu.classList.add('show');
          const parentMenu = childMenu.closest('li')?.querySelector('.nav-link');
          if (parentMenu) {
            parentMenu.classList.add('collapsed');
            parentMenu.setAttribute('aria-expanded', 'true');
          }
        }
      }
    });
  }

  Array.from(sidebarToggleBtn).forEach((sidebarBtn: Element) => {
    this.sidebarToggle(sidebarBtn);
  });
}

sidebarToggle(elem: Element): void {
  elem.addEventListener('click', () => {
    const sidebar = document.querySelector('.sidebar');
    if (sidebar) {
      if (sidebar.classList.contains('sidebar-mini')) {
        sidebar.classList.remove('sidebar-mini');
      } else {
        sidebar.classList.add('sidebar-mini');
      }
    }
  });
}

initSidebarState(): void {
  const sidebar = document.querySelector('.sidebar-default');

  if (sidebar !== null) {
    const sidebarActiveItem = sidebar.querySelectorAll('.active');

    Array.from(sidebarActiveItem).forEach((elem: Element) => {
      if (!elem.closest('ul')?.classList.contains('iq-main-menu')) {
        const childMenu = elem.closest('ul');
        if (childMenu) {
          childMenu.classList.add('show');
          const parentMenu = childMenu.closest('li')?.querySelector('.nav-link');
          if (parentMenu) {
            parentMenu.classList.add('collapsed');
            parentMenu.setAttribute('aria-expanded', 'true');
          }
        }
      }
    });
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
  changeSidebarColorUpdate(color: string): void {
  
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
    this.updateAppWeb()
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
            this.selectedTheme = theme; 
        }
    });
}
applyThemeUpdate(theme: string): void {
  this.ngZone.run(() => {
      console.log(`applyTheme called with theme: ${theme}`);
      const themeColors = this.getThemeColors(theme);
      if (themeColors) {
          this.customizerMode(themeColors.custombodyclass, themeColors.detail1, themeColors.detail2);
          this.selectedTheme = theme; 
          this.updateAppWeb()
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
  activateModeUpdate(setting: string, value: string): void {
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
    this.updateAppWeb()

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
    this.activateModeUpdate('color-mode', 'dark');
  }

  toggleLightMode(): void {
    this.activateModeUpdate('light-mode', 'light');
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
  updateAppWeb(){
    var data={
      'idAppWeb':this.idAppWeb,
      "nomAppWeb": this.nameApp,
      "logoAppWeb": this.logoApp,
      "couleurAppWeb": this.selectedTheme,
      "bannerImage": this.bannerImage,
      "mode":this.currentMode,
      "couleurSideBar":this.currentSidebarColor,
      "adresseUrl":this.adresseUrl,
      "Club":{"idClub":localStorage.getItem("idClub")}
  }
  this.service.updateAppWeb(data).subscribe(data=>{
    this.toast.toast('Modification De App Web',ToastType.SUCCESS, 'Succes', 5000);

  })
  }
  
  
  
  gettypeAbonnementClubs(): void {
    const clubId=localStorage.getItem("idClub")
    if(clubId)
    this.service.getTypeAbonnementClub(clubId).subscribe(typeAbonnementClubs => {
      this.typeAbonnementClubs = typeAbonnementClubs;
      this.originaltypeAbonnementClubs = typeAbonnementClubs;
      console.log(this.originaltypeAbonnementClubs)
    });
    else
       console.error("il n' ya pas de club id")
  }

  searchtypeAbonnementClubs(event: any): void {
    const searchTerm = event.target.value.toLowerCase();
    this.typeAbonnementClubs = this.originaltypeAbonnementClubs.filter(typeAbonnementClub => {
      return Object.values(typeAbonnementClub).some((value: any) => 
        String(value).toLowerCase().includes(searchTerm)
      );
    });
  }
  
  deletetypeAbonnementClub(typeAbonnementClubId: number): void {
    this.service.deleteTypeAbonnementClub(typeAbonnementClubId).subscribe(() => {
      // Remove the deleted typeAbonnementClub from the typeAbonnementClubs array
      this.typeAbonnementClubs = this.typeAbonnementClubs.filter(typeAbonnementClub => typeAbonnementClub.id !== typeAbonnementClubId);
  
      // Fetch the updated list of typeAbonnementClubs from the backend
      this.gettypeAbonnementClubs();
    }, error => {
      console.error('Error deleting typeAbonnementClub:', error);
    });
  }
  

  showtypeAbonnementClubFormModal() {
    this.isModalVisible = true;
  }

  hidetypeAbonnementClubFormModal() {
    this.isModalVisible = false;
  }

  submitForm() {
    // Perform form submission logic here
    console.log({
      id:this.id,
      libType:this.libType,
      
    });
    this.hidetypeAbonnementClubFormModal();
  }

  resetForm() {
    this.id='';
    this.libType='';
    
  }

  openLargeModalAdd() {
    const modalRef = this.modalService.open(CreateTypeAbonnementClubComponent, { size: 'xl', centered: true });
    modalRef.componentInstance.name = 'World';
    modalRef.result.then((result) => {
      if (result === 'saved') {
        this.gettypeAbonnementClubs();
      }
    }).catch((error) => {
      console.log(error);
    });
  }
  
  openLargeModalEdit(typeAbonnementClub: any) {
    const modalRef = this.modalService.open(UpdateTypeAbonnementClubComponent, { size: 'xl', centered: true });
    modalRef.componentInstance.typeAbonnementClub = { ...typeAbonnementClub }; // Pass a copy of the typeAbonnementClub object
  
    modalRef.result.then((result) => {
      if (result === 'updated') {
        // Handle success, e.g., refresh the typeAbonnementClub list
        this.gettypeAbonnementClubs();
      }
    }).catch((error) => {
      console.log(error);
    });
  }
}