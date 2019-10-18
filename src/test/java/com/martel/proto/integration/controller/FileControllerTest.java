package com.martel.proto.integration.controller;

import static org.junit.Assert.*;
import static org.springframework.http.MediaType.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void files() throws IOException {
		System.out.println("@@@@@@@@@@ coucou @@@@@@@@@@@@");

		final FluxExchangeResult<Path> result = webTestClient .get()
																				.uri("/file/browse")
																				.accept(TEXT_EVENT_STREAM)
																				.exchange()
																				.expectStatus()
																				.isOk()
																				.expectHeader()
																				.contentType(TEXT_EVENT_STREAM + ";charset=UTF-8")
																				.returnResult(Path.class);

		Stream<Path> files = Files.walk(Paths.get("/home/dev/eclipse"), 1);
		List<Path> paths = files.collect(Collectors.toList());
//		System.out.println("##########");
//		paths.forEach(test -> System.out.println(test));
//		System.out.println("##########");
		// Optional<Path> path1 = files.findFirst();

		System.out.println("##########");
		Flux<Path> result2 = result.getResponseBody();
		result2.subscribe(i -> System.out.println("@@@@@" + i));
		System.out.println("##########");

		StepVerifier.create(result.getResponseBody())

//						.expectSubscription()
//						.expectNext(customer1, customer2, customer3)
//						.expectNextCount(3)
//						.expectNextCount(1)
						.expectNext(paths.get(0))
//						.expectNextCount(1)
						.consumeNextWith(path -> {
							System.out.println("@@@@@@@@@@@@@@@@" + path);
							assertEquals("/home/dev/eclipse/icon.xpm", path .toFile()
																							.getPath());
						})
//						.expectNextCount(1)
//						.expectNext(paths.get(1))
						.consumeNextWith(path -> {
							System.out.println("@@@@@@@@@@@@@@@@" + path);
							assertEquals("/home/dev/eclipse/artifacts.xml", path  .toFile()
																									.getPath());
						})
//						.expectNext(customer2)
						// .consumeNextWith(customer -> assertEquals(customer.getId(), Long.valueOf(2)))
//						.expectNext(customer3)
//						.consumeNextWith(customer -> assertEquals(customer.getId(), Long.valueOf(3)))
//						.expectComplete()
						.thenCancel()
						.verify();
	}
}