import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Forum extends HttpServlet{
	public void doPost (HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
			
		//LEITURA DOS PARAMETROS
		String email = request.getParameter("email");
		String mensagem = request.getParameter("msg");
		
		//GERANDO ARQUIVO
		FileWriter file = new FileWriter("C:\\Users\\manov\\Downloads\\Forum\\mensagens.txt", true);
		BufferedWriter buffer = new BufferedWriter(file);
		buffer.append(email + ";" + mensagem + "\r\n");
		buffer.close();
		file.close();
		
		//GERANDO RESPOSTA
		PrintWriter out = response.getWriter();
		out.println("<html>"
				+ "<head><title>Forum</title></head>"
				+ "<body><h1>Mensagem Salva com Sucesso</h1></body>"
				+ "</html>");
		out.close();
	}
	
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String linha = null;
		ArrayList<String> msgs = new ArrayList<String>();
				
		//Lendo arquivo
		FileReader reader = new FileReader("C:\\Users\\manov\\Downloads\\Forum\\mensagens.txt");
		BufferedReader leitor = new BufferedReader(reader);
		while ((linha = leitor.readLine()) != null) {  
			msgs.add(linha);
		}  
		leitor.close();  
		reader.close();  
		
		//Gerando resposta
		PrintWriter out = response.getWriter();
		out.println("<html><header><title>Forum</title></header><body>"
				+ "<h1>Mensagens enviadas</h1>"
				+ "<hr/>");
		int i = 1;
		for ( String text : msgs) {
			out.println("<h2>Mensagem " + i + "</h2>");
			i++;
			String[] msg = text.split(";");
			//out.println("<p>" + msg + "</p>");
			out.println("<p>E-mail: " + msg[0] + "</p>"
					+ "<p>Mensagem: " + msg[1] + "</p>"
					+ "<hr/>");
		};
		out.println("</body></html>");
		out.close();
	}
}
