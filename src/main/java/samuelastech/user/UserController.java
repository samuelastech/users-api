package samuelastech.user;

import samuelastech.user.dtos.UserDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	
	@GetMapping("/{id}")
	public UserDTO getUser(@PathVariable long id) {
		return this.userService.getUser(id);
	}
	
	@GetMapping
	public List<UserDTO> getUsers() {
		return this.userService.getUsers();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO createUser(@RequestBody @Valid UserDTO user) {
		return this.userService.createUser(user);
	}
	
	@DeleteMapping({"/{id}"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public UserDTO deleteUser(@PathVariable long id) {
		return this.userService.deleteUser(id);
	}
	
	@PutMapping({"/{id}"})
	public UserDTO updateUser(@PathVariable long id, @RequestBody @Valid UserDTO user) {
		return this.userService.updateUser(id, user);
	}
}
