package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.ClientDTO;
import com.backend.exception.ClientException;
import com.backend.service.impl.ClientServiceImpl;

import jakarta.validation.Valid;

@RequestMapping("/clients")
@RestController
public class ClientController {

	@Autowired
	ClientServiceImpl clientService;

	@PostMapping
	public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO client) throws ClientException {
		ClientDTO response = clientService.createClient(client);
		return new ResponseEntity<ClientDTO>(response, HttpStatus.CREATED);

	}

	@GetMapping
	public ResponseEntity<List<ClientDTO>> getAllClients() throws ClientException {
		List<ClientDTO> response = clientService.getAllClients();
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) throws ClientException {
		ClientDTO response = clientService.getClientById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @Valid @RequestBody ClientDTO client)
			throws ClientException {
		ClientDTO response = clientService.updateClient(id, client);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteClient(@PathVariable Long id) throws ClientException {
		clientService.deleteClient(id);
		return new ResponseEntity<>("Client Deleted Successfully", HttpStatus.OK);

	}
}
