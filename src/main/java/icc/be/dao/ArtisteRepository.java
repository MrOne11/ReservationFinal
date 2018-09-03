package icc.be.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import icc.be.entites.Artiste;

public interface ArtisteRepository extends JpaRepository<Artiste, Long>{

	public Page<Artiste> findByNom(String nom, Pageable pageable);
	public Artiste findByNom(String nom);
	public Artiste findByPrenom(String prenom);
		@Query("select a from Artiste  a where a.nom like :x")
	public Page<Artiste> chercherArtiste(@Param("x") String motcle, Pageable pageable);
		
		@Query("select a from Artiste  a where a.dateNaissance > :x and a.dateNaissance < :y")	
	public List<Artiste> chercherParDate(@Param("x") Date d1,@Param("y") Date d2);

}
