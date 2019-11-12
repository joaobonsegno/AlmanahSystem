package main;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Categoria;
import model.bean.Fornecedor;
import model.bean.Produto;
import model.dao.CategoriaDAO;
import model.dao.FornecedorDAO;
import model.dao.ProdutoDAO;
import manual.Manual;

public class GerenciadorFornecedores extends javax.swing.JFrame {
    public static Fornecedor fornecedorSelecionado;
    ProdutoDAO pDao = new ProdutoDAO();
    FornecedorDAO fDao = new FornecedorDAO();
    
    public GerenciadorFornecedores() {
        initComponents();
        this.setLocationRelativeTo(null);       
        criarComboBox();
        formatarTabelas();
        btnAlterar.setEnabled(false);
        btnInativar.setEnabled(false);
        
        //Seta inativos todos os campos que devem começar inativos
        txtNome.setEnabled(false);
        txtCnpj.setEnabled(false);
        txtCelular.setEnabled(false);
        txtEmail.setEnabled(false);
        txtPesquisa.setEnabled(false);
        txtTelefone.setEnabled(false);
        cbCategorias.setEnabled(false);
        //Seta ineditáveis todos os campos que devem ser
        txtNome.setEditable(false);
        txtCnpj.setEditable(false);
        txtCelular.setEditable(false);
        txtEmail.setEditable(false);
        txtTelefone.setEditable(false);
        
        criarTabelaFornecedores();
    }

