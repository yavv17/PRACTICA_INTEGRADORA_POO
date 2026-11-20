/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Polinene extends PersonajeBase {
    private int cantidadPolimonedas;
    private int inteligencia;
    private boolean tamear;

    public Polinene(int cantidadPolimonedas, int inteligencia, boolean tamear, String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.cantidadPolimonedas = cantidadPolimonedas;
        this.inteligencia = inteligencia;
        this.tamear = tamear;
    }
    
    
    
    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getTipoClase() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
