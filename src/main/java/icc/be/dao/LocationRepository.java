package icc.be.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import icc.be.entites.Locality;
import icc.be.entites.Location;

public interface LocationRepository extends JpaRepository<Location, Long>{
	public Location findByDesignation(String designation);
	public Location findBySlug(String slug);
	public Location findByLocality(Locality locality);
	@Query("select l from Location l where l.designation like :x")
	public Page<Location> rechercheParDesignation(@Param("x") String mc, Pageable pageable);
}
