package com.example.demo.CatFactEntity;

import com.example.demo.CatFact.CatFact;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

@Entity
@Table(name = "cat_facts")
public class CatFactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "catfactjson", columnDefinition = "json")
    private String catFactJSON;

    public CatFactEntity() {
    }

    public CatFactEntity(int id, String catFactJSON) {
        this.id = id;
        this.catFactJSON = catFactJSON;
    }

    public CatFactEntity(CatFact catFact) {
        this.catFactJSON = convertToJSON(catFact);
    }



    //serialization
    private String convertToJSON(CatFact catFact) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(catFact);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Json Parse Error");
        }
    }

    //Deserialization
    public CatFact converToCatFact(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(catFactJSON, CatFact.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON Parse error");
        }


    }
}



