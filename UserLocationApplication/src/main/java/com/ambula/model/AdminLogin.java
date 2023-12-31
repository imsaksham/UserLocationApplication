package com.ambula.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdminLogin {

	@NotNull
	@NotBlank
	@Size(min = 3, max = 20)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Please input a valid username which contain lowercase character and length should be minimum 3 and maximum 20")
	private String username;
	
	@NotNull
	@NotBlank
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,12}$", message = "Password must be alphanumeric and must contain 6-12 characters having at least one special character, one upper case, one lowercase, and one digit.")
	private String password;
}
