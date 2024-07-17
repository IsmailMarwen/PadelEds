import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { MessageService } from 'primeng/api';
import { NgToastService,ToastType } from 'ng-angular-popup';
@Component({
  selector: 'app-create-categorie-abonnement',
  templateUrl: './create-categorie-abonnementt.component.html',
  styleUrl: './create-categorie-abonnementt.component.css'
})
export class CreateCategorieAbonnementtComponent {


  id:any
  designation:any
  preload:boolean=false
  
  constructor(private activeModel:NgbActiveModal, private service:AppwebserviceService,private messageService: MessageService,private toast:NgToastService){}
  closeModal() {
    this.activeModel.dismiss('Cross click');
  }
  
  addcategorieAbonnement() {
    this.preload = true;
    let formValid = true;
    var data ={
      
      designation:this.designation,
      club:{
        idClub:localStorage.getItem("idClub")
      }
    }
  
    // Clear previous error messages
    this.clearErrorMessages();
  
    // Vérifiez si les champs obligatoires sont vides
    if (!this.designation) {
      formValid = false;
      this.showErrorMessage('designation', 'designation est obligatoire');
    }
   
  
    if (!formValid) {
      this.toast.toast('Veuillez remplir tous les champs obligatoires.', ToastType.DANGER, 'Erreur', 5000);
      this.preload = false;
      return;
    }
  
    this.service.addcategorieAbonnement(data).subscribe(
      response => {
        console.log('categorieAbonnement added successfully:', response);
        this.preload = false;
        this.activeModel.close('saved');
      },
      (error) => {
        console.error('Error adding categorieAbonnement:', error);
        this.preload = false;
  
        // Handle specific errors
        if (error.error === "L'codeTypecategorieAbonnement du club existe déjà") {
          this.showErrorMessage('codeTypecategorieAbonnement', 'L\'email du club existe déjà');
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