package com.martel.proto.unit.repository;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.martel.proto.category.UnitTests;
import com.martel.proto.xxx.entity.Invoice;
import com.martel.proto.xxx.repository.InvoiceCrudRepository;

import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@Category(UnitTests.class)
@SpringBootTest
public class InvoiceCrudRepositoryTest {

	@Autowired
	private InvoiceCrudRepository invoiceCrudRepository;

	@Test
	public void find_Success() {

		final Invoice invoice = new Invoice("invoice-find-UT", 1, 1, 2017, 1145.10, "\\dir1");
		final Invoice expected = initContext(invoice);

		StepVerifier.create(invoiceCrudRepository.findById(expected.getId()))
						.assertNext(actual -> checkEntity(actual, expected))
						.verifyComplete();

		cleanContext(expected.getId());
	}

	@Test
	public void findAll_Success() {

		final Invoice invoice1 = new Invoice("invoice1-findAll-UT", 1, 1, 2017, 1145.10, "\\dir1");
		final Invoice expected1 = initContext(invoice1);

		final Invoice invoice2 = new Invoice("invoice2-findAll-UT", 2, 2, 2018, 12.1, "\\dir2");
		final Invoice expected2 = initContext(invoice2);

		StepVerifier.create(invoiceCrudRepository.findAll())
						.assertNext(actual -> checkEntity(actual, invoice1))
						.assertNext(actual -> checkEntity(actual, invoice2))
						.thenCancel()
						.verify();

		cleanContext(expected1.getId());
		cleanContext(expected2.getId());
	}

	@Test
	public void insert_Success() {

		final Invoice invoice = new Invoice("invoice-insert-UT", 1, 1, 2017, 1145.10, "\\dir1");
		final Invoice expected = initContext(invoice);
		
		StepVerifier.create(invoiceCrudRepository.findById(expected.getId()))
						.expectNextCount(1)
						.verifyComplete();

		cleanContext(expected.getId());
	}

	@Test
	public void insert_WithAssignedId_Failed() {

		final Invoice invoice = new Invoice("invoice-insert-failed-UT", 4, 4, 2020, 1, "\\dir4");
		invoice.setId(UUID.randomUUID());
		final Invoice expected = initContext(invoice);

		StepVerifier.create(invoiceCrudRepository.findById(expected.getId()))
						.expectNextCount(0)
						.verifyComplete();
	}

	@Test
	public void update_Success() {

		final Invoice invoice = new Invoice("invoice-update-UT", 1, 1, 2017, 1145.10, "\\dir1");
		final Invoice initial = initContext(invoice);

		initial.setReference("repo-invoice-updated");
		final Invoice expected = invoiceCrudRepository  .save(initial)
																.block();

		StepVerifier.create(invoiceCrudRepository.findById(expected.getId()))
						.assertNext(actual -> checkEntity(actual, expected))
						.verifyComplete();

		cleanContext(expected.getId());
	}

	@Test
	public void delete_Success() {

		final Invoice invoice = new Invoice("invoice-update-UT", 1, 1, 2017, 1145.10, "\\dir1");
		final Invoice expected = initContext(invoice);

		StepVerifier.create(invoiceCrudRepository.findById(expected.getId()))
						.expectNextCount(1)
						.verifyComplete();

		invoiceCrudRepository.deleteById(expected.getId())
							.block();

		StepVerifier.create(invoiceCrudRepository.findById(expected.getId()))
						.expectNextCount(0)
						.verifyComplete();
	}

	private Invoice initContext(final Invoice invoice) {
		return invoiceCrudRepository  .save(invoice)
										.block();
	}

	private void cleanContext(UUID... ids) {
		for (UUID id : ids) {
			invoiceCrudRepository.deleteById(id)
								.block();
		}
	}

	public static void checkEntity(final Invoice actual, final Invoice expected) {
		assertNotNull(actual.getId());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getReference(), actual.getReference());
		assertEquals(expected.getDay(), actual.getDay());
		assertEquals(expected.getMonth(), actual.getMonth());
		assertEquals(expected.getYear(), actual.getYear());
		assertTrue(expected.getAmount() == expected.getAmount());
		assertEquals(expected.getFile(), expected.getFile());
	}
}