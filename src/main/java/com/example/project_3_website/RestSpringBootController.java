package com.example.project_3_website;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path="/heroApi")
public class RestSpringBootController {

    @GetMapping(value = "/getHeroesById")
    public List<Object> getHeroes(@RequestParam int id){
        String url = "https://superheroapi.com/api.php/109324175078057/"+id;
        RestTemplate restTemplate = new RestTemplate();
        Object[] heroes = restTemplate.getForObject(url, Object[].class);
        return Arrays.asList(heroes);
    }

}
