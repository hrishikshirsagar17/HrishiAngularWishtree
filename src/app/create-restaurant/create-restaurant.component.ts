import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Restaurant } from '../restaurant';
import { RestaurantService } from '../restaurant.service';

@Component({
  selector: 'app-create-restaurant',
  templateUrl: './create-restaurant.component.html',
  styleUrls: ['./create-restaurant.component.css']
})
export class CreateRestaurantComponent implements OnInit {

  restaurant: Restaurant = new Restaurant();
  submitted = false;
  file:any;
  resid:any;
  //pickerTwo:any;

  constructor(private restaurantService: RestaurantService, private router: Router) { }


  ngOnInit() {
  }

  newRestaurant(): void {
    this.submitted = false;
    this.restaurant = new Restaurant();
  }

  save() {
    this.restaurantService
    .createRestaurant(this.restaurant).subscribe(data => {
      console.log(data);
      this.resid = data;
      console.log(this.resid.id);
      
    }, 
    error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  uploadFile()
  {
    console.log("Uploading..."+this.file+" : "+this.resid.id);
    this.restaurantService.pushFile(this.file, this.resid.id).subscribe(resp =>{
        console.log(resp)
      },error=>console.log(error)
      );
      this.gotoList();
  }

  onFileChangeEvent(file)
  {
    this.file=file;
    console.log("image..."+this.file)
  }

  gotoList() {
    this.router.navigate(['/restaurants']);
  }

}