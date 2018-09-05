package icc.be.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import icc.be.entites.Country;
import icc.be.entites.Type;

public interface CountryRepository extends JpaRepository<Country, Long>{
	public Country findByName(String name);
	
	
	@Query("select c from Country  c where c.name like :x")
	public Page<Country> ChercherPaysParNom(@Param("x") String motcle, Pageable pageable);

}
