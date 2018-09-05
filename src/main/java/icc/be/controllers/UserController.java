package icc.be.controllers;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import icc.be.FormVerification.RegisterForm;
import icc.be.entites.AppUser;
import icc.be.metier.AccountService;

@Controller
public class UserController {
	@Autowired
	private AccountService accountService;
	@RequestMapping(value="/formUser",method=RequestMethod.GET)
	public String formAddNewUser(Model model){
		
		model.addAttribute("appUser",  new AppUser());
		System.out.println();
		return "user/form";
		
	}
	@RequestMapping(value="/saveUser", method=RequestMethod.POST)
	public String saveEtudiant(Model model, @Valid AppUser user, 
			BindingResult bindingResult,@Valid RegisterForm userForm){
		if(bindingResult.hasErrors())
			return "etudiant/formEtudiant";
		AppUser user1 = accountService.findUserByUsername(userForm.getUsername());
		//cet utilisateur existe déja 
		if(user1!=null){
			return "userExist";
			}
		user.setRole(AppUser.AppRole.USER);
		accountService.saveUser(user);
		
		
		return "user/Confirmation";
	}

}
