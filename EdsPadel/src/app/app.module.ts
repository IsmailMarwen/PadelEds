import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'; 
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { ModalModule } from 'ngx-bootstrap/modal';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ProfileComponent } from './components/profile/profile.component';
import { ImageBannerComponent } from './components/image-banner/image-banner.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { HttpClientModule } from '@angular/common/http';
import { CreateClubComponent } from './components/create-club/create-club.component';
import { RouterModule} from '@angular/router';
import { PayementComponent } from './components/payement/payement.component';
import { ConfirmEmailComponent } from './components/confirm-email/confirm-email.component';
import { LoginClubComponent } from './components/login-club/login-club.component';
import { UpdatePasswordComponent } from './components/update-password/update-password.component';
import { GoogleMapsModule } from '@angular/google-maps';
import { ToastModule } from 'primeng/toast';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';
import { ProgressBarModule } from 'primeng/progressbar';
import { MessageService } from 'primeng/api';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastsContainerComponent } from './components/toasts-container/toasts-container.component';
import {NgToastModule} from 'ng-angular-popup';
import { ResetPasswordComponent } from './components/reset-password/reset-password.component';
import { ContactComponent } from './components/contact/contact.component';
import { SuperAdminComponent } from './components/super-admin/super-admin.component';
import { UsersSuperAdminComponent } from './components/users-super-admin/users-super-admin.component';
import { CreateUserSuperAdminComponent } from './components/create-user-super-admin/create-user-super-admin.component';
import { UpdateUserSuperAdminComponent } from './components/update-user-super-admin/update-user-super-admin.component';
import { ComplexesComponent } from './components/complexes/complexes.component';
import { TypAbonnementComponent } from './components/typ-abonnement/typ-abonnement.component';
import { ConfigAppWebComponent } from './components/config-app-web/config-app-web.component';
import { ActiviteComponent } from './components/activite/activite.component';
import { CreateTypAbonnementComponent } from './components/create-typ-abonnement/create-typ-abonnement.component';
import { UpdateTypAbonnementComponent } from './components/update-typ-abonnement/update-typ-abonnement.component';
import { CreateActiviteComponent } from './components/create-activite/create-activite.component';
import { UpdateActiviteComponent } from './components/update-activite/update-activite.component';
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProfileComponent,
    ImageBannerComponent,
    LoginComponent,
    SignupComponent,
    CreateClubComponent,
    PayementComponent,
    ConfirmEmailComponent,
    LoginClubComponent,
    UpdatePasswordComponent,
    ToastsContainerComponent,
    ResetPasswordComponent,
    ContactComponent,
    SuperAdminComponent,
    UsersSuperAdminComponent,
    CreateUserSuperAdminComponent,
    UpdateUserSuperAdminComponent,
    ComplexesComponent,
    TypAbonnementComponent,
    ConfigAppWebComponent,
    ActiviteComponent,
    CreateTypAbonnementComponent,
    UpdateTypAbonnementComponent,
    CreateActiviteComponent,
    UpdateActiviteComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BsDropdownModule.forRoot(),
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    NgbModule,
    FormsModule,
    HttpClientModule,
    RouterModule,
    GoogleMapsModule,
    ToastModule,
    ButtonModule,
    RippleModule,
    ProgressBarModule,
    BrowserAnimationsModule,
    NgToastModule
  ],
  providers: [
    provideClientHydration(),
    MessageService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
