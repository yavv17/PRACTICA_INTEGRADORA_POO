/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Ninfa extends PersonajeBase {
    private String elemento;
    private int nivelSeduccion;
    private int experiencia;

    public Ninfa(String elemento, int nivelSeduccion, int experiencia, String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.elemento = elemento;
        this.nivelSeduccion = nivelSeduccion;
        this.experiencia = experiencia;
    }
   
    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getTipoClase() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public int getNivelSeduccion() {
        return nivelSeduccion;
    }

    public void setNivelSeduccion(int nivelSeduccion) {
        this.nivelSeduccion = nivelSeduccion;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }
    
    
    
}
