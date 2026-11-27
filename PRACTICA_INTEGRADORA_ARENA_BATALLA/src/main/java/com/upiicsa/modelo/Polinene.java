package com.upiicsa.modelo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class Polinene extends PersonajeBase {
   
    private int cantidadPolimonedas;
    private int inteligencia;
    private boolean tamear;

    public Polinene(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen,int cantidadPolimonedas, int inteligencia, boolean tamear) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.cantidadPolimonedas = cantidadPolimonedas;
        this.inteligencia = inteligencia;
        this.tamear = tamear;
    }
    
    public Polinene(Polinene original){
        super((PersonajeBase) original);
        this.cantidadPolimonedas = original.getCantidadPolimonedas();
        this.inteligencia = original.getInteligencia();
        this.tamear = original.isTamear();
    }

    public Polinene(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
    }
    
    
    
    @Override
    public String getHabilidadEspecial() {
        return "Estudiar y tamear burrito";
    }
    
    @Override
    public String ejecutarAtaque(PersonajeBase objetivo) {
        int dano = getPoder() + getInteligencia()
                + getCantidadPolimonedas() / 5;

        return getNombre() + " ejecuta un ataque estratégico IPN. "
                + objetivo.recibirDano(dano);
    }

    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return estudiarYTamearBurrito(objetivo);
    }

    public String estudiarYTamearBurrito(PersonajeBase objetivo) {
        if (!isTamear()) {
            setTamear(true);
            return getNombre()
                    + " no puede ejecutar la habilidad porque todavía no ha tameado a su burrito.";
        }else{
            setTamear(false);
        }

        int costoPolimonedas = 10;

        if (getCantidadPolimonedas() < costoPolimonedas) {
            return getNombre()
                    + " no tiene suficientes Polimonedas para ejecutar la estrategia.";
        }

        setCantidadPolimonedas(
                getCantidadPolimonedas() - costoPolimonedas
        );

        int bonoInteligencia = getInteligencia() / 2;

        int dano = getPoder()
                + getInteligencia() * 2
                + bonoInteligencia;

        return getNombre() + " ejecuta ESTUDIAR Y TAMEAR BURRITO. "
                + "¡Su conocimiento del IPN alcanza otro nivel!\n"
                + "Su inteligencia potencia el ataque estratégico.\n"
                + "Consume " + costoPolimonedas + " Polimonedas.\n"
                + "Polimonedas restantes: " + getCantidadPolimonedas() + ".\n"
                + objetivo.recibirDano(dano);
    }

    @Override
    public String getTipoClase() {
        return "Polinene";
    }
    
    @Override
    public String getEstadisticasClase() {
        String tamea;
        if(isTamear()){
            tamea = "Tameando";
        }else{
            tamea = "Sin tamear";
        }
        return "Cantidad polimonedas: " + getCantidadPolimonedas()+ 
                " | Inteligencia: " + getInteligencia()+ " | Tamear: " + tamea;
    }

    @Override
    public String getAtributosClaseSerializados() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> atributos = new HashMap<>();
            atributos.put("cantidadPolimonedas", getCantidadPolimonedas());
            atributos.put("inteligencia", getInteligencia());
            atributos.put("tamear", isTamear());

            return mapper.writeValueAsString(atributos);

        } catch (Exception e) {
            throw new RuntimeException("Error al serializar atributos del polinene", e);
        }
    }
    
    public int getCantidadPolimonedas() {
        return cantidadPolimonedas;
    }

    public void setCantidadPolimonedas(int cantidadPolimonedas) {
        this.cantidadPolimonedas = cantidadPolimonedas;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public boolean isTamear() {
        return tamear;
    }

    public void setTamear(boolean tamear) {
        this.tamear = tamear;
    }

    
}
