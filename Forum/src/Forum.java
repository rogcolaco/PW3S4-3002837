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
		FileWriter file = new FileWriter("mensagens.txt", true);
		BufferedWriter buffer = new BufferedWriter(file);
		buffer.append(email + ";" + mensagem + "\r\n");
		buffer.close();
		file.close();
		
		//GERANDO RESPOSTA
		PrintWriter out = response.getWriter();
		out.println("<html>"
				+ "<head><title>Calculadora</title></head>"
				+ "<body><h1>Mensagem Salva com Sucesso</h1></body>"
				+ "</html>");
		out.close();
	}
}
