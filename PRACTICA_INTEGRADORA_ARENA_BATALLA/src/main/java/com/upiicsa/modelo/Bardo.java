package com.upiicsa.modelo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class Bardo extends PersonajeBase {
    
    private int inspiracion;

    public Bardo(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen,int inspiracion) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.inspiracion = inspiracion;
    }
    
    public Bardo(Bardo original){
        super((PersonajeBase) original);
        this.inspiracion = original.getInspiracion();
    }

    public Bardo(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
    }
    
    
    
    @Override
    public String ejecutarAtaque(PersonajeBase objetivo) {
        int dano = getPoder() + getFuerza() / 2
                + getInspiracion();

        return getNombre() + " ejecuta una melodía ofensiva. "
                + objetivo.recibirDano(dano);
    }
    
    @Override
    public String getHabilidadEspecial() {
        return "Buff e inspirar";
    }


    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return buffEInspirar(objetivo);
    }

    public String buffEInspirar(PersonajeBase objetivo) {
        int vidaAnterior = getVida();

        int curacion = (int) (getVidaMaxima() * 0.15)
                + getInspiracion() * 2;

        setVida(Math.min(getVidaMaxima(), getVida() + curacion));

        int bonoInspiracion = getInspiracion();

        int dano = getPoder() + getFuerza()
                + bonoInspiracion * 2;

        String resultado = getNombre()
                + " ejecuta " + getHabilidadEspecial() + ". "
                + "Su melodía recupera "
                + (getVida() - vidaAnterior) + " puntos de vida. "
                + "La inspiración potencia su melodía ofensiva. "
                + objetivo.recibirDano(dano);

        return resultado;
    }

    @Override
    public String getTipoClase() {
        return "Bardo";
    }
    
    @Override
    public String getEstadisticasClase() {
        return "Inspiracion: " + getInspiracion();
    }
    
    @Override
    public String getAtributosClaseSerializados() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> atributos = new HashMap<>();
            atributos.put("inspiracion", getInspiracion());

            return mapper.writeValueAsString(atributos);

        } catch (Exception e) {
            throw new RuntimeException("Error al serializar atributos del bardo", e);
        }
    }

    public int getInspiracion() {
        return inspiracion;
    }

    public void setInspiracion(int inspiracion) {
        this.inspiracion = inspiracion;
    }

}
