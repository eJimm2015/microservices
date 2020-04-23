package fr.dauphine.microservice.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthcheck")
public class RestApi {
    @GetMapping
    public String healthcheck(){
        return "Knock knock Neo, The Matrix has you ...";
    }
}
