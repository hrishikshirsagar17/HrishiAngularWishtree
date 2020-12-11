package com.spring.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.rest.entity.Admin;
import com.spring.rest.entity.Restaurant;
import com.spring.rest.service.AdminService;
import com.spring.rest.service.RestaurantService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/zonions")
public class RestaurantController {

	@Autowired
	private RestaurantService service;
	
	@Autowired
	private AdminService adminService; 
	
	@GetMapping("/restaurants")
	public ResponseEntity<List<Restaurant>> getRestaurants()
	{
		 List<Restaurant> list = service.getAllRestaurants();
		 if(list.size()<=0)
		 {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		 }
		 
			return ResponseEntity.of(Optional.of(list));
	}
	
	@GetMapping("/restaurants/{id}")
	public ResponseEntity<Optional<Restaurant>> getRestaurantById(@PathVariable("id") int id)
	{
		Optional<Restaurant> restaurant=null;
		try
		{
			restaurant = service.getRestaurantById(id);
		if(restaurant==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		catch(Exception e)
		{
			System.out.println("Not Found...");
			e.printStackTrace();
		}
		
		return ResponseEntity.of(Optional.of(restaurant));
	}
	
	@PostMapping("/restaurants")
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant)
	{
		Restaurant res=null;
		try {
			res=service.addRestaurant(restaurant);
			 return ResponseEntity.status(HttpStatus.CREATED).body(res);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@PutMapping("/restaurants/{id}")
	public ResponseEntity<Void> updateRestaurant(@RequestBody Restaurant restaurant ,@PathVariable int id)
	{
		try
		{
			service.updateRestaurantById(restaurant, id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@DeleteMapping("/restaurants/{id}")
	public ResponseEntity<Void> deleteRestaurant(@PathVariable int id)
	{
		try
		{
			service.deleteRestaurantById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@PostMapping("/loginadmin")
	public Admin loginAdmin(@RequestBody Admin admin) throws Exception
	{
		String tempUsername = admin.getUsername();
		String tempPassword = admin.getPassword();
		
		System.out.println(tempUsername+" "+tempPassword);
		
		Admin adminObj = null;
		if(tempUsername != null && tempPassword !=null)
		{
			adminObj = adminService.fetchAdminByUsernameAndPassword(tempUsername, tempPassword);
		}
		
		if(adminObj == null)
		{
			throw new Exception("You are not an admin");
		}
		
		return adminObj;
	}
	
	@PostMapping("/registeradmin")
	public Admin registerAdmin(@RequestBody Admin admin)
	{
		return adminService.registerAdmin(admin);
	}
	//http://localhost.8080/zonions/imgrestaurant//zonions
	@PutMapping(value="/imgrestaurant/{id}",consumes="multipart/form-data")
	public String imageUpload(@RequestParam MultipartFile file, @PathVariable (value="id") int id) throws Exception
	{
		System.out.println("Image "+id);
		return service.uploadImage(file, id);
	}
	
	
//	public  ResponseEntity<Object> getImage(@RequestParam MultipartFile file, @PathVariable int id)
//	{
//		Optional<Restaurant> restaurant=null;
//		try
//		{
//			restaurant = service.getRestaurantById(id);
//		if(restaurant==null)
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//		}
//		catch(Exception e)
//		{
//			System.out.println("Not Found...");
//			e.printStackTrace();
//		}
//		
//		return ResponseEntity.of(Optional.of(restaurant));
//	}
}
