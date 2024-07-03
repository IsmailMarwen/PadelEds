import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { MessageService } from 'primeng/api';
import { NgToastService,ToastType } from 'ng-angular-popup';
@Component({
  selector: 'app-create-type-abonnement-club',
  templateUrl: './create-type-abonnement-club.component.html',
  styleUrl: './create-type-abonnement-club.component.css'
})
export class CreateTypeAbonnementClubComponent {

 
    id:any
    libType:any
    nbMois:any
    nbJours:any
    forfait:any


    preload:boolean=false
    typeAbonnementClub: any = {
      id: '',
      libType: '',
      nbMois:'',
    nbJours:'',
    forfait:'',
    };
    constructor(private activeModel:NgbActiveModal, private service:AppwebserviceService,private messageService: MessageService,private toast:NgToastService){}
    closeModal() {
      this.activeModel.dismiss('Cross click');
    }
    
    addtypeAbonnementClub() {
      this.preload = true;
      let formValid = true;
    
      // Clear previous error messages
      this.clearErrorMessages();
    
      // Vérifiez si les champs obligatoires sont vides
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
    
      this.service.addTypeAbonnementClub(this.typeAbonnementClub).subscribe(
        response => {
          console.log('typeAbonnementClub added successfully:', response);
          this.preload = false;
          this.activeModel.close('saved');
        },
        (error) => {
          console.error('Error adding typeAbonnementClub:', error);
          this.preload = false;
    
          // Handle specific errors
          if (error.error === "L'codeTypetypeAbonnementClub du club existe déjà") {
            this.showErrorMessage('codeTypetypeAbonnementClub', 'L\'email du club existe déjà');
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
  