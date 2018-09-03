package icc.be.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import icc.be.entites.Show;

public interface ShowRepository extends JpaRepository<Show, Long>{
	public Page<Show> findByTitle(String title, Pageable pageable);
	
	public Page<Show> findByBookable(boolean bookable, Pageable pageable);
	
		@Query("select s from Shows  s where s.title like :x")
	public Page<Show> chercherEtudiants(@Param("x") String motcle, Pageable pageable);

}
