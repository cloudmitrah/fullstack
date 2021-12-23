package com.svscorp.web.fullstack.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.svscorp.web.fullstack.dao.ProductRepository;
import com.svscorp.web.fullstack.model.InvoiceDetails;
import com.svscorp.web.fullstack.model.product_details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {
	String html = "";
	String InvoiceCopy = "";
	InvoiceDetails invdetails = null;
	String FileName = "";
	@Autowired
	private ProductRepository repository;

	public InvoiceDetails saveProduct(InvoiceDetails product) {
		return repository.save(product);
	}

	public List<InvoiceDetails> saveProducts(List<InvoiceDetails> products) {
		return repository.saveAll(products);
	}

	public List<InvoiceDetails> getProducts() {
		return repository.findAll();

	}

	public List<InvoiceDetails> getReports(String Date1, String Date2) throws IOException {

		List<InvoiceDetails> invoiceList = new ArrayList<>();
		invoiceList = repository.findReport(Date1, Date2);
		return invoiceList;
	}

	public InvoiceDetails getProductById(String id) {
		invdetails = repository.findById(id).orElse(null);
		return invdetails;
	}

	public String deleteProduct(String id) {
		repository.deleteById(id);
		return "product removed !! " + id;
	}



	public InvoiceDetails updateProduct(InvoiceDetails product, String id) {
		if (repository.existsById(id)) {
			product.setId(id);
		}
		return repository.save(product);
	}





//    public InvoiceDetails getProductByName(String name) {
//        return repository.findByName(name);
//    }

	public String createHtml() {
		String InvDate = new SimpleDateFormat("dd/MM/yyyy").format(invdetails.getInvoice_date());
		int sumQuantity = 0;
		int sumAmount = 0;
		int sumCgst = 0;
		int sumSgst = 0;
		int sumIgst = 0;
		int totalTax = 0;
		int sumTotal = 0;
		html = "<html>\r\n" + "  <head>\r\n" + "  <title>Invoice Generation System</title>\r\n" + "  <style>\r\n"
				+ "\r\n" + "table {\r\n" + "    border-collapse: collapse;\r\n" + "    width: 100%;\r\n"
				+ "    margin-left:auto;\r\n" + "    margin-right:auto;\r\n" + "    table-layout:auto !important;\r\n"
				+ "    white-space:normal;\r\n" + "    font-size: 12px;\r\n" + "    word-wrap:break-word;\r\n"
				+ "  }\r\n" + "\r\n" + "  /* .product {\r\n" + "    border-collapse: collapse;\r\n"
				+ "    width: 100%;\r\n" + "    margin-left:auto;\r\n" + "    margin-right:auto;\r\n"
				+ "    table-layout:auto !important;\r\n" + "    white-space:normal;\r\n" + "    font-size: 9px;\r\n"
				+ "    word-wrap:break-word;\r\n" + "  } */\r\n" + "\r\n" + "  @page { margin-top: 18px;\r\n"
				+ "        margin-left: 30px;\r\n" + "        margin-right: 30px;\r\n"
				+ "        margin-bottom: 5px;}\r\n" + "body {\r\n" + "        font:  Helvetica;\r\n" + "}\r\n" + "\r\n"
				+ "  td{\r\n" + "    border: 1px solid #e6e3e3;\r\n" + "    word-wrap:break-word;\r\n" + "  }\r\n"
				+ "\r\n" + "  th{\r\n" + "      text-align: center;\r\n" + "       background-color: #CE902E;\r\n"
				+ "       color: white;\r\n" + " text-align: center;\r\n" + "     border: 1px solid #e6e3e3;\r\n"
				+ "      word-wrap:break-word;\r\n" + "  }\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n" + "  .footer {\r\n"
				+ "  position: absolute;\r\n" + "  right: 0;\r\n" + "  bottom: 0;\r\n" + "  left: 0;\r\n"
				+ "  text-align: center;\r\n" + "  font-size: 11px;\r\n" + "}\r\n" + "\r\n" + "\r\n" + "\r\n"
				+ "  </style>\r\n" + "  </head>\r\n" + "\r\n" + "  <body>\r\n" + "    <center>\r\n"
				+ "      <img src=\"logobill.png\" height=\"33.6\"width=\"149.76\"/>\r\n" + "      <br>\r\n"
				+ "    </center>\r\n" + "    <table>\r\n" + "      <tbody>\r\n" + "              <tr>\r\n"
				+ "                <td width=\"55%\" class=\"abc\" style=\"text-align: right; border:none;\">\r\n"
				+ "                  <font size=\"4\"><b >TAX INVOICE</b></font>\r\n" + "      </td>\r\n"
				+ "      <td width=\"40%\" class=\"abc\" style=\"text-align: right; border:none;\">\r\n" + "      <i>"
				+ InvoiceCopy + "</i>\r\n" + "      </td >\r\n" + "      </tr>\r\n" + "      </tbody>\r\n"
				+ "      </table>\r\n" + "  ";

		html += "   <table class=\"center\">\r\n" + "      <tbody>\r\n" + "      <tr>\r\n"
				+ "      <td width=\"40%\" style=\"border-bottom:none;\">\r\n"
				+ "      <font size=\"3\"><b>GULISTAN</b></font>\r\n" + "      </td>\r\n"
				+ "      <td width=\"15%\">Invoice No.</td>\r\n" + "          <td width=\"45%\"><b>"
				+ invdetails.getInvoice_no() + "</b></td>\r\n" + "          </tr>\r\n" + "  \r\n" + "  \r\n"
				+ "          <tr>\r\n"
				+ "          <td width=\"40%\" style=\"border-top:none;border-bottom:none;\">Shop No.J-80, Dr. Zakir Hussain Nagar,\r\n"
				+ "          </td>\r\n" + "          <td width=\"15%\">Invoice Copy</td>\r\n"
				+ "        <td width=\"45%\">" + InvoiceCopy + "</td>\r\n" + "        </tr>\r\n" + "  \r\n" + "  \r\n"
				+ "          <tr>\r\n"
				+ "            <td width=\"45%\" style=\"border-bottom:none;border-top:none;\">Next to Nagori Hotel, Ghatkopar Mankhurd Link Road,\r\n"
				+ "            </td>\r\n" + "            <td width=\"10%\">Invoice Date</td>\r\n"
				+ "          <td width=\"45%\"> " + InvDate + "</td>\r\n" + "          </tr>\r\n" + "  \r\n"
				+ "          <tr>\r\n"
				+ "            <td width=\"40%\" style=\"border-top:none;border-bottom:none;\">Govandi Mumbai-400043</td>\r\n"
				+ "            <td width=\"15%\">E-way Bill No.</td>\r\n" + "          <td width=\"45%\">"
				+ invdetails.getEway_bill_no() + "</td>\r\n" + "          </tr>\r\n" + "  \r\n" + "          <tr>\r\n"
				+ "          <td width=\"40%\" style=\"border-bottom:none;border-top:none;\">Contact : +91 9821391539</td>\r\n"
				+ "           <td width=\"15%\"> Dispatch From </td>\r\n" + "          <td width=\"45%\">"
				+ invdetails.getDispatch_from() + "</td>\r\n" + "          </tr>\r\n" + "  \r\n" + "          <tr>\r\n"
				+ "            <td width=\"40%\" style=\"border-top:none;border-bottom:none;\">Email:   gulistan.khalid@gmail.com</td>\r\n"
				+ "             <td width=\"15%\">Place of Supply</td>\r\n" + "          <td width=\"45%\">"
				+ invdetails.getPlace_of_supply() + "</td>\r\n" + "          </tr>\r\n" + "     <tr>\r\n"
				+ "       <td width=\"40%\"  style=\"border-top:none;border-bottom:none;\">GSTIN: 27DWCPK1504A1Z8</td>\r\n"
				+ "        <td width=\"15%\" >Vehicle No.</td>\r\n" + "          <td width=\"45%\"> "
				+ invdetails.getVehicle_no() + "</td>\r\n" + "          </tr>\r\n" + "  \r\n" + "          <tr>\r\n"
				+ "            <td width=\"40%\" style=\"border-top:none;border-bottom:none;\">State: Maharashtra</td>\r\n"
				+ "             <td width=\"15%\">Transport Detail</td>\r\n" + "          <td width=\"45%\"> "
				+ invdetails.getTransport_name() + "</td>\r\n" + "          </tr>\r\n" + "  \r\n" + "          <tr>\r\n"
				+ "            <td width=\"40%\" style=\"border-top:none;\">State Code: 27</td>\r\n"
				+ "           <td width=\"15%\"> Reverse Charge Applicable</td>\r\n" + "          <td width=\"45%\">"
				+ invdetails.getReverse_charge_applicable() + "</td>\r\n" + "          </tr>\r\n" + "  \r\n" + "  \r\n"
				+ "    </tbody>\r\n" + "          </table>\r\n" + "    <br>";

		html += " <table class=\"billto\">\r\n" + "  <thead>\r\n" + "        <tr>\r\n"
				+ "          <th colspan=\"2\">Details of Receiver | Bill To</th>\r\n"
				+ "          <th colspan=\"2\">Details of Consignee | Ship To</th>\r\n" + "        </tr>\r\n"
				+ "      </thead>\r\n" + "      <tbody>\r\n" + "        <tr>\r\n"
				+ "          <td width=10% >Name</td>\r\n" + "          <td width=40%><b>"
				+ invdetails.getBill_to_name() + "</b></td>\r\n" + "          <td width=10%>Name</td>\r\n"
				+ "          <td width=40%><b>" + invdetails.getShip_to_name() + "</b></td>\r\n" + "        </tr>\r\n"
				+ "        <tr>\r\n" + "          <td colspan=\"2\" width=\"50%\">\r\n" + "            Address:\r\n"
				+ "           <br>\r\n" + "           " + invdetails.getBill_to_adress_line1() + "\r\n"
				+ "           <br>\r\n" + "            " + invdetails.getBill_to_adress_line2() + "\r\n"
				+ "             <br>\r\n" + "             " + invdetails.getBill_to_city() + "-"
				+ invdetails.getBill_to_pincode() + "\r\n" + "            </td>\r\n" + "\r\n"
				+ "            <td colspan=\"2\" width=\"50%\">\r\n" + "              Address:\r\n"
				+ "             <br>\r\n" + "              " + invdetails.getShip_to_adress_line1() + "\r\n"
				+ "             <br>\r\n" + "              " + invdetails.getShip_to_adress_line2() + "\r\n"
				+ "             <br>\r\n" + "             " + invdetails.getShip_to_city() + "-"
				+ invdetails.getShip_to_pincode() + "\r\n" + "              </td>\r\n" + "        </tr>\r\n"
				+ "        <tr>\r\n" + "          <td>GSTIN</td>\r\n" + "          <td>" + invdetails.getBill_to_gstin()
				+ "</td>\r\n" + "          <td>GSTIN</td>\r\n" + "          <td>" + invdetails.getShip_to_gstin()
				+ "</td>\r\n" + "        </tr>\r\n" + "        <tr>\r\n" + "          <td>State</td>\r\n"
				+ "          <td>" + invdetails.getBill_to_state() + "</td>\r\n" + "          <td>State</td>\r\n"
				+ "          <td>" + invdetails.getShip_to_state() + "</td>\r\n" + "        </tr>\r\n" +

				"<tr>\r\n" + "          <td>State Code</td>\r\n" + "          <td>" + invdetails.getBill_to_code()
				+ "</td>\r\n" + "          <td>State Code</td>\r\n" + "          <td>" + invdetails.getShip_to_code()
				+ "</td>\r\n" + "        </tr>\r\n" +

				"      </tbody>\r\n" + "      </table>\r\n" + "\r\n" + "\r\n" + "    <br>";

		html += "<table style=\"font-size: 12px;\" class=\"product\">\r\n" + "      <tbody>\r\n" + "      <tr>\r\n"
				+ "      <th rowspan=\"2\" >SN</th>\r\n" + "      <th colspan=\"2\" rowspan=\"2\" >Description</th>\r\n"
				+ "      <th rowspan=\"2\" >HSN/ SAC</th>\r\n" + "      <th rowspan=\"2\">Qty</th>\r\n"
				+ "      <th rowspan=\"2\" >Unit</th>\r\n" + "      <th rowspan=\"2\" >Rate</th>\r\n"
				+ "      <th rowspan=\"2\" >Amount</th>\r\n"
				+ "      <th style=\"text-align: center\"colspan=\"2\" >CGST</th>\r\n"
				+ "      <th style=\"text-align: center\" colspan=\"2\" >SGST</th>\r\n"
				+ "      <th style=\"text-align: center\" colspan=\"2\" >IGST</th>\r\n"
				+ "      <th style=\"text-align: center\"rowspan=\"2\" >Tax Amt</th>\r\n"
				+ "      <th style=\"text-align: center\"rowspan=\"2\" >Total</th>\r\n" + "      </tr>\r\n"
				+ "      <tr>\r\n" + "      <th >Rate</th>\r\n" + "      <th>Amt</th>\r\n" + "      <th >Rate</th>\r\n"
				+ "      <th >Amt</th>\r\n" + "      <th >Rate</th>\r\n" + "      <th >Amt</th>\r\n" + "      </tr>\r\n"
				+ "   \r\n" + " <tr>";

		if (!(invdetails.getProductList().isEmpty())) {
			int count = 1;

			for (product_details prd : invdetails.getProductList()) {
				sumQuantity += prd.getQuantity();
				sumAmount += prd.getAmount();
				sumCgst += prd.getCgst_amount();
				sumSgst += prd.getSgst_amount();
				sumIgst += prd.getIgst_amount();
				sumTotal += prd.getTotal_amount();
				totalTax = sumCgst + sumSgst + sumIgst;
				html += "<td >" + count + "</td>\r\n" + " <td colspan=\"2\">" + prd.getDescription() + "</td>\r\n"
						+ " <td >" + prd.getHsn_sac() + "</td>\r\n" + " <td > " + (int) prd.getQuantity() + "</td>\r\n"
						+ " <td >" + prd.getUnit() + "</td>\r\n" + " <td >" + prd.getRate() + "</td>\r\n"
						+ " <td style=\"text-align: left;\" >" + prd.getAmount() + "</td>\r\n" + " <td >"
						+ (int) prd.getCgst_rate() + "%</td>\r\n" + " <td style=\"text-align: left;\" >"
						+ prd.getCgst_amount() + "</td>\r\n" + " <td >" + (int) prd.getSgst_rate() + "%</td>\r\n"
						+ " <td style=\"text-align: left;\">" + prd.getSgst_amount() + "</td>\r\n" + " <td >"
						+ (int) prd.getIgst_rate() + "%</td>\r\n" + " <td style=\"text-align: left;\" >"
						+ prd.getIgst_amount() + "</td>\r\n" + " <td style=\"text-align: left\">" + prd.getTax_amount()
						+ "</td>\r\n" + " <td style=\"text-align: left\">" + prd.getTotal_amount() + "</td>\r\n"
						+ " </tr>";
				count++;
			}
			html += "  <tr>\r\n" + "  <td height=\"25\" colspan=\"4\" ></td>\r\n" + "  <td ></td>\r\n"
					+ "  <td></td>\r\n" + "  <td></td>\r\n" + "  <td ></td>\r\n"
					+ "  <td style=\"text-align:right;\" colspan=\"2\" ></td>\r\n"
					+ "  <td style=\"text-align:right;\" colspan=\"2\" ></td>\r\n"
					+ "  <td style=\"text-align:right;\" colspan=\"2\" ></td>\r\n"
					+ "  <td style=\"text-align:right;\"></td>\r\n" + "  <td style=\"text-align:right;\"></td>\r\n"
					+ "  </tr>";
			html += "<tr>\r\n" + "  <td colspan=\"4\"><b>Sub-Total:</b></td>\r\n" + "  <td >" + sumQuantity
					+ "</td>\r\n" + "  <td >-</td>\r\n" + "  <td > -</td>\r\n" + "  <td style=\"text-align: left;\" >"
					+ sumAmount + "</td>\r\n" + "  <td style=\"text-align:left;\" colspan=\"2\" >" + sumCgst
					+ "</td>\r\n" + "  <td style=\"text-align:left;\" colspan=\"2\" >" + sumSgst + "</td>\r\n"
					+ "  <td style=\"text-align:left;\" colspan=\"2\" > " + sumIgst + " </td>\r\n"
					+ "  <td style=\"text-align:left;\" > " + totalTax + " </td>\r\n"
					+ "  <td style=\"text-align:left;\" > " + sumTotal + " </td>\r\n" + "  </tr>";
			html += "</tbody>\r\n" + "</table>\r\n" + "\r\n" + "<br>";
		}
//		 totalTax = sumCgst + sumSgst + sumIgst;
		String AmountInWords = convert(sumTotal);
		AmountInWords += " Only";

		html += " <table>\r\n" + "      <tbody>\r\n" + "  <tr>\r\n"
				+ "  <td colspan=\"2\"  width=35%><b>Bank Details</b></td>\r\n"
				+ "  <td  width=30%>Total Amount Without Tax</td>\r\n" + "  <td colspan=\"2\" width=45%>" + sumAmount
				+ "</td>\r\n" + "  </tr>\r\n" + "  \r\n" + "  <tr>\r\n" + "  <td >Bank</td>\r\n"
				+ "  <td  >GREATER BANK</td>\r\n" + "  <td >CGST Amount</td>\r\n" + "  <td colspan=\"2\"  >" + sumCgst
				+ "</td>\r\n" + "  </tr>\r\n" + "  \r\n" + "  <tr>\r\n" + "  <td  >Account Number.</td>\r\n"
				+ "  <td  >30207096987</td>\r\n" + "  <td  >SGST Amount</td>\r\n" + "  <td colspan=\"2\" >" + sumSgst
				+ "</td>\r\n" + "  </tr>\r\n" + "  \r\n" + "  <tr>\r\n" + "  <td  >IFSC</td>\r\n"
				+ "  <td  >GBCB0000024</td>\r\n" + "  <td  >IGST Amount</td>\r\n" + "  <td colspan=\"2\" >" + sumIgst
				+ "</td>\r\n" + "  </tr>\r\n" + "  \r\n" + "  <tr>\r\n"
				+ "  <td rowspan=\"2\"colspan=\"2\" ><b>Note:</b></td>\r\n" + "  <td >Total Tax</td>\r\n"
				+ "  <td colspan=\"2\" >" + totalTax + "</td>\r\n" + "  </tr>\r\n" + "  \r\n" + "  <tr>\r\n" + "  \r\n"
				+ "  <td style=\"background-color:#CE902E; color: white;\" colspan=\"1\" >Total Amount with Tax(INR)</td>\r\n"
				+ "  <td style=\"background-color:#CE902E; color: white;\" colspan=\"2\"><b>" + sumTotal
				+ "/-</b></td>\r\n" + "  </tr>\r\n" + "  \r\n" + "  <tr>\r\n"
				+ "  <td  >Total Invoice Amount in Words(INR)</td>\r\n" + "  <td colspan=\"4\" ><b>" + AmountInWords
				+ "</b></td>\r\n" + "  </tr>\r\n" + "  \r\n" + "  <tr height=\"30\">\r\n"
				+ "  <td style=\"text-align: center\" colspan=\"3\" ><img src=\"./QRcode/MyQRCode.png\"/></td>\r\n"
				+ "  <td style=\"text-align: center\" colspan=\"2\" >\r\n" + "  <b>Gulistan</b>\r\n" + "  <br>\r\n"
				+ "  <br>\r\n" + "  <br>\r\n" + "  <!---<img src=\"stamp1.png\" height=\"60\"width=\"160\"/>--->\r\n"
				+ "  <br>\r\n" + "  Authorized Signatory\r\n" + "  </td>\r\n" + "  </tr>\r\n" + "  </tbody>\r\n"
				+ "  </table>";

		html += "<div class=\"footer\">\r\n" + "  <br>\r\n" + "  This is Computer Generated Invoice\r\n" + "</div>\r\n"
				+ "  </body>\r\n" + "</html>";

		return html;
	}

	public String convert(int n) {
		String[] units = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven",
				"Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };

		String[] tens = { "", // 0
				"", // 1
				"Twenty", // 2
				"Thirty", // 3
				"Forty", // 4
				"Fifty", // 5
				"Sixty", // 6
				"Seventy", // 7
				"Eighty", // 8
				"Ninety" // 9
		};

		if (n < 0) {
			return "Minus " + convert(-n);
		}

		if (n < 20) {
			return units[n];
		}

		if (n < 100) {
			return tens[n / 10] + ((n % 10 != 0) ? " " : "") + units[n % 10];
		}

		if (n < 1000) {
			return units[n / 100] + " Hundred" + ((n % 100 != 0) ? " " : "") + convert(n % 100);
		}

		if (n < 100000) {
			return convert(n / 1000) + " Thousand" + ((n % 10000 != 0) ? " " : "") + convert(n % 1000);
		}

		if (n < 10000000) {
			return convert(n / 100000) + " Lakh" + ((n % 100000 != 0) ? " " : "") + convert(n % 100000);
		}

		return convert(n / 10000000) + " Crore" + ((n % 10000000 != 0) ? " " : "") + convert(n % 10000000);
	}



}
