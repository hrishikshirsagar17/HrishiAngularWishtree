import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Restaurant } from '../restaurant';
import { RestaurantService } from '../restaurant.service';

@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styleUrls: ['./restaurant-details.component.css']
})
export class RestaurantDetailsComponent implements OnInit {

  id: number;
  restaurant:Restaurant;
  url:string;
  imageurl="http://localhost:8080/zonions/imgrestaurant";
  constructor(private route: ActivatedRoute,private router: Router,
    private restaurantService: RestaurantService) { }

  ngOnInit(): void {
    this.restaurant = new Restaurant();

    this.id = this.route.snapshot.params['id'];
    
    this.restaurantService.getRestaurant(this.id)
      .subscribe(data => {
        console.log(data)
        this.restaurant = data;
      }, error => console.log(error));

      this.url=`${this.imageurl}/${this.restaurant.id}`;
  }

  list(){
    this.router.navigate(['restaurants']);
  }

}