/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectosistemasoperativos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carlo
 */
public class frmMain extends javax.swing.JFrame {

    /**
     * Creates new form frmMain
     */

    //Objeto reloj
    Reloj hora_sistema = new Reloj();
    Hilo hilo = new Hilo();
    Grafica grafica = new Grafica();
    int contador = 0;
    int contadorCPU = 10;
    int Quantum = 3;
    int faltante = 0;
    int tProceso = 0;
    int procesoActual;
    int cantidadProcesos = 0;
    int tiempoTerminado = 1;
    String proceso = "P";
    int TamMemoria = 59;
    boolean esReal = false;
    int contadorProcesos = 0;
    int nuevoContador = 0;
    //int contadorF = 0;

    public frmMain() {
        initComponents();
        limpiar();
        //inicio del hilo_reloj 
        hora_sistema.start();

        TablaF.setVisible(false);
        jtTerminados.setVisible(false);
        jtTiempoProcesos.setVisible(false);
        //paint(jPGrafica.getGraphics());

    }

    void agregar() {
        DefaultTableModel modelo = (DefaultTableModel) TablaP.getModel();

        DefaultTableModel modeloF = (DefaultTableModel) TablaF.getModel();

        if (txtInicio.getText().isEmpty() || txtTiempo.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Hacen falta datos.");
        } else {
            int ObtTiempo = Integer.parseInt(txtTiempo.getText());
            if (contadorCPU + ObtTiempo <= TamMemoria) {
                contador++;
                String idProceso = proceso + contador;
                int textoInicio = Integer.parseInt(txtInicio.getText());
                int textoTiempo = Integer.parseInt(txtTiempo.getText());
                //int textoQuantum = Integer
                modelo.addRow(new Object[]{idProceso, textoInicio, textoTiempo});
                txtInicio.setText(null);
                txtTiempo.setText(null);
                grafica.GraficarP(jPGrafica.getGraphics(), 1, textoTiempo, 225, textoTiempo);
                contadorCPU = ObtTiempo + contadorCPU;

                modeloF.addRow(new Object[]{idProceso, "--", Quantum, ObtTiempo, "--", "--", "--"});

            } else {
                JOptionPane.showMessageDialog(null, "Tamaño de memoria insuficiente.\n El espacio restante es: " + (TamMemoria - contadorCPU));
            }
        }
    }

    void limpiar() {
        DefaultTableModel modelo = (DefaultTableModel) TablaP.getModel();
        int filas = modelo.getRowCount();
        for (int i = 0; i < filas; i++) {
            modelo.removeRow(0);
        }
        txtInicio.setText(null);
        txtTiempo.setText(null);

        DefaultTableModel modeloF = (DefaultTableModel) TablaF.getModel();
        int filasF = modeloF.getRowCount();
        for (int i = 0; i < filasF; i++) {
            modeloF.removeRow(0);
        }
        jContadorActual.setText(null);
        jBaseActual.setText(null);
        jLimiteActual.setText(null);
        jProcesoActual.setText("Proceso Actual");
        paint(jPGrafica.getGraphics());
        contadorCPU = 10;
        TablaF.setVisible(false);
        jtTerminados.setText(null);
        jtTerminados.setVisible(false);
        jtTiempoProcesos.setText(null);
        jtTiempoProcesos.setVisible(false);
        cantidadProcesos = 0;
        tiempoTerminado = 1;
        /**/
    }

