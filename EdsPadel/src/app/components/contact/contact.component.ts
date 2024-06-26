import { Component,OnInit,Renderer2,Inject,PLATFORM_ID,NgZone } from '@angular/core';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { NgToastService,ToastType } from 'ng-angular-popup';
import * as L from 'leaflet'; // Importation de Leaflet
import { isPlatformBrowser } from '@angular/common';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ResetPasswordComponent } from '../reset-password/reset-password.component';
@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.css'
})
export class ContactComponent implements OnInit {
  constructor(private service:AppwebserviceService,private router:Router,private toast:NgToastService,private renderer: Renderer2,@Inject(PLATFORM_ID) private platformId: Object,
  private ngZone: NgZone,    private modalService: NgbModal,

){}
  city: any;
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
  adresse:any
  horaireInfos:any
  preload:boolean=true
  themeDetail1:any
  themeDetail2:any
  preloadButton:boolean=false
  tel:any
  prenom:any
  emailUser:any
  sujet:any
  description:any
  ngOnInit(): void {
    
    this.applyTheme('theme1')//important
    this.idClub=localStorage.getItem("idClub")
    this.service.getInfoClub(this.idClub).subscribe(data=>{
        this.city=data.ville
        this.applyTheme(data.appWeb.couleurAppWeb);
        this.logoApp=data.appWeb.logoAppWeb
        this.nomClub=data.nomClub
        this.adresseUrl=data.appWeb.adresseUrl
        this.bannerImage=data.appWeb.bannerImage
        this.latitude=data.latitude
        this.longitude=data.longitude
        this.adresse=data.adresse
        this.horaireInfos=data.horaireInfos
        this.tel=data.telPrincipal
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
  contactClub(){
    this.preloadButton=true
    let formValid = true;
 
  if (!this.nom) {
    formValid = false;
    document.getElementById('fname')?.classList.add('error');
  } else {
    document.getElementById('fname')?.classList.remove('error');
  }
  if (!this.prenom) {
    formValid = false;
    document.getElementById('lname')?.classList.add('error');
  } else {
    document.getElementById('lname')?.classList.remove('error');
  }
  if (!this.emailUser) {
    formValid = false;
    document.getElementById('emailUser')?.classList.add('error');
  } else {
    document.getElementById('emailUser')?.classList.remove('error');
  }
  if (!this.sujet) {
    formValid = false;
    document.getElementById('sujet')?.classList.add('error');
  } else {
    document.getElementById('sujet')?.classList.remove('error');
  }
  if (!this.description) {
    formValid = false;
    document.getElementById('description')?.classList.add('error');
  } else {
    document.getElementById('description')?.classList.remove('error');
  }
  if (!formValid) {
    setTimeout(() => {
      this.preloadButton = false;
      this.toast.toast('Veuillez remplir tous les champs obligatoires.', ToastType.DANGER, 'Erreur', 5000);
    }, 2000);
    return;
  }
   var data= {
      "nom": this.nom,
      "prenom": this.prenom,
      "emailUser":this.emailUser,
      "sujet":this.sujet,
      "description":this.description,
      "idClub": this.idClub
     
    }

    this.service.contactClub(data).subscribe(data=>{

      this.toast.toast('Message envoyé avec succées.', ToastType.SUCCESS, 'Success', 5000);
      this.preloadButton=false
      this.nom=null
      this.prenom=null
      this.emailUser=null
      this.sujet=null
      this.description=null
        })
  }
}
