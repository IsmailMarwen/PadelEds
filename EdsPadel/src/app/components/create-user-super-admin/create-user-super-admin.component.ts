import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
@Component({
  selector: 'app-create-user-super-admin',
  templateUrl: './create-user-super-admin.component.html',
  styleUrl: './create-user-super-admin.component.css'
})
export class CreateUserSuperAdminComponent {
  imgProfile:string="../../../assets/images/avatars/avtar_6.png"
  nom:any
  prenom:any
  email:any
  telephone:any
  password:any
  preload:boolean=false
  constructor(private activeModel:NgbActiveModal){}
  closeModal() {
    this.activeModel.dismiss('Cross click');
  }
  changeLogo(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.imgProfile = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  }
}
