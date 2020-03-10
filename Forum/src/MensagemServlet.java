import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MensagemServlet extends HttpServlet{

		private String filename;
		
		public void init() throws ServletException{
			filename = getServletConfig().getInitParameter("filename");
		}
		
		public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			
			String linha = null;
			ArrayList<String> msgs = new ArrayList<String>();
					
			//Lendo arquivo
			FileReader reader = new FileReader(filename);
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
