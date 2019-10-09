package main;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;

import connection.ConnectionFactory;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Categoria;
import model.bean.Forma;
import model.bean.Funcionario;
import model.bean.Produto;
import model.bean.Venda;
import model.dao.CategoriaDAO;
import model.dao.FuncionarioDAO;
import model.dao.ProdutoDAO;
import model.dao.VendaDAO;
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
        this.setLocationRelativeTo(null);

        pDao = new ProdutoDAO();
        listaProdutos.removeAll(listaProdutos);
        for (Produto p : pDao.read()) {
            listaProdutos.add(p);
        }
        CadastrarMateriaPrima.materiasSelecionadas.removeAll(CadastrarMateriaPrima.materiasSelecionadas);
        criarComboBox();
    }

    public void criarComboBox() {
        cbCategorias.removeAllItems();
        cbCategorias.addItem("");
        CategoriaDAO catDao = new CategoriaDAO();
        for (Categoria c : catDao.read()) {
            cbCategorias.addItem(c.getNome());
        }
    }

    // ----------------------- MÉTODOS PARA CRIAR RELATÓRIOS -----------------------
    public static PdfPTable criarCabecalho() throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{1f, 4f, 6f});
        PdfPCell celulaNome = new PdfPCell(new Phrase("No"));
        celulaNome.setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell celulaData = new PdfPCell(new Phrase("Valor (R$)"));
        celulaData.setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell celulaSexo = new PdfPCell(new Phrase("Data"));
        celulaSexo.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(celulaNome);
        table.addCell(celulaData);
        table.addCell(celulaSexo);

        return table;
    }

    public static void preencherDados(Document document, PdfPTable table, ArrayList<Venda> lista) throws DocumentException {
        Integer contador = 1;
        String c = "";
        if (document.isOpen()) {
            for (Venda venda : lista) {
                c = contador.toString();
                PdfPCell celula1 = new PdfPCell(new Phrase(c));
                PdfPCell celula2 = new PdfPCell(new Phrase(GerenciadorComandas.valorMonetario(venda.getTotal())));
                PdfPCell celula3 = new PdfPCell(new Phrase(venda.getData()));
                celula1.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula2.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula3.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                table.addCell(celula1);
                table.addCell(celula2);
                table.addCell(celula3);
                contador += 1;
            }           
            document.add(table);
        }
    }

    // -----------------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStringProdutos = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnLancador = new javax.swing.JButton();
        lblStringNomeProduto1 = new javax.swing.JLabel();
        cbCategorias = new javax.swing.JComboBox<>();
        btnOk1 = new javax.swing.JButton();
        dataMenor = new com.toedter.calendar.JDateChooser();
        dataMaior = new com.toedter.calendar.JDateChooser();
        lblStringNomeProduto2 = new javax.swing.JLabel();
        lblStringNomeProduto3 = new javax.swing.JLabel();

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
        btnOk1.setText(" Gerar Relatório");
        btnOk1.setBorder(new javax.swing.border.MatteBorder(null));
        btnOk1.setBorderPainted(false);
        btnOk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOk1ActionPerformed(evt);
            }
        });

        dataMenor.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        dataMaior.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        lblStringNomeProduto2.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNomeProduto2.setText("Data Final:");

        lblStringNomeProduto3.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNomeProduto3.setText("Data Inicial:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnOk1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(143, 143, 143))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblStringNomeProduto1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(156, 156, 156))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dataMenor, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblStringNomeProduto3))
                                .addGap(113, 113, 113)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblStringNomeProduto2)
                                    .addComponent(dataMaior, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLancador)
                        .addGap(18, 18, 18)
                        .addComponent(btnStringProdutos)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnStringProdutos))
                    .addComponent(btnLancador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStringNomeProduto1))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringNomeProduto2)
                    .addComponent(lblStringNomeProduto3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dataMenor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataMaior, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80)
                .addComponent(btnOk1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(300, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLancadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancadorActionPerformed
        new Menu().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLancadorActionPerformed

    private void cbCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoriasActionPerformed

    }//GEN-LAST:event_cbCategoriasActionPerformed

    private void cbCategoriasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCategoriasItemStateChanged

    }//GEN-LAST:event_cbCategoriasItemStateChanged

    private void btnOk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOk1ActionPerformed
        try{
            Double credito = 0.0; Double debito = 0.0; Double voucher = 0.0; Double dinheiro = 0.0;            
            VendaDAO v = new VendaDAO();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dMaior = sdf.format(dataMaior.getDate());
            String dMenor = sdf.format(dataMenor.getDate());
            ArrayList<Venda> lista = new ArrayList<>();

            //System.out.println("DIf: "+v.diferencaDatas(dMaior, dMenor)+"\n");
            if (v.diferencaDatas(dMaior, dMenor) >= 0) {
                Paragraph pTitulo = new Paragraph(new Phrase(20F, "Restaurante Almanah\n", FontFactory.getFont(FontFactory.HELVETICA, 20F)));
                pTitulo.setAlignment(Element.ALIGN_CENTER);

                Paragraph pSubtitulo = new Paragraph(new Phrase(20F, "Relatório de Vendas\n", FontFactory.getFont(FontFactory.HELVETICA, 16F)));
                pSubtitulo.setAlignment(Element.ALIGN_CENTER);
                
                Paragraph pSubtituloDatas = new Paragraph(new Phrase(20F, "Data Inicial: "+dMenor+"  Data Final: "+dMaior+"\n\n", FontFactory.getFont(FontFactory.HELVETICA, 12F)));
                pSubtituloDatas.setAlignment(Element.ALIGN_CENTER);
                
                Paragraph pData = new Paragraph(new Phrase(20F, "Data de Emissão: "+GerenciadorComandas.getDataAtualComHoraFormatoBr()+"\n\n", FontFactory.getFont(FontFactory.HELVETICA, 11F)));
                pData.setAlignment(Element.ALIGN_RIGHT);
                Document documento = new Document();
                try {
                    PdfWriter pdf = PdfWriter.getInstance(documento, new FileOutputStream("C:\\Projetos Netbeans\\AlmanahSystem\\arquivos\\Documento.pdf"));
                    
                    //Adiciona ao documento as estruturas de cabeçalho
                    documento.open();
                    documento.add(pTitulo);
                    documento.add(pSubtitulo);
                    documento.add(pSubtituloDatas);
                    documento.add(pData);
                    
                    //Cria a tabela e adiciona seu conteúdo
                    PdfPTable table = this.criarCabecalho();                    
                    for (Venda ve : v.relatorio(dMaior, dMenor)) {
                        lista.add(ve);
                        for (Forma f : v.getFormas(ve)){
                            if (f.getFormaPagamento().equals("Débito")){
                                debito += f.getValor();
                            }else if (f.getFormaPagamento().equals("Crédito")){
                                credito += f.getValor();  break;
                            }else if (f.getFormaPagamento().equals("Voucher")){
                                voucher += f.getValor();
                            }else{
                                dinheiro += f.getValor();
                            }                           
                        }
                    }
                    this.preencherDados(documento, table, lista);
                    
                    //Cria o rodapé, com totais das vendas
                    System.out.println("Débito: "+debito);
                    System.out.println("Crédito: "+credito);
                    System.out.println("Voucher: "+voucher);
                    System.out.println("Dinheiro: "+dinheiro);
                    
                    documento.add(new Paragraph("\n\n"));
                    Paragraph pDinheiro = new Paragraph(new Phrase(20F, "Dinheiro: R$ "+GerenciadorComandas.valorMonetario(dinheiro)+"\n", FontFactory.getFont(FontFactory.HELVETICA, 12F)));
                    pDinheiro.setAlignment(Element.ALIGN_RIGHT);
                    Paragraph pDebito = new Paragraph(new Phrase(20F, "Débito: R$ "+GerenciadorComandas.valorMonetario(debito)+"\n", FontFactory.getFont(FontFactory.HELVETICA, 12F)));
                    pDebito.setAlignment(Element.ALIGN_RIGHT);
                    Paragraph pCredito = new Paragraph(new Phrase(20F, "Crédito: R$ "+GerenciadorComandas.valorMonetario(credito)+"\n", FontFactory.getFont(FontFactory.HELVETICA, 12F)));
                    pCredito.setAlignment(Element.ALIGN_RIGHT);
                    Paragraph pVoucher = new Paragraph(new Phrase(20F, "Voucher: R$ "+GerenciadorComandas.valorMonetario(voucher)+"\n", FontFactory.getFont(FontFactory.HELVETICA, 12F)));
                    pVoucher.setAlignment(Element.ALIGN_RIGHT);
                    Paragraph pLinha = new Paragraph(new Phrase(20F, "______________\n\n", FontFactory.getFont(FontFactory.HELVETICA, 12F)));
                    pLinha.setAlignment(Element.ALIGN_RIGHT);
                    
                    documento.add(pDinheiro);
                    documento.add(pDebito);
                    documento.add(pCredito);
                    documento.add(pVoucher);
                    documento.add(pLinha);
                    Double total = 0.0; total += dinheiro; total += debito; total += credito; total += voucher; 
                    Paragraph pTotal = new Paragraph(new Phrase(20F, "Total:  R$ "+GerenciadorComandas.valorMonetario(total), FontFactory.getFont(FontFactory.HELVETICA, 14F)));
                    pTotal.setAlignment(Element.ALIGN_RIGHT);
                    documento.add(pTotal);
                } catch (FileNotFoundException | DocumentException ex) {
                    System.out.println("Erro: " + ex);
                } finally {
                    documento.close();
                }
                try {
                    Desktop.getDesktop().open(new File("C:\\Projetos Netbeans\\AlmanahSystem\\arquivos\\Documento.pdf"));
                } catch (IOException ex) {
                    System.out.println("Erro: " + ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Erro: data inicial deve ser maior ou igual à data final");
            }
        }catch(java.lang.NullPointerException ex){
            JOptionPane.showMessageDialog(null, "Erro: informe as datas inicial e final");
        }
        

        /*
        // ----------------------------- Com iReport -------------------------------
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
        
        // -------------------------- Gerar PDF "vazio" ----------------------------
        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, new FileOutputStream("C:\\Projetos Netbeans\\AlmanahSystem\\arquivos\\Documento.pdf"));
            documento.open();
            documento.add(new Paragraph("Oi, nenê"));

        } catch (FileNotFoundException | DocumentException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            documento.close();
        }

        try {
            Desktop.getDesktop().open(new File("C:\\Projetos Netbeans\\AlmanahSystem\\arquivos\\Documento.pdf"));
        } catch (IOException ex) {
            System.out.println("Erro: " + ex);
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
    private javax.swing.JButton btnOk1;
    private javax.swing.JLabel btnStringProdutos;
    private javax.swing.JComboBox<String> cbCategorias;
    private com.toedter.calendar.JDateChooser dataMaior;
    private com.toedter.calendar.JDateChooser dataMenor;
    private javax.swing.JLabel lblStringNomeProduto1;
    private javax.swing.JLabel lblStringNomeProduto2;
    private javax.swing.JLabel lblStringNomeProduto3;
    private javax.swing.Box.Filler linha1;
    // End of variables declaration//GEN-END:variables
}
