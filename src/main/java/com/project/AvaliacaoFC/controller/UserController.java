package com.project.AvaliacaoFC.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.AvaliacaoFC.entity.UserFC;
import com.project.AvaliacaoFC.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path="/users")
public class UserController {
	
	
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

private final UserService userService;

@ResponseStatus(HttpStatus.OK)
@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)

public ResponseEntity<List<UserFC>> findAll(@RequestParam(name = "name", required = false) String name) {
    List<UserFC> users = userService.findAll(name);
    return new ResponseEntity<>(users, HttpStatus.OK);
}
@GetMapping(path = "/{user_id}", produces = MediaType.APPLICATION_JSON_VALUE)

public ResponseEntity<UserFC> findById(@PathVariable(name = "user_id") Long id) {


	UserFC users = userService.findById(id);

	if (users != null) {
		return new ResponseEntity<>(users, HttpStatus.OK);
	} else {
		return new ResponseEntity<>(users, HttpStatus.NOT_FOUND);
	}
}
/*@ResponseStatus(HttpStatus.OK)
@GetMapping(path = "/departments/{departments_id}", produces = MediaType.APPLICATION_JSON_VALUE)

public ResponseEntity<List<User>> findByDepartment(@PathVariable(name = "departments_id") Long id) {
	List<User> users = userService.findByDepartment(id);
			return new ResponseEntity<>(users, HttpStatus.OK);
	
}*/
@ResponseStatus(HttpStatus.CREATED)
@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<UserFC> create(@RequestBody UserFC user) {


	try {
		UserFC newUser = userService.create(user);
		return new ResponseEntity<UserFC>(newUser, HttpStatus.CREATED);
	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
@ApiOperation(value="Update an User")
@ApiResponses(value= {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Not Found"),
		
		
})
@ResponseStatus(HttpStatus.OK)
@PutMapping(path = "/{user_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

public ResponseEntity<UserFC> update(@PathVariable(name = "user_id") Long id,
		@RequestBody UserFC user) {

	user.setId(id);

	try {
		UserFC newUser = userService.create(user);
		if (newUser != null) {
			return new ResponseEntity<UserFC>(newUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
@ResponseStatus(HttpStatus.NO_CONTENT)
@DeleteMapping(path = "/{user_id}")
public ResponseEntity<Void> deleteById(@PathVariable(name = "user_id") Long id)
{
	userService.deleteById(id);
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	
}
@ResponseStatus(HttpStatus.NO_CONTENT)
@DeleteMapping
public ResponseEntity<Void> deleteAll()
{
	userService.deleteAll();
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	
}

}
