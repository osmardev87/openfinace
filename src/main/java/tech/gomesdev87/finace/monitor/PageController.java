package tech.gomesdev87.finace.monitor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/monitor")
    public String monitorPage() {
        return "monitor"; // Procura o arquivo monitor.html em templates
    }
}