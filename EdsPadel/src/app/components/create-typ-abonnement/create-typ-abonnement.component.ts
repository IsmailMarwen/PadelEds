import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { MessageService } from 'primeng/api';
import { NgToastService,ToastType } from 'ng-angular-popup';
@Component({
  selector: 'app-create-typ-abonnement',
  templateUrl: './create-typ-abonnement.component.html',
  styleUrl: './create-typ-abonnement.component.css'
})
export class CreateTypAbonnementComponent {
  codeTypeAbonnement:any
  libTypeAbonnement:any
  mthtTypeAbonnement:any
  tauxTva:any
  mtttc:any
  nbMois:any
  couleur:any
  preload:boolean=false
  abonnement: any = {
    codeTypeAbonnement: '',
    libTypeAbonnement: '',
    mthtTypeAbonnement: '',
    tauxTva: '',
    mtttc: '',
    nbMois: '',
    couleur:''
  };
  constructor(private activeModel:NgbActiveModal, private service:AppwebserviceService,private messageService: MessageService,private toast:NgToastService){}
  closeModal() {
    this.activeModel.dismiss('Cross click');
  }
  
  addabonnement() {
    this.preload = true;
    let formValid = true;
  
    // Clear previous error messages
    this.clearErrorMessages();
  
    // Vérifiez si les champs obligatoires sont vides
    if (!this.abonnement.codeTypeAbonnement) {
      formValid = false;
      this.showErrorMessage('codeTypeAbonnement', 'codeTypeAbonnement est obligatoire');
    }
    if (!this.abonnement.libTypeAbonnement) {
      formValid = false;
      this.showErrorMessage('libTypeAbonnement', 'libTypeAbonnement est obligatoire');
    }
    if (!this.abonnement.mthtTypeAbonnement) {
      formValid = false;
      this.showErrorMessage('mthtTypeAbonnement', 'mthtTypeAbonnement est obligatoire');
    }
    if (!this.abonnement.tauxTva) {
      formValid = false;
      this.showErrorMessage('tauxTva', 'tauxTva est obligatoire');
    }
    if (!this.abonnement.mtttc) {
      formValid = false;
      this.showErrorMessage('mtttc', 'mtttc est obligatoire');
    }
    if (!this.abonnement.nbMois) {
      formValid = false;
      this.showErrorMessage('nbMois', 'nbMois est obligatoire');
    }
  
    if (!formValid) {
      this.toast.toast('Veuillez remplir tous les champs obligatoires.', ToastType.DANGER, 'Erreur', 5000);
      this.preload = false;
      return;
    }
  
    this.service.addabonnement(this.abonnement).subscribe(
      response => {
        console.log('abonnement added successfully:', response);
        this.preload = false;
        this.activeModel.close('saved');
      },
      (error) => {
        console.error('Error adding abonnement:', error);
        this.preload = false;
  
        // Handle specific errors
        if (error.error === "L'codeTypeAbonnement du club existe déjà") {
          this.showErrorMessage('codeTypeAbonnement', 'L\'email du club existe déjà');
        }
        
  
        this.toast.toast(error.error, ToastType.DANGER, 'Erreur', 5000);
      }
    );
  }
  changeMttc() {
    const mthtTypeAbonnement = Number(this.abonnement.mthtTypeAbonnement);
    const tauxTva = Number(this.abonnement.tauxTva);
    this.abonnement.mtttc = mthtTypeAbonnement + (mthtTypeAbonnement * tauxTva)/100;
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