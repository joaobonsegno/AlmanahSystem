package main;

import ArrumarString.Monetarios;
import javax.swing.JOptionPane;
import model.dao.ConfDAO;

public class Configuracoes extends javax.swing.JDialog {
        public static boolean flagSuprimento;
    public Configuracoes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnConfirmar);
        txtPreco.setDocument(new Monetarios(7,2));
        
        ConfDAO cDao = new ConfDAO();
        int i = cDao.readCod(); //Leitura da comandas ser manipulada pelo ID. Se i = 1, então elas PODEM ser manipuladas pelo ID
        Double p = cDao.readPreco(); // Se P = 0.0, então o status do buffet à vontade é 0;
        
        if (i == 1){
            cbComandas.setSelected(true);
        }
        if (p == 0.0){
            cbBuffet.setSelected(false);
            jPanel1.setVisible(false);
        }else{
            cbBuffet.setSelected(true);
            String preco = GerenciadorComandas.valorMonetario(p);
            txtPreco.setText(preco);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStringSuprimento = new javax.swing.JLabel();
        linha = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnCancelar = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        cbComandas = new javax.swing.JCheckBox();
        cbBuffet = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        txtPreco = new javax.swing.JTextField();
        lblInsiraSuprimento = new javax.swing.JLabel();
        lblInsiraSuprimento1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Configurações");
        setMinimumSize(new java.awt.Dimension(385, 256));
        setResizable(false);

        lblStringSuprimento.setBackground(new java.awt.Color(0, 102, 204));
        lblStringSuprimento.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        lblStringSuprimento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStringSuprimento.setText("Configurações");

        linha.setBackground(new java.awt.Color(0, 0, 0));
        linha.setOpaque(true);

        btnCancelar.setBackground(new java.awt.Color(255, 0, 51));
        btnCancelar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(new javax.swing.border.MatteBorder(null));
        btnCancelar.setBorderPainted(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnConfirmar.setBackground(new java.awt.Color(0, 153, 51));
        btnConfirmar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnConfirmar.setText("Confirmar");
        btnConfirmar.setBorder(new javax.swing.border.MatteBorder(null));
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        cbComandas.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        cbComandas.setText("Permitir que comandas sejam manipuladas pelo ID");

        cbBuffet.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        cbBuffet.setText("Permitir buffet à vontade");
        cbBuffet.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbBuffetItemStateChanged(evt);
            }
        });
        cbBuffet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbBuffetMouseClicked(evt);
            }
        });
        cbBuffet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBuffetActionPerformed(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        txtPreco.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        lblInsiraSuprimento.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblInsiraSuprimento.setText("Valor:");

        lblInsiraSuprimento1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblInsiraSuprimento1.setText("R$");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblInsiraSuprimento)
                .addGap(6, 6, 6)
                .addComponent(lblInsiraSuprimento1)
                .addGap(1, 1, 1)
                .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInsiraSuprimento1)
                    .addComponent(lblInsiraSuprimento))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbComandas)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbBuffet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 20, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(lblStringSuprimento))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblStringSuprimento)
                .addGap(32, 32, 32)
                .addComponent(cbComandas)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(cbBuffet)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(51, 51, 51)
                    .addComponent(linha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(204, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(244, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        ConfDAO cDao = new ConfDAO();

        if (cbComandas.isSelected()){
            cDao.updateCod(1);
        }else{
            cDao.updateCod(0);
        }     
        
        if (cbBuffet.isSelected()){
            String valor = txtPreco.getText();
            valor = valor.replace(".", "");
            valor = valor.replace(",", ".");
            Double v = Double.parseDouble(valor);
            if (v == 0.0){
                JOptionPane.showMessageDialog(null, "Insira o valor do buffet à vontade");
            }else{
                cDao.updatePreco(1, v);
                dispose();
            }          
        }else{
            cDao.updatePreco(0);
            dispose();
        }
        
        
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void cbBuffetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBuffetActionPerformed
        if (cbBuffet.isSelected()){
            ConfDAO cDao = new ConfDAO();
            Double p = cDao.readPreco();
            String preco = GerenciadorComandas.valorMonetario(p);           
            jPanel1.setVisible(true);    
            txtPreco.setText(preco);
        }else{
            jPanel1.setVisible(false);
        }
    }//GEN-LAST:event_cbBuffetActionPerformed

    private void cbBuffetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbBuffetMouseClicked
    
    }//GEN-LAST:event_cbBuffetMouseClicked

    private void cbBuffetItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbBuffetItemStateChanged
        
    }//GEN-LAST:event_cbBuffetItemStateChanged

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
            java.util.logging.Logger.getLogger(Configuracoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Configuracoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Configuracoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Configuracoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Configuracoes dialog = new Configuracoes(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JCheckBox cbBuffet;
    private javax.swing.JCheckBox cbComandas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblInsiraSuprimento;
    private javax.swing.JLabel lblInsiraSuprimento1;
    private javax.swing.JLabel lblStringSuprimento;
    private javax.swing.Box.Filler linha;
    private javax.swing.Box.Filler linha1;
    private javax.swing.JTextField txtPreco;
    // End of variables declaration//GEN-END:variables
}
