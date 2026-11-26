package com.upiicsa.controlador;

import com.upiicsa.excepciones.ArenaException;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import javax.swing.JOptionPane; 
import com.upiicsa.modelo.*; 
import com.upiicsa.validacion.ValidadorPersonaje; 
import com.upiicsa.vista.ArenaBatallaVista; 
import javax.swing.JComboBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.upiicsa.modelo.ClaseCombatiente;
import java.util.List;

public class ArenaControlador implements ActionListener,ChangeListener { 
    private final GestorArenaModelo modelo; 
    private final ArenaBatallaVista vista; 
    private String rutaAvatarActual = ""; 
    private boolean actualizandoCombos = false;
 
    public ArenaControlador(GestorArenaModelo modelo, ArenaBatallaVista vista) { 
        this.modelo = modelo; 
        this.vista = vista; 
        this.vista.setControlador(this,this); 
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        Object fuente = e.getSource();
        if(fuente == vista.getTabbedPaneArena()){
            int pestania = vista.getTabbedPaneArena().getSelectedIndex();
            try{
                if(pestania == 1){
                    List<PersonajeBase> personajesActuales = modelo.obtenerTodos();
                    ArenaException.validarNumeroPersonajes(modelo.obtenerTodos().size());
                    vista.initLlenarCombosCombatientes(personajesActuales);
                    prepararCombate();
                }
            }catch(ArenaException ex){
                JOptionPane.showMessageDialog(vista, 
              ex.getMessage(), 
               "Validación", 
                JOptionPane.WARNING_MESSAGE); 
                vista.getTabbedPaneArena().setSelectedIndex(0);
            }
        }
    }
 
