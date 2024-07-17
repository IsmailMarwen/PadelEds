import { Component, Input } from '@angular/core';
  import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
  import { AppwebserviceService } from '../../services/appwebservice.service';
  import { MessageService } from 'primeng/api';
  import { NgToastService, ToastType } from 'ng-angular-popup';
@Component({
  selector: 'app-update-type-abonnement-club',
  templateUrl: './update-type-abonnement-club.component.html',
  styleUrl: './update-type-abonnement-club.component.css'
})
export class UpdateTypeAbonnementClubComponent {

    @Input() typeAbonnementClub: any; // Assuming this receives existing subscription details
    preload: boolean = false;
    categories: any[] = [];
    constructor(
      private activeModal: NgbActiveModal,
      private service: AppwebserviceService,
      private messageService: MessageService,
      private toast: NgToastService
    ) {}
  
    closeModal() {
      this.activeModal.dismiss('Cross click');
    }
    ngOnInit() {
      // Fetch the list of categories when the component initializes
      const clubId = localStorage.getItem("idClub");
      if(clubId)
      this.service.getcategorieAbonnement(clubId).subscribe(
        (data) => {
          this.categories = data;
        },
        (error) => {
          console.error('Error fetching categories:', error);
        }
      );
    }
  
    updatetypeAbonnementClub() {
      this.preload = true;
      let formValid = true;
      var data={
        id:this.typeAbonnementClub.id,
        libType: this.typeAbonnementClub.libType,
        nbMois:this.typeAbonnementClub.nbMois,
      nbJours:this.typeAbonnementClub.nbJours,
      forfait:this.typeAbonnementClub.forfait,
      remise:this.typeAbonnementClub.remise,
      categorieAbonnement:{
        idCategorie:this.typeAbonnementClub.categorieAbonnement.idCategorie,
        },
      club:{
        idClub:localStorage.getItem("idClub")
      }
      }      

      // Clear previous error messages
      this.clearErrorMessages();
  
      // Check if mandatory fields are empty
      if (!this.typeAbonnementClub.libType) {
        formValid = false;
        this.showErrorMessage('libType', 'libType est obligatoire');
      }
      if (!this.typeAbonnementClub.nbMois) {
        formValid = false;
        this.showErrorMessage('nbMois', 'nbMois est obligatoire');
      }
      if (!this.typeAbonnementClub.nbJours) {
        formValid = false;
        this.showErrorMessage('nbJours', 'nbJours est obligatoire');
      }
      if (!this.typeAbonnementClub.forfait) {
        formValid = false;
        this.showErrorMessage('forfait', 'forfait est obligatoire');
      }
      
  
      if (!formValid) {
        this.toast.toast('Veuillez remplir tous les champs obligatoires.', ToastType.DANGER, 'Erreur', 5000);
        this.preload = false;
        return;
      }
  
      this.service.updateTypeAbonnementClub(data).subscribe(
        response => {
          console.log('typeAbonnementClub updated successfully:', response);
          this.preload = false;
          this.activeModal.close('updated');
        },
        error => {
          console.error('Error updating typeAbonnementClub:', error);
          this.preload = false;
  
          // Handle specific errors if needed
          if (error.error === "Le code du type d'typeAbonnementClub existe déjà") {
            this.showErrorMessage('codeTypetypeAbonnementClub', "Le code du type d'typeAbonnementClub existe déjà");
          }
  
          this.toast.toast('Une erreur s\'est produite lors de la mise à jour de l\'typeAbonnementClub.', ToastType.DANGER, 'Erreur', 5000);
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
  