import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { ProfileComponent } from './components/profile/profile.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { CreateClubComponent } from './components/create-club/create-club.component';
import { PayementComponent } from './components/payement/payement.component';
import { ConfirmEmailComponent } from './components/confirm-email/confirm-email.component';
import { LoginClubComponent } from './components/login-club/login-club.component';
import { UpdatePasswordComponent } from './components/update-password/update-password.component';
import { authGuard } from './auth.guard';
import { ContactComponent } from './components/contact/contact.component';
import { SuperAdminComponent } from './components/super-admin/super-admin.component';
import { UsersSuperAdminComponent } from './components/users-super-admin/users-super-admin.component';
import { ComplexesComponent } from './components/complexes/complexes.component';
import { ConfigAppWebComponent } from './components/config-app-web/config-app-web.component';
import { UtilisateurComponent } from './components/utilisateur/utilisateur.component';
import { TerrainComponent } from './components/terrain/terrain.component';
import { SocieteComponent } from './components/societe/societe.component';
import { CalanderComponent } from './components/calander/calander.component';
import { ActiviteComponent } from './components/activite/activite.component';
import { TypAbonnementComponent } from './components/typ-abonnement/typ-abonnement.component';
import { typeDepenseeComponent } from './components/type-depense/type-depense.component';
import { TauxTvaComponent } from './components/taux-tva/taux-tva.component';
import { DeviceComponent } from './components/device/device.component';
import { RessourceComponent } from './components/ressource/ressource.component';
import { BanqueComponent } from './components/banque/banque.component';
import { TypeAbonnementClubComponent } from './components/type-abonnement-club/type-abonnement-club.component';
const routes: Routes = [
  {path:'eds/admin/home',component:SuperAdminComponent},
  {path:'eds/admin/users',component:UsersSuperAdminComponent},
  {path:'eds/admin/complexes',component:ComplexesComponent},
  {path:'eds/admin/activites',component:ActiviteComponent},
  {path:'eds/admin/abonnements',component:TypAbonnementComponent},
  {path:'eds/admin/configApp/:id',component:ConfigAppWebComponent},
  { path: '', component: LoginComponent},
  { path: ':url/signUp', component: SignupComponent},
  { path: ':url/home', component: HomeComponent,canActivate:[authGuard]},
  { path: ':url/societe', component: SocieteComponent,canActivate:[authGuard]},
  {path:':url/profile', component:ProfileComponent,canActivate:[authGuard]},
  {path:'createClub',component:CreateClubComponent},
  {path:'payement',component:PayementComponent},
  {path:'confirmEmail',component:ConfirmEmailComponent},
  {path:':url/loginClub',component:LoginClubComponent},
  {path:':url/updatePassword',component:UpdatePasswordComponent,canActivate:[authGuard]},
  {path:':url/contact',component:ContactComponent},
  {path:':url/utilisateur',component:UtilisateurComponent},
  {path:'terrain',component:TerrainComponent},
  {path:'calander',component:CalanderComponent,canActivate:[authGuard]},
  {path:':url/typeDepense',component:typeDepenseeComponent,canActivate:[authGuard]},
{path:':url/tauxTva',component:TauxTvaComponent,canActivate:[authGuard]},
{path:':url/devise',component:DeviceComponent,canActivate:[authGuard]},
{path:':url/ressource',component:RessourceComponent,canActivate:[authGuard]},
{path:':url/banque',component:BanqueComponent,canActivate:[authGuard]},
{path:':url/typeAbonnementClub',component:TypeAbonnementClubComponent,canActivate:[authGuard]},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 

  
}
