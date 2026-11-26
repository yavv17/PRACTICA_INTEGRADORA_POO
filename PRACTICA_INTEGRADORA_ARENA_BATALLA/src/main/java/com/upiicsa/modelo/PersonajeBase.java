package com.upiicsa.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PersonajeBase { 
        
    @Id
    @Column(
        name = "id_per",
        unique = true,
        nullable = false,
        length = 7
    )
    private String id; 
    
    @Column(
        name = "nombre",
        nullable = false
    )
    private String nombre;
    
    @Column(
        name = "vida",
        nullable = false    
    )
    private int vida; 
    
    @Column(
        name = "vida_maxima",
        nullable = false
    )
    private int vidaMaxima; 
    
    @Column(
        name = "fuerza",
        nullable = false
    )
    private int fuerza; 
    
    @Column(
        name = "poder",
        nullable = false
    )
    private int poder; 
    
    @Column(
        name = "defensa",
        nullable = false
    )
    private int defensa; 
    
    @Column(
        name = "arma",
        nullable = false
    )
    private String arma;
    
    @Column(
        name = "ruta_imagen",
        nullable = true
    )
    private String rutaImagen; // Columna VARCHAR en la tabla Personajes (Unidad V) 
 
    public PersonajeBase(String id, String nombre, int vida, int fuerza,  
                         int poder, int defensa, String arma, String rutaImagen) { 
        this.id = id; 
        this.nombre = nombre; 
        this.vida = vida; 
        this.vidaMaxima = vida; 
        this.fuerza = fuerza; 
        this.poder = poder; 
        this.defensa = defensa; 
        this.arma = arma; 
        this.rutaImagen = rutaImagen; 
    } 
    
    public PersonajeBase(PersonajeBase original){
        this.id = original.getId(); 
        this.nombre = original.getNombre(); 
        this.vida = original.getVida(); 
        this.vidaMaxima = original.getVidaMaxima(); 
        this.fuerza = original.getFuerza(); 
        this.poder = original.getPoder(); 
        this.defensa = original.getDefensa(); 
        this.arma = original.getArma(); 
        this.rutaImagen = original.getRutaImagen(); 
    }
 
    public String recibirDano(int danoBruto) { 
        // TODO: Calcular el daño neto descontando parte de 'this.defensa' 
        // asegurando daño mínimo de 1 y que 'this.vida' nunca sea menor a 0. 
        int danoNeto = Math.max(1, danoBruto - (this.defensa / 4)); 
        this.vida = Math.max(0, this.vida - danoNeto); 
        return this.nombre + " recibe " + danoNeto + " pts de daño (HP: " + this.vida + "/" + 
        this.vidaMaxima + ")"; 
    } 
    
    public String getEstadisticasBase(){
        return "Fuerza: " + getFuerza() + " | Poder: " + getPoder() +
                " | Defensa: " + getDefensa() + " | Arma : " + getArma();
    }
 
    public abstract String ejecutarHabilidadEspecial(PersonajeBase objetivo); 
    public abstract String getTipoClase();
    public abstract String getEstadisticasClase();
    // TODO: Completar getters y setters encapsulados necesarios para la JTable, DAO y Controlador 

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getPoder() {
        return poder;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public String getArma() {
        return arma;
    }

    public void setArma(String arma) {
        this.arma = arma;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    @Override
    public String toString() {
        return id + " " + nombre + " (" + getTipoClase() + ")" ;
    }
    
} 