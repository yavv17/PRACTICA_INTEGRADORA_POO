
package com.upiicsa.validacion;

import com.upiicsa.modelo.ClaseCombatiente;
import java.util.regex.Pattern;
import javax.swing.JFrame;

public class ValidadorPersonaje {

    private static final Pattern PATRON_ID =
            Pattern.compile("^PER-\\d{3}$");

    private static final Pattern PATRON_NOMBRE =
            Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-zA-ZáéíóúñÑ09\\s]{2,24}$");

    private static final Pattern PATRON_IMAGEN =
            Pattern.compile("^.+\\.(png|jpg|jpeg)$", Pattern.CASE_INSENSITIVE);

    private static final Pattern PATRON_TEXTO =
            Pattern.compile("^[\\p{L}0-9][\\p{L}0-9\\s.'’-]{1,29}$");

    private static final Pattern PATRON_FRASE =
            Pattern.compile("^[\\p{L}0-9][\\p{L}0-9\\s.,;:!?¿¡'\"()’-]{1,99}$");

    private static final Pattern PATRON_MUNICION =
            Pattern.compile("^Flecha\\s+de\\s+[\\p{L}0-9][\\p{L}0-9\\s-]{1,20}$",
                    Pattern.CASE_INSENSITIVE);

    private static final int MIN_VIDA = 50;
    private static final int MAX_VIDA = 1000;

    private static final int MIN_FUERZA = 10;
    private static final int MAX_FUERZA = 250;

    private static final int MIN_PODER = 10;
    private static final int MAX_PODER = 250;

    private static final int MIN_DEFENSA = 0;
    private static final int MAX_DEFENSA = 150;

    // RANGOS DE ARQUERO Y STAND
    private static final int MIN_RANGO = 1;
    private static final int MAX_RANGO = 10;

    // RANGOS DE BARBARO
    private static final int MIN_ESTAMINA = 0;
    private static final int MAX_ESTAMINA = 100;

    // RANGOS DE RESISTENCIA
    private static final int MIN_RESISTENCIA = 0;
    private static final int MAX_RESISTENCIA = 150;

    // RANGOS DE BARDO
    private static final int MIN_INSPIRACION = 1;
    private static final int MAX_INSPIRACION = 100;

    // RANGOS DE CABALLERO
    private static final int MIN_ARMADURA = 0;
    private static final int MAX_ARMADURA = 150;

    // RANGOS DE NINFA
    private static final int MIN_SEDUCCION = 1;
    private static final int MAX_SEDUCCION = 100;

    private static final int MIN_EXPERIENCIA = 0;
    private static final int MAX_EXPERIENCIA = 10000;

    // RANGOS DE ORCO
    private static final int MIN_COLMILLOS = 1;
    private static final int MAX_COLMILLOS = 20;

    // RANGOS DE POLINENE
    private static final int MIN_POLIMONEDAS = 0;
    private static final int MAX_POLIMONEDAS = 100000;

    private static final int MIN_INTELIGENCIA = 1;
    private static final int MAX_INTELIGENCIA = 100;

    // RANGOS DE SAIYAN
    private static final int MIN_CELULAS_Z = 0;
    private static final int MAX_CELULAS_Z = 10;

    private static final int MIN_ZENKAI = 0;
    private static final int MAX_ZENKAI = 100;

    private static final int MIN_KI = 0;
    private static final int MAX_KI = 1000;

    public static boolean esIdValido(String id) {
        return id != null && PATRON_ID.matcher(id.trim()).matches();
    }

    public static boolean esNombreValido(String nombre) {
        return nombre != null && PATRON_NOMBRE.matcher(nombre.trim()).matches();
    }

    public static boolean esImagenValida(String ruta) {
        return ruta != null && PATRON_IMAGEN.matcher(ruta.trim()).matches();
    }

    public static void validarEstadisticas(JFrame panel,String clase,
            int vida,int fuerza,int poder,int defensa,String arma,
            String habilidadExtra1,String habilidadExtra2,
            String habilidadExtra3,String habilidadExtra4) {

        validarRango(vida, "Vida", MIN_VIDA, MAX_VIDA);
        validarRango(fuerza, "Fuerza", MIN_FUERZA, MAX_FUERZA);
        validarRango(poder, "Poder", MIN_PODER, MAX_PODER);
        validarRango(defensa, "Defensa", MIN_DEFENSA, MAX_DEFENSA);
        validarTexto(arma, "Arma");

        ClaseCombatiente claseE = ClaseCombatiente.valueOf(
            clase.toUpperCase()
        );
        
        switch (claseE) {

            case ARQUERO:
                validarEstadisticasArquero(
                        habilidadExtra1,
                        habilidadExtra2);
                break;

            case BARBARO:
                validarEstadisticasBarbaro(
                        habilidadExtra1,
                        habilidadExtra2,
                        habilidadExtra3);
                break;

            case BARDO:
                validarEstadisticasBardo(
                        habilidadExtra1);
                break;

            case CABALLERO:
                validarEstadisticasCaballero(
                        habilidadExtra1,
                        habilidadExtra2,
                        habilidadExtra3);
                break;

            case GOLEM:
                validarEstadisticasGolem(
                        habilidadExtra1,
                        habilidadExtra2);
                break;

            case NINFA:
                validarEstadisticasNinfa(
                        habilidadExtra1,
                        habilidadExtra2,
                        habilidadExtra3);
                break;

            case ORCO:
                validarEstadisticasOrco(
                        habilidadExtra1,
                        habilidadExtra2,
                        habilidadExtra3,
                        habilidadExtra4);
                break;

            case POLINENE:
                validarEstadisticasPolinene(
                        habilidadExtra1,
                        habilidadExtra2);
                break;

            case SAIYAN:
                validarEstadisticasSaiyan(
                        habilidadExtra1,
                        habilidadExtra2,
                        habilidadExtra3);
                break;

            case STAND:
                validarEstadisticasStand(
                        habilidadExtra1,
                        habilidadExtra2,
                        habilidadExtra3,
                        habilidadExtra4);
                break;
        }
    }

    private static void validarEstadisticasArquero(
            String rango, String municion) {

        validarRango(rango, "Rango", MIN_RANGO, MAX_RANGO);
        validarPatron(municion, "Munición", PATRON_MUNICION);
    }

    private static void validarEstadisticasBarbaro(
            String estamina, String resistencia, String armaAdicional) {

        validarRango(estamina, "Estamina", MIN_ESTAMINA, MAX_ESTAMINA);
        validarRango(resistencia, "Resistencia", MIN_RESISTENCIA, MAX_RESISTENCIA);
        validarTexto(armaAdicional, "Arma Adicional");
    }

    private static void validarEstadisticasBardo(String inspiracion) {

        validarRango(inspiracion, "Inspiración",
                MIN_INSPIRACION, MAX_INSPIRACION);
    }

    private static void validarEstadisticasCaballero(
            String armadura,
            String resistencia,
            String criaturaApoyo) {

        validarRango(armadura, "Armadura", MIN_ARMADURA, MAX_ARMADURA);
        validarRango(resistencia, "Resistencia",
                MIN_RESISTENCIA, MAX_RESISTENCIA);

        validarTexto(criaturaApoyo, "Criatura de Apoyo");
    }

    private static void validarEstadisticasGolem(
            String resistencia, String elemento) {

        validarRango(resistencia, "Resistencia",
                MIN_RESISTENCIA, MAX_RESISTENCIA);

        validarTexto(elemento, "Elemento");
    }

    private static void validarEstadisticasNinfa(
            String elemento, String nivelSeduccion, String experiencia) {

        validarTexto(elemento, "Elemento");

        validarRango(nivelSeduccion, "Nivel de Seducción",
                MIN_SEDUCCION, MAX_SEDUCCION);

        validarRango(experiencia, "Experiencia",
                MIN_EXPERIENCIA, MAX_EXPERIENCIA);
    }

    private static void validarEstadisticasOrco(
            String colmillos, String tamano, String color, String aditamento) {

        validarRango(colmillos, "Colmillos",
                MIN_COLMILLOS, MAX_COLMILLOS);

        validarTexto(tamano, "Tamaño");
        validarTexto(color, "Color");
        validarTexto(aditamento, "Aditamento");
    }

    private static void validarEstadisticasPolinene(
            String cantidadPolimonedas, String inteligencia) {

        validarRango(cantidadPolimonedas, "Cantidad Polimonedas",
                MIN_POLIMONEDAS, MAX_POLIMONEDAS);

        validarRango(inteligencia, "Inteligencia",
                MIN_INTELIGENCIA, MAX_INTELIGENCIA);
    }

    private static void validarEstadisticasSaiyan(
            String celulasZ, String zenkai, String ki) {

        validarRango(celulasZ, "Células Z",
                MIN_CELULAS_Z, MAX_CELULAS_Z);

        validarRango(zenkai, "Zenkai",
                MIN_ZENKAI, MAX_ZENKAI);

        validarRango(ki, "Ki", MIN_KI, MAX_KI);
    }

    private static void validarEstadisticasStand(
            String rango, String tipo, String frase, String usuario) {

        validarRango(rango, "Rango", MIN_RANGO, MAX_RANGO);

        validarTexto(tipo, "Tipo");
        validarFrase(frase, "Frase");
        validarTexto(usuario, "Usuario");
    }

    private static void validarRango(
            String valor, String atributo, int minimo, int maximo) {

        int numero = Integer.parseInt(valor.trim());

        if (numero < minimo || numero > maximo) {
            throw new IllegalArgumentException(
                    atributo + " va en un rango de " + minimo + "-" + maximo);
        }
    }
    
    private static void validarRango(
        int numero, String atributo, int minimo, int maximo) {

        if (numero < minimo || numero > maximo) {
            throw new IllegalArgumentException(
                    atributo + " va en un rango de " + minimo + "-" + maximo);
        }
    }

    private static void validarTexto(String valor, String atributo) {
        validarPatron(valor, atributo, PATRON_TEXTO);
    }

    private static void validarFrase(String valor, String atributo) {
        validarPatron(valor, atributo, PATRON_FRASE);
    }

    private static void validarPatron(
            String valor, String atributo, Pattern patron) {

        if (valor == null || !patron.matcher(valor.trim()).matches()) {
            throw new IllegalArgumentException(
                    atributo + " no tiene un formato válido o excede la longitud permitida.");
        }
    }
}