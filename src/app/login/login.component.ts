import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Admin } from '../admin';
import { AdminService } from '../admin.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  Admin =new Admin();
  msg='';
  constructor(private _service:AdminService,private _router:Router) { }
  ngOnInit(): void {
  }
 loginAdmin()
{
console.log("Login button"+this.Admin.username);
console.log("Login button"+this.Admin.password);

this._service.loginAdminFromRemote(this.Admin).subscribe(
  data =>{ 
    console.log("Response received")
    sessionStorage.setItem("user",JSON.stringify(this.Admin.username));
    this._router.navigate(["restaurants"]);
    },
  error =>{
     console.log("Exception occured"+error)
     this.msg="Invalid credentials please enter valid email id / password";  
    }
  );
}

}
