/*
 * Created on 2018-10-01 ( Date ISO 2018-10-01 - Time 10:55:15 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
*/
package com.ifi.controller;

import com.ifi.entity.Addresses;
import com.ifi.service.AddressesServiceImpl;

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
@RequestMapping(value = "/addresses", produces = "application/hal+json")
public class AddressesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddressesController.class);

	private static final String NOT_FOUND ="Addresses not found";

	@Autowired
	private AddressesServiceImpl addressesService;

	@RequestMapping(value = "/",
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Addresses> findAll() {
		List<Addresses> list = addressesService.findAll();
		return list;
	}


	@GetMapping("{addressId}")
	public Addresses get(@PathVariable("addressId") Integer addressId) {
		final Addresses addresses = addressesService.getOne(addressId);
		LOGGER.info("Addresses found : {}", addresses);
		return addresses;
	}

	@PostMapping(consumes = "application/json")
	public Addresses create(@RequestBody Addresses addresses) {
		LOGGER.info("Addresses creation request : {}", addresses);
		if (addressesService.exist(addresses)) {
			LOGGER.info("Addresses already exist ! : {}", addresses);
			return null;
		} else {
			final Addresses created = addressesService.create(addresses);
			LOGGER.info("Created addresses {}", created);
			return created;
		}
	}

	@PutMapping(value = "{addressId}", consumes = "application/json")
	public Addresses update(@PathVariable("addressId") Integer addressId, @RequestBody Addresses addresses) {
		return addressesService.update(addresses);
	}

	@DeleteMapping("{addressId}")
	public void delete(@PathVariable("addressId") Integer addressId) {
		LOGGER.info("Addresses deletion request : {}", resolveIdForLogger(addressId));
		if (addressesService.delete(addressId)) {
			LOGGER.info("Addresses succesfully deleted");
		} else {
			LOGGER.info(NOT_FOUND);
		}
	}

	private String resolveIdForLogger(Integer addressId) {
		final StringBuilder ids = new StringBuilder();
		ids.append(String.valueOf(addressId));
		return ids.toString();
	}

}

