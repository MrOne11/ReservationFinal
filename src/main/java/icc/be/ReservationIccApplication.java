package icc.be;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import icc.be.dao.ArtisteRepository;
import icc.be.dao.TypeRepository;
import icc.be.dao.UserRepository;
import icc.be.entites.AppUser;
import icc.be.entites.AppUser.AppRole;
import icc.be.entites.Artiste;
import icc.be.entites.Type;
import icc.be.metier.AccountService;

@SpringBootApplication
public class ReservationIccApplication implements CommandLineRunner{

	@Autowired
	private ArtisteRepository artisteRepository;
	
	@Autowired
	private TypeRepository typeRepository;
	
	@Autowired 
	UserRepository userRepository;

	@Autowired 
	AccountService accountService;
	
	public static void main(String[] args) {
		SpringApplication.run(ReservationIccApplication.class, args);
		
		
		
	}

	@Bean
	public BCryptPasswordEncoder getBcryptPassEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Override
	public void run(String... args) throws Exception {
		//accountService.saveUser(new AppUser("mar", "3333", AppRole.ADMIN));
		
	}
}
