package modelo;

public class Golem extends PersonajeBase {
    private int resistencia;
    private String elemento;

    public Golem(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen,int resistencia, String elemento) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.resistencia = resistencia;
        this.elemento = elemento;
    }

    
    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return "H Golem";
    }

    @Override
    public String getTipoClase() {
        return "Golem";
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }
    
}
