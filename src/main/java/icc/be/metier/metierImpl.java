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
import icc.be.dao.RepresentationRepository;
import icc.be.dao.ReservationRepository;
import icc.be.dao.ShowRepository;
import icc.be.dao.UserRepository;
import icc.be.entites.AppUser;
import icc.be.entites.Locality;
import icc.be.entites.Location;
import icc.be.entites.Representation;
import icc.be.entites.Reservation;
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
	@Autowired
	private RepresentationRepository representationRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ReservationRepository reservationRepository;
	
	//Association Location et Locality
	@Override
	public Location addLocalityToLocation(Location location, Long idLocality) {
		Locality locality=localityRepository.getOne(idLocality);
		location.setLocality(locality);
		locationRepository.save(location);
		
		return location;
	}
	
	





	@Override
	public Representation addShowToRepresentation(Representation representation, Long idShow, Long idLocation) {
		Show shows = showRepository.getOne(idShow);
		representation.setShow(shows);
		representationRepository.save(representation);
		return representation;
	}


	@Override
	public Show addLocationToShow(Show show, Long idlocation) {
		Location location = locationRepository.getOne(idlocation);
		show.setLocation(location);
		showRepository.save(show);
		return show;
	}







	@Override
	public Reservation reservation(Reservation reservation, Long idRepresentation, Long idUser, Integer nbPlace) {
		AppUser  user = userRepository.getOne(idUser);
		Representation representation = representationRepository.getOne(idRepresentation);
		reservation.setRepresentation(representation);
		reservation.setUser(user);
		reservation.setNbPlace(nbPlace);
		reservationRepository.save(reservation);
		return reservation;
	}








	
	


	
	

	


	


	

}
