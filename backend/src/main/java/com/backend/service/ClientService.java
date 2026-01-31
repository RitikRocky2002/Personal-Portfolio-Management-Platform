package com.backend.service;

import java.util.List;

import com.backend.dto.ClientDTO;
import com.backend.exception.ClientException;

public interface ClientService {
	ClientDTO createClient(ClientDTO client) throws ClientException;

	List<ClientDTO> getAllClients() throws ClientException;

	ClientDTO getClientById(Long id) throws ClientException;

	ClientDTO updateClient(Long id, ClientDTO client) throws ClientException;

	void deleteClient(Long id) throws ClientException;
}
