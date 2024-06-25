import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from '../../services/appwebservice.service';

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

  constructor(public activeModal: NgbActiveModal, private service: AppwebserviceService) {}

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
