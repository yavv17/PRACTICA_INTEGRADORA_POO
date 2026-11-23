package controlador;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import javax.swing.JOptionPane; 
import javax.swing.JComboBox;
import modelo.*; 
import validacion.ValidadorPersonaje; 
import vista.ArenaBatallaVista; 

public class ArenaControlador implements ActionListener { 
    private final GestorArenaModelo modelo; 
    private final ArenaBatallaVista vista; 
    private String rutaAvatarActual = ""; 
 
    public ArenaControlador(GestorArenaModelo modelo, ArenaBatallaVista vista) { 
        this.modelo = modelo; 
        this.vista = vista; 
        this.vista.setControlador(this); 
    } 
 
    @Override 
    public void actionPerformed(ActionEvent evento) { 
        Object componentePresionado = evento.getSource();
        try { 
            if (componentePresionado == vista.getBtnSubirAvatar()) { 
                String rutaImagenSeleccionada = vista.abrirSelectorImagen(); 
                if (rutaImagenSeleccionada != null && ValidadorPersonaje.esImagenValida(rutaImagenSeleccionada)) { 
                    this.rutaAvatarActual = rutaImagenSeleccionada; 
                }
            } else if (componentePresionado == vista.getBtnGuardar()) {
                String id = vista.getTxtId().getText();
                String nombre = vista.getTxtNombre().getText(); 
                int vida = Integer.parseInt(vista.getTxtVida().getText()); 
                // TODO: Extraer fuerza, poder, defensa, arma y atributos específicos de la subclase 
                int fuerza = Integer.parseInt(vista.getTxtFuerza().getText());
                int poder = Integer.parseInt(vista.getTxtPoder().getText());
                int defensa = Integer.parseInt(vista.getTxtDefensa().getText());
                String arma = vista.getTxtArma().getText();
                String atributoEspecial1 = "Vacio";
                String atributoEspecial2 = "Vacio";
                String atributoEspecial3 = "Vacio";
                String atributoEspecial4 = "Vacio";
                if(vista.getTxtHabilidadExtra1().isVisible()){
                    atributoEspecial1 = vista.getTxtHabilidadExtra1().getText();
                }
                if(vista.getTxtHabilidadExtra2().isVisible()){
                    atributoEspecial2 = vista.getTxtHabilidadExtra2().getText();
                }
                if(vista.getTxtHabilidadExtra3().isVisible()){
                    atributoEspecial3 = vista.getTxtHabilidadExtra3().getText();
                }
                if(vista.getTxtHabilidadExtra4().isVisible()){
                    atributoEspecial4 = vista.getTxtHabilidadExtra4().getText();
                }
                
                if (!ValidadorPersonaje.esIdValido(id) || !ValidadorPersonaje.esNombreValido(nombre)) { 
                    throw new IllegalArgumentException("Formato de ID (PER-000) o Nombre inválido."); 
                } 
                ValidadorPersonaje.validarEstadisticas(vista,vida, fuerza, poder, defensa); 
 
                PersonajeBase nuevoPersonaje = crearInstanciaSegunClase(vista.getClaseSelecionada(), id, nombre, vida,
                        fuerza,poder,defensa,arma,this.rutaAvatarActual,atributoEspecial1,atributoEspecial2,
                        atributoEspecial3,atributoEspecial4); 
                modelo.agregar(vista,nuevoPersonaje); 
                vista.refrescarTabla(modelo.obtenerTodos()); 
                vista.actualizarCombosCombatientes(modelo.obtenerTodos());
                JOptionPane.showMessageDialog(vista, "Combatiente registrado exitosamente."); 
            } else if (componentePresionado == vista.getBtnActualizar()) {
                String id = vista.getTxtId().getText();
                 String nombre = vista.getTxtNombre().getText();
                    int vida = Integer.parseInt(vista.getTxtVida().getText());
                    int fuerza = Integer.parseInt(vista.getTxtFuerza().getText());
                    int poder = Integer.parseInt(vista.getTxtPoder().getText());
                    int defensa = Integer.parseInt(vista.getTxtDefensa().getText());
                    String arma = vista.getTxtArma().getText();

                    String atributoEspecial1 = vista.getTxtHabilidadExtra1().isVisible()
                            ? vista.getTxtHabilidadExtra1().getText() : "Vacio";
                    String atributoEspecial2 = vista.getTxtHabilidadExtra2().isVisible()
                            ? vista.getTxtHabilidadExtra2().getText() : "Vacio";
                    String atributoEspecial3 = vista.getTxtHabilidadExtra3().isVisible()
                            ? vista.getTxtHabilidadExtra3().getText() : "Vacio";
                    String atributoEspecial4 = vista.getTxtHabilidadExtra4().isVisible()
                            ? vista.getTxtHabilidadExtra4().getText() : "Vacio";

                    if (!ValidadorPersonaje.esIdValido(id) || !ValidadorPersonaje.esNombreValido(nombre)) {
                        throw new IllegalArgumentException("Formato de ID (PER-000) o Nombre inválido.");
                    }
                    ValidadorPersonaje.validarEstadisticas(vista, vida, fuerza, poder, defensa);
                
                    PersonajeBase existente = modelo.buscarPorId(id);
                    if (existente == null) {
                        JOptionPane.showMessageDialog(vista,
                                "Error: No se encontró un combatiente con ID " + id + ".",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                String rutaAvatar = this.rutaAvatarActual.isEmpty()
                        ? existente.getRutaImagen()
                        : this.rutaAvatarActual;
            
                PersonajeBase actualizado = crearInstanciaSegunClase(
                        vista.getClaseSelecionada(), id, nombre, vida, fuerza, poder, defensa,
                        arma, rutaAvatar, atributoEspecial1, atributoEspecial2,
                        atributoEspecial3, atributoEspecial4);
            
                if (modelo.actualizar(actualizado)) {
                    vista.refrescarTabla(modelo.obtenerTodos());
                    vista.actualizarCombosCombatientes(modelo.obtenerTodos());
                    JOptionPane.showMessageDialog(vista, "Combatiente actualizado exitosamente.");
                }
            
            }  
            else if(componentePresionado == vista.getBtnEliminar()){
                String id = vista.getTxtId().getText();
                
                if (!ValidadorPersonaje.esIdValido(id)) { 
                    throw new IllegalArgumentException("Formato de ID (PER-000)."); 
                }
                
                boolean personajeEliminado = modelo.eliminar(id);
                if(personajeEliminado){
                    JOptionPane.showMessageDialog(vista, "Combatiente eliminado exitosamente."); 
                }else{
                    JOptionPane.showMessageDialog(vista, 
                    "Error: El ID " + id + " no se encuentra dentro de la lista.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE); 
                }
            }
            // TODO: Implementar eventos para Actualizar, Eliminar, Atacar y Ejecutar Habilidad Especial 
        } catch (NumberFormatException ex) { 
            JOptionPane.showMessageDialog(vista, 
                    "Error numérico: Verifica que los campos no estén vacíos.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE); 
        } catch (IllegalArgumentException ex) { 
            JOptionPane.showMessageDialog(vista, 
                    ex.getMessage(), 
                    "Validación", 
                JOptionPane.WARNING_MESSAGE); 
        } 
    } 
 
    private PersonajeBase crearInstanciaSegunClase(String tipoPersonaje, String id, String nombre, int vida,
            int fuerza, int poder, int defensa, String arma,String rutaImagen, String atributoEspecial1,
            String atributoEspecial2,String atributoEspecial3,String atributoEspecial4) { 
        // TODO: Implementar switch(clase) para instanciar cualquiera de las 10 subclases con 'rutaAvatarActual'
        switch(tipoPersonaje){
            case "Arquero":
                int rango = Integer.parseInt(atributoEspecial1);
                String municion = atributoEspecial2;
                ValidadorPersonaje.validarArquero(rango, municion);
                return new Arquero(id, nombre, vida, fuerza, poder, defensa,
                        arma, rutaImagen, rango, municion);
            case "Barbaro":
               int estamina = Integer.parseInt(atributoEspecial1);
               int resistenciaBarbaro = Integer.parseInt(atributoEspecial2);
               String armaAdicional = atributoEspecial3;
               ValidadorPersonaje.validarBarbaro(
               estamina, resistenciaBarbaro, armaAdicional);
                return new Barbaro(id, nombre, vida, fuerza, poder, defensa,
                         arma, rutaImagen, estamina, resistenciaBarbaro, armaAdicional);
           case "Bardo":
               int inspiracion = Integer.parseInt(atributoEspecial1);
               ValidadorPersonaje.validarBardo(inspiracion);
                return new Bardo(id, nombre, vida, fuerza, poder, defensa,
                         arma, rutaImagen, inspiracion);
            case "Caballero":
                int armadura = Integer.parseInt(atributoEspecial1);
                int resistencia = Integer.parseInt(atributoEspecial2);
                boolean escudo = ValidadorPersonaje.convertirBooleano(atributoEspecial3, "Escudo");
                ValidadorPersonaje.validarCaballero(armadura, resistencia, escudo, atributoEspecial4);
                return new Caballero(id, nombre, vida, fuerza, poder, defensa,
                           arma, rutaImagen, armadura, resistencia, escudo, atributoEspecial4);
            case "Golem":
                int resistenciaGolem = Integer.parseInt(atributoEspecial1);
                String elementoGolem = atributoEspecial2;
                ValidadorPersonaje.validarGolem(
                resistenciaGolem, elementoGolem);
                return new Golem(id, nombre, vida, fuerza, poder, defensa,
                           arma, rutaImagen, resistenciaGolem, elementoGolem);
            case "Ninfa":
               String elementoNinfa = atributoEspecial1;
               int nivelSeduccion = Integer.parseInt(atributoEspecial2);
               int experiencia = Integer.parseInt(atributoEspecial3);
               ValidadorPersonaje.validarNinfa(
               elementoNinfa, nivelSeduccion, experiencia);
                return new Ninfa(id, nombre, vida, fuerza, poder, defensa,
                           arma, rutaImagen, elementoNinfa, nivelSeduccion, experiencia);
            case "Orco":
               int colmillos = Integer.parseInt(atributoEspecial1);
               String tamano = atributoEspecial2;
               String color = atributoEspecial3;
               String aditamento = atributoEspecial4;
               ValidadorPersonaje.validarOrco(
               colmillos, tamano, color, aditamento);
                return new Orco(id, nombre, vida, fuerza, poder, defensa,
                          arma, rutaImagen, colmillos, tamano, color, aditamento);
           case "Polinene":
                int cantidadPolimonedas = Integer.parseInt(atributoEspecial1);
                int inteligencia = Integer.parseInt(atributoEspecial2);
                boolean tamear = ValidadorPersonaje.convertirBooleano(atributoEspecial3, "Tamear");
                ValidadorPersonaje.validarPolinene(
                cantidadPolimonedas, inteligencia, tamear);
                 return new Polinene(id, nombre, vida, fuerza, poder, defensa,
                            arma, rutaImagen, cantidadPolimonedas, inteligencia, tamear);
            case "Saiyan":
                boolean cola = ValidadorPersonaje.convertirBooleano(atributoEspecial1, "Cola");
                int celulasZ = Integer.parseInt(atributoEspecial2);
                int zenkai = Integer.parseInt(atributoEspecial3);
                int ki = Integer.parseInt(atributoEspecial4);
                ValidadorPersonaje.validarSaiyan(
                cola, celulasZ, zenkai, ki);
                 return new Saiyan(id, nombre, vida, fuerza, poder, defensa,
                            arma, rutaImagen, cola, celulasZ, zenkai, ki);
            case "Stand":
                int rangoStand = Integer.parseInt(atributoEspecial1);
                String tipo = atributoEspecial2;
                String frase = atributoEspecial3;
                String usuario = atributoEspecial4;
                ValidadorPersonaje.validarStand(
                rangoStand, tipo, frase, usuario);
                  return new Stand(id, nombre, vida, fuerza, poder, defensa,
                             arma, rutaImagen, rangoStand, tipo, frase, usuario);
            default:
                 throw new IllegalArgumentException("Clase de combatiente no válida: " + tipoPersonaje);
        }
    } 
    private PersonajeBase obtenerCombatienteDesdeCombo(JComboBox<String> combo) {
    String seleccion = (String) combo.getSelectedItem();

    if (seleccion == null || seleccion.startsWith("Selecciona")) {
        throw new IllegalArgumentException("Selecciona los dos combatientes.");
    }

    String id = seleccion.split(" - ", 2)[0];
    PersonajeBase personaje = modelo.buscarPorId(id);

    if (personaje == null) {
        throw new IllegalArgumentException("No se encontró el combatiente seleccionado.");
    }

    return personaje;
  }

   private void validarTurnoCombate(PersonajeBase atacante, PersonajeBase objetivo) {
    if (atacante.getId().equals(objetivo.getId())) {
        throw new IllegalArgumentException("Selecciona dos combatientes diferentes.");
    }

    if (atacante.getVida() <= 0) {
        throw new IllegalArgumentException("El atacante ya fue derrotado.");
    }

    if (objetivo.getVida() <= 0) {
        throw new IllegalArgumentException("El objetivo ya fue derrotado.");
    }
  }
} 
