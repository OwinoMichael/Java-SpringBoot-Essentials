package com.example.demo.CatFact;

import com.example.demo.CatFactEntity.CatFactEntity;
import com.example.demo.CatFactEntity.CatFactRepository;
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
    private final CatFactRepository catFactRepository;

    public CatFactQueryHandler(RestTemplate restTemplate, CatFactRepository catFactRepository) {
        this.restTemplate = restTemplate;
        this.catFactRepository = catFactRepository;
    }

    @Override
    public ResponseEntity<CatFactDTO> execute(Void input) {
        CatFact catFact = getCatFact();
        CatFactDTO catFactDTO = new CatFactDTO(catFact.getFact());
        return ResponseEntity.ok(catFactDTO);

    }

    private CatFact getCatFact() {
        try {
            CatFact catFact = restTemplate.getForObject("https://catfact.ninja/fact", CatFact.class);
            catFactRepository.save(new CatFactEntity(catFact));
            return catFact;
        } catch (Exception e) {
            e.printStackTrace(); // Add this to see the full stack trace
            throw new ExternalCatFactDownException(HttpStatus.SERVICE_UNAVAILABLE,
                    new SimpleResponse("The external API is down"));
        }
    }
}
