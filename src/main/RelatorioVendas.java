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
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import model.bean.Cliente;
import model.bean.Forma;
import model.bean.Venda;
import model.dao.ClienteDAO;
import model.dao.VendaDAO;

public class RelatorioVendas extends javax.swing.JFrame {

    public static ArrayList<Cliente> listaClientes = new ArrayList<>();
    ClienteDAO cDao = new ClienteDAO();
    public static Cliente clienteSelecionado;
    private ArrayList<Venda> lista;
    private Double credito = 0.0; private Double debito = 0.0; private Double voucher = 0.0; private Double dinheiro = 0.0; private Double carteira = 0.0; private Double total = 0.0;
    
    public RelatorioVendas() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.formatarTabela();
        lblDataInicial.setText(GerenciadorRelatorios.dMenor);
        lblDataFinal.setText(GerenciadorRelatorios.dMaior);
        this.getInfo();
        this.criarTabela();
        lblCredito.setText(GerenciadorComandas.valorMonetario(credito));
        lblDebito.setText(GerenciadorComandas.valorMonetario(debito));
        lblDinheiro.setText(GerenciadorComandas.valorMonetario(dinheiro));
        lblVoucher.setText(GerenciadorComandas.valorMonetario(voucher));
        lblTotal.setText(GerenciadorComandas.valorMonetario(total));
        lblCarteira.setText(GerenciadorComandas.valorMonetario(carteira));
    }

    public void getInfo(){
        //Variáveis para criação do relatório       
        VendaDAO v = new VendaDAO();
        lista = new ArrayList<>();
        for (Venda ve : v.relatorio(GerenciadorRelatorios.dMaior, GerenciadorRelatorios.dMenor)) {
            lista.add(ve);
            for (Forma f : v.getFormas(ve)) {
                if (f.getFormaPagamento().equals("Débito")) {
                    debito += f.getValor();
                } else if (f.getFormaPagamento().equals("Crédito")) {
                    credito += f.getValor();
                    break;
                } else if (f.getFormaPagamento().equals("Voucher")) {
                    voucher += f.getValor();
                } else if (f.getFormaPagamento().equals("Dinheiro")){
                    dinheiro += f.getValor();
                } else{
                    carteira += f.getValor();
                }
            }
        }
        total += dinheiro;
        total += debito;
        total += credito;
        total += voucher;
        total += carteira;
    }
    
    public void formatarTabela() {
        jtVendas.setRowHeight(23);
        jtVendas.getColumn("No").setCellRenderer(centro);
        jtVendas.getColumn("Valor").setCellRenderer(centro);
        jtVendas.getColumn("Data").setCellRenderer(centro);
        jtVendas.getColumnModel().getColumn(0).setPreferredWidth(70);
        jtVendas.getColumnModel().getColumn(1).setPreferredWidth(200);
        jtVendas.getColumnModel().getColumn(2).setPreferredWidth(150);

        jtVendas.getColumnModel().getColumn(0).setMinWidth(70);
        jtVendas.getColumnModel().getColumn(1).setMinWidth(200);
        jtVendas.getColumnModel().getColumn(2).setMinWidth(150);

        jtVendas.getColumnModel().getColumn(0).setMaxWidth(70);
        jtVendas.getColumnModel().getColumn(1).setMaxWidth(200);
        jtVendas.getColumnModel().getColumn(2).setMaxWidth(150);
    }

    public void criarTabela() {
        int contador = 1;
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtVendas.getModel();
        for (Venda v : lista) {
            dtmBebidas.addRow(
                    new Object[]{
                        contador,
                        v.getData(),
                        "R$ "+GerenciadorComandas.valorMonetario(v.getTotal())}
            );
            contador += 1;
        }
    }
    
    // ----------------------- MÉTODOS PARA CRIAR RELATÓRIOS -----------------------
    public PdfPTable criarCabecalho() throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{1f, 4f, 6f});
        PdfPCell celulaNo = new PdfPCell(new Phrase("No"));
        celulaNo.setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell celulaValor = new PdfPCell(new Phrase("Valor (R$)"));
        celulaValor.setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell celulaData = new PdfPCell(new Phrase("Data"));
        celulaData.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(celulaNo);
        table.addCell(celulaValor);
        table.addCell(celulaData);

        return table;
    }

    public void preencherDados(Document document, PdfPTable table, ArrayList<Venda> lista) throws DocumentException {
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
    
    /*class Renderer extends JLabel{
        JTableHeader header = jtVendas.getTableHeader();
        int maxHeaderHeight;

        public Renderer(JTable table) {
            super();
            maxHeaderHeight = 22; //Tamanho do Header
        }
        //header.getDefaultRenderer();
        ((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER));
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((String) value);
            setHorizontalAlignment(jtVendas.get(column).alinhamento);

            Dimension d = new Dimension(table.getTableHeader().getPreferredSize().width, maxHeaderHeight);
            table.getTableHeader().setPreferredSize(d);
            return this;
        }
    }*/
    
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
        lblString3 = new javax.swing.JLabel();
        lblString4 = new javax.swing.JLabel();
        lblString5 = new javax.swing.JLabel();
        lblString6 = new javax.swing.JLabel();
        lblDinheiro = new javax.swing.JLabel();
        lblDebito = new javax.swing.JLabel();
        lblCredito = new javax.swing.JLabel();
        lblVoucher = new javax.swing.JLabel();
        lblString8 = new javax.swing.JLabel();
        lblCarteira = new javax.swing.JLabel();
        lblString7 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciador de Produtos");
        setMaximumSize(new java.awt.Dimension(870, 535));
        setMinimumSize(new java.awt.Dimension(870, 535));
        setResizable(false);

        btnStringProdutos.setBackground(new java.awt.Color(0, 102, 204));
        btnStringProdutos.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        btnStringProdutos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringProdutos.setText("Relatório de Vendas");

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
                "No", "Data", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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

        lblString3.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblString3.setText("Dinheiro: R$");

        lblString4.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblString4.setText("Débito: R$");

        lblString5.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblString5.setText("Crédito: R$");

        lblString6.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblString6.setText("Voucher: R$");

        lblDinheiro.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblDinheiro.setText("500,52");

        lblDebito.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblDebito.setText("500,52");

        lblCredito.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblCredito.setText("500,52");

        lblVoucher.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblVoucher.setText("500,52");

        lblString8.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblString8.setText("Carteira: R$");

        lblCarteira.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblCarteira.setText("500,52");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblString6)
                    .addComponent(lblString5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblString4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblString3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblString8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCarteira, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDinheiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDebito, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(lblCredito, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(lblVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblString3)
                    .addComponent(lblDinheiro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblString4)
                    .addComponent(lblDebito))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblString5)
                    .addComponent(lblCredito))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblString6)
                    .addComponent(lblVoucher))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblString8)
                    .addComponent(lblCarteira))
                .addContainerGap())
        );

        lblString7.setFont(new java.awt.Font("Century Gothic", 1, 21)); // NOI18N
        lblString7.setText("Total: R$");

        lblTotal.setFont(new java.awt.Font("Century Gothic", 1, 22)); // NOI18N
        lblTotal.setText("2150,00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnLancador)
                .addGap(212, 212, 212)
                .addComponent(btnStringProdutos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(lblString1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDataInicial))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addComponent(lblString2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblDataFinal))
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(lblString7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnStringProdutos))
                    .addComponent(btnLancador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(25, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblString1)
                            .addComponent(lblDataInicial))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblString2)
                            .addComponent(lblDataFinal))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblString7)
                            .addComponent(lblTotal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(471, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLancadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancadorActionPerformed
        new GerenciadorRelatorios().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLancadorActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed

        //Cria a estrutura inicial do relatório de VENDAS       
        Paragraph pTitulo = new Paragraph(new Phrase(20F, "Restaurante Almanah\n", FontFactory.getFont(FontFactory.HELVETICA, 20F)));
        pTitulo.setAlignment(Element.ALIGN_CENTER);

        Paragraph pSubtitulo = new Paragraph(new Phrase(20F, "Relatório de Vendas\n", FontFactory.getFont(FontFactory.HELVETICA, 16F)));
        pSubtitulo.setAlignment(Element.ALIGN_CENTER);

        Paragraph pSubtituloDatas = new Paragraph(new Phrase(20F, "Data Inicial: " + GerenciadorRelatorios.dMenor + "  Data Final: " + GerenciadorRelatorios.dMaior + "\n\n", FontFactory.getFont(FontFactory.HELVETICA, 12F)));
        pSubtituloDatas.setAlignment(Element.ALIGN_CENTER);

        Paragraph pData = new Paragraph(new Phrase(20F, "Data de Emissão: " + GerenciadorComandas.getDataAtualComHoraFormatoBr() + "\n\n", FontFactory.getFont(FontFactory.HELVETICA, 11F)));
        pData.setAlignment(Element.ALIGN_RIGHT);
        Document documento = new Document();
        try {
            PdfWriter pdf = PdfWriter.getInstance(documento, new FileOutputStream("C:\\Projetos Netbeans\\AlmanahSystem\\relatorios\\vendas\\Vendas.pdf"));

            //Adiciona ao documento as estruturas de cabeçalho
            documento.open();
            documento.add(pTitulo);
            documento.add(pSubtitulo);
            documento.add(pSubtituloDatas);
            documento.add(pData);

            //Cria a tabela e adiciona seu conteúdo
            PdfPTable table = this.criarCabecalho();

            this.preencherDados(documento, table, lista);

            //Cria o rodapé, com totais das vendas                   
            documento.add(new Paragraph("\n\n"));
            Paragraph pDinheiro = new Paragraph(new Phrase(20F, "Dinheiro: R$ " + GerenciadorComandas.valorMonetario(dinheiro) + "\n", FontFactory.getFont(FontFactory.HELVETICA, 12F)));
            pDinheiro.setAlignment(Element.ALIGN_RIGHT);
            Paragraph pDebito = new Paragraph(new Phrase(20F, "Débito: R$ " + GerenciadorComandas.valorMonetario(debito) + "\n", FontFactory.getFont(FontFactory.HELVETICA, 12F)));
            pDebito.setAlignment(Element.ALIGN_RIGHT);
            Paragraph pCredito = new Paragraph(new Phrase(20F, "Crédito: R$ " + GerenciadorComandas.valorMonetario(credito) + "\n", FontFactory.getFont(FontFactory.HELVETICA, 12F)));
            pCredito.setAlignment(Element.ALIGN_RIGHT);
            Paragraph pVoucher = new Paragraph(new Phrase(20F, "Voucher: R$ " + GerenciadorComandas.valorMonetario(voucher) + "\n", FontFactory.getFont(FontFactory.HELVETICA, 12F)));
            pVoucher.setAlignment(Element.ALIGN_RIGHT);
            Paragraph pCarteira = new Paragraph(new Phrase(20F, "Carteira: R$ " + GerenciadorComandas.valorMonetario(carteira) + "\n", FontFactory.getFont(FontFactory.HELVETICA, 12F)));
            pCarteira.setAlignment(Element.ALIGN_RIGHT);
            Paragraph pLinha = new Paragraph(new Phrase(20F, "______________\n\n", FontFactory.getFont(FontFactory.HELVETICA, 12F)));
            pLinha.setAlignment(Element.ALIGN_RIGHT);            
            Paragraph pTotal = new Paragraph(new Phrase(20F, "Total:  R$ " + GerenciadorComandas.valorMonetario(total), FontFactory.getFont(FontFactory.HELVETICA, 14F)));
            pTotal.setAlignment(Element.ALIGN_RIGHT);

            documento.add(pDinheiro);
            documento.add(pDebito);
            documento.add(pCredito);
            documento.add(pVoucher);
            documento.add(pLinha);
            documento.add(pTotal);
        } catch (FileNotFoundException | DocumentException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            documento.close();
        }
        try {
            Desktop.getDesktop().open(new File("C:\\Projetos Netbeans\\AlmanahSystem\\relatorios\\vendas\\Vendas.pdf"));
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
            java.util.logging.Logger.getLogger(RelatorioVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RelatorioVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RelatorioVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RelatorioVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RelatorioVendas().setVisible(true);
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
    private javax.swing.JLabel lblCarteira;
    private javax.swing.JLabel lblCredito;
    private javax.swing.JLabel lblDataFinal;
    private javax.swing.JLabel lblDataInicial;
    private javax.swing.JLabel lblDebito;
    private javax.swing.JLabel lblDinheiro;
    private javax.swing.JLabel lblString1;
    private javax.swing.JLabel lblString2;
    private javax.swing.JLabel lblString3;
    private javax.swing.JLabel lblString4;
    private javax.swing.JLabel lblString5;
    private javax.swing.JLabel lblString6;
    private javax.swing.JLabel lblString7;
    private javax.swing.JLabel lblString8;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblVoucher;
    private javax.swing.Box.Filler linha1;
    // End of variables declaration//GEN-END:variables
}
