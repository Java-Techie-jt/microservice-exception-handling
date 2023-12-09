package com.javatechie.client;

import com.javatechie.dto.OrderResponseDTO;
import com.javatechie.exception.SwiggyServiceException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class RestaurantServiceClient {
    @Autowired
    private RestTemplate template;

    public OrderResponseDTO fetchOrderStatus(String orderId) {
        OrderResponseDTO orderResponseDTO=null;
        try {
            orderResponseDTO= template.getForObject("http://RESTAURANT-SERVICE/restaurant/orders/status/" + orderId, OrderResponseDTO.class);

        } catch (HttpServerErrorException errorException) {
            log.error("RestaurantServiceClient::fetchOrderStatus caught the HttpServer server error {}", errorException.getResponseBodyAsString());
            throw new SwiggyServiceException(errorException.getResponseBodyAsString());
        } catch (Exception ex) {
            log.error("RestaurantServiceClient::fetchOrderStatus caught the generic error {}", ex.getMessage());

        }
        return orderResponseDTO;
    }
}
