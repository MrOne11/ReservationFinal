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

import icc.be.FormVerification.FormVerificationLocality;
import icc.be.dao.LocalityRepository;
import icc.be.entites.Locality;
import icc.be.entites.Type;

@Controller
@RequestMapping(value="/locality")
public class LocalityController {
	
	@Autowired
	private LocalityRepository localityRepository;
	
	
	// Formulaire GET Pour Ajouter type artiste
		@RequestMapping(value="/form",method=RequestMethod.GET)
		public String formLocality(Model model){
			
			model.addAttribute("locality", new Locality());
			System.out.println("******************");
			return "locality/form";
			
		}
		
		
		//Formulaire POST
		// si n'est pas valide renvoit le formulaire GET
		
		@RequestMapping(value="/saveLocality",method=RequestMethod.POST)
		public String save(@Valid Locality locality,
				BindingResult bindingResult ,@Valid FormVerificationLocality verification) throws IllegalStateException, IOException{
			if(bindingResult.hasErrors())
				return "locality/form";
			//Verifier si l'intitulé existe deja ou pas 
				Locality loc = localityRepository.findByPostalCode(verification.getPostalCode());
				//Type type = typeRepositoy.findByIntitule((verification.getIntitule()));
					//ce type existe déja 
					//renvoit la vue Type existe
					if(loc!=null){
						System.out.println("_-_-_-_-_-_-*****");
						return "TypeExist";
				
					}
			System.out.println("*****OO*****");
			localityRepository.save(locality);
			;
			System.out.println("**********");
			return "locality/Confirmation";		
		}
		
		
		// pour afficher tout les locality 
		// Faire une recherche par name de locality 
		
		@RequestMapping(value="/index")
		public String Index(Model model,
				@RequestParam(name="page", defaultValue="0") int p,
				@RequestParam(name="motcle", defaultValue="") String mc){
		
			Page<Locality> pageLocality=localityRepository.chercherLocality("%"+mc+"%", new PageRequest(p, 10));
			int pagesCount=pageLocality.getTotalPages();
			// creer un tableau pages qui est egale au nbre de pageCount
			int [] pages=new int[pagesCount];
			//une boucle qui affiche le nb de page 
			for(int i=0;i<pagesCount;i++) pages[i]=i;
			model.addAttribute("pages", pages);
			model.addAttribute("pageType", pageLocality);
			model.addAttribute("pageCourante",p);
			model.addAttribute("motcle",mc);



			
			return "locality/locality";
		}
		
		// Supprimer Locality par id 
		// cette methode sera appeler si on click sur le bouton supprimer 
		@RequestMapping(value="/supprimer")
		public String supprimer(Long id){
			localityRepository.deleteById(id);
			return "redirect:index";
		}
		
		//Permet de revoyer le Formulaire editType pour Editer une locality 
		@RequestMapping(value="/edit")
		public String edit(Long id,Model model){
			Locality locality=localityRepository.getOne(id);
			model.addAttribute("locality", locality);
			return "locality/editLocality";
		}
		
		
		@RequestMapping(value="/upDate",method=RequestMethod.POST)
		public String update(Locality locality,BindingResult bindingResult){
			if(bindingResult.hasErrors())
				return "locality/editLocality";
			System.out.println(locality);
			locality.setId(locality.getId());
			localityRepository.save(locality);
			
			return "locality/Confirmation";
		}

}
