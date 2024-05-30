package samuelastech.user.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import samuelastech.user.UserEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {	
	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message = "E-mail is required")
	private String email;
	
	@JsonIgnore
	@NotBlank(message = "Password is required")
	private String password;
	
	public static UserDTO convert(UserEntity userEntity) {
		UserDTO user = new UserDTO();
		user.setName(userEntity.getName());
		user.setEmail(userEntity.getEmail());
		user.setPassword(userEntity.getPassword());
		return user;
	}
}
