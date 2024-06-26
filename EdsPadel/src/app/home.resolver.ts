import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { AppwebserviceService } from './services/appwebservice.service';

@Injectable({
  providedIn: 'root'
})
export class HomeResolver implements Resolve<any> {

  constructor(private service: AppwebserviceService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> {
    const idClub = localStorage.getItem("idClub");
    return this.service.getInfoClub(idClub);
  }
}