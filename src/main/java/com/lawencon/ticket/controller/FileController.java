package com.lawencon.ticket.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticket.model.File;
import com.lawencon.ticket.service.FileService;

@RestController
@RequestMapping("files")
public class FileController {

	@Autowired
	private FileService fileService;

	@GetMapping("{id}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable("id") Long id) throws Exception {
		File file = fileService.findById(id);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=attachment." + file.getExtension());

		byte[] fileInBytes = Base64.getDecoder().decode(file.getFileName());
		return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(fileInBytes);
	}
}