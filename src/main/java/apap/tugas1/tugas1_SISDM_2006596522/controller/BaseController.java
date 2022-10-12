package apap.tugas1.tugas1_SISDM_2006596522.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {

    @GetMapping("/")
    private String Beranda() {
        return "home";
    }
}
