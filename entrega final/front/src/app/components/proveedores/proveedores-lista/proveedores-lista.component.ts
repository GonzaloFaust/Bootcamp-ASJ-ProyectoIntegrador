import { Component, OnInit} from '@angular/core';
import { ProveedoresService } from 'src/app/services/proveedores.service';

@Component({
  selector: 'app-proveedores-lista',
  templateUrl: './proveedores-lista.component.html',
  styleUrls: ['./proveedores-lista.component.css']
})
export class ProveedoresListaComponent implements OnInit{
  proveedores:any[]=[]
  constructor( public service:ProveedoresService){

  }
  ngOnInit(): void {
    this.updateLista()
  }

  deleteProveedor(id:string){
    this.service.deleteProveedor(id)
    this.updateLista();
  }
  
  private updateLista(){
    this.proveedores = this.service.getProveedores()
  }
}
