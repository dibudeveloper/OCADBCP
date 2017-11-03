package uni.ocad.bcp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import uni.ocad.bcp.service.BcpService;

@Controller
public class HomeController {
	@Autowired
	private BcpService bcpService;
	
	@GetMapping("/")
	public String getHomeView(){
		
		
		
		System.out.println(bcpService.gettestpago());
		bcpService.setProcesoFile();
		return "fronthomeview";
	}
}
