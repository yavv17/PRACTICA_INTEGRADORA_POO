package com.upiicsa.modelo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class Caballero extends PersonajeBase {
    
    private int armadura;
    private int resistencia;
    private boolean escudo;
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

    public Caballero(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
    }
    

    @Override
    public String ejecutarAtaque(PersonajeBase objetivo) {
        int dano = getFuerza() + getArmadura()
                + getPoder() / 3;

        return getNombre() + " ataca con " + getArma() + ". "
                + objetivo.recibirDano(dano);
    }
    
    @Override
    public String getHabilidadEspecial() {
        return "Bloquear y montar criatura";
    }


    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return bloquearYMontarCriatura(objetivo);
    }

    public String bloquearYMontarCriatura(PersonajeBase objetivo) {
        if (!isEscudo()) {
            return getNombre() + " no puede ejecutar la habilidad porque no tiene su escudo.";
        }

        int aumentoDefensa = getArmadura() + getResistencia() / 2;

        setDefensa(getDefensa() + aumentoDefensa);

        int dano = getFuerza() + getArmadura()
                + getResistencia() + getPoder();

        return getNombre() + " activa su escudo y monta a "
                + getCriaturaApoyo() + ". "
                + "Su defensa aumenta en " + aumentoDefensa + " puntos. "
                + objetivo.recibirDano(dano)
                + " Defensa actual: " + getDefensa();
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
    
    @Override
    public String getAtributosClaseSerializados() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> atributos = new HashMap<>();
            atributos.put("armadura", getArmadura());
            atributos.put("resistencia", getResistencia());
            atributos.put("escudo", isEscudo());
            atributos.put("criaturaApoyo", getCriaturaApoyo());

            return mapper.writeValueAsString(atributos);

        } catch (Exception e) {
            throw new RuntimeException("Error al serializar atributos del caballero", e);
        }
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
