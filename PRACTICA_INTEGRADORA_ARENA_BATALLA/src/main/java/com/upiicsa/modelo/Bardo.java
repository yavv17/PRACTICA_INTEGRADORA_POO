package com.upiicsa.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Bardos")
public class Bardo extends PersonajeBase {
    
    @Column(
        name = "inspiracion",
        nullable = false
    )
    private int inspiracion;

    public Bardo(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen,int inspiracion) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.inspiracion = inspiracion;
    }
    
    public Bardo(Bardo original){
        super((PersonajeBase) original);
        this.inspiracion = original.getInspiracion();
    }

    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return "H Bardo";
    }

    @Override
    public String getTipoClase() {
        return "Bardo";
    }
    
    @Override
    public String getEstadisticasClase() {
        return "Inspiracion: " + getInspiracion();
    }

    public int getInspiracion() {
        return inspiracion;
    }

    public void setInspiracion(int inspiracion) {
        this.inspiracion = inspiracion;
    }

}
