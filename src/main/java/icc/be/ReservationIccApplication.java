package icc.be;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import icc.be.dao.ArtisteRepository;
import icc.be.dao.TypeRepository;
import icc.be.entites.Artiste;
import icc.be.entites.Type;

@SpringBootApplication
public class ReservationIccApplication implements CommandLineRunner{

	@Autowired
	private ArtisteRepository artisteRepository;
	
	@Autowired
	private TypeRepository typeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ReservationIccApplication.class, args);
		
		
		
	}

	@Override
	public void run(String... args) throws Exception {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		artisteRepository.save(new Artiste("X", "B", df.parse("1970-09-20"), "test@gmail.com", "x.png"));
		artisteRepository.save(new Artiste("c", "K", df.parse("1969-03-10"), "test2@gmail.com", "p.png"));
		
		typeRepository.save(new Type("comédien"));
		typeRepository.save(new Type("chanteur"));
		typeRepository.save(new Type("dansseur"));
		
	}
}
