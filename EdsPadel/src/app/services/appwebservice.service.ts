import { Injectable,TemplateRef } from '@angular/core';
import { BehaviorSubject, Observable, throwError,forkJoin } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppwebserviceService {
  toasts: any[] = [];
  apiUrl="http://ip172-18-0-23-cq7pit8l2o9000catl70-8081.direct.labs.play-with-docker.com/api"
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
 

 

  getClubsByVille(ville: any): Observable<any> {
    return this.http.get(`${this.apiUrl}/club/ville?ville=${ville}`);
  }

  getClubsByNom(nom: any): Observable<any> {
    return this.http.get(`${this.apiUrl}/club/nom?nom=${nom}`);
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
 
  getAllUsersByClub(clubId: string): Observable<any[]> {
    return new Observable(observer => {
      forkJoin({
        admins: this.getAdminsByClub(clubId),
        members: this.getMembersByClub(clubId),
        coaches: this.getCoachesByClub(clubId),
        agents: this.getAgentsByClub(clubId)
      }).subscribe({
        next: ({ admins, members, coaches, agents }) => {
          let users: any[] = [];
          users = users.concat(admins, members, coaches, agents);
          observer.next(users);
          observer.complete();
        },
        error: err => observer.error(err)
      });
    });
  }
  getAdminsByClub(clubId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/administrateur/getAllByClub/${clubId}`);
  }
  
  getMembersByClub(clubId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/membre/getAllByClub/${clubId}`);
  }
  
  getCoachesByClub(clubId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/coach/getAllByClub/${clubId}`);
  }
  
  getAgentsByClub(clubId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/agentAcceuil/getAllByClub/${clubId}`);
  }
  
 
  
  deleteUser(userId: number, role: string): Observable<any> {
    let endpoint = '';
    switch (role) {
      case 'admin':
        endpoint = `/administrateur/delete/${userId}`;
        break;
      case 'coach':
        endpoint = `/coach/delete/${userId}`;
        break;
      case 'membre':
        endpoint = `/membre/delete/${userId}`;
        break;
      case 'agent':
        endpoint = `/agentAcceuil/delete/${userId}`;
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
      case 'admin':
        endpoint = '/administrateur';
        break;
      case 'coach':
        endpoint = '/coach';
        break;
      case 'membre':
        endpoint = '/membre';
        break;
      case 'agent':
        endpoint = '/agentAcceuil';
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
      case 'admin':
        endpoint = '/administrateur';
        break;
      case 'coach':
        endpoint = '/coach';
        break;
      case 'membre':
        endpoint = '/membre';
        break;
      case 'agent':
        endpoint = '/agentAcceuil';
        break;
      default:
        // Handle default case or error
        break;
    }
    return this.http.put(`${this.apiUrl}${endpoint}/update`, user);
  }
  getAllActivite():Observable<any>{
    return this.http.get(this.apiUrl+"/activite/getAll")
  }
  updateClub(data:any):Observable<any>{
    return this.http.put(this.apiUrl+"/club/update",data)
  }
  
  getAbonnements():Observable<any>{
    return this.http.get(this.apiUrl+"/TypeAbonnement/getAll")
  }
 
  addSuperUser(data:any):Observable<any>{
    return this.http.post(this.apiUrl+"/superAdmin/add",data);
  }
  addabonnement(abonnement:any):Observable<any>{
    return this.http.post(this.apiUrl+"/TypeAbonnement/add",abonnement);
  }
  addActivite(activite:any):Observable<any>{
    return this.http.post(this.apiUrl+"/activite/add",activite);
  }
  getUsers():Observable<any>{
    return this.http.get<any[]>(this.apiUrl+"/superAdmin/getAll")
  }
  getActivites():Observable<any>{
    return this.http.get<any[]>(this.apiUrl+"/activite/getAll")
  }
  deleteSuperUser(userId: number): Observable<any> {
    const url = `${this.apiUrl}/superAdmin/delete/${userId}`;
    console.log('Delete URL:', url); // Optional: Log the generated URL for debugging
    return this.http.delete(url);
  }
  deleteSuperAbonnement(abonnementId: number): Observable<any> {
    const url = `${this.apiUrl}/TypeAbonnement/delete/${abonnementId}`;
    console.log('Delete URL:', url); // Optional: Log the generated URL for debugging
    return this.http.delete(url);
  }
  deleteActivite(activiteId: number): Observable<any> {
    const url = `${this.apiUrl}/activite/delete/${activiteId}`;
    console.log('Delete URL:', url); // Optional: Log the generated URL for debugging
    return this.http.delete(url);
  }
  updateSuperUser(user:any):Observable<any>{
    return this.http.put(this.apiUrl+'/superAdmin/update',user)
  }
  updateAbonnemet(abonnement:any):Observable<any>{
    return this.http.put(this.apiUrl+'/TypeAbonnement/update',abonnement)
  }
  updateDepense(depense:any):Observable<any>{
    return this.http.put(this.apiUrl+'/typeDepense/update',depense)
  }
  
  updateActivite(activite:any):Observable<any>{
    return this.http.put(this.apiUrl+'/activite/update',activite)
  }
  addtypeDepense(data:any):Observable<any>{
    return this.http.post(this.apiUrl+"/typeDepense/add",data);
  }
  addtauxTva(data:any):Observable<any>{
    return this.http.post(this.apiUrl+"/tauxTva/add",data);
  }
  addDevise(data:any):Observable<any>{
    return this.http.post(this.apiUrl+"/devise/add",data);
  }
  addRessource(data:any):Observable<any>{
    return this.http.post(this.apiUrl+"/ressource/add",data);
  }
  addBanque(data:any):Observable<any>{
    return this.http.post(this.apiUrl+"/banque/add",data);
  }
  addTypeAbonnementClub(data:any):Observable<any>{
    return this.http.post(this.apiUrl+"/typeAbonnementClub/add",data);
  }
  addcategorieAbonnement(data:any):Observable<any>{
    return this.http.post(this.apiUrl+"/CategorieAbonnement/add",data);
  }
  getTauxTva(clubId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/tauxTva/getAllByClub/${clubId}`);
  }
  getcategorieAbonnement(clubId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/CategorieAbonnement/getAllByClub/${clubId}`);
  }
 
  getAllClub(): Observable<any> {
    return this.http.get(`${this.apiUrl}/club/getAll`);
  }
  deletetypeDepense(typeDepenseId: number): Observable<any> {
    const url = `${this.apiUrl}/superAdmin/delete/${typeDepenseId}`;
    console.log('Delete URL:', url); // Optional: Log the generated URL for debugging
    return this.http.delete(url);
  }
  deletetauxTva(tauxTvaId: number): Observable<any> {
    const url = `${this.apiUrl}/tauxTva/delete/${tauxTvaId}`;
    console.log('Delete URL:', url); // Optional: Log the generated URL for debugging
    return this.http.delete(url);
    
  }
  deletecategorieAbonnement(tauxTvaId: number): Observable<any> {
    const url = `${this.apiUrl}/CategorieAbonnement/delete/${tauxTvaId}`;
    console.log('Delete URL:', url); // Optional: Log the generated URL for debugging
    return this.http.delete(url);
    
  }
  deletedevise(deviseId: number): Observable<any> {
    const url = `${this.apiUrl}/devise/delete/${deviseId}`;
    console.log('Delete URL:', url); // Optional: Log the generated URL for debugging
    return this.http.delete(url);
    
  }
  deleteRessource(ressourceId: number): Observable<any> {
    const url = `${this.apiUrl}/ressource/delete/${ressourceId}`;
    console.log('Delete URL:', url); // Optional: Log the generated URL for debugging
    return this.http.delete(url);
    
  }
  deleteBanque(banqueId: number): Observable<any> {
    const url = `${this.apiUrl}/banque/delete/${banqueId}`;
    console.log('Delete URL:', url); // Optional: Log the generated URL for debugging
    return this.http.delete(url);
    
  }
  deleteTypeAbonnementClub(TypeAbonnementClubId: number): Observable<any> {
    const url = `${this.apiUrl}/typeAbonnementClub/delete/${TypeAbonnementClubId}`;
    console.log('Delete URL:', url); // Optional: Log the generated URL for debugging
    return this.http.delete(url);
    
  }
  getAlltypeDepenses(clubId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/typeDepense/getAllByClub/${clubId}`);
  }
  
  getAllDevise(clubId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/devise/getAllByClub/${clubId}`);
  }
  
  getRessource(clubId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/ressource/getAllByClub/${clubId}`);
  }
  
  getBanque(clubId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/banque/getAllByClub/${clubId}`);
  }
  
  getTypeAbonnementClub(clubId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/typeAbonnementClub/getAllByClub/${clubId}`);
  }
  
  updatetauxTva(data:any):Observable<any>{
    return this.http.put(this.apiUrl+"/tauxTva/update",data)
  }
  updatecategorieAbonnement(data:any):Observable<any>{
    return this.http.put(this.apiUrl+"/CategorieAbonnement/update",data)
  }
  updateDevise(data:any):Observable<any>{
    return this.http.put(this.apiUrl+"/devise/update",data)
  }
  updateRessource(data:any):Observable<any>{
    return this.http.put(this.apiUrl+"/ressource/update",data)
  }
  updateBanque(data:any):Observable<any>{
    return this.http.put(this.apiUrl+"/banque/update",data)
  }
  updateTypeAbonnementClub(data:any):Observable<any>{
    return this.http.put(this.apiUrl+"/typeAbonnementClub/update",data)
  }
}
