package modelo;

public class Ninfa extends PersonajeBase {
    private String elemento;
    private int nivelSeduccion;
    private int experiencia;

    public Ninfa(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen,String elemento, int nivelSeduccion, int experiencia) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.elemento = elemento;
        this.nivelSeduccion = nivelSeduccion;
        this.experiencia = experiencia;
    }
   
    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return "H Ninfa";
    }

    @Override
    public String getTipoClase() {
        return "Ninfa";
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
