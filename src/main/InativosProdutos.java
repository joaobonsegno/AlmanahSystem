package main;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.bean.Cardapio;
import model.bean.Cliente;
import model.bean.Prato;
import model.bean.Produto;
import model.dao.CardapioDAO;
import model.dao.ClienteDAO;
import model.dao.ItemCardapioDAO;
import model.dao.PratoDAO;
import model.dao.ProdutoDAO;

public class InativosProdutos extends javax.swing.JDialog {    
    public ProdutoDAO pDao = new ProdutoDAO();
    
    public InativosProdutos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);       
        getRootPane().setDefaultButton(btnAtivar);
        btnAtivar.setEnabled(false);
        formatarTabela(); 
        criarTabela();
    }   
    
    public void criarTabela(){
        ArrayList<Produto> ordenador = new ArrayList<>();

        // Percorre a lista de TODOS os Pratos da mesma CATEGORIA
        for (Produto c : pDao.readInativos()){
            ordenador.add(c);
        }
        Collections.sort(ordenador);
        
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtProdutos.getModel();
        limparTabela();
        for (Produto p: ordenador){
            dtmBebidas.addRow(
                new Object[]{
                    p.getIdProduto(),
                    p.getNome(),
                    GerenciadorComandas.valorMonetario(p.getPreco())}
            );
        }
    }
    
    public void limparTabela(){
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtProdutos.getModel();
        int i = dtmBebidas.getRowCount();
        
        for (int j = 0; j < i; j++){
            dtmBebidas.removeRow(0);
        }       
    }

    public void formatarTabela(){        
        jtProdutos.setRowHeight(28);
        jtProdutos.getColumn("Preço").setCellRenderer(centro);
        jtProdutos.getColumnModel().getColumn(0).setPreferredWidth(0); 
        jtProdutos.getColumnModel().getColumn(1).setPreferredWidth(350);
        jtProdutos.getColumnModel().getColumn(2).setPreferredWidth(97);
        
        jtProdutos.getColumnModel().getColumn(0).setMinWidth(0);
        jtProdutos.getColumnModel().getColumn(1).setMinWidth(350);
        jtProdutos.getColumnModel().getColumn(2).setMinWidth(97);
        
        jtProdutos.getColumnModel().getColumn(0).setMaxWidth(0);
        jtProdutos.getColumnModel().getColumn(1).setMaxWidth(350);
        jtProdutos.getColumnModel().getColumn(2).setMaxWidth(97);
    }
    
    // MÉTODOS PARA ARRUMAR CÉLULAS DA TABELA
    DefaultTableCellRenderer centro = new DefaultTableCellRenderer() {
        public void setValue(Object value) {
            setHorizontalAlignment(JLabel.CENTER);
            super.setValue(value);
        }
    };
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtStringSuprimento = new javax.swing.JLabel();
        linha = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnVoltar = new javax.swing.JButton();
        btnAtivar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProdutos = new javax.swing.JTable();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Produtos Inativos");
        setMaximumSize(new java.awt.Dimension(519, 420));
        setMinimumSize(new java.awt.Dimension(519, 420));
        setResizable(false);

        txtStringSuprimento.setBackground(new java.awt.Color(0, 102, 204));
        txtStringSuprimento.setFont(new java.awt.Font("Century Gothic", 0, 19)); // NOI18N
        txtStringSuprimento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtStringSuprimento.setText("Produtos Inativos");

        linha.setBackground(new java.awt.Color(0, 0, 0));
        linha.setOpaque(true);

        btnVoltar.setBackground(new java.awt.Color(255, 0, 51));
        btnVoltar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnVoltar.setText("Voltar");
        btnVoltar.setBorder(new javax.swing.border.MatteBorder(null));
        btnVoltar.setBorderPainted(false);
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        btnAtivar.setBackground(new java.awt.Color(0, 153, 51));
        btnAtivar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnAtivar.setText("Ativar");
        btnAtivar.setBorder(new javax.swing.border.MatteBorder(null));
        btnAtivar.setBorderPainted(false);
        btnAtivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtivarActionPerformed(evt);
            }
        });

        jtProdutos.setBorder(new javax.swing.border.MatteBorder(null));
        jtProdutos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jtProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Preço"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtProdutos.getTableHeader().setReorderingAllowed(false);
        jtProdutos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtProdutosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtProdutosFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(jtProdutos);
        if (jtProdutos.getColumnModel().getColumnCount() > 0) {
            jtProdutos.getColumnModel().getColumn(0).setResizable(false);
            jtProdutos.getColumnModel().getColumn(1).setResizable(false);
            jtProdutos.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addContainerGap())
        );

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(txtStringSuprimento))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(btnAtivar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(txtStringSuprimento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtivar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(56, 56, 56)
                    .addComponent(linha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(363, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(406, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        new GerenciadorProdutos().setVisible(true); 
        dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnAtivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtivarActionPerformed
        if (jtProdutos.getSelectedRowCount() > 1){
            JOptionPane.showMessageDialog(null, "Selecione somente 1 (um) produto");
        }else{
            int reply = JOptionPane.showConfirmDialog(null, "Deseja realmente ativar o produto?","Ativação de Produto", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                int id = (Integer)jtProdutos.getValueAt(jtProdutos.getSelectedRow(),0);
                pDao.setAtivo(id);
                JOptionPane.showMessageDialog(null, "Produto ativado com sucesso");
                this.limparTabela();
                this.criarTabela();
            }
        }
    }//GEN-LAST:event_btnAtivarActionPerformed

    private void jtProdutosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtProdutosFocusGained
        btnAtivar.setEnabled(true);
    }//GEN-LAST:event_jtProdutosFocusGained

    private void jtProdutosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtProdutosFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jtProdutosFocusLost

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
            java.util.logging.Logger.getLogger(InativosProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InativosProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InativosProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InativosProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InativosProdutos dialog = new InativosProdutos(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAtivar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtProdutos;
    private javax.swing.Box.Filler linha;
    private javax.swing.Box.Filler linha1;
    private javax.swing.JLabel txtStringSuprimento;
    // End of variables declaration//GEN-END:variables
}
