import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { ProfileComponent } from './components/profile/profile.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { CreateClubComponent } from './components/create-club/create-club.component';
const routes: Routes = [
  { path: '', component: LoginComponent},
  { path: ':url/signUp', component: SignupComponent},
  { path: 'home', component: HomeComponent},
  {path:'profile', component:ProfileComponent},
  {path:'createClub',component:CreateClubComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 

  
}
