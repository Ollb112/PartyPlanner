package out.production.Projeto;

public class Cliente {
    private String nome;
    private Sexo sexo;
    private String CPF;
    private String email;

    public Cliente(String a, Sexo b,String c,String d) {
        setNome(a);
        setSexo(b);
        setCPF(c);
        email = d;
    }
    public Cliente() {

    }

    public String toString() {
        return  getNome();
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String n) {
        nome = n;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo s) {
        sexo = s;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String c) {
        CPF = c;
    }

    public String getEmail() {
        return email;
    }
}
