package pin.app.appclients.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class ListClients {
    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private String fechaNacimiento;
    private String fechaProbableMuerte;
}
