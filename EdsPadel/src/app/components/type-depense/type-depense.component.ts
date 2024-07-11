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
import { CreateTypeDepenseComponent } from '../create-type-depense/create-type-depense.component';
import { UpdateTypeDepenseComponent } from '../update-type-depense/update-type-depense.component';
declare var window: any;
declare const Waypoint: any;
declare const CircleProgress: any;
declare const ApexCharts: any;

@Component({
  selector: 'app-type-depense',
  templateUrl: './type-depense.component.html',
  styleUrl: './type-depense.component.css'
})
export class typeDepenseeComponent {
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
  designation:string='';
  
  isModalVisible: boolean = false;
  searchQuery: string = '';
  typeDepenses: any[] = [];
  originaltypeDepenses: any[] = []; // Array to store original typeDepenses before filtering
  
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
    this.gettypeDepenses();
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
    this.initAOS();
    this.initCircleProgress();
    this.initChart();
    this.initProgressBar();
    this.initChartBar();
    this.initChartRadial();
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
  this.initAOS();
  this.initCircleProgress();
  this.initChart();
  this.initProgressBar();
  this.initChartBar();
  this.initChartRadial();
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
 initChartRadial(): void {
  if (document.querySelectorAll('#myChart').length) {
    const themeColors = this.getThemeColors(this.selectedTheme);
    if (themeColors) {
    const options = {
      series: [55, 75],
      chart: {
        height: 230,
        type: 'radialBar',
      },
      colors: [themeColors.detail1, themeColors.detail2],
      plotOptions: {
        radialBar: {
          hollow: {
            margin: 10,
            size: "50%",
          },
          track: {
            margin: 10,
            strokeWidth: '50%',
          },
          dataLabels: {
            show: false,
          }
        }
      },
      labels: ['Apples', 'Oranges'],
    };

    if (typeof ApexCharts !== 'undefined') {
      const chart = new ApexCharts(document.querySelector("#myChart"), options);
      chart.render();

      document.addEventListener('ColorChange', (e:any) => {
        const newOpt = { colors: [e.detail.detail2, e.detail.detail1] };
        chart.updateOptions(newOpt);
      });
    }
  }}
}
 initChartBar(): void {
  if (document.querySelectorAll('#d-activity').length) {
    const themeColors = this.getThemeColors(this.selectedTheme);
    if (themeColors) {

    const options = {
      series: [{
        name: 'Successful deals',
        data: [30, 50, 35, 60, 40, 60, 60, 30, 50, 35]
      }, {
        name: 'Failed deals',
        data: [40, 50, 55, 50, 30, 80, 30, 40, 50, 55]
      }],
      chart: {
        type: 'bar',
        height: 230,
        stacked: true,
        toolbar: {
          show: false
        }
      },
      colors: [themeColors.detail1, themeColors.detail2],
      plotOptions: {
        bar: {
          horizontal: false,
          columnWidth: '28%',
          endingShape: 'rounded',
          borderRadius: 5,
        },
      },
      legend: {
        show: false
      },
      dataLabels: {
        enabled: false
      },
      stroke: {
        show: true,
        width: 2,
        colors: ['transparent']
      },
      xaxis: {
        categories: ['S', 'M', 'T', 'W', 'T', 'F', 'S', 'M', 'T', 'W'],
        labels: {
          minHeight: 20,
          maxHeight: 20,
          style: {
            colors: "#8A92A6",
          },
        }
      },
      yaxis: {
        title: {
          text: ''
        },
        labels: {
          minWidth: 19,
          maxWidth: 19,
          style: {
            colors: "#8A92A6",
          },
        }
      },
      fill: {
        opacity: 1
      },
      tooltip: {
        y: {
          formatter: function (val: any) {
            return "$ " + val + " thousands";
          }
        }
      }
    };

    const chart = new ApexCharts(document.querySelector("#d-activity"), options);
    chart.render();
    document.addEventListener('ColorChange', (e:any) => {
      const newOpt = { colors: [e.detail.detail1, e.detail.detail2] };
      chart.updateOptions(newOpt);
    });
  }}
}
 initChart(): void {

  if (document.querySelectorAll('#d-main').length) {
    const themeColors = this.getThemeColors(this.selectedTheme);
    if (themeColors) {
    const options = {
      series: [{
        name: 'total',
        data: [94, 80, 94, 80, 94, 80, 94]
      }, {
        name: 'pipline',
        data: [72, 60, 84, 60, 74, 60, 78]
      }],
      chart: {
        fontFamily: '"Inter", sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji"',
        height: 245,
        type: 'area',
        toolbar: {
          show: false
        },
        sparkline: {
          enabled: false,
        },
      },
      colors: [themeColors.detail1, themeColors.detail2],
      dataLabels: {
        enabled: false
      },
      stroke: {
        curve: 'smooth',
        width: 3,
      },
      yaxis: {
        show: true,
        labels: {
          show: true,
          minWidth: 19,
          maxWidth: 19,
          style: {
            colors: "#8A92A6",
          },
          offsetX: -5,
        },
      },
      legend: {
        show: false,
      },
      xaxis: {
        labels: {
          minHeight: 22,
          maxHeight: 22,
          show: true,
          style: {
            colors: "#8A92A6",
          },
        },
        lines: {
          show: false  //or just here to disable only x axis grids
        },
        categories: ["Jan", "Feb", "Mar", "Apr", "Jun", "Jul", "Aug"]
      },
      grid: {
        show: false,
      },
      fill: {
        type: 'gradient',
        gradient: {
          shade: 'dark',
          type: "vertical",
          shadeIntensity: 0,
          gradientToColors: undefined, // optional, if not defined - uses the shades of same color in series
          inverseColors: true,
          opacityFrom: .4,
          opacityTo: .1,
          stops: [0, 50, 80],
          colors: ["#3a57e8", "#4bc7d2"]
        }
      },
      tooltip: {
        enabled: true,
      },
    };

    const chart = new ApexCharts(document.querySelector("#d-main"), options);
    chart.render();
    const tooltipText = document.querySelector('.apexcharts-tooltip-text');

    document.addEventListener('ColorChange', (e:any) => {
      const newOpt = {
        colors: [e.detail.detail1, e.detail.detail2],
        fill: {
          type: 'gradient',
          gradient: {
            shade: 'dark',
            type: "vertical",
            shadeIntensity: 0,
            gradientToColors: [e.detail.detail1, e.detail.detail2], // optional, if not defined - uses the shades of same color in series
            inverseColors: true,
            opacityFrom: .4,
            opacityTo: .1,
            stops: [0, 50, 60],
            colors: [e.detail.detail1, e.detail.detail2],
          }
        },
      }
      chart.updateOptions(newOpt);
    });
  }}
}
progressBarInit(elem: HTMLElement): void {
  const currentValue = elem.getAttribute('aria-valuenow');
  elem.style.width = '0%';
  elem.style.transition = 'width 2s';
  if (typeof Waypoint !== 'undefined') {
    new Waypoint({
      element: elem,
      handler: function () {
        setTimeout(() => {
          elem.style.width = currentValue + '%';
        }, 100);
      },
      offset: 'bottom-in-view',
    });
  }
}

initProgressBar(): void {
  const customProgressBar = document.querySelectorAll('[data-toggle="progress-bar"]');
  Array.prototype.slice.call(customProgressBar).forEach((elem: HTMLElement) => {
    this.progressBarInit(elem);
  });
}
 initCircleProgress(): void {
  const progressBar = document.querySelectorAll('.circle-progress');
  if (progressBar) {
    progressBar.forEach((elem: any) => {
      const minValue = elem.getAttribute('data-min-value');
      const maxValue = elem.getAttribute('data-max-value');
      const value = elem.getAttribute('data-value');
      const type = elem.getAttribute('data-type');
      if (elem.id !== '' && elem.id !== null) {
        new CircleProgress(`#${elem.id}`, {
          min: minValue,
          max: maxValue,
          value: value,
          textFormat: type,
        });
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
  
  
  
  gettypeDepenses(): void {
    const clubId = localStorage.getItem("idClub");
    if(clubId)
    this.service.getAlltypeDepenses(clubId).subscribe(typeDepenses => {
      this.typeDepenses = typeDepenses;
      this.originaltypeDepenses = typeDepenses;
    });
    else {
      console.error('No club ID found in local storage');
    }
  }

  searchtypeDepenses(event: any): void {
    const searchTerm = event.target.value.toLowerCase();
    this.typeDepenses = this.originaltypeDepenses.filter(typeDepense => {
      return Object.values(typeDepense).some((value: any) => 
        String(value).toLowerCase().includes(searchTerm)
      );
    });
  }
  
  deletetypeDepense(typeDepenseId: number): void {
    this.service.deletetypeDepense(typeDepenseId).subscribe(() => {
      // Remove the deleted typeDepense from the typeDepenses array
      this.typeDepenses = this.typeDepenses.filter(typeDepense => typeDepense.id !== typeDepenseId);
  
      // Fetch the updated list of typeDepenses from the backend
      this.gettypeDepenses();
    }, error => {
      console.error('Error deleting typeDepense:', error);
    });
  }
  

  showtypeDepenseFormModal() {
    this.isModalVisible = true;
  }

  hidetypeDepenseFormModal() {
    this.isModalVisible = false;
  }

  submitForm() {
    // Perform form submission logic here
    console.log({
      id:this.id,
      designation:this.designation,
      
    });
    this.hidetypeDepenseFormModal();
  }

  resetForm() {
    this.id='';
    this.designation='';
    
  }

  openLargeModalAdd() {
    const modalRef = this.modalService.open(CreateTypeDepenseComponent, { size: 'xl', centered: true });
    modalRef.componentInstance.name = 'World';
    modalRef.result.then((result) => {
      if (result === 'saved') {
        this.gettypeDepenses();
      }
    }).catch((error) => {
      console.log(error);
    });
  }
  
  openLargeModalEdit(typeDepense: any) {
    const modalRef = this.modalService.open(UpdateTypeDepenseComponent, { size: 'xl', centered: true });
    modalRef.componentInstance.typeDepense = { ...typeDepense }; // Pass a copy of the typeDepense object
  
    modalRef.result.then((result) => {
      if (result === 'updated') {
        // Handle success, e.g., refresh the typeDepense list
        this.gettypeDepenses();
      }
    }).catch((error) => {
      console.log(error);
    });
  }
}