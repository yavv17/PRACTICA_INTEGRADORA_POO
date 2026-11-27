package com.upiicsa.modelo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class Saiyan extends PersonajeBase {
    
    private boolean cola;
    private int celulasZ;
    private int zenkai;
    private int ki;

    public Saiyan(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen,boolean cola, int celulasZ, int zenkai, int ki) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.cola = cola;
        this.celulasZ = celulasZ;
        this.zenkai = zenkai;
        this.ki = ki;
    }
    
    public Saiyan(Saiyan original){
        super((PersonajeBase) original);
        this.cola = original.isCola();
        this.celulasZ = original.getCelulasZ();
        this.zenkai = original.getZenkai();
        this.ki = original.ki;
    }

    public Saiyan(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
    }
    
    

    @Override
    public String ejecutarAtaque(PersonajeBase objetivo) {
        int dano = getFuerza() + getPoder()
                + getKi() / 2;

        return getNombre() + " lanza un ataque de energía Saiyan. "
                + objetivo.recibirDano(dano);
    }
    
    @Override
    public String getHabilidadEspecial() {
        return "Cargar ki y transformar";
    }

    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return cargarKiYTransformar(objetivo);
    }

    public String cargarKiYTransformar(PersonajeBase objetivo) {
        int costoKi = 10;

        if (getKi() < costoKi) {
            return getNombre() + " no tiene suficiente Ki para transformarse.";
        }

        setKi(getKi() - costoKi);

        int fuerzaAnterior = getFuerza();
        int poderAnterior = getPoder();

        setFuerza((int) (getFuerza() * 1.5));
        setPoder((int) (getPoder() * 1.5));

        String zenkaiMensaje = "";

        double porcentajeVida = (double) getVida()
                / getVidaMaxima() * 100;

        if (porcentajeVida < 30) {
            int aumentoZenkai = Math.max(1, getZenkai());

            setFuerza(getFuerza() + aumentoZenkai);
            setPoder(getPoder() + aumentoZenkai);

            zenkaiMensaje = "\n¡ZENKAI ACTIVADO! Su fuerza y poder reciben un bono adicional de "
                    + aumentoZenkai + ".";
        }

        int dano = getFuerza() + getPoder()
                + getKi();

        return getNombre() + " activa " + getHabilidadEspecial() + ". "
                + (isCola() ? "Su cola Saiyan se eriza. " : "")
                + "Células Z: " + getCelulasZ() + ". "
                + "Fuerza: " + fuerzaAnterior + " -> " + getFuerza() + ". "
                + "Poder: " + poderAnterior + " -> " + getPoder() + "."
                + zenkaiMensaje + " "
                + objetivo.recibirDano(dano)
                + " Ki restante: " + getKi();
    }

    @Override
    public String getTipoClase() {
        return "Saiyan";
    }
    
    @Override
    public String getEstadisticasClase() {
        String hayCola;
        if(isCola()){
            hayCola = "Tiene cola";
        }else{
            hayCola = "Sin cola";
        }
        return "Cola: " + hayCola + " | CelulasZ: " + 
                getCelulasZ()+ " | Zenkai: " + getZenkai()+
                " | Ki: " + getKi();
    }
    
    @Override
    public String getAtributosClaseSerializados() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> atributos = new HashMap<>();
            atributos.put("cola", isCola());
            atributos.put("celulasZ", getCelulasZ());
            atributos.put("zenkai", getZenkai());
            atributos.put("ki", getKi());

            return mapper.writeValueAsString(atributos);

        } catch (Exception e) {
            throw new RuntimeException("Error al serializar atributos del saiyan", e);
        }
    }

    public boolean isCola() {
        return cola;
    }

    public void setCola(boolean cola) {
        this.cola = cola;
    }

    public int getCelulasZ() {
        return celulasZ;
    }

    public void setCelulasZ(int celulasZ) {
        this.celulasZ = celulasZ;
    }

    public int getZenkai() {
        return zenkai;
    }

    public void setZenkai(int zenkai) {
        this.zenkai = zenkai;
    }

    public int getKi() {
        return ki;
    }

    public void setKi(int ki) {
        this.ki = ki;
    }

    
}
