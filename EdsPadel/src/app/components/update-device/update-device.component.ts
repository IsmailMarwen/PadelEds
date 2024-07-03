import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { MessageService } from 'primeng/api';
import { NgToastService, ToastType } from 'ng-angular-popup';

@Component({
  selector: 'app-update-device',
  templateUrl: './update-device.component.html',
  styleUrl: './update-device.component.css'
})
export class UpdateDeviceComponent {
  @Input() devise: any; // Assuming this receives existing subscription details

  preload: boolean = false;

  constructor(
    private activeModal: NgbActiveModal,
    private service: AppwebserviceService,
    private messageService: MessageService,
    private toast: NgToastService
  ) {}

  closeModal() {
    this.activeModal.dismiss('Cross click');
  }

  updatedevise() {
    this.preload = true;
    let formValid = true;

    // Clear previous error messages
    this.clearErrorMessages();

    // Check if mandatory fields are empty
    if (!this.devise.lib) {
      formValid = false;
      this.showErrorMessage('libelle', 'libelle est obligatoire');
    }
    if (!this.devise.centieme) {
      formValid = false;
      this.showErrorMessage('centieme', 'centieme est obligatoire');
    }
    

    if (!formValid) {
      this.toast.toast('Veuillez remplir tous les champs obligatoires.', ToastType.DANGER, 'Erreur', 5000);
      this.preload = false;
      return;
    }

    this.service.updateDevise(this.devise).subscribe(
      response => {
        console.log('devise updated successfully:', response);
        this.preload = false;
        this.activeModal.close('updated');
      },
      error => {
        console.error('Error updating devise:', error);
        this.preload = false;

        // Handle specific errors if needed
        if (error.error === "Le code du type d'devise existe déjà") {
          this.showErrorMessage('codeTypedevise', "Le code du type d'devise existe déjà");
        }

        this.toast.toast('Une erreur s\'est produite lors de la mise à jour de l\'devise.', ToastType.DANGER, 'Erreur', 5000);
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