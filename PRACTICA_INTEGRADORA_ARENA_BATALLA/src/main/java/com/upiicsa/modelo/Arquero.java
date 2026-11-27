package com.upiicsa.modelo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class Arquero extends PersonajeBase{
    
    private int rango;
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

    public Arquero(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
    }
    
    

    @Override
    public String ejecutarAtaque(PersonajeBase objetivo) {
        int dano = getFuerza() + getPoder() / 2 + getRango();

        return getNombre() + " dispara con " + getArma() + ". "
                + objetivo.recibirDano(dano);
    }
    
    
    @Override
    public String getHabilidadEspecial() {
        return "Disparo perforante";
    }

    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return disparoPerforante(objetivo);
    }

    public String disparoPerforante(PersonajeBase objetivo) {
        int defensaOriginal = objetivo.getDefensa();

        try {
            objetivo.setDefensa(defensaOriginal / 2);

            int dano = getFuerza() + getPoder()
                    + getRango() * 2;

            return getNombre() + " ejecuta " + getHabilidadEspecial() + ". "
                    + "Utiliza munición de tipo: " + getMunicion() + ". "
                    + "Su flecha ignora parte de la defensa rival. "
                    + objetivo.recibirDano(dano);
        } finally {
            objetivo.setDefensa(defensaOriginal);
        }
    }
    
    @Override
    public String getTipoClase() {
        return "Arquero";
    }
    
    @Override
    public String getEstadisticasClase() {
        return "Rango: " + getRango() + " | Municion: " + getMunicion();
    }
    
    @Override
    public String getAtributosClaseSerializados() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> atributos = new HashMap<>();
            atributos.put("rango", getRango());
            atributos.put("municion", getMunicion());

            return mapper.writeValueAsString(atributos);

        } catch (Exception e) {
            throw new RuntimeException("Error al serializar atributos del arquero", e);
        }
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
