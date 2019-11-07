package main;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Categoria;
import model.bean.Produto;
import model.dao.CategoriaDAO;
import model.dao.ProdutoDAO;

public class GerenciadorProdutos extends javax.swing.JFrame {

    public static ArrayList<Produto> listaProdutos = new ArrayList<>();
    ProdutoDAO pDao;
    public static Produto prodSelecionado;

    public GerenciadorProdutos() {
        initComponents();

        jtProdutos.setRowHeight(27);
        this.setLocationRelativeTo(null);
        jtProdutos.getColumnModel().getColumn(0).setPreferredWidth(500);
        jtProdutos.getColumnModel().getColumn(1).setPreferredWidth(150);
        jtProdutos.getColumnModel().getColumn(2).setPreferredWidth(400);

        jtProdutos.getColumnModel().getColumn(0).setMinWidth(500);
        jtProdutos.getColumnModel().getColumn(1).setMinWidth(150);
        jtProdutos.getColumnModel().getColumn(2).setMinWidth(400);

        jtProdutos.getColumnModel().getColumn(0).setMaxWidth(500);
        jtProdutos.getColumnModel().getColumn(1).setMaxWidth(150);
        jtProdutos.getColumnModel().getColumn(2).setMaxWidth(400);
        pDao = new ProdutoDAO();
        listaProdutos.removeAll(listaProdutos);
        for (Produto p : pDao.read()) {
            listaProdutos.add(p);
        }
        CadastrarMateriaPrima.materiasSelecionadas.removeAll(CadastrarMateriaPrima.materiasSelecionadas);
        criarComboBox();
        btnAlterar.setEnabled(false);
        btnInativar.setEnabled(false);
    }

    public void criarTabelaNome(String nome) {
        ArrayList<Produto> ordenador = new ArrayList<>();

        for (Produto prod : pDao.readForNome(nome)) {
            ordenador.add(prod);
        }
        Collections.sort(ordenador);

        DefaultTableModel dtmBebidas = (DefaultTableModel) jtProdutos.getModel();
        limparTabela();
        for (Produto p : ordenador) {
            if (p.getPreco() == 0.0) {
                dtmBebidas.addRow(
                        new Object[]{
                            p.getNome(),
                            "X",
                            p.getIdProduto()}
                );
            } else {
                String valor = GerenciadorComandas.valorMonetario(p.getPreco());
                dtmBebidas.addRow(
                        new Object[]{
                            p.getNome(),
                            valor,
                            p.getIdProduto()}
                );
            }
        }
    }

    public void criarTabelaCategoria(String nome) {
        ArrayList<Produto> ordenador = new ArrayList<>();

        for (Produto prod : pDao.readForCategoria(nome)) {
            ordenador.add(prod);
        }
        Collections.sort(ordenador);

        DefaultTableModel dtmBebidas = (DefaultTableModel) jtProdutos.getModel();
        limparTabela();
        for (Produto p : ordenador) {
            if (p.getPreco() == 0.0) {
                dtmBebidas.addRow(
                        new Object[]{
                            p.getNome(),
                            "X",
                            p.getIdProduto()}
                );
            } else {
                String valor = GerenciadorComandas.valorMonetario(p.getPreco());
                dtmBebidas.addRow(
                        new Object[]{
                            p.getNome(),
                            valor,
                            p.getIdProduto()}
                );
            }
        }
    }

    public void limparTabela() {
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtProdutos.getModel();
        int i = dtmBebidas.getRowCount();

        for (int j = 0; j < i; j++) {
            dtmBebidas.removeRow(0);
        }
    }

    public void criarComboBox() {
        cbCategorias.removeAllItems();
        cbCategorias.addItem("");
        CategoriaDAO catDao = new CategoriaDAO();
        for (Categoria c : catDao.read()) {
            cbCategorias.addItem(c.getNome());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStringProdutos = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnLancador = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProdutos = new javax.swing.JTable();
        btnAlterar = new javax.swing.JButton();
        btnInativar = new javax.swing.JButton();
        lblStringNomeProduto = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        lblStringNomeProduto1 = new javax.swing.JLabel();
        cbCategorias = new javax.swing.JComboBox<>();
        linha2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnLancador1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Gerenciador de Produtos");
        setMaximumSize(new java.awt.Dimension(763, 668));
        setMinimumSize(new java.awt.Dimension(763, 668));
        setResizable(false);

        btnStringProdutos.setBackground(new java.awt.Color(0, 102, 204));
        btnStringProdutos.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        btnStringProdutos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringProdutos.setText("Gerenciador de Produtos");

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
                "Nome", "Valor de Venda (R$)", "ID"
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
        });
        jScrollPane1.setViewportView(jtProdutos);
        if (jtProdutos.getColumnModel().getColumnCount() > 0) {
            jtProdutos.getColumnModel().getColumn(0).setResizable(false);
            jtProdutos.getColumnModel().getColumn(1).setResizable(false);
            jtProdutos.getColumnModel().getColumn(2).setResizable(false);
        }

