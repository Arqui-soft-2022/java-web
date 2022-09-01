package web.qrcode.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import web.qrcode.model.dto.QrCodeDto;
import web.qrcode.model.dto.UserDto;

import org.json.JSONArray;
import org.json.JSONObject;

@Controller
@RequestMapping("/api")
@Slf4j
public class QrController {
	@Value("${URI}") String URI;
	
	@GetMapping("/listar/")
	public String listar(Model model, HttpSession session) 
	{
		Integer user = Integer.parseInt(session.getAttribute("idUsuario").toString());
		HttpEntity<?> request = new HttpEntity<>(new UserDto(user));
		
		try {			
			ResponseEntity<String> response = new RestTemplate()
	        .exchange(URI + "/code/historial/", HttpMethod.POST, request, String.class);	
	        if(response.getStatusCode().value() == 200){
	        	StringBuilder aux = new StringBuilder(response.getBody()).delete(1, 32);
	        	List<QrCodeDto> codes = getCodes(aux.toString());
	        	model.addAttribute("codes", codes);
	            
	        	return "listar";
	        }
		} catch (Exception e) {
			//System.out.println(e.getMessage());
	    }
		
	    return "redirect:/auth/loginView";
	}
	
	/*
	 * Obtiene la lista de codigos de un json
	 */
	
	private List<QrCodeDto> getCodes(String json)
	{		
		List<QrCodeDto> codes = new ArrayList<>();
		JSONArray array = new JSONObject(json).getJSONArray("codes");
		
		for (int i = 0; i < array.length(); i++) {
	        JSONObject x = array.getJSONObject(i);
	        codes.add(new QrCodeDto(x.getInt("id_code"), x.getString("url"),x.getString("url_code"), x.getString("type"), x.getString("date")));        	        
	    }
		
		return codes;
	}
}
