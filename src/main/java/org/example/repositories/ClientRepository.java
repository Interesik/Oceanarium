package org.example.repositories;
import org.example.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
        }
    }
    public List<Client> find(Predicate<Client> predicate){
        List<Client> results = new ArrayList();
        for(int i=0; i<size(); i++){
            if(predicate.test(clients.get(i)) == true){
                results.add(clients.get(i));
            }
        }
        return results;
    }
    public boolean testTrue(Client client){
        return true;
    }
    public String raport(){
        return clients.toString();
    }

    public Client getClient(int i) {
        return clients.get(i);
    }

    public int size(){
        return clients.size();
    }
}
