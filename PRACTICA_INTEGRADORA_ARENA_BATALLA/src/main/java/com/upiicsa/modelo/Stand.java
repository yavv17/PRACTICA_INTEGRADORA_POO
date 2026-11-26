package com.upiicsa.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Stand")
public class Stand extends PersonajeBase {
    
    @Column
    private int rango;
    
    @Column
    private String tipo;
    
    @Column
    private String frase;
    
    @Column
    private String usuario;

    public Stand(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen,int rango, String tipo, String frase, String usuario) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.rango = rango;
        this.tipo = tipo;
        this.frase = frase;
        this.usuario = usuario;
    }
    
    public Stand(Stand original){
        super((PersonajeBase) original);
        this.rango = original.getRango();
        this.tipo = original.getTipo();
        this.frase = original.getFrase();
        this.usuario = original.getUsuario();
    }

    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return "H Stand";
    }

    @Override
    public String getTipoClase() {
        return "Stand";
    }
    
    @Override
    public String getEstadisticasClase() {
        return "Rango: " + getRango()+ " | Tipo: " + getTipo()+ 
                " | Frase: " + getFrase() + " | Usuario: " + getUsuario() ;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
