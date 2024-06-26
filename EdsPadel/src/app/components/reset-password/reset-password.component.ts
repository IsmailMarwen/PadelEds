import { Component,OnInit, Renderer2, Inject, PLATFORM_ID, NgZone } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { isPlatformBrowser } from '@angular/common';
import { NgToastService,ToastType } from 'ng-angular-popup';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrl: './reset-password.component.css'
})
export class ResetPasswordComponent implements OnInit {
  currentMode: any;
  idClub:any
  email:any
  preload:boolean=false
 constructor(public activeModal: NgbActiveModal,public service:AppwebserviceService, private renderer: Renderer2,
  @Inject(PLATFORM_ID) private platformId: Object,
  private ngZone: NgZone,private toast:NgToastService){}
 ngOnInit(): void {
    this.idClub=localStorage.getItem("idClub")
    this.service.getInfoClub(this.idClub).subscribe(data=>{
      this.applyTheme(data.appWeb.couleurAppWeb);
      const themeColors = this.getThemeColors(data.appWeb.couleurAppWeb);
      this.activateMode('color-mode', data.appWeb.mode);
      if(data.appWeb.mode=="dark"){
        this.applyDarkModeToTable(true)
        this.updateInputMode(true);
      }else{
        this.applyDarkModeToTable(false)
        this.updateInputMode(false);
      }
      this.currentMode = data.appWeb.mode;

      
      
   
      
    })
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
closeModal() {
  this.activeModal.dismiss('Cross click');
}
handleModeChange(event: Event) {
  const customEvent = event as CustomEvent;
  if (customEvent.detail.dark) {
    this.currentMode = 'dark';
  } else if (customEvent.detail.light) {
    this.currentMode = 'light';
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
updateInputMode(isDark: boolean): void {
  const inputElement = document.querySelector('.form-contorl');
  if (inputElement) {
    if (isDark) {
      inputElement.classList.add('dark');
    } else {
      inputElement.classList.remove('dark');
    }
  }
}
resetPassword(){
  this.preload=true
  var data={
    "email":this.email,
"club":{"idClub":localStorage.getItem('idClub')}
  }
  this.service.resetPassword(data).subscribe(res=>{
    this.preload=false
    this.toast.toast('Un email a été envoyé à ' + this.email + '. Veuillez vérifier votre boîte de réception.', ToastType.SUCCESS, 'Succès', 5000);
    this.closeModal()
  })
}
}
