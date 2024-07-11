import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { MessageService } from 'primeng/api';
import { NgToastService,ToastType } from 'ng-angular-popup';
@Component({
  selector: 'app-create-device',
  templateUrl: './create-device.component.html',
  styleUrl: './create-device.component.css'
})
export class CreateDeviceComponent {
  id:any
  lib:any
  centieme:any
  preload:boolean=false
  
  constructor(private activeModel:NgbActiveModal, private service:AppwebserviceService,private messageService: MessageService,private toast:NgToastService){}
  closeModal() {
    this.activeModel.dismiss('Cross click');
  }
  
  addDevise() {
    this.preload = true;
    let formValid = true;
    var data={
      lib:this.lib,
      centieme:this.centieme,
      club:{
        idClub:localStorage.getItem("idClub")
      }

    }
  
    // Clear previous error messages
    this.clearErrorMessages();
  
    // Vérifiez si les champs obligatoires sont vides
    if (!this.lib) {
      formValid = false;
      this.showErrorMessage('libelle', 'libelle est obligatoire');
    }
    if (!this.centieme) {
      formValid = false;
      this.showErrorMessage('centieme', 'centieme est obligatoire');
    }
   
  
    if (!formValid) {
      this.toast.toast('Veuillez remplir tous les champs obligatoires.', ToastType.DANGER, 'Erreur', 5000);
      this.preload = false;
      return;
    }
  
    this.service.addDevise(data).subscribe(
      response => {
        console.log('device added successfully:', response);
        this.preload = false;
        this.activeModel.close('saved');
      },
      (error) => {
        console.error('Error adding device:', error);
        this.preload = false;
  
        // Handle specific errors
        if (error.error === "L'codeTypedevice du club existe déjà") {
          this.showErrorMessage('codeTypedevice', 'L\'email du club existe déjà');
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