package modelo;

public class Bardo extends PersonajeBase {
    private int inspiracion;

    public Bardo(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen,int inspiracion) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.inspiracion = inspiracion;
    }

    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return "H Bardo";
    }

    @Override
    public String getTipoClase() {
        return "Bardo";
    }

    public int getInspiracion() {
        return inspiracion;
    }

    public void setInspiracion(int inspiracion) {
        this.inspiracion = inspiracion;
    }
    
    
}
