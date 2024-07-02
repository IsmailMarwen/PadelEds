
import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { MessageService } from 'primeng/api';
import { NgToastService,ToastType } from 'ng-angular-popup';
@Component({
  selector: 'app-create-ressource',
  templateUrl: './create-ressource.component.html',
  styleUrl: './create-ressource.component.css'
})
export class CreateRessourceComponent {
    id:any
    libelle:any
    preload:boolean=false
    ressource: any = {
      id: '',
      libelle: '',
     
    };
    constructor(private activeModel:NgbActiveModal, private service:AppwebserviceService,private messageService: MessageService,private toast:NgToastService){}
    closeModal() {
      this.activeModel.dismiss('Cross click');
    }
    
    addressource() {
      this.preload = true;
      let formValid = true;
    
      // Clear previous error messages
      this.clearErrorMessages();
    
      // Vérifiez si les champs obligatoires sont vides
      if (!this.ressource.libelle) {
        formValid = false;
        this.showErrorMessage('libelle', 'libelle est obligatoire');
      }
     
    
      if (!formValid) {
        this.toast.toast('Veuillez remplir tous les champs obligatoires.', ToastType.DANGER, 'Erreur', 5000);
        this.preload = false;
        return;
      }
    
      this.service.addRessource(this.ressource).subscribe(
        response => {
          console.log('ressource added successfully:', response);
          this.preload = false;
          this.activeModel.close('saved');
        },
        (error) => {
          console.error('Error adding ressource:', error);
          this.preload = false;
    
          // Handle specific errors
          if (error.error === "L'codeTyperessource du club existe déjà") {
            this.showErrorMessage('codeTyperessource', 'L\'email du club existe déjà');
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
  