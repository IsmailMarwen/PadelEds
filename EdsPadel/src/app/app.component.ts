import { Component, OnInit ,TemplateRef, Renderer2, Inject, PLATFORM_ID,NgZone,inject,AfterViewInit } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { NgbOffcanvas } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit ,AfterViewInit {
  constructor(
    private renderer: Renderer2,
    @Inject(PLATFORM_ID) private platformId: Object,
    private ngZone: NgZone,
    private offcanvasService: NgbOffcanvas
  ) {}
  ngOnInit() {
  }
  ngAfterViewInit(): void {
    this.initSidebarToggle()

  }
	openEnd(content: TemplateRef<any>) {
		this.offcanvasService.open(content, { position: 'end' });
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
      // Remove existing theme classes
      const bodyClasses = document.body.classList;
      const themeClasses = Array.from(bodyClasses).filter(cls => cls.startsWith('theme-color-'));
      themeClasses.forEach(cls => bodyClasses.remove(cls));
      this.renderer.addClass(document.body, custombodyclass);
      const primaryColor = getComputedStyle(document.body).getPropertyValue('--bs-primary');
      sessionStorage.setItem('colorcustomchart-mode', primaryColor);
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
}
