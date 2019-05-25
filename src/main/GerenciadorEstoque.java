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
    //public static ArrayList<Produto> listaProdutos = new ArrayList<>();
    
    public static boolean verificaIndice(){
        int escolhido = cbEscolherProduto.getSelectedIndex();
        if (escolhido > 0){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static void retirarEstoque(Produto prod, Integer qtd){
        ProdutoDAO pDao = new ProdutoDAO();
        String qtdAtualS = prod.getQtdEstoque();
        Integer qtdAtual = Integer.parseInt(qtdAtualS);
        qtdAtual -= qtd;
        qtdAtualS = Integer.toString(qtdAtual);
        prod.setQtdEstoque(qtdAtualS);
        pDao.updateEstoque(prod);
        
    }
    
    public void criarCb(ArrayList<Produto> listaProdutos){  
        ArrayList<Integer> ordenador = new ArrayList<>();
        
        for (Produto numero : listaProdutos){
            if(!numero.getQtdMinima().equals("X")){
                ordenador.add(numero.getIdSistema());
            }
        }

        cbEscolherProduto.removeAllItems();
        cbEscolherProduto.addItem("");
        ordenador = GerenciadorComandas.ordenarListas(ordenador);
        for (Integer numero : ordenador){
            String numNovoProduto = Integer.toString(numero);
            cbEscolherProduto.addItem(numNovoProduto);
        }
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
                        p.getIdSistema(),
                        p.getQtdEstoque(),
                        p.getNome()}
                );
            }
        }
        criarCb(pDao.read());
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
                        p.getIdSistema(),
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
       lblErro.setVisible(false);
       this.setLocationRelativeTo(null);
       cbEscolherProduto.removeAllItems();
       jtProdutos.getColumnModel().getColumn(0).setPreferredWidth(80); 
       jtProdutos.getColumnModel().getColumn(1).setPreferredWidth(100);
       jtProdutos.getColumnModel().getColumn(2).setPreferredWidth(335);
        
       jtProdutos.getColumnModel().getColumn(0).setMinWidth(80);
       jtProdutos.getColumnModel().getColumn(1).setMinWidth(100);
       jtProdutos.getColumnModel().getColumn(2).setMinWidth(335);
        
       jtProdutos.getColumnModel().getColumn(0).setMaxWidth(80);
       jtProdutos.getColumnModel().getColumn(1).setMaxWidth(100);
       jtProdutos.getColumnModel().getColumn(2).setMaxWidth(335);
       criarTabela();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStringProdutos = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        linha2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnLancador = new javax.swing.JButton();
        lblStringUsuario = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblStringFuncao = new javax.swing.JLabel();
        lblFuncao = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProdutos = new javax.swing.JTable();
        cbEscolherProduto = new javax.swing.JComboBox<>();
        lblStringSelecione = new javax.swing.JLabel();
        btnMovimentacao = new javax.swing.JButton();
        btnDarEntrada = new javax.swing.JButton();
        lblErro = new javax.swing.JLabel();
        lblStringNomeProduto = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(1, 1), new java.awt.Dimension(1, 1), new java.awt.Dimension(1, 1));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciador de Estoque");
        setMaximumSize(new java.awt.Dimension(980, 620));
        setMinimumSize(new java.awt.Dimension(980, 620));
        setResizable(false);

        btnStringProdutos.setBackground(new java.awt.Color(0, 102, 204));
        btnStringProdutos.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        btnStringProdutos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringProdutos.setText("Gerenciador de Estoque");

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        linha2.setBackground(new java.awt.Color(0, 0, 0));
        linha2.setOpaque(true);

        btnLancador.setFont(new java.awt.Font("Comic Sans MS", 0, 17)); // NOI18N
        btnLancador.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\voltar (1).png")); // NOI18N
        btnLancador.setText("Menu");
        btnLancador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLancadorActionPerformed(evt);
            }
        });

        lblStringUsuario.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblStringUsuario.setText("Usuário:");

        lblUsuario.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblUsuario.setText("Joao");

        lblStringFuncao.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblStringFuncao.setText("Função:");

        lblFuncao.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblFuncao.setText("Gerente");

        jtProdutos.setBorder(new javax.swing.border.MatteBorder(null));
        jtProdutos.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
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

        cbEscolherProduto.setFont(new java.awt.Font("Comic Sans MS", 0, 22)); // NOI18N
        cbEscolherProduto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbEscolherProduto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbEscolherProdutoItemStateChanged(evt);
            }
        });

        lblStringSelecione.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        lblStringSelecione.setText("Selecione o produto:");

        btnMovimentacao.setBackground(new java.awt.Color(153, 153, 0));
        btnMovimentacao.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        btnMovimentacao.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\movimentacao (1).png")); // NOI18N
        btnMovimentacao.setText("Movimentação");
        btnMovimentacao.setBorder(new javax.swing.border.MatteBorder(null));
        btnMovimentacao.setBorderPainted(false);
        btnMovimentacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMovimentacaoActionPerformed(evt);
            }
        });

        btnDarEntrada.setBackground(new java.awt.Color(0, 153, 0));
        btnDarEntrada.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        btnDarEntrada.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\entrada.png")); // NOI18N
        btnDarEntrada.setText(" Dar Entrada");
        btnDarEntrada.setBorder(new javax.swing.border.MatteBorder(null));
        btnDarEntrada.setBorderPainted(false);
        btnDarEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDarEntradaActionPerformed(evt);
            }
        });

        lblErro.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        lblErro.setForeground(new java.awt.Color(255, 0, 0));
        lblErro.setText("Selecione um produto!");

        lblStringNomeProduto.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        lblStringNomeProduto.setText("Nome do Produto:");

        txtPesquisa.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N

        btnOk.setBackground(new java.awt.Color(0, 153, 204));
        btnOk.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        btnOk.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\pesquisar (1).png")); // NOI18N
        btnOk.setText("Buscar");
        btnOk.setBorder(new javax.swing.border.MatteBorder(null));
        btnOk.setBorderPainted(false);
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        filler1.setBackground(new java.awt.Color(0, 0, 0));
        filler1.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblStringNomeProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnMovimentacao, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(lblStringSelecione)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbEscolherProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnDarEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(117, 117, 117))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblErro)
                                .addGap(75, 75, 75))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnStringProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLancador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblStringUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(76, 76, 76)
                .addComponent(lblStringFuncao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, 977, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 4, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, 981, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblStringUsuario)
                        .addComponent(lblUsuario)
                        .addComponent(lblStringFuncao)
                        .addComponent(lblFuncao))
                    .addComponent(btnLancador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(btnStringProdutos)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnMovimentacao, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblStringSelecione)
                                    .addComponent(cbEscolherProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addComponent(lblErro)
                                .addGap(41, 41, 41)
                                .addComponent(btnDarEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(200, 200, 200))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                                        .addComponent(lblStringNomeProduto))
                                    .addComponent(btnOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(filler1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(110, 110, 110)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(507, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(575, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLancadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancadorActionPerformed
        new Menu().setVisible(true); 
        dispose();
    }//GEN-LAST:event_btnLancadorActionPerformed

    private void btnMovimentacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMovimentacaoActionPerformed
        dispose();
        new MovimentacaoEstoque().setVisible(true); 
    }//GEN-LAST:event_btnMovimentacaoActionPerformed

    private void btnDarEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDarEntradaActionPerformed
        if(verificaIndice()){
            Object i = cbEscolherProduto.getSelectedItem();
            String i2 = (String)i;
            Integer id = Integer.parseInt(i2);
            DarEntrada entrada = new DarEntrada(new javax.swing.JFrame(), true, id);
            entrada.setVisible(true); 
            dispose();
        }
        else{
            lblErro.setVisible(true);
        }
    }//GEN-LAST:event_btnDarEntradaActionPerformed

    private void cbEscolherProdutoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbEscolherProdutoItemStateChanged
        int escolhido = cbEscolherProduto.getSelectedIndex();
        if (escolhido > 0){
            lblErro.setVisible(false);
        }
        
    }//GEN-LAST:event_cbEscolherProdutoItemStateChanged

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        criarTabelaNome(txtPesquisa.getText());
    }//GEN-LAST:event_btnOkActionPerformed

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
    private javax.swing.JButton btnLancador;
    private javax.swing.JButton btnMovimentacao;
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel btnStringProdutos;
    private static javax.swing.JComboBox<String> cbEscolherProduto;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtProdutos;
    public static javax.swing.JLabel lblErro;
    private javax.swing.JLabel lblFuncao;
    private javax.swing.JLabel lblStringFuncao;
    private javax.swing.JLabel lblStringNomeProduto;
    private javax.swing.JLabel lblStringSelecione;
    private javax.swing.JLabel lblStringUsuario;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}


