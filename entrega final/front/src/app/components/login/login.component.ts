import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

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

    this.loginService.tryLogIn(this.user,this.pass)
    location.reload();
  }

}
