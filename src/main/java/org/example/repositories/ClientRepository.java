package org.example.repositories;
import org.example.model.Client;

import java.util.List;

public class ClientRepository {
    List<Client> clients;
    public void add(Client newClient){
        clients.add(newClient);
    }
    public void remove(Client oldClient){
        for (Client e:clients) {
            if (oldClient.equals(e)) {
                e.setArchive(true);
                return;
            }
            throw ;
        }
    }
    public String raport(){
        return clients.toString();
    }
    public int size(){
        return clients.size();
    }
}
