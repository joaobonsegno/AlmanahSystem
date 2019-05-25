package main;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.bean.Comanda;
import model.bean.Produto;
import model.dao.ComandaDAO;

public class GerenciadorComandas extends javax.swing.JFrame {
    public static int numeroNovaComanda = 00;
    public static ArrayList<Comanda> comandasAbertas = new ArrayList<>();
    public static DecimalFormat formato = new DecimalFormat("#.##");
    public static Integer indiceSelecionado, idSelecionado;
    
    public static ArrayList<Produto> ordenarListasProduto(ArrayList<Produto> ordenador){
        int tamanho = ordenador.size();
        for (int i = 0; i < tamanho-1; i++){
            for (int j = i+1; j < tamanho; j++){
                if (ordenador.get(i).getIdSistema() > ordenador.get(j).getIdSistema()){
                    Produto x = ordenador.get(i);
                    ordenador.set(i, ordenador.get(j));
                    ordenador.set(j, x);
                }
            }
        }
        return ordenador;
    }
    
    public static String tornarCompativel(String valor){
        boolean flag = true;
        valor = new StringBuilder(valor).reverse().toString();
        StringBuilder builder = new StringBuilder();

        for (int i=0; i<valor.length(); i++) {
            Character c = valor.charAt(i);
            String str = Character.toString(c);
            if (str.equals(",")|str.equals(".")){
                flag = false;
                builder = new StringBuilder(valor);
                if(i==1){
                    String ponto = ".";
                    String decimal = builder.substring(0, i);
                    String mantissa = builder.substring(i+1, valor.length());
                    StringBuilder mantissaBuilder = new StringBuilder(mantissa).reverse();
                    mantissa = mantissaBuilder.toString();
                    System.out.println("Mantissa: "+mantissaBuilder);
                    StringBuilder finalBuilder = new StringBuilder(mantissa);
                    finalBuilder.append(ponto);
                    finalBuilder.append(decimal);
                    finalBuilder.append("0");   
                    String retorno = finalBuilder.toString();
                    return retorno;
                }else{
                    String ponto = ".";
                    String decimal = builder.substring(0, i);
                    String mantissa = builder.substring(i+1, valor.length());
                    StringBuilder mantissaBuilder = new StringBuilder(mantissa).reverse();
                    mantissa = mantissaBuilder.toString();
                    StringBuilder decimalBuilder = new StringBuilder(decimal).reverse();
                    decimal = decimalBuilder.toString();
                    StringBuilder finalBuilder = new StringBuilder(mantissa);
                    finalBuilder.append(ponto);
                    finalBuilder.append(decimal);   
                    String retorno = finalBuilder.toString();
                    return retorno;
                }
            }
        }
        if (flag){
            StringBuilder semPonto = new StringBuilder(valor).reverse();
            semPonto.append(".00");
            String retorno = semPonto.toString();
            return retorno;
        }
        String retorno = builder.toString();
        return retorno;
    }
    
    public static String valorMonetario(Double preco){
        /*
        OUTRA MANEIRA:
        DecimalFormat df = new DecimalFormat("#.00");
        String teste = df.format(novoValor);
        */
        String valorString = String.format("%.2f", preco);
        return valorString;
    }

