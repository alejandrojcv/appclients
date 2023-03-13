package pin.app.appclients.service;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pin.app.appclients.model.Clients;
import pin.app.appclients.model.KpiClients;
import pin.app.appclients.model.ListClients;
import pin.app.appclients.persistence.ClientRepository;

@Service
public class ClientsServicesImpl implements ClientsService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientsServicesImpl.class);

    @Autowired
    ClientRepository clientRepository;

    int age = 0;
    int count = 0;
    float prom = 0;
    double varianza = 0.0;

    @Override
    public Clients saveClients(Clients clients){
        return clientRepository.save(clients);
    }
    @Override
    public Optional<List<ListClients>> getClients(){
        try {
            List<Clients> clients = clientRepository.findAll();
            return Optional.of(clients.stream()
                    .map(c -> ListClients
                            .builder()
                            .apellido(c.getApellido())
                            .nombre(c.getNombre())
                            .fechaNacimiento(c.getFechaNacimiento())
                            .id(c.getId())
                            .edad(c.getEdad())
                            .fechaProbableMuerte(getFechaProbableMuerte(c.getFechaNacimiento(), c.getEdad()))
                            .build()).collect(Collectors.toList()));
        }
        catch (Exception e) {
            LOGGER.error(":::::: Error: {} ::::::", e.getMessage());
            return Optional.empty();
        }
    }

    public String getFechaProbableMuerte(String fecha, int edad){
        int yyyy = Integer.parseInt(fecha.substring(6)) + edad;
        int prob = 102 - edad;
        Calendar unaFecha;
        Random aleatorio;
        aleatorio = new Random();
        unaFecha = Calendar.getInstance();
        unaFecha.set (aleatorio.nextInt(prob)+yyyy, aleatorio.nextInt(12)+1, aleatorio.nextInt(30)+1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(unaFecha.getTime());
    }

    public Optional<KpiClients> getKpiClients(){
        try {
            List<Clients> clients = clientRepository.findAll();
            age = 0;
            count = 0;
            varianza = 0;
            return Optional.of(getValuesKpi(clients));
        }
        catch (Exception e) {
            LOGGER.error(":::::: Error: {} ::::::", e.getMessage());
            return Optional.empty();
        }
    }

    public KpiClients getValuesKpi(List<Clients> clients) {
        double desviacion= 0.0;
        clients.forEach(c -> {
            age += c.getEdad();
            count ++; }
        );
        prom = age/count;

        clients.forEach(c -> {
            double rango;
            rango = Math.pow(c.getEdad() - prom, 2f);
            varianza += rango; }
        );
        varianza = varianza / count;
        desviacion = Math.sqrt(varianza);
        return KpiClients.builder().desviacionEstandar(desviacion).promedioEdades(prom).build();
    }


}
