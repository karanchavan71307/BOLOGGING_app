package com.blogapplication;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class BloggingApplication  implements CommandLineRunner{


	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(BloggingApplication.class, args);
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		String encode = this.passwordEncoder.encode("nitin111");
		System.out.println(encode);
		
		
	}

	
}
