package com.rezwanislam.oauth2demo.service;

import com.rezwanislam.oauth2demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service//("clientDetailsService")
public class ClientService implements ClientDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientRepository.findByClientId(clientId);
    }
}
