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
import icc.be.FormVerification.FormVerificationShow;
import icc.be.dao.LocationRepository;
import icc.be.dao.ShowRepository;
import icc.be.entites.Locality;
import icc.be.entites.Location;
import icc.be.entites.Show;
import icc.be.metier.metier;

@Controller
@RequestMapping(value = "/show")
public class ShowController {
	
	@Autowired
	private ShowRepository showRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private metier metier;
	
	// Formulaire GET Pour Ajouter un Show
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String formShow(Model model) {

				//Recuperer la liste de location 
				List<Location> location = locationRepository.findAll();
				model.addAttribute("show", new Show());
				model.addAttribute("Location",location);
		return "show/form";

	}
	
	
	//POST FORMULAIRE 
	@RequestMapping(value="/saveShow",method=RequestMethod.POST)
	public String save(@Valid Show show,@Valid FormVerificationShow verification,
			BindingResult bindingResult,
			@RequestParam(value="location")Long idLocation)
					throws IllegalStateException, IOException{
		if(bindingResult.hasErrors())
			
			return "show/form";
		
		//verifier si le titre existe dans la BD
		Show showTitle = showRepository.findByTitle(verification.getTitle());		
		//ce type existe déja 
		if(showTitle!=null){
			System.out.println("_-_-_-_-_-_-*****");
			return "ArtisteExist";
	
		}
		System.out.println(idLocation);

				//faire appel à la méthode add
				//pour faire l'association
		metier.addLocationToShow(show, idLocation);
		
		System.out.println("_-_-_-_COOOONFIIIIIRMM-_-_-*****");
		return "show/Confirmation";		
	}
	
	
	//Pour Une page de location avec une recherche par Designation
	@RequestMapping(value="/index")
	public String Index(Model model,
			@RequestParam(name="page", defaultValue="0") int p,
			@RequestParam(name="motcle", defaultValue="") String mc){
		Page<Show> pageShow=showRepository.chercherShowParTitle("%"+mc+"%", new PageRequest(p, 10));
		int pagesCount=pageShow.getTotalPages();
		// creer un tableau pages qui est egale au nbre de pageCount
		int [] pages=new int[pagesCount];
		//une boucle qui affiche le nb de page 
		for(int i=0;i<pagesCount;i++) pages[i]=i;
		model.addAttribute("pages", pages);
		model.addAttribute("pageShow", pageShow);
		model.addAttribute("pageCourante",p);
		model.addAttribute("motcle",mc);

		
		return "show/show";
	}

	
		//Supprimer Show par id
		@RequestMapping(value="/supprimer")
		public String supprimer(Long id){
			showRepository.deleteById(id);
			return "redirect:index";
		}
		
		
		
		//Revoit le formulaire pour editer
		@RequestMapping(value="/edit")
		public String edit(Long id,Model model){
			//récuperer l'objet Show par son id
			Show show = showRepository.getOne(id);
		
			//recuperer liste de location
			List<Location> location = locationRepository.findAll();
			
		
			model.addAttribute("show", show);
			model.addAttribute("ListesLocation",location);
			System.out.println("******************");

			return "show/editShow";
		}
		
		//POST FORMULAIRE 
		@RequestMapping(value="/upDate",method=RequestMethod.POST)
		public String upDate(@Valid Show show,@Valid FormVerificationShow verification,
				BindingResult bindingResult,
				@RequestParam(value="location")Long idLocation)
						throws IllegalStateException, IOException{
			if(bindingResult.hasErrors())
				
				return "show/form";
			
			//verifier si le titre existe dans la BD
			Show showTitle = showRepository.findByTitle(verification.getTitle());		
			//ce type existe déja 
			if(showTitle!=null){
				System.out.println("_-_-_-_-_-_-*****");
				return "ArtisteExist";
		
			}
			System.out.println(idLocation);

					//faire appel à la méthode add
					//pour faire l'association
			metier.addLocationToShow(show, idLocation);
			
			System.out.println("_-_-_-_COOOONFIIIIIRMM-_-_-*****");
			return "show/Confirmation";		
		}
		
		
}
