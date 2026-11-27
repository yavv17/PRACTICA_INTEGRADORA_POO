package com.upiicsa.modelo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class Golem extends PersonajeBase {
    
    private int resistencia;
    private String elemento;

    public Golem(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen,int resistencia, String elemento) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.resistencia = resistencia;
        this.elemento = elemento;
    }
    
    public Golem(Golem original){
        super((PersonajeBase) original);
        this.resistencia = original.getResistencia();
        this.elemento = original.getElemento();
    }

    public Golem(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
    }
    
    

    
    @Override
    public String ejecutarAtaque(PersonajeBase objetivo) {
        int dano = getFuerza() + getPoder()
                + getResistencia() / 2;

        return getNombre() + " ataca utilizando su elemento "
                + getElemento() + ". "
                + objetivo.recibirDano(dano);
    }
    
    @Override
    public String getHabilidadEspecial() {
        return "Mitosis y Hivenmind";
    }


    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return mitosisYHivemind(objetivo);
    }

    public String mitosisYHivemind(PersonajeBase objetivo) {
        int defensaAnterior = getDefensa();
        int resistenciaAnterior = getResistencia();

        int curacion = (int) (getVidaMaxima() * 0.20);
        int vidaAnterior = getVida();

        setDefensa(getDefensa() * 2);
        setResistencia(getResistencia() * 2);

        setVida(Math.min(getVidaMaxima(), getVida() + curacion));

        return getNombre() + " activa " + getHabilidadEspecial() + ". "
                + "Su cuerpo se regenera mediante su elemento "
                + getElemento() + ". "
                + "Defensa: " + defensaAnterior + " -> " + getDefensa() + ". "
                + "Resistencia: " + resistenciaAnterior + " -> " + getResistencia() + ". "
                + "Recupera " + (getVida() - vidaAnterior) + " puntos de vida.";
    }

    @Override
    public String getTipoClase() {
        return "Golem";
    }
    
    @Override
    public String getEstadisticasClase() {
        return "Resistencia: " + getResistencia() + " | Elemento: " + getElemento();
    }
    
    @Override
    public String getAtributosClaseSerializados() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> atributos = new HashMap<>();
            atributos.put("resistencia", getResistencia());
            atributos.put("elemento", getElemento());

            return mapper.writeValueAsString(atributos);

        } catch (Exception e) {
            throw new RuntimeException("Error al serializar atributos del golem", e);
        }
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

}
