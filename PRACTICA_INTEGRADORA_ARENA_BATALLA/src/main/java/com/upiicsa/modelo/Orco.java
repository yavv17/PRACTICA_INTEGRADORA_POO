package com.upiicsa.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "Orcos")
public class Orco extends PersonajeBase {
    
    @Column(
        name = "colmillos",
        nullable = false
    )
    private int colmillos;
    
    @Column(
        name = "tamano",
        nullable = false
    )
    private String tamano;
    
    @Column(
        name = "color",
        nullable = false
    )
    private String color;
    
    @Column(
        name = "aditamento",
        nullable = false
    )
    private String aditamento;

    public Orco(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen,int colmillos, String tamano, String color, String aditamento) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.colmillos = colmillos;
        this.tamano = tamano;
        this.color = color;
        this.aditamento = aditamento;
    }
    
    public Orco(Orco original){
        super((PersonajeBase) original);
        this.colmillos = original.getColmillos();
        this.tamano = original.getTamano();
        this.color = original.getColor();
        this.aditamento = original.getAditamento();
    }

    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return "H Orco";
    }

    @Override
    public String getTipoClase() {
        return "Orco";
    }
    
    @Override
    public String getEstadisticasClase() {
        return "Colmillos: " + getColmillos()+ " | Tamaño: " + 
                getTamano()+ " | Color: " + getColor() 
                + " | Aditamento: " + getAditamento();
    }

    public int getColmillos() {
        return colmillos;
    }

    public void setColmillos(int colmillos) {
        this.colmillos = colmillos;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAditamento() {
        return aditamento;
    }

    public void setAditamento(String aditamento) {
        this.aditamento = aditamento;
    }
    
}
