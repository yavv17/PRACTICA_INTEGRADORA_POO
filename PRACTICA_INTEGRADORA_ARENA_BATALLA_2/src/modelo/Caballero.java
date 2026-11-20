/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Caballero extends PersonajeBase {
    private int armadura;
    private int resistencia;
    private boolean escudo;
    private String criaturaApoyo;

    public Caballero(int armadura, int resistencia, boolean escudo, String criaturaApoyo, String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.armadura = armadura;
        this.resistencia = resistencia;
        this.escudo = escudo;
        this.criaturaApoyo = criaturaApoyo;
    }

    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getTipoClase() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
