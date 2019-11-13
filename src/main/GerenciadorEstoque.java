package main;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.bean.Alerta;
import model.bean.Produto;
import model.dao.AlertaDAO;
import model.dao.ProdutoDAO;
import manual.Manual;

public class GerenciadorEstoque extends javax.swing.JFrame {
    private ArrayList<Alerta> alertas = new ArrayList<>();
    ProdutoDAO pDao = new ProdutoDAO();
    
     public GerenciadorEstoque() {
        initComponents();     
        this.setLocationRelativeTo(null);
        formatarTabela();
        criarTabela();   
        btnDarEntrada.setEnabled(false);
        btnDarBaixa.setEnabled(false); 
        AlertaDAO aDao = new AlertaDAO();
        ProdutoDAO pDao = new ProdutoDAO();
        
        for (Produto prod : pDao.read()){
            if (!prod.getQtdEstoque().equals("X")){
                Alerta a = aDao.readForProduto(prod.getIdProduto());
                if (a.getMensagem() != null){
                    alertas.add(a);
                }
            }
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
        
        if (qtdAtual <= Integer.parseInt(prod.getQtdMinima())){
            AlertaDAO aDao = new AlertaDAO();
            Alerta a = aDao.readForProduto(prod.getIdProduto());
            if (a.getMensagem() == null){
                a.setMensagem("O produto \""+prod.getNome()+"\" está com estoque baixo");
                a.setProduto(prod);
                aDao.create(a);
            } 
        }
    }
        
    public void criarTabela(){
        limparTabela();
        ArrayList<Produto> ordenador = new ArrayList<>();
        
        for (Produto prod : pDao.read()){
            ordenador.add(prod);
        }
        Collections.sort(ordenador);
        
        DefaultTableModel dtmProdutos = (DefaultTableModel) jtProdutos.getModel();

        for (Produto p: ordenador){
            if(!p.getQtdMinima().equals("X")){           
                dtmProdutos.addRow(
                new Object[]{
                    p.getIdProduto(),
                    p.getQtdEstoque(),
                    p.getUnidadeDeMedida(),
                    p.getNome()}
                ); 
            }
        }
        //corNaLinhaInteiro();
    }
    
    public void criarTabelaNome(String nome){
        ArrayList<Produto> ordenador = new ArrayList<>();
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
                    p.getUnidadeDeMedida(),
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

    public void formatarTabela(){
        jtProdutos.setRowHeight(29);
        jtProdutos.getColumnModel().getColumn(0).setPreferredWidth(0); 
        jtProdutos.getColumnModel().getColumn(1).setPreferredWidth(60);
        jtProdutos.getColumnModel().getColumn(2).setPreferredWidth(100);
        jtProdutos.getColumnModel().getColumn(3).setPreferredWidth(550);

        jtProdutos.getColumnModel().getColumn(0).setMinWidth(0);
        jtProdutos.getColumnModel().getColumn(1).setMinWidth(60);
        jtProdutos.getColumnModel().getColumn(2).setMinWidth(100);
        jtProdutos.getColumnModel().getColumn(3).setMinWidth(550);

        jtProdutos.getColumnModel().getColumn(0).setMaxWidth(0);
        jtProdutos.getColumnModel().getColumn(1).setMaxWidth(60);
        jtProdutos.getColumnModel().getColumn(2).setMaxWidth(100);
        jtProdutos.getColumnModel().getColumn(3).setMaxWidth(550);
    }

    // MÉTODOS PARA ARRUMAR CÉLULAS DA TABELA
    
    /*public void corNaLinhaInteiro(){
        jtProdutos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                //String s = (String)table.getValueAt(row, 0);
                //Integer id = Integer.parseInt(s);
                Integer id = (Integer)table.getValueAt(row, 0);
                Produto p = pDao.readForId(id);
                try{
                    if (Integer.parseInt(p.getQtdEstoque()) > Integer.parseInt(p.getQtdMinima())){
                        System.out.println("Entrei no IF");
                        label.setBackground(new java.awt.Color(255, 153, 153));
                    }else{
                        
                    }
                }catch(java.lang.NumberFormatException ex){
                    if (Double.parseDouble(p.getQtdEstoque()) > Double.parseDouble(p.getQtdMinima())){
                        System.out.println("Entrei no IF do CATCH");
                        label.setBackground(new java.awt.Color(255, 153, 153));
                    }
                }finally{
                    return label;
                }
            }
        });
    }*/
    
    public void corNaLinhaInteiro(){
        jtProdutos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                Integer id = (Integer)table.getValueAt(row, 0);
                
                for (Alerta a : alertas){
                    if (Objects.equals(a.getProduto().getIdProduto(), id)){
                        //System.out.println("ID Igual: "+id);
                        label.setBackground(new java.awt.Color(255, 153, 153));
                        //label.setForeground(new java.awt.Color(255, 0, 0));
                    }else{
                        //System.out.println("ID Dif: "+id);
                        label.setBackground(Color.WHITE);
                        //label.setForeground(Color.BLACK);
                    }
                }
                
                //jtProdutos.setSelectionBackground(new java.awt.Color(0, 153, 0));
                jtProdutos.setSelectionForeground(new java.awt.Color(0, 153, 0));
                //jtProdutos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                return label;
                
            }
        });
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
        btnDarBaixa = new javax.swing.JButton();
        linha2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        lblManual = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciador de Estoque");
        setMinimumSize(new java.awt.Dimension(681, 535));
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
                "ID", "Qtd", "Medida", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
        jtProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtProdutos);
        if (jtProdutos.getColumnModel().getColumnCount() > 0) {
            jtProdutos.getColumnModel().getColumn(0).setResizable(false);
            jtProdutos.getColumnModel().getColumn(1).setResizable(false);
            jtProdutos.getColumnModel().getColumn(2).setResizable(false);
            jtProdutos.getColumnModel().getColumn(3).setResizable(false);
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

        btnDarBaixa.setBackground(new java.awt.Color(255, 0, 0));
        btnDarBaixa.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnDarBaixa.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\entrada.png")); // NOI18N
        btnDarBaixa.setText("   Dar Baixa");
        btnDarBaixa.setBorder(new javax.swing.border.MatteBorder(null));
        btnDarBaixa.setBorderPainted(false);
        btnDarBaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDarBaixaActionPerformed(evt);
            }
        });

        linha2.setBackground(new java.awt.Color(0, 0, 0));
        linha2.setOpaque(true);

        lblManual.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblManual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblManual.setText("?");
        lblManual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblManual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblManualMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLancador)
                        .addGap(76, 76, 76)
                        .addComponent(btnStringProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblStringNomeProduto)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(88, 88, 88)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(btnDarBaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(btnDarEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(lblManual)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha2, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStringProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLancador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStringNomeProduto))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDarEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDarBaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblManual)
                        .addContainerGap())))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(521, Short.MAX_VALUE)))
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

    private void btnDarBaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDarBaixaActionPerformed
        Integer idSelecionado = (Integer)jtProdutos.getValueAt(jtProdutos.getSelectedRow(), 0);

        DarBaixa baixa = new DarBaixa(new javax.swing.JFrame(), true, idSelecionado);
        baixa.setVisible(true); 
        dispose();
    }//GEN-LAST:event_btnDarBaixaActionPerformed

    private void jtProdutosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtProdutosFocusGained
        btnDarEntrada.setEnabled(true);
        btnDarBaixa.setEnabled(true);
    }//GEN-LAST:event_jtProdutosFocusGained

    private void txtPesquisaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesquisaFocusGained
        btnDarEntrada.setEnabled(false);
        btnDarBaixa.setEnabled(false);
        jtProdutos.clearSelection();
    }//GEN-LAST:event_txtPesquisaFocusGained

    private void jtProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtProdutosMouseClicked
        
    }//GEN-LAST:event_jtProdutosMouseClicked

    private void txtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyReleased
        String entrada = txtPesquisa.getText();
        if (entrada.length() >= 3) {
            this.criarTabelaNome(entrada);
        } else {
            this.criarTabela();
        }
    }//GEN-LAST:event_txtPesquisaKeyReleased

    private void lblManualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblManualMouseClicked
        Manual.abrirManual("gerenciadorEstoque");
    }//GEN-LAST:event_lblManualMouseClicked

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
    private javax.swing.JButton btnDarBaixa;
    private javax.swing.JButton btnDarEntrada;
    private javax.swing.JButton btnLancador;
    private javax.swing.JLabel btnStringProdutos;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtProdutos;
    private javax.swing.JLabel lblManual;
    private javax.swing.JLabel lblStringNomeProduto;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}


