import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { MessageService } from 'primeng/api';
import { NgToastService, ToastType } from 'ng-angular-popup';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit, OnDestroy {
  @Input() user: any;
  selectedImage: string | null = null;
  url: string | null = null;
  currentMode: string = 'light';
  preload: boolean = false;

  constructor(
    public activeModal: NgbActiveModal,
    private service: AppwebserviceService,
    private messageService: MessageService,
    private toast: NgToastService
  ) {}

  ngOnInit(): void {
    this.selectedImage = localStorage.getItem('banner');
    document.addEventListener('ChangeMode', this.handleModeChange as EventListener);
    this.currentMode = document.body.classList.contains('dark') ? 'dark' : 'light';
  }

  ngOnDestroy() {
    document.removeEventListener('ChangeMode', this.handleModeChange as EventListener);
  }

  closeModal() {
    this.activeModal.dismiss('Cross click');
  }

  handleModeChange(event: Event) {
    const customEvent = event as CustomEvent;
    if (customEvent.detail.dark) {
      this.currentMode = 'dark';
    } else if (customEvent.detail.light) {
      this.currentMode = 'light';
    }
  }

  updateUser() {
    this.preload = true;
    let formValid = true;
    const data = {
      idUtilisateur: this.user.idUtilisateur,
      nom: this.user.nom,
      prenom: this.user.prenom,
      role: this.user.role,
      genre: this.user.genre,
      telephone: this.user.telephone,
      image: this.user.image,
      username: this.user.username,
      password: this.user.password,
      email: this.user.email,
      club: {
        idClub: localStorage.getItem('idClub')
      }
    };

    this.clearErrorMessages();

    // Vérifiez si les champs obligatoires sont vides
    if (!this.user.nom) {
      formValid = false;
      this.showErrorMessage('nom', 'nom est obligatoire');
    }
    if (!this.user.prenom) {
      formValid = false;
      this.showErrorMessage('prenom', 'prenom est obligatoire');
    }
    if (!this.user.role) {
      formValid = false;
      this.showErrorMessage('role', 'role est obligatoire');
    }
    if (!this.user.genre) {
      formValid = false;
      this.showErrorMessage('genre', 'genre est obligatoire');
    }
    if (!this.user.telephone) {
      formValid = false;
      this.showErrorMessage('telephone', 'telephone est obligatoire');
    }
    if (!this.user.image) {
      formValid = false;
      this.showErrorMessage('image', 'imageProfile est obligatoire');
    }
    if (!this.user.username) {
      formValid = false;
      this.showErrorMessage('username', 'username est obligatoire');
    }
    if (!this.user.password) {
      formValid = false;
      this.showErrorMessage('password', 'password est obligatoire');
    }
    if (!this.user.email) {
      formValid = false;
      this.showErrorMessage('email', 'email est obligatoire');
    }

    if (!formValid) {
      this.toast.toast('Veuillez remplir tous les champs obligatoires.', ToastType.DANGER, 'Erreur', 5000);
      this.preload = false;
      return;
    }

    this.service.updateUser(data).subscribe(
      response => {
        console.log('user updated successfully:', response);
        this.preload = false;
        this.activeModal.close('updated');
      },
      error => {
        console.error('Error updating user:', error);
        this.preload = false;

        // Handle specific errors if needed
        if (error.error === "Le user existe déjà") {
          this.showErrorMessage('email', "L'utilisateur avec cet email existe déjà");
        }

        this.toast.toast('Une erreur s\'est produite lors de la mise à jour de l\'utilisateur.', ToastType.DANGER, 'Erreur', 5000);
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

  setGenderMale() {
    this.user.genre = 'homme';
  }

  setGenderFMale() {
    this.user.genre = 'femme';
  }

  changeLogo(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.user.image = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  }
}