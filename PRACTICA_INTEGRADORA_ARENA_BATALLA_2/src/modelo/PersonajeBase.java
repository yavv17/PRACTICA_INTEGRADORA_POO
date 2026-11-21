package modelo;

public abstract class PersonajeBase { 
    private String id; 
    private String nombre; 
    private int vida; 
    private int vidaMaxima; 
    private int fuerza; 
    private int poder; 
    private int defensa; 
    private String arma; 
    private String rutaImagen; // Columna VARCHAR en la tabla Personajes (Unidad V) 
 
    public PersonajeBase(String id, String nombre, int vida, int fuerza,  
                         int poder, int defensa, String arma, String rutaImagen) { 
        this.id = id; 
        this.nombre = nombre; 
        this.vida = vida; 
        this.vidaMaxima = vida; 
        this.fuerza = fuerza; 
        this.poder = poder; 
        this.defensa = defensa; 
        this.arma = arma; 
        this.rutaImagen = rutaImagen; 
    } 
 
    public String recibirDano(int danoBruto) { 
        // TODO: Calcular el daño neto descontando parte de 'this.defensa' 
        // asegurando daño mínimo de 1 y que 'this.vida' nunca sea menor a 0. 
        int danoNeto = Math.max(1, danoBruto - (this.defensa / 4)); 
        this.vida = Math.max(0, this.vida - danoNeto); 
        return this.nombre + " recibe " + danoNeto + " pts de daño (HP: " + this.vida + "/" + 
        this.vidaMaxima + ")"; 
    } 
 
    public abstract String ejecutarHabilidadEspecial(PersonajeBase objetivo); 
    public abstract String getTipoClase();
    // TODO: Completar getters y setters encapsulados necesarios para la JTable, DAO y Controlador 

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getPoder() {
        return poder;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public String getArma() {
        return arma;
    }

    public void setArma(String arma) {
        this.arma = arma;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
    
} 