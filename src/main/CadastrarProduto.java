package main;

import ArrumarString.Monetarios;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.Categoria;
import model.bean.Produto;
import model.dao.MateriaPrimaDAO;
import model.dao.ProdutoDAO;

public class CadastrarProduto extends javax.swing.JFrame {
    ArrayList<Produto> listaProdutos = new ArrayList<>();
    ProdutoDAO pDao = new ProdutoDAO();
    
    public void criarCb(){
        cbCategoria.removeAllItems();
        cbCategoria.addItem("");
        for (Categoria c : Login.categorias){
            cbCategoria.addItem(c.getNome());
        }
        
        cbUnidade.removeAllItems();
        cbUnidade.addItem("");
        cbUnidade.addItem("Kg");
        cbUnidade.addItem("Unidade");
    }
    
    public CadastrarProduto() {
        initComponents();
        this.setLocationRelativeTo(null);
        criarCb();
        for(Produto p:pDao.read()){
            listaProdutos.add(p);
        }
        getRootPane().setDefaultButton(btnConfirmar);
        txtDescricao.setWrapStyleWord(true);
        lblAdicionar.setText("<html><u>[Adicionar]</u></html>");

        txtPreco.setDocument(new Monetarios(7,2));
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStringNovoProduto = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblStringNcm = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblStringNome = new javax.swing.JLabel();
        txtNcm = new javax.swing.JTextField();
        lblStringQuantidadeMin = new javax.swing.JLabel();
        txtQtdMinima = new javax.swing.JTextField();
        lblStringDescricao = new javax.swing.JLabel();
        txtEan = new javax.swing.JTextField();
        lblStringNfe = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        cbCategoria = new javax.swing.JComboBox<>();
        lblStringUnidade = new javax.swing.JLabel();
        cbUnidade = new javax.swing.JComboBox<>();
        lblStringId1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblStringId = new javax.swing.JLabel();
        lblAdicionar = new javax.swing.JLabel();
        lblStringMaterias = new javax.swing.JLabel();
        lblAdicionar1 = new javax.swing.JLabel();
        lblStringPreco = new javax.swing.JLabel();
        txtPreco = new javax.swing.JTextField();
        lblStringPreco1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastrar Produto");
        setMinimumSize(new java.awt.Dimension(810, 572));
        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
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
        lblStringNovoProduto.setText("Cadastrar Produto");

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

        lblStringNcm.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNcm.setText("NCM:");

        txtNome.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        lblStringNome.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNome.setText("Nome:");

        txtNcm.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        lblStringQuantidadeMin.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringQuantidadeMin.setText("Quantidade mínima:");

        txtQtdMinima.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        txtQtdMinima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQtdMinimaActionPerformed(evt);
            }
        });

        lblStringDescricao.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringDescricao.setText("Observações:");

        txtEan.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        lblStringNfe.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNfe.setText("Código de Barras:");

        txtDescricao.setColumns(20);
        txtDescricao.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtDescricao.setLineWrap(true);
        txtDescricao.setRows(5);
        jScrollPane2.setViewportView(txtDescricao);

        cbCategoria.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCategoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCategoriaItemStateChanged(evt);
            }
        });
        cbCategoria.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cbCategoriaFocusGained(evt);
            }
        });

        lblStringUnidade.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringUnidade.setText("Unidade de Medida:");

        cbUnidade.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cbUnidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbUnidade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbUnidadeItemStateChanged(evt);
            }
        });

        lblStringId1.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringId1.setText("Categoria:");

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

        lblStringMaterias.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblAdicionar)
                        .addContainerGap())
                    .addComponent(lblStringMaterias, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)))
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

        lblAdicionar1.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblAdicionar1.setText("[+]");
        lblAdicionar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAdicionar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAdicionar1MouseClicked(evt);
            }
        });

        lblStringPreco.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringPreco.setText("Preço de Venda:");

        txtPreco.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        lblStringPreco1.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringPreco1.setText("R$");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(7, 7, 7)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblStringQuantidadeMin)
                                        .addComponent(lblStringDescricao)
                                        .addComponent(lblStringUnidade, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(241, 241, 241)
                                                    .addComponent(lblStringId1))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(cbUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(lblStringPreco)))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(cbCategoria, 0, 186, Short.MAX_VALUE)
                                                .addComponent(txtPreco))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblAdicionar1)
                                                .addComponent(lblStringPreco1))))
                                    .addGap(4, 4, 4))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblStringNfe)
                                        .addComponent(lblStringNcm)
                                        .addComponent(lblStringNome))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(5, 5, 5)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtQtdMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtEan, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtNcm, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(7, 7, 7))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(295, 295, 295)
                            .addComponent(lblStringNovoProduto))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(45, 45, 45)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(85, 85, 85)
                            .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(157, 157, 157))))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblStringNovoProduto)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNcm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStringNcm))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStringNfe))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblStringId1)
                        .addComponent(lblAdicionar1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblStringQuantidadeMin)
                        .addComponent(txtQtdMinima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringUnidade)
                    .addComponent(cbUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStringPreco)
                    .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStringPreco1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblStringDescricao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(57, 57, 57)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(548, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        String cat = (String)cbCategoria.getSelectedItem();
        String unid = (String)cbUnidade.getSelectedItem();
        String nome = txtNome.getText();
        String precoString = txtPreco.getText();
               
        if(!(cat.equals("")|nome.equals(""))){
            try{
                // Instancia o produto e seu objeto de acesso de dados
                Produto p = new Produto();
                ProdutoDAO pDao = new ProdutoDAO();
                
                // Verifica se o produto sendo cadastrado vai ter preço
                if (!precoString.equals("")){ 
                    // Se o usuário tiver entrado algum preço, seta este no produto
                    p.setPreco(Double.parseDouble(GerenciadorComandas.tornarCompativel(precoString)));              //Atributo 1
                    p.setPrecoComDesconto(Double.parseDouble(GerenciadorComandas.tornarCompativel(precoString)));   //Atributo 2
                }else{
                    // Se o usuário não tiver entrado preço, seta o preço como 0 (zero)
                    p.setPreco(0.0);
                }

                // Instancia o objeto categoria do novo produto
                Categoria categoria = new Categoria();
                for(Categoria c:Login.categorias){
                    if(cat.equals(c.getNome())){
                        categoria.setId(c.getId());
                        categoria.setNome(c.getNome());
                        categoria.setDescricao(c.getDescricao());
                    }
                }
                p.setCategoria(categoria);                     //Atributo 3
                
                // Seta os outros atributos do novo produto
                p.setUnidadeDeMedida(unid);                    //Atributo 4
                p.setNome(txtNome.getText());                  //Atributo 5
                p.setDescricao(txtDescricao.getText());        //Atributo 6
                p.setNcm(txtNcm.getText());                    //Atributo 7
                p.setEan(txtEan.getText());                    //Atributo 8
                p.setValidade("");                             //Atributo 9
                p.setQtdEstoque("0");                          //Atributo 10
                
                // Verifica se o sistema controlará o ESTOQUE do produto
                if(!txtQtdMinima.getText().equals("")){
                    p.setQtdMinima(txtQtdMinima.getText());    //Atributo 11
                }else{
                    p.setQtdMinima("X");
                    p.setQtdEstoque("X");
                }
                
                // Cria o sistema no banco, para depois pegar o ID gerado e colocar no objeto
                pDao.create(p); 
                for (Produto prod:pDao.read()){
                    if (p.getNome().equals(prod.getNome())){
                        p.setIdProduto(prod.getIdProduto());                        
                    }
                }
                
                // Pega os subprodutos (matérias-primas) do novo produto
                if (!CadastrarMateriaPrima.materiasSelecionadas.isEmpty()){ // Verifica de a lista de matérias selecionadas NÃO é vazia
                    for (Produto materia : CadastrarMateriaPrima.materiasSelecionadas){
                        p.setMateriaPrima(materia);
                    }
                    MateriaPrimaDAO materiaDao = new MateriaPrimaDAO();
                    materiaDao.create(p);
                }
                
                listaProdutos.add(p);
                CadastrarMateriaPrima.materiasSelecionadas.removeAll(CadastrarMateriaPrima.materiasSelecionadas);
                new Menu().setVisible(true);
                dispose();                               
            }catch(java.lang.NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Campo \"Preço\" inválido");
            }
        }else{
            String msg = "\n";
            if(cat.equals("")){
                msg += ("- Categoria\n");
            }
            if(nome.equals("")){
                msg += ("- Nome\n");
            }
            JOptionPane.showMessageDialog(null, "O(s) seguinte(s) campo(s) deve(m) ser preenchido(s):"+msg);
        }                         
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (Menu.acaoEscolhida == 2){
            new GerenciadorProdutos().setVisible(true);
            dispose();
        }else{
            new Menu().setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtQtdMinimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQtdMinimaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQtdMinimaActionPerformed

    private void cbCategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCategoriaItemStateChanged

    }//GEN-LAST:event_cbCategoriaItemStateChanged

    private void cbUnidadeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbUnidadeItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbUnidadeItemStateChanged

    private void lblStringIdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStringIdMouseClicked
        
    }//GEN-LAST:event_lblStringIdMouseClicked

    private void lblAdicionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAdicionarMouseClicked
        CadastrarMateriaPrima materia = new CadastrarMateriaPrima(new javax.swing.JFrame(), true);
        materia.setVisible(true); 
    }//GEN-LAST:event_lblAdicionarMouseClicked

    private void lblStringMateriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStringMateriasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblStringMateriasMouseClicked

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        
        
    }//GEN-LAST:event_formFocusGained

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        lblStringMaterias.setText("");
        String materias = "<html><body>";   
        int i = 0;
        if (!CadastrarMateriaPrima.materiasSelecionadas.isEmpty()){ //Verifica se a lista de matérias NÃO está vazia
           // Integer tamanho = CadastrarMateriaPrima.materiasSelecionadas.size(); //Pega o tamanho da lista de matérias
            for (Produto p:CadastrarMateriaPrima.materiasSelecionadas){
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
    }//GEN-LAST:event_formComponentShown

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        
    }//GEN-LAST:event_formMouseMoved

    private void lblAdicionar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAdicionar1MouseClicked
        new CadastrarCategoria().setVisible(true);
    }//GEN-LAST:event_lblAdicionar1MouseClicked

    private void cbCategoriaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbCategoriaFocusGained
        criarCb();
    }//GEN-LAST:event_cbCategoriaFocusGained

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        lblStringMaterias.setText("");
        String materias = "<html><body>";   
        int i = 0;
        if (!CadastrarMateriaPrima.materiasSelecionadas.isEmpty()){ //Verifica se a lista de matérias NÃO está vazia
           // Integer tamanho = CadastrarMateriaPrima.materiasSelecionadas.size(); //Pega o tamanho da lista de matérias
            for (Produto p:CadastrarMateriaPrima.materiasSelecionadas){
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
            java.util.logging.Logger.getLogger(CadastrarProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastrarProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastrarProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new CadastrarProduto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private static javax.swing.JComboBox<String> cbCategoria;
    private static javax.swing.JComboBox<String> cbUnidade;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAdicionar;
    private javax.swing.JLabel lblAdicionar1;
    private javax.swing.JLabel lblStringDescricao;
    private javax.swing.JLabel lblStringId;
    private javax.swing.JLabel lblStringId1;
    private javax.swing.JLabel lblStringMaterias;
    private javax.swing.JLabel lblStringNcm;
    private javax.swing.JLabel lblStringNfe;
    private javax.swing.JLabel lblStringNome;
    private javax.swing.JLabel lblStringNovoProduto;
    private javax.swing.JLabel lblStringPreco;
    private javax.swing.JLabel lblStringPreco1;
    private javax.swing.JLabel lblStringQuantidadeMin;
    private javax.swing.JLabel lblStringUnidade;
    private javax.swing.Box.Filler linha1;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtEan;
    private javax.swing.JTextField txtNcm;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPreco;
    private javax.swing.JTextField txtQtdMinima;
    // End of variables declaration//GEN-END:variables
}


