import { Component, OnInit } from '@angular/core';
import { ProductsService } from 'src/app/services/products.service';
import { environment } from 'src/environments/environment.development';
import { faPen, faEye, faTrash, faRefresh } from '@fortawesome/free-solid-svg-icons'
import { CategoriesService } from 'src/app/services/categories.service';
import { Category } from 'src/app/models/category';
import { HttpResponse } from '@angular/common/http';
import { Product } from 'src/app/models/product';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.css']
})
export class ProductsListComponent implements OnInit {
  faEdit = faPen
  faView = faEye
  faDelete = faTrash
  faUndelete = faRefresh

  showDeleted:boolean=false;
  constructor(private productsService: ProductsService, private categoryService: CategoriesService) { }
  ngOnInit(): void {
    this.getAllProducts();
    this.getAllCategories();
  }

  getAllProducts(){
    this.productsService.getProducts().subscribe(
      {
        next: (data: HttpResponse<Product[]>) => { this.products = data.body! },
        error: (error) => console.log(error)
      }
    )
  }

  getAllCategories(){
    this.categoryService.getCategories().subscribe({

      next: (data: HttpResponse<Category[]>) => { this.categories = data.body! },
      error: (error) => console.log(error)
    })
  }

  showTable():boolean{
    let count=0
    for(let prod of this.products){
      if(prod.prodAvailable || this.showDeleted!=prod.prodAvailable)
      count++;
    }
    return count>0;
  }

  showDeletedProducts(){
    this.showDeleted=!this.showDeleted
    this.search();
  }

  cleanForm(){
    this.filters.searchTerm='';
    this.filters.category='';
    this.search();
  }

  categories: Category[] = []
  products: Product[] = []

  filters: any = {
    searchTerm: "",
    category: ''
  }

  deleteProduct(prod: Product) {
    Swal.fire({
      title: 'Eliminar producto',
      text: `Seguro desea eliminar ${prod.prodName}?`,
      icon: 'question',
      showCancelButton: true,
      allowOutsideClick: false,
      allowEscapeKey: false,
      confirmButtonText: 'Eliminar',
      cancelButtonText: 'Cancelar',
    })
      .then(res => {
        if (res.isConfirmed) {
          this.productsService.deleteProduct(prod).subscribe(
            {
              next: (data: HttpResponse<String>) => { console.log(data.statusText);this.search() },
              error: (error) => console.error(error.message)
            }
          )
        }
      })
    // 
  }

  handleImageError(image: HTMLImageElement) {
    image.src = environment.PROD_IMAGE_MOCK
  }

  undeleteProduct(prod: Product) {
    this.productsService.undeleteProduct(prod.prodId).subscribe(
      {
        next: (data: HttpResponse<String>) => { console.log(data);this.search(); },
        error: (error) => console.error(error.message)
      }
    )
  }


  search() {
    console.log("!antes: ",this.filters)
    this.productsService.getProductBySearch(this.filters).subscribe({
      next: (data: HttpResponse<Product[]>) => {
        this.products = data.body!;
      
      },
      error: (error) => console.error(error.message)
    })
  }
}
