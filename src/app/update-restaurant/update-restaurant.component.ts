import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Restaurant } from '../restaurant';
import { RestaurantService } from '../restaurant.service';

@Component({
  selector: 'app-update-restaurant',
  templateUrl: './update-restaurant.component.html',
  styleUrls: ['./update-restaurant.component.css']
})
export class UpdateRestaurantComponent implements OnInit {

  id: number;
  restaurant:Restaurant;
  submitted=false;
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
  }

  updateRestaurant() {
    this.restaurantService.updateRestaurant(this.id, this.restaurant)
      .subscribe(data => {
        console.log(data);
        this.restaurant = new Restaurant();
        this.gotoList();
      }, error => console.log(error));
  }

  onSubmit() {
    this.submitted=true;
    this.updateRestaurant();    
  }

  gotoList() {
    this.router.navigate(['/restaurants']);
  }


}
