
import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { MessageService } from 'primeng/api';
import { NgToastService,ToastType } from 'ng-angular-popup';
@Component({
  selector: 'app-create-plage-horaire',
  templateUrl: './create-plage-horaire.component.html',
  styleUrl: './create-plage-horaire.component.css'
})
export class CreatePlageHoraireComponent {
  id:any
  libelle:any
   debutAct:any
  finAct:any
   dureeAct:any
  preload:boolean=false
  
  constructor(private activeModel:NgbActiveModal, private service:AppwebserviceService,private messageService: MessageService,private toast:NgToastService){}
  closeModal() {
    this.activeModel.dismiss('Cross click');
  }
  
  addplageHoraire() {
    this.preload = true;
    let formValid = true;
    var data ={
      libelle:this.libelle,
      debutAct:this.debutAct,
  finAct:this.finAct,
   dureeAct:this.dureeAct,
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
    if (!this.debutAct) {
      formValid = false;
      this.showErrorMessage('debutAct', 'debut actitvité est obligatoire');
    }
    if (!this.finAct) {
      formValid = false;
      this.showErrorMessage('finAct', 'fin actitvité est obligatoire');
    }
    if (!this.dureeAct) {
      formValid = false;
      this.showErrorMessage('dureeAct', 'durée actitvité est obligatoire');
    }
   
  
    if (!formValid) {
      this.toast.toast('Veuillez remplir tous les champs obligatoires.', ToastType.DANGER, 'Erreur', 5000);
      this.preload = false;
      return;
    }
  
    this.service.addPlageHoraire(data).subscribe(
      response => {
        console.log('plageHoraire added successfully:', response);
        this.preload = false;
        this.activeModel.close('saved');
      },
      (error) => {
        console.error('Error adding plageHoraire:', error);
        this.preload = false;
  
        // Handle specific errors
        if (error.error === "L'codeTypeplageHoraire du club existe déjà") {
          this.showErrorMessage('codeTypeplageHoraire', 'L\'email du club existe déjà');
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
