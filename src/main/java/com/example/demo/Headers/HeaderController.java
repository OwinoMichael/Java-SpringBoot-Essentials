package com.example.demo.Headers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderController {

    @GetMapping("/header")
    public String getRegionalRequest(@RequestHeader(required = false, defaultValue = "USA") String region){
        if(region.equals("USA")) return "BALD EAGLE FREEDOM";

        if(region.equals("CAN")) return "I bleed maple syrup";

        return "Country not supported :(";
    }
}
