import { Component } from '@angular/core';
import {  SuppliersService } from 'src/app/services/suppliers.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-suppliers-view',
  templateUrl: './suppliers-view.component.html',
  styleUrls: ['./suppliers-view.component.css']
})
export class SuppliersViewComponent {
constructor(private supplierService: SuppliersService, private route: ActivatedRoute){}

  supplier= this.supplierService.getSupplierById(this.route.snapshot.paramMap.get("id-supplier")!)

}
