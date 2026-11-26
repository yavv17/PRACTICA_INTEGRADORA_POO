package com.upiicsa.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "Saiyans")
public class Saiyan extends PersonajeBase {
    
    @Column(
        name = "cola",
        nullable = false
    )
    private boolean cola;
    
    @Column(
        name = "celulasZ",
        nullable = false
    )
    private int celulasZ;
    
    @Column(
        name = "zenkai",
        nullable = false
    )
    private int zenkai;
    
    @Column(
        name = "ki",
        nullable = false
    )
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

    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return "H Saiyan";
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
