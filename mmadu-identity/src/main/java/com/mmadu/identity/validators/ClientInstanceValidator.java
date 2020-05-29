package com.mmadu.identity.validators;

import com.mmadu.identity.entities.ClientInstance;
import com.mmadu.identity.repositories.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@Slf4j
public class ClientInstanceValidator implements Validator {
    private ClientRepository clientRepository;

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ClientInstance.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        log.trace("Validating client instance {}", o);
        ClientInstance instance = (ClientInstance) o;
        if(instance.getClientId() != null && !clientRepository.existsById(instance.getClientId())){
            errors.rejectValue("clientId", "client.not.exists", "client does not exist");
        }
    }
}
