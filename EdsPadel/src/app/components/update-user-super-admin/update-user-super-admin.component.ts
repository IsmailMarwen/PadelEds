import { Component,Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { MessageService } from 'primeng/api';
import { NgToastService,ToastType } from 'ng-angular-popup';

@Component({
  selector: 'app-update-user-super-admin',
  templateUrl: './update-user-super-admin.component.html',
  styleUrl: './update-user-super-admin.component.css'
})
export class UpdateUserSuperAdminComponent {
  imgProfile:string=""
  nom: string = '';
  prenom: string = '';
  email: string = '';
  telephone: string = '';
  password: string = '';
  preload:boolean=false
  @Input() user: any;

  constructor(private activeModel:NgbActiveModal , private service:AppwebserviceService,private messageService: MessageService,private toast:NgToastService){}
  closeModal() {
    this.activeModel.dismiss('Cross click');
  }
  changeLogo(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.imgProfile = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  }
  updateSuperUser() {
    this.preload = true;
    let formValid = true;

    // Clear previous error messages
    this.clearErrorMessages();

    // Check if mandatory fields are empty
    if (!this.user.nom) {
      formValid = false;
      this.showErrorMessage('fname', 'Nom est obligatoire');
    }
    if (!this.user.prenom) {
      formValid = false;
      this.showErrorMessage('lname', 'Prénom est obligatoire');
    }
    if (!this.user.email) {
      formValid = false;
      this.showErrorMessage('email', 'Email est obligatoire');
    }
    if (!this.user.telephone) {
      formValid = false;
      this.showErrorMessage('tel', 'Téléphone est obligatoire');
    }
    if (!this.user.password) {
      formValid = false;
      this.showErrorMessage('pass', 'Mot de passe est obligatoire');
    }
    

    if (!formValid) {
      this.toast.toast('Veuillez remplir tous les champs obligatoires.', ToastType.DANGER, 'Erreur', 5000);
      this.preload = false;
      return;
    }

    this.service.updateSuperUser(this.user).subscribe(
      response => {
        console.log('User updated successfully:', response);
        this.preload = false;
        this.activeModel.close('updated');

        // Optionally close a modal or navigate away
      },
      error => {
        console.error('Error updating user:', error);
        this.preload = false;

        // Handle specific errors if needed
        if (error.error === "L'email du club existe déjà") {
          this.showErrorMessage('email', 'L\'email du club existe déjà');
        }
        if (error.error === "Le nom du club existe déjà") {
          this.showErrorMessage('fname', 'Le nom du club existe déjà');
        }

        this.toast.toast('Veuillez remplir tous les champs obligatoires.', ToastType.DANGER, 'Erreur', 5000);
      }
    );
  }

  // Helper methods to show and hide error messages
  showErrorMessage(fieldId: string, message: string) {
    const element = document.getElementById(`${fieldId}-error`);
    if (element) {
      element.textContent = message;
      const inputElement = document.getElementById(fieldId);
      if (inputElement) {
        inputElement.classList.add('error');
      }
    }
  }

  clearErrorMessages() {
    const errorElements = document.querySelectorAll('.error-message');
    errorElements.forEach(errorElement => {
      errorElement.textContent = '';
    });

    const inputElements = document.querySelectorAll('.error');
    inputElements.forEach(inputElement => {
      inputElement.classList.remove('error');
    });
  }

}