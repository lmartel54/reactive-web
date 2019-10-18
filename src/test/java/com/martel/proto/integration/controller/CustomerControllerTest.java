package com.martel.proto.integration.controller;

import static org.junit.Assert.*;
import static org.springframework.http.MediaType.*;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.martel.proto.category.IntegrationTests;
import com.martel.proto.data.entity.Customer;
import com.martel.proto.data.repository.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@Category(IntegrationTests.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

	@Autowired
	private CustomerRepository repo;

	@Autowired
	private WebTestClient webTestClient;

	private final Customer customer1 = new Customer("web-customer1", 1);
	private final Customer customer2 = new Customer("web-customer2", 2);
	private final Customer customer3 = new Customer("web-customer3", 3);

	@Test
	public void find_Success() {

		// initialize context
		final Customer expected = repo.save(customer1)
												.block();

		// validate function
		final FluxExchangeResult<Customer> result = webTestClient.get()
																					.uri("/api/customer/id/{entityId}", expected.getId())
																					.exchange()
																					.expectStatus()
																					.isOk()
																					.expectHeader()
																					.contentType(TEXT_EVENT_STREAM + ";charset=UTF-8")
																					.returnResult(Customer.class);

		StepVerifier.create(result.getResponseBody())
						.consumeNextWith(actual -> check(customer1, actual))
						.verifyComplete();
	}

	@Test
	public void findAll_Success() {

		// initialize context
		repo  .deleteAll()
				.block();

		repo  .saveAll(Flux.just(customer1, customer2, customer3))
				.blockLast();

		// validate function
		final FluxExchangeResult<Customer> result = webTestClient.get()
																					.uri("/api/customer/all")
																					.exchange()
																					.expectStatus()
																					.isOk()
																					.expectHeader()
																					.contentType(TEXT_EVENT_STREAM + ";charset=UTF-8")
																					.returnResult(Customer.class);

		StepVerifier.create(result.getResponseBody())
						.consumeNextWith(actual -> check(actual, customer1))
						.consumeNextWith(actual -> check(actual, customer2))
						.consumeNextWith(actual -> check(actual, customer3))
						.thenCancel()
						.verify();
	}

	@Test
	public void insert_Success() {

		// initialize context
		final Customer expected = new Customer("web-customer-inserted", 99);

		// validate function
		final FluxExchangeResult<Customer> result = webTestClient.post()
																					.uri("/api/customer/save")
																					.accept(MediaType.TEXT_EVENT_STREAM)
																					.body(Mono.just(expected), Customer.class)
																					.exchange()
																					.expectStatus()
																					.isOk()
																					.expectHeader()
																					.contentType(TEXT_EVENT_STREAM + ";charset=UTF-8")
																					.returnResult(Customer.class);

		StepVerifier.create(result.getResponseBody())
						.consumeNextWith(actual -> check(expected, actual))
						.thenCancel()
						.verify();
	}

	@Test
	public void update_Success() {

		// initialize context
		final Customer current = repo .save(customer1)
												.block();

		current.setName("web-customer-updated");

		// validate function
		final FluxExchangeResult<Customer> result = webTestClient.post()
																					.uri("/api/customer/save")
																					.accept(MediaType.TEXT_EVENT_STREAM)
																					.body(Mono.just(current), Customer.class)
																					.exchange()
																					.expectStatus()
																					.isOk()
																					.expectHeader()
																					.contentType(TEXT_EVENT_STREAM + ";charset=UTF-8")
																					.returnResult(Customer.class);

		StepVerifier.create(result.getResponseBody())
						.consumeNextWith(actual -> check(current, actual))
						.thenCancel()
						.verify();
	}

	@Test
	public void delete_Success() {

		// initialize context
		final Customer current = repo .save(customer1)
												.block();

		// validate function
		webTestClient  .delete()
							.uri("/api/customer/id/{entityId}", current.getId())
							.exchange()
							.expectStatus()
							.isOk();
	}

	private void check(Customer expected, Customer actual) {
		if (expected.getId() == null) {
			assertNotNull(actual.getId());
		} else {
			assertEquals(expected.getId(), actual.getId());
		}
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getAge(), actual.getAge());
	}
}