package back;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.Month;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GeradorDeRelatorios {
	public void obterProgramacaoDoMes(Month m, CentralDeInformacoes c) {
		Document doc = new Document(PageSize.A4);
		
		try {
			OutputStream os = new FileOutputStream("relatorio.pdf");
			PdfWriter.getInstance(doc, os);
			
			doc.open();
			
			 for (Evento e: c.getTodosOsEventos()) {
		            if(m.getValue() == e.getDataHora().getMonthValue()) {
		                Paragraph pg = new Paragraph("Cliente: "+ e.getUsuario().getNome()+"\n" 
		                        +"Evento: " + e.getNome()  + "\n" 
		                        +"Local: " + e.getLocal() +"\n" 
		                        +"Id: " + e.getId()
		                        +"\n------------------------------");
		                doc.add(pg);
		            }
		        }
			
			doc.close();
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();

		}
	}//metodo

}
