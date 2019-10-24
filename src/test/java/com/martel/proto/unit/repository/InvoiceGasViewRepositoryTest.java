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
import com.martel.proto.xxx.entity.view.InvoiceGasView;
import com.martel.proto.xxx.repository.InvoiceCrudRepository;
import com.martel.proto.xxx.repository.InvoiceGasCrudRepository;
import com.martel.proto.xxx.repository.InvoiceGasViewRepository;

import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@Category(UnitTests.class)
@SpringBootTest
public class InvoiceGasViewRepositoryTest {

	@Autowired
	private InvoiceCrudRepository invoiceCrudRepo;

	@Autowired
	private InvoiceGasCrudRepository invoiceGasCrudRepo;

	@Autowired
	private InvoiceGasViewRepository invoiceGasViewRepo;

	@Test
	public void find_Success() {

		final Invoice invoice = new Invoice("repo-invoice-gas-view-find-UT", 1, 1, 2017, 1145.10, "\\dir1");
		final InvoiceGas invoiceDetail = new InvoiceGas(1, 1, 2018, 0, 31, 12, 2018, 200, 365, true, 201, 301);

		final InvoiceGas expected = initContext(invoice, invoiceDetail);

		StepVerifier.create(invoiceGasViewRepo.findById(expected.getId()))
						.assertNext(actual -> checkEntity(actual, invoice, expected))
						.verifyComplete();

		cleanContext(expected.getInvoiceId());
	}

	@Test
	public void findAll_Success() {

		final Invoice invoice1 = new Invoice("repo-invoice1-gas-view-findAll-UT", 1, 1, 2017, 1145.10, "\\dir1");
		final InvoiceGas invoiceDetail1 = new InvoiceGas(1, 1, 2018, 0, 31, 12, 2018, 200, 365, true, 201, 301);
		final InvoiceGas expected1 = initContext(invoice1, invoiceDetail1);

		final Invoice invoice2 = new Invoice("repo-invoice2-gas-view-findAll-UT", 2, 2, 2018, 12.1, "\\dir2");
		final InvoiceGas invoiceDetail2 = new InvoiceGas(1, 1, 2019, 201, 31, 12, 2019, 400, 365, true, 202, 302);
		final InvoiceGas expected2 = initContext(invoice2, invoiceDetail2);

		StepVerifier.create(invoiceGasViewRepo.findAll())
						.assertNext(actual -> checkEntity(actual, invoice1, expected1))
						.assertNext(actual -> checkEntity(actual, invoice2, expected2))
						.thenCancel()
						.verify();

		cleanContext(expected1.getInvoiceId());
		cleanContext(expected2.getInvoiceId());
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

	public static void checkEntity(final InvoiceGasView actual, final Invoice expectedInvoice, final InvoiceGas expectedInvoiceGas) {

		assertNotNull(actual.getId());
		assertNotNull(actual.getInvoiceId());

		final Invoice actualInvoice = new Invoice(actual.getReference(), actual.getDay(), actual.getMonth(), actual.getYear(), actual.getAmount(), actual.getFile());
		actualInvoice.setId(actual.getInvoiceId());
		InvoiceCrudRepositoryTest.checkEntity(actualInvoice, expectedInvoice);

		final InvoiceGas actualInvoiceGas = new InvoiceGas(
		actual.getStartPeriodDay(),
		actual.getStartPeriodMonth(),
		actual.getStartPeriodYear(),
		actual.getStartPeriodIndex(),
		actual.getEndPeriodDay(),
		actual.getEndPeriodMonth(),
		actual.getEndPeriodYear(),
		actual.getEndPeriodIndex(),
		actual.getPeriodDuration(),
		actual.isAutoReport(),
		actual.getVolume(),
		actual.getConsumption());
		actualInvoiceGas.setId(actual.getId());
		actualInvoiceGas.setInvoiceId(actual.getInvoiceId());
		InvoiceGasCrudRepositoryTest.checkEntity(actualInvoiceGas, expectedInvoiceGas);
	}
}