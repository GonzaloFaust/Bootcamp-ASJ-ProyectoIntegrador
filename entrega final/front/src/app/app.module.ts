import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ProductsListComponent } from './components/products/products-list/products-list.component';
import { ProductsViewComponent } from './components/products/products-view/products-view.component';
import { ProductsFormComponent } from './components/products/products-form/products-form.component';
import { SuppliersListComponent } from './components/suppliers/suppliers-list/suppliers-list.component';
import { SuppliersFormComponent } from './components/suppliers/suppliers-form/suppliers-form.component';
import { OrdersListComponent } from './components/orders/orders-list/orders-list.component';
import { OrdersFormComponent } from './components/orders/orders-form/orders-form.component';
import { OrdersViewComponent } from './components/orders/orders-view/orders-view.component';
import { SuppliersViewComponent } from './components/suppliers/suppliers-view/suppliers-view.component';
import { HomeComponent } from './components/home/home.component';
import { AsideComponent } from './shared/aside/aside.component';
import { FooterComponent } from './shared/footer/footer.component';
import { HeaderComponent } from './shared/header/header.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { OrdersService } from './services/orders.service';
import { ProductsService } from './services/products.service';
import { SuppliersService } from './services/suppliers.service';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LocalizationService } from './services/localization.service';
import { ValidationsService } from './services/validations.service';
import { LoginComponent } from './components/login/login.component';



@NgModule({
  declarations: [
    AppComponent,
    ProductsListComponent,
    ProductsViewComponent,
    ProductsFormComponent,
    SuppliersListComponent,
    SuppliersFormComponent,
    OrdersListComponent,
    OrdersFormComponent,
    OrdersViewComponent,
    SuppliersViewComponent,
    HomeComponent,
    AsideComponent,
    FooterComponent,
    HeaderComponent,
    NotFoundComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FontAwesomeModule,
    FormsModule,
    HttpClientModule,

  ],
  providers: [OrdersService,ProductsService, SuppliersService,LocalizationService, ValidationsService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
