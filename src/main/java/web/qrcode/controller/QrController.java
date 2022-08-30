package web.qrcode.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import web.qrcode.model.QrCode;
import web.qrcode.model.QrCodeDto;
import web.qrcode.model.Type;
import web.qrcode.model.UserDto;
import web.qrcode.model.Usuario;

import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;


@Controller
@RequestMapping("/api")
@Slf4j
public class QrController {
	@Value("${URI}") String URI;
	
	@GetMapping("/listar/{user}")
	public String listar(@PathVariable String user, Model model) 
	{
		UserDto usuario = new UserDto(Integer.parseInt(user));
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<?> request = new HttpEntity<>(usuario);
		System.out.println(""+ request.getBody() + "***");
        
		try {
            ResponseEntity<String> response = restTemplate
            .exchange(URI + "/code/historial/", HttpMethod.POST, request, String.class);	
            if(response.getStatusCode().value() == 200){
            	StringBuilder aux = new StringBuilder(response.getBody());
            	for(int i = 0; i<=31; i++)
            		aux.deleteCharAt(0);
        	    String json = "{" + aux.toString();
        	    
        	    JSONObject myjson = new JSONObject(json);
        	    JSONArray array = myjson.getJSONArray("codes");
        	    
        	    int size = array.length();
        	    List<QrCodeDto> codes = new ArrayList<QrCodeDto>();
        	    ArrayList<JSONObject> arrays = new ArrayList<JSONObject>();
        	    for (int i = 0; i < size; i++) {
        	        JSONObject x = array.getJSONObject(i);
        	        codes.add(new QrCodeDto(x.getInt("id_code"), x.getString("url"),x.getString("url_code"), x.getInt("type"), x.getString("date")));        	        
        	    }
        	    
        	    model.addAttribute("codes", codes);
            	
            	return "listar";
            }
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }		
	    return "login";
	}
}
