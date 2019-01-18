package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;


import com.entity.Salutation;
import com.service.ISalutationService;

@CrossOrigin(origins ="*")
@Controller
@RequestMapping("api/v1")
public class SalutationController {
	@Autowired
	private ISalutationService salutationService;
	
	@GetMapping("salutation/{id}")
	public ResponseEntity<Salutation> getSalutationById(@PathVariable("id") Integer salutation_id) {
		Salutation salutation = salutationService.getSalutationById(salutation_id);
		return new ResponseEntity<Salutation>(salutation, HttpStatus.OK);
	}
	@GetMapping("salutation")
	public ResponseEntity<List<Salutation>> getAllSalutation() {
		List<Salutation> list = salutationService.getAllSalutation();
		System.out.println("posted");
		return new ResponseEntity<List<Salutation>>(list, HttpStatus.OK);
	
	}
	@PostMapping("salutation")
	public ResponseEntity<Void> addSalutation(@RequestBody Salutation salutation, UriComponentsBuilder builder) {
		boolean flag = salutationService.addSalutation(salutation);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/salutation/{id}").buildAndExpand(salutation.getSalutation_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("salutationput")
	public ResponseEntity<Salutation> updateSalutation(@RequestBody Salutation salutation) {
		salutationService.updateSalutation(salutation);
		return new ResponseEntity<Salutation>(salutation, HttpStatus.OK);
	}
	@DeleteMapping("salutation/{id}")
	public ResponseEntity<Void> deleteArticle(@PathVariable("id") Integer salutation_id) {
		salutationService.deleteSalutation(salutation_id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}


}
