package com.test.glovo.service;

import com.test.glovo.connector.ExchangeConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class ExchangeService {

    private final ExchangeConnector connector;
    private final ArrayList<String> stocks = new ArrayList<String>(){{
        add("BFX");
        add("BNB");
        add("BTX");
    }};

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

    public Map<String,BigDecimal> getPrices(String productId) throws IOException {
        Map<String, BigDecimal> pricesMap = new TreeMap<>();
        stocks.forEach(e->pricesMap.put(e, connector.getPrice(e, productId)));
        return pricesMap;
    }
}
