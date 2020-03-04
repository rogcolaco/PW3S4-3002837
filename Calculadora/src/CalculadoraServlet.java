import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculadoraServlet extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//LEITURA DOS PARAMETROS DA REQUISICAO
		double num1 = Double.parseDouble(request.getParameter("numero1"));
		double num2 = Double.parseDouble(request.getParameter("numero2"));
		String op = request.getParameter("operador");
		
		//PROCESSAMENTO - VERIFICAR OPERADOR E REALIZAR OPERAÇÃO
		double resultado = 0;
		
		if(op.equals("Sum")) {
			resultado = num1 + num2;
		} else if(op.equals("Sub")) {
			resultado = num1 - num2;
		} else if(op.equals("Mul")) {
			resultado = num1 * num2;
		} else if(op.equals("Div")) {
			resultado = num1 / num2;
		}
		
		//GERANDO RESPOSTA
		PrintWriter out = response.getWriter();
		out.println("<html>"
				+ "<head><title>Calculadora</title></head>"
				+ "<body><h1>O resultado é: " + resultado + "</h1></body>"
				+ "</html>");
		out.close();
	}

}
