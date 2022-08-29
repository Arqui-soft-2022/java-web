package web.qrcode.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api")
@Slf4j
public class QrController {
	@Value("${URI}") String URI;
	
	@GetMapping("/listar/{user}")
	public String listar(@PathVariable String user, Model model) {
		MultiValueMap<String, Integer> body = new LinkedMultiValueMap<String, Integer>();     
		body.add("user", Integer.parseInt(user));

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<?> request = new HttpEntity<Object>(body);
        
		try {
            ResponseEntity<String> response = restTemplate
            .exchange(URI + "/code/historial/", HttpMethod.POST, request, String.class);	
            //log.info(""+response.getBody());
            if(response.getStatusCode().value() == 200){
            	System.out.print(response.getBody());
                return "listar";
            }
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }		
	    return "listar";
	}
}
