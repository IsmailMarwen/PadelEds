import { Component, OnInit, Renderer2, Inject, PLATFORM_ID, NgZone, ElementRef, TemplateRef,AfterViewInit  } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { NgbOffcanvas } from '@ng-bootstrap/ng-bootstrap';
import Scrollbar from 'smooth-scrollbar';
import AOS from 'aos';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { ChangeDetectorRef } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';
import { NgToastService,ToastType } from 'ng-angular-popup';
import { CreateUserSuperAdminComponent } from '../create-user-super-admin/create-user-super-admin.component';
import { UpdateUserSuperAdminComponent } from '../update-user-super-admin/update-user-super-admin.component';
declare var window: any;
declare const Waypoint: any;
declare const CircleProgress: any;
declare const ApexCharts: any;
@Component({
  selector: 'app-complexes',
  templateUrl: './complexes.component.html',
  styleUrl: './complexes.component.css'
})
export class ComplexesComponent implements OnInit,AfterViewInit  {
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
    private toast:NgToastService,
  ) {}
 preload:boolean=true
 themeDetail1:string="#fcbc04"
 themeDetail2:string="#888"
 private selectedTheme:string='theme1'
 users = [
  { profileImage: 'assets/images/shapes/01.png', nom: 'Anna', prenom: 'Sthesia', email: 'annasthesia.com', telephone: '(760) 756 7568' },
  { profileImage: 'assets/images/shapes/02.png', nom: 'Brock', prenom: 'Lee', email: 'brocklee.com', telephone: '+62 5689 458 658' },
  { profileImage: 'assets/images/shapes/03.png', nom: 'Dan', prenom: 'Druff', email: 'dandruff.com', telephone: '+55 6523 456 856' },
  { profileImage: 'assets/images/shapes/04.png', nom: 'Hans', prenom: 'Olo', email: 'hansolo.com', telephone: '+91 2586 253 125' }
];
clubs:any = [];
 ngAfterViewInit() {
    }
    
    ngOnInit() {
      this.service.getAllClub().subscribe(data=>{
        this.clubs=data
        console.log(this.clubs)
      })
        this.applyTheme('theme1')
        setTimeout(() => {
          this.preload = false;
          this.initAll()

        }, 2000);
   
     
  
     
   
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
   initAll() {
    this.initAOS();

    this.initSidebarToggle();
    this.initSidebarState();
    this.scheduleScrollbarInit();
    this.initCircleProgress();
    this.initChart();
    this.initProgressBar();
    this.initChartBar();
    this.initChartRadial();
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
            colors: "#fcbc04",
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
            colors: "#fcbc04",
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
getThemeColors(theme: string): { custombodyclass: string, detail1: string, detail2: string } | null {
  switch (theme) {
    case 'theme1': return { custombodyclass: 'theme-color-eds', detail1: '#888', detail2: '#fcbc04' };
    case 'theme2': return { custombodyclass: 'theme-color-gray', detail1: '#91969E', detail2: '#FD8D00' };
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
openModal() {
  const modalRef = this.modalService.open(CreateUserSuperAdminComponent, { size: 'xl', centered: true }); // Utilisez size: 'xl' pour un modal de grande taille
  modalRef.componentInstance.name = 'World';
  modalRef.result.then((result) => {
    console.log(result);
  }).catch((error) => {
    console.log(error);
  });
}
EditUser() {
  const modalRef = this.modalService.open(UpdateUserSuperAdminComponent, { size: 'xl', centered: true }); // Utilisez size: 'xl' pour un modal de grande taille
  modalRef.componentInstance.name = 'World';
  modalRef.result.then((result) => {
    console.log(result);
  }).catch((error) => {
    console.log(error);
  });
}
goToUsers(){
  this.router.navigate(["/eds/admin/users"])
}
goHome(){
  this.router.navigate(["/eds/admin/home"])
}
goComplexes(){
  this.router.navigate(["/eds/admin/complexes"])
}
openEnd(content: TemplateRef<any>) {
  this.offcanvasService.open(content, { position: 'end' });
}
goToconfig(){
  this.router.navigate(["/eds/admin/configApp/1"])

}
}
