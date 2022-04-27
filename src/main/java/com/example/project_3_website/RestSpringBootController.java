package com.example.project_3_website;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path="/heroApi")
public class RestSpringBootController {

    @GetMapping
    public ResponseEntity<?> getHero() {

        try {
            HttpRequest request = HttpRequest
                    .get("https://www.superheroapi.com/api.php/109324175078057")
                    .connectTimeout(120000);
            String res = request.body();
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/restHero/{id}")
    public ResponseEntity<?> getHeroes(@PathVariable String id) {

        try {
            String uri="https://www.superheroapi.com/api.php/109324175078057/"+id+"/powerstats";
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
