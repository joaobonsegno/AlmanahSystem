package main;

import javax.swing.JOptionPane;
import model.bean.LogCaixa;
import model.dao.CaixaDAO;
import model.dao.LogCaixaDAO;

public class Sangria extends javax.swing.JDialog {
        public static boolean flagSuprimento;
    public Sangria(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnConfirmar);
        LogCaixaDAO logDao = new LogCaixaDAO();
        if (Login.caixaAtual != null){
            lblValor.setText(" R$ "+GerenciadorComandas.valorMonetario(Login.caixaAtual.getDinheiro()));
        }else{
            System.out.println("");
            lblValor.setText(" R$ "+GerenciadorComandas.valorMonetario(logDao.readLast().getSaldo()));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtStringSuprimento = new javax.swing.JLabel();
        linha = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        lblInsiraSuprimento = new javax.swing.JLabel();
        txtEntradaSuprimento = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();
        lblInsiraSuprimento1 = new javax.swing.JLabel();
        lblInsiraSuprimento2 = new javax.swing.JLabel();
        lblValor = new javax.swing.JLabel();
        lblInsiraSuprimento3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Suprimento do Caixa");
        setMinimumSize(new java.awt.Dimension(372, 234));
        setResizable(false);

        txtStringSuprimento.setBackground(new java.awt.Color(0, 102, 204));
        txtStringSuprimento.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        txtStringSuprimento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtStringSuprimento.setText("Sangria");

        linha.setBackground(new java.awt.Color(0, 0, 0));
        linha.setOpaque(true);

        lblInsiraSuprimento.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblInsiraSuprimento.setText("Insira o valor da sangria:");

        txtEntradaSuprimento.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

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

        lblInsiraSuprimento1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblInsiraSuprimento1.setText("R$");

        lblInsiraSuprimento2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblInsiraSuprimento2.setText("Valor atual em caixa:");

        lblValor.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblValor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblValor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblInsiraSuprimento3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblInsiraSuprimento3.setText("R$");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblInsiraSuprimento2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblValor, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblInsiraSuprimento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEntradaSuprimento, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblInsiraSuprimento1)
                            .addComponent(lblInsiraSuprimento3))
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtStringSuprimento, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtStringSuprimento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblInsiraSuprimento2)
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblInsiraSuprimento3)
                        .addGap(9, 9, 9)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblInsiraSuprimento)
                            .addComponent(lblInsiraSuprimento1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtEntradaSuprimento, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addComponent(linha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(190, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        flagSuprimento = false;
        new GerenciadorCaixa().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        try{
            CaixaDAO cDao = new CaixaDAO();
            LogCaixaDAO logDao = new LogCaixaDAO();
            LogCaixa l = new LogCaixa(1);
            l.setCategoria("Caixa");
            l.setData(l.dataAtual());
            l.setTipo("Débito");
            
            String valor = txtEntradaSuprimento.getText();
            valor = GerenciadorComandas.tornarCompativel(valor);
            
            l.setDescricao(Login.funcAtual.getNome()+" retirou R$ "+valor+" do caixa");
            l.setValor(Double.parseDouble(valor));

            if (Double.parseDouble(valor) != 0){
                if (Double.parseDouble(valor) <= Login.caixaAtual.getDinheiro()){
                    Double dinheiroAtual = Login.caixaAtual.getDinheiro();
                    dinheiroAtual -= Double.parseDouble(valor);
                    Login.caixaAtual.setDinheiro(dinheiroAtual);
                    cDao.update(Login.caixaAtual);
                    l.setSaldo(dinheiroAtual);
                    logDao.create(l);
                }else{
                    JOptionPane.showMessageDialog(null, "Saldo em caixa insuficiente");
                }
            }
            
            
            new GerenciadorCaixa().setVisible(true);
            dispose();
        }catch(java.lang.NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Insira um valor válido");
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

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
            java.util.logging.Logger.getLogger(Sangria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sangria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sangria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sangria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Sangria dialog = new Sangria(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel lblInsiraSuprimento;
    private javax.swing.JLabel lblInsiraSuprimento1;
    private javax.swing.JLabel lblInsiraSuprimento2;
    private javax.swing.JLabel lblInsiraSuprimento3;
    private javax.swing.JLabel lblValor;
    private javax.swing.Box.Filler linha;
    private javax.swing.JTextField txtEntradaSuprimento;
    private javax.swing.JLabel txtStringSuprimento;
    // End of variables declaration//GEN-END:variables
}
