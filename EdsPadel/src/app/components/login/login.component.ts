import { Component,OnInit } from '@angular/core';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
  constructor(private service:AppwebserviceService,private router:Router){}
  latitude: any;
  longitude: any;
  clubs:any;
  search:boolean=false
  nom:any
  ngOnInit(): void {
    
  
  }
  goToCreateClub(){
    this.router.navigate(["/createClub"])
  }
  getClubs(): void {
    this.service.getClubsProximete(this.latitude, this.longitude, 500.0).subscribe(data => {
      this.clubs = data.map((club:any) => {
        club.distance = this.calculateDistance(this.latitude, this.longitude, club.latitude, club.longitude);
        return club;
      });
      this.clubs.sort((a:any, b:any) => a.distance - b.distance);
      console.log(this.clubs);
    });
  }

  calculateDistance(lat1: number, lon1: number, lat2: number, lon2: number): number {
    const R = 6371; // Radius of the Earth in km
    const dLat = this.deg2rad(lat2 - lat1);
    const dLon = this.deg2rad(lon2 - lon1);
    const a = 
      Math.sin(dLat / 2) * Math.sin(dLat / 2) +
      Math.cos(this.deg2rad(lat1)) * Math.cos(this.deg2rad(lat2)) * 
      Math.sin(dLon / 2) * Math.sin(dLon / 2);
    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    const distance = R * c; // Distance in km
    return distance;
  }

  deg2rad(deg: number): number {
    return deg * (Math.PI / 180);
  }
  getClubsByNom(nom:any){
    this.search=true
    this.clubs=null;
    this.service.getClubsByNom(nom).subscribe(data => {
      this.clubs = data.map((club:any) => {
        club.distance = this.calculateDistance(this.latitude, this.longitude, club.latitude, club.longitude);
        return club;
      });
      this.clubs.sort((a:any, b:any) => a.distance - b.distance);
      console.log(this.clubs);
    });
  }
  closeList() {
    this.search = false; 
    this.clubs = null; 
  }
  onNomChange(nom: string): void {
    if (nom.trim().length > 0) {
      this.setCurrentLocation().then(() => {
      this.getClubsByNom(nom);
    });
    } else {
      this.search = false;
      this.clubs = null;
    }
  }
  getClubsByProximete(){
    this.search=true
    this.setCurrentLocation().then(() => {

      this.getClubs();

    });
  }
  private setCurrentLocation(): Promise<void> {
    return new Promise((resolve, reject) => {
      if ('geolocation' in navigator) {
        navigator.geolocation.getCurrentPosition(
          (position) => {
            this.latitude = position.coords.latitude;
            this.longitude = position.coords.longitude;
            resolve();
          },
          (error) => reject(error)
        );
      } else {
        reject('Geolocation not available');
      }
    });
  }
}
