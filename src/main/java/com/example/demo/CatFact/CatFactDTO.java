package com.example.demo.CatFact;

import lombok.Data;

@Data
public class CatFactDTO {
    private String catFact;

    public CatFactDTO() {
    }

    public CatFactDTO(String catFact) {
        this.catFact = catFact;
    }

    public String getCatFact() {
        return catFact;
    }

    public void setCatFact(String catFact) {
        this.catFact = catFact;
    }
}
