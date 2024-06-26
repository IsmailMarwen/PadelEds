import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-payement',
  templateUrl: './payement.component.html',
  styleUrl: './payement.component.css'
})
export class PayementComponent {
constructor(private router:Router){}
goToCreateClub(){
  this.router.navigate(["/createClub"])
}
}
