/*
 * Created on 2018-10-01 ( Date ISO 2018-10-01 - Time 10:55:15 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
*/
package com.ifi.controller;

import com.ifi.entity.RefBanks;
import com.ifi.service.RefBanksServiceImpl;

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
@RequestMapping(value = "/refBanks", produces = "application/hal+json")
public class RefBanksController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RefBanksController.class);

	private static final String NOT_FOUND ="RefBanks not found";

	@Autowired
	private RefBanksServiceImpl refBanksService;

	@RequestMapping(value = "/",
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<RefBanks> findAll() {
		List<RefBanks> list = refBanksService.findAll();
		return list;
	}


	@GetMapping("{bankCode}")
	public RefBanks get(@PathVariable("bankCode") Integer bankCode) {
		final RefBanks refBanks = refBanksService.getOne(bankCode);
		LOGGER.info("RefBanks found : {}", refBanks);
		return refBanks;
	}

	@PostMapping(consumes = "application/json")
	public RefBanks create(@RequestBody RefBanks refBanks) {
		LOGGER.info("RefBanks creation request : {}", refBanks);
		if (refBanksService.exist(refBanks)) {
			LOGGER.info("RefBanks already exist ! : {}", refBanks);
			return null;
		} else {
			final RefBanks created = refBanksService.create(refBanks);
			LOGGER.info("Created refBanks {}", created);
			return created;
		}
	}

	@PutMapping(value = "{bankCode}", consumes = "application/json")
	public RefBanks update(@PathVariable("bankCode") Integer bankCode, @RequestBody RefBanks refBanks) {
		return refBanksService.update(refBanks);
	}

	@DeleteMapping("{bankCode}")
	public void delete(@PathVariable("bankCode") Integer bankCode) {
		LOGGER.info("RefBanks deletion request : {}", resolveIdForLogger(bankCode));
		if (refBanksService.delete(bankCode)) {
			LOGGER.info("RefBanks succesfully deleted");
		} else {
			LOGGER.info(NOT_FOUND);
		}
	}

	private String resolveIdForLogger(Integer bankCode) {
		final StringBuilder ids = new StringBuilder();
		ids.append(String.valueOf(bankCode));
		return ids.toString();
	}

}

