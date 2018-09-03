package icc.be.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import icc.be.entites.Locality;


public interface LocalityRepository extends JpaRepository<Locality, Long>{
	public Locality findByPostalCode(String postalCode);
	public Locality findByName(String name);
	
	@Query("select l from Locality  l where l.name like :x")
	public Page<Locality> chercherLocality(@Param("x") String motcle, Pageable pageable);
	}
