package main;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.DefaultTableModel;
import model.bean.Categoria;
import model.bean.CategoriaPrato;
import model.bean.Prato;
import model.bean.Produto;
import model.dao.CategoriaDAO;
import model.dao.PratoDAO;
import model.dao.ProdutoDAO;

public class CadastrarPrato extends javax.swing.JFrame {
    public static ArrayList<Produto> listaProdutos = new ArrayList<>();
    public ProdutoDAO pDao = new ProdutoDAO();
    
    public CadastrarPrato() {
        initComponents();
        this.setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnConfirmar);
        jtProdutos.setRowHeight(30);
        jtProdutos.getColumnModel().getColumn(0).setPreferredWidth(500); 
        jtProdutos.getColumnModel().getColumn(1).setPreferredWidth(50);

        jtProdutos.getColumnModel().getColumn(0).setMinWidth(500);
        jtProdutos.getColumnModel().getColumn(1).setMinWidth(50);

        jtProdutos.getColumnModel().getColumn(0).setMaxWidth(500);
        jtProdutos.getColumnModel().getColumn(1).setMaxWidth(50);
        
        listaProdutos.removeAll(listaProdutos);
        for (Produto p: pDao.read()){
            listaProdutos.add(p);
        }
        criarTabela();
        criarComboBoxCategoriasProdutos();
        criarComboBoxCategoriasPratos();
    }

    public void criarTabela(){
        limparTabela();
        ArrayList<Produto> ordenador = new ArrayList<>();
        
        for (Produto prod : listaProdutos){
            ordenador.add(prod);
        }
        //ordenador = listaProdutos.sort(listaProdutos);
        Collections.sort(ordenador);
        
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtProdutos.getModel();
        for (Produto p: ordenador){
            String valor = GerenciadorComandas.valorMonetario(p.getPreco());
            dtmBebidas.addRow(
                new Object[]{
                    p.getNome(),
                    p.getIdProduto()}
            );
        }  
    }
    
    public void criarTabelaCategoria(String nome){
        ArrayList<Produto> ordenador = new ArrayList<>();
        
        for (Produto prod : pDao.readForCategoria(nome)){
            ordenador.add(prod);
        }
        Collections.sort(ordenador);
        
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtProdutos.getModel();
        limparTabela();
        for (Produto p: ordenador){
            String valor = GerenciadorComandas.valorMonetario(p.getPreco());
            dtmBebidas.addRow(
                new Object[]{
                    p.getNome(),
                    p.getIdProduto()}
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
    
    public void criarComboBoxCategoriasPratos(){
        cbCategoriaPrato.removeAllItems();
        cbCategoriaPrato.addItem("");
        for (CategoriaPrato c:Login.categoriasPratos){
            cbCategoriaPrato.addItem(c.getNome());
        }
    }
    
    public void criarComboBoxCategoriasProdutos(){
        cbCategoriasProdutos.removeAllItems();
        cbCategoriasProdutos.addItem("");
        CategoriaDAO catDao = new CategoriaDAO();
        for (Categoria c:catDao.read()){
            cbCategoriasProdutos.addItem(c.getNome());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStringNovoProduto = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtNome = new javax.swing.JTextField();
        lblStringNome = new javax.swing.JLabel();
        lblStringDescricao = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        lblStringDescricao1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProdutos = new javax.swing.JTable();
        cbCategoriasProdutos = new javax.swing.JComboBox<>();
        lblStringNomeProduto1 = new javax.swing.JLabel();
        lblStringDescricao2 = new javax.swing.JLabel();
        cbCategoriaPrato = new javax.swing.JComboBox<>();
        lblAdicionar1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastrar Prato");
        setMaximumSize(new java.awt.Dimension(648, 701));
        setMinimumSize(new java.awt.Dimension(648, 701));
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        lblStringNovoProduto.setBackground(new java.awt.Color(0, 102, 204));
        lblStringNovoProduto.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        lblStringNovoProduto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStringNovoProduto.setText("Cadastrar Prato");

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        btnConfirmar.setBackground(new java.awt.Color(0, 153, 0));
        btnConfirmar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnConfirmar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\confirm.png")); // NOI18N
        btnConfirmar.setText("  Confirmar");
        btnConfirmar.setBorder(new javax.swing.border.MatteBorder(null));
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(204, 0, 0));
        btnCancelar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\cancel.png")); // NOI18N
        btnCancelar.setText("  Cancelar");
        btnCancelar.setBorder(new javax.swing.border.MatteBorder(null));
        btnCancelar.setBorderPainted(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtNome.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        lblStringNome.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNome.setText("Nome:");

        lblStringDescricao.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringDescricao.setText("Produtos:");

        txtDescricao.setColumns(20);
        txtDescricao.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtDescricao.setRows(5);
        jScrollPane2.setViewportView(txtDescricao);

        lblStringDescricao1.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringDescricao1.setText("Observações:");

        jtProdutos.setBorder(new javax.swing.border.MatteBorder(null));
        jtProdutos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jtProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtProdutos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtProdutosFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jtProdutos);

        cbCategoriasProdutos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cbCategoriasProdutos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCategoriasProdutos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCategoriasProdutosItemStateChanged(evt);
            }
        });
        cbCategoriasProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoriasProdutosActionPerformed(evt);
            }
        });

        lblStringNomeProduto1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblStringNomeProduto1.setText("Exibir Somente:");

        lblStringDescricao2.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringDescricao2.setText("Categoria:");

        cbCategoriaPrato.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cbCategoriaPrato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCategoriaPrato.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCategoriaPratoItemStateChanged(evt);
            }
        });
        cbCategoriaPrato.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cbCategoriaPratoFocusGained(evt);
            }
        });

        lblAdicionar1.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblAdicionar1.setText("[+]");
        lblAdicionar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAdicionar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAdicionar1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblStringDescricao1)
                    .addComponent(lblStringDescricao)
                    .addComponent(lblStringDescricao2)
                    .addComponent(lblStringNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(139, 139, 139)
                                .addComponent(lblStringNomeProduto1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbCategoriaPrato, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAdicionar1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCategoriasProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                    .addComponent(txtNome)
                    .addComponent(jScrollPane2))
                .addGap(0, 23, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblStringNovoProduto)
                .addGap(217, 217, 217))
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblStringNovoProduto)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringDescricao2)
                    .addComponent(cbCategoriaPrato, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAdicionar1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCategoriasProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStringNomeProduto1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStringDescricao1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblStringDescricao))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(640, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        Prato prato = new Prato();
        prato.setNome(txtNome.getText());
        prato.setDescricao(txtDescricao.getText());
        String cat = (String)cbCategoriaPrato.getSelectedItem();
        for (CategoriaPrato c:Login.categoriasPratos){
            if (c.getNome().equals(cat)){
                prato.setCategoria(c);
            }
        }
        
        for (int i = 0; i < jtProdutos.getSelectedRows().length; i++){
            Integer idSelecionado = (Integer)jtProdutos.getValueAt(jtProdutos.getSelectedRows()[i], 1);
            for (Produto p:listaProdutos){
                if (p.getIdProduto() == idSelecionado){
                    prato.setSubproduto(p);
                }
            }
        }

        PratoDAO pratoDao = new PratoDAO();
        pratoDao.create(prato);
        dispose();
        new Menu().setVisible(true);
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        new Menu().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jtProdutosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtProdutosFocusGained

    }//GEN-LAST:event_jtProdutosFocusGained

    private void cbCategoriasProdutosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCategoriasProdutosItemStateChanged
        String escolhido = (String)cbCategoriasProdutos.getSelectedItem();
        //txtPesquisa.setText("");
        try{
            if (!escolhido.equals("")){
                criarTabelaCategoria(escolhido);
            }else{
                criarTabela();
            }
        }catch(java.lang.NullPointerException ex){

        }
    }//GEN-LAST:event_cbCategoriasProdutosItemStateChanged

    private void cbCategoriasProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoriasProdutosActionPerformed

    }//GEN-LAST:event_cbCategoriasProdutosActionPerformed

    private void cbCategoriaPratoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCategoriaPratoItemStateChanged

    }//GEN-LAST:event_cbCategoriaPratoItemStateChanged

    private void cbCategoriaPratoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbCategoriaPratoFocusGained
        
    }//GEN-LAST:event_cbCategoriaPratoFocusGained

    private void lblAdicionar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAdicionar1MouseClicked
        new CadastrarCategoriaPrato().setVisible(true);
    }//GEN-LAST:event_lblAdicionar1MouseClicked

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        criarComboBoxCategoriasPratos();
    }//GEN-LAST:event_formWindowGainedFocus

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
            java.util.logging.Logger.getLogger(CadastrarPrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastrarPrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastrarPrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarPrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new CadastrarPrato().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private static javax.swing.JComboBox<String> cbCategoriaPrato;
    private javax.swing.JComboBox<String> cbCategoriasProdutos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private static javax.swing.JTable jtProdutos;
    private javax.swing.JLabel lblAdicionar1;
    private javax.swing.JLabel lblStringDescricao;
    private javax.swing.JLabel lblStringDescricao1;
    private javax.swing.JLabel lblStringDescricao2;
    private javax.swing.JLabel lblStringNome;
    private javax.swing.JLabel lblStringNomeProduto1;
    private javax.swing.JLabel lblStringNovoProduto;
    private javax.swing.Box.Filler linha1;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}


