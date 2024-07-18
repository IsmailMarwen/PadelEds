
import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { MessageService } from 'primeng/api';
import { NgToastService, ToastType } from 'ng-angular-popup';
@Component({
  selector: 'app-update-plage-horaire',
  templateUrl: './update-plage-horaire.component.html',
  styleUrl: './update-plage-horaire.component.css'
})
export class UpdatePlageHoraireComponent {
  @Input() plageHoraire: any; // Assuming this receives existing subscription details
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

  updateplageHoraire() {
    this.preload = true;
    let formValid = true;
    var data ={
      id:this.plageHoraire.id,
      libelle:this.plageHoraire.libelle,
      debutAct:this.plageHoraire.debutAct,
  finAct:this.plageHoraire.finAct,
   dureeAct:this.plageHoraire.dureeAct,
      club:{
        idClub:localStorage.getItem("idClub")
      }
    }
    // Clear previous error messages
    this.clearErrorMessages();
  
    // Vérifiez si les champs obligatoires sont vides
    if (!this.plageHoraire.libelle) {
      formValid = false;
      this.showErrorMessage('libelle', 'libelle est obligatoire');
    }
    if (!this.plageHoraire.debutAct) {
      formValid = false;
      this.showErrorMessage('debutAct', 'debut actitvité est obligatoire');
    }
    if (!this.plageHoraire.finAct) {
      formValid = false;
      this.showErrorMessage('finAct', 'fin actitvité est obligatoire');
    }
    if (!this.plageHoraire.dureeAct) {
      formValid = false;
      this.showErrorMessage('dureeAct', 'durée actitvité est obligatoire');
    }

    if (!formValid) {
      this.toast.toast('Veuillez remplir tous les champs obligatoires.', ToastType.DANGER, 'Erreur', 5000);
      this.preload = false;
      return;
    }

    this.service.updatePlageHoraire(data).subscribe(
      response => {
        console.log('plageHoraire updated successfully:', response);
        this.preload = false;
        this.activeModal.close('updated');
      },
      error => {
        console.error('Error updating plageHoraire:', error);
        this.preload = false;

        // Handle specific errors if needed
        if (error.error === "Le code du type d'plageHoraire existe déjà") {
          this.showErrorMessage('codeTypeplageHoraire', "Le code du type d'plageHoraire existe déjà");
        }

        this.toast.toast('Une erreur s\'est produite lors de la mise à jour de l\'plageHoraire.', ToastType.DANGER, 'Erreur', 5000);
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
