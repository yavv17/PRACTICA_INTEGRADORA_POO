package com.upiicsa.modelo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;


public class Barbaro extends PersonajeBase{
    

    private int estamina;
    private int resistencia;
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

    public Barbaro(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
    }
    
    

    @Override
    public String ejecutarAtaque(PersonajeBase objetivo) {
        int dano = getFuerza() + getPoder() / 2
                + getResistencia() / 3;

        return getNombre() + " ataca con " + getArma() + ". "
                + objetivo.recibirDano(dano);
    }
    
    @Override
    public String getHabilidadEspecial() {
        return "Furia doble arma";
    }


    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return furiaDobleArma(objetivo);
    }

    public String furiaDobleArma(PersonajeBase objetivo) {
        if (getEstamina() <= 0) {
            return getNombre() + " está demasiado agotado para ejecutar Furia Doble Arma.";
        }

        int defensaAnterior = getDefensa();

        setEstamina(getEstamina() - 1);
        setDefensa(Math.max(0, defensaAnterior - defensaAnterior / 4));

        int dano = getFuerza() * 2 + getPoder()
                + getResistencia() / 2;

        String resultado = getNombre()
                + " ejecuta " + getHabilidadEspecial() + " con"
                + getArma() + " y " + getArmaAdicional() + ". "
                + "Sacrifica parte de su defensa para aumentar su daño crítico. "
                + objetivo.recibirDano(dano);

        return resultado + " Estamina restante: " + getEstamina()
                + " Defensa actual: " + getDefensa();
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
    
    @Override
    public String getAtributosClaseSerializados() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> atributos = new HashMap<>();
            atributos.put("estamina", getEstamina());
            atributos.put("resistencia", getResistencia());
            atributos.put("armaAdicional", getArmaAdicional());

            return mapper.writeValueAsString(atributos);

        } catch (Exception e) {
            throw new RuntimeException("Error al serializar atributos del bárbaro", e);
        }
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
