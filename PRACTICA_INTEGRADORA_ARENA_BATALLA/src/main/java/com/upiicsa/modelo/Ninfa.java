package com.upiicsa.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "Ninfas")
public class Ninfa extends PersonajeBase {
    
    @Column(
        name = "elemento",
        nullable = false
    )
    private String elemento;
    
    @Column(
        name = "nivel_seduccion",
        nullable = false
    )
    private int nivelSeduccion;
    
    @Column(
        name = "experiencia",
        nullable = false
    )
    private int experiencia;

    public Ninfa(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen,String elemento, int nivelSeduccion, int experiencia) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.elemento = elemento;
        this.nivelSeduccion = nivelSeduccion;
        this.experiencia = experiencia;
    }
    
    public Ninfa(Ninfa original){
        super((PersonajeBase) original);
        this.elemento = original.getElemento();
        this.nivelSeduccion = original.getNivelSeduccion();
        this.experiencia = original.getExperiencia();
    }
   
    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return "H Ninfa";
    }

    @Override
    public String getTipoClase() {
        return "Ninfa";
    }
    
    @Override
    public String getEstadisticasClase() {
        return "Elemento: " + getElemento()+ " | Nivel de seduccion: " 
                + getNivelSeduccion()+ " | Experiencia: " + getExperiencia();
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public int getNivelSeduccion() {
        return nivelSeduccion;
    }

    public void setNivelSeduccion(int nivelSeduccion) {
        this.nivelSeduccion = nivelSeduccion;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }
    
}
