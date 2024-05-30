package samuelastech.user;

import samuelastech.user.dtos.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	
	public List<UserDTO> getUsers() {
		List<UserEntity> users = this.userRepository.findAll();
		return users.stream().map(UserDTO::convert).collect(Collectors.toList());
	}
	
	public Page<UserDTO> getPageableUsers(Pageable page) {
		Page<UserEntity> users = this.userRepository.findAll(page);
		return users.map(UserDTO::convert);
	}
	
	public UserDTO getUser(long id) {
		UserEntity user = findUserOrThrow(id);
		return UserDTO.convert(user);
	}
	
	public UserDTO createUser(UserDTO userDTO) {
		UserEntity user = this.userRepository.save(UserEntity.convert(userDTO));
		return UserDTO.convert(user);
	}
	
	public UserDTO deleteUser(long id) {
		UserEntity user = findUserOrThrow(id);
		this.userRepository.delete(user);
		return UserDTO.convert(user);
	}
	
	public UserDTO updateUser(long id, UserDTO userDTO) {
		UserEntity user = findUserOrThrow(id);
		user.setEmail(userDTO.getEmail());
		user.setName(userDTO.getName());
		user.setPassword(userDTO.getPassword());
		this.userRepository.save(user);
		return UserDTO.convert(user);
	}
	
	private UserEntity findUserOrThrow(long id) {
		return this.userRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("User not found"));
	}
}
