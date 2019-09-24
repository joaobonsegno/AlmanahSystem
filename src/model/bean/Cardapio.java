package model.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Cardapio {
    private int id, status;
    private String data;
    private ArrayList<Prato> pratos = new ArrayList<>();

    public String dataAtual(){
        Calendar data = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = sdf.format(data.getTime());
        return dataFormatada;
    }
    
    public String dataAtualSemHora(){
        Calendar data = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = sdf.format(data.getTime());
        return dataFormatada;
    }
    
    public void setDataAtual(){
        Calendar data = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = sdf.format(data.getTime());
        this.data = dataFormatada;
    }

    public int getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(int diaSemana) {
        this.diaSemana = diaSemana;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ArrayList<Prato> getPratos() {
        return pratos;
    }

    public void setPratos(ArrayList<Prato> pratos) {
        this.pratos = pratos;
    }
    
    public void setPrato(Prato p){
        this.pratos.add(p);
    }
    
    public Prato getPrato(int indice){
        return this.pratos.get(indice);       
    }
    
    public void removerPrato(int indice){
        this.pratos.remove(indice);
    }
}
