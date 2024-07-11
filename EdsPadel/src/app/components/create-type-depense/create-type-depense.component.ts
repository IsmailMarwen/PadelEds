import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { NgToastService,ToastType } from 'ng-angular-popup';
@Component({
  selector: 'app-create-type-depense',
  templateUrl: './create-type-depense.component.html',
  styleUrl: './create-type-depense.component.css'
})
export class CreateTypeDepenseComponent {
  id:any
  designation:any
  preload:boolean=false

   

  constructor(private activeModel:NgbActiveModal, private service:AppwebserviceService,private toast:NgToastService){}
  closeModal() {
    this.activeModel.dismiss('Cross click');
  }
  
  addtypeDepense() {
    this.preload = true;
    let formValid = true;
  
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
    var data={
      designation:this.designation,
      club:{
        idClub:localStorage.getItem("idClub")
      }

    }
    this.service.addtypeDepense(data).subscribe(
      response => {
        console.log('typeDepense added successfully:', response);
        this.preload = false;
        this.activeModel.close('saved');
      },
      (error) => {
        console.error('Error adding typeDepense:', error);
        this.preload = false;
  
        // Handle specific errors
        if (error.error === "L'codeTypetypeDepense du club existe déjà") {
          this.showErrorMessage('codeTypetypeDepense', 'L\'email du club existe déjà');
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