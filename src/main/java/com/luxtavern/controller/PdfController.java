package com.luxtavern.controller;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luxtavern.service.PdfService;

@RequestMapping("/api/pdf")
@RestController
public class PdfController {
	@Autowired
	PdfService pdfService;
	
	
	@GetMapping("/createPdf")
	public ResponseEntity<InputStreamResource> createPdf() {
		ByteArrayInputStream pdf = pdfService.createPdf();
		HttpHeaders httpHeader=new HttpHeaders();
		 httpHeader.add("Content-Disposition", "inline; filename=lcwd.pdf");
	        return ResponseEntity.ok()
	                .headers(httpHeader)
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(new InputStreamResource(pdf));
		
	}

}
