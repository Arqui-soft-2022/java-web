package web.qrcode.controller;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;
import web.qrcode.model.Usuario;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    @Value("${URI}") String URI;

    @GetMapping("/loginView")
    public String loginView(){
        return "login";
    }

    @GetMapping("/registerView")
    public String registerView(){
        return "register";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("usuario") Usuario usuario, Model model, HttpSession session){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Usuario> request = new HttpEntity<>(usuario);
        try {
            ResponseEntity<String> response = restTemplate
            .exchange(URI+"/auth/login", HttpMethod.POST, request, String.class);
            JSONObject jsonObject1 = new JSONObject(response.getBody());
            Object usuarioJson = jsonObject1.getJSONObject("usuario");
            JSONObject jsonObject2 = new JSONObject(usuarioJson.toString());
            Integer idUsuario = jsonObject2.getInt("id_usuario");
            session.setAttribute("idUsuario", idUsuario);
            if(response.getStatusCode().value() == 200){
                return "redirect:/code";
            }
        } catch (Exception e) {
            model.addAttribute("mensaje","Usuario o contraseña incorrecta");
        return "login";

        }
        return "redirect:/auth/loginView";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("usuario") Usuario usuario, Model model){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Usuario> request = new HttpEntity<>(usuario);
        try {
            ResponseEntity<String> response = restTemplate
                    .exchange(URI + "/auth/register", HttpMethod.POST, request, String.class);
            if (response.getStatusCode().value() == 200) {
                //log.info(""+response.getBody());
                return "login";
            }
        }catch (Exception e) {
                model.addAttribute("mensaje","Datos erróneos, por favor vuelva a intentarlo");
                return "register";
            }
        return "redirect:/auth/loginView";
    }



}
