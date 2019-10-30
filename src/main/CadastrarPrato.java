package main;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.DefaultTableModel;
import model.bean.Categoria;
import model.bean.CategoriaPrato;
import model.bean.Prato;
import model.bean.Produto;
import model.dao.CategoriaDAO;
import model.dao.CategoriaPratoDAO;
import model.dao.PratoDAO;
import model.dao.ProdutoDAO;

public class CadastrarPrato extends javax.swing.JFrame {
    public static ArrayList<Produto> listaProdutos = new ArrayList<>();
    public ProdutoDAO pDao = new ProdutoDAO();
    
    public CadastrarPrato() {
        initComponents();
        this.setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnConfirmar);
        lblAdicionar.setText("<html><u>[Adicionar]</u></html>");
        listaProdutos.removeAll(listaProdutos);
        for (Produto p: pDao.read()){
            listaProdutos.add(p);
        }
        
        criarComboBoxCategoriasPratos();
    }

    public void atualizarLabel(){
        lblStringMaterias.setText("");
        String materias = "<html><body>";   
        int i = 0;
        if (!CadastrarSubproduto.subprodutosSelecionados.isEmpty()){ //Verifica se a lista de matérias NÃO está vazia
           // Integer tamanho = CadastrarMateriaPrima.materiasSelecionadas.size(); //Pega o tamanho da lista de matérias
            for (Produto p:CadastrarMateriaPrima.materiasSelecionadas){
                if (i == 3 & i == 6){
                    materias += "<br>";
                }
                materias += i+1+". "; //Concatena à string qual é o número da matéria-prima
                materias += p.getNome(); //Pega o nome da matéria-prima que está sendo adicionada
                materias += "  |  "; //Adiciona espaço entre uma matéria-prima e outra
                i += 1;
            }
        }
        materias += "</body></html>";
        lblStringMaterias.setText(materias);
    }
    
    public void criarComboBoxCategoriasPratos(){
        CategoriaPratoDAO cat = new CategoriaPratoDAO();
        cbCategoriaPrato.removeAllItems();
        cbCategoriaPrato.addItem("");
        for (CategoriaPrato c:cat.read()){
            cbCategoriaPrato.addItem(c.getNome());
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
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        lblStringDescricao1 = new javax.swing.JLabel();
        lblStringDescricao2 = new javax.swing.JLabel();
        cbCategoriaPrato = new javax.swing.JComboBox<>();
        lblAdicionar1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblStringId = new javax.swing.JLabel();
        lblAdicionar = new javax.swing.JLabel();
        lblStringMaterias = new javax.swing.JLabel();

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

        txtDescricao.setColumns(20);
        txtDescricao.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtDescricao.setRows(5);
        jScrollPane2.setViewportView(txtDescricao);

        lblStringDescricao1.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringDescricao1.setText("Observações:");

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

        lblStringId.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringId.setText("Matéria-prima:");
        lblStringId.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblStringIdMouseClicked(evt);
            }
        });

        lblAdicionar.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lblAdicionar.setText("[Adicionar]");
        lblAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAdicionarMouseClicked(evt);
            }
        });

        lblStringMaterias.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lblStringMaterias.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblStringMaterias.setText("Matéria-prima:");
        lblStringMaterias.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblStringMaterias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblStringMateriasMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblStringId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStringMaterias, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblAdicionar)
                        .addContainerGap(434, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringId)
                    .addComponent(lblAdicionar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStringMaterias, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblStringNome)
                            .addComponent(lblStringDescricao2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbCategoriaPrato, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAdicionar1))
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(lblStringDescricao1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2)))
                .addContainerGap(58, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(197, 197, 197))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblStringNovoProduto)
                        .addGap(293, 293, 293))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblStringNovoProduto)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringDescricao2)
                    .addComponent(cbCategoriaPrato, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAdicionar1))
                .addGap(46, 46, 46)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStringDescricao1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(556, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        Prato prato = new Prato();
        prato.setNome(txtNome.getText());
        prato.setDescricao(txtDescricao.getText());
        String cat = (String)cbCategoriaPrato.getSelectedItem();
        CategoriaPratoDAO catDao = new CategoriaPratoDAO();
        for (CategoriaPrato c:catDao.read()){
            if (c.getNome().equals(cat)){
                prato.setCategoria(c);
            }
        }
        
        // Pega os subprodutos (subproduto) do novo produto
        if (!CadastrarSubproduto.subprodutosSelecionados.isEmpty()){ // Verifica de a lista de matérias selecionadas NÃO é vazia
            for (Produto p : CadastrarSubproduto.subprodutosSelecionados){
                prato.setSubproduto(p);
            }
        }
        
        new PratoDAO().create(prato);
        dispose();
        new Menu().setVisible(true);
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        new Menu().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cbCategoriaPratoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCategoriaPratoItemStateChanged

    }//GEN-LAST:event_cbCategoriaPratoItemStateChanged

    private void cbCategoriaPratoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbCategoriaPratoFocusGained
        
    }//GEN-LAST:event_cbCategoriaPratoFocusGained

    private void lblAdicionar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAdicionar1MouseClicked
        new CadastrarCategoriaPrato().setVisible(true);
    }//GEN-LAST:event_lblAdicionar1MouseClicked

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        criarComboBoxCategoriasPratos();
        lblStringMaterias.setText("");
        String materias = "<html><body>";   
        int i = 0;
        if (!CadastrarSubproduto.subprodutosSelecionados.isEmpty()){ //Verifica se a lista de matérias NÃO está vazia
           // Integer tamanho = CadastrarMateriaPrima.materiasSelecionadas.size(); //Pega o tamanho da lista de matérias
            for (Produto p:CadastrarSubproduto.subprodutosSelecionados){
                if (i == 3 & i == 6){
                    System.out.println("entrei");
                    materias += "<br>";
                }
                materias += i+1+". "; //Concatena à string qual é o número da matéria-prima
                materias += p.getNome(); //Pega o nome da matéria-prima que está sendo adicionada
                materias += "  "; //Adiciona espaço entre uma matéria-prima e outra
                i += 1;
            }
        }
        materias += "</body></html>";
        lblStringMaterias.setText(materias);
    }//GEN-LAST:event_formWindowGainedFocus

    private void lblStringIdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStringIdMouseClicked

    }//GEN-LAST:event_lblStringIdMouseClicked

    private void lblAdicionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAdicionarMouseClicked
        CadastrarMateriaPrima materia = new CadastrarMateriaPrima(new javax.swing.JFrame(), true);
        materia.setVisible(true);
    }//GEN-LAST:event_lblAdicionarMouseClicked

    private void lblStringMateriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStringMateriasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblStringMateriasMouseClicked

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAdicionar;
    private javax.swing.JLabel lblAdicionar1;
    private javax.swing.JLabel lblStringDescricao1;
    private javax.swing.JLabel lblStringDescricao2;
    private javax.swing.JLabel lblStringId;
    private javax.swing.JLabel lblStringMaterias;
    private javax.swing.JLabel lblStringNome;
    private javax.swing.JLabel lblStringNovoProduto;
    private javax.swing.Box.Filler linha1;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}


