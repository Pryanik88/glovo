package com.test.glovo.connector;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.glovo.dto.ExchangeDTO;
import com.test.glovo.dto.PriceDTO;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExchangeConnector {

    private String urlExchange = "https://api.moneeda.com/api/exchanges/STOCK/products";
    private String urlPrice = "https://api.moneeda.com/api/exchanges/STOCK/ticker?product=PRODUCTID";
    private final String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImRldkBnbG92b2FwcC5jb20iLCJpZCI6IjVhNTcyZGEyNTM4OWMzNzZiZWZlNjY1NCIsImlhdCI6MTUxNTY2MjgyMn0.a6homMOumqLBxwfX9nOwbBaxmSx-srkS8dISSPCPPYE";


    public List<String> getExchanges(String stockId) throws IOException {
        URL url = new URL(urlExchange.replace("STOCK", stockId));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/" + "json");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.connect();
        InputStream content = connection.getInputStream();
        BufferedReader in =
                new BufferedReader(new InputStreamReader(content));
        ObjectMapper mapper = new ObjectMapper();
        List<ExchangeDTO> list = mapper.readValue(in.readLine(), new TypeReference<List<ExchangeDTO>>() {
        });
        return list.stream().map(ExchangeDTO::getId).collect(Collectors.toList());
    }

    public BigDecimal getPrice(String stockId,String exchangeId){
        try {
            URL url = new URL(urlPrice.replace("STOCK", stockId).replace("PRODUCTID", exchangeId));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/" + "json");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.connect();
            InputStream content = connection.getInputStream();
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(content));
            ObjectMapper mapper = new ObjectMapper();
            PriceDTO price = mapper.readValue(in.readLine(), PriceDTO.class);
            return BigDecimal.valueOf(Double.valueOf(price.getPrice()));
        } catch (IOException e ){
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

}
