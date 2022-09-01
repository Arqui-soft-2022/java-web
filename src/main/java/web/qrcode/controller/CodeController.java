package web.qrcode.controller;

import org.json.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import web.qrcode.model.dto.GenerateQRDto;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/code")
@Slf4j
public class CodeController {
    @Value("${URI}") String URI;

    @GetMapping()
    public String generate() {
        return "generate";
    }

    @PostMapping("/generate")
    public String generateCode(@RequestParam(value = "url") String url, Model model, HttpSession session) {
        RestTemplate restTemplate = new RestTemplate();
        GenerateQRDto generateQR = new GenerateQRDto();
        generateQR.setUrl(url);
        if(session!=null && session.getAttribute("idUsuario")!=null) {
            generateQR.setUser(Integer.parseInt(session.getAttribute("idUsuario").toString()));
        }else{
            return "redirect:/auth/loginView";
        }
        HttpEntity<GenerateQRDto> request = new HttpEntity<>(generateQR);
        try {
            ResponseEntity<String> response = restTemplate
                    .exchange(URI + "/code", HttpMethod.POST, request, String.class);
            if (response.getStatusCode().value() == 200) {
                sendBase64(response.getBody(),model);
                return "generate";
            }
        }catch (Exception e) {
            model.addAttribute("mensaje","Datos erróneos, por favor vuelva a intentarlo");
            return "generate";
        }
        return "redirect:/code";
    }

    private void sendBase64(String json, Model model){
        JSONArray array = new JSONArray("["+json+"]");
        String dataQR= array.getJSONObject(0).getJSONObject("qr_code").get("url_code").toString();
        model.addAttribute("imageBase",dataQR);
    }
}