    public static String arredondarValor(String valor){
        Integer flag = 0;
        StringBuilder builderValor = new StringBuilder(valor);
        try{
            if(valor.endsWith("1")|valor.endsWith("2")){
                //Verifica se a String termina com 1 ou 2, para arredondar para baixo
                String modificada = valor.substring(0, valor.length()-1);
                builderValor = new StringBuilder(modificada);
                builderValor.append("0");
            }else if(valor.endsWith("3")|valor.endsWith("4")|valor.endsWith("6")|valor.endsWith("7")){
                //Verifica se a String termina com 3, 4, 6 ou 7 para arredondar para baixo (5)
                String modificada = valor.substring(0, valor.length()-1);
                builderValor = new StringBuilder(modificada);
                builderValor.append("5");
            }else if(valor.endsWith("8")|valor.endsWith("9")){
                //Verifica se a String termina com 8 ou 9, para arredondar para cima
                int j = valor.length();
                j -= 2;
                //Pega x.Xx
                Character penultimo = valor.charAt(j);
                String t = Character.toString(penultimo);
                Integer inteiro = Integer.parseInt(t);
                if(inteiro==9){
                    //Valor x.98 ou x.99
                    j = valor.length();
                    j -= 4;
                    //Pega X.xx
                    penultimo = valor.charAt(j);
                    t = Character.toString(penultimo);
                    inteiro = Integer.parseInt(t);
                    if(inteiro==9){
                        //Valor x9.98 ou x9.99
                        flag = 1;
                        j = valor.length();
                        j -= 5;
                        //Pega Xx.xx
                        penultimo = valor.charAt(j);
                        t = Character.toString(penultimo);
                        inteiro = Integer.parseInt(t);
                        if(inteiro==9){
                            //Valor 99.98 ou 99.99
                            flag = 2;
                            j = valor.length();
                            j -= 6;
                            //Pega Xxx.xx
                            penultimo = valor.charAt(j);
                            t = Character.toString(penultimo);
                            inteiro = Integer.parseInt(t);
                            if(inteiro==9){
                                //Valor 999.98 ou 999.99
                                flag = 3;
                                j = valor.length();
                                j -= 7;
                                //Pega Xxxx.xx
                                penultimo = valor.charAt(j);
                                t = Character.toString(penultimo);
                                inteiro = Integer.parseInt(t);
                                if(inteiro==9){
                                    //Valor 9999.98 ou 9999.99
                                    flag = 4;
                                    j = valor.length();
                                    j -= 8;
                                    //Pega Xxxx.xx
                                    penultimo = valor.charAt(j);
                                    t = Character.toString(penultimo);
                                    inteiro = Integer.parseInt(t);
                                    if(inteiro==9){
                                        //Valor 99999.98 ou 99999.99
                                        flag = 5;
                                        j = valor.length();
                                        j -= 9;
                                        //Pega Xxxxxx.xx
                                        penultimo = valor.charAt(j);
                                        t = Character.toString(penultimo);
                                        inteiro = Integer.parseInt(t);
                                    }else{
                                        //Valor x79999.98 ou x79999.99, por exemplo
                                        inteiro += 1;
                                        t = Integer.toString(inteiro);
                                        String modificada = valor.substring(0, valor.length()-8);
                                        builderValor = new StringBuilder(modificada);
                                        builderValor.append(t);
                                        builderValor.append("0000.00");
                                    }
                                }else{
                                    //Valor x7999.98 ou x7999.99, por exemplo
                                    inteiro += 1;
                                    t = Integer.toString(inteiro);
                                    String modificada = valor.substring(0, valor.length()-7);
                                    builderValor = new StringBuilder(modificada);
                                    builderValor.append(t);
                                    builderValor.append("000.00");
                                }
                            }else{
                                //Valor x799.98 ou x799.99, por exemplo
                                inteiro += 1;
                                t = Integer.toString(inteiro);
                                String modificada = valor.substring(0, valor.length()-6);
                                builderValor = new StringBuilder(modificada);
                                builderValor.append(t);
                                builderValor.append("00.00");
                            }
                        }else{
                            //Valor 799.98 ou 799.99, por exemplo
                            inteiro += 1;
                            t = Integer.toString(inteiro);
                            String modificada = valor.substring(0, valor.length()-5);
                            builderValor = new StringBuilder(modificada);
                            builderValor.append(t);
                            builderValor.append("0.00");
                        }
                    }else{
                        //Valor x7.98 ou x7.99, por exemplo
                        inteiro += 1;
                        t = Integer.toString(inteiro);
                        String modificada = valor.substring(0, valor.length()-4);
                        builderValor = new StringBuilder(modificada);
                        builderValor.append(t);
                        builderValor.append(".00");
                    }
                }else{
                    //Valor x.79, por exemplo
                    inteiro += 1;
                    t = Integer.toString(inteiro);
                    String modificada = valor.substring(0, valor.length()-2);
                    builderValor = new StringBuilder(modificada);
                    builderValor.append(t);
                    builderValor.append("0");
                }
            }
        }catch(java.lang.IndexOutOfBoundsException ex){
            System.out.println(ex);
            if (flag == 1){
                String modificada = "";
                builderValor = new StringBuilder(modificada);
                builderValor.append("10.00");
            }else if (flag == 2){
                String modificada = "";
                builderValor = new StringBuilder(modificada);
                builderValor.append("100.00");
            }else if (flag == 3){
                String modificada = "";
                builderValor = new StringBuilder(modificada);
                builderValor.append("1000.00");
            }else if (flag == 4){
                String modificada = "";
                builderValor = new StringBuilder(modificada);
                builderValor.append("10000.00");
            }else if (flag == 5){
                String modificada = "";
                builderValor = new StringBuilder(modificada);
                builderValor.append("100000.00");
            }
        }finally{
            String retorno = builderValor.toString();
            return retorno;
        }
    }
    
