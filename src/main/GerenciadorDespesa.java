package main;

import ArrumarString.SoNumeros;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.bean.AgendaDespesa;
import model.bean.Cliente;
import model.bean.Produto;
import model.dao.AgendaDespesaDAO;
import model.dao.ClienteDAO;
import model.dao.ProdutoDAO;
import static org.castor.core.util.Messages.message;

public class GerenciadorDespesa extends javax.swing.JFrame {
    public static int opcaoEscolhida, idEscolhido;
    
    public GerenciadorDespesa() {
        initComponents();
        this.setLocationRelativeTo(null);
        btnExcluir.setEnabled(false);
        btnAlterar2.setEnabled(false);
        this.formatarTabela();
        this.criarTabela();
    }

    public void criarTabela() {
        this.limparTabela();
        AgendaDespesaDAO a = new AgendaDespesaDAO();
        
        DefaultTableModel dtm = (DefaultTableModel) jtDespesas.getModel();

        for (AgendaDespesa d : a.read()){
            dtm.addRow(
                new Object[]{
                    d.getDia(),                   
                    "R$ " + GerenciadorComandas.valorMonetario(d.getValor()),
                    d.getDescricao(),
                    d.getId()}
            );
        }
    }

    public void criarTabelaDescricao(String desc) {
        this.limparTabela();
        AgendaDespesaDAO a = new AgendaDespesaDAO();
        
        DefaultTableModel dtm = (DefaultTableModel) jtDespesas.getModel();

        for (AgendaDespesa d : a.readForDescricao(desc)){
            dtm.addRow(
                new Object[]{
                    d.getDia(),                    
                    "R$ " + GerenciadorComandas.valorMonetario(d.getValor()),
                    d.getDescricao(),
                    d.getId()}
            );
        }
    }

    public void limparTabela() {
        DefaultTableModel dtmProdutos = (DefaultTableModel) jtDespesas.getModel();
        int i = dtmProdutos.getRowCount();

        for (int j = 0; j < i; j++) {
            dtmProdutos.removeRow(0);
        }
    }

    public void formatarTabela() {
        jtDespesas.setRowHeight(27);
        jtDespesas.getColumn("Dia").setCellRenderer(centro);
        jtDespesas.getColumn("Valor").setCellRenderer(centro);
        jtDespesas.getColumnModel().getColumn(0).setPreferredWidth(50);
        jtDespesas.getColumnModel().getColumn(1).setPreferredWidth(150);
        jtDespesas.getColumnModel().getColumn(2).setPreferredWidth(600);
        jtDespesas.getColumnModel().getColumn(3).setPreferredWidth(0);
        
        jtDespesas.getColumnModel().getColumn(0).setMinWidth(50);
        jtDespesas.getColumnModel().getColumn(1).setMinWidth(150);
        jtDespesas.getColumnModel().getColumn(2).setMinWidth(600);
        jtDespesas.getColumnModel().getColumn(3).setMinWidth(0);

        jtDespesas.getColumnModel().getColumn(0).setMaxWidth(50);
        jtDespesas.getColumnModel().getColumn(1).setMaxWidth(150);
        jtDespesas.getColumnModel().getColumn(2).setMaxWidth(600);
        jtDespesas.getColumnModel().getColumn(3).setMaxWidth(0);
    }

    DefaultTableCellRenderer centro = new DefaultTableCellRenderer() {
        public void setValue(Object value) {
            setHorizontalAlignment(JLabel.CENTER);
            super.setValue(value);
        }
    };
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStringProdutos = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnLancador = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtDespesas = new javax.swing.JTable();
        lblStringNomeProduto = new javax.swing.JLabel();
        linha2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        txtDescricao = new javax.swing.JTextField();
        btnAlterar2 = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCriar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Gerenciador de Estoque");
        setMinimumSize(new java.awt.Dimension(734, 558));
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        btnStringProdutos.setBackground(new java.awt.Color(0, 102, 204));
        btnStringProdutos.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        btnStringProdutos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringProdutos.setText("Gerenciador de Despesas");

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

