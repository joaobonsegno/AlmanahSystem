package main;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.LogCaixa;
import model.bean.Produto;
import model.dao.CaixaDAO;
import model.dao.LogCaixaDAO;

public class GerenciadorCaixa extends javax.swing.JFrame {
    ArrayList<Produto> listaProdutos = new ArrayList<>();
    
    public GerenciadorCaixa() {
        initComponents();
        jtCaixa.setRowHeight(22);
        jtCaixa.getColumnModel().getColumn(0).setPreferredWidth(80); 
        jtCaixa.getColumnModel().getColumn(1).setPreferredWidth(175);
        jtCaixa.getColumnModel().getColumn(2).setPreferredWidth(80);
        jtCaixa.getColumnModel().getColumn(3).setPreferredWidth(80);
        
        jtCaixa.getColumnModel().getColumn(0).setMinWidth(80);
        jtCaixa.getColumnModel().getColumn(1).setMinWidth(175);
        jtCaixa.getColumnModel().getColumn(2).setMinWidth(80);
        jtCaixa.getColumnModel().getColumn(3).setMinWidth(80);
        
        jtCaixa.getColumnModel().getColumn(0).setMaxWidth(80);
        jtCaixa.getColumnModel().getColumn(1).setMaxWidth(175);
        jtCaixa.getColumnModel().getColumn(2).setMaxWidth(80);
        jtCaixa.getColumnModel().getColumn(3).setMaxWidth(80);
        //lblComanda.setText(comString);
        this.criarTabela();
        
        if (Login.caixaAtual != null){
            btnAbrirCaixa.setBackground(Color.yellow);
            btnAbrirCaixa.setText(" Fechar Caixa");
            lblStatus.setText("Aberto");
            //lblStatus.setForeground(Color.getHSBColor(112,43,93));
            lblStatus.setForeground(new java.awt.Color(51, 204, 0));
            btnSuprimento.setEnabled(true);
            btnSangria.setEnabled(true);
            lblData.setText(Login.caixaAtual.getDataAbertura());
            lblValor.setText(" R$ "+GerenciadorComandas.valorMonetario(Login.caixaAtual.getDinheiro()));
        }else{
            LogCaixaDAO logDao = new LogCaixaDAO();
            btnSuprimento.setEnabled(false);
            btnSangria.setEnabled(false);
            lblData.setText("");
            lblValor.setText(" R$ "+GerenciadorComandas.valorMonetario(logDao.readLast().getSaldo()));
            //lblValor.setText("");
        }
        this.setLocationRelativeTo(null);
        
        /*JFormattedTextField tf = ((JSpinner.DefaultEditor) SpinnerQtd.getEditor()).getTextField();
        tf.setEditable(false);
        SpinnerQtd.setModel(nm);*/
    }
    
    public void criarTabela(){  
        LogCaixaDAO logDao = new LogCaixaDAO();
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtCaixa.getModel();
        for (LogCaixa l: logDao.read()){
            if(l.getStatus() == 1){
                String valor = GerenciadorComandas.valorMonetario(l.getValor());
                String valor2 = GerenciadorComandas.valorMonetario(l.getSaldo());
                dtmBebidas.addRow(
                    new Object[]{
                        valor,
                        l.getData(),
                        l.getTipo(),
                        valor2}
                );
            }
        }
    }
    
    public void fecharCaixa(){
        CaixaDAO caixaDao = new CaixaDAO();
        caixaDao = new CaixaDAO();
        LogCaixaDAO logDao = new LogCaixaDAO();
        Login.caixaAtual.setStatus(0);
        String horario = Login.caixaAtual.dataAtual();
        Login.caixaAtual.setDataFechamento(horario);
        caixaDao.update(Login.caixaAtual);
        Login.caixaAtual = null;
        btnSangria.setEnabled(false);
        btnSuprimento.setEnabled(false);
        lblStatus.setText("Fechado");
        lblStatus.setForeground(Color.red);
        lblData.setText("");
        
        logDao.switchStatus();
        new GerenciadorCaixa().setVisible(true);
        dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStringGerenciador = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        linha2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        jScrollPane1 = new javax.swing.JScrollPane();
        jtCaixa = new javax.swing.JTable();
        lblStringValor = new javax.swing.JLabel();
        lblStringData = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblValor = new javax.swing.JLabel();
        btnSuprimento = new javax.swing.JButton();
        btnSangria = new javax.swing.JButton();
        lblStringStatus = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        btnMenu = new javax.swing.JButton();
        btnAbrirCaixa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Caixa");
        setMaximumSize(new java.awt.Dimension(861, 466));
        setMinimumSize(new java.awt.Dimension(861, 466));
        setResizable(false);

        btnStringGerenciador.setBackground(new java.awt.Color(0, 102, 204));
        btnStringGerenciador.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        btnStringGerenciador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringGerenciador.setText("Caixa");

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        linha2.setBackground(new java.awt.Color(0, 0, 0));
        linha2.setOpaque(true);

        jtCaixa.setBorder(new javax.swing.border.MatteBorder(null));
        jtCaixa.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jtCaixa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Valor (R$)", "Data", "Tipo", "Saldo (R$)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtCaixa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtCaixaFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jtCaixa);
        if (jtCaixa.getColumnModel().getColumnCount() > 0) {
            jtCaixa.getColumnModel().getColumn(0).setResizable(false);
            jtCaixa.getColumnModel().getColumn(1).setResizable(false);
            jtCaixa.getColumnModel().getColumn(2).setResizable(false);
            jtCaixa.getColumnModel().getColumn(3).setResizable(false);
        }

