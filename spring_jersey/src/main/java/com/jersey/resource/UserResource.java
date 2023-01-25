package com.jersey.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jersey.entity.User;
import com.jersey.excption.ResourceNotFoundException;
import com.jersey.repo.UserRepo;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Controller
@Path("api/v1")
public class UserResource {

	@Autowired
	private UserRepo userRepo;

	@GET
	@Produces("application/json")
	@Path("/users")
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@GET
	@Produces("application/json")
	@Path("/users/{id}")
	public ResponseEntity<User> getUserById(@PathParam(value = "id") Integer id) throws ResourceNotFoundException {
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found ::" + id));
		return ResponseEntity.ok().body(user);
	}

	@PUT
	@Consumes("/application/json")
	@Path("/users/{id}")
	public ResponseEntity<User> updateUser(@PathParam(value = "id") int id, @Valid @RequestBody User userDetails)
			throws ResourceNotFoundException {
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not foung::" + id));
		user.setEmail(userDetails.getEmail());
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		final User updateUser = userRepo.save(user);
		return ResponseEntity.ok(updateUser);
	}

	@DELETE
	@Path("users/{id}")
	public Map<String, Boolean> deleteUser(@PathParam(value = "id") Integer id) throws ResourceNotFoundException {
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found::" + id));
		userRepo.delete(user);
		Map<String, Boolean> res = new HashMap<>();
		res.put("deleted", Boolean.TRUE);
		return res;
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/users")
	@PostMapping("/users")
	public User saveUser(User user) {
		User u= userRepo.save(user);
		System.out.println(u.getFirstName());
		return u;
	}

}
