package com.martel.proto.unit.service;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.martel.proto.category.UnitTests;
import com.martel.proto.unit.repository.InvoiceCrudRepositoryTest;
import com.martel.proto.xxx.entity.ExpenseGas;
import com.martel.proto.xxx.entity.Invoice;
import com.martel.proto.xxx.entity.view.InvoiceGasView;
import com.martel.proto.xxx.repository.InvoiceGasCrudRepository;
import com.martel.proto.xxx.service.ExpenseService;
import com.martel.proto.xxx.repository.InvoiceCrudRepository;

@RunWith(SpringRunner.class)
@Category(UnitTests.class)
@SpringBootTest
public class ExpenseServiceTest {

	@Autowired
	private ExpenseService expenseService;

	private final Invoice invoice1 = new Invoice("repo-invoice1", 1, 1, 2017, 1145.10, "\\dir1");
//	private final Invoice invoice2 = new Invoice("repo-invoice2", 2, 2, 2018, 12.1, "\\dir2");
//	private final Invoice invoice3 = new Invoice("repo-invoice3", 3, 3, 2019, 99, "\\dir3");

	@Rule
	public ExternalResource resource = new ExternalResource() {
		protected void after() {
			expenseService.deletAll();
		}
	};

//	@Test
//	public void find_Success() {
//
//		final Invoice expected = repo .save(invoice1)
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
//		repo  .saveAll(Flux.just(invoice1, invoice2, invoice3))
//				.blockLast();
//
//		// validate function
//		StepVerifier.create(repo.findAll())
//						.assertNext(actual -> check(actual, invoice1))
//						.assertNext(actual -> check(actual, invoice2))
//						.assertNext(actual -> check(actual, invoice3))
//						.thenCancel()
//						.verify();
//	}

	@Test
	public void insert_Success() {

		ExpenseGas invoiceGas = new ExpenseGas("repo-invoice-gaz1");
//		invoiceGas.setInvoice(invoice1);

//		new Invoice("repo-invoice1", 1, 1, 2017, 1145.10, "\\dir1");

		// initialize context
		final ExpenseGas expected = expenseService.save(invoiceGas, invoice1)
																.block();

		// validate function
		InvoiceGasView actual = expenseService .find(expected.getId())
															.block();

		System.out.println("coucou");
//		
//		StepVerifier.create(Mono.just(actual))
//						.assertNext(actual2 -> {
//							check(expected, actual2);
//						})
//		.expectNextCount(1)
//						.verifyComplete();

//		StepVerifier.create(invoiceRepository.findById(expected.getInvoice_id()))
//						.expectNextCount(1)
//						.verifyComplete();
//
//		StepVerifier.create(expenseGasRepository.findById(expected.getId()))
//						.expectNextCount(1)
//						.verifyComplete();
	}

//	@Test
//	public void insert_WithAssignedId_Failed() {
//
//		// initialize context
//		final Invoice invoice = new Invoice("repo-invoice-failed", 4, 4, 2020, 1, "\\dir4");
//		invoice.setId(UUID.randomUUID());
//
//		final Invoice expected = repo .save(invoice)
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
//		final Invoice current = repo  .save(invoice1)
//												.block();
//
//		current.setReference("repo-invoice-updated");
//
//		// validate function
//		final Invoice expected = repo .save(current)
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
//		final Invoice expected = repo .save(invoice1)
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
	private void check(final ExpenseGas expected, final ExpenseGas actual) {
		assertNotNull(actual.getId());
		assertNotNull(actual.getInvoice_id());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getInvoice_id(), expected.getInvoice_id());
		assertEquals(expected.getLolo(), expected.getLolo());
		InvoiceCrudRepositoryTest.check(expected.getInvoice(), actual.getInvoice());
	}
}