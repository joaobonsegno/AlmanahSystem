package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.Timer;
import static main.Login.funcAtual;
import model.bean.Produto;
import model.bean.PromocaoUm;
import model.dao.ProdutoDAO;
import model.dao.PromocaoUmDAO;

//Inserir horário
public class Menu extends javax.swing.JFrame {
    public static Integer acaoEscolhida = null;
    public static boolean flagDia = false;
    
    public Menu() {
        initComponents();
        Timer timer = new Timer(1000, new Menu.ClockListener());
        timer.start();
        this.setLocationRelativeTo(null);
      
        if(Login.caixaAtual != null){
            pdv_comandas.setEnabled(true);
        }else{
            pdv_comandas.setEnabled(false);
        }
        
        setPromocao();
        setFuncionario();      
    }
    
    public void setFuncionario(){
        if (null != funcAtual.getCargo().getId())
            switch (funcAtual.getCargo().getId()) {
                case 1:
                    setGarcom();
                    break;
                case 2:
                    setBalconista();
                    break;
                case 3:
                    setCozinheiro();
                    break;
            }
        
        lblNome.setText(Login.funcAtual.getNome());
        lblFuncao.setText(Login.funcAtual.getCargo().getNome());
    }

    public void setPromocao(){
        Date d = new Date(); 
        Calendar c = new GregorianCalendar(); 
        c.setTime(d); 
        int dia = c.get(c.DAY_OF_WEEK);
        
        ProdutoDAO pDao = new ProdutoDAO();
        PromocaoUmDAO promoDao = new PromocaoUmDAO();
        PromocaoUm promocaoUm = promoDao.read();
        if (promocaoUm.getStatus() == 1){
            switch(dia){
                case 1:
                    if (promocaoUm.getDom() == 1){
                        flagDia = true;
                    }else{
                        flagDia = false;
                    }
                    break;
                case 2:
                    if (promocaoUm.getSeg() == 1){
                        flagDia = true;
                    }else{
                        flagDia = false;
                    }
                    break;
                case 3:
                    if (promocaoUm.getTer() == 1){
                        flagDia = true;
                    }else{
                        flagDia = false;
                    }
                    break;
                case 4:
                    if (promocaoUm.getQua() == 1){
                        flagDia = true;
                    }else{
                        flagDia = false;
                    }
                    break;
                case 5:
                    if (promocaoUm.getQui() == 1){
                        flagDia = true;
                    }else{
                        flagDia = false;
                    }
                    break;
                case 6:
                    if (promocaoUm.getSex() == 1){
                        flagDia = true;
                    }else{
                        flagDia = false;
                    }
                    break;
                case 7:
                    if (promocaoUm.getSab() == 1){
                        flagDia = true;
                    }else{
                        flagDia = false;
                    }
                    break;
            }
            if (flagDia){
                for (Produto prod:pDao.read()){
                    if (prod.getCategoria().getNome().equals("Suco")){
                        prod.setPrecoComDesconto(prod.getPreco()*promocaoUm.getMultiplicador());
                        pDao.updatePromocao(prod);
                    }              
                }
            }         
        }
    }
    
    public void setGarcom(){
        // ID: 1
        pdv_caixa.setEnabled(false);
        estoque.setVisible(false);
        produtos.setVisible(false);
        funcionarios.setVisible(false);
        clientes.setVisible(false);
        financeiro.setVisible(false);
    }
    
    public void setBalconista(){
        // ID: 2
        //pdv_caixa.setEnabled(false);
        estoque.setVisible(false);
        produtos.setVisible(false);
        funcionarios.setVisible(false);
        //clientes.setVisible(false);
        financeiro.setVisible(false);
    }
    
    public void setCozinheiro(){
        // ID: 3
        pdv_caixa.setVisible(false);
        estoque_movimentacao.setEnabled(false);
        produtos.setVisible(false);
        funcionarios.setVisible(false);
        clientes.setVisible(false);
        financeiro.setVisible(false);
    }
    
