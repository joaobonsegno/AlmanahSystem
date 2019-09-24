package main;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import connection.ConnectionFactory;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import model.bean.Categoria;
import model.bean.Produto;
import model.dao.CategoriaDAO;
import model.dao.ProdutoDAO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class GerenciadorRelatorios extends javax.swing.JFrame {
    public static ArrayList<Produto> listaProdutos = new ArrayList<>();
    ProdutoDAO pDao;
    public static Produto prodSelecionado;
    
    public GerenciadorRelatorios() {        
        initComponents();
       
        jtProdutos.setRowHeight(27);
        getRootPane().setDefaultButton(btnOk);
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
        for (Produto p: pDao.read()){
            listaProdutos.add(p);
        }
        CadastrarMateriaPrima.materiasSelecionadas.removeAll(CadastrarMateriaPrima.materiasSelecionadas);
        criarTabela();
        criarComboBox();
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
            if (p.getPreco() == 0.0){
                dtmBebidas.addRow(
                    new Object[]{
                        p.getNome(),
                        "X",
                        p.getIdProduto()}
                );
            }else{
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
    
    public void criarTabelaNome(String nome){
        ArrayList<Produto> ordenador = new ArrayList<>();
        
        for (Produto prod : pDao.readForNome(nome)){
            ordenador.add(prod);
        }
        Collections.sort(ordenador);
        
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtProdutos.getModel();
        limparTabela();
        for (Produto p: ordenador){
            if (p.getPreco() == 0.0){
                dtmBebidas.addRow(
                    new Object[]{
                        p.getNome(),
                        "X",
                        p.getIdProduto()}
                );
            }else{
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
    
    public void criarTabelaCategoria(String nome){
        ArrayList<Produto> ordenador = new ArrayList<>();
        
        for (Produto prod : pDao.readForCategoria(nome)){
            ordenador.add(prod);
        }
        Collections.sort(ordenador);
        
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtProdutos.getModel();
        limparTabela();
        for (Produto p: ordenador){
            if (p.getPreco() == 0.0){
                dtmBebidas.addRow(
                    new Object[]{
                        p.getNome(),
                        "X",
                        p.getIdProduto()}
                );
            }else{
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
    
    public void limparTabela(){
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtProdutos.getModel();
        int i = dtmBebidas.getRowCount();
        
        for (int j = 0; j < i; j++){
            dtmBebidas.removeRow(0);
        }       
    }
    
    public void criarComboBox(){
        cbCategorias.removeAllItems();
        cbCategorias.addItem("");
        CategoriaDAO catDao = new CategoriaDAO();
        for (Categoria c:catDao.read()){
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
        lblStringNomeProduto = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        lblStringNomeProduto1 = new javax.swing.JLabel();
        cbCategorias = new javax.swing.JComboBox<>();
        btnOk1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciador de Produtos");
        setMaximumSize(new java.awt.Dimension(1046, 668));
        setMinimumSize(new java.awt.Dimension(1046, 668));
        setResizable(false);

        btnStringProdutos.setBackground(new java.awt.Color(0, 102, 204));
        btnStringProdutos.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        btnStringProdutos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringProdutos.setText("Gerenciador de Relatórios");

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
        jScrollPane1.setViewportView(jtProdutos);
        if (jtProdutos.getColumnModel().getColumnCount() > 0) {
            jtProdutos.getColumnModel().getColumn(0).setResizable(false);
            jtProdutos.getColumnModel().getColumn(1).setResizable(false);
            jtProdutos.getColumnModel().getColumn(2).setResizable(false);
        }

        lblStringNomeProduto.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNomeProduto.setText("Nome do Produto:");

        txtPesquisa.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        btnOk.setBackground(new java.awt.Color(0, 153, 204));
        btnOk.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        btnOk.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\pesquisar (1).png")); // NOI18N
        btnOk.setText(" Buscar");
        btnOk.setBorder(new javax.swing.border.MatteBorder(null));
        btnOk.setBorderPainted(false);
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        lblStringNomeProduto1.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNomeProduto1.setText("Tipo de Relatório:");

        cbCategorias.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cbCategorias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCategorias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCategoriasItemStateChanged(evt);
            }
        });
        cbCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoriasActionPerformed(evt);
            }
        });

        btnOk1.setBackground(new java.awt.Color(0, 153, 204));
        btnOk1.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        btnOk1.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\pesquisar (1).png")); // NOI18N
        btnOk1.setText(" PDF");
        btnOk1.setBorder(new javax.swing.border.MatteBorder(null));
        btnOk1.setBorderPainted(false);
        btnOk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOk1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(btnOk1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 416, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLancador)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblStringNomeProduto)
                                    .addComponent(lblStringNomeProduto1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnStringProdutos)
                                .addGap(149, 149, 149)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 1182, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnStringProdutos))
                    .addComponent(btnLancador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStringNomeProduto)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStringNomeProduto1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(btnOk1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(137, 137, 137))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(694, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLancadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancadorActionPerformed
        new Menu().setVisible(true); 
        dispose();
    }//GEN-LAST:event_btnLancadorActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        cbCategorias.setSelectedIndex(0);
        criarTabelaNome(txtPesquisa.getText());     
    }//GEN-LAST:event_btnOkActionPerformed

    private void cbCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoriasActionPerformed
        
    }//GEN-LAST:event_cbCategoriasActionPerformed

    private void cbCategoriasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCategoriasItemStateChanged
        String escolhido = (String)cbCategorias.getSelectedItem();
        txtPesquisa.setText("");
        try{
            if (!escolhido.equals("")){
                criarTabelaCategoria(escolhido);
            }else{
                criarTabela();
            }
        }catch(java.lang.NullPointerException ex){
            
        }
    }//GEN-LAST:event_cbCategoriasItemStateChanged

    private void btnOk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOk1ActionPerformed
        // -------------------------- Com iReport ----------------------------
        Connection con = ConnectionFactory.getConnection();
        String src = "C:\\Projetos Netbeans\\AlmanahSystem\\arquivos\\Produtos.jasper";
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(src, null, con);           
        } catch (JRException ex) {
            System.out.println("Erro: "+ex);
        }
        
        JasperViewer viewer = new JasperViewer(jasperPrint, false);
        viewer.setVisible(true);
        
        /*
            Document documento = new Document();
            try {
            PdfWriter.getInstance(documento, new FileOutputStream("C:\\Projetos Netbeans\\AlmanahSystem\\arquivos\\Documento.pdf"));
            documento.open();
            documento.add(new Paragraph("Oi, nenê"));
            
            } catch (FileNotFoundException | DocumentException ex) {
            System.out.println("Erro: "+ex);
            }finally{
            documento.close();
            }
            
            try {
            Desktop.getDesktop().open(new File("C:\\Projetos Netbeans\\AlmanahSystem\\arquivos\\Documento.pdf"));
            } catch (IOException ex) {
            System.out.println("Erro: "+ex);
            }*/
    }//GEN-LAST:event_btnOk1ActionPerformed

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
            java.util.logging.Logger.getLogger(GerenciadorRelatorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciadorRelatorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciadorRelatorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciadorRelatorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new GerenciadorRelatorios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLancador;
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnOk1;
    private javax.swing.JLabel btnStringProdutos;
    private javax.swing.JComboBox<String> cbCategorias;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtProdutos;
    private javax.swing.JLabel lblStringNomeProduto;
    private javax.swing.JLabel lblStringNomeProduto1;
    private javax.swing.Box.Filler linha1;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}