        jtDespesas.setBorder(new javax.swing.border.MatteBorder(null));
        jtDespesas.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jtDespesas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dia", "Valor", "Descrição", "id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtDespesas.getTableHeader().setReorderingAllowed(false);
        jtDespesas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtDespesasFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jtDespesas);
        if (jtDespesas.getColumnModel().getColumnCount() > 0) {
            jtDespesas.getColumnModel().getColumn(0).setResizable(false);
            jtDespesas.getColumnModel().getColumn(1).setResizable(false);
            jtDespesas.getColumnModel().getColumn(2).setResizable(false);
            jtDespesas.getColumnModel().getColumn(3).setResizable(false);
        }

        lblStringNomeProduto.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNomeProduto.setText("Descrição:");

        linha2.setBackground(new java.awt.Color(0, 0, 0));
        linha2.setOpaque(true);

        txtDescricao.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        txtDescricao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDescricaoFocusGained(evt);
            }
        });
        txtDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescricaoKeyReleased(evt);
            }
        });

        btnAlterar2.setBackground(new java.awt.Color(204, 204, 0));
        btnAlterar2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnAlterar2.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\lapis.png")); // NOI18N
        btnAlterar2.setText("   Alterar");
        btnAlterar2.setBorder(new javax.swing.border.MatteBorder(null));
        btnAlterar2.setBorderPainted(false);
        btnAlterar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterar2ActionPerformed(evt);
            }
        });

        btnExcluir.setBackground(new java.awt.Color(204, 0, 0));
        btnExcluir.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnExcluir.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\delete (1).png")); // NOI18N
        btnExcluir.setText("   Excluir");
        btnExcluir.setBorder(new javax.swing.border.MatteBorder(null));
        btnExcluir.setBorderPainted(false);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnCriar.setBackground(new java.awt.Color(51, 204, 0));
        btnCriar.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        btnCriar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\add (2) (1).png")); // NOI18N
        btnCriar.setText("  Criar Despesa");
        btnCriar.setBorder(new javax.swing.border.MatteBorder(null));
        btnCriar.setBorderPainted(false);
        btnCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLancador)
                        .addGap(81, 81, 81)
                        .addComponent(btnStringProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblStringNomeProduto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCriar, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(btnAlterar2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha2, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnStringProdutos)
                    .addComponent(btnLancador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringNomeProduto)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCriar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAlterar2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(63, 63, 63)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(532, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(582, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLancadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancadorActionPerformed
        new Menu().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLancadorActionPerformed

    public boolean isDigit(String c) {
        try {
            Integer.parseInt(c);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    private void jtDespesasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtDespesasFocusGained
        btnAlterar2.setEnabled(true);
        btnExcluir.setEnabled(true);
    }//GEN-LAST:event_jtDespesasFocusGained

    private void btnAlterar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterar2ActionPerformed
        //Menu.acaoEscolhida = 2;
        idEscolhido = (Integer)jtDespesas.getValueAt(jtDespesas.getSelectedRow(),3);
        jtDespesas.clearSelection();
        opcaoEscolhida = 2;
        new AlterarDespesa(new javax.swing.JFrame(), true).setVisible(true);
    }//GEN-LAST:event_btnAlterar2ActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int reply = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir a despesa?", "Exclusão de Despesa", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            AgendaDespesaDAO a = new AgendaDespesaDAO();
            a.delete((Integer)jtDespesas.getValueAt(jtDespesas.getSelectedRow(),3));
            this.criarTabela();
            jtDespesas.clearSelection();
            btnExcluir.setEnabled(false);
            btnAlterar2.setEnabled(false);
        }       
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void txtDescricaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescricaoKeyReleased
        String e = txtDescricao.getText();
        if (e.length() >= 3){
            this.criarTabelaDescricao(e);
        }else{
            this.criarTabela();
        }
    }//GEN-LAST:event_txtDescricaoKeyReleased

    private void btnCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarActionPerformed
        jtDespesas.clearSelection();
        opcaoEscolhida = 1;
        new CriarDespesa(new javax.swing.JFrame(), true).setVisible(true);
    }//GEN-LAST:event_btnCriarActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        if (CriarDespesa.flagNovaDespesa){
            txtDescricao.setText("");
            this.criarTabela();
        }
    }//GEN-LAST:event_formWindowGainedFocus

    private void txtDescricaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescricaoFocusGained
        btnAlterar2.setEnabled(false);
        btnExcluir.setEnabled(false);
        jtDespesas.clearSelection();
    }//GEN-LAST:event_txtDescricaoFocusGained

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
            java.util.logging.Logger.getLogger(GerenciadorDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciadorDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciadorDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciadorDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new GerenciadorDespesa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnAlterar1;
    private javax.swing.JButton btnAlterar2;
    private javax.swing.JButton btnCriar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLancador;
    private javax.swing.JLabel btnStringProdutos;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtDespesas;
    private javax.swing.JLabel lblStringNomeProduto;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    private javax.swing.JTextField txtDescricao;
    // End of variables declaration//GEN-END:variables
}
