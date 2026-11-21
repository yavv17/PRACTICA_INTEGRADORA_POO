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
        ClaseSelecionada.removeAllItems();
        for (ClaseCombatiente clase : ClaseCombatiente.values()) {
             ClaseSelecionada.addItem(clase.getNombre());
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
    

    public String getClaseSelecionada() {
        return (String) ClaseSelecionada.getSelectedItem();
    }
    public void setClaseSelecionada(JComboBox<String> ClaseSelecionada) {
        this.ClaseSelecionada = ClaseSelecionada;
    }
    
    public void actualizarElementosHabilidadExtra() {
        if (ClaseSelecionada.getSelectedItem() == null) {
            return;
        }

        String claseSeleccionada = ClaseSelecionada.getSelectedItem().toString();
        int nHabExtra;
        int nMaxExtra = 4;
        
        for(int i = 0; i < nMaxExtra; i++){
            camposExtra.get(i).setText("");
        }
        
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

        revalidate();
        repaint();
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
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
        ClaseSelecionada = new javax.swing.JComboBox<>();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        TableRegistroCombatientes = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Registro de combatientes");

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

        ClaseSelecionada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ClaseSelecionada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClaseSelecionadaActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
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
                            .addComponent(ClaseSelecionada, 0, 220, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(lblAvatarCombatiente, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(BtnLimpiar)
                            .addComponent(BtnSubirAvatar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblImageAvatarCombatiente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(42, 42, 42))))
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
                        .addComponent(ClaseSelecionada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnSubirAvatar)))
                .addGap(34, 34, 34)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRegistroHabilidadExtra1)
                    .addComponent(TxtHabilidadExtra1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
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
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
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
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("1. Forja de Personajes  (CRUD)", jPanel2);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("Combatiente 1");

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, 0, 637, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox2, 0, 636, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setText("Combatiente 2");

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Combate");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Combate");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("Bitacora del Combate (Registro en Tiempo Real)");

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setText("Reiniciar Combate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Registrar Batalla en BD");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(449, 449, 449)
                .addComponent(jButton1)
                .addGap(64, 64, 64)
                .addComponent(jButton2)
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
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(27, 27, 27))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("2. Carga de Combatientes (POD)", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jTabbedPane1)
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

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
        TxtId.setText("");
        TxtNombre.setText("");
        TxtVida.setText("");
        TxtFuerza.setText("");
        TxtPoder.setText("");
        TxtDefensa.setText("");
        ClaseSelecionada.setSelectedIndex(0);
        actualizarElementosHabilidadExtra();
        lblImageAvatarCombatiente.setIcon(null);
        lblImageAvatarCombatiente.setText("Sin imagen");
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

    private void ClaseSelecionadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClaseSelecionadaActionPerformed
        actualizarElementosHabilidadExtra();
    }//GEN-LAST:event_ClaseSelecionadaActionPerformed

    private void TxtDefensaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtDefensaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtDefensaActionPerformed

    private void TxtFuerzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtFuerzaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtFuerzaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnActualizar;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JButton BtnSubirAvatar;
    private javax.swing.JComboBox<String> ClaseSelecionada;
    private javax.swing.JTable TableRegistroCombatientes;
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblAvatarCombatiente;
    private javax.swing.JLabel lblImageAvatarCombatiente;
    private javax.swing.JLabel lblRegistroArmaCombatiente;
    private javax.swing.JLabel lblRegistroClaseCombatiente;
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
    // End of variables declaration//GEN-END:variables
}
