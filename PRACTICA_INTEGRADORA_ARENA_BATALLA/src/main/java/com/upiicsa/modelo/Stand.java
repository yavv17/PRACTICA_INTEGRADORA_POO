package com.upiicsa.modelo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class Stand extends PersonajeBase {

    private int rango;
    private String tipo;
    private String frase;
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

    public Stand(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
    }
    

    @Override
    public String ejecutarAtaque(PersonajeBase objetivo) {
        int dano = getFuerza() + getPoder()
                + getRango();

        return getNombre() + " y su Stand de tipo "
                + getTipo() + " atacan. "
                + objetivo.recibirDano(dano);
    }

    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return manifestarRafaga(objetivo);
    }
    
    @Override
    public String getHabilidadEspecial() {
        return "Manifestar rafaga";
    }


    public String manifestarRafaga(PersonajeBase objetivo) {
        int danoIndividual = getFuerza() + getPoder()
                + getRango();

        int numeroGolpes = 3;
        int danoTotal = danoIndividual * numeroGolpes;

        return getNombre() + " manifiesta su Stand junto a "
                + getUsuario() + ". "
                + "¡" + getFrase() + "! "
                + "Su Stand de tipo " + getTipo()
                + " ejecuta una ráfaga de " + numeroGolpes + " golpes. "
                + objetivo.recibirDano(danoTotal);
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
    
    @Override
    public String getAtributosClaseSerializados() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> atributos = new HashMap<>();
            atributos.put("rango", getRango());
            atributos.put("tipo", getTipo());
            atributos.put("frase", getFrase());
            atributos.put("usuario", getUsuario());

            return mapper.writeValueAsString(atributos);

        } catch (Exception e) {
            throw new RuntimeException("Error al serializar atributos del stand", e);
        }
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
