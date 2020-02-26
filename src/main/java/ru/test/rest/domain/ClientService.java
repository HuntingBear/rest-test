package ru.test.rest.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.rest.util.Password;

import java.util.Optional;

@Service
@Transactional
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client addClient(Client client) {
        String password = client.getPassword();
        String salt = Password.generateSalt(512).get();
        client.setSalt(salt);
        client.setPassword(Password.hashPassword(password, salt).get());
        return clientRepository.save(client);
    }

    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }
}
