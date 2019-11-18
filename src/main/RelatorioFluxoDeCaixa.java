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
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import model.dao.RelatorioFluxoDAO;
import relatorio.RelatorioFluxo;

public class RelatorioFluxoDeCaixa extends javax.swing.JFrame {
    private ArrayList<RelatorioFluxo> listaFluxos;
    private Double credito = 0.0; private Double debito = 0.0; private Double total = 0.0;
    
    public RelatorioFluxoDeCaixa() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.formatarTabela();
        lblDataInicial.setText(GerenciadorRelatorios.dMenor);
        lblDataFinal.setText(GerenciadorRelatorios.dMaior);
        this.getInfo();
        this.ordenarDatas();
        this.criarTabela();
        lblCredito.setText(GerenciadorComandas.valorMonetario(credito));
        lblDebito.setText(GerenciadorComandas.valorMonetario(debito));
        if (total < 0.0){
            lblSaldo.setForeground(Color.red);
        }else{
            lblSaldo.setForeground(new java.awt.Color(51, 204, 0));
        }
        lblSaldo.setText(GerenciadorComandas.valorMonetario(total));
        /*
        this.criarTabela();
        lblCredito.setText(GerenciadorComandas.valorMonetario(credito));
        lblDebito.setText(GerenciadorComandas.valorMonetario(debito));
        lblDinheiro.setText(GerenciadorComandas.valorMonetario(dinheiro));
        lblVoucher.setText(GerenciadorComandas.valorMonetario(voucher));
        lblTotal.setText(GerenciadorComandas.valorMonetario(total));
        lblCarteira.setText(GerenciadorComandas.valorMonetario(carteira));*/
    }

    public void getInfo(){
        //Variáveis para criação do relatório  
        RelatorioFluxoDAO fDao = new RelatorioFluxoDAO();
        listaFluxos = new ArrayList<>();
        for (RelatorioFluxo r : fDao.relatorioFormas(GerenciadorRelatorios.dMaior, GerenciadorRelatorios.dMenor)) {
            listaFluxos.add(r);
            credito += r.getValor();
        }
        for (RelatorioFluxo r : fDao.relatorioFormasCarteira(GerenciadorRelatorios.dMaior, GerenciadorRelatorios.dMenor)) {
            listaFluxos.add(r);
            credito += r.getValor();
        }
        for (RelatorioFluxo r : fDao.relatorioDespesas(GerenciadorRelatorios.dMaior, GerenciadorRelatorios.dMenor)){
            listaFluxos.add(r);
            debito += r.getValor();
        }
        total = credito - debito;
    }
    
    public void ordenarDatas(){
        try{
            // Instancia os objetos necessários
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy   HH:mm:ss");
            Date data1;
            Date data2;

            // Rotina de ordenação da lista genérica a partir das datas
            int tamanho = listaFluxos.size();
            for (int i = 0; i < tamanho-1; i++){
                for (int j = i+1; j < tamanho; j++){
                    data1 = formatador.parse(listaFluxos.get(i).getData());
                    data2 = formatador.parse(listaFluxos.get(j).getData());
                    int resultado = data1.compareTo(data2);
                    if (resultado == 1){
                        RelatorioFluxo x = listaFluxos.get(i);
                        listaFluxos.set(i, listaFluxos.get(j));
                        listaFluxos.set(j, x);
                    }
                }
            }       
        }catch(ParseException ex){}
        
    }
    
    public void formatarTabela() {
        jtVendas.setRowHeight(23);
        jtVendas.getColumnModel().getColumn(0).setPreferredWidth(80); //TIPO
        jtVendas.getColumnModel().getColumn(1).setPreferredWidth(105); //VALOR
        jtVendas.getColumnModel().getColumn(2).setPreferredWidth(180); //DATA
        jtVendas.getColumnModel().getColumn(3).setPreferredWidth(500); //DESCRIÇÃO

        jtVendas.getColumnModel().getColumn(0).setMinWidth(80);
        jtVendas.getColumnModel().getColumn(1).setMinWidth(105);
        jtVendas.getColumnModel().getColumn(2).setMinWidth(180);
        jtVendas.getColumnModel().getColumn(3).setMinWidth(500);

        jtVendas.getColumnModel().getColumn(0).setMaxWidth(80);
        jtVendas.getColumnModel().getColumn(1).setMaxWidth(105);
        jtVendas.getColumnModel().getColumn(2).setMaxWidth(180);
        jtVendas.getColumnModel().getColumn(3).setMaxWidth(500);
    }

    public void criarTabela() {
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtVendas.getModel();
        for (RelatorioFluxo f : listaFluxos) {
            dtmBebidas.addRow(
                    new Object[]{
                        f.getTipo(),
                        "R$  "+GerenciadorComandas.valorMonetario(f.getValor()),
                        f.getData(),
                        f.getDescricao()}
            );
        }
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
        Paragraph pSubtitulo = new Paragraph(new Phrase(20F, "Relatório de Fluxo de Caixa\n", FontFactory.getFont(FontFactory.HELVETICA, 16F)));
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
        PdfPTable table = new PdfPTable(new float[]{2.5f, 4f, 3.8f, 10f});
        table.setWidthPercentage(100.0f);
        
        // Célula do Tipo
        Paragraph t = new Paragraph(new Phrase(15F, "  \nTipo", FontFactory.getFont(FontFactory.HELVETICA, 14F)));
        t.setAlignment(Element.ALIGN_CENTER);
        t.setSpacingBefore(20);
        t.setSpacingAfter(20);
        PdfPCell celulaTipo = new PdfPCell();
        celulaTipo.addElement(t);
        celulaTipo.setBackgroundColor(BaseColor.LIGHT_GRAY);
        
        // Célula do Valor
        Paragraph v = new Paragraph(new Phrase(15F, "  \nValor", FontFactory.getFont(FontFactory.HELVETICA, 14F)));
        v.setAlignment(Element.ALIGN_CENTER);
        v.setSpacingBefore(20);
        v.setSpacingAfter(20);
        PdfPCell celulaValor = new PdfPCell();
        celulaValor.addElement(v);
        celulaValor.setBackgroundColor(BaseColor.LIGHT_GRAY);
        
        // Célula da Data
        Paragraph d = new Paragraph(new Phrase(15F, "  \nData", FontFactory.getFont(FontFactory.HELVETICA, 14F)));
        d.setAlignment(Element.ALIGN_CENTER);
        d.setSpacingBefore(20);
        d.setSpacingAfter(20);
        PdfPCell celulaData = new PdfPCell();
        celulaData.addElement(d);
        celulaData.setBackgroundColor(BaseColor.LIGHT_GRAY);
        
        // Célula da Descrição
        Paragraph de = new Paragraph(new Phrase(15F, "  \nDescrição", FontFactory.getFont(FontFactory.HELVETICA, 14F)));
        de.setAlignment(Element.ALIGN_CENTER);
        de.setSpacingBefore(20);
        de.setSpacingAfter(20);
        PdfPCell celulaDescricao = new PdfPCell();
        celulaDescricao.addElement(de);
        celulaDescricao.setBackgroundColor(BaseColor.LIGHT_GRAY);
        
        table.addCell(celulaTipo);
        table.addCell(celulaValor);
        table.addCell(celulaData);
        table.addCell(celulaDescricao);

        return table;
    }

    public void preencherDados(Document document, PdfPTable table, ArrayList<RelatorioFluxo> lista) throws DocumentException {
        if (document.isOpen()) {
            for (RelatorioFluxo r : lista) {
                PdfPCell celula1 = new PdfPCell(new Phrase(r.getTipo()));
                PdfPCell celula2 = new PdfPCell(new Phrase("R$ "+GerenciadorComandas.valorMonetario(r.getValor())));
                PdfPCell celula3 = new PdfPCell(new Phrase(r.getData()));
                PdfPCell celula4 = new PdfPCell(new Phrase(r.getDescricao()));
                
                celula1.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula2.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula3.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula4.setHorizontalAlignment(Element.ALIGN_LEFT);
                
                table.addCell(celula1);
                table.addCell(celula2);
                table.addCell(celula3);
                table.addCell(celula4);
            }
            document.add(table);
        }
    }
    
    // --------------------- MÉTODO PARA CRIAR GRÁFICO NO RELATÓRIO --------------------------




    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStringProdutos = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnLancador = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtVendas = new javax.swing.JTable();
        btnExportar = new javax.swing.JButton();
        lblString1 = new javax.swing.JLabel();
        lblString2 = new javax.swing.JLabel();
        lblDataInicial = new javax.swing.JLabel();
        lblDataFinal = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblString5 = new javax.swing.JLabel();
        lblCredito = new javax.swing.JLabel();
        lblString4 = new javax.swing.JLabel();
        lblDebito = new javax.swing.JLabel();
        lblString7 = new javax.swing.JLabel();
        lblSaldo = new javax.swing.JLabel();
        linha2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Relatório de Fluxo de Caixa");
        setMaximumSize(new java.awt.Dimension(1150, 600));
        setMinimumSize(new java.awt.Dimension(1150, 600));
        setResizable(false);

        btnStringProdutos.setBackground(new java.awt.Color(0, 102, 204));
        btnStringProdutos.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        btnStringProdutos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringProdutos.setText("Relatório de Fluxo de Caixa");

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        btnLancador.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        btnLancador.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\voltar (1).png")); // NOI18N
        btnLancador.setText("Voltar");
        btnLancador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLancadorActionPerformed(evt);
            }
        });

        jtVendas.setBorder(new javax.swing.border.MatteBorder(null));
        jtVendas.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jtVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo", "Valor", "Data", "Descrição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
        }

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

        lblString1.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblString1.setText("Data Inicial:");

        lblString2.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblString2.setText("Data Final:");

        lblDataInicial.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblDataInicial.setText("04/06/1999");

        lblDataFinal.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblDataFinal.setText("04/06/1999");

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        lblString5.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblString5.setForeground(new java.awt.Color(51, 204, 0));
        lblString5.setText("Total Crédito: R$");

        lblCredito.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblCredito.setForeground(new java.awt.Color(51, 204, 0));
        lblCredito.setText("500,52");

        lblString4.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblString4.setForeground(new java.awt.Color(255, 0, 0));
        lblString4.setText("Total Débito: R$");

        lblDebito.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblDebito.setForeground(new java.awt.Color(255, 0, 0));
        lblDebito.setText("500,52");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblString4)
                    .addComponent(lblString5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDebito, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(lblCredito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblString5)
                    .addComponent(lblCredito))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblString4)
                    .addComponent(lblDebito))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblString7.setFont(new java.awt.Font("Century Gothic", 1, 21)); // NOI18N
        lblString7.setText("Balanço: R$");

        lblSaldo.setFont(new java.awt.Font("Century Gothic", 1, 26)); // NOI18N
        lblSaldo.setText("2150,00");

        linha2.setBackground(new java.awt.Color(0, 0, 0));
        linha2.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLancador)
                        .addGap(284, 284, 284)
                        .addComponent(btnStringProdutos)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(lblString1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDataInicial))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(47, 47, 47)
                                    .addComponent(lblString2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblDataFinal))
                                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblString7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStringProdutos)
                    .addComponent(btnLancador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblString1)
                            .addComponent(lblDataInicial))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblString2)
                            .addComponent(lblDataFinal))
                        .addGap(84, 84, 84)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblString7)
                            .addComponent(lblSaldo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addGap(22, 22, 22))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(586, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLancadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancadorActionPerformed
        new GerenciadorRelatorios().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLancadorActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed

        Paragraph pData = new Paragraph(new Phrase(20F, "Data de Emissão: " + GerenciadorComandas.getDataAtualComHoraFormatoBr(), FontFactory.getFont(FontFactory.HELVETICA, 11F)));
        pData.setAlignment(Element.ALIGN_RIGHT);
        pData.setSpacingBefore(2);
        pData.setSpacingAfter(2);
        Document documento = new Document();
        try {
            PdfWriter pdf = PdfWriter.getInstance(documento, new FileOutputStream("C:\\Projetos Netbeans\\AlmanahSystem\\relatorios\\fluxo de caixa\\Fluxo De Caixa.pdf"));

            //Adiciona ao documento as estruturas de cabeçalho           
            documento.open();
            documento.add(criarCabecalho());
            documento.add(pData);

            //Cria a tabela e adiciona seu conteúdo
            PdfPTable table = this.criarTabelaPdf();
            this.preencherDados(documento, table, listaFluxos);

            //Cria o rodapé, com totais das vendas                   
            documento.add(new Paragraph("\n\n"));
            Paragraph pDebito = new Paragraph(new Phrase(20F, "Total Débito: R$ " + GerenciadorComandas.valorMonetario(debito) + "\n", FontFactory.getFont(FontFactory.HELVETICA, 12F)));
            pDebito.setAlignment(Element.ALIGN_RIGHT);
            Paragraph pCredito = new Paragraph(new Phrase(20F, "Total Crédito: R$ " + GerenciadorComandas.valorMonetario(credito) + "\n", FontFactory.getFont(FontFactory.HELVETICA, 12F)));
            pCredito.setAlignment(Element.ALIGN_RIGHT);
            Paragraph pLinha = new Paragraph(new Phrase(20F, "____________________\n\n", FontFactory.getFont(FontFactory.HELVETICA, 12F)));
            pLinha.setAlignment(Element.ALIGN_RIGHT);            
            Paragraph pTotal = new Paragraph(new Phrase(20F, "Balanço:  R$ " + GerenciadorComandas.valorMonetario(total), FontFactory.getFont(FontFactory.HELVETICA, 14F,Font.BOLD)));
            pTotal.setAlignment(Element.ALIGN_RIGHT);
            
            documento.add(pCredito);
            documento.add(pDebito);            
            documento.add(pLinha);
            documento.add(pTotal);
        } catch (FileNotFoundException | DocumentException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            documento.close();
        }
        try {
            Desktop.getDesktop().open(new File("C:\\Projetos Netbeans\\AlmanahSystem\\relatorios\\fluxo de caixa\\Fluxo De Caixa.pdf"));
        } catch (IOException ex) {
            System.out.println("Erro: " + ex);
        }

    }//GEN-LAST:event_btnExportarActionPerformed

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
            java.util.logging.Logger.getLogger(RelatorioFluxoDeCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RelatorioFluxoDeCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RelatorioFluxoDeCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RelatorioFluxoDeCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RelatorioFluxoDeCaixa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnLancador;
    private javax.swing.JLabel btnStringProdutos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtVendas;
    private javax.swing.JLabel lblCredito;
    private javax.swing.JLabel lblDataFinal;
    private javax.swing.JLabel lblDataInicial;
    private javax.swing.JLabel lblDebito;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JLabel lblString1;
    private javax.swing.JLabel lblString2;
    private javax.swing.JLabel lblString4;
    private javax.swing.JLabel lblString5;
    private javax.swing.JLabel lblString7;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    // End of variables declaration//GEN-END:variables

}
