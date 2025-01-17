package main;

import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.control.SelectionModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.bean.Categoria;
import model.bean.CategoriaPrato;
import model.bean.Prato;
import model.bean.Produto;
import model.dao.CategoriaDAO;
import model.dao.CategoriaPratoDAO;
import model.dao.ItemCardapioDAO;
import model.dao.PratoDAO;

public class AdicionarPrato extends javax.swing.JDialog {    
    public ArrayList<Prato> listaPratos;
    public PratoDAO pDao = new PratoDAO();
    public static boolean flagModificacao = false;
    String entradas = "";
    
    public AdicionarPrato(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);       
        getRootPane().setDefaultButton(btnAdicionar);
        listaPratos = new ArrayList<>();
        
        listaPratos.removeAll(listaPratos);
        for (Prato p: pDao.read()){
            listaPratos.add(p);
        }
        btnAdicionar.setEnabled(false);
        limparTabela();
        formatarTabela();
        criarComboBox();          
    }   
    
    public void criarTabela(ArrayList<Prato> pratos){
        ArrayList<Prato> ordenador = new ArrayList<>();
        
        // Percorre a lista de TODOS os Pratos da mesma CATEGORIA
        for (Prato pratoCategoria : pratos){
            boolean flagExistente = false;
            if (GerenciadorCardapios.cardapio.getPratos().size() > 0){
                // Percorre a lista de Pratos do Cardápio, para verificar se este Prato desta Categoria já está adicionado
                for (Prato pratoExistente:GerenciadorCardapios.cardapio.getPratos()){
                    if (pratoExistente.getId() == pratoCategoria.getId()){
                        flagExistente = true;
                        break;
                    }
                }
                
                if (!flagExistente){
                    ordenador.add(pratoCategoria);
                }
            }else{
                ordenador.add(pratoCategoria);
            } 
        }
        Collections.sort(ordenador);
        
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtPratos.getModel();
        limparTabela();
        for (Prato prato: ordenador){
            dtmBebidas.addRow(
                new Object[]{
                    prato.getId(),
                    prato.getNome()}
            );
        }
    }

    /*public void setarSelecionado(){
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtPratos.getModel();
        int quantidadeDeProdutos = dtmBebidas.getRowCount();
        
        for (int i = 0; i < quantidadeDeProdutos; i++){
            Integer id = (Integer)dtmBebidas.getValueAt(i, 1);
            for (Prato p : pratosSelecionados){
                if (p.getId() == id){
                    //jtProdutos.setRowSelectionInterval(i, i);
                    jtPratos.addRowSelectionInterval(i, i);
                }
            }
        }
    }*/
    
    public void limparTabela(){
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtPratos.getModel();
        int i = dtmBebidas.getRowCount();
        
        for (int j = 0; j < i; j++){
            dtmBebidas.removeRow(0);
        }       
    }
    
    public void criarComboBox(){
        cbCategorias.removeAllItems();
        cbCategorias.addItem("");
        CategoriaPratoDAO catDao = new CategoriaPratoDAO();
        for (CategoriaPrato c:catDao.read()){
            cbCategorias.addItem(c.getNome());
        }
    }

    public void formatarTabela(){        
        jtPratos.setRowHeight(26);
        jtPratos.getColumnModel().getColumn(0).setPreferredWidth(0); 
        jtPratos.getColumnModel().getColumn(1).setPreferredWidth(530);
        
        jtPratos.getColumnModel().getColumn(0).setMinWidth(0);
        jtPratos.getColumnModel().getColumn(1).setMinWidth(530);
        
        jtPratos.getColumnModel().getColumn(0).setMaxWidth(0);
        jtPratos.getColumnModel().getColumn(1).setMaxWidth(530);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtStringSuprimento = new javax.swing.JLabel();
        linha = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnCancelar = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblStringNomeProduto1 = new javax.swing.JLabel();
        cbCategorias = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtPratos = new javax.swing.JTable();
        lblStringNomeProduto2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Adição de Pratos");
        setMaximumSize(new java.awt.Dimension(519, 489));
        setMinimumSize(new java.awt.Dimension(519, 489));
        setResizable(false);

        txtStringSuprimento.setBackground(new java.awt.Color(0, 102, 204));
        txtStringSuprimento.setFont(new java.awt.Font("Century Gothic", 0, 19)); // NOI18N
        txtStringSuprimento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtStringSuprimento.setText("Adição de Pratos");

        linha.setBackground(new java.awt.Color(0, 0, 0));
        linha.setOpaque(true);

        btnCancelar.setBackground(new java.awt.Color(255, 0, 51));
        btnCancelar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnCancelar.setText("Voltar");
        btnCancelar.setBorder(new javax.swing.border.MatteBorder(null));
        btnCancelar.setBorderPainted(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAdicionar.setBackground(new java.awt.Color(0, 153, 51));
        btnAdicionar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnAdicionar.setText("Adicionar");
        btnAdicionar.setBorder(new javax.swing.border.MatteBorder(null));
        btnAdicionar.setBorderPainted(false);
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        lblStringNomeProduto1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblStringNomeProduto1.setText("Exibir Somente:");

        cbCategorias.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
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

        jtPratos.setBorder(new javax.swing.border.MatteBorder(null));
        jtPratos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jtPratos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtPratos.getTableHeader().setReorderingAllowed(false);
        jtPratos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtPratosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtPratosFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(jtPratos);
        if (jtPratos.getColumnModel().getColumnCount() > 0) {
            jtPratos.getColumnModel().getColumn(0).setResizable(false);
            jtPratos.getColumnModel().getColumn(1).setResizable(false);
        }

        lblStringNomeProduto2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblStringNomeProduto2.setText("Nome do Prato:");

        txtNome.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        txtNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNomeFocusGained(evt);
            }
        });
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomeKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblStringNomeProduto2)
                            .addComponent(lblStringNomeProduto1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(cbCategorias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringNomeProduto2)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringNomeProduto1)
                    .addComponent(cbCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(15, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(129, 129, 129))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txtStringSuprimento)
                                .addGap(177, 177, 177))))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(txtStringSuprimento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(57, 57, 57)
                    .addComponent(linha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(431, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(475, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        //materiasSelecionadas.removeAll(materiasSelecionadas);
        ItemCardapioDAO itemCDao = new ItemCardapioDAO();

        for (int i = 0; i < jtPratos.getSelectedRowCount(); i++){
            Integer idSelecionado = (Integer)jtPratos.getValueAt(jtPratos.getSelectedRows()[i], 0);
            for (Prato p:listaPratos){
                if (p.getId() == idSelecionado){
                    GerenciadorCardapios.cardapio.setPrato(p);
                    itemCDao.create(GerenciadorCardapios.cardapio, p);
                    flagModificacao = true;
                }
            }
        }
        //cbCategorias.setSelectedIndex(0);
        String nome = txtNome.getText();
        if (nome.equals("")){
            String escolhido = (String)cbCategorias.getSelectedItem();
            criarTabela(pDao.readForCategoria(escolhido));
        }else{
            criarTabela(pDao.readForNome(nome));
        }       
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void jtPratosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtPratosFocusGained
        btnAdicionar.setEnabled(true);
    }//GEN-LAST:event_jtPratosFocusGained

    private void cbCategoriasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCategoriasItemStateChanged
        String escolhido = (String)cbCategorias.getSelectedItem();
        //txtPesquisa.setText("");
        try{
            if (!escolhido.equals("")){
                criarTabela(pDao.readForCategoria(escolhido));
            }else{
                limparTabela();
            }
        }catch(java.lang.NullPointerException ex){}
    }//GEN-LAST:event_cbCategoriasItemStateChanged

    private void cbCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoriasActionPerformed

    }//GEN-LAST:event_cbCategoriasActionPerformed

    private void cbCategoriasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbCategoriasFocusGained
        txtNome.setText("");
        entradas = "";
        jtPratos.clearSelection();
        btnAdicionar.setEnabled(false);
    }//GEN-LAST:event_cbCategoriasFocusGained

    private void txtNomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNomeFocusGained
        cbCategorias.setSelectedIndex(0);
        jtPratos.clearSelection();
        btnAdicionar.setEnabled(false);
    }//GEN-LAST:event_txtNomeFocusGained

    private void txtNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyReleased
        /*Character entrada = evt.getKeyChar();
        int codigoTecla = evt.getKeyCode();
        
        if (txtNome.getText().equals("")){
            entradas = "";
        }else if (codigoTecla == 8){
            entradas = entradas.substring(0, entradas.length()-1);
        }else if (codigoTecla == 16){
            
        }else{
            entradas += entrada.toString();
        }        
        System.out.println("Entrada: "+entradas);      
        if (entradas.length()>= 3){
            criarTabelaNome(entradas);
        }else{
            limparTabela();
        }   */        
        String e = txtNome.getText();
        if (e.length() >= 3){
            criarTabela(pDao.readForNome(e));
        }else{
            limparTabela();
        }  
    }//GEN-LAST:event_txtNomeKeyReleased

    private void txtNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyTyped
        
    }//GEN-LAST:event_txtNomeKeyTyped

    private void jtPratosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtPratosFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jtPratosFocusLost

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
            java.util.logging.Logger.getLogger(AdicionarPrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdicionarPrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdicionarPrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdicionarPrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AdicionarPrato dialog = new AdicionarPrato(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> cbCategorias;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtPratos;
    private javax.swing.JLabel lblStringNomeProduto1;
    private javax.swing.JLabel lblStringNomeProduto2;
    private javax.swing.Box.Filler linha;
    private javax.swing.Box.Filler linha1;
    private javax.swing.JTextField txtNome;
    private javax.swing.JLabel txtStringSuprimento;
    // End of variables declaration//GEN-END:variables
}
