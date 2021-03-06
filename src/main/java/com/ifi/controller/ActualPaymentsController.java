/*
 * Created on 2018-10-01 ( Date ISO 2018-10-01 - Time 10:55:15 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
*/
package com.ifi.controller;

import com.ifi.entity.ActualPayments;
import com.ifi.service.ActualPaymentsServiceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/actualPayments", produces = "application/hal+json")
public class ActualPaymentsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActualPaymentsController.class);

	private static final String NOT_FOUND ="ActualPayments not found";

	@Autowired
	private ActualPaymentsServiceImpl actualPaymentsService;

	@RequestMapping(value = "/",
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<ActualPayments> findAll() {
		List<ActualPayments> list = actualPaymentsService.findAll();
		return list;
	}


	@GetMapping("{actualPaymentId}")
	public ActualPayments get(@PathVariable("actualPaymentId") Integer actualPaymentId) {
		final ActualPayments actualPayments = actualPaymentsService.getOne(actualPaymentId);
		LOGGER.info("ActualPayments found : {}", actualPayments);
		return actualPayments;
	}

	@PostMapping(consumes = "application/json")
	public ActualPayments create(@RequestBody ActualPayments actualPayments) {
		LOGGER.info("ActualPayments creation request : {}", actualPayments);
		if (actualPaymentsService.exist(actualPayments)) {
			LOGGER.info("ActualPayments already exist ! : {}", actualPayments);
			return null;
		} else {
			final ActualPayments created = actualPaymentsService.create(actualPayments);
			LOGGER.info("Created actualPayments {}", created);
			return created;
		}
	}

	@PutMapping(value = "{actualPaymentId}", consumes = "application/json")
	public ActualPayments update(@PathVariable("actualPaymentId") Integer actualPaymentId, @RequestBody ActualPayments actualPayments) {
		return actualPaymentsService.update(actualPayments);
	}

	@DeleteMapping("{actualPaymentId}")
	public void delete(@PathVariable("actualPaymentId") Integer actualPaymentId) {
		LOGGER.info("ActualPayments deletion request : {}", resolveIdForLogger(actualPaymentId));
		if (actualPaymentsService.delete(actualPaymentId)) {
			LOGGER.info("ActualPayments succesfully deleted");
		} else {
			LOGGER.info(NOT_FOUND);
		}
	}

	private String resolveIdForLogger(Integer actualPaymentId) {
		final StringBuilder ids = new StringBuilder();
		ids.append(String.valueOf(actualPaymentId));
		return ids.toString();
	}

}

