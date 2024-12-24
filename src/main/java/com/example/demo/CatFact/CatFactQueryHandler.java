package com.example.demo.CatFact;

import com.example.demo.Exceptions.ExternalCatFactDownException;
import com.example.demo.Exceptions.SimpleResponse;
import com.example.demo.Query;
import org.springframework.boot.web.embedded.netty.NettyWebServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.KeyStore;

@Service
public class CatFactQueryHandler implements Query <Void, CatFactDTO>{

    private final RestTemplate restTemplate;

    public CatFactQueryHandler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<CatFactDTO> execute(Void input) {
        try{
            CatFact catFact =  restTemplate.getForObject("https://catfact.ninja/fact", CatFact.class);
            CatFactDTO catFactDTO = new CatFactDTO(catFact.getFact());
            return ResponseEntity.ok(catFactDTO);
        } catch (Exception e) {
            throw new ExternalCatFactDownException(HttpStatus.SERVICE_UNAVAILABLE, new SimpleResponse("The external API is down"));
        }

    }
}
