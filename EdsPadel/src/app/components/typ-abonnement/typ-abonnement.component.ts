import { Component, OnInit, Renderer2, Inject, PLATFORM_ID, NgZone, ElementRef, AfterViewInit } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { NgbOffcanvas, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import Scrollbar from 'smooth-scrollbar';
import AOS from 'aos';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { CreateTypAbonnementComponent } from '../create-typ-abonnement/create-typ-abonnement.component';
import { UpdateTypAbonnementComponent } from '../update-typ-abonnement/update-typ-abonnement.component';

declare var window: any;
declare const Waypoint: any;
declare const CircleProgress: any;
declare const ApexCharts: any;

@Component({
  selector: 'app-typ-abonnement',
  templateUrl: './typ-abonnement.component.html',
  styleUrls: ['./typ-abonnement.component.css']
})
export class TypAbonnementComponent implements OnInit, AfterViewInit {
  abonnements: any[] = [];
  originalAbonnements: any[] = [];
  preload: boolean = true;
  themeDetail1: string = "#fcbc04";
  themeDetail2: string = "#888";
  private selectedTheme: string = 'theme1';

  codeTypeAbonnement: string = '';
  libTypeAbonnement: string = '';
  mthtTypeAbonnement: string = '';
  tauxTva: string = '';
  mtttc: string = '';
  nbMois: string = '';
  couleur:string=''
  constructor(
    private renderer: Renderer2,
    @Inject(PLATFORM_ID) private platformId: Object,
    private ngZone: NgZone,
    private elRef: ElementRef,
    private offcanvasService: NgbOffcanvas,
    private modalService: NgbModal,
    private cdr: ChangeDetectorRef,
    private service: AppwebserviceService,
    private router: Router,
    private toast: NgToastService,
  ) {}

  ngAfterViewInit() {
    this.getAbonnements();
  }

  ngOnInit() {
    this.applyTheme('theme1');
    setTimeout(() => {
      this.preload = false;
      this.initAll();
    }, 2000);
  }

  applyTheme(theme: string): void {
    this.ngZone.run(() => {
      const themeColors = this.getThemeColors(theme);
      if (themeColors) {
        this.customizerMode(themeColors.custombodyclass, themeColors.detail1, themeColors.detail2);
        this.selectedTheme = theme;
      }
    });
  }

  initAll() {

    this.initSidebarToggle();
    this.initSidebarState();
    this.scheduleScrollbarInit();
  
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
 
  getThemeColors(theme: string): { custombodyclass: string, detail1: string, detail2: string } | null {
    switch (theme) {
      case 'theme1':
        return { custombodyclass: 'theme-color-eds', detail1: '#888', detail2: '#fcbc04' };
      case 'theme2':
        return { custombodyclass: 'theme-color-gray', detail1: '#91969E', detail2: '#FD8D00' };
      default:
        return null;
    }
  }

  customizerMode(custombodyclass: string, detail1: string, detail2: string | null): void {
    if (isPlatformBrowser(this.platformId)) {
      const bodyClasses = document.body.classList;
      const themeClasses = Array.from(bodyClasses).filter(cls => cls.startsWith('theme-color-'));
      themeClasses.forEach(cls => bodyClasses.remove(cls));
      this.renderer.addClass(document.body, custombodyclass);
      // Modify styles or perform actions based on custom body class and details 1 & 2
    }
  }

  openModal() {
    const modalRef = this.modalService.open(CreateTypAbonnementComponent, { size: 'xl', centered: true });
    modalRef.componentInstance.name = 'World';
    modalRef.result.then((result) => {
      if (result === 'saved') {
        this.getAbonnements();
      }
    }).catch((error) => {
      console.log(error);
    });
  }

  editAbonnement(abonnement: any) {
    const modalRef = this.modalService.open(UpdateTypAbonnementComponent, { size: 'xl', centered: true }); // Utilisez size: 'xl' pour un modal de grande taille
    modalRef.componentInstance.abonnement = { ...abonnement }; // Pass a copy of the user object
    modalRef.result.then((result) => {
      if (result === 'updated') {
        // Handle success, e.g., refresh the user list
        this.getAbonnements();
      }
    }).catch((error) => {
      console.log(error);
    });  }
 

  goToUsers() {
    this.router.navigate(['/eds/admin/users']);
  }

  goHome() {
    this.router.navigate(['/eds/admin/home']);
  }

  goComplexes() {
    this.router.navigate(['/eds/admin/complexes']);
  }

  goActivites() {
    this.router.navigate(['/eds/admin/activites']);
  }

  searchAbonnements(event: any): void {
    const searchTerm = event.target.value.toLowerCase();
    this.abonnements = this.originalAbonnements.filter(abonnement => {
      return Object.values(abonnement).some((value: any) =>
        String(value).toLowerCase().includes(searchTerm)
      );
    });
  }

  getAbonnements(): void {
    this.service.getAbonnements().subscribe(superAdmin => {
      console.log(superAdmin)
      this.abonnements = superAdmin;
      this.originalAbonnements = superAdmin;
    });
  }

  deleteAbonnement(abonnementId: number): void {
    this.service.deleteSuperAbonnement(abonnementId).subscribe(() => {
      this.abonnements = this.abonnements.filter(abonnement => abonnement.id !== abonnementId);
      this.getAbonnements(); // Fetch updated list
    }, error => {
      console.error('Error deleting abonnement:', error);
    });
  }
}