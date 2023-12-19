import { Component, OnInit } from '@angular/core';
import { ServiceProveedoresService } from 'src/app/services/service-proveedores.service';

@Component({
  selector: 'app-proveedores-lista',
  templateUrl: './proveedores-lista.component.html',
  styleUrls: ['./proveedores-lista.component.css']
})
export class ProveedoresListaComponent implements OnInit{
  proveedores:any[]=[]
  constructor( public service:ServiceProveedoresService){

  }
  ngOnInit(): void {
    this.updateLista()
  }
  delete(id:string){
    this.service.deleteProveedor(id)
    this.updateLista();
  }
  
  private updateLista(){
    this.proveedores= this.service.getProveedores()
    console.log(this.proveedores)
  }
}