    class ClockListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Calendar now = Calendar.getInstance();
            lblHora.setText(String.format("%1$tH:%1$tM:%1$tS", now));
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnSair = new javax.swing.JButton();
        lblLogo = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        btnLancador1 = new javax.swing.JButton();
        lblStringMenu1 = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblFuncao = new javax.swing.JLabel();
        lblNome1 = new javax.swing.JLabel();
        lblNome2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        pdv = new javax.swing.JMenu();
        pdv_caixa = new javax.swing.JMenuItem();
        pdv_comandas = new javax.swing.JMenuItem();
        estoque = new javax.swing.JMenu();
        estoque_gerenciar = new javax.swing.JMenuItem();
        estoque_movimentacao = new javax.swing.JMenuItem();
        produtos = new javax.swing.JMenu();
        produtos_gerenciar = new javax.swing.JMenuItem();
        produtos_cadastrar = new javax.swing.JMenuItem();
        produtos_gerenciarPratos = new javax.swing.JMenuItem();
        produtos_cadastrarPratos = new javax.swing.JMenuItem();
        produtos_cardapio = new javax.swing.JMenuItem();
        funcionarios = new javax.swing.JMenu();
        funcionarios_gerenciar = new javax.swing.JMenuItem();
        funcionarios_cadastrar = new javax.swing.JMenuItem();
        clientes = new javax.swing.JMenu();
        funcionarios_gerenciar1 = new javax.swing.JMenuItem();
        funcionarios_cadastrar1 = new javax.swing.JMenuItem();
        financeiro = new javax.swing.JMenu();
        financeiro_promocoes = new javax.swing.JMenuItem();
        financeiro_gerenciar = new javax.swing.JMenuItem();
        financeiro_gerenciar1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu");
        setBackground(new java.awt.Color(153, 204, 255));
        setMaximumSize(new java.awt.Dimension(950, 513));
        setMinimumSize(new java.awt.Dimension(950, 513));
        setResizable(false);

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        btnSair.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnSair.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\close (2).png")); // NOI18N
        btnSair.setText(" Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/97783ed4-65e7-4371-906e-4ec50e65b0bd_200x200.png"))); // NOI18N

        lblHora.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        lblHora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHora.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnLancador1.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        btnLancador1.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\sino (1).png")); // NOI18N
        btnLancador1.setBorder(null);
        btnLancador1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLancador1ActionPerformed(evt);
            }
        });

        lblStringMenu1.setBackground(new java.awt.Color(0, 102, 204));
        lblStringMenu1.setFont(new java.awt.Font("Century Gothic", 1, 21)); // NOI18N
        lblStringMenu1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStringMenu1.setText("Menu");

        lblNome.setBackground(new java.awt.Color(0, 102, 204));
        lblNome.setFont(new java.awt.Font("Century Gothic", 0, 21)); // NOI18N
        lblNome.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNome.setText("Sessão Atual");

        lblFuncao.setBackground(new java.awt.Color(0, 102, 204));
        lblFuncao.setFont(new java.awt.Font("Century Gothic", 0, 21)); // NOI18N
        lblFuncao.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFuncao.setText("Sessão Atual");

        lblNome1.setBackground(new java.awt.Color(0, 102, 204));
        lblNome1.setFont(new java.awt.Font("Century Gothic", 0, 21)); // NOI18N
        lblNome1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNome1.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\chave (2).png")); // NOI18N

        lblNome2.setBackground(new java.awt.Color(0, 102, 204));
        lblNome2.setFont(new java.awt.Font("Century Gothic", 0, 21)); // NOI18N
        lblNome2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNome2.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\usuario (2).png")); // NOI18N

        pdv.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\lancador (6).png")); // NOI18N
        pdv.setText("PDV");
        pdv.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N

        pdv_caixa.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        pdv_caixa.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\caixa3.png")); // NOI18N
        pdv_caixa.setText("Caixa");
        pdv_caixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdv_caixaActionPerformed(evt);
            }
        });
        pdv.add(pdv_caixa);

        pdv_comandas.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        pdv_comandas.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\comanda4.png")); // NOI18N
        pdv_comandas.setText("Gerenciador de Comandas");
        pdv_comandas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdv_comandasActionPerformed(evt);
            }
        });
        pdv.add(pdv_comandas);

        jMenuBar1.add(pdv);

        estoque.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\estoque (3).png")); // NOI18N
        estoque.setText("Estoque  ");
        estoque.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N

        estoque_gerenciar.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        estoque_gerenciar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\entrada2.png")); // NOI18N
        estoque_gerenciar.setText("Gerenciar");
        estoque_gerenciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estoque_gerenciarActionPerformed(evt);
            }
        });
        estoque.add(estoque_gerenciar);

        estoque_movimentacao.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        estoque_movimentacao.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\movimentacao (2).png")); // NOI18N
        estoque_movimentacao.setText("Movimentação");
        estoque_movimentacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estoque_movimentacaoActionPerformed(evt);
            }
        });
        estoque.add(estoque_movimentacao);

        jMenuBar1.add(estoque);

        produtos.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\produtos icon (3).png")); // NOI18N
        produtos.setText(" Produtos ");
        produtos.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N

        produtos_gerenciar.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        produtos_gerenciar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\produtos (2).png")); // NOI18N
        produtos_gerenciar.setText("Gerenciar Produtos");
        produtos_gerenciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produtos_gerenciarActionPerformed(evt);
            }
        });
        produtos.add(produtos_gerenciar);

        produtos_cadastrar.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        produtos_cadastrar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\plus2.png")); // NOI18N
        produtos_cadastrar.setText("Cadastrar Produto");
        produtos_cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produtos_cadastrarActionPerformed(evt);
            }
        });
        produtos.add(produtos_cadastrar);

        produtos_gerenciarPratos.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        produtos_gerenciarPratos.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\menu (1).png")); // NOI18N
        produtos_gerenciarPratos.setText("Gerenciar Pratos");
        produtos_gerenciarPratos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produtos_gerenciarPratosActionPerformed(evt);
            }
        });
        produtos.add(produtos_gerenciarPratos);

        produtos_cadastrarPratos.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        produtos_cadastrarPratos.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\manage meal (1) (1).png")); // NOI18N
        produtos_cadastrarPratos.setText("Cadastrar Prato");
        produtos_cadastrarPratos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produtos_cadastrarPratosActionPerformed(evt);
            }
        });
        produtos.add(produtos_cadastrarPratos);

        produtos_cardapio.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        produtos_cardapio.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\cardapio.png")); // NOI18N
        produtos_cardapio.setText("Cardápio");
        produtos_cardapio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produtos_cardapioActionPerformed(evt);
            }
        });
        produtos.add(produtos_cardapio);

        jMenuBar1.add(produtos);

        funcionarios.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\pessoa (1) (1).png")); // NOI18N
        funcionarios.setText(" Funcionários");
        funcionarios.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N

        funcionarios_gerenciar.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        funcionarios_gerenciar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\produtos (2).png")); // NOI18N
        funcionarios_gerenciar.setText("Gerenciar");
        funcionarios_gerenciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                funcionarios_gerenciarActionPerformed(evt);
            }
        });
        funcionarios.add(funcionarios_gerenciar);

        funcionarios_cadastrar.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        funcionarios_cadastrar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\add pessoa (2).png")); // NOI18N
        funcionarios_cadastrar.setText("Cadastrar");
        funcionarios_cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                funcionarios_cadastrarActionPerformed(evt);
            }
        });
        funcionarios.add(funcionarios_cadastrar);

        jMenuBar1.add(funcionarios);

        clientes.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\negocio.png")); // NOI18N
        clientes.setText(" Clientes");
        clientes.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N

        funcionarios_gerenciar1.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        funcionarios_gerenciar1.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\produtos (2).png")); // NOI18N
        funcionarios_gerenciar1.setText("Gerenciar");
        funcionarios_gerenciar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                funcionarios_gerenciar1ActionPerformed(evt);
            }
        });
        clientes.add(funcionarios_gerenciar1);

        funcionarios_cadastrar1.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        funcionarios_cadastrar1.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\add pessoa (2).png")); // NOI18N
        funcionarios_cadastrar1.setText("Cadastrar");
        funcionarios_cadastrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                funcionarios_cadastrar1ActionPerformed(evt);
            }
        });
        clientes.add(funcionarios_cadastrar1);

        jMenuBar1.add(clientes);

        financeiro.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\moeda (1).png")); // NOI18N
        financeiro.setText(" Financeiro");
        financeiro.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N

        financeiro_promocoes.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        financeiro_promocoes.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\tag (1).png")); // NOI18N
        financeiro_promocoes.setText("Promoções");
        financeiro_promocoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                financeiro_promocoesActionPerformed(evt);
            }
        });
        financeiro.add(financeiro_promocoes);

        financeiro_gerenciar.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        financeiro_gerenciar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\big-money-bag (2).png")); // NOI18N
        financeiro_gerenciar.setText("Despesas");
        financeiro_gerenciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                financeiro_gerenciarActionPerformed(evt);
            }
        });
        financeiro.add(financeiro_gerenciar);

        financeiro_gerenciar1.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        financeiro_gerenciar1.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\relatorio (1).png")); // NOI18N
        financeiro_gerenciar1.setText("Relatórios");
        financeiro_gerenciar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                financeiro_gerenciar1ActionPerformed(evt);
            }
        });
        financeiro.add(financeiro_gerenciar1);

        jMenuBar1.add(financeiro);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 356, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFuncao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNome1)
                                    .addComponent(lblNome2)))
                            .addComponent(lblHora, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLancador1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblStringMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(331, 331, 331)
                                .addComponent(btnSair))))))
            .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStringMenu1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 106, Short.MAX_VALUE)
                        .addComponent(lblLogo)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLancador1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNome)
                            .addComponent(lblNome2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFuncao)
                            .addComponent(lblNome1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblHora, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSairActionPerformed

    private void pdv_comandasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdv_comandasActionPerformed
        new GerenciadorComandas().setVisible(true);     
        dispose();
    }//GEN-LAST:event_pdv_comandasActionPerformed

    private void estoque_gerenciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estoque_gerenciarActionPerformed
        new GerenciadorEstoque().setVisible(true);     
        dispose();
    }//GEN-LAST:event_estoque_gerenciarActionPerformed

    private void produtos_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produtos_cadastrarActionPerformed
        acaoEscolhida = 1;
        new CadastrarProduto().setVisible(true); 
        dispose();
    }//GEN-LAST:event_produtos_cadastrarActionPerformed

    private void produtos_gerenciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produtos_gerenciarActionPerformed
        new GerenciadorProdutos().setVisible(true);
        dispose();
    }//GEN-LAST:event_produtos_gerenciarActionPerformed

    private void btnLancador1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancador1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLancador1ActionPerformed

    private void funcionarios_gerenciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_funcionarios_gerenciarActionPerformed
        new GerenciadorFuncionario().setVisible(true); 
        dispose();
    }//GEN-LAST:event_funcionarios_gerenciarActionPerformed

    private void funcionarios_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_funcionarios_cadastrarActionPerformed
        new CadastrarFuncionario().setVisible(true); 
        dispose();
    }//GEN-LAST:event_funcionarios_cadastrarActionPerformed

    private void pdv_caixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdv_caixaActionPerformed
        new GerenciadorCaixa().setVisible(true); 
        dispose();
    }//GEN-LAST:event_pdv_caixaActionPerformed

    private void estoque_movimentacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estoque_movimentacaoActionPerformed
        new MovimentacaoEstoque().setVisible(true);
        dispose();
                
    }//GEN-LAST:event_estoque_movimentacaoActionPerformed

    private void financeiro_gerenciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeiro_gerenciarActionPerformed
        new CadastrarDespesa(new javax.swing.JFrame(), true).setVisible(true);
        dispose();
    }//GEN-LAST:event_financeiro_gerenciarActionPerformed

    private void financeiro_promocoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeiro_promocoesActionPerformed
        new Promocoes().setVisible(true);
        dispose();
    }//GEN-LAST:event_financeiro_promocoesActionPerformed

    private void funcionarios_gerenciar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_funcionarios_gerenciar1ActionPerformed
        new GerenciadorClientes().setVisible(true);
        dispose();
    }//GEN-LAST:event_funcionarios_gerenciar1ActionPerformed

    private void funcionarios_cadastrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_funcionarios_cadastrar1ActionPerformed
        new CadastrarCliente().setVisible(true);
        dispose();
    }//GEN-LAST:event_funcionarios_cadastrar1ActionPerformed

    private void financeiro_gerenciar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeiro_gerenciar1ActionPerformed
        new GerenciadorRelatorios().setVisible(true);
        dispose();
    }//GEN-LAST:event_financeiro_gerenciar1ActionPerformed

    private void produtos_cadastrarPratosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produtos_cadastrarPratosActionPerformed
        new CadastrarPrato().setVisible(true);
        dispose();
    }//GEN-LAST:event_produtos_cadastrarPratosActionPerformed

    private void produtos_gerenciarPratosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produtos_gerenciarPratosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_produtos_gerenciarPratosActionPerformed

    private void produtos_cardapioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produtos_cardapioActionPerformed
        new GerenciadorCardapios().setVisible(true);
        dispose();
    }//GEN-LAST:event_produtos_cardapioActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLancador1;
    private javax.swing.JButton btnSair;
    private javax.swing.JMenu clientes;
    private javax.swing.JMenu estoque;
    private javax.swing.JMenuItem estoque_gerenciar;
    private javax.swing.JMenuItem estoque_movimentacao;
    private javax.swing.JMenu financeiro;
    private javax.swing.JMenuItem financeiro_gerenciar;
    private javax.swing.JMenuItem financeiro_gerenciar1;
    private javax.swing.JMenuItem financeiro_promocoes;
    private javax.swing.JMenu funcionarios;
    private javax.swing.JMenuItem funcionarios_cadastrar;
    private javax.swing.JMenuItem funcionarios_cadastrar1;
    private javax.swing.JMenuItem funcionarios_gerenciar;
    private javax.swing.JMenuItem funcionarios_gerenciar1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblFuncao;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNome1;
    private javax.swing.JLabel lblNome2;
    private javax.swing.JLabel lblStringMenu1;
    private javax.swing.Box.Filler linha1;
    private javax.swing.JMenu pdv;
    private javax.swing.JMenuItem pdv_caixa;
    private javax.swing.JMenuItem pdv_comandas;
    private javax.swing.JMenu produtos;
    private javax.swing.JMenuItem produtos_cadastrar;
    private javax.swing.JMenuItem produtos_cadastrarPratos;
    private javax.swing.JMenuItem produtos_cardapio;
    private javax.swing.JMenuItem produtos_gerenciar;
    private javax.swing.JMenuItem produtos_gerenciarPratos;
    // End of variables declaration//GEN-END:variables
}
