import { Component, OnInit} from '@angular/core';
import { SuppliersService } from 'src/app/services/suppliers.service';

@Component({
  selector: 'app-suppliers-list',
  templateUrl: './suppliers-list.component.html',
  styleUrls: ['./suppliers-list.component.css']
})
export class SuppliersListComponent implements OnInit{
  proveedores:any[]=[]
  constructor( public service:SuppliersService){

  }
  ngOnInit(): void {
    this.updateLista()
  }

  deleteProveedor(id:string){
    this.service.deleteSupplier(id)
    this.updateLista();
  }
  
  private updateLista(){
    this.proveedores = this.service.getSuppliers()
  }
}
