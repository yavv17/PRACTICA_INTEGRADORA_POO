package modelo;

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
    
    
    
    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return "H Polinene";
    }

    @Override
    public String getTipoClase() {
        return "Polinene";
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
