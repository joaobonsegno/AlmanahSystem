package main;

import ArrumarString.SoNumeros;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Categoria;
import model.bean.Comanda;
import model.bean.Produto;
import model.dao.ProdutoDAO;

public class AlterarProdutoOLD extends javax.swing.JFrame {
    /*public static ArrayList<Produto> listaProdutos = new ArrayList<>();
    
    public void criarCb(){
        cbCategoria.removeAllItems();
        for (Categoria c : Login.categorias){
            cbCategoria.addItem(c.getNome());
        }
        cbCategoria.setSelectedItem(GerenciadorProdutos.prodSelecionado.getCategoria().getNome());
    }
    
    public AlterarProduto() {
       initComponents();
       this.setLocationRelativeTo(null);
       txtId.setDocument(new SoNumeros());
       txtQtdMinima.setDocument(new SoNumeros());
       criarCb();
       if(GerenciadorProdutos.prodSelecionado.getQtdMinima().equals("X")){
            txtId.setText(Integer.toString(GerenciadorProdutos.prodSelecionado.getIdSistema()));
            txtNome.setText(GerenciadorProdutos.prodSelecionado.getNome());
            txtNcm.setText(GerenciadorProdutos.prodSelecionado.getNcm());
            txtQtdMinima.setEnabled(false);
            txtEan.setText(GerenciadorProdutos.prodSelecionado.getEan());
            txtPreco.setText(GerenciadorComandas.valorMonetario(GerenciadorProdutos.prodSelecionado.getPreco()));
            txtDescricao.setText(GerenciadorProdutos.prodSelecionado.getDescricao());
       }else{
            txtId.setText(Integer.toString(GerenciadorProdutos.prodSelecionado.getIdSistema()));
            txtNome.setText(GerenciadorProdutos.prodSelecionado.getNome());
            txtNcm.setText(GerenciadorProdutos.prodSelecionado.getNcm());
            txtQtdMinima.setText(GerenciadorProdutos.prodSelecionado.getQtdMinima());
            txtEan.setText(GerenciadorProdutos.prodSelecionado.getEan());
            txtPreco.setText(GerenciadorComandas.valorMonetario(GerenciadorProdutos.prodSelecionado.getPreco()));
            txtDescricao.setText(GerenciadorProdutos.prodSelecionado.getDescricao());
       }
       ProdutoDAO pDao = new ProdutoDAO();
       for(Produto p:pDao.read()){
           listaProdutos.add(p);
       }
       getRootPane().setDefaultButton(btnConfirmar);
    }*/


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStringNovoProduto = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        linha2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        lblStringUsuario = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblStringFuncao = new javax.swing.JLabel();
        lblFuncao = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblStringId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblStringNcm = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblStringNome = new javax.swing.JLabel();
        txtNcm = new javax.swing.JTextField();
        lblStringQuantidadeMin = new javax.swing.JLabel();
        txtQtdMinima = new javax.swing.JTextField();
        lblStringDescricao = new javax.swing.JLabel();
        txtEan = new javax.swing.JTextField();
        lblStringNfe = new javax.swing.JLabel();
        lblStringPrecoVenda = new javax.swing.JLabel();
        txtPreco = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        cbCategoria = new javax.swing.JComboBox<>();
        lblStringId1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Alterar Produto");
        setMinimumSize(new java.awt.Dimension(710, 700));
        setResizable(false);

        lblStringNovoProduto.setBackground(new java.awt.Color(0, 102, 204));
        lblStringNovoProduto.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        lblStringNovoProduto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStringNovoProduto.setText("Alterar Produto");

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        linha2.setBackground(new java.awt.Color(0, 0, 0));
        linha2.setOpaque(true);

        lblStringUsuario.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblStringUsuario.setText("Usuário:");

        lblUsuario.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblUsuario.setText("Joao");

        lblStringFuncao.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblStringFuncao.setText("Função:");

        lblFuncao.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblFuncao.setText("Gerente");

