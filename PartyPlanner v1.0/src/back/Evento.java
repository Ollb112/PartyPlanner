package back;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;


public class Evento {
    private long id;
    private String nome;
    private LocalDateTime data;
    private String local;
    private Cliente cliente;
    private boolean contratado;

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataHora() {
        return data;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.data = dataHora;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isContratado() {
        return contratado;
    }

    public void setContratado(boolean contratado) {
        this.contratado = contratado;
    }

    public Evento(String nome, LocalDateTime data, String local, Cliente cliente){
        id = System.currentTimeMillis();
        this.nome = nome;
        this.data = data;
        this.local = local;
        this.cliente = cliente;
        this.contratado = false;
    }
    public Evento(){
        id = System.currentTimeMillis();
    }
    public boolean jaOcorreu(){
        if(this.data.isAfter(LocalDateTime.now())){
            return false;
        }
        return true;
    }

    public String toString() {
        String status = "";
        if(jaOcorreu()){
            status = "realizado";
        }
        else{
            status = "a ser realizado";
        }
        DateTimeFormatter formater = new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy HH:mm").toFormatter();
        return cliente.getNome() + " convida para seu " + nome +", " + status + " às " + data.format(formater) + " em " + local;
    }
	
}
