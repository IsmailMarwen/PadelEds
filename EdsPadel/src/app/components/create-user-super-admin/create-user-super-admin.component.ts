import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { MessageService } from 'primeng/api';
import { NgToastService, ToastType } from 'ng-angular-popup';

@Component({
  selector: 'app-create-user-super-admin',
  templateUrl: './create-user-super-admin.component.html',
  styleUrls: ['./create-user-super-admin.component.css']
})
export class CreateUserSuperAdminComponent {
  imgProfile: string = "../../../assets/images/avatars/avtar_6.png";
  preload: boolean = false;
  image: string = "../../../assets/images/avatars/avtar_6.png";  
  nom:any
  prenom:any
  email:any
  telephone:any
  password:any
  constructor(
    private activeModal: NgbActiveModal,
    private service: AppwebserviceService,
    private messageService: MessageService,
    private toast: NgToastService
  ) {}

  closeModal() {
    this.activeModal.dismiss('Cross click');
  }

  changeLogo(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.image = e.target.result;
        console.log(this.image)
      };
      reader.readAsDataURL(file);
    }
  }

  addUser() {
    this.preload = true;
    let formValid = true;

    // Clear previous error messages
    this.clearErrorMessages();

    // Vérifiez si les champs obligatoires sont vides
    if (!this.nom) {
      formValid = false;
      this.showErrorMessage('fname', 'Nom est obligatoire');
    }
    if (!this.prenom) {
      formValid = false;
      this.showErrorMessage('lname', 'Prénom est obligatoire');
    }
    if (!this.email) {
      formValid = false;
      this.showErrorMessage('email', 'Email est obligatoire');
    }
    if (!this.telephone) {
      formValid = false;
      this.showErrorMessage('tel', 'Téléphone est obligatoire');
    }
    if (!this.password) {
      formValid = false;
      this.showErrorMessage('pass', 'Mot de passe est obligatoire');
    }

    if (!formValid) {
      this.toast.toast('Veuillez remplir tous les champs obligatoires.', ToastType.DANGER, 'Erreur', 5000);
      this.preload = false;
      return;
    }
    var data={
      "nom":this.nom,
      "prenom":this.prenom,
      "email":this.email,
      "telephone":this.telephone,
      "password":this.password,
      "image":this.image
    }
    
    this.service.addSuperUser(data).subscribe(
      response => {
        console.log('User added successfully:', response);
        this.preload = false;
        this.activeModal.close('saved');
      },
      (error) => {
        console.error('Error adding user:', error);
        this.preload = false;

        // Handle specific errors
        if (error.error === "L'email du club existe déjà") {
          this.showErrorMessage('email', 'L\'email du club existe déjà');
        }
        if (error.error === "Le nom du club existe déjà") {
          this.showErrorMessage('fname', 'Le nom du club existe déjà');
        }

        this.toast.toast(error.error, ToastType.DANGER, 'Erreur', 5000);
      }
    );
  }

  // Helper methods to show and hide error messages
  showErrorMessage(fieldId: string, message: string) {
    const element = document.getElementById(`${fieldId}-error`) as HTMLElement;
    if (element) {
      element.innerText = message;
      const inputElement = document.getElementById(fieldId) as HTMLElement;
      if (inputElement) {
        inputElement.classList.add('error');
      }
    }
  }

  clearErrorMessages() {
    const errorElements = document.querySelectorAll('.error-message') as NodeListOf<HTMLElement>;
    errorElements.forEach(errorElement => {
      errorElement.innerText = '';
    });

    const inputElements = document.querySelectorAll('.error') as NodeListOf<HTMLElement>;
    inputElements.forEach(inputElement => {
      inputElement.classList.remove('error');
    });
  }
}