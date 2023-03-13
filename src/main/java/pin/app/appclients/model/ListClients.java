package pin.app.appclients.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ListClients {
    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private String fechaNacimiento;
    private String fechaProbableMuerte;
}
