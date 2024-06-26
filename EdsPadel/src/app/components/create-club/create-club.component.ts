import { Component, OnInit,ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { AppwebserviceService } from '../../services/appwebservice.service';
import * as L from 'leaflet'; // Importation de Leaflet
import { MessageService } from 'primeng/api';
import { NgToastService,ToastType } from 'ng-angular-popup';
import { title } from 'process';
interface Country {
  name: string;
  flagUrl: string;
}
@Component({
  selector: 'app-create-club',
  templateUrl: './create-club.component.html',
  styleUrls: ['./create-club.component.css']
})

export class CreateClubComponent implements OnInit {
  countries: Country[] = [
    { name: 'Afghanistan', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/af.svg' },
    { name: 'Albania', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/al.svg' },
    { name: 'Algeria', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/dz.svg' },
    { name: 'Andorra', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ad.svg' },
    { name: 'Angola', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ao.svg' },
    { name: 'Antigua and Barbuda', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ag.svg' },
    { name: 'Argentina', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ar.svg' },
    { name: 'Armenia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/am.svg' },
    { name: 'Australia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/au.svg' },
    { name: 'Austria', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/at.svg' },
    { name: 'Azerbaijan', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/az.svg' },
    { name: 'Bahamas', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bs.svg' },
    { name: 'Bahrain', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bh.svg' },
    { name: 'Bangladesh', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bd.svg' },
    { name: 'Barbados', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bb.svg' },
    { name: 'Belarus', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/by.svg' },
    { name: 'Belgium', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/be.svg' },
    { name: 'Belize', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bz.svg' },
    { name: 'Benin', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bj.svg' },
    { name: 'Bhutan', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bt.svg' },
    { name: 'Bolivia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bo.svg' },
    { name: 'Bosnia and Herzegovina', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ba.svg' },
    { name: 'Botswana', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bw.svg' },
    { name: 'Brazil', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/br.svg' },
    { name: 'Brunei', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bn.svg' },
    { name: 'Bulgaria', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bg.svg' },
    { name: 'Burkina Faso', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bf.svg' },
    { name: 'Burundi', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bi.svg' },
    { name: 'Cambodia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/kh.svg' },
    { name: 'Cameroon', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/cm.svg' },
    { name: 'Canada', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ca.svg' },
    { name: 'Cape Verde', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/cv.svg' },
    { name: 'Central African Republic', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/cf.svg' },
    { name: 'Chad', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/td.svg' },
    { name: 'Chile', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/cl.svg' },
    { name: 'China', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/cn.svg' },
    { name: 'Colombia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/co.svg' },
    { name: 'Comoros', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/km.svg' },
    { name: 'Congo (Congo-Brazzaville)', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/cg.svg' },
    { name: 'Costa Rica', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/cr.svg' },
    { name: 'Croatia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/hr.svg' },
    { name: 'Cuba', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/cu.svg' },
    { name: 'Cyprus', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/cy.svg' },
    { name: 'Czechia (Czech Republic)', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/cz.svg' },
    { name: 'Denmark', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/dk.svg' },
    { name: 'Djibouti', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/dj.svg' },
    { name: 'Dominica', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/dm.svg' },
    { name: 'Dominican Republic', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/do.svg' },
    { name: 'East Timor (Timor-Leste)', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/tl.svg' },
    { name: 'Ecuador', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ec.svg' },
    { name: 'Egypt', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/eg.svg' },
    { name: 'El Salvador', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sv.svg' },
    { name: 'Equatorial Guinea', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/gq.svg' },
    { name: 'Eritrea', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/er.svg' },
    { name: 'Estonia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ee.svg' },
    { name: 'Eswatini (fmr. "Swaziland")', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sz.svg' },
    { name: 'Ethiopia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/et.svg' },
    { name: 'Fiji', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/fj.svg' },
    { name: 'Finland', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/fi.svg' },
    { name: 'France', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/fr.svg' },
    { name: 'Gabon', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ga.svg' },
    { name: 'Gambia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/gm.svg' },
    { name: 'Georgia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ge.svg' },
    { name: 'Germany', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/de.svg' },
    { name: 'Ghana', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/gh.svg' },
    { name: 'Greece', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/gr.svg' },
    { name: 'Grenada', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/gd.svg' },
    { name: 'Guatemala', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/gt.svg' },
    { name: 'Guinea', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/gn.svg' },
    { name: 'Guinea-Bissau', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/gw.svg' },
    { name: 'Guyana', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/gy.svg' },
    { name: 'Haiti', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ht.svg' },
    { name: 'Honduras', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/hn.svg' },
    { name: 'Hungary', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/hu.svg' },
    { name: 'Iceland', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/is.svg' },
    { name: 'India', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/in.svg' },
    { name: 'Indonesia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/id.svg' },
    { name: 'Iran', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ir.svg' },
    { name: 'Iraq', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/iq.svg' },
    { name: 'Ireland', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ie.svg' },
    { name: 'Israel', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/il.svg' },
    { name: 'Italy', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/it.svg' },
    { name: 'Jamaica', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/jm.svg' },
    { name: 'Japan', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/jp.svg' },
    { name: 'Jordan', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/jo.svg' },
    { name: 'Kazakhstan', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/kz.svg' },
    { name: 'Kenya', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ke.svg' },
    { name: 'Kiribati', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ki.svg' },
    { name: 'Kosovo', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/xk.svg' },
    { name: 'Kuwait', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/kw.svg' },
    { name: 'Kyrgyzstan', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/kg.svg' },
    { name: 'Laos', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/la.svg' },
    { name: 'Latvia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/lv.svg' },
    { name: 'Lebanon', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/lb.svg' },
    { name: 'Lesotho', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ls.svg' },
    { name: 'Liberia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/lr.svg' },
    { name: 'Libya', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ly.svg' },
    { name: 'Liechtenstein', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/li.svg' },
    { name: 'Lithuania', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/lt.svg' },
    { name: 'Luxembourg', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/lu.svg' },
    { name: 'Madagascar', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mg.svg' },
    { name: 'Malawi', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mw.svg' },
    { name: 'Malaysia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/my.svg' },
    { name: 'Maldives', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mv.svg' },
    { name: 'Mali', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ml.svg' },
    { name: 'Malta', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mt.svg' },
    { name: 'Marshall Islands', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mh.svg' },
    { name: 'Mauritania', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mr.svg' },
    { name: 'Mauritius', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mu.svg' },
    { name: 'Mexico', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mx.svg' },
    { name: 'Micronesia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/fm.svg' },
    { name: 'Moldova', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/md.svg' },
    { name: 'Monaco', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mc.svg' },
    { name: 'Mongolia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mn.svg' },
    { name: 'Montenegro', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/me.svg' },
    { name: 'Morocco', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ma.svg' },
    { name: 'Mozambique', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mz.svg' },
    { name: 'Myanmar', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mm.svg' },
    { name: 'Namibia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/na.svg' },
    { name: 'Nauru', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/nr.svg' },
    { name: 'Nepal', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/np.svg' },
    { name: 'Netherlands', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/nl.svg' },
    { name: 'New Zealand', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/nz.svg' },
    { name: 'Nicaragua', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ni.svg' },
    { name: 'Niger', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ne.svg' },
    { name: 'Nigeria', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ng.svg' },
    { name: 'North Korea', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/kp.svg' },
    { name: 'North Macedonia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mk.svg' },
    { name: 'Norway', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/no.svg' },
    { name: 'Oman', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/om.svg' },
    { name: 'Pakistan', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/pk.svg' },
    { name: 'Palau', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/pw.svg' },
    { name: 'Palestine State', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ps.svg' },
    { name: 'Panama', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/pa.svg' },
    { name: 'Papua New Guinea', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/pg.svg' },
    { name: 'Paraguay', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/py.svg' },
    { name: 'Peru', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/pe.svg' },
    { name: 'Philippines', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ph.svg' },
    { name: 'Poland', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/pl.svg' },
    { name: 'Portugal', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/pt.svg' },
    { name: 'Qatar', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/qa.svg' },
    { name: 'Romania', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ro.svg' },
    { name: 'Russia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ru.svg' },
    { name: 'Rwanda', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/rw.svg' },
    { name: 'Saint Kitts and Nevis', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/kn.svg' },
    { name: 'Saint Lucia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/lc.svg' },
    { name: 'Saint Vincent and the Grenadines', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/vc.svg' },
    { name: 'Samoa', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ws.svg' },
    { name: 'San Marino', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sm.svg' },
    { name: 'Sao Tome and Principe', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/st.svg' },
    { name: 'Saudi Arabia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sa.svg' },
    { name: 'Senegal', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sn.svg' },
    { name: 'Serbia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/rs.svg' },
    { name: 'Seychelles', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sc.svg' },
    { name: 'Sierra Leone', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sl.svg' },
    { name: 'Singapore', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sg.svg' },
    { name: 'Slovakia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sk.svg' },
    { name: 'Slovenia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/si.svg' },
    { name: 'Solomon Islands', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sb.svg' },
    { name: 'Somalia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/so.svg' },
    { name: 'South Africa', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/za.svg' },
    { name: 'South Korea', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/kr.svg' },
    { name: 'South Sudan', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ss.svg' },
    { name: 'Spain', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/es.svg' },
    { name: 'Sri Lanka', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/lk.svg' },
    { name: 'Sudan', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sd.svg' },
    { name: 'Suriname', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sr.svg' },
    { name: 'Sweden', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/se.svg' },
    { name: 'Switzerland', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ch.svg' },
    { name: 'Syria', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sy.svg' },
    { name: 'Taiwan', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/tw.svg' },
    { name: 'Tajikistan', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/tj.svg' },
    { name: 'Tanzania', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/tz.svg' },
    { name: 'Thailand', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/th.svg' },
    { name: 'Togo', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/tg.svg' },
    { name: 'Tonga', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/to.svg' },
    { name: 'Trinidad and Tobago', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/tt.svg' },
    { name: 'Tunisia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/tn.svg' },
    { name: 'Turkey', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/tr.svg' },
    { name: 'Turkmenistan', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/tm.svg' },
    { name: 'Tuvalu', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/tv.svg' },
    { name: 'Uganda', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ug.svg' },
    { name: 'Ukraine', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ua.svg' },
    { name: 'United Arab Emirates', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ae.svg' },
    { name: 'United Kingdom', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/gb.svg' },
    { name: 'United States', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/us.svg' },
    { name: 'Uruguay', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/uy.svg' },
    { name: 'Uzbekistan', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/uz.svg' },
    { name: 'Vanuatu', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/vu.svg' },
    { name: 'Vatican City', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/va.svg' },
    { name: 'Venezuela', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ve.svg' },
    { name: 'Vietnam', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/vn.svg' },
    { name: 'Yemen', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ye.svg' },
    { name: 'Zambia', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/zm.svg' },
    { name: 'Zimbabwe', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/zw.svg' }
  ];
 
  map: L.Map;
  geocoder: google.maps.Geocoder;
  marker: L.Marker | undefined;
  latitude: number=0
  longitude: number=0
  preload:boolean=false
  nomClub:any
  email:any
  ville:any
  pays:any="Sélectionner pays"
  adresse:any
  codePostal:any
  matriculeFiscale:any
  telPrincipale:any
  telSecondaire:any
  descriptionInfo:any
  logo: any = "https://png.pngtree.com/png-vector/20220610/ourmid/pngtree-mascot-icon-illustration-of-a-padel-player-png-image_4955855.png";
affiche:boolean=false
searchText: string = '';
filteredCountries: Country[] = [];
selectedCountry: Country | null = null;
nbTerrain:number=1
mode:any
theme:any
sidebarcolor:any
selectedPays:boolean=true
adresseUrl:any
selectedAdresse:boolean=true
selectedMode:boolean=true
selectedTheme:boolean=true
selectedSideBarColor:boolean=true
selectedLogo:boolean=true
getCurrentLocation() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(
      (position: GeolocationPosition) => {
        this.latitude = position.coords.latitude;
        this.longitude = position.coords.longitude;
        console.log('Latitude:', this.latitude);
        console.log('Longitude:', this.longitude);
      },
      (error: GeolocationPositionError) => {
        console.error('Error getting current location:', error.message);
      }
    );
  } else {
    console.error('Geolocation is not supported by this browser.');
  }
}
setDarkMode(){
  this.mode="dark"
  this.isModeActive(this.mode)

}
setlightMode(){
  this.mode="light"
  this.isModeActive(this.mode)
}
setTheme(theme:any){
  this.theme=theme
  this.isthemeActive(theme)
}
isModeActive(mode: string): boolean {
  return this.mode === mode;
}
isthemeActive(theme: string): boolean {
  return this.theme === theme;
}
isSideBarActive(sidebar: string): boolean {
  return this.sidebarcolor === sidebar;
}
changeSidebarColor(sideBar:any){
 this.sidebarcolor=sideBar
}
visible: boolean = false;

progress: number = 0;

interval :any;
  constructor(private router:Router,private service:AppwebserviceService,private messageService: MessageService, private cdr: ChangeDetectorRef,private toast:NgToastService) { 
    this.geocoder = new google.maps.Geocoder();
    this.map = {} as L.Map;
  }

  ngOnInit() {
   this.getCurrentLocation()
  }
  openToast(){
    this.toast.toast('An error occurred',ToastType.DANGER, 'Error Message', 5000);
    
  }
  showConfirm() {
    console.log("hhhhhhh")
    if (!this.visible) {
        this.messageService.add({ key: 'confirm', sticky: true, severity: 'custom', summary: 'Uploading your files.' });
        this.visible = true;
        this.progress = 0;

        if (this.interval) {
            clearInterval(this.interval);
        }

        this.interval = setInterval(() => {
            if (this.progress <= 100) {
                this.progress = this.progress + 20;
            }

            if (this.progress >= 100) {
                this.progress = 100;
                clearInterval(this.interval);
            }
            this.cdr.markForCheck();
        }, 1000);
    }
}

onClose() {
    this.visible = false;
}
  mapClicked(event: google.maps.MapMouseEvent) {
    if (event.latLng) {
      this.latitude = event.latLng.lat();
      this.longitude = event.latLng.lng();

    }
    
    this.getAddressFromCoordinates();
  }

  getAddressFromCoordinates() {
    this.service.getAddress(this.latitude, this.longitude).subscribe(
      address => {
        this.adresse = address;
      },
      error => {
        console.error('Erreur lors de la récupération de l\'adresse:', error);
      }
    );
  }
 
  affichepays(){
    this.affiche=true
    this.filteredCountries = this.countries;

  }
  HiddenPayspays(){
    this.affiche=false

  }
  filterCountries(nom: string): void {
    if (nom.trim().length > 0) {
      this.filteredCountries = this.countries.filter(country =>
        country.name.toLowerCase().includes(nom)
      );
    } else {
      this.filteredCountries = this.countries;

    }
  }
  changeLogo(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.logo = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  }
  selectPays(country: Country): void {
    this.selectedCountry = country;
    this.pays = country.name;
    this.affiche = false; 
  }
addNbTerrain(){
  this.nbTerrain+=1
}
RemoveNbTerrain(){
  this.nbTerrain-=1
}
login(){
  this.service.getAddressCoordinates(this.adresse).subscribe(res=>{
    console.log(res)
  })
  /*
  this.preload=true
  let formValid = true;

  // Vérifiez si les champs obligatoires sont vides
  if (!this.nomClub) {
    formValid = false;
    document.getElementById('fname')?.classList.add('error');
  } else {
    document.getElementById('fname')?.classList.remove('error');
  }
  if (!this.email) {
    formValid = false;
    document.getElementById('lname')?.classList.add('error');
  } else {
    document.getElementById('lname')?.classList.remove('error');
  }
  if (!this.ville) {
    formValid = false;
    document.getElementById('ville')?.classList.add('error');
  } else {
    document.getElementById('ville')?.classList.remove('error');
  }
  if(this.pays=="Sélectionner pays"){
    formValid = false;
    this.selectedPays=false
  }
  if(!this.adresse){
      formValid=false
      this.selectedAdresse=false
  }
  if (!this.codePostal) {
    formValid = false;
    document.getElementById('codePostal')?.classList.add('error');
  } else {
    document.getElementById('codePostal')?.classList.remove('error');
  }
  if (!this.matriculeFiscale) {
    formValid = false;
    document.getElementById('matriculeFiscale')?.classList.add('error');
  } else {
    document.getElementById('matriculeFiscale')?.classList.remove('error');
  }
  if (!this.telPrincipale) {
    formValid = false;
    document.getElementById('tel')?.classList.add('error');
  } else {
    document.getElementById('tel')?.classList.remove('error');
  }
  if (!this.adresseUrl) {
    formValid = false;
    document.getElementById('adresseUrl')?.classList.add('error');
  } else {
    document.getElementById('adresseUrl')?.classList.remove('error');
  }
  if(!this.mode){
    formValid=false
    this.selectedMode=false
}
if(!this.theme){
  formValid=false
  this.selectedTheme=false
}
if(!this.sidebarcolor){
  formValid=false
  this.selectedSideBarColor=false
}

  if (!formValid) {
    this.toast.toast('Veuillez remplir tous les champs obligatoires.', ToastType.DANGER, 'Erreur', 5000);
    this.preload=false
    return;
  }
  const adr=this.adresse+" "+this.codePostal
 
    var data={
      "club": {
          "nomClub": this.nomClub,
          "email": this.email,
          "latitude": this.latitude,
          "longitude": this.longitude,
          "codePostal": this.codePostal,
          "ville": this.ville,
          "pays": this.pays,
          "fuseau": "EST",
          "horaireInfos": this.descriptionInfo,
          "telPrincipal": this.telPrincipale,
          "telSecondaire": this.telSecondaire,
          "activite": "Padel",
          "nbTerrain": this.nbTerrain,
          "offre": "gratuite",
          "payement": true,
          "matriculeFiscale": this.matriculeFiscale,
          "adresse": this.adresse
      },
      "appWeb": {
          "nomAppWeb": this.nomClub,
          "logoAppWeb": this.logo,
          "couleurAppWeb": this.theme,
          "bannerImage": "https://media.babolat.com//image/upload/f_auto,q_auto,c_crop,w_2000,h_751/Website_content/Padel_News/02092020-Launch/padel-equipment/equipment-banner-2.jpg",
          "adresseUrl":this.adresseUrl ,
          "mode":this.mode,
          "couleurSideBar":this.sidebarcolor
      }
  }
  this.service.saveClub(data).subscribe(
    (data) => {
      this.preload = false;
      localStorage.setItem('email', this.email);
      localStorage.setItem('idClub', data.idClub);
      this.router.navigate(['/confirmEmail']);
    },
    (error) => {
      this.preload = false;
      if(error.error=="L'email du club existe déjà"){
        document.getElementById('lname')?.classList.add('error');

      }
      if(error.error=="Le nom du club existe déjà"){
        document.getElementById('fname')?.classList.add('error');

      }
      if(error.error=="L'adresse URL de l'application Web existe déjà"){
        document.getElementById('adresseUrl')?.classList.add('error');

      }
      
      this.toast.toast(error.error, ToastType.DANGER, 'Erreur', 5000);
    }
  );*/
}
}
