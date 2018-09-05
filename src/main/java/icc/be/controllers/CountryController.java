package icc.be.controllers;

import java.io.IOException;

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

import icc.be.FormVerification.FormVerificationCountry;
import icc.be.FormVerification.FormVerificationType;
import icc.be.dao.CountryRepository;
import icc.be.entites.Country;
import icc.be.entites.Type;

@Controller
@RequestMapping(value="/counrty")
public class CountryController {
	
	
	@Autowired
	private CountryRepository countryRepository;
	
	// Formulaire GET Pour Ajouter type artiste
	@RequestMapping(value="/form",method=RequestMethod.GET)
	public String formCountry(Model model){
		
		model.addAttribute("country", new Country());
		
		return "country/form";
		
	}
	
	
	//Formulaire POST
	// si n'est pas valide renvoit le formulaire GET
	@RequestMapping(value="/saveCountry",method=RequestMethod.POST)
	public String save(@Valid Country country,
			BindingResult bindingResult ,@Valid FormVerificationCountry verification)
					throws IllegalStateException, IOException{
		
		if(bindingResult.hasErrors())
			return "country/form";
			//Verifier si le nom du pays  existe deja ou pas 
			Country pays = countryRepository.findByName(verification.getName());
			
				// si existe affiche page TypeExiste
				//renvoit la vue Type existe
				if(pays!=null){
					System.out.println("_-_-_-_-_-_-*****");
					return "TypeExist";
			
				}
		//Si non fait un save
		countryRepository.save(country);
		// puis renvoit la page confirmation de l'ajout
		return "country/Confirmation";		
	}
	
	
	
	@RequestMapping(value="/index")
	public String Index(Model model,
			@RequestParam(name="page", defaultValue="0") int p,
			@RequestParam(name="motcle", defaultValue="") String mc){
		
		Page<Country> pageCoutry = countryRepository.ChercherPaysParNom("%"+mc+"%", new PageRequest(p, 10));
		
		int pagesCount=pageCoutry.getTotalPages();
		// creer un tableau pages qui est egale au nbre de pageCount
		int [] pages=new int[pagesCount];
		//une boucle qui affiche le nb de page 
		for(int i=0;i<pagesCount;i++) pages[i]=i;
		model.addAttribute("pages", pages);
		model.addAttribute("pageCountry", pageCoutry);
		model.addAttribute("pageCourante",p);
		model.addAttribute("motcle",mc);



		
		return "country/country";
	}

	
	//Supprimer le pays par ID
	@RequestMapping(value="/supprimer")
	public String supprimer(Long id){
		
		countryRepository.deleteById(id);
		return "redirect:index";
	}
	
	
	//Permet de revoyer le Formulaire editCountry pour Editer un pays 
	@RequestMapping(value="/edit")
	public String edit(Long id,Model model){
		Country country=countryRepository.getOne(id);
		model.addAttribute("country", country);
		return "country/editCountry";
	}
	
	//Post UpDate du pays 
	@RequestMapping(value="/upDate",method=RequestMethod.POST)
	public String update(Country country,BindingResult bindingResult,
			@Valid FormVerificationCountry verification){
		
		if(bindingResult.hasErrors())
			
			return "country/editCountry";
		
		//Verifier si le nom du pays  existe deja ou pas 
		Country pays = countryRepository.findByName(verification.getName());
		
			// si existe affiche page TypeExiste
			//renvoit la vue Type existe
			if(pays!=null){
				
				return "TypeExist";
		
			}
		
		country.setId(country.getId());
		
		countryRepository.save(country);
		
		
	
		
		return "country/Confirmation";	
	}
}
