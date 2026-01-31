package com.backend.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dto.ClientDTO;
import com.backend.entity.Client;
import com.backend.exception.ClientException;
import com.backend.repository.ClientRepository;
import com.backend.service.ClientService;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ClientRepository clientRepo;

	@Override
	public ClientDTO createClient(ClientDTO client) throws ClientException {
		Long id = client.getId();
		if (id != null) {
			Optional<Client> checkIfPresent = clientRepo.findById(id);
			Client value = checkIfPresent.get();
			if (value != null)
				throw new ClientException("ClientService.CLIENT_ALREADY_EXISTS");
		}
		client.setCreatedAt(LocalDateTime.now());
		Client newClient = modelMapper.map(client, Client.class);
		Client response = clientRepo.save(newClient);
		return modelMapper.map(response, ClientDTO.class);
	}

	@Override
	public List<ClientDTO> getAllClients() throws ClientException {
		List<Client> clients = clientRepo.findAll();
		if (clients == null || clients.isEmpty()) {
			throw new ClientException("ClientService.NO_CLIENTS");
		}
		List<ClientDTO> response = new ArrayList<>();
		for (Client c : clients) {
			response.add(modelMapper.map(c, ClientDTO.class));
		}
		return response;
	}

	@Override
	public ClientDTO getClientById(Long id) throws ClientException {
		Optional<Client> optional = clientRepo.findById(id);
		Client optionalClient = optional.get();
		if (optionalClient == null) {
			throw new ClientException("ClientService.CLIENT_NOT_FOUND");
		}
		ClientDTO client = modelMapper.map(optionalClient, ClientDTO.class);
		return client;
	}

	@Override
	public ClientDTO updateClient(Long id, ClientDTO client) throws ClientException {
		Optional<Client> optional = clientRepo.findById(id);
		Client optionalClient = optional.get();
		if (optionalClient == null) {
			throw new ClientException("ClientService.CLIENT_NOT_FOUND");
		}
		if (client.getId() != null) {
			optionalClient.setId(client.getId());
		}
		if (client.getName() != null) {
			optionalClient.setName(client.getName());
		}
		if (client.getDescription() != null) {
			optionalClient.setDescription(client.getDescription());
		}
		if (client.getLogoUrl() != null) {
			optionalClient.setLogoUrl(client.getLogoUrl());
		}
		if (client.getWebsiteUrl() != null) {
			optionalClient.setWebsiteUrl(client.getWebsiteUrl());
		}

		Client savedClient = clientRepo.save(optionalClient);
		ClientDTO response = modelMapper.map(savedClient, ClientDTO.class);
		return response;
	}

	@Override
	public void deleteClient(Long id) throws ClientException {
		Optional<Client> optional = clientRepo.findById(id);
		Client optionalClient = optional.get();
		if (optionalClient == null) {
			throw new ClientException("ClientService.CLIENT_NOT_FOUND");
		}
		clientRepo.deleteById(id);
	}

}
