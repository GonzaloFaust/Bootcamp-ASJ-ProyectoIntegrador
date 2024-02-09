import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  
constructor(private loginService:LoginService){}
  ngOnInit(): void {
    
  }
  user="";
  pass="";

  logIn(){

   if( this.loginService.tryLogIn(this.user,this.pass)){
     location.reload();

   }
   else{
    Swal.fire({
      position: "center",
      icon: "error",
      title: "Usuario o contraseña incorrecta",
      showConfirmButton: false,
      timer: 1500
    })
   }
  }

}
