package main;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Produto;
import model.bean.Promocao;
import model.bean.PromocaoUm;
import model.dao.ProdutoDAO;
import model.dao.PromocaoDAO;
import model.dao.PromocaoUmDAO;

public class Promocoes extends javax.swing.JFrame {
    ArrayList<Produto> listaProdutos = new ArrayList<>();
    public static Integer idSelecionado;
    
    public Promocoes() {
        initComponents();
        jtPromocoes.setRowHeight(23);
        jtPromocoes.getColumnModel().getColumn(0).setPreferredWidth(30); 
        jtPromocoes.getColumnModel().getColumn(1).setPreferredWidth(517);
        jtPromocoes.getColumnModel().getColumn(2).setPreferredWidth(65);
        
        jtPromocoes.getColumnModel().getColumn(0).setMinWidth(30);
        jtPromocoes.getColumnModel().getColumn(1).setMinWidth(517);
        jtPromocoes.getColumnModel().getColumn(2).setMinWidth(65);
        
        
        jtPromocoes.getColumnModel().getColumn(0).setMaxWidth(30);
        jtPromocoes.getColumnModel().getColumn(1).setMaxWidth(517);
        jtPromocoes.getColumnModel().getColumn(2).setMaxWidth(65);

        criarTabela();
        this.setLocationRelativeTo(null);
        btnAlterar.setEnabled(false);
        
        ProdutoDAO pDao = new ProdutoDAO();
        PromocaoUmDAO promoDao = new PromocaoUmDAO();
        PromocaoUm promocaoUm = promoDao.read();
        if (promocaoUm.getStatus() == 1){
            for (Produto prod:pDao.read()){
                if (prod.getCategoria().getNome().equals("Suco")){
                    prod.setPrecoComDesconto(prod.getPreco()*promocaoUm.getMultiplicador());
                    pDao.updatePromocao(prod);
                }              
            }
        }
        /*JFormattedTextField tf = ((JSpinner.DefaultEditor) SpinnerQtd.getEditor()).getTextField();
        tf.setEditable(false);
        SpinnerQtd.setModel(nm);*/
    }
    
    public void criarTabela(){ 
        PromocaoDAO promoDao = new PromocaoDAO();
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtPromocoes.getModel();
        PromocaoUmDAO promoUmDao = new PromocaoUmDAO();
        PromocaoUm p1 = promoUmDao.read();
        
        for (Promocao p:promoDao.read()){
            if (p.getId() == 1){
                if (p1.getStatus() == 1){
                    dtmBebidas.addRow(
                    new Object[]{
                        p.getId(),
                        p.getDescricao(),
                        "Ativo"}
                    );
                }else{
                    dtmBebidas.addRow(
                    new Object[]{
                        p.getId(),
                        p.getDescricao(),
                        "Inativo"}
                    );
                }
                
            }else{
                dtmBebidas.addRow(
                new Object[]{
                    p.getId(),
                    p.getDescricao(),
                    "Inativo"}
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
        
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtPromocoes.getModel();
        limparTabela();
        for (Produto p: ordenador){
            if(p.getCategoria().getNome().equals("Bebida")){
                String valor = GerenciadorComandas.valorMonetario(p.getPreco());
                dtmBebidas.addRow(
                    new Object[]{
                        p.getIdProduto(),
                        p.getQtdEstoque(),
                        valor,
                        p.getNome()}
                );
            }
        }
    }
    
    public void limparTabela(){
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtPromocoes.getModel();
        int i = dtmBebidas.getRowCount();
        
        for (int j = 0; j < i; j++){
            dtmBebidas.removeRow(0);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStringGerenciador = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        jScrollPane1 = new javax.swing.JScrollPane();
        jtPromocoes = new javax.swing.JTable();
        lblStringNomeProduto = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        btnLancador = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Nova Bebida");
        setMaximumSize(new java.awt.Dimension(676, 576));
        setMinimumSize(new java.awt.Dimension(676, 576));
        setResizable(false);

        btnStringGerenciador.setBackground(new java.awt.Color(0, 102, 204));
        btnStringGerenciador.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        btnStringGerenciador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringGerenciador.setText("Promoções");

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        jtPromocoes.setBorder(new javax.swing.border.MatteBorder(null));
        jtPromocoes.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jtPromocoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descrição", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtPromocoes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtPromocoesFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jtPromocoes);
        if (jtPromocoes.getColumnModel().getColumnCount() > 0) {
            jtPromocoes.getColumnModel().getColumn(0).setResizable(false);
        }

        lblStringNomeProduto.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNomeProduto.setText("Nome:");

        txtPesquisa.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N

        btnOk.setBackground(new java.awt.Color(0, 153, 204));
        btnOk.setFont(new java.awt.Font("Century Gothic", 0, 19)); // NOI18N
        btnOk.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\pesquisar (1).png")); // NOI18N
        btnOk.setText(" Buscar");
        btnOk.setBorder(new javax.swing.border.MatteBorder(null));
        btnOk.setBorderPainted(false);
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnLancador.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        btnLancador.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\voltar (1).png")); // NOI18N
        btnLancador.setText("Menu");
        btnLancador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLancadorActionPerformed(evt);
            }
        });

        btnAlterar.setBackground(new java.awt.Color(204, 204, 0));
        btnAlterar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnAlterar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\lapis.png")); // NOI18N
        btnAlterar.setText("   Editar");
        btnAlterar.setBorder(new javax.swing.border.MatteBorder(null));
        btnAlterar.setBorderPainted(false);
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lblStringNomeProduto)
                        .addGap(6, 6, 6)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLancador)
                        .addGap(139, 139, 139)
                        .addComponent(btnStringGerenciador, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLancador)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnStringGerenciador)))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringNomeProduto)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(516, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public int produtoExiste(Integer idSelecionado){
        boolean flag = true;
        int contador = -1;
        for (Produto p:GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado-1).getItens()){
            if (p.getIdProduto() == idSelecionado){
                contador += 1;
                flag = false;
                break;
            }
            contador += 1;
        }
        if (flag){
            contador = -1;
        }
        return contador;
    }
    
    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        criarTabelaNome(txtPesquisa.getText());
        jtPromocoes.clearSelection();
    }//GEN-LAST:event_btnOkActionPerformed

    private void jtPromocoesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtPromocoesFocusGained
        btnAlterar.setEnabled(true);
    }//GEN-LAST:event_jtPromocoesFocusGained

    private void btnLancadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancadorActionPerformed
        new Menu().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLancadorActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        boolean flagComandasAbertas = true;
        if (GerenciadorComandas.comandasAbertas.isEmpty()){
            flagComandasAbertas = false;
        }
        
        if (flagComandasAbertas){
            JOptionPane.showMessageDialog(null, "Não é possível editar promoções com comandas abertas!");
        }else{
            idSelecionado = (Integer)jtPromocoes.getValueAt(jtPromocoes.getSelectedRow(), 0); 
            if (idSelecionado == 1){
                Promocao1 promocao1 = new Promocao1(new javax.swing.JFrame(), true);
                promocao1.setVisible(true); 
                dispose();
            }
        }
    }//GEN-LAST:event_btnAlterarActionPerformed

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
            java.util.logging.Logger.getLogger(Promocoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Promocoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Promocoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Promocoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Promocoes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnLancador;
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel btnStringGerenciador;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtPromocoes;
    private javax.swing.JLabel lblStringNomeProduto;
    private javax.swing.Box.Filler linha1;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}