        btnAlterar.setBackground(new java.awt.Color(204, 204, 0));
        btnAlterar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnAlterar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\lapis.png")); // NOI18N
        btnAlterar.setText("   Alterar");
        btnAlterar.setBorder(new javax.swing.border.MatteBorder(null));
        btnAlterar.setBorderPainted(false);
        btnAlterar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnAlterarFocusGained(evt);
            }
        });
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnInativar.setBackground(new java.awt.Color(204, 0, 0));
        btnInativar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnInativar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\delete (1).png")); // NOI18N
        btnInativar.setText("   Inativar");
        btnInativar.setBorder(new javax.swing.border.MatteBorder(null));
        btnInativar.setBorderPainted(false);
        btnInativar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInativarActionPerformed(evt);
            }
        });

        lblStringNomeProduto.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNomeProduto.setText("Nome do Produto:");

        txtPesquisa.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtPesquisa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPesquisaFocusGained(evt);
            }
        });
        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyReleased(evt);
            }
        });

        lblStringNomeProduto1.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNomeProduto1.setText("Exibir Somente:");

        cbCategorias.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cbCategorias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCategorias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCategoriasItemStateChanged(evt);
            }
        });
        cbCategorias.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cbCategoriasFocusGained(evt);
            }
        });
        cbCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoriasActionPerformed(evt);
            }
        });

        linha2.setBackground(new java.awt.Color(0, 0, 0));
        linha2.setOpaque(true);

        btnLancador1.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        btnLancador1.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\inativo true (1).png")); // NOI18N
        btnLancador1.setText("Inativos");
        btnLancador1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLancador1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnLancador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnStringProdutos)
                .addGap(103, 103, 103)
                .addComponent(btnLancador1))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblStringNomeProduto)
                                    .addComponent(lblStringNomeProduto1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(124, 124, 124))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(btnInativar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(71, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha2, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLancador1)
                    .addComponent(btnLancador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStringProdutos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStringNomeProduto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStringNomeProduto1))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInativar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(658, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLancadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancadorActionPerformed
        new Menu().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLancadorActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        try {
            Menu.acaoEscolhida = 2;
            Integer idSelecionado = (Integer) jtProdutos.getValueAt(jtProdutos.getSelectedRow(), 2);

            for (Produto p : listaProdutos) {
                if (p.getIdProduto().equals(idSelecionado)) {
                    prodSelecionado = p;
                    new AlterarProduto().setVisible(true);
                    dispose();
                }
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Selecione O produto que deseja alterar");
        }
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnInativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInativarActionPerformed
        try {
            if (jtProdutos.getSelectedRowCount() > 1) {
                JOptionPane.showMessageDialog(null, "Selecione somente 1 (um) produto");
            } else {
                int reply = JOptionPane.showConfirmDialog(null, "Deseja realmente inativar o produto?", "Inativação de Produto", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    Integer idSelecionado = (Integer) jtProdutos.getValueAt(jtProdutos.getSelectedRow(), 2);

                    for (Produto p : listaProdutos) {
                        if (p.getIdProduto().equals(idSelecionado)) {
                            ProdutoDAO cDao = new ProdutoDAO();
                            cDao.setInativo(p.getIdProduto());
                            this.limparTabela();
                            btnAlterar.setEnabled(false);
                            btnInativar.setEnabled(false);
                            jtProdutos.clearSelection();
                        }
                    }
                }
            }
        } catch (java.util.ConcurrentModificationException ex) {
            System.out.println("Deu a exceção");
        } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Selecione O produto que deseja inativar");
        }
    }//GEN-LAST:event_btnInativarActionPerformed

    private void cbCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoriasActionPerformed

    }//GEN-LAST:event_cbCategoriasActionPerformed

    private void cbCategoriasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCategoriasItemStateChanged
        String escolhido = (String) cbCategorias.getSelectedItem();
        txtPesquisa.setText("");
        try {
            if (!escolhido.equals("")) {
                criarTabelaCategoria(escolhido);
            } else {
                limparTabela();
            }
        } catch (java.lang.NullPointerException ex) {

        }
    }//GEN-LAST:event_cbCategoriasItemStateChanged

    private void txtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyReleased
        String entrada = txtPesquisa.getText();
        if (entrada.length() >= 3) {
            this.criarTabelaNome(entrada);
        } else {
            this.limparTabela();
        }

    }//GEN-LAST:event_txtPesquisaKeyReleased

    private void txtPesquisaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesquisaFocusGained
        cbCategorias.setSelectedIndex(0);
        btnAlterar.setEnabled(false);
        btnInativar.setEnabled(false);
        jtProdutos.clearSelection();
    }//GEN-LAST:event_txtPesquisaFocusGained

    private void btnLancador1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancador1ActionPerformed
        InativosProdutos novoPrato = new InativosProdutos(new javax.swing.JFrame(), true);
        novoPrato.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLancador1ActionPerformed

    private void jtProdutosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtProdutosFocusGained
        btnAlterar.setEnabled(true);
        btnInativar.setEnabled(true);
    }//GEN-LAST:event_jtProdutosFocusGained

    private void cbCategoriasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbCategoriasFocusGained
        btnAlterar.setEnabled(false);
        btnInativar.setEnabled(false);
        jtProdutos.clearSelection();
    }//GEN-LAST:event_cbCategoriasFocusGained

    private void btnAlterarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnAlterarFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAlterarFocusGained

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
            java.util.logging.Logger.getLogger(GerenciadorProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciadorProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciadorProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciadorProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerenciadorProdutos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnInativar;
    private javax.swing.JButton btnLancador;
    private javax.swing.JButton btnLancador1;
    private javax.swing.JLabel btnStringProdutos;
    private javax.swing.JComboBox<String> cbCategorias;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtProdutos;
    private javax.swing.JLabel lblStringNomeProduto;
    private javax.swing.JLabel lblStringNomeProduto1;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
