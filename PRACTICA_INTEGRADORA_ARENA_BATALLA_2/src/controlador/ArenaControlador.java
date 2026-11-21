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
                int fuerza = Integer.parseInt(vista.getTxtFuerza().getText());
                int poder = Integer.parseInt(vista.getTxtPoder().getText());
                int defensa = Integer.parseInt(vista.getTxtDefensa().getText());
                String arma = vista.getTxtArma().getText();
                String habilidadExtra1 = "Vacio";
                String habilidadExtra2 = "Vacio";
                String habilidadExtra3 = "Vacio";
                String habilidadExtra4 = "Vacio";
                if(vista.getTxtHabilidadExtra1().isVisible()){
                    habilidadExtra1 = vista.getTxtHabilidadExtra1().getText();
                }
                if(vista.getTxtHabilidadExtra2().isVisible()){
                    habilidadExtra2 = vista.getTxtHabilidadExtra2().getText();
                }
                if(vista.getTxtHabilidadExtra3().isVisible()){
                    habilidadExtra3 = vista.getTxtHabilidadExtra3().getText();
                }
                if(vista.getTxtHabilidadExtra4().isVisible()){
                    habilidadExtra4 = vista.getTxtHabilidadExtra4().getText();
                }
                
                if (!ValidadorPersonaje.esIdValido(id) || !ValidadorPersonaje.esNombreValido(nombre)) { 
                    throw new IllegalArgumentException("Formato de ID (PER-000) o Nombre inválido."); 
                } 
                ValidadorPersonaje.validarEstadisticas(vista,vida, 50, 50, 20); 
 
                PersonajeBase nuevo = crearInstanciaSegunClase(vista.getClaseSelecionada(), id, nombre, vida,
                        fuerza,poder,defensa,arma,this.rutaAvatarActual,habilidadExtra1,habilidadExtra2,
                        habilidadExtra3,habilidadExtra4); 
                modelo.agregar(vista,nuevo); 
 
                vista.refrescarTabla(modelo.obtenerTodos()); 
                JOptionPane.showMessageDialog(vista, "Combatiente registrado exitosamente."); 
            } else if(fuente == vista.getBtnActualizar()){
                
            }else if(fuente == vista.getBtnEliminar()){
                String id = vista.getTxtId().getText();
                
                if (!ValidadorPersonaje.esIdValido(id)) { 
                    throw new IllegalArgumentException("Formato de ID (PER-000)."); 
                }
                
                boolean eliminado = modelo.eliminar(id);
                if(eliminado){
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
 
    private PersonajeBase crearInstanciaSegunClase(String clase, String id, String nombre, int vida,
            int fuerza, int poder, int defensa, String arma,String rutaImagen, String habilidadExtra1,
            String habilidadExtra2,String habilidadExtra3,String habilidadExtra4) { 
        // TODO: Implementar switch(clase) para instanciar cualquiera de las 10 subclases con 'rutaAvatarActual'
        switch(clase){
            case "Arquero": 
                return new Arquero(id,nombre,vida,fuerza,poder,defensa,arma,rutaImagen,
                        Integer.parseInt(habilidadExtra1),habilidadExtra2);
            case "Barbaro": 
                return new Barbaro(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen,
                        Integer.parseInt(habilidadExtra1), 
                        Integer.parseInt(habilidadExtra2), 
                        habilidadExtra3);
            case "Bardo":
                return new Bardo(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen, 
                        Integer.parseInt(habilidadExtra1));
            case "Caballero":
                return new Caballero(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen, 
                        Integer.parseInt(habilidadExtra1),
                        Integer.parseInt(habilidadExtra2), 
                        true, //Corregir
                        habilidadExtra4);
            case "Golem":
                return new Golem(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen, 
                        Integer.parseInt(habilidadExtra1), 
                        habilidadExtra2);
            case "Ninfa":
                return new Ninfa(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen, 
                        habilidadExtra1, 
                        Integer.parseInt(habilidadExtra2), 
                        Integer.parseInt(habilidadExtra3));
            case "Orco":
                return new Orco(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen, 
                        Integer.parseInt(habilidadExtra1), 
                        habilidadExtra2, 
                        habilidadExtra3, 
                        habilidadExtra4);
            case "Polinene":
                return new Polinene(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen, 
                        Integer.parseInt(habilidadExtra1), 
                        Integer.parseInt(habilidadExtra2),
                        true); //Corregir
            case "Saiyan":
                return new Saiyan(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen, 
                        true, //Corregir
                        Integer.parseInt(habilidadExtra2), 
                        Integer.parseInt(habilidadExtra3), 
                        Integer.parseInt(habilidadExtra4));
            case "Stand":
                return new Stand(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen, 
                        Integer.parseInt(habilidadExtra1), 
                        habilidadExtra2, 
                        habilidadExtra3, 
                        habilidadExtra4);
            default:
                break;
        }
        return null; 
    } 
} 