    void revisarBase(int i) {

        int contadorTiempoBase = 10;
        for (int c = 0; c < i; c++) {
            Object tiempoBase = TablaP.getValueAt(c, 2);
            String stringTiempoBase = tiempoBase.toString();
            int intTiempoBase = Integer.parseInt(stringTiempoBase);
            contadorTiempoBase = contadorTiempoBase + intTiempoBase;
        }

        String HexaTiempoBase = Integer.toHexString(contadorTiempoBase);
        jBaseActual.setText(HexaTiempoBase + "h");

        int contadorTiempoLimite = 10;
        for (int c = 0; c <= i; c++) {
            Object tiempoLimite = TablaP.getValueAt(c, 2);
            String stringTiempoLimite = tiempoLimite.toString();
            int intTiempoLimite = Integer.parseInt(stringTiempoLimite);
            contadorTiempoLimite = contadorTiempoLimite + intTiempoLimite;
        }

        String HexaTiempoLimite = Integer.toHexString(contadorTiempoLimite);
        jLimiteActual.setText(HexaTiempoLimite + "h");

        Object tiempoActual = TablaF.getValueAt(i, 3);
        String stringTiempoActual = tiempoActual.toString();
        int intTiempoActual = Integer.parseInt(stringTiempoActual);
        int sumaActual = contadorTiempoLimite - intTiempoActual;
        String hexaSumaActual = Integer.toHexString(sumaActual);
        jContadorActual.setText(hexaSumaActual + "h");
        
        Object tRes = TablaF.getValueAt(i, 3);
        String stringTRes = tRes.toString();
        int intTRes = Integer.parseInt(stringTRes);
        grafica.pintar(jPGrafica.getGraphics(), contadorTiempoBase, contadorTiempoLimite, intTiempoActual);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnIniciar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        txtInicio = new javax.swing.JTextField();
        txtTiempo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaP = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jProcesoActual = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jActual = new javax.swing.JPanel();
        jContadorActual = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jBaseActual = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLimiteActual = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaF = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jtTerminados = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jtTiempoProcesos = new javax.swing.JTextField();
        Labelhora = new javax.swing.JLabel();
        lblhorasistema = new javax.swing.JLabel();
        jPGrafica = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(231, 255, 255));

