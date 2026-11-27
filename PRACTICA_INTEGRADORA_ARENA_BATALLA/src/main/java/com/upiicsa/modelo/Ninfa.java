package com.upiicsa.modelo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class Ninfa extends PersonajeBase {
    
    private String elemento;
    private int nivelSeduccion;
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

    public Ninfa(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
    }
    
    
    
    @Override
    public String ejecutarAtaque(PersonajeBase objetivo) {
        int dano = getPoder() + getExperiencia() / 2;

        return getNombre() + " lanza un ataque de "
                + getElemento() + ". "
                + objetivo.recibirDano(dano);
    }
    
    @Override
    public String getHabilidadEspecial() {
        return "Seduccion y ulti";
    }


    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return seduccionYUlti(objetivo);
    }

    public String seduccionYUlti(PersonajeBase objetivo) {
        int defensaOriginal = objetivo.getDefensa();

        try {
            int reduccion = (int) (defensaOriginal * 0.40);

            objetivo.setDefensa(defensaOriginal - reduccion);

            int dano = getPoder() * 2
                    + getNivelSeduccion() * 2
                    + getExperiencia();

            return getNombre() + " ejecuta " + getHabilidadEspecial() + ". "
                    + "Utiliza su elemento " + getElemento() + ". "
                    + "Reduce temporalmente " + reduccion
                    + " puntos de defensa del rival. "
                    + objetivo.recibirDano(dano);
        } finally {
            objetivo.setDefensa(defensaOriginal);
        }
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

    @Override
    public String getAtributosClaseSerializados() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> atributos = new HashMap<>();
            atributos.put("elemento", getElemento());
            atributos.put("nivelSeduccion", getNivelSeduccion());
            atributos.put("experiencia", getExperiencia());

            return mapper.writeValueAsString(atributos);

        } catch (Exception e) {
            throw new RuntimeException("Error al serializar atributos de la ninfa", e);
        }
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
