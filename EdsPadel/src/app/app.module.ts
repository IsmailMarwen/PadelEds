import { NgModule,CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
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
import { UtilisateurComponent } from './components/utilisateur/utilisateur.component';
import { TerrainComponent } from './components/terrain/terrain.component';
import { AddUserComponent } from './components/add-user/add-user.component';
import { EditUserComponent } from './components/edit-user/edit-user.component';
import { PayementComponent } from './components/payement/payement.component';
import { ConfirmEmailComponent } from './components/confirm-email/confirm-email.component';
import { LoginClubComponent } from './components/login-club/login-club.component';
import { UpdatePasswordComponent } from './components/update-password/update-password.component';
import { ToastsContainerComponent } from './components/toasts-container/toasts-container.component';
import { ResetPasswordComponent } from './components/reset-password/reset-password.component';
import { ContactComponent } from './components/contact/contact.component';
import { SuperAdminComponent } from './components/super-admin/super-admin.component';
import { UsersSuperAdminComponent } from './components/users-super-admin/users-super-admin.component';
import { CreateUserSuperAdminComponent } from './components/create-user-super-admin/create-user-super-admin.component';
import { UpdateUserSuperAdminComponent } from './components/update-user-super-admin/update-user-super-admin.component';
import { ComplexesComponent } from './components/complexes/complexes.component';
import { TypAbonnementComponent } from './components/typ-abonnement/typ-abonnement.component';
import { ActiviteComponent } from './components/activite/activite.component';
import { ConfigAppWebComponent } from './components/config-app-web/config-app-web.component';
import { GoogleMapsModule } from '@angular/google-maps';
import { ToastModule } from 'primeng/toast';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';
import { ProgressBarModule } from 'primeng/progressbar';
import { MessageService } from 'primeng/api';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {NgToastModule} from 'ng-angular-popup';
import { SocieteComponent } from './components/societe/societe.component';
import { FilterUsersComponent } from './components/filter-users/filter-users.component';
import { CalanderComponent } from './components/calander/calander.component';
import { CreateTypAbonnementComponent } from './components/create-typ-abonnement/create-typ-abonnement.component';
import { UpdateTypAbonnementComponent } from './components/update-typ-abonnement/update-typ-abonnement.component';
import { CreateActiviteComponent } from './components/create-activite/create-activite.component';
import { UpdateActiviteComponent } from './components/update-activite/update-activite.component';
import { typeDepenseeComponent } from './components/type-depense/type-depense.component';
import { BanqueComponent } from './components/banque/banque.component';
import { CreateBanqueComponent } from './components/create-banque/create-banque.component';
import { UpdateBanqueComponent } from './components/update-banque/update-banque.component';
import { CreateDeviceComponent } from './components/create-device/create-device.component';
import { CreateRessourceComponent } from './components/create-ressource/create-ressource.component';
import { CreateTauxTvaComponent } from './components/create-taux-tva/create-taux-tva.component';
import { CreateTypeAbonnementClubComponent } from './components/create-type-abonnement-club/create-type-abonnement-club.component';
import { DeviceComponent } from './components/device/device.component';
import { TauxTvaComponent } from './components/taux-tva/taux-tva.component';
import { CreateTypeDepenseComponent } from './components/create-type-depense/create-type-depense.component';
import { UpdateDeviceComponent } from './components/update-device/update-device.component';
import { UpdateRessourceComponent } from './components/update-ressource/update-ressource.component';
import { UpdateTauxTvaComponent } from './components/update-taux-tva/update-taux-tva.component';
import { UpdateTypeAbonnementClubComponent } from './components/update-type-abonnement-club/update-type-abonnement-club.component';
import { UpdateTypeDepenseComponent } from './components/update-type-depense/update-type-depense.component';
import { TypeAbonnementClubComponent } from './components/type-abonnement-club/type-abonnement-club.component';
import { RessourceComponent } from './components/ressource/ressource.component';
import { UpdateComplexeComponent } from './components/update-complexe/update-complexe.component';
import { AddComplexeComponent } from './components/add-complexe/add-complexe.component';
import { ColorPickerModule } from 'ngx-color-picker';
import { LoginSuperAdminComponent } from './components/login-super-admin/login-super-admin.component';
import { InscriptionEnLigneComponent } from './components/inscription-en-ligne/inscription-en-ligne.component';

@NgModule({
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
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
    TerrainComponent,
    AddUserComponent,
    EditUserComponent,
    UtilisateurComponent,
    SocieteComponent,
    FilterUsersComponent,
    CalanderComponent,
    CreateTypAbonnementComponent,
    UpdateTypAbonnementComponent,
    CreateActiviteComponent,
    UpdateActiviteComponent,
    typeDepenseeComponent,
    BanqueComponent,
    CreateBanqueComponent,
    UpdateBanqueComponent,
    CreateDeviceComponent,
    CreateRessourceComponent,
    CreateTauxTvaComponent,
    CreateTypeAbonnementClubComponent,
    DeviceComponent,
    TauxTvaComponent,
    CreateTypeDepenseComponent,
    UpdateDeviceComponent,
    UpdateRessourceComponent,
    UpdateTauxTvaComponent,
    UpdateTypeAbonnementClubComponent,
    UpdateTypeDepenseComponent,
    TypeAbonnementClubComponent,
    UpdateRessourceComponent,
    RessourceComponent,
    UpdateComplexeComponent,
    AddComplexeComponent,
    LoginSuperAdminComponent,
    InscriptionEnLigneComponent
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
    NgToastModule,
    ColorPickerModule
  ],
  providers: [
    provideClientHydration(),
    MessageService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
