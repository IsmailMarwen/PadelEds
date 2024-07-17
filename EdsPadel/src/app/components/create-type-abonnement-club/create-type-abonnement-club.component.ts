import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { MessageService } from 'primeng/api';
import { NgToastService, ToastType } from 'ng-angular-popup';

@Component({
  selector: 'app-create-type-abonnement-club',
  templateUrl: './create-type-abonnement-club.component.html',
  styleUrls: ['./create-type-abonnement-club.component.css']
})
export class CreateTypeAbonnementClubComponent implements OnInit {
  id: any;
  libType: any;
  nbMois: any;
  nbJours: any;
  forfait: any;
  remise: any;
  categorieAbonnement: any;
  categories: any[] = []; // To hold the list of categories

  preload: boolean = false;

  constructor(
    private activeModel: NgbActiveModal, 
    private service: AppwebserviceService, 
    private messageService: MessageService, 
    private toast: NgToastService,
  ) {}

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

  closeModal() {
    this.activeModel.dismiss('Cross click');
  }

  addtypeAbonnementClub() {
    this.preload = true;
    let formValid = true;
    const data = {
      libType: this.libType,
      nbMois: this.nbMois,
      nbJours: this.nbJours,
      forfait: this.forfait,
      remise: this.remise,
      categorieAbonnement:{
        idCategorie:this.categorieAbonnement,
        },
      club: {
        idClub: localStorage.getItem('idClub')
      }
    };
    console.log(data);
    
    // Clear previous error messages
    this.clearErrorMessages();

    // Vérifiez si les champs obligatoires sont vides
    if (!this.libType) {
      formValid = false;
      this.showErrorMessage('libType', 'libType est obligatoire');
    }
    if (!this.nbMois) {
      formValid = false;
      this.showErrorMessage('nbMois', 'nbMois est obligatoire');
    }
    if (!this.nbJours) {
      formValid = false;
      this.showErrorMessage('nbJours', 'nbJours est obligatoire');
    }
    if (!this.forfait) {
      formValid = false;
      this.showErrorMessage('forfait', 'forfait est obligatoire');
    }
    if (!this.remise) {
      formValid = false;
      this.showErrorMessage('remise', 'remise est obligatoire');
    }
    if (!this.categorieAbonnement) {
      formValid = false;
      this.showErrorMessage('categorie', 'categorie est obligatoire');
    }
    
    if (!formValid) {
      this.toast.toast('Veuillez remplir tous les champs obligatoires.', ToastType.DANGER, 'Erreur', 5000);
      this.preload = false;
      return;
    }

    this.service.addTypeAbonnementClub(data).subscribe(
      (response) => {
        console.log('typeAbonnementClub added successfully:', response);
        this.preload = false;
        this.activeModel.close('saved');
      },
      (error) => {
        console.error('Error adding typeAbonnementClub:', error);
        this.preload = false;

        // Handle specific errors
        if (error.error === "L'codeTypetypeAbonnementClub du club existe déjà") {
          this.showErrorMessage('codeTypetypeAbonnementClub', "L'email du club existe déjà");
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
    errorElements.forEach((errorElement) => {
      errorElement.innerText = '';
    });

    const inputElements = document.querySelectorAll('.error') as NodeListOf<HTMLElement>;
    inputElements.forEach((inputElement) => {
      inputElement.classList.remove('error');
    });
  }
}
