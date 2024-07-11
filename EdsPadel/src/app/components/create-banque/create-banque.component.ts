import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { MessageService } from 'primeng/api';
import { NgToastService,ToastType } from 'ng-angular-popup';
@Component({
  selector: 'app-create-banque',
  templateUrl: './create-banque.component.html',
  styleUrl: './create-banque.component.css'
})
export class CreateBanqueComponent {
    id:any
    libelle:any
    preload:boolean=false
   
    constructor(private activeModel:NgbActiveModal, private service:AppwebserviceService,private messageService: MessageService,private toast:NgToastService){}
    closeModal() {
      this.activeModel.dismiss('Cross click');
    }
    
    addbanque() {
      this.preload = true;
      let formValid = true;

      var data ={
        libelle:this.libelle,
        club:{
          idClub:localStorage.getItem("idClub")
        }


      }
    
      // Clear previous error messages
      this.clearErrorMessages();
    
      // Vérifiez si les champs obligatoires sont vides
      if (!this.libelle) {
        formValid = false;
        this.showErrorMessage('libelle', 'libelle est obligatoire');
      }
     
    
      if (!formValid) {
        this.toast.toast('Veuillez remplir tous les champs obligatoires.', ToastType.DANGER, 'Erreur', 5000);
        this.preload = false;
        return;
      }
    
      this.service.addBanque(data).subscribe(
        response => {
          console.log('banque added successfully:', response);
          this.preload = false;
          this.activeModel.close('saved');
        },
        (error) => {
          console.error('Error adding banque:', error);
          this.preload = false;
    
          // Handle specific errors
          if (error.error === "L'codeTypebanque du club existe déjà") {
            this.showErrorMessage('codeTypebanque', 'L\'email du club existe déjà');
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
  