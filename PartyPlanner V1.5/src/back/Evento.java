package back;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;


public class Evento {
    private long id;
    private String nome;
    private LocalDateTime data;
    private String local;
    private Usuario usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isContratado() {
        return contratado;
    }

    public void setContratado(boolean contratado) {
        this.contratado = contratado;
    }

    public Evento(String nome, LocalDateTime data, String local, Usuario usuario){
        id = System.currentTimeMillis();
        this.nome = nome;
        this.data = data;
        this.local = local;
        this.usuario = usuario;
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
        return usuario.getNome() + " convida para seu " + nome +", " + status + " às " + data.format(formater) + " em " + local;
    }
	
}
