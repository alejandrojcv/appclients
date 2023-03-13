package pin.app.appclients.service;

import java.util.List;
import java.util.Optional;
import pin.app.appclients.model.Clients;
import pin.app.appclients.model.ListClients;

public interface ClientsService {
    public Optional<List<ListClients>> getClients();

    public Clients saveClients(Clients clients);
}
