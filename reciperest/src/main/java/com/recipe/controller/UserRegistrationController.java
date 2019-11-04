package com.recipe.controller;

import com.recipe.exception.UserAlreadyExists;
import com.recipe.models.User;
import com.recipe.userservices.IUserService;
import com.recipe.userservices.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class UserRegistrationController {

	private final IUserService userService;

	public UserRegistrationController(IUserService userService) {
		this.userService = userService;
	}

	@PostMapping("/registration")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) throws Exception {
		User existing = userService.findByEmail(userDTO.getEmail());
		if(existing != null) {
			throw new UserAlreadyExists();
		}
		UserDTO userDto = userService.saveAPI(userDTO);
		return ResponseEntity.created(URI.create("/api/user/"+userDto.getId())).body(userDto);
	}

	@GetMapping("/user")
	public UserDTO getByEmail(@RequestParam("email") String email) {
		User existing = userService.findByEmail(email);
		if(existing == null) {
			return null;
		}
		UserDTO userDTO = new UserDTO(existing.getId(),
				existing.getFirstName(), existing.getLastName(),
				email, null);
		return userDTO;
	}

	@ExceptionHandler(UserAlreadyExists.class)
	public ResponseEntity<?> handleException(UserAlreadyExists e){
		return ResponseEntity.unprocessableEntity().body(e);
	}
}
