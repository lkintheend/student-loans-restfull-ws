/*
 * Created on 2018-10-01 ( Date ISO 2018-10-01 - Time 10:55:15 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
*/
package com.ifi.controller;

import com.ifi.entity.Students;
import com.ifi.service.StudentsServiceImpl;

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
@RequestMapping(value = "/students", produces = "application/hal+json")
public class StudentsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentsController.class);

	private static final String NOT_FOUND ="Students not found";

	@Autowired
	private StudentsServiceImpl studentsService;

	@RequestMapping(value = "/",
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Students> findAll() {
		List<Students> list = studentsService.findAll();
		return list;
	}


	@GetMapping("{studentId}")
	public Students get(@PathVariable("studentId") Integer studentId) {
		final Students students = studentsService.getOne(studentId);
		LOGGER.info("Students found : {}", students);
		return students;
	}

	@PostMapping(consumes = "application/json")
	public Students create(@RequestBody Students students) {
		LOGGER.info("Students creation request : {}", students);
		if (studentsService.exist(students)) {
			LOGGER.info("Students already exist ! : {}", students);
			return null;
		} else {
			final Students created = studentsService.create(students);
			LOGGER.info("Created students {}", created);
			return created;
		}
	}

	@PutMapping(value = "{studentId}", consumes = "application/json")
	public Students update(@PathVariable("studentId") Integer studentId, @RequestBody Students students) {
		return studentsService.update(students);
	}

	@DeleteMapping("{studentId}")
	public void delete(@PathVariable("studentId") Integer studentId) {
		LOGGER.info("Students deletion request : {}", resolveIdForLogger(studentId));
		if (studentsService.delete(studentId)) {
			LOGGER.info("Students succesfully deleted");
		} else {
			LOGGER.info(NOT_FOUND);
		}
	}

	private String resolveIdForLogger(Integer studentId) {
		final StringBuilder ids = new StringBuilder();
		ids.append(String.valueOf(studentId));
		return ids.toString();
	}

}

