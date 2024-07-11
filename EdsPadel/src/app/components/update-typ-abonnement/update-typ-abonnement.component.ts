import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { MessageService } from 'primeng/api';
import { NgToastService, ToastType } from 'ng-angular-popup';

@Component({
  selector: 'app-update-typ-abonnement',
  templateUrl: './update-typ-abonnement.component.html',
  styleUrls: ['./update-typ-abonnement.component.css']
})
export class UpdateTypAbonnementComponent {
  @Input() abonnement: any; // Assuming this receives existing subscription details
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
  changeMttc() {
    const mthtTypeAbonnement = Number(this.abonnement.mthtTypeAbonnement);
    const tauxTva = Number(this.abonnement.tauxTva);
    this.abonnement.mtttc = mthtTypeAbonnement + (mthtTypeAbonnement * tauxTva)/100;
}
  updateAbonnement() {
    this.preload = true;
    let formValid = true;

    // Clear previous error messages
    this.clearErrorMessages();

    // Check if mandatory fields are empty
    if (!this.abonnement.codeTypeAbonnement) {
      formValid = false;
      this.showErrorMessage('codeTypeAbonnement', 'Code Type Abonnement est obligatoire');
    }
    if (!this.abonnement.libTypeAbonnement) {
      formValid = false;
      this.showErrorMessage('libTypeAbonnement', 'Libellé Type Abonnement est obligatoire');
    }
    if (!this.abonnement.mthtTypeAbonnement) {
      formValid = false;
      this.showErrorMessage('mthtTypeAbonnement', 'Montant HT Type Abonnement est obligatoire');
    }
    if (!this.abonnement.tauxTva) {
      formValid = false;
      this.showErrorMessage('tauxTva', 'Taux TVA est obligatoire');
    }
    if (!this.abonnement.mtttc) {
      formValid = false;
      this.showErrorMessage('mtttc', 'Montant TTC est obligatoire');
    }
    if (!this.abonnement.nbMois) {
      formValid = false;
      this.showErrorMessage('nbMois', 'Nombre de Mois est obligatoire');
    }

    if (!formValid) {
      this.toast.toast('Veuillez remplir tous les champs obligatoires.', ToastType.DANGER, 'Erreur', 5000);
      this.preload = false;
      return;
    }

    this.service.updateAbonnemet(this.abonnement).subscribe(
      response => {
        console.log('Abonnement updated successfully:', response);
        this.preload = false;
        this.activeModal.close('updated');
      },
      error => {
        console.error('Error updating abonnement:', error);
        this.preload = false;

        // Handle specific errors if needed
        if (error.error === "Le code du type d'abonnement existe déjà") {
          this.showErrorMessage('codeTypeAbonnement', "Le code du type d'abonnement existe déjà");
        }

        this.toast.toast('Une erreur s\'est produite lors de la mise à jour de l\'abonnement.', ToastType.DANGER, 'Erreur', 5000);
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