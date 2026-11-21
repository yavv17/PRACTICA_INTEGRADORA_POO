package modelo;

public class Orco extends PersonajeBase {
    private int colmillos;
    private String tamano;
    private String color;
    private String aditamento;

    public Orco(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen,int colmillos, String tamano, String color, String aditamento) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.colmillos = colmillos;
        this.tamano = tamano;
        this.color = color;
        this.aditamento = aditamento;
    }

    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return "H Orco";
    }

    @Override
    public String getTipoClase() {
        return "Orco";
    }

    public int getColmillos() {
        return colmillos;
    }

    public void setColmillos(int colmillos) {
        this.colmillos = colmillos;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAditamento() {
        return aditamento;
    }

    public void setAditamento(String aditamento) {
        this.aditamento = aditamento;
    }
    
    
    
    
}
