package main;

public class NovaComanda extends javax.swing.JDialog {
    
    public NovaComanda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        String numNovaComanda = Integer.toString(GerenciadorComandas.numeroNovaComanda);
        lblNovoID.setText(numNovaComanda);
        getRootPane().setDefaultButton(btnConfirmar);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStringNovaComanda = new javax.swing.JLabel();
        linha = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        lblStringNovoID = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();
        lblNovoID = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nova Comanda");
        setMaximumSize(new java.awt.Dimension(369, 184));
        setMinimumSize(new java.awt.Dimension(369, 184));
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        lblStringNovaComanda.setBackground(new java.awt.Color(0, 102, 204));
        lblStringNovaComanda.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        lblStringNovaComanda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStringNovaComanda.setText("Nova Comanda");

        linha.setBackground(new java.awt.Color(0, 0, 0));
        linha.setOpaque(true);

        lblStringNovoID.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNovoID.setText("Número da Comanda:");

        btnCancelar.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(new javax.swing.border.MatteBorder(null));
        btnCancelar.setBorderPainted(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnConfirmar.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        btnConfirmar.setText("Confirmar");
        btnConfirmar.setBorder(new javax.swing.border.MatteBorder(null));
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        lblNovoID.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblNovoID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNovoID.setText("10");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(lblStringNovaComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(linha, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblStringNovoID)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNovoID, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(2, 2, 2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblStringNovaComanda)
                .addGap(9, 9, 9)
                .addComponent(linha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringNovoID, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNovoID, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        GerenciadorComandas.numeroNovaComanda -= 1;
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
       
       GerenciadorComandas.lblErro.setVisible(false);
       GerenciadorComandas.novaComanda();
       dispose();
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        /*Character c = evt.getKeyChar();
        if (c != '\n'){
            String t = c.toString();
            lblNovoID.setText(t);
        }*/
        
    }//GEN-LAST:event_formKeyReleased

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        
    }//GEN-LAST:event_formKeyTyped

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
    }//GEN-LAST:event_formKeyPressed

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
            java.util.logging.Logger.getLogger(NovaComanda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NovaComanda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NovaComanda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NovaComanda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NovaComanda dialog = new NovaComanda(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel lblNovoID;
    private javax.swing.JLabel lblStringNovaComanda;
    private javax.swing.JLabel lblStringNovoID;
    private javax.swing.Box.Filler linha;
    // End of variables declaration//GEN-END:variables
}
