import { Component, OnInit, OnDestroy } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { EventEmitter } from '@angular/core';


@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit, OnDestroy {
  
  user: any = {
    role: '',
    nom: '',
    prenom: '',
    email: '',
    phone: '',
    gender: '',
    username: '',
    password: ''
  };
  selectedImage: string | null = null;
  url: string | null = null;
  currentMode: string = 'light';

  constructor(public activeModal: NgbActiveModal, private service: AppwebserviceService) {}

  ngOnInit(): void {
    this.selectedImage = localStorage.getItem('banner');
    document.addEventListener('ChangeMode', this.handleModeChange.bind(this));
    this.currentMode = document.body.classList.contains('dark') ? 'dark' : 'light';
  }

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
  onFileSelected(event: any): void {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.onload = (e: any) => {
      console.log("Image loaded:", e.target.result); // Ajoutez ceci pour vérifier le chargement
      this.user.image = e.target.result;
    };

    reader.onerror = (error) => {
      console.error("Error reading file:", error); // Ajoutez ceci pour gérer les erreurs
    };

    if (file) {
      reader.readAsDataURL(file);
    } else {
      console.error("No file selected"); // Ajoutez ceci pour gérer l'absence de fichier
    }
  }
  addUser() {
    this.service.addUser(this.user).subscribe(
      response => {
        console.log('User added successfully:', response);
        this.activeModal.close('saved');
      },
      (error) => {
        console.error('Error adding user:', error);
        // Handle error
      }
    );
  }
  updateUser() {
    // Call the update user API with the user data
    this.service.updateUser(this.user).subscribe(
      response => {
        // Handle success response
        console.log('User updated successfully:', response);
        this.activeModal.close('saved');
      },
      error => {
        // Handle error response
        console.error('Error updating user:', error);
      }
    );
  }
}
