package com.github.stockservice.service;

import com.github.stockservice.dto.StockTickDto;
import com.github.stockservice.entities.Stock;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
public class StockService {

    private static final Stock AMZN = new Stock(1000, "AMZN", 20);
    private static final Stock APPL = new Stock(100, "APPL", 3);
    private static final Stock MSFT = new Stock(200, "MSFT", 5);

    public Flux<StockTickDto> getStockPrice() {
        return Flux.interval(Duration.ofSeconds(2))
                .flatMap(i -> Flux.just(AMZN, APPL, MSFT))
                .map(s -> new StockTickDto(s.getCode(), s.getPrice()));
    }
}
