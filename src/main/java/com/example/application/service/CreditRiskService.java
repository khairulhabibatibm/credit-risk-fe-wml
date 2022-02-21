package com.example.application.service;

import com.example.application.pojo.AccessTokenRequest;
import com.example.application.pojo.AccessTokenRequest2;
import com.example.application.pojo.AccessTokenResponse;
import com.example.application.pojo.AccessTokenResponse2;
import com.example.application.pojo.PredictionInput;
import com.example.application.pojo.PredictionWMLResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.internal.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class CreditRiskService {

    @Autowired
    RestTemplate restTemplate;

    private String baseUrl = System.getenv("BASE_URL");
    private String tokenBaseUrl = System.getenv("TOKEN_BASE_URL");
    private String apikey = System.getenv("IAM_API_KEY");
    private static String URN_TOKEN = "urn:ibm:params:oauth:grant-type:apikey";

    public CreditRiskService(){

    }

    public Mono<PredictionWMLResponse> getPrediction(PredictionInput dto) throws Exception{
        try{

            String accessToken = getAccessToken2();

            ObjectMapper mapper = new ObjectMapper();

            WebClient client = WebClient.builder()
                // .filters(filter -> {
                //     filter.add(logRequest());
                //     filter.add(logResponse());
                // })
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
                .build();

            return client.post()
                .body(Mono.just(dto), PredictionInput.class)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(PredictionWMLResponse.class);

            // return resp.getPredictions().get(0).getValues() + ":" + resp.getPredictions().get(0).getValues();
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        
    }

    // get access token from cp4d cluster
    // private String getAccessToken(){
    //     try {
    //         WebClient client = WebClient.builder()
    //             .baseUrl(baseUrl)
    //             .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
    //             .build();

    //         AccessTokenRequest req = new AccessTokenRequest(username, password);
    //         Mono<AccessTokenResponse> resp = client.post()
    //             .uri(tokenUri)
    //             .body(Mono.just(req),AccessTokenRequest.class)
    //             .header(HttpHeaders.CACHE_CONTROL, "no-cache")
    //             .retrieve()
    //             .bodyToMono(AccessTokenResponse.class);

    //         return resp.block().getToken();
    //     } catch (Exception e) {
    //         //TODO: handle exception
    //         e.printStackTrace();
    //         return "";
    //     }
    // }

    // get access token from global IAM
    private String getAccessToken2(){
        System.out.println("token url = " + tokenBaseUrl + ", apikey = " + apikey);
        try {
            WebClient client = WebClient.builder()
                .baseUrl(tokenBaseUrl)
                // .filters(filter -> {
                //     filter.add(logRequest());
                //     filter.add(logResponse());
                // })
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE) 
                .build();

            MultiValueMap<String,String> bodyMap = new LinkedMultiValueMap<>();
            bodyMap.add("grant_type", URN_TOKEN);
            bodyMap.add("apikey", apikey);
            Mono<AccessTokenResponse2> resp = client.post()
                .body(BodyInserters.fromFormData(bodyMap))
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(AccessTokenResponse2.class);

            return resp.block().getAccess_token();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
                StringBuilder sb = new StringBuilder("Request: \n");
                //append clientRequest method and url
                clientRequest
                  .headers()
                  .forEach((name, values) -> values.forEach(value -> sb.append(name).append(":").append(value).append("#")));
                System.out.println(sb.toString());

                System.out.println(clientRequest.url().toString());
                
            return Mono.just(clientRequest);
        });
    }

    ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
                StringBuilder sb = new StringBuilder("Response: \n");
                //append clientRequest method and url
                clientResponse.headers().asHttpHeaders().forEach((name, values) -> values.forEach(value -> sb.append(name).append(":").append(value).append("#")));
                System.out.println(sb.toString());

                if (clientResponse.statusCode() != null && (clientResponse.statusCode().is4xxClientError() || clientResponse.statusCode().is5xxServerError())) {
                    return clientResponse.bodyToMono(String.class)
					.flatMap(body -> {
						System.out.println("Body is " + body);					
						return Mono.just(clientResponse);
					});
                }

            return Mono.just(clientResponse);
        });
    }
    
}
