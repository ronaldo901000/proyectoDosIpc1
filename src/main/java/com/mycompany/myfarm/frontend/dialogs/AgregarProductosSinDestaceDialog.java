/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.myfarm.frontend.dialogs;

import com.mycompany.myfarm.backend.MotorJuego;
import com.mycompany.myfarm.backend.animal.Animal;
import com.mycompany.myfarm.backend.creadores.CreadorDeAnimales;
import com.mycompany.myfarm.backend.producto.AlimentoFruto;
import com.mycompany.myfarm.backend.producto.AlimentoGrano;
import com.mycompany.myfarm.backend.producto.Producto;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ronaldo
 */
public class AgregarProductosSinDestaceDialog extends javax.swing.JDialog {
    private int contadorDePorcentaje;
    private static final int PORCENTAJE_MAXIMO=100;
    private CreadorDeAnimales creador;
    /**
     * Creates new form AgregarProductosSinDestaceDialog
     */
    public AgregarProductosSinDestaceDialog(CreadorDeAnimales creador) {
        initComponents();
        this.creador=creador;
        setLocationRelativeTo(null);
        colocarProductosACombo();
        colocarListaParaPorcentaje();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductosAnimalAgregados = new javax.swing.JTable();
        cmbProductos = new javax.swing.JComboBox<>();
        cmbPorcetaje = new javax.swing.JComboBox<>();
        btnAgregarProducto = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblinfo = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnTerminar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblInfoPorcentaje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Productos Sin Destace");
        setModal(true);

        jPanel1.setBackground(new java.awt.Color(0, 204, 153));

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        tablaProductosAnimalAgregados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Porcentaje DeProduccion"
            }
        ));
        jScrollPane1.setViewportView(tablaProductosAnimalAgregados);

        btnAgregarProducto.setBackground(new java.awt.Color(255, 153, 0));
        btnAgregarProducto.setFont(new java.awt.Font("Ubuntu Mono", 1, 19)); // NOI18N
        btnAgregarProducto.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregarProducto.setText("Agregar");
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Ubuntu Mono", 0, 19)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Producto");

        jLabel3.setFont(new java.awt.Font("Ubuntu Mono", 0, 19)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Porcentaje");

        lblinfo.setFont(new java.awt.Font("Ubuntu Mono", 0, 17)); // NOI18N
        lblinfo.setForeground(new java.awt.Color(0, 102, 102));
        lblinfo.setText("Si dejas libre un porcentaje Puedes agregar mas productos en el apartado de agregar producto animal");

        jLabel6.setFont(new java.awt.Font("Ubuntu Mono", 0, 19)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Tabla con los productos que vas agregando");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(288, 288, 288)
                        .addComponent(jLabel2)
                        .addGap(88, 88, 88)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblinfo))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(cmbProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(cmbPorcetaje, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblinfo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPorcetaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarProducto))
                .addGap(19, 19, 19))
        );

        jLabel1.setBackground(new java.awt.Color(255, 204, 51));
        jLabel1.setFont(new java.awt.Font("Ubuntu Mono", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Productos que genera el animal");
        jLabel1.setOpaque(true);

        btnTerminar.setBackground(new java.awt.Color(255, 255, 204));
        btnTerminar.setFont(new java.awt.Font("Ubuntu Mono", 1, 24)); // NOI18N
        btnTerminar.setForeground(new java.awt.Color(0, 0, 0));
        btnTerminar.setText("Terminar");
        btnTerminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminarActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Selecciona el producto y el porcentaje que se genera  (Sin destace)");

        lblInfoPorcentaje.setFont(new java.awt.Font("Ubuntu Mono", 0, 24)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(27, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(139, 139, 139))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(lblInfoPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTerminar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTerminar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInfoPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
        //se agrupan los elementos necesarios para seleccionar el producto que eligio el jugador
        String nombreDelProducto = (String) cmbProductos.getSelectedItem();
        int porcentajeDeProduccion = cmbPorcetaje.getSelectedIndex() + 1;
        int total = contadorDePorcentaje + porcentajeDeProduccion;
        //se verifica que no se ha excedido del limite de porcentaje
        if (total <= PORCENTAJE_MAXIMO) {
            creador.buscarYAgregarProductoAAnimal(nombreDelProducto, porcentajeDeProduccion);
            agregarProductoALaTabla(creador.darleElProductoSeleccionadoAlPanel(nombreDelProducto));
            contadorDePorcentaje=total;
            mostrarInfoEnLabel();
        }
        else{
            avisarQuePaso();
        }

    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void btnTerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminarActionPerformed
        int indice = MotorJuego.getAlmacenador().getMercadoGeneral().getAnimalesDisponibles().getTamaño() - 1;
        Animal animal = MotorJuego.getAlmacenador().getMercadoGeneral().getAnimalesDisponibles().obtenerContenido(indice);
        if (animal.getProductos().getTamaño()>0) {
            this.dispose();
            boolean seSigueCreandoProductos = creador.verificarSiElAnimalEsDestasable();
            if (seSigueCreandoProductos) {
                AgregarProductoConDestaceDialog dialog = new AgregarProductoConDestaceDialog(creador);
                dialog.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(this, "Creacion de animal exitosa", "Animal creado",JOptionPane.INFORMATION_MESSAGE);
            }
            
        }
        else{
            JOptionPane.showMessageDialog(this, "Debes darle al menos un producto al animal", "Sin productos",JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnTerminarActionPerformed

    private void mostrarInfoEnLabel(){
        if(contadorDePorcentaje<PORCENTAJE_MAXIMO){
            lblInfoPorcentaje.setText("Porcentaje disponible: "+(PORCENTAJE_MAXIMO-contadorDePorcentaje));
        }
        else{
            lblInfoPorcentaje.setText("Ya no puedes agregar mas productos");
        }
    }
    
    private void avisarQuePaso(){
        if(contadorDePorcentaje>=PORCENTAJE_MAXIMO){
            JOptionPane.showMessageDialog(this,"Ya no puedes agregar mas productos, presiona terminar", "Productos completados",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(this, "Exediste el limite de produccion vuelve a intentarlo", "Limite de productos excedido", JOptionPane.ERROR_MESSAGE);
            cmbPorcetaje.setSelectedIndex(-1);
        }  
    }
    //metodo que se encarga de colocar el nombre del un producto por cada item del comboBox
    private void colocarProductosACombo() {
  
        for (int i = 0; i < MotorJuego.getAlmacenador().getProductosDisponibles().getTamaño(); i++) {
            Producto producto=MotorJuego.getAlmacenador().getProductosDisponibles().obtenerContenido(i);
            if (!(producto instanceof AlimentoGrano || producto instanceof AlimentoFruto)) {
                if (!producto.isSeObtieneConDestace()) {
                    cmbProductos.addItem("" + producto.getNombre());
                }
            }

        }
    }

    private void colocarListaParaPorcentaje() {
        for (int i = 1; i <= 100; i++) {
            cmbPorcetaje.addItem("" + i);
        }
    }

    private void agregarProductoALaTabla(Producto producto) {
        DefaultTableModel modelo = (DefaultTableModel) tablaProductosAnimalAgregados.getModel();
        modelo.addRow(new Object[]{producto.getNombre(), producto.getPorcetajeDeLaProduccion()});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnTerminar;
    private javax.swing.JComboBox<String> cmbPorcetaje;
    private javax.swing.JComboBox<String> cmbProductos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblInfoPorcentaje;
    private javax.swing.JLabel lblinfo;
    private javax.swing.JTable tablaProductosAnimalAgregados;
    // End of variables declaration//GEN-END:variables
}
