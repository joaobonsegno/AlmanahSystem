package main;

import manual.Manual;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.Produto;
import model.dao.ProdutoDAO;
import model.dao.VendaDAO;

public class GerenciadorRelatorios extends javax.swing.JFrame {

    public static ArrayList<Produto> listaProdutos = new ArrayList<>();
    ProdutoDAO pDao;
    public static Produto prodSelecionado;
    public static String dMaior;
    public static String dMenor;

    public GerenciadorRelatorios() {
        initComponents();
        this.setLocationRelativeTo(null);

        pDao = new ProdutoDAO();
        listaProdutos.removeAll(listaProdutos);
        for (Produto p : pDao.read()) {
            listaProdutos.add(p);
        }
        CadastrarMateriaPrima.materiasSelecionadas.removeAll(CadastrarMateriaPrima.materiasSelecionadas);
        criarComboBox();
        
    }

    public void criarComboBox() {
        cbCategorias.removeAllItems();
        cbCategorias.addItem("");
        cbCategorias.addItem("Estoque");
        cbCategorias.addItem("Fluxo de Caixa");
        cbCategorias.addItem("Produtos");
        cbCategorias.addItem("Vendas");
    }

    // -----------------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStringProdutos = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnLancador = new javax.swing.JButton();
        lblStringNomeProduto1 = new javax.swing.JLabel();
        cbCategorias = new javax.swing.JComboBox<>();
        btnOk1 = new javax.swing.JButton();
        dataMenor = new com.toedter.calendar.JDateChooser();
        dataMaior = new com.toedter.calendar.JDateChooser();
        lblStringNomeProduto2 = new javax.swing.JLabel();
        lblStringNomeProduto3 = new javax.swing.JLabel();
        linha2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        lblManual = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Gerenciador de Relatórios");
        setMinimumSize(new java.awt.Dimension(575, 325));
        setResizable(false);