        lblStringValor.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringValor.setText("Valor em caixa:");

        lblStringData.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringData.setText("Data de abertura:");

        lblData.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblData.setText("04/06/1999 16:50:53");
        lblData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblValor.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblValor.setText(" R$");
        lblValor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnSuprimento.setBackground(new java.awt.Color(27, 176, 64));
        btnSuprimento.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnSuprimento.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\dinheiro (2).png")); // NOI18N
        btnSuprimento.setText(" Suprimento");
        btnSuprimento.setBorder(new javax.swing.border.MatteBorder(null));
        btnSuprimento.setBorderPainted(false);
        btnSuprimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuprimentoActionPerformed(evt);
            }
        });

        btnSangria.setBackground(new java.awt.Color(255, 102, 102));
        btnSangria.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnSangria.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\tirar dinheiro (1).png")); // NOI18N
        btnSangria.setText(" Sangria");
        btnSangria.setBorder(new javax.swing.border.MatteBorder(null));
        btnSangria.setBorderPainted(false);
        btnSangria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSangriaActionPerformed(evt);
            }
        });

        lblStringStatus.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringStatus.setText("Status do Caixa:");

        lblStatus.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(255, 0, 51));
        lblStatus.setText("Fechado");

        btnMenu.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        btnMenu.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\voltar (1).png")); // NOI18N
        btnMenu.setText("Menu");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        btnAbrirCaixa.setBackground(new java.awt.Color(153, 153, 0));
        btnAbrirCaixa.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnAbrirCaixa.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\cadeado (1).png")); // NOI18N
        btnAbrirCaixa.setText(" Abrir Caixa");
        btnAbrirCaixa.setBorder(new javax.swing.border.MatteBorder(null));
        btnAbrirCaixa.setBorderPainted(false);
        btnAbrirCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirCaixaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnMenu)
                        .addGap(261, 261, 261)
                        .addComponent(btnStringGerenciador, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblStringData)
                                            .addComponent(lblStringValor)
                                            .addComponent(lblStringStatus))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblValor, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(113, 113, 113)
                                        .addComponent(btnAbrirCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(46, 46, 46))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(btnSuprimento, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSangria, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, 858, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 1, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnStringGerenciador)
                    .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblValor, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblStringValor)
                                        .addGap(29, 29, 29)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(lblStringData)))
                                .addGap(50, 50, 50))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblStringStatus)
                                    .addComponent(lblStatus))
                                .addGap(178, 178, 178)))
                        .addComponent(btnAbrirCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSangria, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSuprimento, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(404, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(452, Short.MAX_VALUE)))
        );

        getAccessibleContext().setAccessibleName("Caixa");

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    private void jtCaixaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtCaixaFocusGained

    }//GEN-LAST:event_jtCaixaFocusGained

    private void btnSuprimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuprimentoActionPerformed
        Suprimento suprimento = new Suprimento(new javax.swing.JFrame(), true);
        suprimento.setVisible(true); 
        dispose();
        
    }//GEN-LAST:event_btnSuprimentoActionPerformed
        
    private void btnSangriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSangriaActionPerformed
        Sangria sangria = new Sangria(new javax.swing.JFrame(), true);
        sangria.setVisible(true); 
        dispose();
    }//GEN-LAST:event_btnSangriaActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        new Menu().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnAbrirCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirCaixaActionPerformed
        if(Login.caixaAtual != null){
            if(GerenciadorComandas.comandasAbertas.isEmpty()){
                fecharCaixa();
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
                if ("WebLaF".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GerenciadorCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciadorCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciadorCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciadorCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new GerenciadorCaixa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirCaixa;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnSangria;
    private javax.swing.JLabel btnStringGerenciador;
    private javax.swing.JButton btnSuprimento;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtCaixa;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblStringData;
    private javax.swing.JLabel lblStringStatus;
    private javax.swing.JLabel lblStringValor;
    private javax.swing.JLabel lblValor;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    // End of variables declaration//GEN-END:variables
}


