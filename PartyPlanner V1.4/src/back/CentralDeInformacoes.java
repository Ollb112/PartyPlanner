package back;

import java.util.ArrayList;


public class CentralDeInformacoes {
	private ArrayList<Administrador> todosOsAdministradores = new ArrayList<Administrador>();
	private ArrayList<Cliente> todosOsClientes = new ArrayList<Cliente>();
	private ArrayList<Fornecedor> todosOsFornecedores = new ArrayList<Fornecedor>();
	private ArrayList<Evento> todosOsEventos = new ArrayList<Evento>();
	
    public Usuario recuperarAdministradorPorEmail(String s) {
        for (Usuario x: todosOsAdministradores) {
            if(s.equals(x.getEmail())) {
                return x;
            }
        }
        return null;
    }
    
    public boolean adicionarAdministrador(Administrador a) {
        for (Administrador x: todosOsAdministradores) {
            if(x.getEmail().equals(a.getEmail())) {
                return false;
            }
        }
        todosOsAdministradores.add(a);
        return true;
    }
    
    public Usuario recuperarFornecedorPorEmail(String s) {
        for (Usuario x: todosOsFornecedores) {
            if(s.equals(x.getEmail())) {
                return x;
            }
        }
        return null;
    }
    
    public boolean adicionarFornecedor(Fornecedor a) {
        for (Fornecedor x: todosOsFornecedores) {
            if(x.getEmail().equals(a.getEmail())) {
                return false;
            }
        }
        todosOsFornecedores.add(a);
        return true;
    }
    
    
    public Usuario recuperarClientePorEmail(String s) {
        for (Usuario x: todosOsClientes) {
            if(s.equals(x.getEmail())) {
                return x;
            }
        }
        return null;
    }
    
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
            if(evento.getUsuario().getEmail().equals(email)){
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
	public ArrayList<Administrador> getTodosOsAdministradores() {
		return todosOsAdministradores;
	}
	public void setTodosOsAdministradores(ArrayList<Administrador> todosOsAdministradores) {
		this.todosOsAdministradores = todosOsAdministradores;
	}
	public ArrayList<Fornecedor> getTodosOsFornecedores() {
		return todosOsFornecedores;
	}
	public void setTodosOsFornecedores(ArrayList<Fornecedor> todosOsFornecedores) {
		this.todosOsFornecedores = todosOsFornecedores;
	}
	
}
