package icc.be.controllers;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import icc.be.FormVerification.FormVerificationArtiste;
import icc.be.dao.LocationRepository;
import icc.be.dao.RepresentationRepository;
import icc.be.dao.ReservationRepository;
import icc.be.dao.ShowRepository;
import icc.be.dao.UserRepository;
import icc.be.entites.AppUser;
import icc.be.entites.Location;
import icc.be.entites.Representation;
import icc.be.entites.Reservation;
import icc.be.entites.Show;
import icc.be.metier.metier;



@Controller
@RequestMapping(value = "/reservation")
public class ReservationController {
	
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private RepresentationRepository representationRepository;
	@Autowired 
	private ShowRepository showRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private UserRepository user;
	@Autowired
	private metier metier;
	
	@RequestMapping(value="/info",method = RequestMethod.GET)
	public String info(Model model,Long id){
		Representation representation = representationRepository.getOne(id);

		List<Show> shows = showRepository.findAll();
		List<Location> location = locationRepository.findAll();
		model.addAttribute("representation", representation);
		model.addAttribute("reservation", new Reservation());
		model.addAttribute("show",shows);
		model.addAttribute("location",location);

		/*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		AppUser customUser = (AppUser)authentication.getPrincipal();
		Long userId = customUser.getId();

		*/
		
		return "reservation/info";
	}
	
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String formReservation(Model model) {

				//Recuperer la liste de Representation
				List<Representation> representations = representationRepository.findAll();
				
				model.addAttribute("reservation", new Reservation());
				model.addAttribute("representation",representations);
			
		return "reservation/form";

	}
	
	@RequestMapping(value = "/saveReservation", method = RequestMethod.POST)
	public String save(@Valid Reservation reservation, BindingResult bindingResult,
			@Valid FormVerificationArtiste verification,
			@RequestParam(value="nbPlace")Integer nbPlace,
			@RequestParam(value="representation")Long idShow) throws IllegalStateException, IOException {
		if (bindingResult.hasErrors())
			return "representation/form";
		
		//recuprer ID du Utilisateur authentifié 
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		AppUser customUser = (AppUser)authentication.getPrincipal();
		Long idUser = customUser.getId();

		
		//apeler methode reservation pour faire l'association
		metier.reservation(reservation, idShow, idUser, nbPlace);
		return "reservation/Confirmation";
	}
	



}
