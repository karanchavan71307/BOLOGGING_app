package com.blogapplication.paylaod;



import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.blogapplication.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDto {

	
	private int id;
	
	@NotEmpty
	@Size(min = 4,message = "name must be min 4 charector !!" )
	private String name;
	
	@Email
	private String email;
	
	@NotEmpty
	@Size(min = 4, max = 10,message = "password must be between 4-10 charector !!")
	private String password;
	
	@NotEmpty()
	@Size(max = 100)
	private String about;
	
	private List<Role> role=new ArrayList<>();
}
