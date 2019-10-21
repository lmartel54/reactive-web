package com.martel.proto.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("invoices")
public class Invoice {

	@Id
	private Long id;

	private String reference;
	private int day;
	private int month;
	private int year;
	private double amount;
	private double tva;
	private String path;
}