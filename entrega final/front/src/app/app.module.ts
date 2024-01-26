import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ProductosListaComponent } from './components/productos/productos-lista/productos-lista.component';
import { ProductosVerComponent } from './components/productos/productos-ver/productos-ver.component';
import { ProductosCrearComponent } from './components/productos/productos-crear/productos-crear.component';
import { ProveedoresListaComponent } from './components/proveedores/proveedores-lista/proveedores-lista.component';
import { ProveedoresCrearComponent } from './components/proveedores/proveedores-crear/proveedores-crear.component';
import { OrdenesListaComponent } from './components/ordenes/ordenes-lista/ordenes-lista.component';
import { OrdenesCrearComponent } from './components/ordenes/ordenes-crear/ordenes-crear.component';
import { OrdenesVerComponent } from './components/ordenes/ordenes-ver/ordenes-ver.component';
import { ProveedoresVerComponent } from './components/proveedores/proveedores-ver/proveedores-ver.component';
import { HomeComponent } from './components/home/home.component';
import { AsideComponent } from './shared/aside/aside.component';
import { FooterComponent } from './shared/footer/footer.component';
import { HeaderComponent } from './shared/header/header.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { OrdenesService } from './services/ordenes.service';
import { ProductosService } from './services/productos.service';
import { ProveedoresService } from './services/proveedores.service';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LocalizationService } from './services/localization.service';
import { ValidationsService } from './services/validations.service';

@NgModule({
  declarations: [
    AppComponent,
    ProductosListaComponent,
    ProductosVerComponent,
    ProductosCrearComponent,
    ProveedoresListaComponent,
    ProveedoresCrearComponent,
    OrdenesListaComponent,
    OrdenesCrearComponent,
    OrdenesVerComponent,
    ProveedoresVerComponent,
    HomeComponent,
    AsideComponent,
    FooterComponent,
    HeaderComponent,
    NotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FontAwesomeModule,
    FormsModule,
    HttpClientModule
    
  ],
  providers: [OrdenesService,ProductosService, ProveedoresService,LocalizationService, ValidationsService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
