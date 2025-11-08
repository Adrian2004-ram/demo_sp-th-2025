package org.iedvdm.demo_spth2025.modelo;

import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@AllArgsConstructor // crea el constructor
@Builder
public class Comercial {
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Double comision;
}
