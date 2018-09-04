package icc.be.metier;

import icc.be.entites.Location;
import icc.be.entites.Show;

public interface metier {
	public Location addLocalityToLocation(Location location, Long idLocality);
	
	public Show addLocationToShow(Show show, Long idlocation);


}
