package com.upiicsa.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Caballeros")
public class Caballero extends PersonajeBase {
    
    @Column(
        name = "armadura",
        nullable = false
    )
    private int armadura;
    
    @Column(
        name = "resistencia",
        nullable = false
    )
    private int resistencia;
    
    @Column(
        name = "escudo",
        nullable = false
    )
    private boolean escudo;
    
    @Column(
        name = "criatura_apoyo",
        nullable = false
    )
    private String criaturaApoyo;

    public Caballero(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen,int armadura, int resistencia, boolean escudo, String criaturaApoyo) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.armadura = armadura;
        this.resistencia = resistencia;
        this.escudo = escudo;
        this.criaturaApoyo = criaturaApoyo;
    }
    
    public Caballero(Caballero original){
        super ((PersonajeBase) original);
        this.armadura = original.getArmadura();
        this.resistencia = original.getResistencia();
        this.escudo = original.isEscudo();
        this.criaturaApoyo = original.getCriaturaApoyo();
    }

    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return "H Caballero";
    }

    @Override
    public String getTipoClase() {
        return "Caballero";
    }
    
    @Override
    public String getEstadisticasClase() {
        String hayEscudo;
        if(isEscudo()){
            hayEscudo = "Activado";
        }else{
            hayEscudo = "Desactivado";
        }
        return "Armadura: " + getArmadura()+ " | Resistencia: " + getResistencia() + " | Escudo: " 
                + hayEscudo + " | Criatura de apoyo: " + getCriaturaApoyo();
    }

    public int getArmadura() {
        return armadura;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public boolean isEscudo() {
        return escudo;
    }

    public void setEscudo(boolean escudo) {
        this.escudo = escudo;
    }

    public String getCriaturaApoyo() {
        return criaturaApoyo;
    }

    public void setCriaturaApoyo(String criaturaApoyo) {
        this.criaturaApoyo = criaturaApoyo;
    }

}
