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
import icc.be.FormVerification.FormVerificationType;
import icc.be.dao.TypeRepository;
import icc.be.entites.Type;


@Controller
@RequestMapping(value="/type")
public class TypeController {
	
	@Autowired
	private TypeRepository typeRepositoy;
	
	
	// Formulaire GET Pour Ajouter type artiste
	@RequestMapping(value="/form",method=RequestMethod.GET)
	public String formUser(Model model){
		
		model.addAttribute("typeArtiste", new Type());
		System.out.println("******************");
		return "type/form";
		
	}
	
	
	//Formulaire POST
	// si n'est pas valide renvoit le formulaire GET
	
	@RequestMapping(value="/saveType",method=RequestMethod.POST)
	public String save(@Valid Type typeArtiste,
			BindingResult bindingResult ,@Valid FormVerificationType verification) throws IllegalStateException, IOException{
		if(bindingResult.hasErrors())
			return "type/form";
		//Verifier si l'intitulé existe deja ou pas 

			Type type = typeRepositoy.findByIntitule((verification.getIntitule()));
				//ce type existe déja 
				//renvoit la vue Type existe
				if(type!=null){
					System.out.println("_-_-_-_-_-_-*****");
					return "TypeExist";
			
				}
		System.out.println("*****OO*****");
		typeRepositoy.save(typeArtiste);
		System.out.println("**********");
		return "type/Confirmation";		
	}
	
	// pour afficher tout les type d'artiste 
	// Faire une recherche par Intitulé du type 
	
	@RequestMapping(value="/index")
	public String Index(Model model,
			@RequestParam(name="page", defaultValue="0") int p,
			@RequestParam(name="motcle", defaultValue="") String mc){
	
		
		Page<Type> pageType=typeRepositoy.chercherIntitule("%"+mc+"%", new PageRequest(p, 10));
		int pagesCount=pageType.getTotalPages();
		// creer un tableau pages qui est egale au nbre de pageCount
		int [] pages=new int[pagesCount];
		//une boucle qui affiche le nb de page 
		for(int i=0;i<pagesCount;i++) pages[i]=i;
		model.addAttribute("pages", pages);
		model.addAttribute("pageType", pageType);
		model.addAttribute("pageCourante",p);
		model.addAttribute("motcle",mc);



		
		return "type/typeArtistes";
	}
	
	@RequestMapping(value="/supprimer")
	public String supprimer(Long id){
		typeRepositoy.deleteById(id);
		return "redirect:index";
	}
	//Permet de revoyer le Formulaire editType pour Editer un type d'artiste 
	@RequestMapping(value="/edit")
	public String edit(Long id,Model model){
		Type type=typeRepositoy.getOne(id);
		model.addAttribute("type", type);
		return "type/editType";
	}
	
	
	@RequestMapping(value="/upDate",method=RequestMethod.POST)
	public String update(Type type,BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return "type/editType";
		System.out.println(type);
		type.setId(type.getId());
		typeRepositoy.save(type);
		System.out.println("--------------");
		System.out.println(type);
		
		return "type/Confirmation";
	}
	
}
