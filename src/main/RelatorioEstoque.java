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
import model.bean.Categoria;
import model.bean.Produto;
import model.dao.CategoriaDAO;
import model.dao.ProdutoDAO;

public class RelatorioEstoque extends javax.swing.JFrame {
    private ArrayList<Produto> lista = new ArrayList<>();
    
    public RelatorioEstoque() {
        initComponents();
        this.criarComboBox();
        this.setLocationRelativeTo(null);
        this.formatarTabela();
        //this.getInfo();       
    }

    public void getInfo(){
        try{
            //Variáveis para criação do relatório       
            ProdutoDAO pDao = new ProdutoDAO();

            int ascDesc = cbOrdem.getSelectedIndex();
            int ordem = cbOrdemCategorias.getSelectedIndex();
            String exibir = (String)cbExibirSomente.getSelectedItem();
            lista.removeAll(lista);
            for (Produto p : pDao.relatorioEstoque(exibir, ascDesc, ordem)){
                lista.add(p);
            }
            this.criarTabela();
        }catch(java.lang.NumberFormatException ex){}
        
    }
    
    public void formatarTabela() {       
        jtVendas.setRowHeight(25);
        jtVendas.getColumn("No").setCellRenderer(centro);
        jtVendas.getColumn("Qtd em Estoque").setCellRenderer(direita);
        jtVendas.getColumn("Categoria").setCellRenderer(centro);
        
        jtVendas.getColumnModel().getColumn(0).setPreferredWidth(70);
        jtVendas.getColumnModel().getColumn(1).setPreferredWidth(340);
        jtVendas.getColumnModel().getColumn(2).setPreferredWidth(100);
        jtVendas.getColumnModel().getColumn(3).setPreferredWidth(100);
        jtVendas.getColumnModel().getColumn(4).setPreferredWidth(200);

        jtVendas.getColumnModel().getColumn(0).setMinWidth(70);
        jtVendas.getColumnModel().getColumn(1).setMinWidth(340);
        jtVendas.getColumnModel().getColumn(2).setMinWidth(100);
        jtVendas.getColumnModel().getColumn(3).setMinWidth(100);
        jtVendas.getColumnModel().getColumn(4).setMinWidth(200);

        jtVendas.getColumnModel().getColumn(0).setMaxWidth(70);
        jtVendas.getColumnModel().getColumn(1).setMaxWidth(340);
        jtVendas.getColumnModel().getColumn(2).setMaxWidth(100);
        jtVendas.getColumnModel().getColumn(3).setMaxWidth(100);
        jtVendas.getColumnModel().getColumn(4).setMaxWidth(200);
    }