    public static void novaComanda(){ 
        
        ComandaDAO comandaDao = new ComandaDAO();
        DefaultTableModel dtmComandas = (DefaultTableModel) jtComandas.getModel();
        Comanda comanda = new Comanda(numeroNovaComanda);  
        String valor = valorMonetario(comanda.getValor());
        Object[] dados = {comanda.getId(), valor};
        dtmComandas.addRow(dados);
        
        comandaDao.create(comanda);
        for(Comanda c:comandaDao.read()){
            if(c.getId()==numeroNovaComanda){
                comanda.setIdBanco(c.getIdBanco());
            }
        }
        GerenciadorComandas.comandasAbertas.add(comanda);
        ordenarComandas();
    }
    
    public static ArrayList<Integer> ordenarListas(ArrayList<Integer> ordenador){
        int tamanho = ordenador.size();
        for (int i = 0; i < tamanho-1; i++){
            for (int j = i+1; j < tamanho; j++){
                if (ordenador.get(i) > ordenador.get(j)){
                    int x = ordenador.get(i);
                    ordenador.set(i, ordenador.get(j));
                    ordenador.set(j, x);
                }
            }
        }
        return ordenador;
    }
 
    public static void ordenarComandas(){  
        ArrayList<Integer> ordenador = new ArrayList<>();
        
        for (Comanda numero : comandasAbertas){
            ordenador.add(numero.getId());
        }

        cbEscolherComanda.removeAllItems();
        cbEscolherComanda.addItem("");
        ordenador = ordenarListas(ordenador);
        for (Integer numero : ordenador){
            String numNovaComanda = Integer.toString(numero);
            cbEscolherComanda.addItem(numNovaComanda);
        }  
    }
    
    public static boolean existeComanda(){
        int quantidade = comandasAbertas.size();
        if (quantidade > 0){
            return true;
        }
        else{
            lblErro.setText("Não há comandas abertas");
            lblErro.setVisible(true);
            return false;
        }
    }

    public static boolean verificaIndice(){
        int escolhido = cbEscolherComanda.getSelectedIndex();
        if (escolhido > 0){
            return true;
        }
        else{
            lblErro.setText("Selecione uma comanda!");
            lblErro.setVisible(true);
            return false;
        }
    }
    
    public static void novaInstancia(){
        DefaultTableModel dtmComandas = (DefaultTableModel) jtComandas.getModel();
        
        for(Comanda c : comandasAbertas){
            String valor = valorMonetario(c.getValor());
            Object[] dados = {c.getId(), valor};
            dtmComandas.addRow(dados);
        }
        ordenarComandas();
    }
    