        btnStringProdutos.setBackground(new java.awt.Color(0, 102, 204));
        btnStringProdutos.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        btnStringProdutos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringProdutos.setText("Gerenciador de Relatórios");

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        btnLancador.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        btnLancador.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\voltar (1).png")); // NOI18N
        btnLancador.setText("Menu");
        btnLancador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLancadorActionPerformed(evt);
            }
        });

        lblStringNomeProduto1.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNomeProduto1.setText("Tipo de Relatório:");

        cbCategorias.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cbCategorias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCategorias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCategoriasItemStateChanged(evt);
            }
        });
        cbCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoriasActionPerformed(evt);
            }
        });

        btnOk1.setBackground(new java.awt.Color(0, 153, 204));
        btnOk1.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        btnOk1.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\pesquisar (1).png")); // NOI18N
        btnOk1.setText(" Gerar Relatório");
        btnOk1.setBorder(new javax.swing.border.MatteBorder(null));
        btnOk1.setBorderPainted(false);
        btnOk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOk1ActionPerformed(evt);
            }
        });

        dataMenor.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        dataMaior.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        lblStringNomeProduto2.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNomeProduto2.setText("Data Final:");

        lblStringNomeProduto3.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNomeProduto3.setText("Data Inicial:");

        linha2.setBackground(new java.awt.Color(0, 0, 0));
        linha2.setOpaque(true);

        lblManual.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblManual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblManual.setText("?");
        lblManual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblManual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblManualMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnOk1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(143, 143, 143))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblStringNomeProduto1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbCategorias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(87, 87, 87))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dataMenor, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblStringNomeProduto3))
                                .addGap(113, 113, 113)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblStringNomeProduto2)
                                    .addComponent(dataMaior, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(lblManual))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLancador)
                        .addGap(18, 18, 18)
                        .addComponent(btnStringProdutos)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblManual)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLancador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnStringProdutos))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStringNomeProduto1))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStringNomeProduto2)
                            .addComponent(lblStringNomeProduto3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dataMenor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dataMaior, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addComponent(btnOk1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(264, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(311, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLancadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancadorActionPerformed
        new Menu().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLancadorActionPerformed

    private void cbCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoriasActionPerformed

    }//GEN-LAST:event_cbCategoriasActionPerformed

    private void cbCategoriasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCategoriasItemStateChanged
        if (cbCategorias.getSelectedIndex() == 0 || cbCategorias.getSelectedIndex() == 1){
            dataMaior.setEnabled(false);
            dataMenor.setEnabled(false);
        }else{
            dataMaior.setEnabled(true);
            dataMenor.setEnabled(true);
        }
    }//GEN-LAST:event_cbCategoriasItemStateChanged

    private void btnOk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOk1ActionPerformed
        //try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");           
            Integer selecionado = (Integer) cbCategorias.getSelectedIndex();
            VendaDAO v = new VendaDAO();
            switch (selecionado) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Selecione o tipo de relatório desejado");
                    break;
                case 1: // ESTOQUE
                    new RelatorioEstoque().setVisible(true);
                    dispose();
                    break;
 //---------------------------------------------- FLUXO DE CAIXA ----------------------------------------------------                      
                case 2: // FLUXO DE CAIXA
                    try {
                        dMaior = sdf.format(dataMaior.getDate());
                        dMenor = sdf.format(dataMenor.getDate());
                        if (v.diferencaDatas(GerenciadorRelatorios.dMaior, GerenciadorRelatorios.dMenor) >= 0) {
                            new RelatorioFluxoDeCaixa().setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "A data inicial deve ser maior ou igual à data final");
                        }
                    } catch (java.lang.NullPointerException ex) {
                        JOptionPane.showMessageDialog(null, "Informe as datas inicial e final");
                    }
                    break;
 //----------------------------------------------------- PRODUTOS ----------------------------------------------------   
                case 3: // PRODUTOS
                    try {
                        dMaior = sdf.format(dataMaior.getDate());
                        dMenor = sdf.format(dataMenor.getDate());
                        if (v.diferencaDatas(GerenciadorRelatorios.dMaior, GerenciadorRelatorios.dMenor) >= 0) {
                            new RelatorioProdutos().setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "A data inicial deve ser maior ou igual à data final");
                        }
                    } catch (java.lang.NullPointerException ex) {
                        JOptionPane.showMessageDialog(null, "Informe as datas inicial e final");
                    }
                    break;
//----------------------------------------------------- VENDAS ------------------------------------------------------                     
                case 4: // VENDAS
                    try {
                        dMaior = sdf.format(dataMaior.getDate());
                        dMenor = sdf.format(dataMenor.getDate());
                        if (v.diferencaDatas(GerenciadorRelatorios.dMaior, GerenciadorRelatorios.dMenor) >= 0) {
                            new RelatorioVendas().setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "A data inicial deve ser maior ou igual à data final");
                        }
                    } catch (java.lang.NullPointerException ex) {
                        JOptionPane.showMessageDialog(null, "Informe as datas inicial e final");
                    }
                    break;
            }
    
        /*} catch (java.lang.NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Informe as datas inicial e final");
        }*/
    }//GEN-LAST:event_btnOk1ActionPerformed

    private void lblManualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblManualMouseClicked
        Manual.abrirManual("gerenciadorRelatorios");
    }//GEN-LAST:event_lblManualMouseClicked

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
                if ("WebLaF".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GerenciadorRelatorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciadorRelatorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciadorRelatorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciadorRelatorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerenciadorRelatorios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLancador;
    private javax.swing.JButton btnOk1;
    private javax.swing.JLabel btnStringProdutos;
    private javax.swing.JComboBox<String> cbCategorias;
    private com.toedter.calendar.JDateChooser dataMaior;
    private com.toedter.calendar.JDateChooser dataMenor;
    private javax.swing.JLabel lblManual;
    private javax.swing.JLabel lblStringNomeProduto1;
    private javax.swing.JLabel lblStringNomeProduto2;
    private javax.swing.JLabel lblStringNomeProduto3;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    // End of variables declaration//GEN-END:variables
}
