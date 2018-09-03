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

import icc.be.FormVerification.FormVerificationArtiste;
import icc.be.FormVerification.FormVerificationType;
import icc.be.dao.ArtisteRepository;
import icc.be.dao.TypeRepository;
import icc.be.entites.Artiste;
import icc.be.entites.Type;

@Controller
@RequestMapping(value = "/artiste")
public class ArtisteController {

	@Autowired
	private ArtisteRepository artisteRepository;

	// Formulaire GET Pour Ajouter un artiste
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String formArtist(Model model) {

		model.addAttribute("Artiste", new Artiste());
		System.out.println("******************");
		return "artiste/form";

	}

	// POST ARTSITE
	@RequestMapping(value = "/saveArtiste", method = RequestMethod.POST)
	public String save(@Valid Artiste artiste, BindingResult bindingResult,
			@Valid FormVerificationArtiste verification) throws IllegalStateException, IOException {
		if (bindingResult.hasErrors())
			return "artiste/form";
		// Verifier si le nom et prénom existe deja ou pas

		Artiste nom = artisteRepository.findByNom(verification.getNom());
		Artiste prenom = artisteRepository.findByPrenom(verification.getPrenom());

		// renvoit la vue Artiste existe
		// Si le nom et prénom existent dans la BD
		if (nom != null & prenom != null) {
			System.out.println("_-_-_-_-_-_-*****");
			return "ArtisteExist";

		}

		return "artiste/Confirmation";
	}
	
	
	// pour afficher tout les type d'artiste 
	// Faire une recherche par Intitulé du type 
	
	@RequestMapping(value="/index")
	public String Index(Model model,
			@RequestParam(name="page", defaultValue="0") int p,
			@RequestParam(name="motcle", defaultValue="") String mc){
	
		
		Page<Artiste> pageArtiste=artisteRepository.chercherArtiste("%"+mc+"%", new PageRequest(p, 10));
		int pagesCount=pageArtiste.getTotalPages();
		// creer un tableau pages qui est egale au nbre de pageCount
		int [] pages=new int[pagesCount];
		//une boucle qui affiche le nb de page 
		for(int i=0;i<pagesCount;i++) pages[i]=i;
		model.addAttribute("pages", pages);
		model.addAttribute("pageArtiste", pageArtiste);
		model.addAttribute("pageCourante",p);
		model.addAttribute("motcle",mc);



		
		return "artiste/artiste";
	}

	@RequestMapping(value="/supprimer")
	public String supprimer(Long id){
		artisteRepository.deleteById(id);
		return "redirect:index";
	}
	//Permet de revoyer le Formulaire editType pour Editer un type d'artiste 
	@RequestMapping(value="/edit")
	public String edit(Long id,Model model){
		Artiste artiste=artisteRepository.getOne(id);
		model.addAttribute("artiste", artiste);
		return "artiste/editArtiste";
	}
	
	
	@RequestMapping(value="/upDate",method=RequestMethod.POST)
	public String update(Artiste artiste,BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return "artiste/editArtiste";
		System.out.println(artiste);
		artiste.setId(artiste.getId());
		artisteRepository.save(artiste);
		System.out.println("--------------");
		System.out.println(artiste);
		
		return "artiste/Confirmation";
	}
}