    public GerenciadorComandas() {
        initComponents();
        DefaultTableModel dtmComandas = (DefaultTableModel) jtComandas.getModel();
        jtComandas.getColumnModel().getColumn(0).setPreferredWidth(80); 
        jtComandas.getColumnModel().getColumn(1).setPreferredWidth(180);
        jtComandas.getColumnModel().getColumn(0).setMinWidth(80);
        jtComandas.getColumnModel().getColumn(1).setMinWidth(180);
        jtComandas.getColumnModel().getColumn(0).setMaxWidth(80);
        jtComandas.getColumnModel().getColumn(1).setMaxWidth(180);
        cbEscolherComanda.removeAllItems();
        novaInstancia();
        lblErro.setVisible(false);
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStringGerenciador = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        linha2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnLancador = new javax.swing.JButton();
        lblStringUsuario = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblStringFuncao = new javax.swing.JLabel();
        lblFuncao = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtComandas = new javax.swing.JTable();
        cbEscolherComanda = new javax.swing.JComboBox<>();
        lblStringSelecione = new javax.swing.JLabel();
        btnNovaComanda = new javax.swing.JButton();
        btnBebida = new javax.swing.JButton();
        btnSobremesa = new javax.swing.JButton();
        btnEncerrar = new javax.swing.JButton();
        lblErro = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(1, 1), new java.awt.Dimension(1, 1), new java.awt.Dimension(1, 1));
        btnBebida1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciador de Comandas");
        setMaximumSize(new java.awt.Dimension(1130, 570));
        setMinimumSize(new java.awt.Dimension(1130, 570));
        setResizable(false);

        btnStringGerenciador.setBackground(new java.awt.Color(0, 102, 204));
        btnStringGerenciador.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        btnStringGerenciador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringGerenciador.setText("Gerenciador de Comandas");

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        linha2.setBackground(new java.awt.Color(0, 0, 0));
        linha2.setOpaque(true);

