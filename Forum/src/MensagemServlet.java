import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MensagemServlet extends HttpServlet{

		private String filename;
		private String bgColor;
		private String color;
		
		public void init() throws ServletException{
			filename = getServletContext().getInitParameter("filename");
			bgColor = getServletConfig().getInitParameter("bgColor");
			color = getServletConfig().getInitParameter("color");
		}
		
		public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
					
			//Lendo arquivo
			FileReader file = new FileReader(filename);
			BufferedReader leitor = new BufferedReader(file);
			String linha = leitor.readLine();
			
			//Gerando resposta na tela
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>Mensagens Enviadas</title></head>"
					+ "<body bgcolor="+ bgColor +" text="+ color + "><table><tr><th>Email</th><th>Mensagem</th></tr>");
			while (linha != null) {  
				String[] msg = linha.split(";");
				out.println("<tr><td>" + msg[0] + "</td>"
						+ "<td>" + msg[1] + "</td></tr>");
				linha = leitor.readLine();
			}  
			out.println("</table></body></html>");
			leitor.close();   
			file.close();
			out.close();
		}
		
}
