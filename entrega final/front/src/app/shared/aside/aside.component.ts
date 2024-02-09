import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { faHome, faTruck, faBox, faMoneyBill, faGear } from '@fortawesome/free-solid-svg-icons'

@Component({
  selector: 'app-aside',
  templateUrl: './aside.component.html',
  styleUrls: ['./aside.component.css']
})
export class AsideComponent {
faSupplier=faTruck
faProduct=faBox
  faAdmin=faGear
  faOrder=faMoneyBill
  faHome=faHome
  constructor(private route: Router){}

  laRuta(word:string):boolean{
    
    return this.route.routerState.snapshot.url.split('/').includes(word);
  }
}
