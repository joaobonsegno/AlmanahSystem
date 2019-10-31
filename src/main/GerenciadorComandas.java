package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.bean.Comanda;
import model.bean.Produto;
import model.dao.ComandaDAO;

public class GerenciadorComandas extends javax.swing.JFrame {
    public static ArrayList<Comanda> comandasAbertas = new ArrayList<>();
    public static DecimalFormat formato = new DecimalFormat("#.##");
    public static Integer indiceSelecionado, idSelecionado;
    public static ArrayList<Integer> idsAbertos = new ArrayList<>();
    
    public GerenciadorComandas() {
        initComponents();
        Timer timer = new Timer(1000, new GerenciadorComandas.ClockListener());
        timer.start();
        this.formatarTabela();
        this.novaInstancia();
        this.setLocationRelativeTo(null);
        
        
        if (Login.funcAtual.getCargo().getId() == 1){
            btnEncerrar.setEnabled(false);
        }
        
        txtNumeroComanda.requestFocus();      
    }

    public void formatarTabela(){
        jtComandas.setRowHeight(28);
        jtComandas.getColumn("ID").setCellRenderer(centro);
        jtComandas.getColumn("Total (R$)").setCellRenderer(centro);
        jtComandas.getColumn("Data").setCellRenderer(centro);
        jtComandas.getTableHeader().setFont(new Font("Century Gothic", 1, 14));
        jtComandas.getColumnModel().getColumn(0).setPreferredWidth(60); 
        jtComandas.getColumnModel().getColumn(1).setPreferredWidth(120);
        jtComandas.getColumnModel().getColumn(2).setPreferredWidth(190);
        jtComandas.getColumnModel().getColumn(0).setMinWidth(60);
        jtComandas.getColumnModel().getColumn(1).setMinWidth(120);
        jtComandas.getColumnModel().getColumn(2).setMinWidth(190);
        jtComandas.getColumnModel().getColumn(0).setMaxWidth(60);
        jtComandas.getColumnModel().getColumn(1).setMaxWidth(120);
        jtComandas.getColumnModel().getColumn(2).setMaxWidth(190);
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy   HH:mm:ss");
        String dataFormatada = sdf.format(data.getTime());
        return dataFormatada;
    }
    
    public static String getDataAtualSemHoraFormatoBr(){
        Calendar data = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
    
    public static void novaComanda(int cod){ 
        
        ComandaDAO comandaDao = new ComandaDAO();
        DefaultTableModel dtmComandas = (DefaultTableModel) jtComandas.getModel();
        Comanda comanda = new Comanda(cod);  
        String valor = valorMonetario(comanda.getValor());
        comanda.setData(getDataAtualComHoraFormatoBr());
        Object[] dados = {comanda.getId(), valor, comanda.getData()};
        dtmComandas.addRow(dados);
        
        comandaDao.create(comanda);
        for(Comanda c:comandaDao.read()){
            if(c.getId()==cod){
                comanda.setIdBanco(c.getIdBanco());
            }
        }
        GerenciadorComandas.comandasAbertas.add(comanda);
        idsAbertos.add(comanda.getId());
        //ordenarComandas();
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
            return false;
        }
    }
    
