package com.upiicsa.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;


@Entity
public class Golem extends PersonajeBase {
    
    @Column(
        name = "resistencia",
        nullable = false
    )
    private int resistencia;
    
    @Column(
        name = "elemento",
        nullable = false
    )
    private String elemento;

    public Golem(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen,int resistencia, String elemento) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.resistencia = resistencia;
        this.elemento = elemento;
    }
    
    public Golem(Golem original){
        super((PersonajeBase) original);
        this.resistencia = original.getResistencia();
        this.elemento = original.getElemento();
    }

    
    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return "H Golem";
    }

    @Override
    public String getTipoClase() {
        return "Golem";
    }
    
    @Override
    public String getEstadisticasClase() {
        return "Resistencia: " + getResistencia() + " | Elemento: " + getElemento();
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

}
