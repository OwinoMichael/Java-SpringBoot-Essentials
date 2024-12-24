package com.example.demo.CatFact;

public class CatFact {
    private String Fact;
    private int length;

    public CatFact() {
    }

    public CatFact(String fact, int length) {
        Fact = fact;
        this.length = length;
    }

    public String getFact() {
        return Fact;
    }

    public void setFact(String fact) {
        Fact = fact;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
