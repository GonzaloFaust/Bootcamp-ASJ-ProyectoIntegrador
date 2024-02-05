import { Component, OnInit } from '@angular/core';
import {  SuppliersService } from 'src/app/services/suppliers.service';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Supplier } from 'src/app/models/supplier';

@Component({
  selector: 'app-suppliers-view',
  templateUrl: './suppliers-view.component.html',
  styleUrls: ['./suppliers-view.component.css']
})
export class SuppliersViewComponent implements OnInit{

  
  supplier:Supplier | undefined;
constructor(private supplierService: SuppliersService, private route: ActivatedRoute){}


  ngOnInit(): void {
    
    this.supplierService.getSupplierById(this.route.snapshot.paramMap.get("id-supplier")!).subscribe(
      {
        next:(data:HttpResponse<Supplier>)=>{this.supplier=data.body!} ,
          error: (error:any)=> console.log(error)
      }
    )
  }

  

}
