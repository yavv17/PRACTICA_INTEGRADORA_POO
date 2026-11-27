package com.upiicsa.modelo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class Orco extends PersonajeBase {
    
    private int colmillos;
    private String tamano;
    private String color;
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

    public Orco(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
    }
    

    @Override
    public String ejecutarAtaque(PersonajeBase objetivo) {
        int dano = getFuerza() + getPoder() / 2
                + getColmillos();

        return getNombre() + " ataca brutalmente con "
                + getArma() + ". "
                + objetivo.recibirDano(dano);
    }
    
    @Override
    public String getHabilidadEspecial() {
        return "Wagh";
    }


    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return wagh(objetivo);
    }

    public String wagh(PersonajeBase objetivo) {
        int fuerzaOriginal = getFuerza();

        try {
            int aumento = Math.max(1, fuerzaOriginal / 2);

            setFuerza(fuerzaOriginal + aumento);

            int dano = getFuerza() + getPoder()
                    + getColmillos() * 2;

            return getNombre() + " grita " + getHabilidadEspecial() + " "
                    + "Su tamaño " + getTamano()
                    + " y sus colmillos " + getColor()
                    + " reflejan su furia. "
                    + "Su fuerza aumenta temporalmente a "
                    + getFuerza() + ". "
                    + objetivo.recibirDano(dano);
        } finally {
            setFuerza(fuerzaOriginal);
        }
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
    
    @Override
    public String getAtributosClaseSerializados() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> atributos = new HashMap<>();
            atributos.put("colmillos", getColmillos());
            atributos.put("tamano", getTamano());
            atributos.put("color", getColor());
            atributos.put("aditamento", getAditamento());

            return mapper.writeValueAsString(atributos);

        } catch (Exception e) {
            throw new RuntimeException("Error al serializar atributos del orco", e);
        }
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
