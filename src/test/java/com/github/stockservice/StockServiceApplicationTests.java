package com.github.stockservice;


import io.rsocket.transport.netty.client.TcpClientTransport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.test.StepVerifier;

@SpringBootTest
class StockServiceApplicationTests {

	@Autowired
	private RSocketRequester.Builder builder;

	@Test
	void stocktPriceTest() {
		var request = this.builder
				.transport(TcpClientTransport.create("localhost", 7070));

		var flux = request.route("stock.ticks")
				.retrieveFlux(StockTickTestDto.class)
				.doOnNext(System.out::println)
				.take(12);

		StepVerifier.create(flux)
				.expectNextCount(12)
				.verifyComplete();
	}

}
