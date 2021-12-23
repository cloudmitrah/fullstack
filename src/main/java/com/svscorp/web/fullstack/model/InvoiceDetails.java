package com.svscorp.web.fullstack.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "invoice")
public class InvoiceDetails {
	@Id
	private String id;
	private String invoice_no;
	private Date invoice_date;
	private long eway_bill_no;
	private String dispatch_from;
	private String place_of_supply;
	private String vehicle_no;
	private String transport_name;
	private String reverse_charge_applicable;
	private String bill_to_name;
	private String bill_to_adress_line1;
	private String bill_to_adress_line2;
	private String bill_to_city;
	private long bill_to_pincode;
	private String bill_to_gstin;
	private String bill_to_state;
	private String bill_to_code;
	private String ship_to_name;
	private String ship_to_adress_line1;
	private String ship_to_adress_line2;
	private String ship_to_city;
	private long ship_to_pincode;
	private String ship_to_gstin;
	private String ship_to_state;
	private String ship_to_code;
	private double amount_without_tax;
	private double total_cgst;
	private double total_sgst;
	private double total_igst;
	private double total_tax;
	private long total_amount_with_tax;
	private String reports_description;
	private List<product_details> productList = new ArrayList<>();

}
