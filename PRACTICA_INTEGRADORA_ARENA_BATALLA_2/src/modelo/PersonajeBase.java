package modelo;

public abstract class PersonajeBase { 
    protected String id; 
    protected String nombre; 
    protected int vida; 
    protected int vidaMaxima; 
    protected int fuerza; 
    protected int poder; 
    protected int defensa; 
    protected String arma; 
    protected String rutaImagen; // Columna VARCHAR en la tabla Personajes (Unidad V) 
 
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
} 