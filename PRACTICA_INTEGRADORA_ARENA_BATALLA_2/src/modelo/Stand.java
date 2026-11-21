package modelo;

public class Stand extends PersonajeBase {
    private int rango;
    private String tipo;
    private String frase;
    private String usuario;

    public Stand(String id, String nombre, int vida, int fuerza, int poder, int defensa, String arma, String rutaImagen,int rango, String tipo, String frase, String usuario) {
        super(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen);
        this.rango = rango;
        this.tipo = tipo;
        this.frase = frase;
        this.usuario = usuario;
    }

    @Override
    public String ejecutarHabilidadEspecial(PersonajeBase objetivo) {
        return "H Stand";
    }

    @Override
    public String getTipoClase() {
        return "Stand";
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
   
}
