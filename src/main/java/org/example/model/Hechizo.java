package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hechizo {
    private String id;
    private String nombre;
    private String mago;
    private Integer potencia;
    private String nivelPeligro;
}