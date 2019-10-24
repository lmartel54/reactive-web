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
import com.martel.proto.xxx.entity.InvoiceGas;
import com.martel.proto.xxx.repository.InvoiceCrudRepository;
import com.martel.proto.xxx.repository.InvoiceGasCrudRepository;

import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@Category(UnitTests.class)
@SpringBootTest
public class InvoiceGasCrudRepositoryTest {

	@Autowired
	private InvoiceCrudRepository invoiceCrudRepo;

	@Autowired
	private InvoiceGasCrudRepository invoiceGasCrudRepo;

	@Test
	public void find_Success() {

		final Invoice invoice = new Invoice("repo-invoice-gas-find-UT", 1, 1, 2017, 1145.10, "\\dir1");
		final InvoiceGas invoiceDetail = new InvoiceGas(1, 1, 2018, 0, 31, 12, 2018, 200, 365, true, 201, 301);
		final InvoiceGas expected = initContext(invoice, invoiceDetail);

		StepVerifier.create(invoiceGasCrudRepo.findById(invoiceDetail.getId()))
						.assertNext(actual -> checkEntity(actual, expected))
						.verifyComplete();

		cleanContext(expected.getInvoiceId());
	}

	@Test
	public void findAll_Success() {

		final Invoice invoice1 = new Invoice("repo-invoice1-gas-findAll-UT", 1, 1, 2017, 1145.10, "\\dir1");
		final InvoiceGas invoiceDetail1 = new InvoiceGas(1, 1, 2018, 0, 31, 12, 2018, 200, 365, true, 201, 301);
		final InvoiceGas expected1 = initContext(invoice1, invoiceDetail1);

		final Invoice invoice2 = new Invoice("repo-invoice2-gas-findAll-UT", 2, 2, 2018, 12.1, "\\dir2");
		final InvoiceGas invoiceDetail2 = new InvoiceGas(1, 1, 2019, 201, 31, 12, 2019, 400, 365, true, 202, 302);
		final InvoiceGas expected2 = initContext(invoice2, invoiceDetail2);

		StepVerifier.create(invoiceGasCrudRepo.findAll())
						.assertNext(actual -> checkEntity(actual, expected1))
						.assertNext(actual -> checkEntity(actual, expected2))
						.thenCancel()
						.verify();

		cleanContext(expected1.getInvoiceId());
		cleanContext(expected2.getInvoiceId());
	}

	@Test
	public void insert_Success() {

		final Invoice invoice = new Invoice("repo-invoice-gas-insert-UT", 1, 1, 2017, 1145.10, "\\dir1");
		final InvoiceGas invoiceDetail = new InvoiceGas(1, 1, 2018, 0, 31, 12, 2018, 200, 365, true, 201, 301);
		invoiceDetail.setInvoiceId(invoiceCrudRepo.save(invoice)
																.block()
																.getId());

		final InvoiceGas expected = invoiceGasCrudRepo  .save(invoiceDetail)
																		.block();

		StepVerifier.create(invoiceGasCrudRepo.findById(expected.getId()))
						.expectNextCount(1)
						.verifyComplete();

		cleanContext(expected.getInvoiceId());
	}

	@Test
	public void update_Success() {

		final Invoice invoice = new Invoice("repo-invoice-gas-update-UT", 1, 1, 2017, 1145.10, "\\dir1");
		final InvoiceGas invoiceDetail = new InvoiceGas(1, 1, 2018, 0, 31, 12, 2018, 200, 365, true, 201, 301);
		final InvoiceGas current = initContext(invoice, invoiceDetail);

		current.setAutoReport(false);
		current.setStartPeriodDay(24);
		current.setStartPeriodMonth(10);
		current.setStartPeriodYear(2019);

		final InvoiceGas expected = invoiceGasCrudRepo  .save(current)
																		.block();

		StepVerifier.create(invoiceGasCrudRepo.findById(expected.getId()))
						.assertNext(actual -> checkEntity(actual, expected))
						.verifyComplete();

		cleanContext(expected.getInvoiceId());
	}

	@Test
	public void delete_Success() {

		final Invoice invoice = new Invoice("repo-invoice-gas-delete-UT", 1, 1, 2017, 1145.10, "\\dir1");
		final InvoiceGas invoiceDetail = new InvoiceGas(1, 1, 2018, 0, 31, 12, 2018, 200, 365, true, 201, 301);
		final InvoiceGas expected = initContext(invoice, invoiceDetail);

		StepVerifier.create(invoiceGasCrudRepo.findById(expected.getId()))
						.expectNextCount(1)
						.verifyComplete();

		StepVerifier.create(invoiceCrudRepo.findById(expected.getInvoiceId()))
						.expectNextCount(1)
						.verifyComplete();

		invoiceGasCrudRepo.deleteById(expected.getId())
								.block();

		StepVerifier.create(invoiceGasCrudRepo.findById(expected.getId()))
						.expectNextCount(0)
						.verifyComplete();

		StepVerifier.create(invoiceCrudRepo.findById(expected.getInvoiceId()))
						.expectNextCount(1)
						.verifyComplete();

		cleanContext(expected.getInvoiceId());
	}

	private InvoiceGas initContext(final Invoice invoice, final InvoiceGas invoiceDetail) {

		invoiceDetail.setInvoiceId(invoiceCrudRepo.save(invoice)
																.block()
																.getId());

		return invoiceGasCrudRepo  .save(invoiceDetail)
											.block();
	}

	private void cleanContext(UUID... ids) {
		for (UUID id : ids) {
			invoiceCrudRepo.deleteById(id)
								.block();
		}
	}

	public static void checkEntity(final InvoiceGas actual, final InvoiceGas expected) {
		assertNotNull(actual.getId());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getInvoiceId(), actual.getInvoiceId());
		assertEquals(expected.getStartPeriodDay(), actual.getStartPeriodDay());
		assertEquals(expected.getStartPeriodMonth(), actual.getStartPeriodMonth());
		assertEquals(expected.getStartPeriodYear(), actual.getStartPeriodYear());
		assertEquals(expected.getStartPeriodIndex(), actual.getStartPeriodIndex());
		assertEquals(expected.getEndPeriodDay(), actual.getEndPeriodDay());
		assertEquals(expected.getEndPeriodMonth(), actual.getEndPeriodMonth());
		assertEquals(expected.getEndPeriodYear(), actual.getEndPeriodYear());
		assertEquals(expected.getEndPeriodIndex(), actual.getEndPeriodIndex());
		assertEquals(expected.getPeriodDuration(), actual.getPeriodDuration());
		assertEquals(expected.isAutoReport(), actual.isAutoReport());
		assertEquals(expected.getVolume(), actual.getVolume());
		assertEquals(expected.getConsumption(), actual.getConsumption());
	}
}