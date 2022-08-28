package web.qrcode.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;
import web.qrcode.model.Usuario;

@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    @Value("${URI}") String URI;

    @GetMapping("/loginView")
    public String loginView(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("usuario") Usuario usuario, Model model){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Usuario> request = new HttpEntity<>(usuario);
        try {
            ResponseEntity<String> response = restTemplate
            .exchange(URI+"/auth/login", HttpMethod.POST, request, String.class);
            log.info(""+response.getBody());
            if(response.getStatusCode().value() == 200){
                return "login";
            }
        } catch (Exception e) {
            model.addAttribute("mensaje","Usuario o contraseña incorrecta");
        return "login";

        }
        return "redirect:/auth/loginView";
    }



}