        btnLancador.setFont(new java.awt.Font("Comic Sans MS", 0, 17)); // NOI18N
        btnLancador.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\voltar (1).png")); // NOI18N
        btnLancador.setText("Lançador");
        btnLancador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLancadorActionPerformed(evt);
            }
        });

        lblStringUsuario.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblStringUsuario.setText("Usuário:");

        lblUsuario.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblUsuario.setText("Joao");

        lblStringFuncao.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblStringFuncao.setText("Função:");

        lblFuncao.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblFuncao.setText("Gerente");

        jtComandas.setBorder(new javax.swing.border.MatteBorder(null));
        jtComandas.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        jtComandas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Total (R$)"
            }
        ));
        jScrollPane1.setViewportView(jtComandas);
        if (jtComandas.getColumnModel().getColumnCount() > 0) {
            jtComandas.getColumnModel().getColumn(0).setResizable(false);
            jtComandas.getColumnModel().getColumn(1).setResizable(false);
        }

        cbEscolherComanda.setFont(new java.awt.Font("Comic Sans MS", 0, 22)); // NOI18N
        cbEscolherComanda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbEscolherComanda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbEscolherComandaItemStateChanged(evt);
            }
        });

        lblStringSelecione.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        lblStringSelecione.setText("Selecione a comanda:");

        btnNovaComanda.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        btnNovaComanda.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\add (1).png")); // NOI18N
        btnNovaComanda.setText("   Nova Comanda");
        btnNovaComanda.setBorder(new javax.swing.border.MatteBorder(null));
        btnNovaComanda.setBorderPainted(false);
        btnNovaComanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovaComandaActionPerformed(evt);
            }
        });

        btnBebida.setBackground(new java.awt.Color(0, 153, 153));
        btnBebida.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        btnBebida.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\bebida2.png")); // NOI18N
        btnBebida.setText("  Bebida");
        btnBebida.setBorder(new javax.swing.border.MatteBorder(null));
        btnBebida.setBorderPainted(false);
        btnBebida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBebidaActionPerformed(evt);
            }
        });

        btnSobremesa.setBackground(new java.awt.Color(0, 153, 153));
        btnSobremesa.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        btnSobremesa.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\93093.png")); // NOI18N
        btnSobremesa.setText("Sobremesa");
        btnSobremesa.setBorder(new javax.swing.border.MatteBorder(null));
        btnSobremesa.setBorderPainted(false);
        btnSobremesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSobremesaActionPerformed(evt);
            }
        });

        btnEncerrar.setBackground(new java.awt.Color(0, 153, 0));
        btnEncerrar.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        btnEncerrar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\encerrar (1).png")); // NOI18N
        btnEncerrar.setText("  Encerrar");
        btnEncerrar.setBorder(new javax.swing.border.MatteBorder(null));
        btnEncerrar.setBorderPainted(false);
        btnEncerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncerrarActionPerformed(evt);
            }
        });

        lblErro.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        lblErro.setForeground(new java.awt.Color(255, 0, 0));
        lblErro.setText("Selecione uma comanda!");

        filler1.setBackground(new java.awt.Color(0, 0, 0));
        filler1.setOpaque(true);

        btnBebida1.setBackground(new java.awt.Color(0, 153, 153));
        btnBebida1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        btnBebida1.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\refeicao (2).png")); // NOI18N
        btnBebida1.setText(" Refeição");
        btnBebida1.setBorder(new javax.swing.border.MatteBorder(null));
        btnBebida1.setBorderPainted(false);
        btnBebida1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBebida1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnStringGerenciador, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(415, 415, 415))
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnLancador)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(btnNovaComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblErro)
                        .addGap(182, 182, 182))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblStringSelecione)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbEscolherComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblStringUsuario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(lblStringFuncao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBebida1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(btnBebida, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(btnSobremesa, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(56, 56, 56))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnEncerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(231, 231, 231))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, 1137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha2, javax.swing.GroupLayout.DEFAULT_SIZE, 1137, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(497, 497, 497)
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(639, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblStringUsuario)
                        .addComponent(lblUsuario)
                        .addComponent(lblStringFuncao)
                        .addComponent(lblFuncao))
                    .addComponent(btnLancador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnStringGerenciador)
                        .addGap(110, 110, 110)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStringSelecione)
                            .addComponent(cbEscolherComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addComponent(lblErro)
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSobremesa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBebida, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBebida1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(btnEncerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(btnNovaComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(110, 110, 110)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(456, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(524, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(120, 120, 120)
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLancadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancadorActionPerformed
        new Lancador().setVisible(true); 
        dispose();
    }//GEN-LAST:event_btnLancadorActionPerformed

    private void btnNovaComandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaComandaActionPerformed
        numeroNovaComanda += 1;
        NovaComanda novaComanda = new NovaComanda(new javax.swing.JFrame(), true);
        novaComanda.setVisible(true); 
    }//GEN-LAST:event_btnNovaComandaActionPerformed

    private void btnBebidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBebidaActionPerformed
        try{
            indiceSelecionado = cbEscolherComanda.getSelectedIndex();
            Object i = cbEscolherComanda.getSelectedItem();
            String i2 = (String)i;
            idSelecionado = Integer.parseInt(i2);
            if (existeComanda() && verificaIndice()){
                new NovaBebida().setVisible(true); 
                dispose();
            }
        }catch(java.lang.NumberFormatException ex){
            lblErro.setVisible(true);
        }
    }//GEN-LAST:event_btnBebidaActionPerformed

    private void btnSobremesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSobremesaActionPerformed
        try{
            indiceSelecionado = cbEscolherComanda.getSelectedIndex();
            Object i = cbEscolherComanda.getSelectedItem();
            String i2 = (String)i;
            idSelecionado = Integer.parseInt(i2);
            if (existeComanda() && verificaIndice()){
                new NovaSobremesa().setVisible(true); 
                dispose();
            }
        }catch(java.lang.NumberFormatException ex){
            lblErro.setVisible(true);
        }
    }//GEN-LAST:event_btnSobremesaActionPerformed

    private void btnEncerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncerrarActionPerformed
        /*Object ii = cbEscolherComanda.getSelectedItem();
        String i2 = (String)ii;
        idSelecionado = Integer.parseInt(i2);
        try{
            DefaultTableModel dtmComandas = (DefaultTableModel) jtComandas.getModel();
            if (verificaIndice()){
                int i = cbEscolherComanda.getSelectedIndex();
                i -= 1;
                System.out.println("Posição do Elemento: "+i);
                dtmComandas.removeRow(i);
                comandasAbertas.remove(i);
                ordenarComandas();  
            }
        }catch(java.lang.IndexOutOfBoundsException ex){
            System.out.println("Lista vazia!");
        }*/
        try{
            Object i = cbEscolherComanda.getSelectedItem();
            String i2 = (String)i;
            idSelecionado = Integer.parseInt(i2);
            for(Comanda c:comandasAbertas){
                if(c.getId() == idSelecionado){
                    if(c.getValor() == 0){
                        ComandaDAO comandaDao = new ComandaDAO();
                        DefaultTableModel dtmComandas = (DefaultTableModel) jtComandas.getModel();
                        int indice = cbEscolherComanda.getSelectedIndex();
                        indice -= 1;
                        System.out.println("Posição do Elemento: "+i);
                        dtmComandas.removeRow(indice);
                        comandasAbertas.remove(indice);
                        ordenarComandas();  
                        c.setStatus(0);
                        comandaDao.update(c);
                    }else{
                        new EncerrarComanda().setVisible(true); 
                        dispose();
                    }
                }
            }    
        }catch(java.lang.NumberFormatException|java.lang.NullPointerException ex){
            System.out.println(ex);
            /*System.out.println(ex);
            Object ii = cbEscolherComanda.getSelectedItem();
            String i2 = (String)ii;
            idSelecionado = Integer.parseInt(i2);
            try{
                DefaultTableModel dtmComandas = (DefaultTableModel) jtComandas.getModel();
                if (verificaIndice()){
                    int i = cbEscolherComanda.getSelectedIndex();
                    i -= 1;
                    System.out.println("Posição do Elemento: "+i);
                    dtmComandas.removeRow(i);
                    comandasAbertas.remove(i);
                    ordenarComandas();  
                }
            }catch(java.lang.IndexOutOfBoundsException ex1){
                System.out.println("Lista vazia!");
            }*/
            lblErro.setVisible(true);
        }
    }//GEN-LAST:event_btnEncerrarActionPerformed

    private void cbEscolherComandaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbEscolherComandaItemStateChanged
        int escolhido = cbEscolherComanda.getSelectedIndex();
        if (escolhido > 0){
            lblErro.setVisible(false);
        }
        
    }//GEN-LAST:event_cbEscolherComandaItemStateChanged

    private void btnBebida1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBebida1ActionPerformed
        try{
            indiceSelecionado = cbEscolherComanda.getSelectedIndex();
            Object i = cbEscolherComanda.getSelectedItem();
            String i2 = (String)i;
            idSelecionado = Integer.parseInt(i2);
            if (existeComanda() && verificaIndice()){
                NovoPrato novoPrato = new NovoPrato(new javax.swing.JFrame(), true);
                novoPrato.setVisible(true); 
                dispose();
            }
        }catch(java.lang.NumberFormatException ex){
            lblErro.setVisible(true);
        }
    }//GEN-LAST:event_btnBebida1ActionPerformed

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
            java.util.logging.Logger.getLogger(GerenciadorComandas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciadorComandas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciadorComandas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciadorComandas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerenciadorComandas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBebida;
    private javax.swing.JButton btnBebida1;
    private javax.swing.JButton btnEncerrar;
    private javax.swing.JButton btnLancador;
    private javax.swing.JButton btnNovaComanda;
    private javax.swing.JButton btnSobremesa;
    private javax.swing.JLabel btnStringGerenciador;
    private static javax.swing.JComboBox<String> cbEscolherComanda;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtComandas;
    public static javax.swing.JLabel lblErro;
    private javax.swing.JLabel lblFuncao;
    private javax.swing.JLabel lblStringFuncao;
    private javax.swing.JLabel lblStringSelecione;
    private javax.swing.JLabel lblStringUsuario;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    // End of variables declaration//GEN-END:variables
}


