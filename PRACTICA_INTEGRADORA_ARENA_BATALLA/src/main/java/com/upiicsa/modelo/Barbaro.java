package com.upiicsa.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Barbaros")
public class Barbaro extends PersonajeBase{
    
    @Column(
        name = "estamina",
        nullable = false
    )
    private int estamina;
    
    @Column(
        name = "resistencia",
        nullable = false
    )
    private int resistencia;
    
    @Column(
        name = "arma_adicional",
        nullable = false
    )
    private String armaAdicional;

    public Barbaro(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen,int estamina, int resistencia, String armaAdicional) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.estamina = estamina;
        this.resistencia = resistencia;
        this.armaAdicional = armaAdicional;
    }
    
    public Barbaro(Barbaro original){
        super((PersonajeBase) original);
        this.estamina = original.getEstamina();
        this.resistencia = original.getResistencia();
        this.armaAdicional = original.getArmaAdicional();
    }
    
    
    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return "H Barbaro";
    }

    @Override
    public String getTipoClase() {
        return "Barbaro";
    }
    
    @Override
    public String getEstadisticasClase() {
        return "Estamina: " + getEstamina() + " | Resistencia: " 
                + getResistencia() + " | Arma adicional: " + getArmaAdicional();
    }


    public int getEstamina() {
        return estamina;
    }

    public void setEstamina(int estamina) {
        this.estamina = estamina;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public String getArmaAdicional() {
        return armaAdicional;
    }

    public void setArmaAdicional(String armaAdicional) {
        this.armaAdicional = armaAdicional;
    }

}