    public void criarTabela() {
        this.limparTabela();
        int contador = 1;
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtVendas.getModel();
        for (Produto p : lista) {
            dtmBebidas.addRow(
                    new Object[]{
                        contador,
                        p.getNome(),
                        p.getQtdEstoque(),
                        p.getUnidadeDeMedida(),
                        p.getCategoria().getNome()}
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
        CategoriaDAO cDao = new CategoriaDAO();
        
        // Organiza itens do 1º CB (EXIBIR SOMENTE...)
        cbExibirSomente.removeAllItems();    
        cbExibirSomente.addItem("<Todos>");
        for (Categoria c : cDao.read()){
            cbExibirSomente.addItem(c.getNome());
        }
        
        // Organiza itens do 2º CB (CATEGORIA / NOME / QTD?)
        cbOrdemCategorias.removeAllItems();
        cbOrdemCategorias.addItem("Categoria");
        cbOrdemCategorias.addItem("Nome");
        cbOrdemCategorias.addItem("Qtd em Estoque");
        
        // Organiza itens do 3º CB (CRESCENTE / DESCRESCENTE)
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
        Paragraph pTitulo = new Paragraph(new Phrase(20F, "\nRestaurante Almanah\n", FontFactory.getFont(FontFactory.HELVETICA, 20F, Font.BOLD)));
        pTitulo.setAlignment(Element.ALIGN_CENTER);
        pTitulo.setSpacingBefore(20);
        pTitulo.setSpacingAfter(20);       
        cabecalho.addElement(pTitulo);
        
        // Célula do SUBTÍTULO ("Relatório de ...")
        Paragraph pSubtitulo = new Paragraph(new Phrase(20F, "Relatório de Estoque\n", FontFactory.getFont(FontFactory.HELVETICA, 16F)));
        pSubtitulo.setAlignment(Element.ALIGN_CENTER);
        pTitulo.setSpacingBefore(20);
        pTitulo.setSpacingAfter(20);       
        cabecalho.addElement(pSubtitulo);

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
        
        // Célula da Qtd em Estoque
        Paragraph p = new Paragraph(new Phrase(15F, "\nQtd em Estoque", FontFactory.getFont(FontFactory.HELVETICA, 14F)));
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingBefore(20);
        p.setSpacingAfter(20);
        PdfPCell celulaQtd = new PdfPCell();
        celulaQtd.addElement(p);
        celulaQtd.setBackgroundColor(BaseColor.LIGHT_GRAY);

        // Célula do Unidade de Medida
        Paragraph q = new Paragraph(new Phrase(15F, "\nMedida", FontFactory.getFont(FontFactory.HELVETICA, 14F)));
        q.setAlignment(Element.ALIGN_CENTER);
        q.setSpacingBefore(20);
        q.setSpacingAfter(20);
        PdfPCell celulaMedida = new PdfPCell();
        celulaMedida.addElement(q);
        celulaMedida.setBackgroundColor(BaseColor.LIGHT_GRAY);
        
        // Célula do Categoria
        Paragraph t = new Paragraph(new Phrase(15F, "\nCategoria", FontFactory.getFont(FontFactory.HELVETICA, 14F)));
        t.setAlignment(Element.ALIGN_CENTER);
        t.setSpacingBefore(20);
        t.setSpacingAfter(20);
        PdfPCell celulaCategoria = new PdfPCell();
        celulaCategoria.addElement(t);
        celulaCategoria.setBackgroundColor(BaseColor.LIGHT_GRAY);

        table.addCell(celulaNo);
        table.addCell(celulaNome);
        table.addCell(celulaQtd);
        table.addCell(celulaMedida);
        table.addCell(celulaCategoria);
        
        return table;
    }

    public void preencherDados(Document document, PdfPTable table, ArrayList<Produto> lista) throws DocumentException {
        Integer contador = 1;
        String c = "";
        String qtd = "";
        if (document.isOpen()) {
            for (Produto p : lista) {
                c = contador.toString();

                PdfPCell celula1 = new PdfPCell(new Phrase(c));
                PdfPCell celula2 = new PdfPCell(new Phrase(p.getNome()));
                PdfPCell celula3 = new PdfPCell(new Phrase(p.getQtdEstoque()));
                PdfPCell celula4 = new PdfPCell(new Phrase(p.getUnidadeDeMedida()));
                PdfPCell celula5 = new PdfPCell(new Phrase(p.getCategoria().getNome()));
                celula1.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula2.setHorizontalAlignment(Element.ALIGN_LEFT);
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
        linha2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        jPanel3 = new javax.swing.JPanel();
        cbOrdem = new javax.swing.JComboBox<>();
        cbOrdemCategorias = new javax.swing.JComboBox<>();
        lblString5 = new javax.swing.JLabel();
        lblString6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        cbExibirSomente = new javax.swing.JComboBox<>();
        lblManual2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Relatório de Estoque");
        setMinimumSize(new java.awt.Dimension(743, 625));
        setResizable(false);

        btnStringProdutos.setBackground(new java.awt.Color(0, 102, 204));
        btnStringProdutos.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        btnStringProdutos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringProdutos.setText("Relatório de Estoque");

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
                "No", "Nome", "Qtd em Estoque", "Medida", "Categoria"
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

        linha2.setBackground(new java.awt.Color(0, 0, 0));
        linha2.setOpaque(true);

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        cbOrdem.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cbOrdem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Descrescente", " " }));
        cbOrdem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbOrdemItemStateChanged(evt);
            }
        });

        cbOrdemCategorias.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cbOrdemCategorias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Descrescente", " " }));
        cbOrdemCategorias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbOrdemCategoriasItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbOrdemCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(cbOrdem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbOrdem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbOrdemCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblString5.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblString5.setText("Exibir somente:");

        lblString6.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblString6.setText("Ordenar por:");

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        cbExibirSomente.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cbExibirSomente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Descrescente", " " }));
        cbExibirSomente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbExibirSomenteItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbExibirSomente, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(152, 152, 152))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbExibirSomente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblManual2.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblManual2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblManual2.setText("?");
        lblManual2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblManual2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblManual2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLancador)
                        .addGap(188, 188, 188)
                        .addComponent(btnStringProdutos))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(lblString5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(19, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 816, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lblString6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
            .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(linha2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblManual2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLancador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(btnStringProdutos)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblString5)
                        .addGap(23, 23, 23)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblString6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblManual2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
            PdfWriter pdf = PdfWriter.getInstance(documento, new FileOutputStream("C:\\Projetos Netbeans\\AlmanahSystem\\relatorios\\estoque\\Estoque.pdf"));

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
            Desktop.getDesktop().open(new File("C:\\Projetos Netbeans\\AlmanahSystem\\relatorios\\estoque\\Estoque.pdf"));
        } catch (IOException ex) {
            System.out.println("Erro: " + ex);
        }

    }//GEN-LAST:event_btnExportarActionPerformed

    private void cbOrdemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbOrdemItemStateChanged
        this.getInfo();
    }//GEN-LAST:event_cbOrdemItemStateChanged

    private void cbOrdemCategoriasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbOrdemCategoriasItemStateChanged
        this.getInfo();
    }//GEN-LAST:event_cbOrdemCategoriasItemStateChanged

    private void cbExibirSomenteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbExibirSomenteItemStateChanged
        try{
            this.getInfo();
        }catch(java.lang.NullPointerException ex){}
        
    }//GEN-LAST:event_cbExibirSomenteItemStateChanged

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
            java.util.logging.Logger.getLogger(RelatorioEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RelatorioEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RelatorioEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RelatorioEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RelatorioEstoque().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnLancador;
    private javax.swing.JLabel btnStringProdutos;
    private static javax.swing.JComboBox<String> cbExibirSomente;
    private static javax.swing.JComboBox<String> cbOrdem;
    private static javax.swing.JComboBox<String> cbOrdemCategorias;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtVendas;
    private javax.swing.JLabel lblManual2;
    private javax.swing.JLabel lblString5;
    private javax.swing.JLabel lblString6;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    // End of variables declaration//GEN-END:variables
}
