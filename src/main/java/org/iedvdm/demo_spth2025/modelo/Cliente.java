package org.iedvdm.demo_spth2025.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data //crea los getter, setter, etc
@AllArgsConstructor // crea el constructor
@Builder
public class Cliente {
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String ciudad;
    private int categoria;



}
