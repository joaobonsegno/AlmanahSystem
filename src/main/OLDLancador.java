package main;

import javax.swing.JOptionPane;
import model.bean.Categoria;
import model.dao.CaixaDAO;


public class OLDLancador extends javax.swing.JFrame {
    public static boolean turno, caixa;
    CaixaDAO caixaDao;
    public OLDLancador() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        if(Login.caixaAtual != null){
            btnAbrirCaixa.setText("Fechar Caixa");
            btnAbrirTurno.setEnabled(false);
        }
    }
    
    public void fecharCaixa(){
        caixaDao = new CaixaDAO();
        Login.caixaAtual.setStatus(0);
        String horario = Login.caixaAtual.dataAtual();
        Login.caixaAtual.setDataFechamento(horario);
        caixaDao.update(Login.caixaAtual);
        Login.caixaAtual = null;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStringLancador = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        linha2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        lblStringUsuario = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblStringFuncao = new javax.swing.JLabel();
        lblFuncao = new javax.swing.JLabel();
        btnMenu = new javax.swing.JButton();
        btnAbrirTurno = new javax.swing.JButton();
        btnAbrirCaixa = new javax.swing.JButton();
        btnGerenciador = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lançador");
        setResizable(false);

        lblStringLancador.setBackground(new java.awt.Color(0, 102, 204));
        lblStringLancador.setFont(new java.awt.Font("Comic Sans MS", 0, 17)); // NOI18N
        lblStringLancador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStringLancador.setText("Lançador");

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        linha2.setBackground(new java.awt.Color(0, 0, 0));
        linha2.setOpaque(true);

        lblStringUsuario.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        lblStringUsuario.setText("Usuário:");

        lblUsuario.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        lblUsuario.setText("Joao");

        lblStringFuncao.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        lblStringFuncao.setText("Função:");

        lblFuncao.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        lblFuncao.setText("Gerente");

        btnMenu.setFont(new java.awt.Font("Comic Sans MS", 0, 15)); // NOI18N
        btnMenu.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\voltar (1).png")); // NOI18N
        btnMenu.setText("Menu");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        btnAbrirTurno.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnAbrirTurno.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\turno.png")); // NOI18N
        btnAbrirTurno.setText("Abrir Turno");
        btnAbrirTurno.setBorder(null);
        btnAbrirTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirTurnoActionPerformed(evt);
            }
        });

        btnAbrirCaixa.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnAbrirCaixa.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\caixa2.png")); // NOI18N
        btnAbrirCaixa.setText("Abrir Caixa");
        btnAbrirCaixa.setBorder(new javax.swing.border.MatteBorder(null));
        btnAbrirCaixa.setBorderPainted(false);
        btnAbrirCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirCaixaActionPerformed(evt);
            }
        });

        btnGerenciador.setFont(new java.awt.Font("Corbel", 0, 16)); // NOI18N
        btnGerenciador.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\comanda.png")); // NOI18N
        btnGerenciador.setText("  Gerenciador de Comandas");
        btnGerenciador.setBorder(new javax.swing.border.MatteBorder(null));
        btnGerenciador.setBorderPainted(false);
        btnGerenciador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(linha2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblStringUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblStringFuncao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGerenciador, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAbrirTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(btnAbrirCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(lblStringLancador, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblStringUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblUsuario)
                        .addComponent(lblStringFuncao)
                        .addComponent(lblFuncao)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStringLancador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAbrirCaixa, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(btnAbrirTurno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGerenciador, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbrirTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirTurnoActionPerformed

    }//GEN-LAST:event_btnAbrirTurnoActionPerformed

    private void btnGerenciadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerenciadorActionPerformed

        if(Login.caixaAtual != null){
            new GerenciadorComandas().setVisible(true);     
            dispose();
        }else{
            JOptionPane.showMessageDialog(null, "O caixa deve estar aberto!");
        }
        
             
    }//GEN-LAST:event_btnGerenciadorActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        new Menu().setVisible(true);     
        dispose();
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnAbrirCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirCaixaActionPerformed
        Categoria asd = new Categoria();
        
        if(Login.caixaAtual != null){
            if(GerenciadorComandas.comandasAbertas.isEmpty()){
                fecharCaixa();
                btnAbrirTurno.setEnabled(true);
                btnAbrirCaixa.setText("Abrir Caixa");
            }else{
                JOptionPane.showMessageDialog(null, "Há comandas abertas");
            }
        }else{
            Suprimento suprimento = new Suprimento(new javax.swing.JFrame(), true);
            suprimento.setVisible(true); 
            dispose();
        }
        
    }//GEN-LAST:event_btnAbrirCaixaActionPerformed

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
            java.util.logging.Logger.getLogger(OLDLancador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OLDLancador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OLDLancador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OLDLancador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OLDLancador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirCaixa;
    private javax.swing.JButton btnAbrirTurno;
    private javax.swing.JButton btnGerenciador;
    private javax.swing.JButton btnMenu;
    private javax.swing.JLabel lblFuncao;
    private javax.swing.JLabel lblStringFuncao;
    private javax.swing.JLabel lblStringLancador;
    private javax.swing.JLabel lblStringUsuario;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    // End of variables declaration//GEN-END:variables
}
