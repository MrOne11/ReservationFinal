package icc.be.metier;


import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import icc.be.dao.ArtisteRepository;
import icc.be.dao.LocalityRepository;
import icc.be.dao.LocationRepository;
import icc.be.dao.ShowRepository;
import icc.be.entites.Locality;
import icc.be.entites.Location;
import icc.be.entites.Show;



@Service
@Transactional
public class metierImpl implements metier{
	@Autowired
	private LocalityRepository localityRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private ShowRepository showRepository;
	
	//Association Location et Locality
	@Override
	public Location addLocalityToLocation(Location location, Long idLocality) {
		Locality locality=localityRepository.getOne(idLocality);
		location.setLocality(locality);
		locationRepository.save(location);
		
		return location;
	}
	
	
	@Override
	public Show addLocationToShow(Show show, Long idlocation) {
		Location location = locationRepository.getOne(idlocation);
		show.setLocation(location);
		showRepository.save(show);
		return show;
	}
	
	


	
	

	


	


	

}
