package main;

import ArrumarString.Monetarios;
import javax.swing.JOptionPane;
import static main.GerenciadorComandas.idsAbertos;
import model.dao.ComandaDAO;
import model.dao.ConfDAO;
import model.dao.ItemComandaDAO;
import manual.Manual;

public class NovaRefeicao extends javax.swing.JDialog {
    ConfDAO cDao = new ConfDAO();
    Double valorAVontade;
    
    public void alterarCheckBox(){
        if (cbPeso.isSelected()){
            cbBuffet.setSelected(false);
            lblStringPeso.setText("Valor da Refeição:");
            txtEntradaPreco.setVisible(true);
            lblStringkg.setVisible(true);
            txtEntradaPreco.requestFocus();
        }
        else if (cbBuffet.isSelected()){
            cbPeso.setSelected(false);
            lblStringPeso.setText("Valor fixo: R$ "+GerenciadorComandas.valorMonetario(valorAVontade));
            txtEntradaPreco.setVisible(false);
            lblStringkg.setVisible(false);
        }  
    }

    public NovaRefeicao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        cbPeso.setSelected(true);       
        txtEntradaPreco.setDocument(new Monetarios(7,2));
        
        //Verifica se a configuração de buffet à vontade está ativa
        Double p = cDao.readPreco();
        if (p == 0.0){
            cbBuffet.setEnabled(false);
        }else{
            valorAVontade = p;
        }
        txtEntradaPreco.requestFocus();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo1 = new javax.swing.ButtonGroup();
        lblStringNovoPrato = new javax.swing.JLabel();
        linha = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        lblStringPeso = new javax.swing.JLabel();
        txtEntradaPreco = new javax.swing.JTextField();
        cbBuffet = new javax.swing.JCheckBox();
        cbPeso = new javax.swing.JCheckBox();
        lblStringkg = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtNumeroComanda = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnLancador = new javax.swing.JButton();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        lblManual2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo Prato");
        setMaximumSize(new java.awt.Dimension(516, 290));
        setMinimumSize(new java.awt.Dimension(516, 290));
        setResizable(false);

        lblStringNovoPrato.setBackground(new java.awt.Color(0, 102, 204));
        lblStringNovoPrato.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        lblStringNovoPrato.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStringNovoPrato.setText("Nova Refeição");

        linha.setBackground(new java.awt.Color(0, 0, 0));
        linha.setOpaque(true);

        lblStringPeso.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblStringPeso.setText("Valor da Refeição:");

