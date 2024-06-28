import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { MessageService } from 'primeng/api';
import { NgToastService, ToastType } from 'ng-angular-popup';

@Component({
  selector: 'app-create-activite',
  templateUrl: './create-activite.component.html',
  styleUrls: ['./create-activite.component.css']
})
export class CreateActiviteComponent {
  libelle: any;
  couleur: any;
  preload: boolean = false;
  activite: any = {
    libelle: '',
    couleur: ''
  };

  constructor(
    private activeModal: NgbActiveModal,
    private service: AppwebserviceService,
    private messageService: MessageService,
    private toast: NgToastService
  ) {}

  closeModal() {
    this.activeModal.dismiss('Cross click');
  }

  addActivite() {
    this.preload = true;
    let formValid = true;

    // Clear previous error messages
    this.clearErrorMessages();

    // Vérifiez si les champs obligatoires sont vides
    if (!this.activite.libelle) {
      formValid = false;
      this.showErrorMessage('libelle', 'Libelle est obligatoire');
    }
    if (!this.activite.couleur) {
      formValid = false;
      this.showErrorMessage('couleur', 'Couleur est obligatoire');
    }

    if (!formValid) {
      this.toast.toast('Veuillez remplir tous les champs obligatoires.', ToastType.DANGER, 'Erreur', 5000);
      this.preload = false;
      return;
    }

    this.service.addActivite(this.activite).subscribe(
      response => {
        console.log('Activite added successfully:', response);
        this.preload = false;
        this.activeModal.close('saved');
      },
      (error) => {
        console.error('Error adding activite:', error);
        this.preload = false;

        // Handle specific errors
        if (error.error === "L'libelle du club existe déjà") {
          this.showErrorMessage('libelle', 'Le libelle du club existe déjà');
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
