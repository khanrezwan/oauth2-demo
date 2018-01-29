package com.rezwanislam.oauth2demo.service;

import com.rezwanislam.oauth2demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service//("clientDetailsService")
public class ClientService implements ClientDetailsService, ClientRegistrationService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientRepository.findByClientId(clientId);
    }

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {

    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {

    }

    @Override
    public void updateClientSecret(String s, String s1) throws NoSuchClientException {

    }

    @Override
    public void removeClientDetails(String s) throws NoSuchClientException {

    }

    @Override
    public List<ClientDetails> listClientDetails() {
        return null;
    }
}
