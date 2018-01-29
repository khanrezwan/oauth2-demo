package com.rezwanislam.oauth2demo;

import com.rezwanislam.oauth2demo.model.Client;
import com.rezwanislam.oauth2demo.model.User;
import com.rezwanislam.oauth2demo.repository.ClientRepository;
import com.rezwanislam.oauth2demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableResourceServer
public class Oauth2DemoApplication implements CommandLineRunner {

	PasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(Oauth2DemoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		//todo tut https://spring.io/guides/gs/accessing-data-mongodb/
//		clientRepository.deleteAll();
//		userRepository.deleteAll();
//		User user1, user2;
//
//		user1 = new User("rik", "rik@abc.com", true, true, "password");
//		user2 = new User("mahb", "mahb@abc.com", true, true, "password");
//		userRepository.save(user1);
//		userRepository.save(user2);
//
//		Client client1;
//		Set<String>scopes = new HashSet<>();
//		scopes.add("read");
//		scopes.add("write");
//		client1 = new Client("cli1","secret",scopes);
//		clientRepository.save(client1);


	}
}

//todo following these tuts
// https://gigsterous.github.io/engineering/2017/03/01/spring-boot-4.html
// https://github.com/dsyer/sparklr-boot
// https://jugbd.org/2017/09/19/implementing-oauth2-spring-boot-spring-security/
// https://docs.spring.io/spring-security/oauth/apidocs/org/springframework/security/oauth2/provider/package-summary.html