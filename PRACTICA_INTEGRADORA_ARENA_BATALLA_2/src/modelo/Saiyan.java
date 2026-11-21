package modelo;

public class Saiyan extends PersonajeBase {
    private boolean cola;
    private int celulasZ;
    private int zenkai;
    private int ki;

    public Saiyan(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen,boolean cola, int celulasZ, int zenkai, int ki) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.cola = cola;
        this.celulasZ = celulasZ;
        this.zenkai = zenkai;
        this.ki = ki;
    }

    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return "H Saiyan";
    }

    @Override
    public String getTipoClase() {
        return "Saiyan";
    }

    public boolean isCola() {
        return cola;
    }

    public void setCola(boolean cola) {
        this.cola = cola;
    }

    public int getCelulasZ() {
        return celulasZ;
    }

    public void setCelulasZ(int celulasZ) {
        this.celulasZ = celulasZ;
    }

    public int getZenkai() {
        return zenkai;
    }

    public void setZenkai(int zenkai) {
        this.zenkai = zenkai;
    }

    public int getKi() {
        return ki;
    }

    public void setKi(int ki) {
        this.ki = ki;
    }
    
    
    
    
    
    
}
