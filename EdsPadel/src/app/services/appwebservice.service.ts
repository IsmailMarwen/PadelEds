import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable,forkJoin  } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AppwebserviceService {
  apiUrl="http://localhost:8081/api"

  constructor(private http: HttpClient) { }

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

  getClubsProximete(latitude: any, longitude: any, distance: any): Observable<any> {
    return this.http.get(`${this.apiUrl}/club/proximite?latitude=${latitude}&longitude=${longitude}&distance=${distance}`);
  }

  getClubsByVille(ville: any): Observable<any> {
    return this.http.get(`${this.apiUrl}/club/ville?ville=${ville}`);
  }

  getClubsByNom(nom: any): Observable<any> {
    return this.http.get(`${this.apiUrl}/club/nom?nom=${nom}`);
  }
  getAllUsers(): Observable<any[]> {
    return new Observable(observer => {
      let users: any[] = [];
      this.getAdmins().subscribe(admins => {
        users = users.concat(admins);
        this.getMembers().subscribe(members => {
          users = users.concat(members);
          this.getCoaches().subscribe(coaches => {
            users = users.concat(coaches);
            observer.next(users);
            observer.complete();
          });
        });
      });
    });
  }
  getAdmins(): Observable<any> {
    return this.http.get(`${this.apiUrl}/administrateur/getAll`);
  }

  
  // Methods for Members
  getMembers(): Observable<any> {
    return this.http.get(`${this.apiUrl}/membre/getAll`);
  }

 

  // Methods for Coaches
  getCoaches(): Observable<any> {
    return this.http.get(`${this.apiUrl}/coach/getAll`);
  }

  
  deleteUser(userId: number, role: string): Observable<any> {
    let endpoint = '';
    switch (role) {
      case 'Administrateur':
        endpoint = `/administrateur/delete/${userId}`;
        break;
      case 'Coach':
        endpoint = `/coach/delete/${userId}`;
        break;
      case 'Membre':
        endpoint = `/membre/delete/${userId}`;
        break;
      default:
        // Handle default case or error
        break;
    }
    return this.http.delete(`${this.apiUrl}${endpoint}`);
  }
  
  addUser(user: any): Observable<any> {
    let endpoint = '';
    switch (user.role) {
      case 'Administrateur':
        endpoint = '/administrateur';
        break;
      case 'Coach':
        endpoint = '/coach';
        break;
      case 'Membre':
        endpoint = '/membre';
        break;
      default:
        // Handle default case or error
        break;
    }
    return this.http.post(`${this.apiUrl}${endpoint}/add`, user);
  }
  updateUser(user: any): Observable<any> {
    let endpoint = '';
    switch (user.role) {
      case 'Administrateur':
        endpoint = '/administrateur';
        break;
      case 'Coach':
        endpoint = '/coach';
        break;
      case 'Membre':
        endpoint = '/membre';
        break;
      default:
        // Handle default case or error
        break;
    }
    return this.http.put(`${this.apiUrl}${endpoint}/update`, user);
  }
  
}
