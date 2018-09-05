package icc.be.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import icc.be.FormVerification.FormVerificationArtiste;
import icc.be.FormVerification.FormVerificationRepresentation;
import icc.be.FormVerification.FormVerificationShow;
import icc.be.dao.LocationRepository;
import icc.be.dao.RepresentationRepository;
import icc.be.dao.ShowRepository;
import icc.be.dao.UserRepository;
import icc.be.entites.AppUser;
import icc.be.entites.Artiste;
import icc.be.entites.Location;
import icc.be.entites.Representation;
import icc.be.entites.Show;
import icc.be.metier.metier;

@Controller
@RequestMapping(value = "/representation")
public class RepresentationController {
	
	@Autowired
	private RepresentationRepository representationRepository;
	@Autowired
	private ShowRepository showRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private metier metier;
	@Autowired
	private UserRepository user;
	
	

	
	// Formulaire GET Pour Ajouter une représentation
		@RequestMapping(value = "/form", method = RequestMethod.GET)
		public String formRepresentation(Model model) {

					//Recuperer la liste de show
					List<Show> shows = showRepository.findAll();
					//Recuperer La liste de location
					List<Location> location = locationRepository.findAll();
					model.addAttribute("representation", new Representation());
					model.addAttribute("show",shows);
					model.addAttribute("location",location);
			return "representation/form";

		}
	
	//POST FORMULAIRE 

	@RequestMapping(value = "/saveRepr", method = RequestMethod.POST)
	public String save(@Valid Representation representation, BindingResult bindingResult,
			@Valid FormVerificationArtiste verification,
			@RequestParam(value="location")Long idLocation,
			@RequestParam(value="show")Long idShow) throws IllegalStateException, IOException {
		if (bindingResult.hasErrors())
			return "representation/form";
		// Verifier si le nom et prénom existe deja ou pas

		

		
		metier.addShowToRepresentation(representation, idShow, idLocation);
		return "representation/Confirmation";
	}
	
	
	//Pour Une page de Représentation avec une recherche par Title du show
	@RequestMapping(value="/index")
	public String Index(Model model,
			@RequestParam(name="page", defaultValue="0") int p,
			@RequestParam(name="motcle", defaultValue="") String mc){
		Page<Representation> pageRepresentation=representationRepository.chercherParTitle("%"+mc+"%", new PageRequest(p, 10));
		int pagesCount=pageRepresentation.getTotalPages();
		// creer un tableau pages qui est egale au nbre de pageCount
		int [] pages=new int[pagesCount];
		//une boucle qui affiche le nb de page 
		for(int i=0;i<pagesCount;i++) pages[i]=i;
		model.addAttribute("pages", pages);
		model.addAttribute("pageRep", pageRepresentation);
		model.addAttribute("pageCourante",p);
		model.addAttribute("motcle",mc);

	
		return "representation/representation";
	}

	
	//Supprimer Representation par id
	@RequestMapping(value="/supprimer")
	public String supprimer(Long id){
		representationRepository.deleteById(id);
		return "redirect:index";
	}
	
	
	
	
	//Revoit le formulaire pour editer
	@RequestMapping(value="/edit")
	public String edit(Long id,Model model){
		//récuperer l'objet Rep par son id
		Representation representation = representationRepository.getOne(id);

	
		//recuperer liste de location
		List<Location> location = locationRepository.findAll();
		
		//recuperer liste de show
		List<Show> show = showRepository.findAll();
		
		model.addAttribute("representation", representation);
		model.addAttribute("show", show);
		model.addAttribute("location",location);
		System.out.println("******************");

		return "representation/editRepresentation";
	}
	
	@RequestMapping(value = "/upDate", method = RequestMethod.POST)
	public String upDate(@Valid Representation representation, BindingResult bindingResult,
			@Valid FormVerificationArtiste verification,
			@RequestParam(value="location")Long idLocation,
			@RequestParam(value="show")Long idShow) throws IllegalStateException, IOException {
		if (bindingResult.hasErrors())
			return "representation/form";
		// Verifier si le nom et prénom existe deja ou pas

		

		
		metier.addShowToRepresentation(representation, idShow, idLocation);
		return "representation/Confirmation";
	}
	

	

	

	
}
