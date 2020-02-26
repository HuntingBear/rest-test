package ru.test.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.test.rest.domain.Client;
import ru.test.rest.domain.ClientService;
import ru.test.rest.dto.ClientModel;
import ru.test.rest.exception.NotFoundException;

@RepositoryRestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @ResponseBody
    @RequestMapping(value = "/clients",
            method = RequestMethod.POST,
            produces = "application/hal+json")
    public PersistentEntityResource newClient(@RequestBody ClientModel clientModel,
                                              PersistentEntityResourceAssembler assembler) {
        Client client = parseClientModel(new Client(), clientModel);
        return assembler.toFullResource(clientService.addClient(client));
    }

    @ResponseBody
    @RequestMapping(value = "/clients/{id}",
            method = RequestMethod.PATCH,
            produces = "application/hal+json")
    @ExceptionHandler(NotFoundException.class)
    public PersistentEntityResource newClient(@PathVariable("id") Long id,
                                              @RequestBody ClientModel clientModel,
                                              PersistentEntityResourceAssembler assembler) {
        clientService.findById(id).ifPresent(client -> {
            parseClientModel(client, clientModel);
            clientService.addClient(client);
        });
        return assembler.toFullResource(clientService.findById(id)
                .orElseThrow(NotFoundException::new));
    }

    private static Client parseClientModel(Client client, final ClientModel clientModel) {
        client.setLogin(clientModel.getLogin() != null ? clientModel.getLogin() : client.getLogin());
        client.setFirstName(clientModel.getFirstName() != null ? clientModel.getFirstName() : client.getFirstName());
        client.setSecondName(clientModel.getSecondName() != null ? clientModel.getSecondName() : client.getSecondName());
        client.setBirthday(clientModel.getBirthday() != null ? clientModel.getBirthday() : client.getBirthday());
        client.setPassword(clientModel.getPassword() != null ? clientModel.getPassword() : client.getPassword());
        client.setAbout(clientModel.getAbout() != null ? clientModel.getAbout() : client.getAbout());
        client.setAddress(clientModel.getAddress() != null ? clientModel.getAddress() : client.getAddress());
        return client;
    }
}
