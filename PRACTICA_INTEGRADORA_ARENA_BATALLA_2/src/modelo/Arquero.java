package modelo;

public class Arquero extends PersonajeBase{
    private int rango;
    private String municion;

    public Arquero(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen, int rango, String municion) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.rango = rango;
        this.municion = municion;
    }
    
    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return "H Arquero";
    }

    @Override
    public String getTipoClase() {
        return "Arquero";
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    public String getMunicion() {
        return municion;
    }

    public void setMunicion(String municion) {
        this.municion = municion;
    }

    @Override
    public String toString() {
        return "Arquero{" + "rango=" + rango + ", municion=" + municion + '}';
    }    
    
}
