import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-aside',
  templateUrl: './aside.component.html',
  styleUrls: ['./aside.component.css']
})
export class AsideComponent {

  constructor(private route: ActivatedRoute){}

  laRuta():boolean{
    const params =   this.route.snapshot.params
    console.log(params)
    return false;
  }
}
