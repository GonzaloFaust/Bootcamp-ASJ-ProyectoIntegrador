import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-aside',
  templateUrl: './aside.component.html',
  styleUrls: ['./aside.component.css']
})
export class AsideComponent {

  constructor(private route: Router){}

  laRuta(word:string):boolean{
    
    return this.route.routerState.snapshot.url.split('/').includes(word);
  }
}
