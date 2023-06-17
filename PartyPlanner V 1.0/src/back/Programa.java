package back;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Scanner;

/*public class Programa {
   	public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Persistencia banco = new Persistencia();
        CentralDeInformacoes cdi = banco.recuperarCentral("central.xml");    
        GeradorDeRelatorios relatorio = new GeradorDeRelatorios();
        String parametro = "inicio";

        while(!parametro.equals("S")) {
        	 System.out.println(" 1- Novo cliente "
             		+ "\n 2- Listar todos os clientes "
             		+ "\n 3- Exibir informacoes de um cliente especifico "
             		+ "\n 4- Novo Evento "
             		+ "\n 5- Listar todos os eventos "
             		+ "\n 6- Listar os eventos de um cliente "
             		+ "\n 7- Gerar relatorio de programacao do mes"
             		+ "\n 8- Contratar Evento"
             		+ "\n S- Sair");
             parametro = leitor.nextLine().toUpperCase();

            switch (parametro) {
                case "1":
                    System.out.println("Digite seu nome:");
                    String nome = leitor.nextLine();
                    System.out.println("Digite o seu CPF:");
                    String cpf = leitor.nextLine();
                    System.out.println("Digite o sexo masculino|feminino");
                    String sexo = leitor.nextLine().toUpperCase();
                    System.out.println("Digite seu email:");
                    String email = leitor.nextLine();
                    Cliente s = new Cliente(nome, cpf, email, Sexo.valueOf(sexo));
                    if(cdi.adicionarCliente(s)) {
                    	banco.salvarCentral(cdi, "central.xml");
                        System.out.println("Cliente adcionado com sucesso.");
                    }
                    else{
                        System.out.println("Já existe cliente com este email cadastrado.");
                    }
                    break;
                    
                case "2":
                    if(cdi.getTodosOsClientes().size() != 0) {
                        for (Usuario x : cdi.getTodosOsClientes()) {
                            System.out.println(x.toString());
                        }
                    }
                    else{
                        System.out.println("Não há clientes cadastrados.");
                    }
                    break;
                    
                case "3":
                    System.out.println("Digite por favor o seu email:");
                    String recuperacao = leitor.nextLine();
                    cdi.recuperarClientePorEmail(recuperacao);
                    System.out.println(cdi.recuperarClientePorEmail(recuperacao));
                    break;
                    
                case "4":
                    System.out.println("Digite o seu email:");
                    email = leitor.nextLine();
                    Usuario c1 = cdi.recuperarClientePorEmail(email);
                    if(c1 != null) {
                        System.out.println("Digite o nome do evento:");
                        nome = leitor.nextLine();
                        System.out.println("Digite a data e a hora do evento: DD/MM/YYYY HR:MIN:");
                        String datastring = leitor.nextLine();
                        DateTimeFormatter formater = new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy HH:mm").toFormatter();
                        LocalDateTime data = LocalDateTime.parse(datastring, formater);
                        System.out.println("Digite o Local:");
                        String local = leitor.nextLine();
                        Evento evento = new Evento(nome,data , local, c1);
                        System.out.println();
                        if(cdi.adicionarEvento(evento)){
                        	banco.salvarCentral(cdi, "central.xml");
                            System.out.println("Evento adcionado com sucesso.");
                        }
                        else {
                            System.out.println("Evento não cadastrado.");
                        }
                    } else{
                       System.out.println("Cliente não cadastrado.");
                    }
                    break;
                    
                case "5":
                    for(Evento evento : cdi.getTodosOsEventos()){
                        System.out.println(evento);
                    }

                    break;
                    
                case "6":
                    System.out.println("Digite o seu email: ");
                    String verificador = leitor.nextLine();
                    for(Evento evento : cdi.recuperarEventosDeUmCliente(verificador)){
                        System.out.println(evento);
                    }
                    break;
                    
                case "7":
                	System.out.println("informe o mes que deseja obter o relatorio: ");
                	int mes = Integer.parseInt(leitor.nextLine());
                	relatorio.obterProgramacaoDoMes(Month.of(mes), cdi);
                	break;
                case "8":
                	System.out.println("Eventos ainda nao realizados: ");
                	
                    for(Evento evento : cdi.getTodosOsEventos()){
                    	if (!evento.jaOcorreu()) {
                    		System.out.println(evento);
                    	}
                    }
                	System.out.println("informe o nome do evento voce deseja enviar para o cliente: ");
                    String nomeDoEvento = leitor.nextLine();
                    for(Evento evento : cdi.getTodosOsEventos()){
                    	if (evento.getNome().equals(nomeDoEvento)) {
                    		evento.setContratado(true);
                            System.out.println("Informe o conteudo da mensagem; ");
                    		String conteudo = leitor.nextLine();
                    		Mensageiro.enviarEmailParaCliente(evento, conteudo);
                    		System.out.println("Menssagem enviado com sucesso.");
                    	}
                    		
                    }
                	break;
            }

        }
        
        leitor.close();
    }
}*/


