package proyectosistemasoperativos;

import java.awt.*;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Random;
public class main extends JFrame {

    //Objeto reloj
    Reloj hora_sistema = new Reloj();
    Hilo hilo = new Hilo();
    Grafica grafica = new Grafica();
    Random random = new Random();
    int contador = 0;
    int contadorCPU = 10;
    int Quantum = random.nextInt(5)+1;
    int faltante = 0;
    int tProceso = 0;
    int procesoActual;
    int cantidadProcesos = 0;
    int tiempoTerminado = 1;
    String proceso = "P";
    int TamMemoria = 50;
    boolean esReal = false;
    int contadorProcesos = 0;
    int nuevoContador = 0;
    //int contadorF = 0;

    public main() {
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
        jProcesoActual.setText("...");
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

        int contadorTiempoLimite = 10;
        for (int c = 0; c <= i; c++) {
            Object tiempoLimite = TablaP.getValueAt(c, 2);
            String stringTiempoLimite = tiempoLimite.toString();
            int intTiempoLimite = Integer.parseInt(stringTiempoLimite);
            contadorTiempoLimite = contadorTiempoLimite + intTiempoLimite;
        }

        Object tiempoActual = TablaF.getValueAt(i, 3);
        String stringTiempoActual = tiempoActual.toString();
        int intTiempoActual = Integer.parseInt(stringTiempoActual);

        Object tRes = TablaF.getValueAt(i, 3);
        String stringTRes = tRes.toString();
        int intTRes = Integer.parseInt(stringTRes);
        grafica.pintar(jPGrafica.getGraphics(), contadorTiempoBase, contadorTiempoLimite, intTiempoActual);
    }

    private void initComponents() {

        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jLabel1 = new JLabel();
        btnIniciar = new JButton();
        jLabel2 = new JLabel();
        btnAdd = new JButton();
        txtInicio = new JTextField();
        txtTiempo = new JTextField();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        btnLimpiar = new JButton();
        jScrollPane1 = new JScrollPane();
        TablaP = new JTable();
        jLabel3 = new JLabel();
        jPanel3 = new JPanel();
        jLabel4 = new JLabel();
        jPanel4 = new JPanel();
        jProcesoActual = new JLabel();
        jActual = new JPanel();
        jPanel6 = new JPanel();
        jPanel7 = new JPanel();
        jScrollPane2 = new JScrollPane();
        TablaF = new JTable();
        jLabel11 = new JLabel();
        jtTerminados = new JTextField();
        jLabel12 = new JLabel();
        jtTiempoProcesos = new JTextField();
        Labelhora = new JLabel();
        lblhorasistema = new JLabel();
        jPGrafica = new JPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new Color(24, 18, 43));

        jPanel2.setBackground(new Color(24, 18, 43));

        jLabel1.setFont(new Font("Lexend", 1, 36));
        jLabel1.setForeground(new Color(153, 146, 178));
        jLabel1.setText("SistemasOperativos");