    public void novaInstancia(){
        limparTabela();  
        DefaultTableModel dtmComandas = (DefaultTableModel) jtComandas.getModel();
        idsAbertos.removeAll(idsAbertos);
        for(Comanda c : comandasAbertas){
            String valor = valorMonetario(c.getValor());
            Object[] dados = {c.getId(), valor, c.getData()};
            dtmComandas.addRow(dados);
            idsAbertos.add(c.getId());
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
    
    public static int retornaIndice(int id){
        int contador = 0;
        for(Comanda c:comandasAbertas){
            if(c.getId() == id){
                break;  
            }
            contador += 1;
        }
        return contador;
    }
    
    // MÉTODOS PARA ARRUMAR CÉLULAS DA TABELA
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
    
    // MÉTODO PARA RELÓGIO NA TELA
    class ClockListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Calendar now = Calendar.getInstance();
            lblHora.setText(String.format("%1$tH:%1$tM:%1$tS", now));
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStringGerenciador = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnLancador = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtComandas = new javax.swing.JTable();
        btnEncerrar = new javax.swing.JButton();
        lblHora = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtNumeroComanda = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        linha2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnRefeicao = new javax.swing.JButton();
        btnBebida = new javax.swing.JButton();
        btnSobremesa = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciador de Comandas");
        setMaximumSize(new java.awt.Dimension(779, 528));
        setMinimumSize(new java.awt.Dimension(779, 528));
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
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

        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jtComandas.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jtComandas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Total (R$)", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtComandas.getTableHeader().setReorderingAllowed(false);
        jtComandas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtComandasFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jtComandas);
        if (jtComandas.getColumnModel().getColumnCount() > 0) {
            jtComandas.getColumnModel().getColumn(0).setResizable(false);
            jtComandas.getColumnModel().getColumn(1).setResizable(false);
            jtComandas.getColumnModel().getColumn(2).setResizable(false);
        }

        btnEncerrar.setBackground(new java.awt.Color(0, 102, 0));
        btnEncerrar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnEncerrar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\encerrar (1).png")); // NOI18N
        btnEncerrar.setText(" Encerrar Comandas");
        btnEncerrar.setBorder(new javax.swing.border.MatteBorder(null));
        btnEncerrar.setBorderPainted(false);
        btnEncerrar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnEncerrarFocusGained(evt);
            }
        });
        btnEncerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncerrarActionPerformed(evt);
            }
        });

        lblHora.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        lblHora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHora.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Comandas Abertas");

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        txtNumeroComanda.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNumeroComanda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNumeroComandaFocusGained(evt);
            }
        });
        txtNumeroComanda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroComandaKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Código:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumeroComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumeroComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        linha2.setBackground(new java.awt.Color(0, 0, 0));
        linha2.setOpaque(true);

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Nova Comanda");

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        btnRefeicao.setBackground(new java.awt.Color(0, 153, 153));
        btnRefeicao.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnRefeicao.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\refeicao (2).png")); // NOI18N
        btnRefeicao.setText(" Refeição");
        btnRefeicao.setBorder(new javax.swing.border.MatteBorder(null));
        btnRefeicao.setBorderPainted(false);
        btnRefeicao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnRefeicaoFocusGained(evt);
            }
        });
        btnRefeicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefeicaoActionPerformed(evt);
            }
        });

        btnBebida.setBackground(new java.awt.Color(0, 153, 153));
        btnBebida.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnBebida.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\bebida2.png")); // NOI18N
        btnBebida.setText("  Bebida");
        btnBebida.setBorder(new javax.swing.border.MatteBorder(null));
        btnBebida.setBorderPainted(false);
        btnBebida.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnBebidaFocusGained(evt);
            }
        });
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
        btnSobremesa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnSobremesaFocusGained(evt);
            }
        });
        btnSobremesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSobremesaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRefeicao, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBebida, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSobremesa, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRefeicao, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBebida, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSobremesa, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Adicionar Itens");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnLancador)
                .addGap(117, 117, 117)
                .addComponent(btnStringGerenciador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblHora, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEncerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)))
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(144, 144, 144))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha2, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLancador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnStringGerenciador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblHora, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEncerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(64, 64, 64)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(463, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(516, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLancadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancadorActionPerformed
        new Menu().setVisible(true); 
        dispose();
    }//GEN-LAST:event_btnLancadorActionPerformed

    private void btnBebidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBebidaActionPerformed
        new NovaBebida().setVisible(true); 
    }//GEN-LAST:event_btnBebidaActionPerformed

    private void btnSobremesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSobremesaActionPerformed
        new NovaSobremesa().setVisible(true); 
    }//GEN-LAST:event_btnSobremesaActionPerformed

    private void btnEncerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncerrarActionPerformed
        /*try{
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
                        idsAbertos.remove(indice);
                        //ordenarComandas();  
                        c.setStatus(0);
                        comandaDao.update(c);
                    }else{*/
                        new EncerrarComanda().setVisible(true); 
                        dispose();
                    /*}
                }
            }    
        }catch(java.lang.NumberFormatException|java.lang.NullPointerException ex){

        }catch(java.util.ConcurrentModificationException ex){}*/
    }//GEN-LAST:event_btnEncerrarActionPerformed

    private void btnRefeicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefeicaoActionPerformed
        NovaRefeicao novoPrato = new NovaRefeicao(new javax.swing.JFrame(), true);
        novoPrato.setVisible(true);        
    }//GEN-LAST:event_btnRefeicaoActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        novaInstancia();
    }//GEN-LAST:event_formWindowGainedFocus

    private void jtComandasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtComandasFocusGained
        
    }//GEN-LAST:event_jtComandasFocusGained

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        System.out.println("asd");
    }//GEN-LAST:event_formKeyReleased

    private void txtNumeroComandaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroComandaKeyReleased
        if (evt.getKeyCode() == 10){
            ComandaDAO comDao = new ComandaDAO();
            int cod = comDao.codComanda(txtNumeroComanda.getText());           
            if (cod == 0){
                if (!txtNumeroComanda.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Código de comanda inválido");
            }else{
                txtNumeroComanda.setText("");
                boolean flagComandaAberta = false;
                for (Integer i : idsAbertos){
                    if (i == cod){
                        JOptionPane.showMessageDialog(null, "Comanda "+cod+" já foi aberta");
                        flagComandaAberta = true;
                        break;
                    }
                }
                if (!flagComandaAberta){
                    this.novaComanda(cod);
                }               
            }           
        }
    }//GEN-LAST:event_txtNumeroComandaKeyReleased

    private void txtNumeroComandaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroComandaFocusGained
        jtComandas.clearSelection();
    }//GEN-LAST:event_txtNumeroComandaFocusGained

    private void btnRefeicaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnRefeicaoFocusGained
        jtComandas.clearSelection();
    }//GEN-LAST:event_btnRefeicaoFocusGained

    private void btnBebidaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnBebidaFocusGained
        jtComandas.clearSelection();
    }//GEN-LAST:event_btnBebidaFocusGained

    private void btnSobremesaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnSobremesaFocusGained
        jtComandas.clearSelection();
    }//GEN-LAST:event_btnSobremesaFocusGained

    private void btnEncerrarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnEncerrarFocusGained
//        jtComandas.clearSelection();
    }//GEN-LAST:event_btnEncerrarFocusGained

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
    private javax.swing.JButton btnRefeicao;
    private javax.swing.JButton btnSobremesa;
    private javax.swing.JLabel btnStringGerenciador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtComandas;
    private javax.swing.JLabel lblHora;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    private javax.swing.JTextField txtNumeroComanda;
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
