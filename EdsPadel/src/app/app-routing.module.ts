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
import { TypAbonnementComponent } from './components/typ-abonnement/typ-abonnement.component';
import { ActiviteComponent } from './components/activite/activite.component';
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
  {path:':url/profile', component:ProfileComponent,canActivate:[authGuard]},
  {path:'createClub',component:CreateClubComponent},
  {path:'payement',component:PayementComponent},
  {path:'confirmEmail',component:ConfirmEmailComponent},
  {path:':url/loginClub',component:LoginClubComponent},
  {path:':url/updatePassword',component:UpdatePasswordComponent,canActivate:[authGuard]},
  {path:':url/contact',component:ContactComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 

  
}
