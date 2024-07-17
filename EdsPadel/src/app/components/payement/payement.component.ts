import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AppwebserviceService } from '../../services/appwebservice.service';

@Component({
  selector: 'app-payement',
  templateUrl: './payement.component.html',
  styleUrl: './payement.component.css'
})
export class PayementComponent {
constructor(private router:Router,private service:AppwebserviceService,
){}
 id: any;
  codeTypeAbonnement: any;
  libTypeAbonnement: any;
  mthtTypeAbonnement: any;
  tauxTva: any;
  mtttc: any;
  nbMois: any;
  couleur: any;
  nbUtilisateur:any;
   nbJoursGratuit:any;
   remise:any;
  typeAbonnements: any[] = [];

  ngOnInit(): void {
    this.service.getAbonnements().subscribe(
      (data) => {
        this.typeAbonnements = data;
      },
      (error) => {
        console.error('Error fetching TypeAbonnement data:', error);
      }
    );
  }
goToCreateClub(){
  this.router.navigate(["/createClub"])
}
}