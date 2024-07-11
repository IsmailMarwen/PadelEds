import { Component, OnInit, OnDestroy } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { MessageService } from 'primeng/api';
import { NgToastService, ToastType } from 'ng-angular-popup';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit, OnDestroy {
  preload: boolean = false;
  image:string="../../../assets/images/avatars/avtar_6.png";
  role: string = '';
  nom: string = '';
  prenom: string = '';
  email: string = '';
  telephone: string = '';
  genre: string = '';
  username: string = '';
  password: string = '';

  currentMode: string = 'light';

  constructor(public activeModal: NgbActiveModal, private service: AppwebserviceService, private messageService: MessageService, private toast: NgToastService) {}

  ngOnInit(): void {}

  ngOnDestroy() {
    document.removeEventListener('ChangeMode', this.handleModeChange.bind(this));
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

  setRoleAdmin() {
    this.role = "admin";
  }

  setRoleCoach() {
    this.role = "coach";
  }

  setRoleAgent() {
    this.role = "agent";
  }

  setRoleMembre() {
    this.role = "membre";
  }

  setGenderMale() {
    this.genre = "homme";
  }

  setGenderFMale() {
    this.genre = "femme";
  }

  addUser() {
    this.preload = true;
    let formValid = true;
    const data = {
      nom: this.nom,
      prenom: this.prenom,
      role: this.role,
      genre: this.genre,
      telephone: this.telephone,
      image: this.image,
      username: this.username,
      password: this.password,
      email: this.email,
      club: {
        idClub: localStorage.getItem("idClub")
      }
    };
    this.clearErrorMessages();
    if (!this.image) {
      formValid = false;
      this.showErrorMessage('image', 'image est obligatoire');
    }
    if (!this.nom) {
      formValid = false;
      this.showErrorMessage('nom', 'Nom est obligatoire');
    }
    if (!this.prenom) {
      formValid = false;
      this.showErrorMessage('prenom', 'Prénom est obligatoire');
    }
    if (!this.role) {
      formValid = false;
      this.showErrorMessage('role', 'Role est obligatoire');
    }
    if (!this.genre) {
      formValid = false;
      this.showErrorMessage('genre', 'Genre est obligatoire');
    }
    if (!this.telephone) {
      formValid = false;
      this.showErrorMessage('telephone', 'Téléphone est obligatoire');
    }
    if (!this.username) {
      formValid = false;
      this.showErrorMessage('username', 'Username est obligatoire');
    }
    if (!this.password) {
      formValid = false;
      this.showErrorMessage('password', 'Password est obligatoire');
    }
    if (!this.email) {
      formValid = false;
      this.showErrorMessage('email', 'Email est obligatoire');
    }

    if (!formValid) {
      this.toast.toast('Veuillez remplir tous les champs obligatoires.', ToastType.DANGER, 'Erreur', 5000);
      this.preload = false;
      return;
    }

    this.service.addUser(data).subscribe(
      response => {
        console.log('User added successfully:', response);
        this.preload = false;
        this.activeModal.close('saved');
      },
      error => {
        console.error('Error adding user:', error);
        this.preload = false;
        this.toast.toast(error.error, ToastType.DANGER, 'Erreur', 5000);
      }
    );
  }

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

  changeLogo(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.image = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  }
}