        btnIniciar.setBackground(new Color(68, 60, 104));
        btnIniciar.setFont(new Font("Lexend", 1, 36));
        btnIniciar.setForeground(new Color(153, 146, 178));
        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new Font("Lexend", 1, 36));
        jLabel2.setForeground(new Color(68, 60, 104));
        jLabel2.setText("Procesos");

        btnAdd.setBackground(new Color(68, 60, 104));
        btnAdd.setFont(new Font("Lexend", 1, 14));
        btnAdd.setForeground(new Color(153, 146, 178));
        btnAdd.setText("Agregar Proceso");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel5.setFont(new Font("Lexend", 1, 14));
        jLabel5.setForeground(new Color(153, 146, 178));
        jLabel5.setText("Tiempo Inicio");

        jLabel6.setFont(new Font("Lexend", 1, 14));
        jLabel6.setForeground(new Color(153, 146, 178));
        jLabel6.setText("Rafaga");

        btnLimpiar.setBackground(new Color(68, 60, 104));
        btnLimpiar.setFont(new Font("Lexend", 1, 14));
        btnLimpiar.setForeground(new Color(153, 146, 178));
        btnLimpiar.setText("Reiniciar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        TablaP.setBackground(new Color(153, 146, 178));
        TablaP.setFont(new Font("Lexend", 0, 14));
        TablaP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Proceso", "Inicio", "Rafaga"
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
        TablaP.setGridColor(new Color(34, 11, 34));
        TablaP.setSelectionBackground(new Color(99, 89, 133));
        jScrollPane1.setViewportView(TablaP);

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6))
                            .addComponent(btnIniciar, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
                            .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txtInicio, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTiempo, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnLimpiar, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txtInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTiempo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnIniciar, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
        );

        jLabel3.setFont(new Font("Lexend", 1, 21));
        jLabel3.setForeground(new Color(99, 89, 133));
        jLabel3.setText("Memoria Principal");

        jPanel3.setBackground(new Color(24, 18, 43));

        jLabel4.setFont(new Font("Lexend", 1, 24));
        jLabel4.setForeground(new Color(99, 89, 133));
        jLabel4.setText("Proceso Actual");

        jPanel4.setBackground(new Color(68, 60, 104));

        jProcesoActual.setBackground(new Color(153, 146, 178));
        jProcesoActual.setFont(new Font("Lexend", 1, 18));
        jProcesoActual.setForeground(new Color(255, 255, 255));
        jProcesoActual.setHorizontalAlignment(SwingConstants.CENTER);
        jProcesoActual.setText("Proceso");

        GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProcesoActual, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProcesoActual, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addContainerGap())
        );

        jActual.setBackground(new Color(153, 146, 178));

        GroupLayout jActualLayout = new GroupLayout(jActual);
        jActual.setLayout(jActualLayout);
        jActualLayout.setHorizontalGroup(
            jActualLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jActualLayout.createSequentialGroup()
                .addContainerGap()
                .addContainerGap())
        );
        jActualLayout.setVerticalGroup(
            jActualLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jActualLayout.createSequentialGroup()
                .addContainerGap()
                .addContainerGap())
        );

        jPanel6.setBackground(new Color(7, 35, 39));

        GroupLayout jPanel6Layout = new GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addContainerGap())
        );

        jPanel7.setBackground(new Color(255, 0, 0));

        GroupLayout jPanel7Layout = new GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addContainerGap())
        );

        TablaF.setBackground(new Color(153, 146, 178));
        TablaF.setSelectionBackground(new Color(99, 89, 133));
        TablaF.setGridColor(new Color(34, 11, 34));
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

        jLabel11.setFont(new Font("Lexend", 1, 18));
        jLabel11.setForeground(new Color(99, 89, 133));
        jLabel11.setText("Cantidad de Procesos:");

        jtTerminados.setHorizontalAlignment(JTextField.CENTER);

        jLabel12.setFont(new Font("Lexend", 1, 18));
        jLabel12.setForeground(new Color(99, 89, 133));
        jLabel12.setText("Tiempo de Procesos:");

        jtTiempoProcesos.setHorizontalAlignment(JTextField.CENTER);

        Labelhora.setFont(new Font("Lexend", 1, 18));
        Labelhora.setForeground(new Color(99, 89, 133));
        Labelhora.setText("Hora:");

        lblhorasistema.setFont(new Font("Lexend", 1, 18));
        lblhorasistema.setForeground(new Color(153, 146, 178));
        lblhorasistema.setText("--------------------");

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(25, 25, 25)))
                        .addGap(192, 192, 192))
                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jActual, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
                        .addGap(158, 158, 158))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(Labelhora, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblhorasistema)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtTiempoProcesos, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(jtTerminados))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addGap(19, 19, 19))
                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jActual, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)))
                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jtTerminados, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, GroupLayout.Alignment.TRAILING)
                    .addComponent(jtTiempoProcesos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(Labelhora, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblhorasistema))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        GroupLayout jPGraficaLayout = new GroupLayout(jPGrafica);
        jPGrafica.setLayout(jPGraficaLayout);
        jPGraficaLayout.setHorizontalGroup(
            jPGraficaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );
        jPGraficaLayout.setVerticalGroup(
            jPGraficaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPGrafica, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPGrafica, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
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
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Activador(){
          try {
            Thread.sleep(1000); //Dormir sistema 1 segundo
            tiempoTerminado++; // tiempo terminado ++
        } catch (InterruptedException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
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
            graficarP.setColor(new Color(153, 146, 178));
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

        g.setColor(new Color(99, 89, 133));
        g.fillRect(1, 1, 225, 600);

        g.setColor(new Color(68, 60, 104));
        g.fillRect(1, 500, 225, 100);
        g.setColor(new Color(153, 146, 178));
        g.setFont(new Font("Lexend", Font.BOLD, 36));
        g.drawString("S.O.", 90, 580);
        
        
    }
    
    public void paintActivador(Graphics g){
        //super.paint(g);
        // x -- y -- ancho -- largo
        g = jPGrafica.getGraphics();
        g.setColor(Color.GREEN);
        g.fillRect(1, 1, 225, 10);
    }
    public void ActivadorActivo(Graphics g){
       g = jPGrafica.getGraphics();
        g.setColor(Color.MAGENTA);
        g.fillRect(1, 1, 225, 10); 
    }
    private JLabel Labelhora;
    private JTable TablaF;
    private JTable TablaP;
    private JButton btnAdd;
    private JButton btnIniciar;
    private JButton btnLimpiar;
    private JPanel jActual;
    private JLabel jLabel1;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JPanel jPGrafica;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JLabel jProcesoActual;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTextField jtTerminados;
    private JTextField jtTiempoProcesos;
    private JLabel lblhorasistema;
    private JTextField txtInicio;
    private JTextField txtTiempo;
  
}
