package com.quest.userResource;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.quest.exception.UserNotFoundEXception;

@RestController
public class UserResourceController {
	
	@Autowired
	private UserDaoService service;
	
	
	
	//GET users
	@GetMapping("/users")
	public List<User> getAlluser(){
		return service.findAll();
		
	}
	
	//retriveAllUsers
	@GetMapping("/usersall")
	public List<User> retrieveAlluser(){
		return service.findAll();
		
	}
	
	//retriveUser(int id)
	@GetMapping("/users/{id}")
 public User retriveUserbyId(@PathVariable int id) {
	
		 User user=service.findOne(id);
		 if(user==null) {
			 throw new UserNotFoundEXception("id-"+id);
		 }
		return user;
	 
 }
	
	
	//by using java8
	@GetMapping("/usersbyid/{id}")
	 public User getUserbyId(@PathVariable int id) {
		
			 User user=service.findOneuser(id);
			if(user==null)
				throw new UserNotFoundEXception("id: "+id);
			return user;
		 
	 }
	
	
	
	//created
	
	@PostMapping("/users")
	public void saveUser(@RequestBody User user) {
		service.save(user);
		
	}
	
	
	//java8
	@PostMapping("/userscreate")
	public void createUser(@RequestBody User user) {
		service.createUser(user);
		
		
	}
	
	//details of user & return the created URI -java 8
	@PostMapping("/user")
	public ResponseEntity<User> createUsers(@Valid @RequestBody User user) {
		User savedUser=service.createUser(user);
		
		URI location=ServletUriComponentsBuilder
		.fromCurrentRequest().path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/deletebyid/{id}")
	 public void deleteUserbyId(@PathVariable int id) {
		
	service.deleteById(id);
				 
	 }
	
	//http://localhost:8080/users
	
	//EntityModel
	//WebMvcLinkBuilder
	
	@GetMapping("/usersid/{id}")
	 public EntityModel<User> retriveUser(@PathVariable int id) {
		
			 User user=service.findOneuser(id);
			if(user==null)
				throw new UserNotFoundEXception("id: "+id);
			EntityModel<User> entityModel= EntityModel.of(user);
			
		//	WebMvcLinkBuilder link= linkTo(methodOn(this.getClass()).getAlluser);
					//linkTo(methodOn(this.getClass()).getAlluser)
		//	entityModel.add(link.withRel("users"));
			
			return entityModel;
		 
	 }
	
	
} 
