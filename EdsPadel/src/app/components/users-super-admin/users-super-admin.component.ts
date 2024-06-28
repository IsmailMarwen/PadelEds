import { Component, OnInit, Renderer2, Inject, PLATFORM_ID, NgZone, ElementRef, TemplateRef,AfterViewInit  } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { NgbOffcanvas } from '@ng-bootstrap/ng-bootstrap';
import Scrollbar from 'smooth-scrollbar';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { ChangeDetectorRef } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { NgToastService,ToastType } from 'ng-angular-popup';
import { CreateUserSuperAdminComponent } from '../create-user-super-admin/create-user-super-admin.component';
import { UpdateUserSuperAdminComponent } from '../update-user-super-admin/update-user-super-admin.component';
declare var window: any;
declare const Waypoint: any;


@Component({
  selector: 'app-users-super-admin',
  templateUrl: './users-super-admin.component.html',
  styleUrl: './users-super-admin.component.css'
})
export class UsersSuperAdminComponent implements OnInit,AfterViewInit  {
  users : any[] = [];

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
  originalUsers: any[] = [];
id:string='';
  image:string='';
  nom: string = '';
  email: string = '';
  prenom: string = '';
  telephone: string = '';
  password: string = '';
 ngAfterViewInit() {
  this.getUsers();
    }
    
    ngOnInit() {
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
    const modalRef = this.modalService.open(CreateUserSuperAdminComponent, { size: 'xl', centered: true });
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
EditUser(user: any) {
  const modalRef = this.modalService.open(UpdateUserSuperAdminComponent, { size: 'xl', centered: true }); // Utilisez size: 'xl' pour un modal de grande taille
  modalRef.componentInstance.user = { ...user }; // Pass a copy of the user object
  modalRef.result.then((result) => {
    if (result === 'updated') {
      // Handle success, e.g., refresh the user list
      this.getUsers();
    }
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
goAbonnements(){
  this.router.navigate(["/eds/admin/abonnements"])
}
goActivites(){
  this.router.navigate(["/eds/admin/activites"])
}

searchUsers(event: any): void {
  const searchTerm = event.target.value.toLowerCase();
  this.users = this.originalUsers.filter(user => {
    return Object.values(user).some((value: any) => 
      String(value).toLowerCase().includes(searchTerm)
    );
  });
}
getUsers(): void {
  this.service.getUsers().subscribe(superAdmin => {
    this.users = superAdmin;
    this.originalUsers = superAdmin;
  });
}
deleteSuperUser(userId: number): void {
  this.service.deleteSuperUser(userId).subscribe(() => {
    // Remove the deleted user from the users array
    this.users = this.users.filter(user => user.id !== userId);

    // Fetch the updated list of users from the backend
    this.getUsers();
  }, error => {
    console.error('Error deleting user:', error);
  });
}

  




}
