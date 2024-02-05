import { HttpResponse } from '@angular/common/http';
import { Component, OnInit} from '@angular/core';
import { Supplier } from 'src/app/models/supplier';
import { SuppliersService } from 'src/app/services/suppliers.service';

@Component({
  selector: 'app-suppliers-list',
  templateUrl: './suppliers-list.component.html',
  styleUrls: ['./suppliers-list.component.css']
})
export class SuppliersListComponent implements OnInit{
  suppliersList:Supplier[]=[]
  constructor( public supplierService:SuppliersService){

  }
  ngOnInit(): void {
    this.updateLista()
  }

  deleteProveedor(id:string){
    this.supplierService.deleteSupplier(id)
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
}
