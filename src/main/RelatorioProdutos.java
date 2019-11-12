package main;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import manual.Manual;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.bean.Cliente;
import model.dao.ClienteDAO;
import model.dao.ProdutoDAO;
import relatorio.RelatorioProduto;

public class RelatorioProdutos extends javax.swing.JFrame {

    public static ArrayList<Cliente> listaClientes = new ArrayList<>();
    ClienteDAO cDao = new ClienteDAO();
    public static Cliente clienteSelecionado;
    private ArrayList<RelatorioProduto> lista;
    
    public RelatorioProdutos() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.formatarTabela();
        this.criarComboBox();
        lblDataInicial.setText(GerenciadorRelatorios.dMenor);
        lblDataFinal.setText(GerenciadorRelatorios.dMaior);
        //this.getInfo();       
    }

    public void getInfo(){
        try{
            //Variáveis para criação do relatório       
            ProdutoDAO pDao = new ProdutoDAO();
            lista = new ArrayList<>();
            int ordem = cbOrdem.getSelectedIndex();
            for (RelatorioProduto p : pDao.relatorio(GerenciadorRelatorios.dMaior, GerenciadorRelatorios.dMenor, Integer.parseInt((String)cbLimite.getSelectedItem()),ordem)){
                lista.add(p);
            }
            this.criarTabela();
        }catch(java.lang.NumberFormatException ex){}
        
    }
    
    public void formatarTabela() {       
        jtVendas.setRowHeight(25);
        jtVendas.getColumn("No").setCellRenderer(centro);
        jtVendas.getColumn("Qtd de Vendas").setCellRenderer(centro);
        jtVendas.getColumn("Preço (R$)").setCellRenderer(direita);
        jtVendas.getColumn("Total (R$)").setCellRenderer(direita);
        jtVendas.getColumn("Nome").setCellRenderer(centro);
        
        jtVendas.getColumnModel().getColumn(0).setPreferredWidth(70);
        jtVendas.getColumnModel().getColumn(1).setPreferredWidth(250);
        jtVendas.getColumnModel().getColumn(2).setPreferredWidth(120);
        jtVendas.getColumnModel().getColumn(3).setPreferredWidth(120);
        jtVendas.getColumnModel().getColumn(4).setPreferredWidth(120);

        jtVendas.getColumnModel().getColumn(0).setMinWidth(70);
        jtVendas.getColumnModel().getColumn(1).setMinWidth(250);
        jtVendas.getColumnModel().getColumn(2).setMinWidth(120);
        jtVendas.getColumnModel().getColumn(3).setMinWidth(120);
        jtVendas.getColumnModel().getColumn(4).setMinWidth(120);

        jtVendas.getColumnModel().getColumn(0).setMaxWidth(70);
        jtVendas.getColumnModel().getColumn(1).setMaxWidth(250);
        jtVendas.getColumnModel().getColumn(2).setMaxWidth(120);
        jtVendas.getColumnModel().getColumn(3).setMaxWidth(120);
        jtVendas.getColumnModel().getColumn(4).setMaxWidth(120);
    }

    public void criarTabela() {
        this.limparTabela();
        int contador = 1;
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtVendas.getModel();
        for (RelatorioProduto p : lista) {
            dtmBebidas.addRow(
                    new Object[]{
                        contador,
                        p.getNome(),
                        GerenciadorComandas.valorMonetario(p.getPreco())+"  ",
                        p.getQtdVendas(),
                        GerenciadorComandas.valorMonetario(p.getTotal())+"  "}
            );
            contador += 1;
        }
    }
    
    public void limparTabela(){
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtVendas.getModel();
        int i = dtmBebidas.getRowCount();
        
        for (int j = 0; j < i; j++){
            dtmBebidas.removeRow(0);
        }       
    }
    
    public void criarComboBox(){
        cbLimite.removeAllItems();
        cbLimite.addItem("10");
        cbLimite.addItem("20");
        cbLimite.addItem("30");
        cbLimite.addItem("40");
        cbLimite.addItem("50");
        cbLimite.addItem("60");
        cbLimite.addItem("70");
        cbLimite.addItem("80");
        cbLimite.addItem("90");
        cbLimite.addItem("100");
        
        cbOrdem.removeAllItems();
        cbOrdem.addItem("Decrescente");
        cbOrdem.addItem("Crescente");
    }
    
    // ----------------------- MÉTODOS PARA CRIAR RELATÓRIOS -----------------------
    public PdfPTable criarCabecalho() throws DocumentException {      
        PdfPTable table = new PdfPTable(new float[]{5f,10f});
        table.setWidthPercentage(100.0f);
        PdfPCell cabecalho = new PdfPCell();
        cabecalho.setBackgroundColor(BaseColor.LIGHT_GRAY);
        Paragraph espaco = new Paragraph(new Phrase("\n", FontFactory.getFont(FontFactory.HELVETICA, 20F)));

        // Colocar imagem na tabela
        Image jpg;
        try {
            jpg = Image.getInstance("C:\\Projetos Netbeans\\AlmanahSystem\\images\\logo.png");
            jpg.scaleAbsoluteWidth(100);
            jpg.scaleAbsoluteHeight(80);
            jpg.setAlignment(Element.ALIGN_CENTER);

            PdfPCell imagem = new PdfPCell();
            // cell.setBorder(PdfPCell.NO_BORDER);;
            //imagem.addElement(espaco);
            imagem.addElement(jpg);
            imagem.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(imagem);
        } catch (BadElementException ex) {System.err.println("Erro: "+ex);
        } catch (IOException ex)         {System.err.println("Erro: "+ex);}
        
        // Célula do TÍTULO ("Restaurante Almanah")
        Paragraph pTitulo = new Paragraph(new Phrase(20F, "Restaurante Almanah\n", FontFactory.getFont(FontFactory.HELVETICA, 20F, Font.BOLD)));
        pTitulo.setAlignment(Element.ALIGN_CENTER);
        pTitulo.setSpacingBefore(20);
        pTitulo.setSpacingAfter(20);       
        cabecalho.addElement(pTitulo);
        
        // Célula do SUBTÍTULO ("Relatório de ...")
        Paragraph pSubtitulo = new Paragraph(new Phrase(20F, "Relatório de Produtos\n", FontFactory.getFont(FontFactory.HELVETICA, 16F)));
        pSubtitulo.setAlignment(Element.ALIGN_CENTER);
        pTitulo.setSpacingBefore(20);
        pTitulo.setSpacingAfter(20);       
        cabecalho.addElement(pSubtitulo);

        // Célula do DATAS ("Datas inicial e final")
        Paragraph pSubtituloDatas = new Paragraph(new Phrase(20F, "Data Inicial: " + GerenciadorRelatorios.dMenor + "  Data Final: " + GerenciadorRelatorios.dMaior + "\n\n", FontFactory.getFont(FontFactory.HELVETICA, 12F)));
        pSubtituloDatas.setAlignment(Element.ALIGN_CENTER);
        pTitulo.setSpacingBefore(20);
        pTitulo.setSpacingAfter(20);       
        cabecalho.addElement(pSubtituloDatas);
        
        table.addCell(cabecalho);
        return table;
    }
    
    public PdfPTable criarTabelaPdf() throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{1f, 6f, 3f, 4f, 3f});
        table.setWidthPercentage(100.0f);
        
        // Célula do No
        Paragraph n = new Paragraph(new Phrase(15F, "\nNo", FontFactory.getFont(FontFactory.HELVETICA, 14F)));
        n.setAlignment(Element.ALIGN_CENTER);
        n.setSpacingBefore(20);
        n.setSpacingAfter(20);
        PdfPCell celulaNo = new PdfPCell();
        celulaNo.addElement(n);
        celulaNo.setBackgroundColor(BaseColor.LIGHT_GRAY);
        
        // Célula do Nome
        Paragraph no = new Paragraph(new Phrase(15F, "\nNome", FontFactory.getFont(FontFactory.HELVETICA, 14F)));
        no.setAlignment(Element.ALIGN_CENTER);
        no.setSpacingBefore(20);
        no.setSpacingAfter(20);
        PdfPCell celulaNome = new PdfPCell();
        celulaNome.addElement(no);
        celulaNome.setBackgroundColor(BaseColor.LIGHT_GRAY);
        
        // Célula do Preço
        Paragraph p = new Paragraph(new Phrase(15F, "\nPreço (R$)", FontFactory.getFont(FontFactory.HELVETICA, 14F)));
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingBefore(20);
        p.setSpacingAfter(20);
        PdfPCell celulaPreco = new PdfPCell();
        celulaPreco.addElement(p);
        celulaPreco.setBackgroundColor(BaseColor.LIGHT_GRAY);

        // Célula do Preço
        Paragraph q = new Paragraph(new Phrase(15F, "\nQtd de Vendas", FontFactory.getFont(FontFactory.HELVETICA, 14F)));
        q.setAlignment(Element.ALIGN_CENTER);
        q.setSpacingBefore(20);
        q.setSpacingAfter(20);
        PdfPCell celulaQtd = new PdfPCell();
        celulaQtd.addElement(q);
        celulaQtd.setBackgroundColor(BaseColor.LIGHT_GRAY);
        
        // Célula do Total
        Paragraph t = new Paragraph(new Phrase(15F, "\nTotal (R$)", FontFactory.getFont(FontFactory.HELVETICA, 14F)));
        t.setAlignment(Element.ALIGN_CENTER);
        t.setSpacingBefore(20);
        t.setSpacingAfter(20);
        PdfPCell celulaTotal = new PdfPCell();
        celulaTotal.addElement(t);
        celulaTotal.setBackgroundColor(BaseColor.LIGHT_GRAY);

        table.addCell(celulaNo);
        table.addCell(celulaNome);
        table.addCell(celulaPreco);
        table.addCell(celulaQtd);
        table.addCell(celulaTotal);
        
        return table;
    }

    public void preencherDados(Document document, PdfPTable table, ArrayList<RelatorioProduto> lista) throws DocumentException {
        Integer contador = 1;
        String c = "";
        String qtd = "";
        if (document.isOpen()) {
            for (RelatorioProduto p : lista) {
                c = contador.toString();
                qtd = Integer.toString(p.getQtdVendas());
                PdfPCell celula1 = new PdfPCell(new Phrase(c));
                PdfPCell celula2 = new PdfPCell(new Phrase(p.getNome()));
                PdfPCell celula3 = new PdfPCell(new Phrase(GerenciadorComandas.valorMonetario(p.getPreco())));
                PdfPCell celula4 = new PdfPCell(new Phrase(qtd));
                PdfPCell celula5 = new PdfPCell(new Phrase(GerenciadorComandas.valorMonetario(p.getTotal())));
                celula1.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula2.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula3.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula4.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula5.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                table.addCell(celula1);
                table.addCell(celula2);
                table.addCell(celula3);
                table.addCell(celula4);
                table.addCell(celula5);
                contador += 1;
            }
            document.add(table);
        }
    }
    
    //MÉTODOS PARA ARRUMAR CÉLULAS DA TABELA
    DefaultTableCellRenderer centro = new DefaultTableCellRenderer() {
        public void setValue(Object value) {
            setHorizontalAlignment(JLabel.CENTER);
            super.setValue(value);
        }
    };

    DefaultTableCellRenderer direita = new DefaultTableCellRenderer() {
        public void setValue(Object value) {
            setHorizontalAlignment(JLabel.RIGHT);
            super.setValue(value);
        }
    };
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStringProdutos = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnLancador = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtVendas = new javax.swing.JTable();
        btnExportar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblString1 = new javax.swing.JLabel();
        lblDataInicial = new javax.swing.JLabel();
        lblString2 = new javax.swing.JLabel();
        lblDataFinal = new javax.swing.JLabel();
        linha2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        jPanel2 = new javax.swing.JPanel();
        lblString4 = new javax.swing.JLabel();
        cbLimite = new javax.swing.JComboBox<>();
        lblString3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        cbOrdem = new javax.swing.JComboBox<>();
        lblManual2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Relatório de Produtos");
        setMaximumSize(new java.awt.Dimension(743, 625));
        setMinimumSize(new java.awt.Dimension(743, 625));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnStringProdutos.setBackground(new java.awt.Color(0, 102, 204));
        btnStringProdutos.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        btnStringProdutos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringProdutos.setText("Relatório de Produtos");
        getContentPane().add(btnStringProdutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, -1, -1));

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);
        getContentPane().add(linha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 67, 743, -1));

        btnLancador.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        btnLancador.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\voltar (1).png")); // NOI18N
        btnLancador.setText("Voltar");
        btnLancador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLancadorActionPerformed(evt);
            }
        });
        getContentPane().add(btnLancador, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 25, -1, 36));

        jtVendas.setBorder(new javax.swing.border.MatteBorder(null));
        jtVendas.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jtVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Nome", "Preço (R$)", "Qtd de Vendas", "Total (R$)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtVendas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtVendas);
        if (jtVendas.getColumnModel().getColumnCount() > 0) {
            jtVendas.getColumnModel().getColumn(0).setResizable(false);
            jtVendas.getColumnModel().getColumn(1).setResizable(false);
            jtVendas.getColumnModel().getColumn(2).setResizable(false);
            jtVendas.getColumnModel().getColumn(3).setResizable(false);
            jtVendas.getColumnModel().getColumn(4).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 678, 381));

        btnExportar.setBackground(new java.awt.Color(204, 204, 0));
        btnExportar.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        btnExportar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\pdf png 2.png")); // NOI18N
        btnExportar.setText(" Exportar");
        btnExportar.setBorder(new javax.swing.border.MatteBorder(null));
        btnExportar.setBorderPainted(false);
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });
        getContentPane().add(btnExportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, 182, 59));

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        lblString1.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblString1.setText("Data Inicial:");

        lblDataInicial.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblDataInicial.setText("04/06/1999");

        lblString2.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblString2.setText("Data Final:");

        lblDataFinal.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblDataFinal.setText("04/06/1999");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblString1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDataInicial)
                .addGap(65, 65, 65)
                .addComponent(lblString2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDataFinal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblString1)
                    .addComponent(lblDataInicial)
                    .addComponent(lblString2)
                    .addComponent(lblDataFinal))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 550, -1, -1));

        linha2.setBackground(new java.awt.Color(0, 0, 0));
        linha2.setOpaque(true);
        getContentPane().add(linha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 11, 743, -1));

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        lblString4.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblString4.setText("produtos");

        cbLimite.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cbLimite.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbLimite.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbLimiteItemStateChanged(evt);
            }
        });

        lblString3.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblString3.setText("Limite de");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblString3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblString4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblString4)
                    .addComponent(cbLimite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblString3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 46));

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        cbOrdem.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cbOrdem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Descrescente", " " }));
        cbOrdem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbOrdemItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbOrdem, 0, 148, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbOrdem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, 170, 46));

        lblManual2.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblManual2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblManual2.setText("?");
        lblManual2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblManual2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblManual2MouseClicked(evt);
            }
        });
        getContentPane().add(lblManual2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 590, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLancadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancadorActionPerformed
        new GerenciadorRelatorios().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLancadorActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed

        //Cria a estrutura inicial do relatório de VENDAS       
        Paragraph pData = new Paragraph(new Phrase(20F, "Data de Emissão: " + GerenciadorComandas.getDataAtualComHoraFormatoBr(), FontFactory.getFont(FontFactory.HELVETICA, 11F)));
        pData.setAlignment(Element.ALIGN_RIGHT);
        pData.setSpacingBefore(2);
        pData.setSpacingAfter(2);
        Document documento = new Document();
        try {
            PdfWriter pdf = PdfWriter.getInstance(documento, new FileOutputStream("C:\\Projetos Netbeans\\AlmanahSystem\\relatorios\\produtos\\Produtos.pdf"));

            //Adiciona ao documento as estruturas de cabeçalho
            documento.open();
            documento.add(criarCabecalho());
            documento.add(pData);

            //Cria a tabela e adiciona seu conteúdo
            PdfPTable table = this.criarTabelaPdf();
            this.preencherDados(documento, table, lista);

        } catch (FileNotFoundException | DocumentException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            documento.close();
        }
        
        try {
            Desktop.getDesktop().open(new File("C:\\Projetos Netbeans\\AlmanahSystem\\relatorios\\produtos\\Produtos.pdf"));
        } catch (IOException ex) {
            System.out.println("Erro: " + ex);
        }

    }//GEN-LAST:event_btnExportarActionPerformed

    private void cbLimiteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbLimiteItemStateChanged
        this.getInfo();
    }//GEN-LAST:event_cbLimiteItemStateChanged

    private void cbOrdemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbOrdemItemStateChanged
        this.getInfo();
    }//GEN-LAST:event_cbOrdemItemStateChanged

    private void lblManual2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblManual2MouseClicked
        Manual.abrirManual("login.html");
    }//GEN-LAST:event_lblManual2MouseClicked

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
            java.util.logging.Logger.getLogger(RelatorioProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RelatorioProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RelatorioProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RelatorioProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RelatorioProdutos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnLancador;
    private javax.swing.JLabel btnStringProdutos;
    private static javax.swing.JComboBox<String> cbLimite;
    private static javax.swing.JComboBox<String> cbOrdem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtVendas;
    private javax.swing.JLabel lblDataFinal;
    private javax.swing.JLabel lblDataInicial;
    private javax.swing.JLabel lblManual2;
    private javax.swing.JLabel lblString1;
    private javax.swing.JLabel lblString2;
    private javax.swing.JLabel lblString3;
    private javax.swing.JLabel lblString4;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    // End of variables declaration//GEN-END:variables
}
