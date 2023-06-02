package com.net.blog.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.net.blog.entities.User;
import com.net.blog.repositories.GeneralRepository;

@Repository
interface UserRepository extends GeneralRepository<User, Long>{}

@Configuration
@Profile("test")
public class Config implements CommandLineRunner 
{
	@SuppressWarnings("unused")
	@Autowired
	private UserRepository userRepository;			
	
	@Override
	public void run(String... args) throws Exception 
	{
		
	}
}