    @Override 
    public void actionPerformed(ActionEvent e) { 
        Object fuente = e.getSource();
        String id;
        String mensajeExito = "";
        try { 
            if (fuente == vista.getBtnSubirAvatar()) { 
                String ruta = vista.abrirSelectorImagen(); 
                if (ruta != null && ValidadorPersonaje.esImagenValida(ruta)) { 
                    this.rutaAvatarActual = ruta; 
                }else if(ruta == null){
                    this.rutaAvatarActual = "";
                }
            } else if (fuente == vista.getBtnGuardar() || fuente == vista.getBtnActualizar()) {
                String clase = (String) vista.getComboClaseSelecionada().getSelectedItem();
                id = asignarID();
                String nombre = vista.getTxtNombre().getText(); 
                int vida = Integer.parseInt(vista.getTxtVida().getText()); 
                // TODO: Extraer fuerza, poder, defensa, arma y atributos específicos de la subclase 
                int fuerza = Integer.parseInt(vista.getTxtFuerza().getText());
                int poder = Integer.parseInt(vista.getTxtPoder().getText());
                int defensa = Integer.parseInt(vista.getTxtDefensa().getText());
                String arma = vista.getTxtArma().getText();
                String ruta = this.rutaAvatarActual;
                
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
                
                if(null == ruta || ruta.isBlank()){
                    ClaseCombatiente claseCombatiente = ClaseCombatiente.valueOf(
                    clase.toUpperCase()
                    );
                    ruta = claseCombatiente.getRutaImagenAvatarDefault();
                }
                
                if(fuente == vista.getBtnGuardar()){
                    if (!ValidadorPersonaje.esNombreValido(nombre)) { 
                        throw new IllegalArgumentException("Nombre inválido."); 
                    }
                    ValidadorPersonaje.validarEstadisticas(vista,clase,vida,
                        fuerza,poder,defensa,arma,
                        habilidadExtra1,habilidadExtra2,
                        habilidadExtra3,habilidadExtra4); 
                
                    PersonajeBase nuevo = crearInstanciaSegunClase(clase, id, nombre, vida,
                        fuerza,poder,defensa,arma,ruta,
                        habilidadExtra1,habilidadExtra2,
                        habilidadExtra3,habilidadExtra4);
                    modelo.agregar(vista,nuevo);
                    mensajeExito = "Combatiente registrado exitosamente.";
                }else{
                    PersonajeBase actual = modelo.buscarPorId(id);
                    
                    mensajeExito = "Combatiente actualizado exitosamente.";
                }
                JOptionPane.showMessageDialog(vista, mensajeExito);
                vista.refrescarTabla(modelo.obtenerTodos()); 
                vista.limpiarRegistro();
                rutaAvatarActual = "";
            }else if(fuente == vista.getBtnEliminar()){
                id = asignarID();
                mensajeExito = "Combatiente eliminado exitosamente.";
                
                boolean eliminado = modelo.eliminar(id);
                if(eliminado){
                    JOptionPane.showMessageDialog(vista, mensajeExito);
                    vista.refrescarTabla(modelo.obtenerTodos()); 
                    vista.limpiarRegistro();
                    
                }else{
                    JOptionPane.showMessageDialog(vista, 
                    "Error: El ID " + id + " no se encuentra dentro de la lista.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE); 
                }
            }else if(fuente == vista.getBtnAtacarCombatiente1()||
                     fuente == vista.getBtnAtacarCombatiente2()){
                if(fuente == vista.getBtnAtacarCombatiente1()){
                    
                }else{
                    
                }
            }else if(fuente == vista.getBtnHabilidadCombatiente1() || 
                      fuente == vista.getBtnHabilidadCombatiente2()){
                if(fuente == vista.getBtnHabilidadCombatiente1()){
                    
                }else{
                    
                }
            }else if(fuente == vista.getBtnReiniciarCombate()){
                prepararCombate();
            }else if(fuente == vista.getBtnRegistrarBatallaBD()){
                
            }else if(fuente == vista.getComboCombatiente1() || 
                fuente == vista.getComboCombatiente2()){

                if(actualizandoCombos){
                    return;
                }

                actualizandoCombos = true;

                JComboBox<PersonajeBase> comboSeleccionado;
                JComboBox<PersonajeBase> comboOtro;

                if (fuente == vista.getComboCombatiente1()) {
                    comboSeleccionado = vista.getComboCombatiente1();
                    comboOtro = vista.getComboCombatiente2();
                }else{
                    comboSeleccionado = vista.getComboCombatiente2();
                    comboOtro = vista.getComboCombatiente1();
                }

                PersonajeBase seleccionado =
                        (PersonajeBase) comboSeleccionado.getSelectedItem();
                

                vista.llenarComboCombatiente(comboOtro,
                            modelo.obtenerTodos(),
                            seleccionado);
                    
                actualizandoCombos = false;
                
                prepararCombate();
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
    
    private String asignarID(){
        String id = vista.getTxtId().getText();
        if (!ValidadorPersonaje.esIdValido(id)) { 
            throw new IllegalArgumentException("Formato de ID (PER-000)."); 
        }else{
            return id;
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
                        vista.getCheckBoxHabilidadBooleana().isSelected(), 
                        habilidadExtra3);
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
                        vista.getCheckBoxHabilidadBooleana().isSelected());
            case "Saiyan":
                return new Saiyan(id, nombre, vida, fuerza, poder, defensa, arma, rutaImagen, 
                        vista.getCheckBoxHabilidadBooleana().isSelected(), 
                        Integer.parseInt(habilidadExtra1), 
                        Integer.parseInt(habilidadExtra2), 
                        Integer.parseInt(habilidadExtra3));
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
    
    private void prepararCombate(){
        PersonajeBase original1 = 
                (PersonajeBase) vista.getComboCombatiente1().getSelectedItem();
        
        PersonajeBase original2 =
                (PersonajeBase) vista.getComboCombatiente2().getSelectedItem();
        
        if(original1 == null || original2 == null){
            return;
        }
        
        modelo.iniciarCombate(original1, original2);
        vista.actualizarDatosCombate(modelo.getCombatiente1(), 
                modelo.getCombatiente2());
    }

} 