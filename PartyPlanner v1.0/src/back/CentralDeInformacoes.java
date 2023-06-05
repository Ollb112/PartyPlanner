package back;

import java.util.ArrayList;


public class CentralDeInformacoes {

	private ArrayList<Cliente> todosOsClientes = new ArrayList<Cliente>();
	private ArrayList<Evento> todosOsEventos = new ArrayList<Evento>();
	
    public boolean adicionarCliente(Cliente a) {
        for (Cliente x: todosOsClientes) {
            if(x.getEmail().equals(a.getEmail())) {
                return false;
            }
        }
        todosOsClientes.add(a);
        return true;
    }
    public boolean adicionarEvento(Evento evento) {
        for (Evento id : todosOsEventos) {
            if (evento.getId() == id.getId() || evento.jaOcorreu()) {
                return false;
            }
        }
        todosOsEventos.add(evento);
        return true;
    }
    public Evento recuperarEventoPeloId(long id){
        for(Evento evento : todosOsEventos){
            if(id == evento.getId()){
                return evento;
            }
        }
        return null;
    }
    public ArrayList<Evento> recuperarEventosDeUmCliente(String email){
        ArrayList<Evento> eventos = new ArrayList<>();
        for(Evento evento : todosOsEventos){
            if(evento.getCliente().getEmail().equals(email)){
            	eventos.add(evento);
            }
        }
        return eventos;
    }

    public ArrayList<Evento> getTodosOsEventos() {
        return todosOsEventos;
    }
    public ArrayList<Cliente> getTodosOsClientes(){
        return todosOsClientes;
    }
    public void setTodosOsClientes( ArrayList<Cliente> x){
        todosOsClientes = x;
    }
    public Cliente recuperarClientePorEmail(String s) {
        for (Cliente x: todosOsClientes) {
            if(s.equals(x.getEmail())) {
                return x;
            }
        }
        return null;
    }
	
}