        btnConfirmar.setBackground(new java.awt.Color(0, 153, 0));
        btnConfirmar.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
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
        btnCancelar.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\cancel.png")); // NOI18N
        btnCancelar.setText("  Cancelar");
        btnCancelar.setBorder(new javax.swing.border.MatteBorder(null));
        btnCancelar.setBorderPainted(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblStringId.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        lblStringId.setText("Identificador (ID):");

        txtId.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N

        lblStringNcm.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        lblStringNcm.setText("NCM:");

        txtNome.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N

        lblStringNome.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        lblStringNome.setText("Nome:");

        txtNcm.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N

        lblStringQuantidadeMin.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        lblStringQuantidadeMin.setText("Quantidade mínima:");

        txtQtdMinima.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        txtQtdMinima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQtdMinimaActionPerformed(evt);
            }
        });

        lblStringDescricao.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        lblStringDescricao.setText("Observações:");

        txtEan.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N

        lblStringNfe.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        lblStringNfe.setText("Código de Barras:");

        lblStringPrecoVenda.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        lblStringPrecoVenda.setText("Preço de venda (R$):");

        txtPreco.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N

        txtDescricao.setColumns(20);
        txtDescricao.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        txtDescricao.setRows(5);
        jScrollPane2.setViewportView(txtDescricao);

        cbCategoria.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCategoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCategoriaItemStateChanged(evt);
            }
        });

        lblStringId1.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        lblStringId1.setText("Categoria:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblStringNcm)
                            .addComponent(lblStringNome)
                            .addComponent(lblStringNfe)
                            .addComponent(lblStringId)
                            .addComponent(lblStringQuantidadeMin)
                            .addComponent(lblStringPrecoVenda)
                            .addComponent(lblStringDescricao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblStringId1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtNome)
                            .addComponent(txtNcm)
                            .addComponent(txtEan)
                            .addComponent(txtQtdMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblStringUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(152, 152, 152)
                        .addComponent(lblStringFuncao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(141, 141, 141))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblStringNovoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(208, 208, 208))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha2, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringUsuario)
                    .addComponent(lblUsuario)
                    .addComponent(lblStringFuncao)
                    .addComponent(lblFuncao))
                .addGap(37, 37, 37)
                .addComponent(lblStringNovoProduto)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblStringId))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblStringId1)
                        .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStringNome)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStringNcm)
                            .addComponent(txtNcm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStringNfe))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStringQuantidadeMin)
                            .addComponent(txtQtdMinima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStringPrecoVenda)
                            .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStringDescricao)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 139, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(110, 110, 110)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(587, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(655, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        /*boolean flagId = true;
        String cat = (String)cbCategoria.getSelectedItem();
        String id = txtId.getText();
        String nome = txtNome.getText();
        String precoString = txtPreco.getText();
        
        if(!(cat.equals("")|id.equals("")|nome.equals("")|precoString.equals(""))){
            try{               
                for(Produto p:listaProdutos){
                    if(p.getIdSistema()==Integer.parseInt(id)){
                        flagId=false;
                    }
                }
                for(Produto p:listaProdutos){
                    if(Integer.parseInt(id)==GerenciadorProdutos.prodSelecionado.getIdSistema()){
                        flagId = true;
                        break;
                    }
                }
                if(flagId){
                    Double preco = Double.parseDouble(GerenciadorComandas.tornarCompativel(txtPreco.getText()));
                    for(Categoria c:Login.categorias){
                        if(cat.equals(c.getNome())){
                            Categoria categoria = new Categoria();
                            categoria.setId(c.getId());
                            categoria.setNome(c.getNome());
                            categoria.setDescricao(c.getDescricao());
                            GerenciadorProdutos.prodSelecionado.setCategoria(categoria);
                        }
                    }
                    
                    ProdutoDAO pDao = new ProdutoDAO();
                    GerenciadorProdutos.prodSelecionado.setIdSistema(Integer.parseInt(txtId.getText()));
                    GerenciadorProdutos.prodSelecionado.setNome(txtNome.getText());
                    GerenciadorProdutos.prodSelecionado.setDescricao(txtDescricao.getText());
                    GerenciadorProdutos.prodSelecionado.setNcm(txtNcm.getText());
                    GerenciadorProdutos.prodSelecionado.setEan(txtEan.getText());
                    GerenciadorProdutos.prodSelecionado.setPreco(preco);
                    GerenciadorProdutos.prodSelecionado.setValidade("");
                    if(!txtQtdMinima.getText().equals("")){
                        GerenciadorProdutos.prodSelecionado.setQtdMinima(txtQtdMinima.getText());
                        String quantid = GerenciadorProdutos.prodSelecionado.getQtdEstoque();
                        GerenciadorProdutos.prodSelecionado.setQtdEstoque(quantid);
                    }else{
                        GerenciadorProdutos.prodSelecionado.setQtdMinima("X");
                        GerenciadorProdutos.prodSelecionado.setQtdEstoque("X");
                    }
                    pDao.update(GerenciadorProdutos.prodSelecionado);
                    new GerenciadorProdutos().setVisible(true); 
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "ID já existente!");
                }
            }catch(java.lang.NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Campo \"Preço\" inválido");
            }
        }else{
            String msg = "\n";
            if(cat.equals("")){
                msg += ("- Categoria\n");
            }
            if(id.equals("")){
                msg += ("- ID\n");
            }
            if(nome.equals("")){
                msg += ("- Nome\n");
            }
            if(precoString.equals("")){
                msg += ("- Preço\n");
            }
            JOptionPane.showMessageDialog(null, "O(s) seguinte(s) campo(s) deve(m) ser preenchido(s):"+msg);
        }*/
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        new GerenciadorProdutos().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtQtdMinimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQtdMinimaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQtdMinimaActionPerformed

    private void cbCategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCategoriaItemStateChanged

    }//GEN-LAST:event_cbCategoriaItemStateChanged

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
            java.util.logging.Logger.getLogger(AlterarProdutoOLD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlterarProdutoOLD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlterarProdutoOLD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlterarProdutoOLD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new AlterarProdutoOLD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private static javax.swing.JComboBox<String> cbCategoria;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblFuncao;
    private javax.swing.JLabel lblStringDescricao;
    private javax.swing.JLabel lblStringFuncao;
    private javax.swing.JLabel lblStringId;
    private javax.swing.JLabel lblStringId1;
    private javax.swing.JLabel lblStringNcm;
    private javax.swing.JLabel lblStringNfe;
    private javax.swing.JLabel lblStringNome;
    private javax.swing.JLabel lblStringNovoProduto;
    private javax.swing.JLabel lblStringPrecoVenda;
    private javax.swing.JLabel lblStringQuantidadeMin;
    private javax.swing.JLabel lblStringUsuario;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtEan;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNcm;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPreco;
    private javax.swing.JTextField txtQtdMinima;
    // End of variables declaration//GEN-END:variables
}


