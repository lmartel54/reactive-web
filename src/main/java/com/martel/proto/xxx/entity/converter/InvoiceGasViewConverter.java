package com.martel.proto.xxx.entity.converter;

import com.martel.proto.xxx.entity.Invoice;
import com.martel.proto.xxx.entity.InvoiceGas;
import com.martel.proto.xxx.entity.view.InvoiceGasView;

public class InvoiceGasViewConverter {

	public static Invoice getInvoice(final InvoiceGasView view) {
		final Invoice invoice = new Invoice(view.getReference(), view.getDay(), view.getMonth(), view.getYear(), view.getAmount(), view.getFile());
		invoice.setId(view.getInvoiceId());
		return invoice;
	}

	public static InvoiceGas getInvoiceGas(final InvoiceGasView view) {

		final InvoiceGas invoiceGas = new InvoiceGas(
		view.getStartPeriodDay(),
		view.getStartPeriodMonth(),
		view.getStartPeriodYear(),
		view.getStartPeriodIndex(),
		view.getEndPeriodDay(),
		view.getEndPeriodMonth(),
		view.getEndPeriodYear(),
		view.getEndPeriodIndex(),
		view.getPeriodDuration(),
		view.isAutoReport(),
		view.getVolume(),
		view.getConsumption());
		invoiceGas.setId(view.getId());
		invoiceGas.setInvoiceId(view.getInvoiceId());
		return invoiceGas;
	}

	public static InvoiceGasView from(final Invoice invoice, final InvoiceGas invoiceGas) {

		final InvoiceGasView view = new InvoiceGasView(
		invoice.getReference(),
		invoice.getDay(),
		invoice.getMonth(),
		invoice.getYear(),
		invoice.getAmount(),
		invoice.getFile(),
		invoiceGas.getStartPeriodDay(),
		invoiceGas.getStartPeriodMonth(),
		invoiceGas.getStartPeriodYear(),
		invoiceGas.getStartPeriodIndex(),
		invoiceGas.getEndPeriodDay(),
		invoiceGas.getEndPeriodMonth(),
		invoiceGas.getEndPeriodYear(),
		invoiceGas.getEndPeriodIndex(),
		invoiceGas.getPeriodDuration(),
		invoiceGas.isAutoReport(),
		invoiceGas.getVolume(),
		invoiceGas.getConsumption());
		return view;
	}
}
