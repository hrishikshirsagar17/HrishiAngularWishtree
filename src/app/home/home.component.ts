import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Restaurant } from '../restaurant';
import { RestaurantService } from '../restaurant.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  restaurants: Observable<Restaurant[]>;
  constructor(private restaurantService: RestaurantService, private router: Router) { }

  ngOnInit(): void {

    this.reloadData();
  }

  reloadData()
  {
    this.restaurants = this.restaurantService.getRestaurantsList();
   
    this.restaurants.subscribe (data=> {
      console.log(data);
      
    });
    
  }

  restaurantDetails(id: number){
    this.router.navigate(['details', id]);
  }

}
