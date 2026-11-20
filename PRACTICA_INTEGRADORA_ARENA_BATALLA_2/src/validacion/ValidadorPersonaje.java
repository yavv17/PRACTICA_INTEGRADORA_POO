package validacion;
import java.util.regex.Pattern;
import javax.swing.JFrame;

public class ValidadorPersonaje { 
    private static final Pattern PATRON_ID = Pattern.compile("^PER-\\d{3}$"); 
    private static final Pattern PATRON_NOMBRE = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-zA-ZáéíóúñÑ09\\s]{2,24}$"); 
    private static final Pattern PATRON_IMAGEN = Pattern.compile("^.+\\.(png|jpg|jpeg)$", Pattern.CASE_INSENSITIVE); 
 
    private static final int MIN_VIDA = 50;
    private static final int MAX_VIDA = 100;
    
    private static final int MIN_FUERZA = 10;
    private static final int MAX_FUERZA = 250;
    
    private static final int MIN_PODER = 10;
    private static final int MAX_PODER = 250;
    
    private static final int MIN_DEFENSA = 0;
    private static final int MAX_DEFENSA = 150;
    
    public static boolean esIdValido(String id) { 
        return id != null && PATRON_ID.matcher(id.trim()).matches(); 
    } 
 
    public static boolean esNombreValido(String nombre) { 
        return nombre != null && PATRON_NOMBRE.matcher(nombre.trim()).matches(); 
    } 
 
    public static boolean esImagenValida(String ruta) { 
        return ruta != null && PATRON_IMAGEN.matcher(ruta.trim()).matches(); 
    } 
 
    public static void validarEstadisticas(JFrame panel,int vida, int fuerza, int poder, int defensa) { 
        // TODO: Validar rangos (vida: 50-1000, fuerza/poder: 10-250, defensa: 0-150). 
        // Si algún valor está fuera de rango, lanzar IllegalArgumentException con un mensaje claro. 
        try{
            if(vida < MIN_VIDA || vida > MAX_VIDA){
                throw new IllegalArgumentException("La vida va en un rango de " + MIN_VIDA + "-"+ MAX_VIDA);
            }   
            if(fuerza < MIN_FUERZA || fuerza > MAX_FUERZA){
                throw new IllegalArgumentException("La fuerza va en un rango de " + MIN_FUERZA + "-"+ MAX_FUERZA);
            }
            if(poder < MIN_PODER || poder > MAX_PODER){
                throw new IllegalArgumentException("El poder va en un rango de " + MIN_PODER + "-"+ MAX_PODER);
            }
            if(defensa < MIN_DEFENSA || defensa > MAX_DEFENSA){
                throw new IllegalArgumentException("La defensa va en un rango de " + MIN_DEFENSA + "-"+ MAX_DEFENSA);
            }
        }catch(IllegalArgumentException exception){
                javax.swing.JOptionPane.showMessageDialog(panel,
                    exception.getMessage(),
                    "Dato Fuera de Rango",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    } 
} 