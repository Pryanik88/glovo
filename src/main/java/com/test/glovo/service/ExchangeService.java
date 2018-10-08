package com.test.glovo.service;

import com.test.glovo.connector.ExchangeConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExchangeService {

    private final ExchangeConnector connector;

    @Autowired
    public ExchangeService(ExchangeConnector connector) {
        this.connector = connector;
    }

    public List<String> getProducts() throws IOException {

        List<String> btxList = connector.getExchanges("BTX");
        List<String> bnbList = connector.getExchanges("BNB");
        List<String> bfxList = connector.getExchanges("BFX");

        return btxList.stream().filter(bnbList::contains).filter(bfxList::contains).collect(Collectors.toList());

    }

    public Map<String,String> getPrices(String productId) throws IOException {
        Map<String, String> pricesMap = new HashMap<>();
        pricesMap.put("BTX", connector.getPrice("BTX", productId));
        pricesMap.put("BFX", connector.getPrice("BFX", productId));
        pricesMap.put("BNB", connector.getPrice("BNB", productId));
        return pricesMap;
    }
}
