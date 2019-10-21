package com.martel.proto.unit.repository;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.martel.proto.category.UnitTests;
import com.martel.proto.data.entity.Customer;
import com.martel.proto.data.entity.Invoice;
import com.martel.proto.data.repository.InvoiceRepository;

import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@Category(UnitTests.class)
@SpringBootTest
public class InvoiceCrudRepositoryTest {

	@Autowired
	private InvoiceRepository repo;

	private final Customer customer1 = new Customer("repo-customer1", 1);

	private final Invoice invoice1 = new Invoice(null, "SDO-250-2016-1000145398", 24, 1, 2016, 1145.10, 19.6, "\\dir");

//	private final Customer customer2 = new Customer("repo-customer2", 2);
//	private final Customer customer3 = new Customer("repo-customer3", 3);

//	@Test
//	public void find_Success() {
//
//		final Customer expected = repo.save(customer1)
//												.block();
//
//		StepVerifier.create(repo.findById(expected.getId()))
//						.assertNext(actual -> check(actual, expected))
//						.verifyComplete();
//	}
//
//	@Test
//	public void findAll_Success() {
//
//		// initialize context
//		repo  .deleteAll()
//				.block();
//
//		repo  .saveAll(Flux.just(customer1, customer2, customer3))
//				.blockLast();
//
//		// validate function
//		StepVerifier.create(repo.findAll())
//						.assertNext(actual -> check(actual, customer1))
//						.assertNext(actual -> check(actual, customer2))
//						.assertNext(actual -> check(actual, customer3))
//						.thenCancel()
//						.verify();
//	}

	@Test
	public void insert_Success() {

		// initialize context
		final Invoice expected = repo .save(invoice1)
												.block();

		// validate function
		StepVerifier.create(repo.findById(expected.getId()))
						.expectNextCount(1)
						.verifyComplete();
	}

//	@Test
//	public void insert_WithAssignedId_Failed() {
//
//		// initialize context
//		final Customer customer = new Customer("repo-customer-failed", 99);
//		customer.setId(Long.valueOf(1));
//
//		final Customer expected = repo.save(customer)
//												.block();
//
//		// validate function
//		StepVerifier.create(repo.findById(expected.getId()))
//						.expectNextCount(0)
//						.verifyComplete();
//	}
//
//	@Test
//	public void update_Success() {
//
//		// initialize context
//		final Customer current = repo .save(customer1)
//												.block();
//
//		current.setName("repo-customer-updated");
//
//		// validate function
//		final Customer expected = repo.save(current)
//												.block();
//
//		StepVerifier.create(repo.findById(expected.getId()))
//						.assertNext(actual -> check(actual, expected))
//						.verifyComplete();
//	}
//
//	@Test
//	public void delete_Success() {
//
//		// initialize context
//		final Customer expected = repo.save(customer1)
//												.block();
//
//		StepVerifier.create(repo.findById(expected.getId()))
//						.expectNextCount(1)
//						.verifyComplete();
//
//		// validate function
//		repo  .delete(expected)
//				.block();
//
//		StepVerifier.create(repo.findById(expected.getId()))
//						.expectNextCount(0)
//						.verifyComplete();
//	}
//
//	private void check(final Customer expected, final Customer actual) {
//		assertNotNull(actual.getId());
//		assertEquals(expected.getId(), actual.getId());
//		assertEquals(expected.getName(), actual.getName());
//		assertEquals(expected.getAge(), actual.getAge());
//	}
}