package pin.app.appclients.controller;


import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pin.app.appclients.model.Clients;
import pin.app.appclients.model.KpiClients;
import pin.app.appclients.model.ListClients;
import pin.app.appclients.service.ClientsServicesImpl;

@RestController
public class ClientsController {
    @Autowired
    ClientsServicesImpl clientsServices;

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientsController.class);

    @PostMapping("/creacliente")
    public ResponseEntity<Clients> newClient(@RequestBody Clients client){
        try {
            if (!StringUtils.hasText(String.valueOf(client.getEdad())) || client.getEdad() <= 0) {
                LOGGER.info(":::::::::: FALTA INGRESAR LA EDAD ::::::::::");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if (!StringUtils.hasText(client.getFechaNacimiento())) {
                LOGGER.info(":::::::::: FALTA INGRESAR LA FECHA DE NACIMIENTO ::::::::::");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if (!StringUtils.hasText(client.getNombre())) {
                LOGGER.info(":::::::::: FALTA INGREAR EL NOMBRE ::::::::::");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if (!StringUtils.hasText(client.getApellido())) {
                LOGGER.info(":::::::::: FALTA INGRESAR EL APELLIDO ::::::::::");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            Clients newClient = clientsServices.saveClients(client);
            return new ResponseEntity<>(newClient, HttpStatus.CREATED);
        }
        catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/kpideclientes")
    public ResponseEntity<KpiClients> getKpiClients(){
        try {
            Optional<KpiClients> kpiClients = clientsServices.getKpiClients();
            if (kpiClients.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity
                    .ok()
                    .body(kpiClients.get());
        }
        catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }

    }

    @GetMapping("/listclientes")
    public ResponseEntity<List<ListClients>> getClients(){
        try {
            Optional<List<ListClients>> listClients = clientsServices.getClients();
            if (listClients.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity
                    .ok()
                    .body(listClients.get());
        }
        catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
