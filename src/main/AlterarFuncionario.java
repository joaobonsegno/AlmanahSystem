package main;

import ArrumarString.Monetarios;
import ArrumarString.SoNumeros;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.Cargo;
import model.bean.Estado;
import model.bean.Funcionario;
import model.dao.CargoDAO;
import model.dao.EstadoDAO;
import model.dao.FuncionarioDAO;
import manual.Manual;

public class AlterarFuncionario extends javax.swing.JFrame {
    ArrayList<Cargo> listaCargos = new ArrayList<>();
    ArrayList<Estado> listaEstados = new ArrayList<>();
    EstadoDAO eDao = new EstadoDAO();
    CargoDAO cargoDao = new CargoDAO();
    
    public void criarCb(){
        cbUf.removeAllItems();
        cbUf.addItem("");
        for (Estado e : listaEstados){
            cbUf.addItem(e.getNome());
        }
        
        cbFuncao.removeAllItems();
        cbFuncao.addItem("");
        for (Cargo c : listaCargos){
            cbFuncao.addItem(c.getNome());
        }
        
        cbSexo.removeAllItems();
        cbSexo.addItem("");
        cbSexo.addItem("Feminino");
        cbSexo.addItem("Masculino");
    }
    
    public AlterarFuncionario() {
       initComponents();
       this.setLocationRelativeTo(null);
       txtNumero.setDocument(new SoNumeros()); 
       txtSalario.setDocument(new Monetarios(7,2));
       
       for (Estado e : eDao.read()){
           listaEstados.add(e);
       }
       
       for (Cargo c : cargoDao.read()){
           listaCargos.add(c);
       }
       
       criarCb();
       getRootPane().setDefaultButton(btnConfirmar);
       
       txtNome.setText(GerenciadorFuncionario.funcSelecionado.getNome());
       txtEmail.setText(GerenciadorFuncionario.funcSelecionado.getEmail());
       txtCpf.setText(GerenciadorFuncionario.funcSelecionado.getCpf());
       txtSalario.setText(Double.toString(GerenciadorFuncionario.funcSelecionado.getSalario()));
       txtTelefone.setText(GerenciadorFuncionario.funcSelecionado.getTelefone());
       txtCelular.setText(GerenciadorFuncionario.funcSelecionado.getCelular());
       txtUsuario.setText(GerenciadorFuncionario.funcSelecionado.getUsuario());
       txtSenha.setText(GerenciadorFuncionario.funcSelecionado.getSenha());
       txtConfirmacao.setText(GerenciadorFuncionario.funcSelecionado.getSenha());
       txtLogradouro.setText(GerenciadorFuncionario.funcSelecionado.getLogradouro());
       txtNumero.setText(Integer.toString(GerenciadorFuncionario.funcSelecionado.getNumero()));
       txtBairro.setText(GerenciadorFuncionario.funcSelecionado.getBairro());
       txtCidade.setText(GerenciadorFuncionario.funcSelecionado.getCidade());
       txtCep.setText(GerenciadorFuncionario.funcSelecionado.getCep());
       txtComplemento.setText(GerenciadorFuncionario.funcSelecionado.getComplemento());
       cbSexo.setSelectedItem(GerenciadorFuncionario.funcSelecionado.getSexo());
       cbFuncao.setSelectedItem(GerenciadorFuncionario.funcSelecionado.getCargo().getNome());
       cbUf.setSelectedItem(GerenciadorFuncionario.funcSelecionado.getEstado().getNome());
       calendarNasc.setDate(Date.valueOf(GerenciadorFuncionario.funcSelecionado.getDataNasc()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStringNovoFunc = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblStringFuncao = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblStringSexo = new javax.swing.JLabel();
        lblStringCpf = new javax.swing.JLabel();
        lblStringContatoUm = new javax.swing.JLabel();
        lblStringNascimento = new javax.swing.JLabel();
        lblStringEmail = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblStringNome = new javax.swing.JLabel();
        cbSexo = new javax.swing.JComboBox<>();
        cbFuncao = new javax.swing.JComboBox<>();
        calendarNasc = new com.toedter.calendar.JDateChooser();
        lblStringContatoDois = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblStringSalario = new javax.swing.JLabel();
        lblStringUsuario = new javax.swing.JLabel();
        lblStringConfirmacao = new javax.swing.JLabel();
        lblStringSenha = new javax.swing.JLabel();
        txtSalario = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        txtConfirmacao = new javax.swing.JPasswordField();
        lblStringBairro = new javax.swing.JLabel();
        lblStringLogradouro = new javax.swing.JLabel();
        lblStringNumero = new javax.swing.JLabel();
        lblStringCidade = new javax.swing.JLabel();
        txtLogradouro = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        txtComplemento = new javax.swing.JTextField();
        lblStringCep = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        lblStringComplemento = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        lblStringUf = new javax.swing.JLabel();
        cbUf = new javax.swing.JComboBox<>();
        txtTelefone = new javax.swing.JFormattedTextField();
        txtCelular = new javax.swing.JFormattedTextField();
        txtCep = new javax.swing.JFormattedTextField();
        txtCpf = new javax.swing.JFormattedTextField();
        linha2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        lblManual = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Alterar Funcionário");
        setMaximumSize(new java.awt.Dimension(1152, 690));
        setMinimumSize(new java.awt.Dimension(1152, 690));
        setResizable(false);

        lblStringNovoFunc.setBackground(new java.awt.Color(0, 102, 204));
        lblStringNovoFunc.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        lblStringNovoFunc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStringNovoFunc.setText("Alterar Funcionário");

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

        lblStringFuncao.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringFuncao.setText("*Função:");

        txtNome.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        lblStringSexo.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringSexo.setText("*Sexo:");

        lblStringCpf.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringCpf.setText("*CPF:");

        lblStringContatoUm.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringContatoUm.setText("Telefone:");

        lblStringNascimento.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNascimento.setText("*Nascimento:");

        lblStringEmail.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringEmail.setText("Email:");

        txtUsuario.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });

        lblStringNome.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNome.setText("*Nome:");

        cbSexo.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Masculino", "Feminino" }));
        cbSexo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbSexoItemStateChanged(evt);
            }
        });

        cbFuncao.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cbFuncao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbFuncao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbFuncaoItemStateChanged(evt);
            }
        });
        cbFuncao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFuncaoActionPerformed(evt);
            }
        });

        calendarNasc.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        lblStringContatoDois.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringContatoDois.setText("*Celular:");

        txtEmail.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        lblStringSalario.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringSalario.setText("*Salário (R$):");

        lblStringUsuario.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringUsuario.setText("*Usuário:");

        lblStringConfirmacao.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringConfirmacao.setText("*Confirmação da Senha:");

        lblStringSenha.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringSenha.setText("*Senha:");

        txtSalario.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        txtSalario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalarioActionPerformed(evt);
            }
        });

        txtSenha.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        txtConfirmacao.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        lblStringBairro.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringBairro.setText("*Bairro:");

        lblStringLogradouro.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringLogradouro.setText("*Logradouro:");

        lblStringNumero.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNumero.setText("*Número:");

        lblStringCidade.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringCidade.setText("*Cidade:");

        txtLogradouro.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        txtBairro.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        txtComplemento.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        lblStringCep.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringCep.setText("*CEP:");

        txtNumero.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        txtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroActionPerformed(evt);
            }
        });

        lblStringComplemento.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringComplemento.setText("Complemento:");

        txtCidade.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        lblStringUf.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringUf.setText("*UF:");

        cbUf.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cbUf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbUf.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbUfItemStateChanged(evt);
            }
        });

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefone.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        try {
            txtCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCelular.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        try {
            txtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCep.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        linha2.setBackground(new java.awt.Color(0, 0, 0));
        linha2.setOpaque(true);

        lblManual.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblManual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblManual.setText("?");
        lblManual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(linha1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1148, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(335, 335, 335)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119)
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblManual))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblStringNascimento)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(2, 2, 2)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(lblStringLogradouro)
                                                .addComponent(lblStringContatoUm)
                                                .addComponent(lblStringEmail)
                                                .addComponent(lblStringSenha)
                                                .addComponent(lblStringNome)
                                                .addComponent(lblStringBairro)))
                                        .addComponent(lblStringCidade, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(lblStringComplemento))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                                        .addComponent(lblStringUf))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtEmail)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(calendarNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lblStringSalario)
                                                    .addComponent(lblStringContatoDois))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtCelular, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                                                    .addComponent(txtSalario)))
                                            .addComponent(txtNome)
                                            .addComponent(txtCidade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                                            .addComponent(txtBairro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                                            .addComponent(txtLogradouro, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(4, 4, 4)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(22, 22, 22)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblStringFuncao, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lblStringCep, javax.swing.GroupLayout.Alignment.TRAILING)))
                                            .addComponent(lblStringNumero)
                                            .addComponent(lblStringUsuario, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblStringSexo, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblStringCpf, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblStringConfirmacao)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtConfirmacao, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                                    .addComponent(txtNumero, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                                    .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                                    .addComponent(cbFuncao, 0, 227, Short.MAX_VALUE)
                                    .addComponent(cbSexo, 0, 227, Short.MAX_VALUE)
                                    .addComponent(cbUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCpf)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(450, 450, 450)
                                .addComponent(lblStringNovoFunc)))
                        .addGap(0, 49, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha2, javax.swing.GroupLayout.DEFAULT_SIZE, 1152, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblStringNovoFunc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStringNome)
                            .addComponent(lblStringCpf)
                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStringEmail)
                            .addComponent(lblStringSexo)
                            .addComponent(cbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblStringSalario)
                                        .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblStringNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(calendarNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblStringContatoUm)
                                            .addComponent(lblStringContatoDois)
                                            .addComponent(lblStringUsuario)
                                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(2, 2, 2)))
                                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblStringSenha)
                                    .addComponent(lblStringConfirmacao)
                                    .addComponent(txtConfirmacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblStringLogradouro)
                                    .addComponent(txtLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblStringNumero)
                                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblStringBairro)
                                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblStringCidade)
                                    .addComponent(lblStringCep)
                                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblStringFuncao)
                                    .addComponent(cbFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblStringUf)
                                .addComponent(cbUf, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblStringComplemento)))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblManual, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(676, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        int flag = 0;
        FuncionarioDAO fDao = new FuncionarioDAO();
        String sexo = (String)cbSexo.getSelectedItem();
        String funcao = (String)cbFuncao.getSelectedItem();
        String uf = (String)cbUf.getSelectedItem();
        //String nasc = calendarNasc.ge
        String nome = txtNome.getText();
        String cpf = txtCpf.getText();
        String senha = txtSenha.getText();
        String usuario = txtUsuario.getText();
        for (Funcionario f:fDao.read()){
            if (f.getUsuario().equals(usuario)&!f.getUsuario().equals(GerenciadorFuncionario.funcSelecionado.getUsuario())){
                flag = 1;
            }
        } 
        if (!senha.equals(txtConfirmacao.getText())){
            flag = 2;
        }
        
        if (flag == 0){
            if(!(nome.equals("")|cpf.equals("")|sexo.equals("")|funcao.equals(""))){
                try{
                    Estado estado = new Estado();
                    for(Estado e:listaEstados){
                        if(e.getNome().equals(uf)){
                            estado.setId(e.getId());
                            estado.setNome(uf);
                            //JOptionPane.showMessageDialog(null, "ID já existente!");
                            //flagId=false;
                        }
                    }
                    Cargo cargo = new Cargo();
                    for(Cargo c:listaCargos){
                        if(funcao.equals(c.getNome())){
                            cargo.setId(c.getId());
                            cargo.setNome(c.getNome());
                            cargo.setDescricao(c.getDescricao());
                        }
                    }
                    Funcionario cadastro = new Funcionario();
                    GerenciadorFuncionario.funcSelecionado.clonarFuncionario(cadastro);
                    
                    cadastro.setNome(nome);
                    cadastro.setCpf(cpf);
                    cadastro.setEmail(txtEmail.getText());
                    cadastro.setSexo(sexo);
                    String salario = txtSalario.getText();
                    salario = salario.replace(".","");
                    salario = salario.replace(",",".");
                    cadastro.setSalario(Double.parseDouble(salario));
                    cadastro.setCargo(cargo);
                    cadastro.setTelefone(txtTelefone.getText());
                    cadastro.setCelular(txtCelular.getText());
                    cadastro.setUsuario(usuario);
                    cadastro.setSenha(senha);
                    cadastro.setLogradouro(txtLogradouro.getText());
                    cadastro.setBairro(txtBairro.getText());
                    cadastro.setNumero(Integer.parseInt(txtNumero.getText()));
                    cadastro.setCidade(txtCidade.getText());
                    cadastro.setCep(txtCep.getText());
                    cadastro.setComplemento(txtComplemento.getText());
                    cadastro.setEstado(estado);
                    
                    //Formatar a entrada do JCalendar
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String dataFormatada = sdf.format(calendarNasc.getDate());
                    
                    cadastro.setDataNasc(dataFormatada);

                    fDao.update(cadastro);
                    new Menu().setVisible(true); 
                    dispose();

                }catch(java.lang.NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Campo \"Salário\" inválido");
                }
            }else{
                String msg = "\n";
                if(nome.equals("")){
                    msg += ("- Nome\n");
                }
                if(cpf.equals("   .   .   -  ")){
                    msg += ("- CPF\n");
                }
                if(sexo.equals("")){
                    msg += ("- Sexo\n");
                }
                if(funcao.equals("")){
                    msg += ("- Função\n");
                }
                JOptionPane.showMessageDialog(null, "O(s) seguinte(s) campo(s) deve(m) ser preenchido(s):"+msg);
            }
        }else if (flag == 1){
            JOptionPane.showMessageDialog(null, "Falha no cadastro: usuário \""+usuario+"\" já está em uso.");
        }else if (flag == 2){
            JOptionPane.showMessageDialog(null, "Falha no cadastro: senhas não coincidem.");
        }
        
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        new GerenciadorFuncionario().setVisible(true); 
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cbSexoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbSexoItemStateChanged

    }//GEN-LAST:event_cbSexoItemStateChanged

    private void cbFuncaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbFuncaoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbFuncaoItemStateChanged

    private void txtSalarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalarioActionPerformed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroActionPerformed

    private void cbUfItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbUfItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbUfItemStateChanged

    private void cbFuncaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFuncaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbFuncaoActionPerformed

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
            java.util.logging.Logger.getLogger(AlterarFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlterarFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlterarFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlterarFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new AlterarFuncionario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private com.toedter.calendar.JDateChooser calendarNasc;
    private static javax.swing.JComboBox<String> cbFuncao;
    private static javax.swing.JComboBox<String> cbSexo;
    private static javax.swing.JComboBox<String> cbUf;
    private javax.swing.JLabel lblManual;
    private javax.swing.JLabel lblStringBairro;
    private javax.swing.JLabel lblStringCep;
    private javax.swing.JLabel lblStringCidade;
    private javax.swing.JLabel lblStringComplemento;
    private javax.swing.JLabel lblStringConfirmacao;
    private javax.swing.JLabel lblStringContatoDois;
    private javax.swing.JLabel lblStringContatoUm;
    private javax.swing.JLabel lblStringCpf;
    private javax.swing.JLabel lblStringEmail;
    private javax.swing.JLabel lblStringFuncao;
    private javax.swing.JLabel lblStringLogradouro;
    private javax.swing.JLabel lblStringNascimento;
    private javax.swing.JLabel lblStringNome;
    private javax.swing.JLabel lblStringNovoFunc;
    private javax.swing.JLabel lblStringNumero;
    private javax.swing.JLabel lblStringSalario;
    private javax.swing.JLabel lblStringSenha;
    private javax.swing.JLabel lblStringSexo;
    private javax.swing.JLabel lblStringUf;
    private javax.swing.JLabel lblStringUsuario;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JFormattedTextField txtCelular;
    private javax.swing.JFormattedTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtComplemento;
    private javax.swing.JPasswordField txtConfirmacao;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtLogradouro;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtSalario;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JFormattedTextField txtTelefone;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}


