package code;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


/**
 * @
 * Apresenta a pagina com a lista de regioes e o formulario para o utilizador realizar as queries.
 * @throws Exception
 */
public class CriarPagina {


	public static void main(String[] args) throws Exception {

		CovidQuery cq = new CovidQuery();

		System.out.println(cgi_lib.Header());

		System.out.println("<h1>Requisito 5: Queries</h1><br>");

		//Lista das regioes
		System.out.println("<h2>Lista das regioes disponiveis para pesquisa:</h2><ul>");
		ArrayList <String> regioes = new CovidQuery().obterRegioes();
		for (String regiao: regioes) {
			System.out.println("<li>" + regiao + "</li>");	
		}
		System.out.println("</ul>");

		//Formulario
		System.out.println("<br><br><br><h1>Efetuar pesquisa com filtros </h1>");
		try (BufferedReader br = new BufferedReader(new FileReader("form.html"))) {
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		}

		System.out.println("<br>" + cgi_lib.HtmlBot());
	}
}
