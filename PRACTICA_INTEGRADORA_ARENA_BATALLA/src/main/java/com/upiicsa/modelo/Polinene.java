package com.upiicsa.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Polinenes")
public class Polinene extends PersonajeBase {
   
    @Column(
        name = "cantidad_polimonedas",
        nullable = false
    )
    private int cantidadPolimonedas;
    
    @Column(
        name = "inteligencia",
        nullable = false
    )
    private int inteligencia;
    
    @Column(
        name = "tamear",
        nullable = false
    )
    private boolean tamear;

    public Polinene(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen,int cantidadPolimonedas, int inteligencia, boolean tamear) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.cantidadPolimonedas = cantidadPolimonedas;
        this.inteligencia = inteligencia;
        this.tamear = tamear;
    }
    
    public Polinene(Polinene original){
        super((PersonajeBase) original);
        this.cantidadPolimonedas = original.getCantidadPolimonedas();
        this.inteligencia = original.getInteligencia();
        this.tamear = original.isTamear();
    }
    
    
    
    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return "H Polinene";
    }

    @Override
    public String getTipoClase() {
        return "Polinene";
    }
    
    @Override
    public String getEstadisticasClase() {
        String tamea;
        if(isTamear()){
            tamea = "Tameando";
        }else{
            tamea = "Sin tamear";
        }
        return "Cantidad polimonedas: " + getCantidadPolimonedas()+ 
                " | Inteligencia: " + getInteligencia()+ " | Tamear: " + tamea;
    }

    public int getCantidadPolimonedas() {
        return cantidadPolimonedas;
    }

    public void setCantidadPolimonedas(int cantidadPolimonedas) {
        this.cantidadPolimonedas = cantidadPolimonedas;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public boolean isTamear() {
        return tamear;
    }

    public void setTamear(boolean tamear) {
        this.tamear = tamear;
    }

    
}
