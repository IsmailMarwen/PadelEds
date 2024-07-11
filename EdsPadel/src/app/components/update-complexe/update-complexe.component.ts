import { Component, OnInit, Input,AfterViewInit  } from '@angular/core';
import { NgbOffcanvas } from '@ng-bootstrap/ng-bootstrap';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { ChangeDetectorRef } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { NgToastService,ToastType } from 'ng-angular-popup';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

interface Country {
  name: string;
  flagUrl: string;
}
@Component({
  selector: 'app-update-complexe',
  templateUrl: './update-complexe.component.html',
  styleUrl: './update-complexe.component.css'
})
export class UpdateComplexeComponent implements OnInit,AfterViewInit {
  countries: Country[] = [
    { name: 'Afghanistan', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/af.svg' },
    { name: 'Albanie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/al.svg' },
    { name: 'Algérie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/dz.svg' },
    { name: 'Andorre', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ad.svg' },
    { name: 'Angola', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ao.svg' },
    { name: 'Antigua-et-Barbuda', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ag.svg' },
    { name: 'Argentine', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ar.svg' },
    { name: 'Arménie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/am.svg' },
    { name: 'Australie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/au.svg' },
    { name: 'Autriche', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/at.svg' },
    { name: 'Azerbaïdjan', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/az.svg' },
    { name: 'Bahamas', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bs.svg' },
    { name: 'Bahreïn', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bh.svg' },
    { name: 'Bangladesh', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bd.svg' },
    { name: 'Barbade', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bb.svg' },
    { name: 'Bélarus', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/by.svg' },
    { name: 'Belgique', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/be.svg' },
    { name: 'Belize', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bz.svg' },
    { name: 'Bénin', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bj.svg' },
    { name: 'Bhoutan', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bt.svg' },
    { name: 'Bolivie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bo.svg' },
    { name: 'Bosnie-Herzégovine', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ba.svg' },
    { name: 'Botswana', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bw.svg' },
    { name: 'Brésil', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/br.svg' },
    { name: 'Brunei', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bn.svg' },
    { name: 'Bulgarie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bg.svg' },
    { name: 'Burkina Faso', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bf.svg' },
    { name: 'Burundi', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/bi.svg' },
    { name: 'Cambodge', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/kh.svg' },
    { name: 'Cameroun', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/cm.svg' },
    { name: 'Canada', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ca.svg' },
    { name: 'Cap-Vert', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/cv.svg' },
    { name: 'République centrafricaine', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/cf.svg' },
    { name: 'Tchad', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/td.svg' },
    { name: 'Chili', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/cl.svg' },
    { name: 'Chine', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/cn.svg' },
    { name: 'Colombie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/co.svg' },
    { name: 'Comores', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/km.svg' },
    { name: 'Congo (Brazzaville)', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/cg.svg' },
    { name: 'Costa Rica', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/cr.svg' },
    { name: 'Croatie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/hr.svg' },
    { name: 'Cuba', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/cu.svg' },
    { name: 'Chypre', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/cy.svg' },
    { name: 'Tchéquie (République tchèque)', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/cz.svg' },
    { name: 'Danemark', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/dk.svg' },
    { name: 'Djibouti', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/dj.svg' },
    { name: 'Dominique', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/dm.svg' },
    { name: 'République dominicaine', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/do.svg' },
    { name: 'Timor oriental', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/tl.svg' },
    { name: 'Équateur', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ec.svg' },
    { name: 'Égypte', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/eg.svg' },
    { name: 'El Salvador', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sv.svg' },
    { name: 'Guinée équatoriale', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/gq.svg' },
    { name: 'Érythrée', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/er.svg' },
    { name: 'Estonie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ee.svg' },
    { name: 'Eswatini (ex-Swaziland)', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sz.svg' },
    { name: 'Éthiopie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/et.svg' },
    { name: 'Fidji', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/fj.svg' },
    { name: 'Finlande', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/fi.svg' },
    { name: 'France', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/fr.svg' },
    { name: 'Gabon', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ga.svg' },
    { name: 'Gambie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/gm.svg' },
    { name: 'Géorgie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ge.svg' },
    { name: 'Allemagne', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/de.svg' },
    { name: 'Ghana', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/gh.svg' },
    { name: 'Grèce', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/gr.svg' },
    { name: 'Grenade', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/gd.svg' },
    { name: 'Guatemala', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/gt.svg' },
    { name: 'Guinée', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/gn.svg' },
    { name: 'Guinée-Bissau', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/gw.svg' },
    { name: 'Guyane', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/gy.svg' },
    { name: 'Haïti', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ht.svg' },
    { name: 'Honduras', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/hn.svg' },
    { name: 'Hongrie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/hu.svg' },
    { name: 'Islande', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/is.svg' },
    { name: 'Inde', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/in.svg' },
    { name: 'Indonésie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/id.svg' },
    { name: 'Iran', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ir.svg' },
    { name: 'Irak', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/iq.svg' },
    { name: 'Irlande', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ie.svg' },
    { name: 'Israël', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/il.svg' },
    { name: 'Italie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/it.svg' },
    { name: 'Jamaïque', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/jm.svg' },
    { name: 'Japon', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/jp.svg' },
    { name: 'Jordanie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/jo.svg' },
    { name: 'Kazakhstan', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/kz.svg' },
    { name: 'Kenya', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ke.svg' },
    { name: 'Kiribati', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ki.svg' },
    { name: 'Corée du Nord', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/kp.svg' },
    { name: 'Corée du Sud', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/kr.svg' },
    { name: 'Koweït', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/kw.svg' },
    { name: 'Kirghizistan', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/kg.svg' },
    { name: 'Laos', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/la.svg' },
    { name: 'Lettonie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/lv.svg' },
    { name: 'Liban', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/lb.svg' },
    { name: 'Lesotho', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ls.svg' },
    { name: 'Libéria', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/lr.svg' },
    { name: 'Libye', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ly.svg' },
    { name: 'Liechtenstein', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/li.svg' },
    { name: 'Lituanie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/lt.svg' },
    { name: 'Luxembourg', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/lu.svg' },
    { name: 'Madagascar', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mg.svg' },
    { name: 'Malawi', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mw.svg' },
    { name: 'Malaisie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/my.svg' },
    { name: 'Maldives', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mv.svg' },
    { name: 'Mali', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ml.svg' },
    { name: 'Malte', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mt.svg' },
    { name: 'Îles Marshall', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mh.svg' },
    { name: 'Mauritanie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mr.svg' },
    { name: 'Maurice', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mu.svg' },
    { name: 'Mexique', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mx.svg' },
    { name: 'Micronésie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/fm.svg' },
    { name: 'Moldavie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/md.svg' },
    { name: 'Monaco', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mc.svg' },
    { name: 'Mongolie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mn.svg' },
    { name: 'Monténégro', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/me.svg' },
    { name: 'Maroc', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ma.svg' },
    { name: 'Mozambique', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mz.svg' },
    { name: 'Myanmar (Birmanie)', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mm.svg' },
    { name: 'Namibie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/na.svg' },
    { name: 'Nauru', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/nr.svg' },
    { name: 'Népal', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/np.svg' },
    { name: 'Pays-Bas', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/nl.svg' },
    { name: 'Nouvelle-Zélande', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/nz.svg' },
    { name: 'Nicaragua', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ni.svg' },
    { name: 'Niger', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ne.svg' },
    { name: 'Nigéria', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ng.svg' },
    { name: 'Macédoine du Nord', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/mk.svg' },
    { name: 'Norvège', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/no.svg' },
    { name: 'Oman', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/om.svg' },
    { name: 'Pakistan', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/pk.svg' },
    { name: 'Palau', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/pw.svg' },
    { name: 'Palestine', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ps.svg' },
    { name: 'Panama', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/pa.svg' },
    { name: 'Papouasie-Nouvelle-Guinée', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/pg.svg' },
    { name: 'Paraguay', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/py.svg' },
    { name: 'Pérou', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/pe.svg' },
    { name: 'Philippines', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ph.svg' },
    { name: 'Pologne', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/pl.svg' },
    { name: 'Portugal', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/pt.svg' },
    { name: 'Qatar', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/qa.svg' },
    { name: 'Roumanie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ro.svg' },
    { name: 'Russie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ru.svg' },
    { name: 'Rwanda', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/rw.svg' },
    { name: 'Saint-Christophe-et-Niévès', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/kn.svg' },
    { name: 'Sainte-Lucie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/lc.svg' },
    { name: 'Saint-Vincent-et-les-Grenadines', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/vc.svg' },
    { name: 'Samoa', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ws.svg' },
    { name: 'Saint-Marin', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sm.svg' },
    { name: 'Sao Tomé-et-Principe', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/st.svg' },
    { name: 'Arabie Saoudite', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sa.svg' },
    { name: 'Sénégal', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sn.svg' },
    { name: 'Serbie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/rs.svg' },
    { name: 'Seychelles', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sc.svg' },
    { name: 'Sierra Leone', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sl.svg' },
    { name: 'Singapour', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sg.svg' },
    { name: 'Slovaquie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sk.svg' },
    { name: 'Slovénie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/si.svg' },
    { name: 'Îles Salomon', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sb.svg' },
    { name: 'Somalie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/so.svg' },
    { name: 'Afrique du Sud', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/za.svg' },
    { name: 'Soudan du Sud', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ss.svg' },
    { name: 'Espagne', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/es.svg' },
    { name: 'Sri Lanka', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/lk.svg' },
    { name: 'Soudan', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sd.svg' },
    { name: 'Suriname', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sr.svg' },
    { name: 'Eswatini', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sz.svg' },
    { name: 'Suède', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/se.svg' },
    { name: 'Suisse', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ch.svg' },
    { name: 'Syrie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/sy.svg' },
    { name: 'Tadjikistan', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/tj.svg' },
    { name: 'Tanzanie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/tz.svg' },
    { name: 'Thaïlande', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/th.svg' },
    { name: 'Timor oriental', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/tl.svg' },
    { name: 'Togo', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/tg.svg' },
    { name: 'Tonga', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/to.svg' },
    { name: 'Trinité-et-Tobago', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/tt.svg' },
    { name: 'Tunisie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/tn.svg' },
    { name: 'Turquie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/tr.svg' },
    { name: 'Turkménistan', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/tm.svg' },
    { name: 'Tuvalu', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/tv.svg' },
    { name: 'Ouganda', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ug.svg' },
    { name: 'Ukraine', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ua.svg' },
    { name: 'Émirats arabes unis', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ae.svg' },
    { name: 'Royaume-Uni', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/gb.svg' },
    { name: 'États-Unis', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/us.svg' },
    { name: 'Uruguay', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/uy.svg' },
    { name: 'Ouzbékistan', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/uz.svg' },
    { name: 'Vanuatu', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/vu.svg' },
    { name: 'Vatican', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/va.svg' },
    { name: 'Venezuela', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ve.svg' },
    { name: 'Viêt Nam', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/vn.svg' },
    { name: 'Yémen', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/ye.svg' },
    { name: 'Zambie', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/zm.svg' },
    { name: 'Zimbabwe', flagUrl: 'https://hatscripts.github.io/circle-flags/flags/zw.svg' }
  ]

  descriptionInfo:any
  telSecondaire:any
  telPrincipale:any
  matriculeFiscale:any
  codePostal:any
  adresse:any
  ville:any
  email:any
  nomClub:any
  selectedTheme: string = ''
  currentMode: string = '';
 currentSidebarColor: string = ''
 bannerImage:any
 nameApp:String=''
 logoApp:string=""
 theme:any
 preload:boolean=true
 themeDetail1:any
 themeDetail2:any
 idAppWeb:any
 adresseUrl:any
 affiche:boolean=false
 searchText:any
 filteredCountries: Country[] = [];
selectedCountry: any  = null;
pays:any
allActivites: any[]=[];
selectedActivites: any[] = [];
raisonSociale:any
latitude:any
longitude:any
preloader:boolean=false
@Input() idClub: any;

getCountryByName(countryName: string): Country | undefined {
  return this.countries.find(country => country.name === countryName);
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
selectPays(country: Country): void {
  this.selectedCountry = country;
  this.pays = country.name;
  this.affiche = false; 
}
  constructor(
    private service:AppwebserviceService,
    private router: Router,
    private activeModal: NgbActiveModal,


  ) {}
  ngAfterViewInit() {
  }
  closeModal() {
    this.activeModal.dismiss('Cross click');
  }
  logout(){
    localStorage.removeItem('jwt_token');
    this.router.navigate([this.adresseUrl+"/loginClub"])
  }
  ngOnInit() {
    this.service.getAllActivite().subscribe(res => {
      this.allActivites = res;
    });
    this.idClub=this.idClub
    this.service.getInfoClub(this.idClub).subscribe(data=>{
      this.raisonSociale=data.raisonSociale
      this.latitude=data.latitude
      this.longitude=data.longitude
      this.selectedActivites=data.activites
      this.nomClub=data.nomClub
      this.email=data.email
      this.pays=data.pays
      this.selectedCountry = this.getCountryByName(data.pays);
      this.ville=data.ville
      this.adresse=data.adresse
      this.codePostal=data.codePostal
      this.matriculeFiscale=data.matriculeFiscale
      this.telPrincipale=data.telPrincipal
      this.telSecondaire=data.telSecondaire
      this.adresseUrl=data.appWeb.adresseUrl
    this.idAppWeb=data.appWeb.idAppWeb      
    this.nameApp=data.appWeb.nomAppWeb
    this.descriptionInfo=data.horaireInfos
  })

  }
 

 
 isSelected(activite: any): boolean {
  return this.selectedActivites.some(a => a.id === activite.id);
}

selectActivite(activite: any): void {
  if (!this.isSelected(activite)) {
    this.selectedActivites.push(activite);
  }else{
    this.selectedActivites = this.selectedActivites.filter(a => a.id !== activite.id);

  }
}


getActiviteClass(activite: any): string {
  switch (activite.libelle) {
    case 'Football': return 'icon-football';
    case 'Volleyball': return 'icon-volley';
    case 'Basketball': return 'icon-basket';
    case 'Tennis': return 'icon-tennis';
    case 'Padel': return 'icon-ping';
    case 'Handball': return 'icon-handball';
    default: return '';
  }
}




 
  updateClub(){
    this.preloader=true
    var data={
      "idClub":this.idClub,
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
      "offre": "gratuite",
      "payement": true,
      "matriculeFiscale": this.matriculeFiscale,
      "adresse": this.adresse,
      "activites":this.selectedActivites,
      "raisonSociale":this.raisonSociale,
      "typeAbonnement": {
        "id": 1,
        "libTypeAbonnement": "free"
      }
      
  }
  console.log(data)

  this.service.updateClub(data).subscribe(res=>{
    this.preloader=false
    this.activeModal.close('saved');

  })
  
  }}
