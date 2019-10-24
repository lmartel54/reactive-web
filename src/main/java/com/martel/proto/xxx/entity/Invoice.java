package com.martel.proto.xxx.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("admin.invoice")
public class Invoice {

	@Id
	@Column("ivx_id")
	private UUID id;

	@Column("ivx_reference")
	private String reference;

	@Column("ivx_day")
	private int day;

	@Column("ivx_month")
	private int month;

	@Column("ivx_year")
	private int year;

	@Column("ivx_amount")
	private double amount;

	@Column("ivx_file")
	private String file;

	public Invoice(String reference, int day, int month, int year, double amount, String file) {
		super();
		this.reference = reference;
		this.day = day;
		this.month = month;
		this.year = year;
		this.amount = amount;
		this.file = file;
	}
}