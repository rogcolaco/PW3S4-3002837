import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Forum extends HttpServlet{
	
	private String filename;
	private String color;
	
	public void init()throws ServletException{
		
		//Configurando caminho para salvar arquivo
		filename = getServletContext().getInitParameter("filename");
		color = getServletConfig().getInitParameter("color");
		getServletContext().setAttribute("contador", 0);
	}
	
	public void doPost (HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
		
		//PEGAR INFORMAÇÃO DO CONTEXTO
		int count = (Integer)getServletContext().getAttribute("contador");
			
		//COLOCAR CONTEUDO NO CONTEXTO
		//primeiro parametro nome (qualquer coisa)
		//segundo parametro valor do atributo
		//getServletContext().setAttribute("contador", cont++);
		
		
		
		//LEITURA DOS PARAMETROS
		String email = request.getParameter("email");
		String mensagem = request.getParameter("mensagem");
		
		//GERANDO ARQUIVO
		FileWriter file = new FileWriter(filename, true);
		BufferedWriter buffer = new BufferedWriter(file);
		buffer.append(email + ";" + mensagem + "\r\n");
		getServletContext().setAttribute("contador", ++count);
		buffer.close();
		file.close();
		count = (Integer)getServletContext().getAttribute("contador");
		
		//GERANDO RESPOSTA
		PrintWriter out = response.getWriter();
		out.println("<html>"
				+ "<head><title>Forum</title></head>"
				+ "<body bgcolor="+ color +"><h1>Mensagem Salva com Sucesso</h1>"
				+ "<p>Número de acessos:"+ count + "</p></body>"
				+ "</html>");
		out.close();
	}
}
