package com.svscorp.web.fullstack.model;

import lombok.Data;

@Data
public class product_details {
	private String description;
	private long hsn_sac;
	private double quantity;
	private String unit;
	private double rate;
	private double amount;
	private double cgst_rate;
	private double cgst_amount;
	private double sgst_rate;
	private double sgst_amount;
	private double igst_rate;
	private double igst_amount;
	private double tax_amount;
	private double total_amount;

}
