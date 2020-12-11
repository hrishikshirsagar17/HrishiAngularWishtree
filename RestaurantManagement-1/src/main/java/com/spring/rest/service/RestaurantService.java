package com.spring.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.rest.entity.Restaurant;
import com.spring.rest.repository.RestaurantRepository;

@Service
public class RestaurantService {	

	@Autowired
	private RestaurantRepository repository;
	
	public List<Restaurant> getAllRestaurants()
	{
		List<Restaurant> list = (List<Restaurant>)repository.findAll();
		return list;
	}
	
	public Optional<Restaurant> getRestaurantById(int id)
	{
		Optional<Restaurant> restaurant = repository.findById(id);
		return restaurant;
	}
	
	public Restaurant addRestaurant(Restaurant restaurant)
	{
		Restaurant result = repository.save(restaurant);
		return result;
	}
	
	
	public void updateRestaurantById(Restaurant restaurant, int id)
	{
		restaurant.setId(id);
		repository.save(restaurant);
	}
	
	
	public void deleteRestaurantById(int id)
	{
		repository.deleteById(id);
	}
	
	public String uploadImage(@RequestParam("file") MultipartFile file, int id) throws Exception
	{

		System.out.println("Original Image Byte Size - " + file.getBytes().length);
	    Restaurant restaurant=repository.findById(id).orElseThrow(()->new Exception("Restaurant not found for this id ::" + id));
	     restaurant.setImgName(file.getOriginalFilename());
	     restaurant.setImgtype(file.getContentType());
	     restaurant.setImg(file.getBytes());
	     System.out.println("Upload rest obj:"+restaurant.getName());
	     
		repository.save(restaurant);
		return "Image Uploaded";
	}

	
	
}
