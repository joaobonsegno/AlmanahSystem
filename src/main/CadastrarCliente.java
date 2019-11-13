package main;

import ArrumarString.SoNumeros;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import model.bean.Cargo;
import model.bean.Cliente;
import model.bean.Estado;
import model.dao.CargoDAO;
import model.dao.ClienteDAO;
import model.dao.EstadoDAO;
import manual.Manual;

public class CadastrarCliente extends javax.swing.JFrame {
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
        
        cbSexo.removeAllItems();
        cbSexo.addItem("");
        cbSexo.addItem("Feminino");
        cbSexo.addItem("Masculino");
    }
    
    public CadastrarCliente() {
       initComponents();
       this.setLocationRelativeTo(null);
       txtNumero.setDocument(new SoNumeros()); 
       for (Estado e : eDao.read()){
           listaEstados.add(e);
       }
       
       for (Cargo c : cargoDao.read()){
           listaCargos.add(c);
       }
       criarCb();
       getRootPane().setDefaultButton(btnConfirmar);
       
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStringNovoFunc = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtNome = new javax.swing.JTextField();
        lblStringSexo = new javax.swing.JLabel();
        lblStringCpf = new javax.swing.JLabel();
        lblStringContatoUm = new javax.swing.JLabel();
        lblStringNascimento = new javax.swing.JLabel();
        lblStringEmail = new javax.swing.JLabel();
        lblStringNome = new javax.swing.JLabel();
        cbSexo = new javax.swing.JComboBox<>();
        calendarNasc = new com.toedter.calendar.JDateChooser();
        lblStringContatoDois = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
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
        setTitle("Cadastrar Cliente");
        setMaximumSize(new java.awt.Dimension(1110, 564));
        setMinimumSize(new java.awt.Dimension(1110, 564));
        setResizable(false);

        lblStringNovoFunc.setBackground(new java.awt.Color(0, 102, 204));
        lblStringNovoFunc.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        lblStringNovoFunc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStringNovoFunc.setText("Cadastrar Cliente");

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
        lblStringEmail.setText("*Email:");

        lblStringNome.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNome.setText("*Nome:");

        cbSexo.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Masculino", "Feminino" }));
        cbSexo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbSexoItemStateChanged(evt);
            }
        });

        calendarNasc.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        lblStringContatoDois.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringContatoDois.setText("*Celular:");

        txtEmail.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

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
        lblManual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblManualMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(313, 313, 313)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119)
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblManual))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(47, 47, 47)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblStringNascimento)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(46, 46, 46)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lblStringEmail)
                                                    .addComponent(lblStringNome))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblStringContatoUm)
                                        .addGap(15, 15, 15)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(calendarNasc, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                            .addComponent(txtTelefone))
                                        .addGap(55, 55, 55)
                                        .addComponent(lblStringContatoDois)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(54, 54, 54)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblStringSexo, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblStringCpf, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbSexo, 0, 184, Short.MAX_VALUE)
                                    .addComponent(txtCpf)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(2, 2, 2)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(lblStringLogradouro)
                                                .addComponent(lblStringBairro)))
                                        .addComponent(lblStringCidade, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(lblStringComplemento))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblStringUf))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(57, 57, 57)
                                                .addComponent(lblStringCep))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(lblStringNumero)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCep, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                    .addComponent(txtNumero)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(446, 446, 446)
                                .addComponent(lblStringNovoFunc)))
                        .addGap(0, 59, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha2, javax.swing.GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblStringNovoFunc)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStringNome))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStringEmail)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStringCpf)
                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStringSexo)
                            .addComponent(cbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStringNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(calendarNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblStringContatoUm))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblStringContatoDois)))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
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
                    .addComponent(txtCep, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblStringUf)
                        .addComponent(cbUf, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblStringComplemento)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblManual, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(64, 64, 64)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(499, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(550, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        int flag = 0;
        ClienteDAO cDao = new ClienteDAO();
        String sexo = (String)cbSexo.getSelectedItem();
        String uf = (String)cbUf.getSelectedItem();
        String nome = txtNome.getText();
        String cpf = txtCpf.getText();
        
        if (flag == 0){
            if(!(nome.equals("")|cpf.equals("")|sexo.equals(""))){
                try{
                    Estado estado = new Estado();
                    for(Estado e:listaEstados){
                        if(e.getNome().equals(uf)){
                            estado.setId(e.getId());
                            estado.setNome(uf);
                        }
                    }

                    Cliente c = new Cliente();
                    c.setNome(nome);
                    c.setCpf(cpf);
                    c.setEmail(txtEmail.getText());
                    c.setSexo(sexo);
                    c.setTelefone(txtTelefone.getText());
                    c.setCelular(txtCelular.getText());
                    c.setLogradouro(txtLogradouro.getText());
                    c.setBairro(txtBairro.getText());
                    c.setNumero(Integer.parseInt(txtNumero.getText()));
                    c.setCidade(txtCidade.getText());
                    c.setCep(txtCep.getText());
                    c.setComplemento(txtComplemento.getText());
                    
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String dataFormatada = sdf.format(calendarNasc.getDate());
                    
                    c.setDataNasc(dataFormatada);
                    c.setSaldo(0.0);
                    c.setSaldoPendente(0.0);
                    c.setEstado(estado);

                    cDao.create(c);
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
                JOptionPane.showMessageDialog(null, "O(s) seguinte(s) campo(s) deve(m) ser preenchido(s):"+msg);
            }
        }
        
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        
        new Menu().setVisible(true); 
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cbSexoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbSexoItemStateChanged

    }//GEN-LAST:event_cbSexoItemStateChanged

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroActionPerformed

    private void cbUfItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbUfItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbUfItemStateChanged

    private void lblManualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblManualMouseClicked
        Manual.abrirManual("cadastrarCliente");
    }//GEN-LAST:event_lblManualMouseClicked

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
            java.util.logging.Logger.getLogger(CadastrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastrarCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private com.toedter.calendar.JDateChooser calendarNasc;
    private static javax.swing.JComboBox<String> cbSexo;
    private static javax.swing.JComboBox<String> cbUf;
    private javax.swing.JLabel lblManual;
    private javax.swing.JLabel lblStringBairro;
    private javax.swing.JLabel lblStringCep;
    private javax.swing.JLabel lblStringCidade;
    private javax.swing.JLabel lblStringComplemento;
    private javax.swing.JLabel lblStringContatoDois;
    private javax.swing.JLabel lblStringContatoUm;
    private javax.swing.JLabel lblStringCpf;
    private javax.swing.JLabel lblStringEmail;
    private javax.swing.JLabel lblStringLogradouro;
    private javax.swing.JLabel lblStringNascimento;
    private javax.swing.JLabel lblStringNome;
    private javax.swing.JLabel lblStringNovoFunc;
    private javax.swing.JLabel lblStringNumero;
    private javax.swing.JLabel lblStringSexo;
    private javax.swing.JLabel lblStringUf;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JFormattedTextField txtCelular;
    private javax.swing.JFormattedTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtComplemento;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtLogradouro;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}


