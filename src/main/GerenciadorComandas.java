package main;

import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;
import model.bean.Comanda;
import model.bean.Produto;
import model.dao.ComandaDAO;

public class GerenciadorComandas extends javax.swing.JFrame {
    public static ArrayList<Comanda> comandasAbertas = new ArrayList<>();
    public static int numeroNovaComanda = 00;
    public static DecimalFormat formato = new DecimalFormat("#.##");
    public static Integer indiceSelecionado, idSelecionado;
    
    
    
    public GerenciadorComandas() {
        initComponents();
        jtComandas.setRowHeight(25);
        DefaultTableModel dtmComandas = (DefaultTableModel) jtComandas.getModel();
        jtComandas.getColumnModel().getColumn(0).setPreferredWidth(80); 
        jtComandas.getColumnModel().getColumn(1).setPreferredWidth(180);
        jtComandas.getColumnModel().getColumn(0).setMinWidth(80);
        jtComandas.getColumnModel().getColumn(1).setMinWidth(180);
        jtComandas.getColumnModel().getColumn(0).setMaxWidth(80);
        jtComandas.getColumnModel().getColumn(1).setMaxWidth(180);
        this.novaInstancia();
        lblErro.setVisible(false);
        this.setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnNovaComanda);
        jtComandas.getTableHeader().setFont(new Font("Century Gothic", 1, 14));
        if (Login.funcAtual.getCargo().getId() == 1){
            btnEncerrar.setEnabled(false);
        }
    }

    public void limparTabela(){
        DefaultTableModel dtmComandas = (DefaultTableModel) jtComandas.getModel();
        int i = jtComandas.getRowCount();
        
        for (int j = 0; j < i; j++){
            dtmComandas.removeRow(0);
        }       
    }
    
    public static ArrayList<Produto> ordenarListasProduto(ArrayList<Produto> ordenador){
        int tamanho = ordenador.size();
        for (int i = 0; i < tamanho-1; i++){
            for (int j = i+1; j < tamanho; j++){
                if (ordenador.get(i).getIdProduto() > ordenador.get(j).getIdProduto()){
                    Produto x = ordenador.get(i);
                    ordenador.set(i, ordenador.get(j));
                    ordenador.set(j, x);
                }
            }
        }
        return ordenador;
    }
    
    public static String getDataAtualFormatoUSA(){
        Calendar data = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dataFormatada = sdf.format(data.getTime());
        return dataFormatada;
    }
    
    public static String getDataAtualComHoraFormatoBr(){
        Calendar data = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = sdf.format(data.getTime());
        return dataFormatada;
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
//        ordenarComandas();
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
    
    public void novaInstancia(){
        limparTabela();
        DefaultTableModel dtmComandas = (DefaultTableModel) jtComandas.getModel();
        
        for(Comanda c : comandasAbertas){
            String valor = valorMonetario(c.getValor());
            Object[] dados = {c.getId(), valor};
            dtmComandas.addRow(dados);
        }
        //ordenarComandas();
    }
    
    public int retornaIndice(){
        int contador = 0;
        for(Comanda c:comandasAbertas){
            if(c.getId() == idSelecionado){
                break;  
            }
            contador += 1;
        }
        return contador;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStringGerenciador = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnLancador = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtComandas = new javax.swing.JTable();
        btnNovaComanda = new javax.swing.JButton();
        btnBebida = new javax.swing.JButton();
        btnSobremesa = new javax.swing.JButton();
        btnEncerrar = new javax.swing.JButton();
        lblErro = new javax.swing.JLabel();
        btnRefeicao = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciador de Comandas");
        setMaximumSize(new java.awt.Dimension(791, 501));
        setMinimumSize(new java.awt.Dimension(791, 501));
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        btnStringGerenciador.setBackground(new java.awt.Color(0, 102, 204));
        btnStringGerenciador.setFont(new java.awt.Font("Century Gothic", 0, 21)); // NOI18N
        btnStringGerenciador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringGerenciador.setText("Gerenciador de Comandas");

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

        jtComandas.setBorder(new javax.swing.border.MatteBorder(null));
        jtComandas.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jtComandas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Total (R$)"
            }
        ));
        jtComandas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtComandasFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jtComandas);
        if (jtComandas.getColumnModel().getColumnCount() > 0) {
            jtComandas.getColumnModel().getColumn(0).setResizable(false);
            jtComandas.getColumnModel().getColumn(1).setResizable(false);
        }

        btnNovaComanda.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnNovaComanda.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\add (1).png")); // NOI18N
        btnNovaComanda.setText(" Nova Comanda");
        btnNovaComanda.setBorder(new javax.swing.border.MatteBorder(null));
        btnNovaComanda.setBorderPainted(false);
        btnNovaComanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovaComandaActionPerformed(evt);
            }
        });

        btnBebida.setBackground(new java.awt.Color(0, 153, 153));
        btnBebida.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
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
        btnSobremesa.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
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
        btnEncerrar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnEncerrar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\encerrar (1).png")); // NOI18N
        btnEncerrar.setText("  Encerrar");
        btnEncerrar.setBorder(new javax.swing.border.MatteBorder(null));
        btnEncerrar.setBorderPainted(false);
        btnEncerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncerrarActionPerformed(evt);
            }
        });

        lblErro.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        lblErro.setForeground(new java.awt.Color(255, 0, 0));
        lblErro.setText("Selecione uma comanda!");

        btnRefeicao.setBackground(new java.awt.Color(0, 153, 153));
        btnRefeicao.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnRefeicao.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\refeicao (2).png")); // NOI18N
        btnRefeicao.setText(" Refeição");
        btnRefeicao.setBorder(new javax.swing.border.MatteBorder(null));
        btnRefeicao.setBorderPainted(false);
        btnRefeicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefeicaoActionPerformed(evt);
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
                        .addGap(135, 135, 135)
                        .addComponent(btnStringGerenciador))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSobremesa, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBebida, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRefeicao, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addComponent(btnEncerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(btnNovaComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(lblErro)))
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnStringGerenciador))
                    .addComponent(btnLancador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNovaComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(lblErro))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(btnRefeicao, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(btnBebida, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(btnEncerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addComponent(btnSobremesa, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(52, 52, 52)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(448, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLancadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancadorActionPerformed
        new Menu().setVisible(true); 
        dispose();
    }//GEN-LAST:event_btnLancadorActionPerformed

    private void btnNovaComandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaComandaActionPerformed
        numeroNovaComanda += 1;
        NovaComanda novaComanda = new NovaComanda(new javax.swing.JFrame(), true);
        novaComanda.setVisible(true); 
    }//GEN-LAST:event_btnNovaComandaActionPerformed

    private void btnBebidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBebidaActionPerformed
        try{
            idSelecionado = (Integer)jtComandas.getValueAt(jtComandas.getSelectedRow(), 0);
            indiceSelecionado = retornaIndice()+1;
            if (existeComanda()){
                new NovaBebida().setVisible(true); 
                //dispose();
            }
        }catch(java.lang.ArrayIndexOutOfBoundsException ex){
            lblErro.setVisible(true);
        }
    }//GEN-LAST:event_btnBebidaActionPerformed

    private void btnSobremesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSobremesaActionPerformed
        try{
            idSelecionado = (Integer)jtComandas.getValueAt(jtComandas.getSelectedRow(), 0);
            indiceSelecionado = retornaIndice()+1;
            if (existeComanda()){
                new NovaSobremesa().setVisible(true); 
              //  dispose();
            }
        }catch(java.lang.ArrayIndexOutOfBoundsException ex){
            lblErro.setVisible(true);
        }
    }//GEN-LAST:event_btnSobremesaActionPerformed

    private void btnEncerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncerrarActionPerformed
        try{
            idSelecionado = (Integer)jtComandas.getValueAt(jtComandas.getSelectedRow(), 0);
            indiceSelecionado = retornaIndice();
            for(Comanda c:comandasAbertas){
                if(c.getId() == idSelecionado){
                    if(c.getValor() == 0){
                        ComandaDAO comandaDao = new ComandaDAO();
                        DefaultTableModel dtmComandas = (DefaultTableModel) jtComandas.getModel();
                        int indice = retornaIndice();
                        dtmComandas.removeRow(indice);
                        comandasAbertas.remove(indice);
                        //ordenarComandas();  
                        c.setStatus(0);
                        comandaDao.update(c);
                    }else{
                        new EncerrarComanda().setVisible(true); 
                        dispose();
                    }
                }
            }    
        }catch(java.lang.NumberFormatException|java.lang.NullPointerException ex){
            lblErro.setVisible(true);
        }
    }//GEN-LAST:event_btnEncerrarActionPerformed

    private void btnRefeicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefeicaoActionPerformed
        try{
            idSelecionado = (Integer)jtComandas.getValueAt(jtComandas.getSelectedRow(), 0);
            indiceSelecionado = retornaIndice()+1;
            /*Object i = cbEscolherComanda.getSelectedItem();
            String i2 = (String)i;
            idSelecionado = Integer.parseInt(i2);*/
            if (existeComanda()){
                NovaRefeicao novoPrato = new NovaRefeicao(new javax.swing.JFrame(), true);
                novoPrato.setVisible(true); 
               // dispose();
            }
        }catch(java.lang.ArrayIndexOutOfBoundsException ex){
            lblErro.setVisible(true);
        }
    }//GEN-LAST:event_btnRefeicaoActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        novaInstancia();
    }//GEN-LAST:event_formWindowGainedFocus

    private void jtComandasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtComandasFocusGained
        lblErro.setVisible(false);
    }//GEN-LAST:event_jtComandasFocusGained

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
    private javax.swing.JButton btnEncerrar;
    private javax.swing.JButton btnLancador;
    private javax.swing.JButton btnNovaComanda;
    private javax.swing.JButton btnRefeicao;
    private javax.swing.JButton btnSobremesa;
    private javax.swing.JLabel btnStringGerenciador;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtComandas;
    public static javax.swing.JLabel lblErro;
    private javax.swing.Box.Filler linha1;
    // End of variables declaration//GEN-END:variables
}

/*public static void ordenarComandas(){  
        ArrayList<Integer> ordenador = new ArrayList<>();
        
        for (Comanda numero : comandasAbertas){
            ordenador.add(numero.getId());
        }

        ordenador = ordenarListas(ordenador);
        for (Integer numero : ordenador){
            String numNovaComanda = Integer.toString(numero);
        }  
    }*/
