import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Category } from 'src/app/models/category';
import { Field } from 'src/app/models/fields';
import { CategoriesService } from 'src/app/services/categories.service';
import { FieldsService } from 'src/app/services/fields.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-management',
  templateUrl: './management.component.html',
  styleUrls: ['./management.component.css']
})
export class ManagementComponent implements OnInit {

  categories: Category[] = [];
  fields: Field[] = [];
  selectedCategory: any='';

  constructor(private categoryService: CategoriesService, private fieldService: FieldsService) { }
  ngOnInit(): void {
    this.fieldService.getFields().subscribe(
      {
        next: data => {
          this.fields = data.body
        },
        error: error => { }
      }
    )
  }

  updateCategorias(id: string) {
    let idNumber: number = parseInt(id);
    this.categoryService.getCategories().subscribe(
      {
        next: data => {
          this.categories = data.body.filter((c: Category) => c.field.fieldId == idNumber)
        }
      }
    )
  }

  createCategory(name: string, desc: string, field:string){
    let idNumber: number = parseInt(field);
    this.selectedCategory= {}
    this.selectedCategory.catName = name;
    this.selectedCategory.catDetail = desc;
    this.selectedCategory.field=  this.fields.filter(f=>f.fieldId==idNumber)[0]
    console.log(this.selectedCategory)
    this.categoryService.createCategory(this.selectedCategory).subscribe(
      {
        next: (data) => {

          Swal.fire({
            position: "center",
            icon: "success",
            title: `Categoria creada exitosamente`,

            showConfirmButton: false,
            timer: 1500
          })
          this.selectedCategory=''
        },
        error: (error) => {

          Swal.fire({
            position: "center",
            icon: "error",
            title: error.error,

            showConfirmButton: false,
            timer: 1500
          })
          this.selectedCategory=''
        }
      }
    )
  }

  editCategory(name: string, desc: string) {
 
    this.selectedCategory= this.categories.filter(c=>c.catId==this.selectedCategory)[0]
    this.selectedCategory.catName = name;
    this.selectedCategory.catDetail = desc;
    
    this.categoryService.updateCategory(this.selectedCategory).subscribe(
      {
        next: (data) => {

          Swal.fire({
            position: "center",
            icon: "success",
            title: `Categoria modificada exitosamente`,

            showConfirmButton: false,
            timer: 1500
          })
          this.selectedCategory=''
        },
        error: (error) => {

          Swal.fire({
            position: "center",
            icon: "error",
            title: error.error,

            showConfirmButton: false,
            timer: 1500
          })
          this.selectedCategory=''
        }
      }
    )
  }

  deleteCategory() {
    this.selectedCategory= this.categories.filter(c=>c.catId==this.selectedCategory)[0]
    this.categoryService.deleteCategory(this.selectedCategory).subscribe(
      {
        next: (data) => {

          Swal.fire({
            position: "center",
            icon: "success",
            title: `Categoria elimina exitosamente`,

            showConfirmButton: false,
            timer: 1500
          })
          this.selectedCategory=''
        },
        error: (error) => {
          
          Swal.fire({
            position: "center",
            icon: "error",
            title: error.error,
            
            showConfirmButton: false,
            timer: 1500
          })
          this.selectedCategory=''
        }
      }
    )
  }

}
