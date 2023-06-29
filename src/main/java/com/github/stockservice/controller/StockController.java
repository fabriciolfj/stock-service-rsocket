package com.github.stockservice.controller;

import com.github.stockservice.dto.StockTickDto;
import com.github.stockservice.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class StockController {


    private final StockService service;

    @MessageMapping("stock.ticks")
    public Flux<StockTickDto> stockPrice() {
        return this.service.getStockPrice();
    }
}
