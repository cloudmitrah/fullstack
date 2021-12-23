package com.svscorp.web.fullstack.dao;

import java.util.List;

import com.svscorp.web.fullstack.model.InvoiceDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface ProductRepository extends MongoRepository<InvoiceDetails, String> {
	@Query("{'arrived': {$gte: ?0, $lte:?1 }}")
	List<InvoiceDetails> findReport(String Date1, String Date2);
}
