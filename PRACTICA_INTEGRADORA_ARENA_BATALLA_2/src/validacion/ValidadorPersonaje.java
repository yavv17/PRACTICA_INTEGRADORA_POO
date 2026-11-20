/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package validacion;
import java.util.regex.Pattern;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ValidadorPersonaje { 
    private static final Pattern PATRON_ID = Pattern.compile("^PER-\\d{3}$"); 
    private static final Pattern PATRON_NOMBRE = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-zA-ZáéíóúñÑ09\\s]{2,24}$"); 
    private static final Pattern PATRON_IMAGEN = Pattern.compile("^.+\\.(png|jpg|jpeg)$", Pattern.CASE_INSENSITIVE); 
 
    public static boolean esIdValido(String id) { 
        return id != null && PATRON_ID.matcher(id.trim()).matches(); 
    } 
 
    public static boolean esNombreValido(String nombre) { 
        return nombre != null && PATRON_NOMBRE.matcher(nombre.trim()).matches(); 
    } 
 
    public static boolean esImagenValida(String ruta) { 
        return ruta != null && PATRON_IMAGEN.matcher(ruta.trim()).matches(); 
    } 
 
    public static void validarEstadisticas(int vida, int fuerza, int poder, int defensa) { 
        // TODO: Validar rangos (vida: 50-1000, fuerza/poder: 10-250, defensa: 0-150). 
        // Si algún valor está fuera de rango, lanzar IllegalArgumentException con un mensaje claro. 
    } 
} 