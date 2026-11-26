package com.upiicsa.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "Arqueros")
public class Arquero extends PersonajeBase{
    
    @Column(
        name = "rango",
        nullable = false
    )
    private int rango;
    
    @Column(
        name = "municion",
        nullable = false
    )
    private String municion;

    public Arquero(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen, int rango, String municion) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.rango = rango;
        this.municion = municion;
    }
    
    public Arquero(Arquero original){
        super((PersonajeBase) original);
        this.rango = original.getRango();
        this.municion = original.getMunicion();
    }
    
    
    
    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return "H Arquero";
    }

    @Override
    public String getTipoClase() {
        return "Arquero";
    }
    
    @Override
    public String getEstadisticasClase() {
        return "Rango: " + getRango() + " | Municion: " + getMunicion();
    }


    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    public String getMunicion() {
        return municion;
    }

    public void setMunicion(String municion) {
        this.municion = municion;
    }
}
