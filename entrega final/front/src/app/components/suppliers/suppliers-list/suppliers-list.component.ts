import { HttpResponse } from '@angular/common/http';
import { Component, OnInit} from '@angular/core';
import { Supplier } from 'src/app/models/supplier';
import { SuppliersService } from 'src/app/services/suppliers.service';
import { faPen, faEye, faTrash, faRefresh } from '@fortawesome/free-solid-svg-icons'
import { environment } from 'src/environments/environment.development';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-suppliers-list',
  templateUrl: './suppliers-list.component.html',
  styleUrls: ['./suppliers-list.component.css']
})
export class SuppliersListComponent implements OnInit{
  suppliersList:Supplier[]=[]
  faEdit = faPen
  faView = faEye
  faDelete = faTrash
  faUndelete = faRefresh
  showDeleted:boolean=false;

  constructor( public supplierService:SuppliersService){

  }
  ngOnInit(): void {
    this.updateLista()
  }

  deleteProveedor(sup:Supplier){
    this.supplierService.deleteSupplier(sup)
    this.updateLista();
  }
  
  private updateLista(){
   this.supplierService.getSuppliers().subscribe(
      {
        next:(data:HttpResponse<Supplier[]>)=>{this.suppliersList=data.body!} ,
         error: (error:any)=> console.log(error)
      }
    )
  }

  filters: any = {
    searchTerm: "",
  }

  showDeletedProducts(){
    this.showDeleted=!this.showDeleted
    this.search();
  }

  cleanForm(){
    this.filters.searchTerm='';
    this.search();
  }

  search() {
    // console.log("!antes: ",this.filters)
    this.supplierService.getSupplierBySearch(this.filters).subscribe({
      next: (data: HttpResponse<Supplier[]>) => {
        this.suppliersList = data.body!;
      },
      error: (error) => console.error(error.message)
    })
  }
  showTable():boolean{
    let count=0
    for(let sup of this.suppliersList){
      if(sup.isActive || this.showDeleted!=sup.isActive)
      count++;
    }
    return count>0;
  }

  deleteSupplier(sup: Supplier) {
    Swal.fire({
      title: 'Eliminar proveedor',
      text: `Seguro desea eliminar ${sup.supBussinessName}?`,
      icon: 'question',
      showCancelButton: true,
      allowOutsideClick: false,
      allowEscapeKey: false,
      confirmButtonText: 'Eliminar',
      cancelButtonText: 'Cancelar',
    })
      .then(res => {
        if (res.isConfirmed) {
          this.supplierService.deleteSupplier(sup).subscribe(
            {
              next: (data: HttpResponse<String>) => { console.log(data.statusText);this.search();},
              error: (error) => console.error(error.message)
            }
          )
        }
      })
    
  }

  handleImageError(image: HTMLImageElement) {
    image.src = environment.SUP_IMAGE_MOCK
  }

  undeleteSupplier(sup:Supplier) {
    this.supplierService.undeleteProduct(sup).subscribe(
      {
        next: (data: HttpResponse<String>) => { console.log(data) ;this.search();},
        error: (error) => console.error(error.message)
      }
    )
    
   }
}
