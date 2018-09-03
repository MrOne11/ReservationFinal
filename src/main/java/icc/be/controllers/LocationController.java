package icc.be.controllers;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import icc.be.FormVerification.FormVerificationLocation;
import icc.be.dao.LocalityRepository;
import icc.be.dao.LocationRepository;
import icc.be.entites.Locality;
import icc.be.entites.Location;
import icc.be.metier.metier;

@Controller
@RequestMapping(value="/location")
public class LocationController {
	
	@Autowired
	private LocalityRepository localityRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private metier metier;
	
	//GET Form Location 
	@RequestMapping(value="/form",method=RequestMethod.GET)
	public String formLocation(Model model){
		
		//Recuperer la liste des locality 
		List<Locality> localities = localityRepository.findAll();
		model.addAttribute("location", new Location());
		model.addAttribute("Locality",localities);
		System.out.println(localities.toString());
		return "location/form";
		
		
	}
	
	
	
	//
	//POST FORMULAIRE 
	@RequestMapping(value="/saveLocation",method=RequestMethod.POST)
	public String save(@Valid Location location,@Valid FormVerificationLocation verification,
			BindingResult bindingResult,
			@RequestParam(value="locality")Long idLocality )
					throws IllegalStateException, IOException{
		if(bindingResult.hasErrors())
			return "location/form";
		Location locDesignation = locationRepository.findByDesignation(verification.getDesignation());
		Location locSlug = locationRepository.findBySlug(verification.getSlug());
		
		//ce type existe déja 
		if(locDesignation!=null & locSlug!=null){
			System.out.println("_-_-_-_-_-_-*****");
			return "ArtisteExist";
	
		}
		
		System.out.println("*****OO*****");
		//faire appel à la méthode addTypeTo
		//faire l'association de location avec locality
		metier.addLocalityToLocation(location, idLocality);
		
		System.out.println("**********");
		return "location/Confirmation";		
	}
	
	//Pour Une page de location avec une recherche par Designation
	@RequestMapping(value="/index")
	public String Index(Model model,
			@RequestParam(name="page", defaultValue="0") int p,
			@RequestParam(name="motcle", defaultValue="") String mc){
		Page<Location> pageLocation=locationRepository.rechercheParDesignation("%"+mc+"%", new PageRequest(p, 10));
		int pagesCount=pageLocation.getTotalPages();
		// creer un tableau pages qui est egale au nbre de pageCount
		int [] pages=new int[pagesCount];
		//une boucle qui affiche le nb de page 
		for(int i=0;i<pagesCount;i++) pages[i]=i;
		model.addAttribute("pages", pages);
		model.addAttribute("pageLocation", pageLocation);
		model.addAttribute("pageCourante",p);
		model.addAttribute("motcle",mc);

		
		return "location/location";
	}
	
	
	
	//Supprimer location par id
	@RequestMapping(value="/supprimer")
	public String supprimer(Long id){
		locationRepository.deleteById(id);
		return "redirect:index";
	}

	//Revoit le formulaire pour editer
	@RequestMapping(value="/edit")
	public String edit(Long id,Model model){
		//récuperer l'objet location par son id
		Location location = locationRepository.getOne(id);
		//recuperer liste de locality
		List<Locality> localities = localityRepository.findAll();
	
		model.addAttribute("location", location);
		model.addAttribute("ListesLocality",localities);
		System.out.println("******************");

		return "location/editLocation";
	}
	
	
	//POST
	//upDateEtudiant meme méthode que saveCours
	@RequestMapping(value="/upDate",method=RequestMethod.POST)
	//
	public String update(@Valid Location location,@Valid FormVerificationLocation verification,BindingResult bindingResult,
			@RequestParam(value="locality")Long idLocality )
					throws IllegalStateException, IOException{
		if(bindingResult.hasErrors())
			return "location/form";
		Location locDesignation = locationRepository.findByDesignation(verification.getDesignation());
		Location locSlug = locationRepository.findBySlug(verification.getSlug());
		
		//ce type existe déja 
		if(locDesignation!=null & locSlug!=null){
			System.out.println("_-_-_-_-_-_-*****");
			return "ArtisteExist";
	
		}
		
		System.out.println("*****OO*****");
		//faire appel à la méthode addTypeTo
		//faire l'association de location avec locality
		metier.addLocalityToLocation(location, idLocality);
		
		System.out.println("**********");
		return "location/Confirmation";
	}
}
