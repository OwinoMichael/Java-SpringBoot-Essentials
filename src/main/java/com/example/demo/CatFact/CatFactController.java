package com.example.demo.CatFact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catfact")
public class CatFactController {

    @Autowired
    private CatFactQueryHandler catFactQueryHandler;

    @GetMapping
    public ResponseEntity<CatFactDTO> getCatFact(){
        return catFactQueryHandler.execute(null);
    }
}
