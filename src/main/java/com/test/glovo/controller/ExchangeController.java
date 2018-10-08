package com.test.glovo.controller;

import com.test.glovo.dto.ExchangeDTO;
import com.test.glovo.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ExchangeController {

    private final ExchangeService service;

    @Autowired
    public ExchangeController(ExchangeService service) {
        this.service = service;
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8088")
    public List<String> getProducts() throws IOException {
        return service.getProducts();
    }

    @GetMapping("/{productId}/prices")
    public Map<String,String> getPrice(@PathVariable String productId) throws IOException {
        return service.getPrices(productId);
    }
}