        txtEntradaPreco.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtEntradaPreco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEntradaPrecoKeyReleased(evt);
            }
        });

        grupo1.add(cbBuffet);
        cbBuffet.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        cbBuffet.setText("Buffet à vontade");
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

        grupo1.add(cbPeso);
        cbPeso.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        cbPeso.setText("Peso");
        cbPeso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbPesoMouseClicked(evt);
            }
        });
        cbPeso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPesoActionPerformed(evt);
            }
        });

        lblStringkg.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblStringkg.setText("R$");

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        txtNumeroComanda.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNumeroComanda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNumeroComandaFocusGained(evt);
            }
        });
        txtNumeroComanda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroComandaKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Código:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumeroComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumeroComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnLancador.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        btnLancador.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\voltar (1).png")); // NOI18N
        btnLancador.setText("Voltar");
        btnLancador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLancadorActionPerformed(evt);
            }
        });

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        lblManual2.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblManual2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblManual2.setText("?");
        lblManual2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblManual2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblManual2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                        .addComponent(lblManual2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbBuffet)
                                    .addComponent(cbPeso)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblStringPeso)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtEntradaPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblStringkg))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnLancador)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblStringNovoPrato)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringNovoPrato)
                    .addComponent(btnLancador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(cbBuffet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbPeso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringkg)
                    .addComponent(txtEntradaPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStringPeso))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblManual2)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(57, 57, 57)
                    .addComponent(linha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(232, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(278, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbBuffetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbBuffetMouseClicked
        alterarCheckBox();
    }//GEN-LAST:event_cbBuffetMouseClicked

    private void cbBuffetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBuffetActionPerformed
        alterarCheckBox();
    }//GEN-LAST:event_cbBuffetActionPerformed

    private void cbPesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPesoActionPerformed
        alterarCheckBox();
    }//GEN-LAST:event_cbPesoActionPerformed

    private void cbPesoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbPesoMouseClicked
        alterarCheckBox();
    }//GEN-LAST:event_cbPesoMouseClicked

    private void txtNumeroComandaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroComandaFocusGained
        
    }//GEN-LAST:event_txtNumeroComandaFocusGained

    private void txtNumeroComandaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroComandaKeyReleased
        if (evt.getKeyCode() == 10){
            ComandaDAO comDao = new ComandaDAO();
            int cod;
            
            if (cDao.readCod() == 1){
                cod = Integer.parseInt(txtNumeroComanda.getText());
            }else{
                cod = comDao.codComanda(txtNumeroComanda.getText());
            }
            
            if (cod == 0){
                if (!txtNumeroComanda.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Código de comanda inválido");
                    txtNumeroComanda.setText("");
                }
            }else{
                try{
                    txtNumeroComanda.setText("");
                    boolean flagComandaAberta = false;
                    for (Integer i : idsAbertos){
                        if (i == cod){
                            flagComandaAberta = true;
                            break;
                        }
                    }
                    if (!flagComandaAberta){
                        GerenciadorComandas.novaComanda(cod);
                    }
                    String valor = txtEntradaPreco.getText();
                    valor = valor.replace("." , "");
                    valor = valor.replace(",", ".");
                    int indice = GerenciadorComandas.retornaIndice(cod);
                    ItemComandaDAO item = new ItemComandaDAO();
                    if (cbPeso.isSelected()){
                        //preco = GerenciadorComandas.arredondarValor(preco);
                        Double precoPrato = Double.parseDouble(valor);
                        if (precoPrato > 0.0){
                            GerenciadorComandas.comandasAbertas.get(indice).setPratos(precoPrato);
                            item.create(GerenciadorComandas.comandasAbertas.get(indice), precoPrato);
                        }else{
                            JOptionPane.showMessageDialog(null, "Insira o valor do prato");
                        }                      
                    }
                    else{
                        GerenciadorComandas.comandasAbertas.get(indice).setPratos(valorAVontade);
                        item.create(GerenciadorComandas.comandasAbertas.get(indice), valorAVontade);
                    }
                    dispose();
                }catch(java.lang.NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Insira o valor do prato");
                }
            }
        }
    }//GEN-LAST:event_txtNumeroComandaKeyReleased

    private void btnLancadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancadorActionPerformed
        
        dispose();
    }//GEN-LAST:event_btnLancadorActionPerformed

    private void txtEntradaPrecoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntradaPrecoKeyReleased
        if (evt.getKeyCode() == 10)
            txtNumeroComanda.requestFocus();
    }//GEN-LAST:event_txtEntradaPrecoKeyReleased

    private void lblManual2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblManual2MouseClicked
        Manual.abrirManual("novaRefeicao");
    }//GEN-LAST:event_lblManual2MouseClicked

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
            java.util.logging.Logger.getLogger(NovaRefeicao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NovaRefeicao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NovaRefeicao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NovaRefeicao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NovaRefeicao dialog = new NovaRefeicao(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnLancador;
    private javax.swing.JCheckBox cbBuffet;
    private javax.swing.JCheckBox cbPeso;
    private javax.swing.ButtonGroup grupo1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblManual2;
    private javax.swing.JLabel lblStringNovoPrato;
    private javax.swing.JLabel lblStringPeso;
    private javax.swing.JLabel lblStringkg;
    private javax.swing.Box.Filler linha;
    private javax.swing.Box.Filler linha1;
    private javax.swing.JTextField txtEntradaPreco;
    private javax.swing.JTextField txtNumeroComanda;
    // End of variables declaration//GEN-END:variables
}
