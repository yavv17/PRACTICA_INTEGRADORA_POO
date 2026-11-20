package controlador;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import javax.swing.JOptionPane; 
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
    public void actionPerformed(ActionEvent e) { 
        Object fuente = e.getSource(); 
        try { 
            if (fuente == vista.getBtnSubirAvatar()) { 
                String ruta = vista.abrirSelectorImagen(); 
                if (ruta != null && ValidadorPersonaje.esImagenValida(ruta)) { 
                    this.rutaAvatarActual = ruta; 
                } 
            } else if (fuente == vista.getBtnGuardar()) { 
                String id = vista.getTxtId().getText(); 
                String nombre = vista.getTxtNombre().getText(); 
                int vida = Integer.parseInt(vista.getTxtVida().getText()); 
                // TODO: Extraer fuerza, poder, defensa, arma y atributos específicos de la subclase 
 
                if (!ValidadorPersonaje.esIdValido(id) || !ValidadorPersonaje.esNombreValido(nombre)) { 
                    throw new IllegalArgumentException("Formato de ID (PER-000) o Nombre inválido."); 
                } 
                ValidadorPersonaje.validarEstadisticas(vista,vida, 50, 50, 20); 
 
                PersonajeBase nuevo = crearInstanciaSegunClase(vista.getClaseSelecionada(), id, nombre, vida); 
                modelo.agregar(nuevo); 
 
                vista.refrescarTabla(modelo.obtenerTodos()); 
                JOptionPane.showMessageDialog(vista, "Combatiente registrado exitosamente."); 
            } 
            // TODO: Implementar eventos para Actualizar, Eliminar, Atacar y Ejecutar Habilidad Especial 
        } catch (NumberFormatException ex) { 
            JOptionPane.showMessageDialog(vista, "Error numérico: Verifica que los campos no estén vacíos.", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (IllegalArgumentException ex) { 
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "Validación", 
JOptionPane.WARNING_MESSAGE); 
        } 
    } 
 
    private PersonajeBase crearInstanciaSegunClase(String clase, String id, String nombre, int vida) { 
        // TODO: Implementar switch(clase) para instanciar cualquiera de las 10 subclases con 'rutaAvatarActual' 
        return null; 
    } 
} 