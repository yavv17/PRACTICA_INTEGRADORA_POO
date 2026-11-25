package vista;

import javax.swing.*; 
import javax.swing.filechooser.FileNameExtensionFilter; 
import javax.swing.table.DefaultTableModel; 
import java.awt.*; 
import java.awt.event.ActionListener; 
import java.awt.event.KeyAdapter; 
import java.awt.event.KeyEvent; 
import java.io.File; 
import java.util.ArrayList;
import java.util.List; 
import modelo.ClaseCombatiente;
import modelo.PersonajeBase; 

public final class ArenaBatallaVista extends javax.swing.JFrame {
    
    public static final String RUTA_IMAGEN_VERSUS =  "resources/imagenes/Image_Versus.png";
    private DefaultTableModel modeloTabla; 
    private List<JLabel> labelsExtra = new ArrayList<>();
    private List<JTextField> camposExtra = new ArrayList<>();
    
 
    public String abrirSelectorImagen() { 
        JFileChooser chooser = new JFileChooser(); 
        chooser.setFileFilter(new FileNameExtensionFilter("Imágenes (PNG, JPG)", "png", "jpg", 
"jpeg")); 
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
            File archivo = chooser.getSelectedFile(); 
            mostrarAvatarEnLabel(lblImageAvatarCombatiente, archivo.getAbsolutePath()); 
            return archivo.getAbsolutePath(); 
        } 
        return null; 
    } 
 
    public void mostrarAvatarEnLabel(JLabel destino, String rutaImagen) { 
        ImageIcon icono = new ImageIcon(rutaImagen); 
        Image escalada = icono.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH); 
        destino.setText("");
        destino.setIcon(new ImageIcon(escalada)); 
    } 
 
    // Validación preventiva en Vista (Tema 4.2.2) 
    public void restringirSoloNumeros(JTextField campo) { 
        campo.addKeyListener(new KeyAdapter() { 
            @Override 
            public void keyTyped(KeyEvent e) { 
                if (!Character.isDigit(e.getKeyChar())) { 
                    e.consume(); 
                } 
            } 
        }); 
    }
 
    // Despliegue de resultados por Modelo (Tema 4.3) 
    public void refrescarTabla(List<PersonajeBase> lista) { 
        modeloTabla.setRowCount(0); 
        for (PersonajeBase p : lista) { 
            // TODO: Construir el arreglo Object[] fila usando los getters polimórficos de 'p' 
            // y agregarlo con modeloTabla.addRow(fila); 
            
            Object[] fila = {
                p.getId(),
                p.getNombre(),
                p.getTipoClase(),
                p.getVida(),
                p.getFuerza(),
                p.getDefensa(),
                p.ejecutarHabilidadEspecial(p)
            };
            
            modeloTabla.addRow(fila);
        } 
        
        System.out.println("Personajes recibidos: " + lista.size());
        System.out.println("Filas de la tabla: " + modeloTabla.getRowCount());
        
    } 
 
    public void setControlador(ActionListener listener) { 
        // TODO: Registrar 'listener' en todos los botones (Guardar, Actualizar, Eliminar, Subir Avatar, Atacar, Especial);
        BtnGuardar.addActionListener(listener);
        BtnActualizar.addActionListener(listener);
        BtnEliminar.addActionListener(listener);
        BtnSubirAvatar.addActionListener(listener);
    } 
    
    public ArenaBatallaVista() {
        initComponents();
        llenarListasExtra();
        llenarComboClases();
        actualizarElementosHabilidadExtra();
        modeloTabla = (DefaultTableModel) TableRegistroCombatientes.getModel();
        mostrarAvatarEnLabel(lblImageVersus,RUTA_IMAGEN_VERSUS);
    }
    
    
    
    public void llenarListasExtra(){
        labelsExtra.add(lblRegistroHabilidadExtra1);
        labelsExtra.add(lblRegistroHabilidadExtra2);
        labelsExtra.add(lblRegistroHabilidadExtra3);
        labelsExtra.add(lblRegistroHabilidadExtra4);

        camposExtra.add(TxtHabilidadExtra1);
        camposExtra.add(TxtHabilidadExtra2);
        camposExtra.add(TxtHabilidadExtra3);
        camposExtra.add(TxtHabilidadExtra4);
    }
    
    public void llenarComboClases(){
        ComboClaseSelecionada.removeAllItems();
        for (ClaseCombatiente clase : ClaseCombatiente.values()) {
             ComboClaseSelecionada.addItem(clase.getNombre());
        }
    }

    public JButton getBtnSubirAvatar() {
        return BtnSubirAvatar;
    }

    public JButton getBtnGuardar() {
        return BtnGuardar;
    }

    public JTextField getTxtId() {
        return TxtId;
    }

    public JTextField getTxtNombre() {
        return TxtNombre;
    }

    public JTextField getTxtVida() {
        return TxtVida;
    }

    public JTextField getTxtArma() {
        return TxtArma;
    }

    public JTextField getTxtDefensa() {
        return TxtDefensa;
    }

    public JTextField getTxtFuerza() {
        return TxtFuerza;
    }

    public JTextField getTxtPoder() {
        return TxtPoder;
    }

    public JButton getBtnActualizar() {
        return BtnActualizar;
    }

    public JButton getBtnEliminar() {
        return BtnEliminar;
    }

    public JButton getBtnLimpiar() {
        return BtnLimpiar;
    }

    public JTextField getTxtHabilidadExtra1() {
        return TxtHabilidadExtra1;
    }

    public JTextField getTxtHabilidadExtra2() {
        return TxtHabilidadExtra2;
    }

    public JTextField getTxtHabilidadExtra3() {
        return TxtHabilidadExtra3;
    }

    public JTextField getTxtHabilidadExtra4() {
        return TxtHabilidadExtra4;
    }

    public JButton getBtnAtacarCombatiente1() {
        return BtnAtacarCombatiente1;
    }

    public JButton getBtnAtacarCombatiente2() {
        return BtnAtacarCombatiente2;
    }

    public JButton getBtnHabilidadCombatiente1() {
        return BtnHabilidadCombatiente1;
    }

    public JButton getBtnHabilidadCombatiente2() {
        return BtnHabilidadCombatiente2;
    }

    public JButton getBtnRegistrarBatallaBD() {
        return BtnRegistrarBatallaBD;
    }

    public JButton getBtnReiniciarCombate() {
        return BtnReiniciarCombate;
    }

    public JCheckBox getCheckBoxHabilidadBooleana() {
        return CheckBoxHabilidadBooleana;
    }

    public JComboBox<String> getComboClaseSelecionada() {
        return ComboClaseSelecionada;
    }
    
    public void actualizarElementosHabilidadExtra() {
        if (ComboClaseSelecionada.getSelectedItem() == null) {
            return;
        }

        String claseSeleccionada = ComboClaseSelecionada.getSelectedItem().toString();
        int nHabExtra;
        int nMaxExtra = 4;
        
        for(int i = 0; i < nMaxExtra; i++){
            camposExtra.get(i).setText("");
        }
        CheckBoxHabilidadBooleana.setSelected(false);
        
        ClaseCombatiente clase = ClaseCombatiente.valueOf(
            claseSeleccionada.toUpperCase()
        );

        String[] atributos = clase.getAtributos();

        nHabExtra = atributos.length;

        for (int i = 0; i < nHabExtra; i++) {

            labelsExtra.get(i).setText(
                ClaseCombatiente.valueOf(claseSeleccionada.toUpperCase())
                    .getAtributos()[i]
            );

            labelsExtra.get(i).setVisible(true);
            camposExtra.get(i).setVisible(true);
        }

        for (int i = nHabExtra; i < nMaxExtra; i++) {
            labelsExtra.get(i).setVisible(false);
            camposExtra.get(i).setVisible(false);
        }
        
        if(clase.tieneAtributoBooleano()){
            CheckBoxHabilidadBooleana.setVisible(true);
            String [] atributosBooleanos = clase.getAtributosBooleanos();
            CheckBoxHabilidadBooleana.setText(atributosBooleanos[0]);
        }else{
            CheckBoxHabilidadBooleana.setVisible(false);
        }

        revalidate();
        repaint();
    }
    
    public void limpiarRegistro(){
        TxtId.setText("");
        TxtNombre.setText("");
        TxtVida.setText("");
        TxtFuerza.setText("");
        TxtPoder.setText("");
        TxtDefensa.setText("");
        TxtArma.setText("");
        ComboClaseSelecionada.setSelectedIndex(0);
        CheckBoxHabilidadBooleana.setSelected(false);
        actualizarElementosHabilidadExtra();
        lblImageAvatarCombatiente.setIcon(null);
        lblImageAvatarCombatiente.setText("Sin imagen");
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        TabbedPaneArena = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblRegistroCombatientes = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblRegistroIdCombatiente = new javax.swing.JLabel();
        lblRegistroNombreCombatiente = new javax.swing.JLabel();
        lblRegistroFuerzaCombatiente = new javax.swing.JLabel();
        lblRegistroPoderCombatiente = new javax.swing.JLabel();
        lblRegistroDefensaCombatiente = new javax.swing.JLabel();
        lblRegistroArmaCombatiente = new javax.swing.JLabel();
        lblRegistroClaseCombatiente = new javax.swing.JLabel();
        TxtId = new javax.swing.JTextField();
        TxtNombre = new javax.swing.JTextField();
        TxtFuerza = new javax.swing.JTextField();
        TxtPoder = new javax.swing.JTextField();
        TxtDefensa = new javax.swing.JTextField();
        TxtArma = new javax.swing.JTextField();
        ComboClaseSelecionada = new javax.swing.JComboBox<>();
        lblAvatarCombatiente = new javax.swing.JLabel();
        BtnSubirAvatar = new javax.swing.JButton();
        BtnGuardar = new javax.swing.JButton();
        BtnActualizar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        BtnLimpiar = new javax.swing.JButton();
        lblRegistroVidaCombatiente = new javax.swing.JLabel();
        TxtVida = new javax.swing.JTextField();
        lblImageAvatarCombatiente = new javax.swing.JLabel();
        lblRegistroHabilidadExtra1 = new javax.swing.JLabel();
        lblRegistroHabilidadExtra2 = new javax.swing.JLabel();
        lblRegistroHabilidadExtra3 = new javax.swing.JLabel();
        lblRegistroHabilidadExtra4 = new javax.swing.JLabel();
        TxtHabilidadExtra1 = new javax.swing.JTextField();
        TxtHabilidadExtra2 = new javax.swing.JTextField();
        TxtHabilidadExtra3 = new javax.swing.JTextField();
        TxtHabilidadExtra4 = new javax.swing.JTextField();
        CheckBoxHabilidadBooleana = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableRegistroCombatientes = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        lblCombatiente1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        ComboCombatiente1 = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        ComboCombatiente2 = new javax.swing.JComboBox<>();
        lblCombatiente2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblImageCombatiente1 = new javax.swing.JLabel();
        BtnAtacarCombatiente1 = new javax.swing.JButton();
        BtnHabilidadCombatiente1 = new javax.swing.JButton();
        ProgressBarVidaCombatiente1 = new javax.swing.JProgressBar();
        lblVidaCombatiente1 = new javax.swing.JLabel();
        lblAtributosPersonajeBaseCombatiente1 = new javax.swing.JLabel();
        lblAtributosClaseCombatiente1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lblImageCombatiente2 = new javax.swing.JLabel();
        BtnAtacarCombatiente2 = new javax.swing.JButton();
        BtnHabilidadCombatiente2 = new javax.swing.JButton();
        ProgressBarVidaCombatiente2 = new javax.swing.JProgressBar();
        lblVidaCombatiente2 = new javax.swing.JLabel();
        lblAtributosPersonajeBaseCombatiente2 = new javax.swing.JLabel();
        lblAtributosClaseCombatiente2 = new javax.swing.JLabel();
        lblCombate1 = new javax.swing.JLabel();
        lblCombate2 = new javax.swing.JLabel();
        lblBitacoraCombate = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        BtnReiniciarCombate = new javax.swing.JButton();
        BtnRegistrarBatallaBD = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TextAreaBitacora = new javax.swing.JTextArea();
        lblImageVersus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Arena de Batalla POO - UPIICSA");

        TabbedPaneArena.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblRegistroCombatientes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblRegistroCombatientes.setText("Registro de combatientes");

        lblRegistroIdCombatiente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRegistroIdCombatiente.setText("ID de Personaje (PER-000)");

        lblRegistroNombreCombatiente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRegistroNombreCombatiente.setText("Nombre del Combatiente");

        lblRegistroFuerzaCombatiente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRegistroFuerzaCombatiente.setText("Fuerza");

        lblRegistroPoderCombatiente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRegistroPoderCombatiente.setText("Poder");

        lblRegistroDefensaCombatiente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRegistroDefensaCombatiente.setText("Defensa");

        lblRegistroArmaCombatiente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRegistroArmaCombatiente.setText("Arma");
        lblRegistroArmaCombatiente.setToolTipText("");

        lblRegistroClaseCombatiente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRegistroClaseCombatiente.setText("Clase del Combatiente");

        TxtId.setToolTipText("");

        TxtFuerza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtFuerzaActionPerformed(evt);
            }
        });

        TxtDefensa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtDefensaActionPerformed(evt);
            }
        });

        ComboClaseSelecionada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboClaseSelecionada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboClaseSelecionadaActionPerformed(evt);
            }
        });

        lblAvatarCombatiente.setText("Avatar");

        BtnSubirAvatar.setBackground(new java.awt.Color(14, 66, 179));
        BtnSubirAvatar.setForeground(new java.awt.Color(255, 255, 255));
        BtnSubirAvatar.setText("Subir Avatar");
        BtnSubirAvatar.setFocusPainted(false);
        BtnSubirAvatar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSubirAvatarActionPerformed(evt);
            }
        });

        BtnGuardar.setBackground(new java.awt.Color(0, 166, 5));
        BtnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        BtnGuardar.setText("Guardar");
        BtnGuardar.setFocusPainted(false);
        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });

        BtnActualizar.setBackground(new java.awt.Color(233, 116, 10));
        BtnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        BtnActualizar.setText("Actualizar");
        BtnActualizar.setToolTipText("");
        BtnActualizar.setFocusPainted(false);
        BtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActualizarActionPerformed(evt);
            }
        });

        BtnEliminar.setBackground(new java.awt.Color(224, 35, 33));
        BtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        BtnEliminar.setText("Eliminar");
        BtnEliminar.setFocusPainted(false);
        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });

        BtnLimpiar.setBackground(new java.awt.Color(237, 236, 241));
        BtnLimpiar.setText("Limpiar");
        BtnLimpiar.setFocusPainted(false);
        BtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarActionPerformed(evt);
            }
        });

        lblRegistroVidaCombatiente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRegistroVidaCombatiente.setText("Vida");

        TxtVida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtVidaActionPerformed(evt);
            }
        });

        lblImageAvatarCombatiente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImageAvatarCombatiente.setText("Sin imagen");

        lblRegistroHabilidadExtra1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRegistroHabilidadExtra1.setText("HabilidadExtra1");

        lblRegistroHabilidadExtra2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRegistroHabilidadExtra2.setText("HabilidadExtra2");

        lblRegistroHabilidadExtra3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRegistroHabilidadExtra3.setText("HabilidadExtra3");

        lblRegistroHabilidadExtra4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRegistroHabilidadExtra4.setText("HabilidadExtra4");

        TxtHabilidadExtra1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtHabilidadExtra1ActionPerformed(evt);
            }
        });

        TxtHabilidadExtra2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtHabilidadExtra2ActionPerformed(evt);
            }
        });

        TxtHabilidadExtra4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtHabilidadExtra4ActionPerformed(evt);
            }
        });

        CheckBoxHabilidadBooleana.setLabel("Habilidad Bool");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblRegistroHabilidadExtra2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRegistroHabilidadExtra1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRegistroHabilidadExtra3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRegistroHabilidadExtra4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtHabilidadExtra2)
                            .addComponent(TxtHabilidadExtra3)
                            .addComponent(TxtHabilidadExtra4)
                            .addComponent(TxtHabilidadExtra1, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(BtnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addComponent(BtnActualizar)
                        .addGap(84, 84, 84)
                        .addComponent(BtnEliminar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblRegistroVidaCombatiente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRegistroIdCombatiente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRegistroNombreCombatiente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRegistroFuerzaCombatiente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRegistroPoderCombatiente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRegistroDefensaCombatiente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRegistroArmaCombatiente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRegistroClaseCombatiente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(TxtArma, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtPoder, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtFuerza, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtId, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtVida, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtDefensa, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ComboClaseSelecionada, 0, 220, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(lblAvatarCombatiente, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(BtnSubirAvatar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblImageAvatarCombatiente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnLimpiar))
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(CheckBoxHabilidadBooleana, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRegistroIdCombatiente)
                    .addComponent(TxtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAvatarCombatiente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRegistroNombreCombatiente)
                            .addComponent(TxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblRegistroVidaCombatiente)
                            .addComponent(TxtVida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblRegistroFuerzaCombatiente)
                            .addComponent(TxtFuerza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRegistroPoderCombatiente)
                            .addComponent(TxtPoder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRegistroDefensaCombatiente)
                            .addComponent(TxtDefensa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblImageAvatarCombatiente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRegistroArmaCombatiente)
                    .addComponent(TxtArma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRegistroClaseCombatiente)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ComboClaseSelecionada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnSubirAvatar)))
                .addGap(34, 34, 34)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRegistroHabilidadExtra1)
                    .addComponent(TxtHabilidadExtra1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CheckBoxHabilidadBooleana))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRegistroHabilidadExtra2)
                    .addComponent(TxtHabilidadExtra2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRegistroHabilidadExtra3)
                    .addComponent(TxtHabilidadExtra3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRegistroHabilidadExtra4)
                    .addComponent(TxtHabilidadExtra4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnGuardar)
                    .addComponent(BtnActualizar)
                    .addComponent(BtnEliminar)
                    .addComponent(BtnLimpiar))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblRegistroCombatientes, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblRegistroCombatientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        TableRegistroCombatientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Clase", "Vida", "Fuerza", "Defensa", "Especial"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TableRegistroCombatientes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        TabbedPaneArena.addTab("1. Forja de Personajes  (CRUD)", jPanel2);

        lblCombatiente1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCombatiente1.setText("Combatiente 1");

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ComboCombatiente1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboCombatiente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboCombatiente1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ComboCombatiente1, 0, 637, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ComboCombatiente1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ComboCombatiente2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboCombatiente2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboCombatiente2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ComboCombatiente2, 0, 636, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ComboCombatiente2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblCombatiente2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCombatiente2.setText("Combatiente 2");

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblImageCombatiente1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImageCombatiente1.setText("Sin imagen");

        BtnAtacarCombatiente1.setBackground(new java.awt.Color(201, 6, 2));
        BtnAtacarCombatiente1.setForeground(new java.awt.Color(255, 255, 255));
        BtnAtacarCombatiente1.setText("Atacar");

        BtnHabilidadCombatiente1.setBackground(new java.awt.Color(18, 77, 181));
        BtnHabilidadCombatiente1.setForeground(new java.awt.Color(255, 255, 255));
        BtnHabilidadCombatiente1.setText("Habilidad");

        lblVidaCombatiente1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblVidaCombatiente1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVidaCombatiente1.setText("HP: 000/000");

        lblAtributosPersonajeBaseCombatiente1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAtributosPersonajeBaseCombatiente1.setText("Fuerza: 80 | Poder: 50 | Defensa: 30");

        lblAtributosClaseCombatiente1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAtributosClaseCombatiente1.setText("KI: 100");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(BtnAtacarCombatiente1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(BtnHabilidadCombatiente1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lblImageCombatiente1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ProgressBarVidaCombatiente1, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                            .addComponent(lblVidaCombatiente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAtributosPersonajeBaseCombatiente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAtributosClaseCombatiente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblImageCombatiente1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(lblVidaCombatiente1)
                        .addGap(15, 15, 15)
                        .addComponent(ProgressBarVidaCombatiente1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblAtributosPersonajeBaseCombatiente1)
                        .addGap(4, 4, 4)
                        .addComponent(lblAtributosClaseCombatiente1)))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnAtacarCombatiente1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnHabilidadCombatiente1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblImageCombatiente2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImageCombatiente2.setText("Sin imagen");

        BtnAtacarCombatiente2.setBackground(new java.awt.Color(201, 6, 2));
        BtnAtacarCombatiente2.setForeground(new java.awt.Color(255, 255, 255));
        BtnAtacarCombatiente2.setText("Atacar");
        BtnAtacarCombatiente2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 6, 2), 1, true));

        BtnHabilidadCombatiente2.setBackground(new java.awt.Color(18, 77, 181));
        BtnHabilidadCombatiente2.setForeground(new java.awt.Color(255, 255, 255));
        BtnHabilidadCombatiente2.setText("Habilidad");

        lblVidaCombatiente2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblVidaCombatiente2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVidaCombatiente2.setText("HP: 000/000");

        lblAtributosPersonajeBaseCombatiente2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAtributosPersonajeBaseCombatiente2.setText("Fuerza: 80 | Poder: 50 | Defensa: 30");
        lblAtributosPersonajeBaseCombatiente2.setToolTipText("");

        lblAtributosClaseCombatiente2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAtributosClaseCombatiente2.setText("KI: 100");
        lblAtributosClaseCombatiente2.setToolTipText("");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lblImageCombatiente2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ProgressBarVidaCombatiente2, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                            .addComponent(lblVidaCombatiente2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAtributosPersonajeBaseCombatiente2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAtributosClaseCombatiente2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(BtnAtacarCombatiente2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(BtnHabilidadCombatiente2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lblImageCombatiente2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(lblVidaCombatiente2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ProgressBarVidaCombatiente2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblAtributosPersonajeBaseCombatiente2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAtributosClaseCombatiente2)))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnAtacarCombatiente2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnHabilidadCombatiente2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblCombate1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCombate1.setText("Combate");

        lblCombate2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCombate2.setText("Combate");

        lblBitacoraCombate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBitacoraCombate.setText("Bitacora del Combate (Registro en Tiempo Real)");

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        BtnReiniciarCombate.setText("Reiniciar Combate");
        BtnReiniciarCombate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnReiniciarCombateActionPerformed(evt);
            }
        });

        BtnRegistrarBatallaBD.setBackground(new java.awt.Color(5, 156, 8));
        BtnRegistrarBatallaBD.setForeground(new java.awt.Color(255, 255, 255));
        BtnRegistrarBatallaBD.setText("Registrar Batalla en BD");
        BtnRegistrarBatallaBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegistrarBatallaBDActionPerformed(evt);
            }
        });

        TextAreaBitacora.setEditable(false);
        TextAreaBitacora.setColumns(20);
        TextAreaBitacora.setRows(5);
        jScrollPane2.setViewportView(TextAreaBitacora);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(483, 483, 483)
                .addComponent(BtnReiniciarCombate)
                .addGap(126, 126, 126)
                .addComponent(BtnRegistrarBatallaBD)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnReiniciarCombate)
                    .addComponent(BtnRegistrarBatallaBD))
                .addGap(16, 16, 16))
        );

        lblImageVersus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblBitacoraCombate, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCombatiente1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCombatiente2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(lblCombate1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(lblImageVersus, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(lblCombate2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(530, 530, 530)))))
                        .addGap(27, 27, 27))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCombatiente2)
                    .addComponent(lblCombatiente1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCombate1)
                    .addComponent(lblCombate2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblImageVersus, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblBitacoraCombate)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        TabbedPaneArena.addTab("2. Carga de Combatientes (POD)", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(TabbedPaneArena)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TabbedPaneArena)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboCombatiente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboCombatiente2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboCombatiente2ActionPerformed

    private void BtnReiniciarCombateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnReiniciarCombateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnReiniciarCombateActionPerformed

    private void BtnRegistrarBatallaBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegistrarBatallaBDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRegistrarBatallaBDActionPerformed

    private void ComboCombatiente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboCombatiente1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboCombatiente1ActionPerformed

    private void TxtHabilidadExtra4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtHabilidadExtra4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtHabilidadExtra4ActionPerformed

    private void TxtHabilidadExtra2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtHabilidadExtra2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtHabilidadExtra2ActionPerformed

    private void TxtHabilidadExtra1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtHabilidadExtra1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtHabilidadExtra1ActionPerformed

    private void TxtVidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtVidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtVidaActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed
        limpiarRegistro();
        JOptionPane.showMessageDialog(this, "Registro limpiado correctamente.");
    }//GEN-LAST:event_BtnLimpiarActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void BtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnActualizarActionPerformed

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void BtnSubirAvatarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSubirAvatarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSubirAvatarActionPerformed

    private void ComboClaseSelecionadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboClaseSelecionadaActionPerformed
        actualizarElementosHabilidadExtra();
    }//GEN-LAST:event_ComboClaseSelecionadaActionPerformed

    private void TxtDefensaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtDefensaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtDefensaActionPerformed

    private void TxtFuerzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtFuerzaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtFuerzaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnActualizar;
    private javax.swing.JButton BtnAtacarCombatiente1;
    private javax.swing.JButton BtnAtacarCombatiente2;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnHabilidadCombatiente1;
    private javax.swing.JButton BtnHabilidadCombatiente2;
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JButton BtnRegistrarBatallaBD;
    private javax.swing.JButton BtnReiniciarCombate;
    private javax.swing.JButton BtnSubirAvatar;
    private javax.swing.JCheckBox CheckBoxHabilidadBooleana;
    private javax.swing.JComboBox<String> ComboClaseSelecionada;
    private javax.swing.JComboBox<String> ComboCombatiente1;
    private javax.swing.JComboBox<String> ComboCombatiente2;
    private javax.swing.JProgressBar ProgressBarVidaCombatiente1;
    private javax.swing.JProgressBar ProgressBarVidaCombatiente2;
    private javax.swing.JTabbedPane TabbedPaneArena;
    private javax.swing.JTable TableRegistroCombatientes;
    private javax.swing.JTextArea TextAreaBitacora;
    private javax.swing.JTextField TxtArma;
    private javax.swing.JTextField TxtDefensa;
    private javax.swing.JTextField TxtFuerza;
    private javax.swing.JTextField TxtHabilidadExtra1;
    private javax.swing.JTextField TxtHabilidadExtra2;
    private javax.swing.JTextField TxtHabilidadExtra3;
    private javax.swing.JTextField TxtHabilidadExtra4;
    private javax.swing.JTextField TxtId;
    private javax.swing.JTextField TxtNombre;
    private javax.swing.JTextField TxtPoder;
    private javax.swing.JTextField TxtVida;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAtributosClaseCombatiente1;
    private javax.swing.JLabel lblAtributosClaseCombatiente2;
    private javax.swing.JLabel lblAtributosPersonajeBaseCombatiente1;
    private javax.swing.JLabel lblAtributosPersonajeBaseCombatiente2;
    private javax.swing.JLabel lblAvatarCombatiente;
    private javax.swing.JLabel lblBitacoraCombate;
    private javax.swing.JLabel lblCombate1;
    private javax.swing.JLabel lblCombate2;
    private javax.swing.JLabel lblCombatiente1;
    private javax.swing.JLabel lblCombatiente2;
    private javax.swing.JLabel lblImageAvatarCombatiente;
    private javax.swing.JLabel lblImageCombatiente1;
    private javax.swing.JLabel lblImageCombatiente2;
    private javax.swing.JLabel lblImageVersus;
    private javax.swing.JLabel lblRegistroArmaCombatiente;
    private javax.swing.JLabel lblRegistroClaseCombatiente;
    private javax.swing.JLabel lblRegistroCombatientes;
    private javax.swing.JLabel lblRegistroDefensaCombatiente;
    private javax.swing.JLabel lblRegistroFuerzaCombatiente;
    private javax.swing.JLabel lblRegistroHabilidadExtra1;
    private javax.swing.JLabel lblRegistroHabilidadExtra2;
    private javax.swing.JLabel lblRegistroHabilidadExtra3;
    private javax.swing.JLabel lblRegistroHabilidadExtra4;
    private javax.swing.JLabel lblRegistroIdCombatiente;
    private javax.swing.JLabel lblRegistroNombreCombatiente;
    private javax.swing.JLabel lblRegistroPoderCombatiente;
    private javax.swing.JLabel lblRegistroVidaCombatiente;
    private javax.swing.JLabel lblVidaCombatiente1;
    private javax.swing.JLabel lblVidaCombatiente2;
    // End of variables declaration//GEN-END:variables
}