        jPanel2.setBackground(new java.awt.Color(231, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setText("FLMC");

        btnIniciar.setBackground(new java.awt.Color(7, 35, 39));
        btnIniciar.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        btnIniciar.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel2.setText("Procesos");

        btnAdd.setBackground(new java.awt.Color(7, 35, 39));
        btnAdd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Agregar Proceso");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Tiempo Inicio");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Tiempo Consumo");

        btnLimpiar.setBackground(new java.awt.Color(7, 35, 39));
        btnLimpiar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("Limpiar celdas");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        TablaP.setBackground(new java.awt.Color(80, 188, 185));
        TablaP.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        TablaP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Proceso", "T.Inicio", "T.Consumo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaP.setGridColor(new java.awt.Color(7, 35, 39));
        TablaP.setSelectionBackground(new java.awt.Color(204, 255, 255));
        jScrollPane1.setViewportView(TablaP);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6))
                            .addComponent(btnIniciar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
        );

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setText("Memoria Principal");

        jPanel3.setBackground(new java.awt.Color(51, 133, 139));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setText("CPU");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Contador de Programa");

        jPanel4.setBackground(new java.awt.Color(7, 35, 39));

        jProcesoActual.setBackground(new java.awt.Color(7, 35, 39));
        jProcesoActual.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jProcesoActual.setForeground(new java.awt.Color(255, 255, 255));
        jProcesoActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jProcesoActual.setText("Proceso Actual");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProcesoActual, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProcesoActual, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("Planificador");

        jActual.setBackground(new java.awt.Color(7, 35, 39));

        jContadorActual.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jContadorActual.setForeground(new java.awt.Color(255, 255, 255));
        jContadorActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jContadorActual.setText("Actual");

        javax.swing.GroupLayout jActualLayout = new javax.swing.GroupLayout(jActual);
        jActual.setLayout(jActualLayout);
        jActualLayout.setHorizontalGroup(
            jActualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jActualLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jContadorActual, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                .addContainerGap())
        );
        jActualLayout.setVerticalGroup(
            jActualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jActualLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jContadorActual, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(7, 35, 39));

        jBaseActual.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBaseActual.setForeground(new java.awt.Color(255, 255, 255));
        jBaseActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jBaseActual.setText("Base");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBaseActual, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBaseActual, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(7, 35, 39));

        jLimiteActual.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLimiteActual.setForeground(new java.awt.Color(255, 255, 255));
        jLimiteActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLimiteActual.setText("Límite");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLimiteActual, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLimiteActual, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(7, 35, 39));
        jLabel8.setText("Base:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(7, 35, 39));
        jLabel10.setText("Límite:");

        TablaF.setBackground(new java.awt.Color(80, 188, 185));
        TablaF.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Estado", "Quantum", "T.Faltante", "T.Total", "HoraInicio", "HoraFin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaF.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                TablaFComponentShown(evt);
            }
        });
        jScrollPane2.setViewportView(TablaF);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setText("Cantidad de Procesos:");

        jtTerminados.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setText("Tiempo de Procesos:");

        jtTiempoProcesos.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        Labelhora.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Labelhora.setText("Hora del sistema:");

        lblhorasistema.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblhorasistema.setText("------------------------------");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(25, 25, 25)))
                        .addGap(192, 192, 192))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(158, 158, 158))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(Labelhora, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblhorasistema)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtTiempoProcesos, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(jtTerminados))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jLabel8)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(31, 31, 31)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jtTerminados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtTiempoProcesos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Labelhora, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblhorasistema))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPGraficaLayout = new javax.swing.GroupLayout(jPGrafica);
        jPGrafica.setLayout(jPGraficaLayout);
        jPGraficaLayout.setHorizontalGroup(
            jPGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );
        jPGraficaLayout.setVerticalGroup(
            jPGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("0x00h");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("0x3ch");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13))
                    .addComponent(jPGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        agregar();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
        contador = 0;
        hilo.stop();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        // TODO add your handling code here:
        TablaF.setVisible(true);
        jtTerminados.setVisible(true);
        jtTiempoProcesos.setVisible(true);
        btnLimpiar.setVisible(false);
        btnIniciar.setVisible(false);
        paintActivador(jPGrafica.getGraphics());
        hilo = new Hilo();
        hilo.start();
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void TablaFComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_TablaFComponentShown
        // TODO add your handling code here:

    }//GEN-LAST:event_TablaFComponentShown

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMain().setVisible(true);
            }
        });
    }

    private class Hilo extends Thread { //Objeto de tipo Hilo con extension ejectubale

        @Override
        public void run() {
            int estado = 1; //Estado de while que indica si se puede seguir o no
            int i = 0; // contador de while
            boolean existe = false;
            // Grafica Gr = new Grafica();
            while (estado != 0) {
                existe = false;
                while (i < contador) { //Recorrer las filas
                    Revisar(i);
                    RevisarListo();
                    Object verEstado = TablaF.getValueAt(i, 1);
                    String stringVerEstado = verEstado.toString();
                    if ("Listo".equals(stringVerEstado) || "Espera".equals(stringVerEstado)) {
                        if (faltante != 0 && faltante > Quantum) { //Ejecutando Procesos cuando sea mayor al quantum
                            for (int c = 1; c <= Quantum; c++) {
                                paintActivador(jPGrafica.getGraphics());
                                // Gr.paint(jPGrafica.getGraphics(), 1, tProceso, 225, faltante );
                                TablaF.setValueAt("Procesando", i, 1);
                                revisarBase(i);
                                Dormir();
                                faltante--;
                                //contadorM ++;
                                TablaF.setValueAt(String.valueOf(faltante), i, 3);
                                tiempoTerminado++;
                                existe = true;
                                RevisarListo();
                                jtTiempoProcesos.setText(String.valueOf((tiempoTerminado - 1) + " Segundos"));
                                // agregar la hora del sistema del inicio y final
                            }
                            TablaF.setValueAt("Espera", i, 1);
                            ActivadorActivo(jPGrafica.getGraphics());//pintar verde
                            Activador();//activador
                            if (faltante == 0) {
                                TablaF.setValueAt("Terminado", i, 1);
                                Informar(i);
                                String horaF = lblhorasistema.getText();
                                TablaF.setValueAt(horaF, i, 6);
                            }
                        } else {
                            if (faltante > 0 && Quantum != 0) { // Ejecutando proceso cuando tiempo restante sea menor que el quantum
                                while (faltante > 0) {
                                    // Gr.paint(jPGrafica.getGraphics(), 1, tProceso, 225, faltante );
                                    paintActivador(jPGrafica.getGraphics());
                                    TablaF.setValueAt("Procesando", i, 1);
                                    revisarBase(i);
                                    Dormir();
                                    faltante--;
                                    //contadorM ++;
                                    TablaF.setValueAt(String.valueOf(faltante), i, 3);
                                    tiempoTerminado++;
                                    existe = true;
                                    RevisarListo();
                                    jtTiempoProcesos.setText(String.valueOf((tiempoTerminado - 1) + " Segundos"));
                                }
                                TablaF.setValueAt("Espera", i, 1);
                                ActivadorActivo(jPGrafica.getGraphics());//pintar verde
                                Activador();//activador
                                //paintActivador(jPGrafica.getGraphics());
                                if (faltante == 0 && Quantum != 0) {
                                    TablaF.setValueAt("Terminado", i, 1);
                                    TablaF.setValueAt(tiempoTerminado - 2, i, 4);
                                    Informar(i);
                                    String horaF = lblhorasistema.getText();
                                    TablaF.setValueAt(horaF, i, 6);
                                }
                            } else {
                                if (faltante == 0 && Quantum != 0) {
                                    TablaF.setValueAt("Terminado", i, 1);
                                    TablaF.setValueAt(tiempoTerminado - 2, i, 4);
                                    Informar(i);
                                    String horaF = lblhorasistema.getText();
                                    TablaF.setValueAt(horaF, i, 6);
                                }
                            }
                        }
                    }
                    i++; // Pasa a la siguiente fila
                }
                i = 0; //
                if (existe == false) {
                    tiempoTerminado++;
                    Dormir();
                }
                if (contador == cantidadProcesos) {
                    estado = 0;
                    btnLimpiar.setVisible(true);
                    btnIniciar.setVisible(true);
                }
            }
        }
    }

    public void HoraInicio(int i) {
        Object textoI = TablaF.getValueAt(i, 1);
        String pasarI = textoI.toString();
        if ("Listo".equals(pasarI)) {

        }
    }

    public void Informar(int i) {
        cantidadProcesos++;
        jtTerminados.setText(String.valueOf(cantidadProcesos + " Terminados"));

    }

    public void RevisarListo() {
        int numeroFilas = TablaF.getRowCount();
        for (int c = 0; c < numeroFilas; c++) {
            Object tiempoInicio = TablaP.getValueAt(c, 1);
            String stringTiempoInicio = tiempoInicio.toString();
            int intTiempoInicio = Integer.parseInt(stringTiempoInicio);
            Object tardado = TablaP.getValueAt(c, 2);
            String stringTardado = tardado.toString();
            int intTardado = Integer.parseInt(stringTardado);
            tProceso = intTiempoInicio; // este es el tiempo en que inicia el proceso
            if (tProceso == tiempoTerminado) {
                TablaF.setValueAt("Listo", c, 1);
                String horaI = lblhorasistema.getText();
                TablaF.setValueAt(horaI, c, 5);
                //contadorProcesos = contadorProcesos + intTardado;
                //nuevoContador++;
              //  grafica.GraficarP(jPGrafica.getGraphics(), contadorProcesos,nuevoContador);
            } else if (tProceso == 0 && esReal == false) {
                TablaF.setValueAt("Listo", c, 1);
                String horaI = lblhorasistema.getText();
                TablaF.setValueAt(horaI, c, 5);
                esReal = true;
                /*contadorProcesos = contadorProcesos + intTardado;
                nuevoContador++;
                grafica.GraficarP(jPGrafica.getGraphics(), contadorProcesos,nuevoContador);*/
            }            
        }
    }

    public void Revisar(int i) {
        Object texto = TablaF.getValueAt(i, 0);
        String pasar = texto.toString();
        int longitud = 0;
        longitud = pasar.length();
        if (longitud == 2) {
            char segundoCaracter = pasar.charAt(1);
            int valorEntero = Character.getNumericValue(segundoCaracter);
            procesoActual = valorEntero;
        } else {
            char segundoCaracter = pasar.charAt(1);
            char tercerCaracter = pasar.charAt(2);
            String segundotercero = "" + segundoCaracter + tercerCaracter;
            int valorEntero = Integer.parseInt(segundotercero);
            procesoActual = valorEntero;
        }
        Object tiempoFaltante = TablaF.getValueAt(i, 3);
        String stringTiempoFaltante = tiempoFaltante.toString();
        int enteroTiempoFaltante = Integer.parseInt(stringTiempoFaltante);
        faltante = enteroTiempoFaltante;
        if (faltante > 0) {
            jProcesoActual.setText(String.valueOf(proceso + procesoActual));
        } else {
            jProcesoActual.setText("Finalizado");
            // agregar la opción de quitarle el espacio a la memoriaCPU
        }
    }

    public void Dormir() {
        try {
            Thread.sleep(1000); //Dormir sistema 1 segundo
        } catch (InterruptedException ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Activador(){
          try {
            Thread.sleep(1000); //Dormir sistema 1 segundo
            tiempoTerminado++; // tiempo terminado ++
        } catch (InterruptedException ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //hilo reloj a 1 segundo real
    public class Reloj extends Thread {

        Calendar calendario;

        @Override
        public void run() {
            while (true) {
                String horaSistema = "";
                calendario = Calendar.getInstance();
                if (calendario.get(Calendar.HOUR_OF_DAY) < 10) {
                    horaSistema += String.valueOf("0" + calendario.get(Calendar.HOUR_OF_DAY)) + ":";
                } else {
                    horaSistema += String.valueOf(calendario.get(Calendar.HOUR_OF_DAY)) + ":";
                }
                if (calendario.get(Calendar.MINUTE) < 10) {
                    horaSistema += String.valueOf("0" + calendario.get(Calendar.MINUTE)) + ":";
                } else {
                    horaSistema += String.valueOf(calendario.get(Calendar.MINUTE)) + ":";
                }
                if (calendario.get(Calendar.SECOND) < 10) {
                    horaSistema += String.valueOf("0" + calendario.get(Calendar.SECOND)) + ":";
                } else {
                    horaSistema += String.valueOf(calendario.get(Calendar.SECOND)) + ":";
                }
                horaSistema += String.valueOf(calendario.get(Calendar.MILLISECOND)) + " hrs";
                lblhorasistema.setText(horaSistema);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    // Clase para graficar
    public class Grafica {

        public void GraficarP(Graphics g, int x, int y, int ancho, int altura) {
            Stroke grosor = new BasicStroke(3.0f);
            Graphics2D graficar = (Graphics2D) g;
            String idProceso1 = proceso + contador;
            g.setColor(Color.BLACK);
            g.setFont(new Font("Serif", Font.BOLD, 11));
            g.drawString(idProceso1, 110, 600 - ((10 * contadorCPU) + ((altura / 2) * 10)));
            graficar.setStroke(grosor);
            graficar.setColor(Color.BLACK);
            graficar.drawRect(3, 600 - ((10 * contadorCPU) + (altura * 10)), 220, altura * 10);


        }

       /* public void GraficarP(Graphics g, int altura, int c) {
            Stroke grosor = new BasicStroke(3.0f);
            Graphics2D graficar = (Graphics2D) g;
            String idProceso1 = proceso + c;
            g.setColor(Color.BLACK);
            g.setFont(new Font("Serif", Font.BOLD, 11));
            g.drawString(idProceso1, 110, 600 - ((10 * 10) + ((altura / 2) * 10)));
            graficar.setStroke(grosor);
            graficar.setColor(Color.BLACK);
            graficar.drawRect(3, 600 - ((10 * 10) + (altura * 10)), 220, altura * 10);


        }*/
        
        public void pintar(Graphics g, int base, int limite, int i){
            Graphics2D graficarP = (Graphics2D) g;
            graficarP.setColor(new Color(7, 35, 39));
            graficarP.fillRect(3,600- (limite*10), 220, (limite-base-i+1)*10); 

            //graficarP.fillRect(3,600- (limite*10), 220, (limite-base)*10); 
            //graficarP.fillRect(3,600- ((base*10)+(limite*10)), 220, (limite-base)*10); 
        }
    }

    public void paint(Graphics g) {
        //Stroke grosor = new BasicStroke (2.0f);
        super.paint(g);
        // x -- y -- ancho -- largo
        g = jPGrafica.getGraphics();

        g.setColor(Color.GRAY);
        g.fillRect(1, 1, 225, 600);

        g.setColor(new Color(7, 35, 39));
        g.fillRect(1, 500, 225, 100);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 36));
        g.drawString("S.O.", 90, 580);
        
        
    }
    
    public void paintActivador(Graphics g){
        //super.paint(g);
        // x -- y -- ancho -- largo
        g = jPGrafica.getGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(1, 1, 225, 10);
    }
    public void ActivadorActivo(Graphics g){
       g = jPGrafica.getGraphics();
        g.setColor(Color.GREEN);
        g.fillRect(1, 1, 225, 10); 
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Labelhora;
    private javax.swing.JTable TablaF;
    private javax.swing.JTable TablaP;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JPanel jActual;
    private javax.swing.JLabel jBaseActual;
    private javax.swing.JLabel jContadorActual;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLimiteActual;
    private javax.swing.JPanel jPGrafica;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel jProcesoActual;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jtTerminados;
    private javax.swing.JTextField jtTiempoProcesos;
    private javax.swing.JLabel lblhorasistema;
    private javax.swing.JTextField txtInicio;
    private javax.swing.JTextField txtTiempo;
    // End of variables declaration//GEN-END:variables
}
