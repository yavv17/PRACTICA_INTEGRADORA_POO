package modelo;

public class Barbaro extends PersonajeBase{
    private int estamina;
    private int resistencia;
    private String armaAdicional;

    public Barbaro(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen,int estamina, int resistencia, String armaAdicional) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.estamina = estamina;
        this.resistencia = resistencia;
        this.armaAdicional = armaAdicional;
    }
    
    
    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return "H Barbaro";
    }

    @Override
    public String getTipoClase() {
        return "Barbaro";
    }

    public int getEstamina() {
        return estamina;
    }

    public void setEstamina(int estamina) {
        this.estamina = estamina;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public String getArmaAdicional() {
        return armaAdicional;
    }

    public void setArmaAdicional(String armaAdicional) {
        this.armaAdicional = armaAdicional;
    }
    
    
}
