import { Component,OnInit,OnDestroy  } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppwebserviceService } from '../../services/appwebservice.service';
import { NgToastService,ToastType } from 'ng-angular-popup';

@Component({
  selector: 'app-image-banner',
  templateUrl: './image-banner.component.html',
  styleUrl: './image-banner.component.css'
})
export class ImageBannerComponent implements OnInit,OnDestroy  {
  selectedImage: string | null = null;
  url: string | null = null;
  currentMode: string = 'light';
  idAppWeb:any
  constructor(public activeModal: NgbActiveModal, public service:AppwebserviceService,private toast:NgToastService) {}
  ngOnInit(): void {
    this.selectedImage=localStorage.getItem('banner')
    document.addEventListener('ChangeMode', this.handleModeChange as EventListener);
    this.currentMode = document.body.classList.contains('dark') ? 'dark' : 'light';
    this.service.getInfoClub(localStorage.getItem('idClub')).subscribe(data=>{
      this.idAppWeb=data.appWeb.idAppWeb
    })
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


  editImage(image: any) {
    this.selectedImage = image;
    this.service.setBannerImage(image);
    this.updateAppWeb()
    this.activeModal.close(); 
  }
  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.selectedImage = e.target.result;
        if(this.selectedImage!=null){
          this.service.setBannerImage(this.selectedImage);
        }
        this.updateAppWeb()
       

      };
      reader.readAsDataURL(file);
    }
  }
  changeBannerImage(event: any) {
    const file: File = event.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = (e: any) => {
          this.selectedImage = e.target.result;
          if(this.selectedImage!=null){
            this.service.setBannerImage(this.selectedImage);

          }
          this.updateAppWeb();
          this.activeModal.close();

        };
        reader.readAsDataURL(file);
      }
  
  }
  updateAppWeb(){
    var data={
      'idAppWeb':this.idAppWeb,
      "bannerImage": this.selectedImage,
  
  }
  this.service.updateAppWeb(data).subscribe(data=>{
    this.toast.toast('Modification De App Web',ToastType.SUCCESS, 'Succes', 5000);

  })
  }
}
