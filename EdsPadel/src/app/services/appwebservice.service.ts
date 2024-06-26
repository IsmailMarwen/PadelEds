import { Injectable,TemplateRef } from '@angular/core';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AppwebserviceService {
  toasts: any[] = [];
  apiUrl="http://localhost:8081/api"
  private geocodeUrl = 'https://nominatim.openstreetmap.org/search';
  private nominatimUrl = 'https://nominatim.openstreetmap.org/reverse';
  private meteoApiUrl='https://api.openweathermap.org/data/2.5';
  private apiKey = 'bd5e378503939ddaee76f12ad7a97608';
  private apiUrlGetAdresse = 'https://api.geoapify.com/v1/geocode/search';
  private apiKeyAdresse='0efd67c90bf949df86f3e41ced1d94d9'
  constructor(private http:HttpClient) { }
  private bannerImageSource = new BehaviorSubject<string>(localStorage.getItem('banner') || 'https://media.babolat.com//image/upload/f_auto,q_auto,c_crop,w_2000,h_751/Website_content/Padel_News/02092020-Launch/padel-equipment/equipment-banner-2.jpg');

  bannerImage$ = this.bannerImageSource.asObservable();
  getWeather(city: string): Observable<any> {
    return this.http.get(`${this.meteoApiUrl}/weather?q=${city}&units=metric&appid=${this.apiKey}&lang=fr`);
  }
  setBannerImage(image: string) {
    localStorage.setItem('banner', image);
    this.bannerImageSource.next(image);
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
  saveClub(data:any):Observable<any>{
    return this.http.post(this.apiUrl+"/club/add",data);
  }
  getCoordinates(address: string): Observable<{ lat: number, lon: number }> {
    const url = `${this.geocodeUrl}?q=${encodeURIComponent(address)}&format=json&addressdetails=1&limit=1`;
    return this.http.get(url).pipe(
      map((response: any) => {
        if (response.length > 0) {
          const location = response[0];
          return { lat: parseFloat(location.lat), lon: parseFloat(location.lon) };
        } else {
          throw new Error('No results found');
        }
      }),
      catchError((error) => {
        console.error('HTTP error:', error);
        return throwError(() => new Error('Geocoding failed'));
      })
    );
  }
  login(data:any):Observable<any>{
    return this.http.post(this.apiUrl+'/authentication/login',data)
  }
  getInfoClub(id:any):Observable<any>{
    return this.http.get(this.apiUrl+"/club/getById/"+id)
  }
  getAddress(latitude: any, longitude: any): Observable<string> {
    const url = `${this.nominatimUrl}?lat=${latitude}&lon=${longitude}&format=json&addressdetails=1`;
    return this.http.get<any>(url).pipe(
      map(response => {
        if (response && response.display_name) {
          return response.display_name;
        } else {
          throw new Error('Adresse non trouvée');
        }
      })
    );
  }
  show(textOrTpl: string | TemplateRef<any>, options: any = {}) {
    this.toasts.push({ textOrTpl, ...options });
  }

  remove(toast:any) {
    this.toasts = this.toasts.filter(t => t !== toast);
  }

  clear() {
    this.toasts.splice(0, this.toasts.length);
  }
  resetPassword(data:any):Observable<any>{
    return this.http.put(this.apiUrl+'/authentication/resetPassword',data)
  }
  updatePassword(data:any):Observable<any>{
    return this.http.put(this.apiUrl+'/authentication/updatePassword',data)
  }
  updateAppWeb(data:any):Observable<any>{
    return this.http.put(this.apiUrl+'/appWeb/update',data)
  }
  getWeatherForecast(city: string):Observable<any> {
    return this.http.get(`${this.meteoApiUrl}/forecast?q=${city}&units=metric&appid=${this.apiKey}&lang=fr`);

  }
  getAddressCoordinates(address: string): Observable<any> {
    const url = `${this.apiUrlGetAdresse}?text=${encodeURIComponent(address)}&apiKey=${this.apiKeyAdresse}`;
    return this.http.get<any>(url);
  }
  contactClub(data:any):Observable<any>{
    return this.http.post(this.apiUrl+'/authentication/contact',data)
  }
}
