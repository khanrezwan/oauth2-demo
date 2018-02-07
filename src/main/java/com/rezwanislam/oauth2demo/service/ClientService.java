package com.rezwanislam.oauth2demo.service;

import com.rezwanislam.oauth2demo.model.Client;
import com.rezwanislam.oauth2demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service//("clientDetailsService")
public class ClientService implements ClientDetailsService, ClientRegistrationService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientRepository.findByClientId(clientId);
    }

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {


        final Client mongoClientDetails = new Client(clientDetails.getClientId(),
                clientDetails.getClientSecret(),
                clientDetails.getScope(),
                clientDetails.getResourceIds(),
                clientDetails.getAuthorizedGrantTypes(),
                clientDetails.getRegisteredRedirectUri(),
                (List<GrantedAuthority>) clientDetails.getAuthorities(),
                clientDetails.getAccessTokenValiditySeconds(),
                clientDetails.getRefreshTokenValiditySeconds(),
                clientDetails.getAdditionalInformation(),
                getAutoApproveScopes(clientDetails));
//                (clientDetails.getClientId(),
//                passwordEncoder.encode(clientDetails.getClientSecret()),
//                clientDetails.getScope(),
//                clientDetails.getResourceIds(),
//                clientDetails.getAuthorizedGrantTypes(),
//                clientDetails.getRegisteredRedirectUri(),
//                new ArrayList<>().addAll(clientDetails.getAuthorities()),
//                clientDetails.getAccessTokenValiditySeconds(),
//                clientDetails.getRefreshTokenValiditySeconds(),
//                clientDetails.getAdditionalInformation(),
//                getAutoApproveScopes(clientDetails));

        clientRepository.save(mongoClientDetails);
    }

    private Set<String> getAutoApproveScopes(ClientDetails clientDetails) {
        return null;
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
