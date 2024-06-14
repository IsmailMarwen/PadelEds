import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class AppwebserviceService {
  apiUrl="http://localhost:8081/api"

  constructor(private http:HttpClient) { }
  private bannerImageSource = new BehaviorSubject<string>(localStorage.getItem('banner') || 'https://static.ballejaune.com/pictures/headband/default_38_small.jpg?3');
  private logoSource = new BehaviorSubject<string>(localStorage.getItem('logo') || 'https://i.pinimg.com/originals/af/b1/3b/afb13bddf95b5b3434c1a3b50ce0f003.png');

  bannerImage$ = this.bannerImageSource.asObservable();
  logo$ = this.logoSource.asObservable();

  setBannerImage(image: string) {
    localStorage.setItem('banner', image);
    this.bannerImageSource.next(image);
  }
  setLogoAppWeb(image: string) {
    localStorage.setItem('logo', image);
    this.logoSource.next(image);
  }
  getClubsProximete(latitude:any,longitude:any,distance:any):Observable<any>{
    return this.http.get(this.apiUrl+"/club/proximite?latitude="+latitude+"&longitude="+longitude+"&distance="+distance)
  }
  getClubsByVille(ville:any):Observable<any>{
    return this.http.get(this.apiUrl+"/club/ville?ville="+ville)
  }
  getClubsByNom(nom:any):Observable<any>{
    return this.http.get(this.apiUrl+"/club/nom?nom="+nom)
  }
}
