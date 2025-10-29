package org.iedvdm.demo_spth2025.modelo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Comercial {
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Double comisión;
}