    public void formatarTabelas(){
        //Tabela Produtos
        jtProdutos.setRowHeight(27);
        jtProdutos.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtProdutos.getColumnModel().getColumn(1).setPreferredWidth(500);

        jtProdutos.getColumnModel().getColumn(0).setMinWidth(0);
        jtProdutos.getColumnModel().getColumn(1).setMinWidth(500);

        jtProdutos.getColumnModel().getColumn(0).setMaxWidth(0);
        jtProdutos.getColumnModel().getColumn(1).setMaxWidth(500);
        
        //Tabela Produtos Fornecidos
        jtProdutosFornecidos.setRowHeight(27);
        jtProdutosFornecidos.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtProdutosFornecidos.getColumnModel().getColumn(1).setPreferredWidth(500);

        jtProdutosFornecidos.getColumnModel().getColumn(0).setMinWidth(0);
        jtProdutosFornecidos.getColumnModel().getColumn(1).setMinWidth(500);

        jtProdutosFornecidos.getColumnModel().getColumn(0).setMaxWidth(0);
        jtProdutosFornecidos.getColumnModel().getColumn(1).setMaxWidth(500);
        
        //Tabela Fornecedores
        jtFornecedores.setRowHeight(27);
        jtFornecedores.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtFornecedores.getColumnModel().getColumn(1).setPreferredWidth(500);

        jtFornecedores.getColumnModel().getColumn(0).setMinWidth(0);
        jtFornecedores.getColumnModel().getColumn(1).setMinWidth(500);

        jtFornecedores.getColumnModel().getColumn(0).setMaxWidth(0);
        jtFornecedores.getColumnModel().getColumn(1).setMaxWidth(500);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Tabela de FORNECEDORES"> 
    public void criarTabelaFornecedores() {
        limparTabelaFornecedores();
        DefaultTableModel dtm = (DefaultTableModel) jtFornecedores.getModel();
        for (Fornecedor f : fDao.read()) {
            dtm.addRow(
                    new Object[]{
                        f.getId(),
                        f.getNome()}
            );
        }
    }
    
    public void criarTabelaFornecedoresNome(String nome) {
        limparTabelaFornecedores();
        DefaultTableModel dtm = (DefaultTableModel) jtFornecedores.getModel();
        for (Fornecedor f : fDao.readForNome(nome)) {
            dtm.addRow(
                    new Object[]{
                        f.getId(),
                        f.getNome()}
            );
        }
    }
    
    public void limparTabelaFornecedores() {
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtFornecedores.getModel();
        int i = dtmBebidas.getRowCount();

        for (int j = 0; j < i; j++) {
            dtmBebidas.removeRow(0);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Tabela de PRODUTOS FORNECIDOS"> 
    public void criarTabelaProdutosFornecidos() {
        limparTabelaProdutosFornecidos();
        ArrayList<Produto> ordenador = new ArrayList<>();

        for (Produto prod : fDao.readProdutosFornecidos(fornecedorSelecionado)) {
            ordenador.add(prod);              
        }
        Collections.sort(ordenador);

        DefaultTableModel dtmBebidas = (DefaultTableModel) jtProdutosFornecidos.getModel();
        for (Produto p : ordenador) {
            dtmBebidas.addRow(
                    new Object[]{
                        p.getIdProduto(),
                        p.getNome()}
            );
        }
    }
    
    public void limparTabelaProdutosFornecidos() {
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtProdutosFornecidos.getModel();
        int i = dtmBebidas.getRowCount();

        for (int j = 0; j < i; j++) {
            dtmBebidas.removeRow(0);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Tabela de PRODUTOS"> 
    public void criarTabelaNomeProdutos(String nome) {
        ArrayList<Produto> ordenador = new ArrayList<>();

        for (Produto prod : pDao.readForNome(nome)) {
            if (!fornecedorSelecionado.possuiProduto(prod)){
                ordenador.add(prod);   
            }  
        }
        Collections.sort(ordenador);

        DefaultTableModel dtmBebidas = (DefaultTableModel) jtProdutos.getModel();
        limparTabelaProdutos();
        for (Produto p : ordenador) {
            dtmBebidas.addRow(
                    new Object[]{
                        p.getIdProduto(),
                        p.getNome()}
            );
        }
    }

    public void criarTabelaCategoriaProdutos(String nome) {
        limparTabelaProdutos();
        ArrayList<Produto> ordenador = new ArrayList<>();

        for (Produto prod : pDao.readForCategoria(nome)) {
            if (!fornecedorSelecionado.possuiProduto(prod)){
                
                ordenador.add(prod);   
            }
        }
        Collections.sort(ordenador);

        DefaultTableModel dtmBebidas = (DefaultTableModel) jtProdutos.getModel();     
        for (Produto p : ordenador) {
            dtmBebidas.addRow(
                    new Object[]{
                        p.getIdProduto(),
                        p.getNome()}
            );
        }
    }

    public void limparTabelaProdutos() {
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtProdutos.getModel();
        int i = dtmBebidas.getRowCount();

        for (int j = 0; j < i; j++) {
            dtmBebidas.removeRow(0);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Config da tela: CRIAR CB / ATIVAR BOTÕES / DESATIVAR BOTOES"> 
    public void criarComboBox() {
        cbCategorias.removeAllItems();
        cbCategorias.addItem("");
        CategoriaDAO catDao = new CategoriaDAO();
        for (Categoria c : catDao.read()) {
            cbCategorias.addItem(c.getNome());
        }
    }

    public void ativarBotoesECampos(){
        btnAlterar.setEnabled(true);
        btnInativar.setEnabled(true);
        txtNome.setEnabled(true);
        txtCnpj.setEnabled(true);
        txtCelular.setEnabled(true);
        txtEmail.setEnabled(true);
        txtTelefone.setEnabled(true);
        txtPesquisa.setEnabled(true);
        cbCategorias.setEnabled(true);
    }
    
    public void inativarBotoesECampos(){
        txtNome.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        txtCelular.setText("");
        txtCnpj.setText("");
        
        limparTabelaProdutos();
        limparTabelaProdutosFornecidos();
        
        btnAlterar.setEnabled(false);
        btnInativar.setEnabled(false);
        txtNome.setEnabled(false);
        txtCnpj.setEnabled(false);
        txtCelular.setEnabled(false);
        txtEmail.setEnabled(false);
        txtTelefone.setEnabled(false);
        txtPesquisa.setEnabled(false);
        cbCategorias.setEnabled(false);
    }
    // </editor-fold>
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStringProdutos = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnLancador = new javax.swing.JButton();
        linha2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnLancador1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProdutos = new javax.swing.JTable();
        lblStringNomeProduto = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        lblStringNomeProduto1 = new javax.swing.JLabel();
        cbCategorias = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtFornecedores = new javax.swing.JTable();
        lblStringNomeProduto2 = new javax.swing.JLabel();
        txtPesquisaFornecedor = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lblStringNomeProduto3 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblStringNomeProduto4 = new javax.swing.JLabel();
        txtCnpj = new javax.swing.JTextField();
        lblStringNomeProduto5 = new javax.swing.JLabel();
        lblStringNomeProduto6 = new javax.swing.JLabel();
        lblStringNomeProduto7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JTextField();
        btnAlterar = new javax.swing.JButton();
        btnInativar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtProdutosFornecidos = new javax.swing.JTable();
        btnAlterar1 = new javax.swing.JButton();
        lblManual = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Gerenciador de Forencedores");
        setMaximumSize(new java.awt.Dimension(930, 668));
        setMinimumSize(new java.awt.Dimension(930, 668));
        setResizable(false);

        btnStringProdutos.setBackground(new java.awt.Color(0, 102, 204));
        btnStringProdutos.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        btnStringProdutos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringProdutos.setText("Gerenciador de Fornecedores");

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

        linha2.setBackground(new java.awt.Color(0, 0, 0));
        linha2.setOpaque(true);

        btnLancador1.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        btnLancador1.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\inativo true (1).png")); // NOI18N
        btnLancador1.setText("Inativos");
        btnLancador1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLancador1ActionPerformed(evt);
            }
        });

        jtProdutos.setBorder(new javax.swing.border.MatteBorder(null));
        jtProdutos.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jtProdutos.setModel(new javax.swing.table.DefaultTableModel(
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
        jtProdutos.getTableHeader().setReorderingAllowed(false);
        jtProdutos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtProdutosFocusGained(evt);
            }
        });
        jtProdutos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtProdutosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtProdutos);
        if (jtProdutos.getColumnModel().getColumnCount() > 0) {
            jtProdutos.getColumnModel().getColumn(0).setResizable(false);
            jtProdutos.getColumnModel().getColumn(1).setResizable(false);
        }

        lblStringNomeProduto.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblStringNomeProduto.setText("Nome:");

        txtPesquisa.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
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

        lblStringNomeProduto1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblStringNomeProduto1.setText("Categoria:");

        cbCategorias.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblStringNomeProduto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStringNomeProduto1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCategorias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringNomeProduto)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStringNomeProduto1)
                    .addComponent(cbCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jtFornecedores.setBorder(new javax.swing.border.MatteBorder(null));
        jtFornecedores.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jtFornecedores.setModel(new javax.swing.table.DefaultTableModel(
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
        jtFornecedores.getTableHeader().setReorderingAllowed(false);
        jtFornecedores.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtFornecedoresFocusGained(evt);
            }
        });
        jtFornecedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtFornecedoresMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtFornecedores);
        if (jtFornecedores.getColumnModel().getColumnCount() > 0) {
            jtFornecedores.getColumnModel().getColumn(0).setResizable(false);
            jtFornecedores.getColumnModel().getColumn(1).setResizable(false);
        }

        lblStringNomeProduto2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblStringNomeProduto2.setText("Nome:");

        txtPesquisaFornecedor.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPesquisaFornecedor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPesquisaFornecedorFocusGained(evt);
            }
        });
        txtPesquisaFornecedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaFornecedorKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblStringNomeProduto2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisaFornecedor)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringNomeProduto2)
                    .addComponent(txtPesquisaFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel3.setMaximumSize(new java.awt.Dimension(494, 221));
        jPanel3.setMinimumSize(new java.awt.Dimension(494, 221));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblStringNomeProduto3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblStringNomeProduto3.setText("Celular:");
        jPanel3.add(lblStringNomeProduto3, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 141, -1, -1));

        txtNome.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNome.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNomeFocusGained(evt);
            }
        });
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomeKeyReleased(evt);
            }
        });
        jPanel3.add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 11, 367, -1));

        lblStringNomeProduto4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblStringNomeProduto4.setText("CNPJ:");
        jPanel3.add(lblStringNomeProduto4, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 184, -1, -1));

        txtCnpj.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCnpj.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCnpjFocusGained(evt);
            }
        });
        txtCnpj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCnpjKeyReleased(evt);
            }
        });
        jPanel3.add(txtCnpj, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 183, 195, -1));

        lblStringNomeProduto5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblStringNomeProduto5.setText("Nome:");
        jPanel3.add(lblStringNomeProduto5, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 12, -1, -1));

        lblStringNomeProduto6.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblStringNomeProduto6.setText("Telefone:");
        jPanel3.add(lblStringNomeProduto6, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 98, -1, -1));

        lblStringNomeProduto7.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblStringNomeProduto7.setText("Email:");
        jPanel3.add(lblStringNomeProduto7, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 55, -1, -1));

        txtEmail.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEmailFocusGained(evt);
            }
        });
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });
        jPanel3.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 54, 195, -1));

        txtCelular.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCelular.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCelularFocusGained(evt);
            }
        });
        txtCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCelularKeyReleased(evt);
            }
        });
        jPanel3.add(txtCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 140, 195, -1));

        txtTelefone.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTelefone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTelefoneFocusGained(evt);
            }
        });
        txtTelefone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefoneKeyReleased(evt);
            }
        });
        jPanel3.add(txtTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 97, 195, -1));

        btnAlterar.setBackground(new java.awt.Color(204, 204, 0));
        btnAlterar.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        btnAlterar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\lapis1 (1).png")); // NOI18N
        btnAlterar.setText("   Alterar");
        btnAlterar.setBorder(new javax.swing.border.MatteBorder(null));
        btnAlterar.setBorderPainted(false);
        btnAlterar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnAlterarFocusGained(evt);
            }
        });
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        jPanel3.add(btnAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 120, 40));

        btnInativar.setBackground(new java.awt.Color(204, 0, 0));
        btnInativar.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        btnInativar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\delete (2) (1).png")); // NOI18N
        btnInativar.setText("   Inativar");
        btnInativar.setBorder(new javax.swing.border.MatteBorder(null));
        btnInativar.setBorderPainted(false);
        btnInativar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInativarActionPerformed(evt);
            }
        });
        jPanel3.add(btnInativar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 120, 38));

        jtProdutosFornecidos.setBorder(new javax.swing.border.MatteBorder(null));
        jtProdutosFornecidos.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jtProdutosFornecidos.setModel(new javax.swing.table.DefaultTableModel(
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
        jtProdutosFornecidos.getTableHeader().setReorderingAllowed(false);
        jtProdutosFornecidos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtProdutosFornecidosFocusGained(evt);
            }
        });
        jtProdutosFornecidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtProdutosFornecidosKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jtProdutosFornecidos);
        if (jtProdutosFornecidos.getColumnModel().getColumnCount() > 0) {
            jtProdutosFornecidos.getColumnModel().getColumn(0).setResizable(false);
            jtProdutosFornecidos.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 15, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnAlterar1.setBackground(new java.awt.Color(0, 153, 0));
        btnAlterar1.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        btnAlterar1.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\add (2) (1).png")); // NOI18N
        btnAlterar1.setText("  Novo");
        btnAlterar1.setBorder(new javax.swing.border.MatteBorder(null));
        btnAlterar1.setBorderPainted(false);
        btnAlterar1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnAlterar1FocusGained(evt);
            }
        });
        btnAlterar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterar1ActionPerformed(evt);
            }
        });

        lblManual.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblManual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblManual.setText("?");
        lblManual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnLancador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnStringProdutos)
                .addGap(151, 151, 151)
                .addComponent(btnLancador1))
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAlterar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(lblManual)
                .addGap(3, 3, 3))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha2, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLancador1)
                    .addComponent(btnLancador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStringProdutos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAlterar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblManual))
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(656, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLancadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancadorActionPerformed
        new Menu().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLancadorActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        try {
            if (jtFornecedores.getSelectedRowCount() > 1) {
                JOptionPane.showMessageDialog(null, "Selecione somente 1 (um) fornecedor");
            }else{
                new AlterarFornecedor().setVisible(true);
                dispose();
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Selecione o fornecedor que deseja alterar");
        }
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnInativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInativarActionPerformed
        try {
            if (jtFornecedores.getSelectedRowCount() > 1) {
                JOptionPane.showMessageDialog(null, "Selecione somente 1 (um) fornecedor");
            } else {
                int reply = JOptionPane.showConfirmDialog(null, "Deseja realmente inativar o fornecedor?", "Inativação de Fornecedor", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    Integer idSelecionado = (Integer) jtFornecedores.getValueAt(jtFornecedores.getSelectedRow(), 0);      
                    fDao.setInativo(idSelecionado);
                    this.limparTabelaFornecedores();
                    this.criarTabelaFornecedores();
                    inativarBotoesECampos();
                }
            }
        } catch (java.util.ConcurrentModificationException ex) {
            System.out.println("Deu a exceção");
        } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Selecione o fornecedor que deseja inativar");
        }
    }//GEN-LAST:event_btnInativarActionPerformed

    private void cbCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoriasActionPerformed

    }//GEN-LAST:event_cbCategoriasActionPerformed

    private void cbCategoriasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCategoriasItemStateChanged
        String escolhido = (String) cbCategorias.getSelectedItem();
        txtPesquisa.setText("");
        try {
            if (!escolhido.equals("")) {
                criarTabelaCategoriaProdutos(escolhido);
            } else {
                limparTabelaProdutos();
            }
        } catch (java.lang.NullPointerException ex) {

        }
    }//GEN-LAST:event_cbCategoriasItemStateChanged

    private void txtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyReleased
        String entrada = txtPesquisa.getText();
        if (entrada.length() >= 3) {
            this.criarTabelaNomeProdutos(entrada);
        } else {
            this.limparTabelaProdutos();
        }

    }//GEN-LAST:event_txtPesquisaKeyReleased

    private void txtPesquisaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesquisaFocusGained
        cbCategorias.setSelectedIndex(0);
        btnAlterar.setEnabled(false);
        btnInativar.setEnabled(false);
        jtProdutos.clearSelection();
    }//GEN-LAST:event_txtPesquisaFocusGained

    private void btnLancador1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancador1ActionPerformed
        InativosFornecedores novoPrato = new InativosFornecedores(new javax.swing.JFrame(), true);
        novoPrato.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLancador1ActionPerformed

    private void jtProdutosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtProdutosFocusGained
        
    }//GEN-LAST:event_jtProdutosFocusGained

    private void cbCategoriasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbCategoriasFocusGained
        
        jtProdutos.clearSelection();
    }//GEN-LAST:event_cbCategoriasFocusGained

    private void btnAlterarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnAlterarFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAlterarFocusGained

    private void jtFornecedoresFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtFornecedoresFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtFornecedoresFocusGained

    private void txtPesquisaFornecedorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesquisaFornecedorFocusGained
        inativarBotoesECampos();
        jtFornecedores.clearSelection();
    }//GEN-LAST:event_txtPesquisaFornecedorFocusGained

    private void txtPesquisaFornecedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaFornecedorKeyReleased
        String entrada = txtPesquisaFornecedor.getText();
        if (entrada.length() >= 3) {
            this.criarTabelaFornecedoresNome(entrada);
        } else {
            this.criarTabelaFornecedores();
        }
    }//GEN-LAST:event_txtPesquisaFornecedorKeyReleased

    private void txtNomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNomeFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeFocusGained

    private void txtNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeKeyReleased

    private void txtCnpjFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCnpjFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCnpjFocusGained

    private void txtCnpjKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCnpjKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCnpjKeyReleased

    private void txtEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailFocusGained

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtCelularFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCelularFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCelularFocusGained

    private void txtCelularKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelularKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCelularKeyReleased

    private void txtTelefoneFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTelefoneFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefoneFocusGained

    private void txtTelefoneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefoneKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefoneKeyReleased

    private void jtProdutosFornecidosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtProdutosFornecidosFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtProdutosFornecidosFocusGained

    private void btnAlterar1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnAlterar1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAlterar1FocusGained

    private void btnAlterar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterar1ActionPerformed
        new CadastrarFornecedor().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnAlterar1ActionPerformed

    private void jtProdutosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtProdutosKeyReleased
        // Tecla 39: ">"   &    Tecla 37: "<"
        if (evt.getKeyCode() == 39){
            jtProdutosFornecidos.clearSelection();
            int qtd = jtProdutos.getSelectedRowCount();
            
            for (int i = 0; i < qtd; i++){
                Produto p = pDao.readForId((Integer)jtProdutos.getValueAt(jtProdutos.getSelectedRows()[i], 0));
                fornecedorSelecionado.setProdutoSalvando(p); // ESTE MÉTODO JÁ ADICIONA O ITEM NO BANCO
            }

            // Cria novamente a tabela, retirando o produto que acabou de ser adicionado ao Fornecedor
            String escolhido = (String)cbCategorias.getSelectedItem();
            if (!escolhido.equals("")){ 
                criarTabelaCategoriaProdutos(escolhido);
            }else{
                criarTabelaNomeProdutos(txtPesquisa.getText());
            }
            
            // Cria novamente a tabela de produtos fornecidos
            criarTabelaProdutosFornecidos();
        }
    }//GEN-LAST:event_jtProdutosKeyReleased

    private void jtFornecedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtFornecedoresMouseClicked
        if (evt.getClickCount() == 2) {
            if (jtFornecedores.getSelectedRowCount() > 1){
                JOptionPane.showMessageDialog(null, "Selecione somente 1 (um) fornecedor.");
            }else{
                Integer idSelecionado = (Integer) jtFornecedores.getValueAt(jtFornecedores.getSelectedRow(), 0);
                fornecedorSelecionado = fDao.readForId(idSelecionado);
                txtNome.setText(fornecedorSelecionado.getNome());
                txtEmail.setText(fornecedorSelecionado.getEmail());
                txtTelefone.setText(fornecedorSelecionado.getTelefone());
                txtCelular.setText(fornecedorSelecionado.getCelular());
                txtCnpj.setText(fornecedorSelecionado.getCnpj());
                ativarBotoesECampos();     
                criarTabelaProdutosFornecidos();
            }
        }
    }//GEN-LAST:event_jtFornecedoresMouseClicked

    private int getIndice(Produto prod){
        int contador = 0;
        for (Produto p : fornecedorSelecionado.getProdutosFornecidos()){
            if (p.getIdProduto() == prod.getIdProduto()){
                break;
            }
            contador += 1;
        }
        return contador;
    }
    
    private void jtProdutosFornecidosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtProdutosFornecidosKeyReleased
        // Tecla 39: ">"   &    Tecla 37: "<"
        if (evt.getKeyCode() == 37){
            jtProdutos.clearSelection();
            int qtd = jtProdutosFornecidos.getSelectedRowCount();
            
            for (int i = 0; i < qtd; i++){
                Produto p = pDao.readForId((Integer)jtProdutosFornecidos.getValueAt(jtProdutosFornecidos.getSelectedRows()[i], 0));
                fornecedorSelecionado.removerProduto(getIndice(p));
            }

            // Cria novamente a tabela, adicionando o produto que acabou de ser adicionado à tabela de Produtos
            String escolhido = (String)cbCategorias.getSelectedItem();
            if (!escolhido.equals("")){ 
                criarTabelaCategoriaProdutos(escolhido);
            }else{
                criarTabelaNomeProdutos(txtPesquisa.getText());
            }
            
            // Cria novamente a tabela de produtos fornecidos
            criarTabelaProdutosFornecidos();
        }
    }//GEN-LAST:event_jtProdutosFornecidosKeyReleased

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
            java.util.logging.Logger.getLogger(GerenciadorFornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciadorFornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciadorFornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciadorFornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerenciadorFornecedores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnAlterar1;
    private javax.swing.JButton btnInativar;
    private javax.swing.JButton btnLancador;
    private javax.swing.JButton btnLancador1;
    private javax.swing.JLabel btnStringProdutos;
    private javax.swing.JComboBox<String> cbCategorias;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private static javax.swing.JTable jtFornecedores;
    private static javax.swing.JTable jtProdutos;
    private static javax.swing.JTable jtProdutosFornecidos;
    private javax.swing.JLabel lblManual;
    private javax.swing.JLabel lblStringNomeProduto;
    private javax.swing.JLabel lblStringNomeProduto1;
    private javax.swing.JLabel lblStringNomeProduto2;
    private javax.swing.JLabel lblStringNomeProduto3;
    private javax.swing.JLabel lblStringNomeProduto4;
    private javax.swing.JLabel lblStringNomeProduto5;
    private javax.swing.JLabel lblStringNomeProduto6;
    private javax.swing.JLabel lblStringNomeProduto7;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCnpj;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPesquisa;
    private javax.swing.JTextField txtPesquisaFornecedor;
    private javax.swing.JTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
