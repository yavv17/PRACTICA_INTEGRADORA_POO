package modelo;

public class Caballero extends PersonajeBase {
    private int armadura;
    private int resistencia;
    private boolean escudo;
    private String criaturaApoyo;

    public Caballero(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen,int armadura, int resistencia, boolean escudo, String criaturaApoyo) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.armadura = armadura;
        this.resistencia = resistencia;
        this.escudo = escudo;
        this.criaturaApoyo = criaturaApoyo;
    }

    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return "H Caballero";
    }

    @Override
    public String getTipoClase() {
        return "Caballero";
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

    @Override
    public String toString() {
        return "Caballero{" + "armadura=" + armadura + ", resistencia=" + resistencia + ", escudo=" + escudo + ", criaturaApoyo=" + criaturaApoyo + '}';
    }
    
    
    
    
}
