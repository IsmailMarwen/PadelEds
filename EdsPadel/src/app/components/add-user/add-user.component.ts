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
  preload:boolean=false
    role:string= ''
    nom: string= ''
    prenom: string= ''
    email: string= ''
    phone: string= ''
    gender: string= ''
    username:string= ''
    password: string= ''

  imageProfile:string="../../../assets/images/avatars/avtar_6.png"
  currentMode: string = 'light';
  
  constructor(public activeModal: NgbActiveModal, private service: AppwebserviceService) {}

  ngOnInit(): void {

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
  setRoleAdmin(){
    this.role="admin"
  }
  setRoleCoach(){
    this.role="coach"
  }
  setRoleAgent(){
    this.role="agent"
  }
  setRoleMembre(){
    this.role="membre"
  }
  setGenderMale(){
    this.gender="homme"
  }
  setGenderFMale(){
    this.gender="femme"
  }
  addUser() {
    this.preload=true
    var data={
      nom:this.nom,
      prenom:this.prenom,
      role:this.role,
      genre:this.gender,
      telephone:this.phone,
      image:this.imageProfile,
      username:this.username,
      password:this.password,
      email:this.email,
      club:{
        idClub:localStorage.getItem("idClub")
      }
    }
    this.service.addUser(data).subscribe(
      response => {
        console.log('User added successfully:', response);
        this.preload=false
        this.activeModal.close('saved');
      },
      (error) => {
        console.error('Error adding user:', error);
        // Handle error
      }
    );
  }

  changeLogo(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.imageProfile = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  }
}
