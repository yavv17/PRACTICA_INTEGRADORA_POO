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
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JLabel;

public class ArenaControlador implements ActionListener,ChangeListener { 
    private final GestorArenaModelo modelo; 
    private final ArenaBatallaVista vista; 
    private String rutaAvatarActual = ""; 
    private boolean actualizandoCombos = false;
    private boolean turnoCombatiente1 = true;
    private boolean combateEnCurso = false;
    private int nTurno = 0;
 
    public ArenaControlador(GestorArenaModelo modelo, ArenaBatallaVista vista) { 
        this.modelo = modelo; 
        this.vista = vista; 
        this.vista.setControlador(this,this);
        cargarPersonajes();
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
                    modelo.agregar(nuevo);
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
            }else if(fuente == vista.getBtnAtacarCombatiente1()){
                ejecutarAccionCombate(true, true);
            }else if(fuente == vista.getBtnAtacarCombatiente2()){
                ejecutarAccionCombate(true, false);
            }else if(fuente == vista.getBtnHabilidadCombatiente1()){
                ejecutarAccionCombate(false, true);
            }else if(fuente == vista.getBtnHabilidadCombatiente2()){
                ejecutarAccionCombate(false, false);
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
        }catch(SQLException ex){
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
    
    private void prepararCombate() {
        PersonajeBase original1 =
                (PersonajeBase) vista.getComboCombatiente1().getSelectedItem();

        PersonajeBase original2 =
                (PersonajeBase) vista.getComboCombatiente2().getSelectedItem();

        if (original1 == null || original2 == null) {
            combateEnCurso = false;

            actualizarAccionesCombatienteTurno(
                    vista.getLblCombatienteTurno1(),
                    vista.getLblCombatienteTurno2());

            return;
        }

        modelo.iniciarCombate(original1, original2);

        turnoCombatiente1 = true;
        combateEnCurso = true;
        nTurno = 0;

        PersonajeBase combatiente1 = modelo.getCombatiente1();
        PersonajeBase combatiente2 = modelo.getCombatiente2();

        vista.actualizarDatosCombate(combatiente1, combatiente2);
        vista.actualizarEstadisticasCombate(combatiente1, combatiente2);

        actualizarAccionesCombatienteTurno(
                vista.getLblCombatienteTurno1(),
                vista.getLblCombatienteTurno2());
    }
    
    private void actualizarAccionesCombatienteTurno(
        JLabel lblCombatienteTurno1,
        JLabel lblCombatienteTurno2) {

        if (!combateEnCurso) {
            vista.bloquearBtnsCombatientes();

            lblCombatienteTurno1.setText("Combate finalizado");
            lblCombatienteTurno2.setText("Combate finalizado");

            lblCombatienteTurno1.setForeground(Color.BLACK);
            lblCombatienteTurno2.setForeground(Color.BLACK);

            return;
        }

        if (turnoCombatiente1) {
            vista.getBtnAtacarCombatiente1().setEnabled(true);
            vista.getBtnHabilidadCombatiente1().setEnabled(true);

            vista.getBtnAtacarCombatiente2().setEnabled(false);
            vista.getBtnHabilidadCombatiente2().setEnabled(false);

            lblCombatienteTurno1.setText("Selecciona ataque");
            lblCombatienteTurno1.setForeground(Color.GREEN);

            lblCombatienteTurno2.setText("Esperando ataque");
            lblCombatienteTurno2.setForeground(Color.BLACK);

        } else {
            vista.getBtnAtacarCombatiente1().setEnabled(false);
            vista.getBtnHabilidadCombatiente1().setEnabled(false);

            vista.getBtnAtacarCombatiente2().setEnabled(true);
            vista.getBtnHabilidadCombatiente2().setEnabled(true);

            lblCombatienteTurno1.setText("Esperando ataque");
            lblCombatienteTurno1.setForeground(Color.BLACK);

            lblCombatienteTurno2.setText("Selecciona ataque");
            lblCombatienteTurno2.setForeground(Color.GREEN);
        }
    }
    
    private void ejecutarAccionCombate(
            boolean ataqueNormal, boolean esCombatiente1) {

        if (!combateEnCurso) {
            return;
        }

        if (esCombatiente1 != turnoCombatiente1) {
            return;
        }

        PersonajeBase combatiente1 = modelo.getCombatiente1();
        PersonajeBase combatiente2 = modelo.getCombatiente2();

        if (combatiente1 == null || combatiente2 == null) {
            return;
        }

        String resultado;

        nTurno++;

        if (esCombatiente1) {
            if (ataqueNormal) {
                resultado = combatiente1.ejecutarAtaque(combatiente2);
            } else {
                resultado = combatiente1.ejecutarHabilidadEspecial(combatiente2);
            }
        } else {
            if (ataqueNormal) {
                resultado = combatiente2.ejecutarAtaque(combatiente1);
            } else {
                resultado = combatiente2.ejecutarHabilidadEspecial(combatiente1);
            }
        }

        vista.actualizarBitacora("[Turno " + nTurno + "] : " + resultado);


        vista.actualizarEstadisticasCombate(combatiente1, combatiente2);

        if (verificarEstadoCombate()) {
            return;
        }

        turnoCombatiente1 = !turnoCombatiente1;

        actualizarAccionesCombatienteTurno(
                vista.getLblCombatienteTurno1(),
                vista.getLblCombatienteTurno2());
    }
    
    private boolean verificarEstadoCombate() {
       PersonajeBase combatiente1 = modelo.getCombatiente1();
       PersonajeBase combatiente2 = modelo.getCombatiente2();

       if (combatiente1 == null || combatiente2 == null) {
           combateEnCurso = false;
           return true;
       }

       boolean muerto1 = combatiente1.getVida() <= 0;
       boolean muerto2 = combatiente2.getVida() <= 0;

       if (muerto1 || muerto2) {

           combateEnCurso = false;

           actualizarAccionesCombatienteTurno(
                   vista.getLblCombatienteTurno1(),
                   vista.getLblCombatienteTurno2());

           String mensaje;

           if (muerto1 && muerto2) {
               mensaje = "¡El combate terminó en empate!";
           } else if (muerto1) {
               mensaje = "¡" + combatiente2.getNombre()
                       + " ha ganado el combate!";
           } else {
               mensaje = "¡" + combatiente1.getNombre()
                       + " ha ganado el combate!";
           }

           vista.actualizarBitacora("[Turno " + nTurno + "] : " + mensaje);

           JOptionPane.showMessageDialog(
                   vista,
                   mensaje,
                   "Combate finalizado",
                   JOptionPane.INFORMATION_MESSAGE);

           return true;
       }

       return false;
   }
    
    private void cargarPersonajes() {
        try {
            modelo.cargarRoster();
            List<PersonajeBase> personajes = modelo.obtenerTodos();

            vista.refrescarTabla(personajes);

        }catch(SQLException e){
                JOptionPane.showMessageDialog(
                vista,
                "Error al cargar los personajes: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }catch (Exception e) {
            JOptionPane.showMessageDialog(
                vista,
                "Error al cargar los personajes: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
} 