package icc.be.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


	@Controller
	public class SecurityController {
		@RequestMapping(value="/login")
		public String login(){
			return "login";
			
		}
		
		@RequestMapping(value="/")
		public String home(){
			return "redirect:/representation/index";
			
		}

		@RequestMapping(value="/403")
		public String accessRefuse(){
			return "403";
			
		}

		@RequestMapping(value="/index")
		public String index(){
			return "show/index";
			
		}
	
	}



