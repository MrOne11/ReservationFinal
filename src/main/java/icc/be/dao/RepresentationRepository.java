package icc.be.dao;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import icc.be.entites.Location;
import icc.be.entites.Representation;
import icc.be.entites.Show;

public interface RepresentationRepository extends JpaRepository<Representation, Long>{
	
	@Query("select r from Representation  r where r.show.title like :x")
	public Page<Representation> chercherParTitle(@Param("x") String motcle, Pageable pageable);

	
	public Representation findByShow(Show show);
	
	public Representation findByMoment(Date date);
	
	public Representation findByLocation(Location location);

}
