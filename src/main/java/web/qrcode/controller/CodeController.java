package web.qrcode.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import web.qrcode.model.GenerateQR;
import web.qrcode.model.QrCode;


@Controller
@RequestMapping("/code")
@Slf4j
public class CodeController {
    @Value("${URI}") String URI;

    @PostMapping("/generate")
    public String generateCode(@ModelAttribute("generate") GenerateQR generateQR, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<GenerateQR> request = new HttpEntity<>(generateQR);
        try {
            ResponseEntity<String> response = restTemplate
                    .exchange(URI + "/code", HttpMethod.POST, request, String.class);
            if (response.getStatusCode().value() == 200) {
                log.info(""+response.getBody());
                JSONArray array = new JSONArray("["+response.getBody()+"]");
                String dataQR= array.getJSONObject(0).getJSONObject("qr_code").get("url_code").toString();
                model.addAttribute("base",dataQR);
                return "generate";
            }
        }catch (Exception e) {
            model.addAttribute("mensaje","Datos erróneos, por favor vuelva a intentarlo");
            return "generate";
        }
        return "generate";
    }

    @GetMapping()
    public String generate() {
        return "generate";
    }
}
