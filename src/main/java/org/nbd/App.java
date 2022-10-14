package org.nbd;

import org.checkerframework.checker.units.qual.C;
import org.nbd.crud.ClientRepository;
import org.nbd.entities.Client;

import java.util.Date;

public class App {

    public static void main(String[] args) {
        ClientRepository clientRepository = new ClientRepository();
        clientRepository.createNewClient("Olek", "Kobusinski", new Date(1992, 5, 16));
        Client client = clientRepository.findById(1);
        System.out.println(client.getPersonalID());
        clientRepository.updateFirstName(client, "Aleksander");
        clientRepository.delete(client);
    }
}
