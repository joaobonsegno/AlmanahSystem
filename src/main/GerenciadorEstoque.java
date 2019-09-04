package main;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import static main.GerenciadorComandas.comandasAbertas;
import static main.GerenciadorComandas.lblErro;
import static main.GerenciadorComandas.ordenarListas;
import static main.GerenciadorProdutos.listaProdutos;
import model.bean.Comanda;
import model.bean.Produto;
import model.dao.ProdutoDAO;

public class GerenciadorEstoque extends javax.swing.JFrame {
    
    public static void retirarEstoque(Produto prod, Integer qtd){
        ProdutoDAO pDao = new ProdutoDAO();
        String qtdAtualS = prod.getQtdEstoque();
        Integer qtdAtual = Integer.parseInt(qtdAtualS);
        qtdAtual -= qtd;
        qtdAtualS = Integer.toString(qtdAtual);
        prod.setQtdEstoque(qtdAtualS);
        pDao.updateEstoque(prod);
        
    }
        
    public void criarTabela(){
        ArrayList<Produto> ordenador = new ArrayList<>();
        ProdutoDAO pDao = new ProdutoDAO();
        
        for (Produto prod : pDao.read()){
            ordenador.add(prod);
        }
        ordenador = GerenciadorComandas.ordenarListasProduto(ordenador);
        
        DefaultTableModel dtmProdutos = (DefaultTableModel) jtProdutos.getModel();

        for (Produto p: ordenador){
            if(!p.getQtdMinima().equals("X")){
                dtmProdutos.addRow(
                    new Object[]{
                        p.getIdProduto(),
                        p.getQtdEstoque(),
                        p.getNome()}
                );
            }
        }
    }
    
    public void criarTabelaNome(String nome){
        ArrayList<Produto> ordenador = new ArrayList<>();
        ProdutoDAO pDao = new ProdutoDAO();
        for (Produto prod : pDao.readForNome(nome)){
            ordenador.add(prod);
        }
        ordenador = GerenciadorComandas.ordenarListasProduto(ordenador);
        
        DefaultTableModel dtmProdutos = (DefaultTableModel) jtProdutos.getModel();
        limparTabela();
        for (Produto p: ordenador){
            if(!p.getQtdMinima().equals("X")){
                dtmProdutos.addRow(
                    new Object[]{
                        p.getIdProduto(),
                        p.getQtdEstoque(),
                        p.getNome()}
                );
            }
        }
    }
    
    public void limparTabela(){
        DefaultTableModel dtmProdutos = (DefaultTableModel) jtProdutos.getModel();
        int i = dtmProdutos.getRowCount();
        
        for (int j = 0; j < i; j++){
            dtmProdutos.removeRow(0);
        }
    }
    
    public GerenciadorEstoque() {
       initComponents();
       getRootPane().setDefaultButton(btnOk);
       jtProdutos.setRowHeight(22);
       this.setLocationRelativeTo(null);
       jtProdutos.getColumnModel().getColumn(0).setPreferredWidth(80); 
       jtProdutos.getColumnModel().getColumn(1).setPreferredWidth(100);
       jtProdutos.getColumnModel().getColumn(2).setPreferredWidth(430);
        
       jtProdutos.getColumnModel().getColumn(0).setMinWidth(80);
       jtProdutos.getColumnModel().getColumn(1).setMinWidth(100);
       jtProdutos.getColumnModel().getColumn(2).setMinWidth(430);
        
       jtProdutos.getColumnModel().getColumn(0).setMaxWidth(80);
       jtProdutos.getColumnModel().getColumn(1).setMaxWidth(100);
       jtProdutos.getColumnModel().getColumn(2).setMaxWidth(430);
       criarTabela();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStringProdutos = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnLancador = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProdutos = new javax.swing.JTable();
        btnDarEntrada = new javax.swing.JButton();
        lblStringNomeProduto = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        btnDarEntrada1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciador de Estoque");
        setMinimumSize(new java.awt.Dimension(681, 615));
        setResizable(false);

        btnStringProdutos.setBackground(new java.awt.Color(0, 102, 204));
        btnStringProdutos.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        btnStringProdutos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringProdutos.setText("Gerenciador de Estoque");

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

        jtProdutos.setBorder(new javax.swing.border.MatteBorder(null));
        jtProdutos.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jtProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Qtd", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtProdutos);
        if (jtProdutos.getColumnModel().getColumnCount() > 0) {
            jtProdutos.getColumnModel().getColumn(0).setResizable(false);
            jtProdutos.getColumnModel().getColumn(1).setResizable(false);
            jtProdutos.getColumnModel().getColumn(2).setResizable(false);
        }

        btnDarEntrada.setBackground(new java.awt.Color(0, 153, 0));
        btnDarEntrada.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnDarEntrada.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\entrada.png")); // NOI18N
        btnDarEntrada.setText(" Dar Entrada");
        btnDarEntrada.setBorder(new javax.swing.border.MatteBorder(null));
        btnDarEntrada.setBorderPainted(false);
        btnDarEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDarEntradaActionPerformed(evt);
            }
        });

        lblStringNomeProduto.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNomeProduto.setText("Nome do Produto:");

        txtPesquisa.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        btnOk.setBackground(new java.awt.Color(0, 153, 204));
        btnOk.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        btnOk.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\pesquisar (1).png")); // NOI18N
        btnOk.setText("Buscar");
        btnOk.setBorder(new javax.swing.border.MatteBorder(null));
        btnOk.setBorderPainted(false);
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnDarEntrada1.setBackground(new java.awt.Color(255, 0, 0));
        btnDarEntrada1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnDarEntrada1.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\entrada.png")); // NOI18N
        btnDarEntrada1.setText("   Dar Baixa");
        btnDarEntrada1.setBorder(new javax.swing.border.MatteBorder(null));
        btnDarEntrada1.setBorderPainted(false);
        btnDarEntrada1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDarEntrada1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(btnStringProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnLancador)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblStringNomeProduto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnDarEntrada1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnDarEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLancador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStringProdutos))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStringNomeProduto))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDarEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDarEntrada1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(63, 63, 63)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(494, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLancadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancadorActionPerformed
        new Menu().setVisible(true); 
        dispose();
    }//GEN-LAST:event_btnLancadorActionPerformed

    private void btnDarEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDarEntradaActionPerformed
        Integer idSelecionado = (Integer)jtProdutos.getValueAt(jtProdutos.getSelectedRow(), 0);

        DarEntrada entrada = new DarEntrada(new javax.swing.JFrame(), true, idSelecionado);
        entrada.setVisible(true); 
        dispose();

    }//GEN-LAST:event_btnDarEntradaActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        criarTabelaNome(txtPesquisa.getText());
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnDarEntrada1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDarEntrada1ActionPerformed
        Integer idSelecionado = (Integer)jtProdutos.getValueAt(jtProdutos.getSelectedRow(), 0);

        DarBaixa baixa = new DarBaixa(new javax.swing.JFrame(), true, idSelecionado);
        baixa.setVisible(true); 
        dispose();
    }//GEN-LAST:event_btnDarEntrada1ActionPerformed

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
            java.util.logging.Logger.getLogger(GerenciadorEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciadorEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciadorEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciadorEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new GerenciadorEstoque().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDarEntrada;
    private javax.swing.JButton btnDarEntrada1;
    private javax.swing.JButton btnLancador;
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel btnStringProdutos;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtProdutos;
    private javax.swing.JLabel lblStringNomeProduto;
    private javax.swing.Box.Filler linha1;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}


