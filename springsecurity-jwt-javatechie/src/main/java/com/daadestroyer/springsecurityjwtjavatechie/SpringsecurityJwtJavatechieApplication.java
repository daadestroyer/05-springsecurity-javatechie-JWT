package com.daadestroyer.springsecurityjwtjavatechie;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.daadestroyer.springsecurityjwtjavatechie.entity.User;
import com.daadestroyer.springsecurityjwtjavatechie.repo.UserRepo;

@SpringBootApplication
public class SpringsecurityJwtJavatechieApplication {

	@Autowired
	private UserRepo userRepo;
	
	@PostConstruct
	public void initUsers() {
		List<User> listOfUsers = Stream.of(new User(101, "shubham nigam", "pwd1", "shubham@gmail.com"),
				new User(102, "shubhanshu arya", "pwd2", "shubhanshu@gmail.com"),
				new User(103, "ansh gupta", "pwd3", "ansh@gmail.com"),
				new User(104, "vatsal patel", "pwd4", "vatsal@gmail.com")).collect(Collectors.toList());
	
		this.userRepo.saveAll(listOfUsers);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityJwtJavatechieApplication.class, args);
	}

}
