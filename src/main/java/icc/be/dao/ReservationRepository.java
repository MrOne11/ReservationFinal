package icc.be.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import icc.be.entites.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	
}
