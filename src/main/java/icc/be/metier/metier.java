package icc.be.metier;

import icc.be.entites.Location;
import icc.be.entites.Representation;
import icc.be.entites.Reservation;
import icc.be.entites.Show;

public interface metier {
	public Location addLocalityToLocation(Location location, Long idLocality);
	
	public Show addLocationToShow(Show show, Long idlocation);
	
	public Representation addShowToRepresentation(Representation representation, Long idShow,Long idLocation);
	
	public Reservation reservation(Reservation reservation, Long idShow,Long idUser, Integer nbPlace);



}
