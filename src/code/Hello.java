package code;

import java.util.ArrayList;
import java.util.Hashtable;

public class Hello {

	static CovidQuery cq = new CovidQuery();
	public static Hashtable form_data = new Hashtable();


	public static void main( String args[] ) throws Exception {

		System.out.println(cgi_lib.Header());

		form_data = cgi_lib.ReadParse(System.in);
		processarForm();

		System.out.println(cgi_lib.HtmlBot());
	}




	/**
	 * Verifica se o utilizador preencheu de forma errada o formulario.<br>Caso afirmativo, diz ao utilizador o que fez mal.
	 * <br>Caso negativo, prossegue a analise do form.
	 * 
	 * @return <b>true</b> se houver um erro, <b>false</b> se puder prosseguir
	 */
	public static boolean verificarErrosInput() {
		if(!(form_data.containsKey("testesCB") || form_data.containsKey("infCB") || form_data.containsKey("intCB"))) {
			System.out.println("<h1>Nao selecionou nenhuma checkbox!</h1>");
			return true;
		}
		else if(form_data.containsKey("testesCB") && form_data.get("QTDTestes").equals("")) {
			System.out.println("<h1>Precisa de colocar um valor no espaco em branco!</h1>");
			return true;
		}
		else if(form_data.containsKey("infCB") && form_data.get("QTDInf").equals("")) {
			System.out.println("<h1>Precisa de colocar um valor no espaco em branco!</h1>");
			return true;
		}
		else if(form_data.containsKey("intCB") && form_data.get("QTDInt").equals("")) {
			System.out.println("<h1>Precisa de colocar um valor no espaco em branco!</h1>");
			return true;
		}
		return false;
	}


	/**
	 * Processa o formulario e os dados que o utilizador introduziu, devolve em html os dados que o utilizador quer ver.
	 * 
	 * @throws Exception
	 */
	private static void processarForm()throws Exception{


		//  Print the name/value pairs sent from the browser.
		//System.out.println(cgi_lib.Variables(form_data));

		//Se o utilizador fez tudo bem, prossegue com a query
		if(!verificarErrosInput()){

			if(((String)form_data.get("rangeQuery")).equals("pais")) { //Apresentar a soma das regioes (pais todo)
				System.out.println("<h1>Aqui estao os dados referentes ao territorio nacional que preenchem os seus filtros:</h2><br>");
				processPaisInteiro();			
			}
			else { //Apresentar por Regiao
				System.out.println("<h1>Aqui estao os dados das regioes que preenchem os seus filtros:</h2><br>");
				ArrayList<Regiao> dados = cq.getDadosRegioes();
				for (Regiao regiao: dados) {
					compareToRDF(regiao);
					System.out.println("<br>");
				}
			}
		}


	}

	
	/**
	 * Compara as informacoes a nivel nacional (soma de todas as regioes) com as informacoes dadas pelo utilizador no formulario.
	 * Se for compativel com o filtro escolhido pelo utilizador, envia a info para a resposta (html).
	 * 
	 */
	private static void processPaisInteiro() throws Exception {
		String infecoes = cq.query("sum(//*/Infecoes/text())");
		String internamentos = cq.query("sum(//*/Internamentos/text())");
		String testes = cq.query("sum(//*/Testes/text())");
		
		if (form_data.containsKey("infCB")) {
			switch ((String) form_data.get("tipoOperadorInf")) {
			case "maior":
				if(Integer.valueOf(infecoes) > Integer.valueOf((String) form_data.get("QTDInf"))) {
					System.out.println("Infecoes: " + infecoes);
					System.out.println("<br>");
				}
				break;
			case "menor":
				if(Integer.valueOf(infecoes) < Integer.valueOf((String) form_data.get("QTDInf"))) {
					System.out.println("Infecoes: " + infecoes);
					System.out.println("<br>");
				}
				break;
			case "igual":
				if(Integer.valueOf(infecoes) == Integer.valueOf((String) form_data.get("QTDInf"))) {
					System.out.println("Infecoes: " + infecoes);
					System.out.println("<br>");
				}
				break;
			case "diferente":
				if(Integer.valueOf(infecoes) != Integer.valueOf((String) form_data.get("QTDInf"))) {
					System.out.println("Infecoes: " + infecoes);
					System.out.println("<br>");
				}
				break;
			case "menorouigual":
				if(Integer.valueOf(infecoes) <= Integer.valueOf((String) form_data.get("QTDInf"))) {
					System.out.println("Infecoes: " + infecoes);
					System.out.println("<br>");}
				break;
			case "maiorouigual":
				if(Integer.valueOf(infecoes) >= Integer.valueOf((String) form_data.get("QTDInf"))) {
					System.out.println("Infecoes: " + infecoes);
					System.out.println("<br>");
				}
				break;
			}
		}

		if (form_data.containsKey("intCB")) {
			switch ((String) form_data.get("tipoOperadorInt")) {
			case "maior":
				if(Integer.valueOf(internamentos) > Integer.valueOf((String) form_data.get("QTDInt"))) {
					System.out.println("Internamentos: " + internamentos);
					System.out.println("<br>");
				}
				break;
			case "menor":
				if(Integer.valueOf(internamentos) < Integer.valueOf((String) form_data.get("QTDInt"))) {
					System.out.println("Internamentos: " + internamentos);
					System.out.println("<br>");
				}
				break;
			case "igual":
				if(Integer.valueOf(internamentos) == Integer.valueOf((String) form_data.get("QTDInt"))) {
					System.out.println("Internamentos: " + internamentos);
					System.out.println("<br>");
				}
				break;
			case "diferente":
				if(Integer.valueOf(internamentos) != Integer.valueOf((String) form_data.get("QTDInt"))) {
					System.out.println("Internamentos: " + internamentos);
					System.out.println("<br>");
				}
				break;
			case "menorouigual":
				if(Integer.valueOf(internamentos) <= Integer.valueOf((String) form_data.get("QTDInt"))) {
					System.out.println("Internamentos: " + internamentos);
					System.out.println("<br>");
				}
				break;
			case "maiorouigual":
				if(Integer.valueOf(internamentos) >= Integer.valueOf((String) form_data.get("QTDInt"))) {
					System.out.println("Internamentos: " + internamentos);
				}
				break;
			}
		}
		if (form_data.containsKey("testesCB")) {
			switch ((String) form_data.get("tipoOperadorTestes")) {
			case "maior":
				if(Integer.valueOf(testes) > Integer.valueOf((String) form_data.get("QTDTestes"))) {
					System.out.println("Testes: " + testes);
					System.out.println("<br>");
				}
				break;
			case "menor":
				if(Integer.valueOf(testes) < Integer.valueOf((String) form_data.get("QTDTestes"))) {
					System.out.println("Testes: " + testes);
					System.out.println("<br>");
				}
				break;
			case "igual":
				if(Integer.valueOf(testes) == Integer.valueOf((String) form_data.get("QTDTestes"))) {
					System.out.println("Testes: " + testes);
					System.out.println("<br>");
				}
				break;
			case "diferente":
				if(Integer.valueOf(testes) != Integer.valueOf((String) form_data.get("QTDTestes"))) {
					System.out.println("Testes: " + testes);
					System.out.println("<br>");
				}
				break;
			case "menorouigual":
				if(Integer.valueOf(testes) <= Integer.valueOf((String) form_data.get("QTDTestes"))) {
					System.out.println("Testes: " + testes);
					System.out.println("<br>");
				}
				break;
			case "maiorouigual":
				if(Integer.valueOf(testes) >= Integer.valueOf((String) form_data.get("QTDTestes"))) {
					System.out.println("Testes: " + testes);
					System.out.println("<br>");
				}
				break;
			}
		}
	}




	/**
	 * Passando os dados de uma certa regiao, o metodo vai comparar com as informacoes dadas pelo utilizador no formulario.
	 * Se for compativel com o filtro escolhido pelo utilizador, envia a info para a resposta (html).
	 * 
	 * @param infecoes - numero de infecoes na regiao a analisar
	 * @param internamentos - numero de internamentos na regiao a analisar
	 * @param testes - numero de testes na regiao a analisar
	 */
	private static void compareToRDF(Regiao regiao) {

		String infecoes = regiao.getInfecoes();
		String internamentos = regiao.getInternamentos();
		String testes = regiao.getTestes();

		if (form_data.containsKey("infCB")) {
			switch ((String) form_data.get("tipoOperadorInf")) {
			case "maior":
				if(Integer.valueOf(infecoes) > Integer.valueOf((String) form_data.get("QTDInf"))) {
					mostraRegiao(regiao);
					System.out.println("Infecoes: " + infecoes);
					System.out.println("<br>");
				}
				break;
			case "menor":
				if(Integer.valueOf(infecoes) < Integer.valueOf((String) form_data.get("QTDInf"))) {
					mostraRegiao(regiao);
					System.out.println("Infecoes: " + infecoes);
					System.out.println("<br>");
				}
				break;
			case "igual":
				if(Integer.valueOf(infecoes) == Integer.valueOf((String) form_data.get("QTDInf"))) {
					mostraRegiao(regiao);
					System.out.println("Infecoes: " + infecoes);
					System.out.println("<br>");
				}
				break;
			case "diferente":
				if(Integer.valueOf(infecoes) != Integer.valueOf((String) form_data.get("QTDInf"))) {
					mostraRegiao(regiao);
					System.out.println("Infecoes: " + infecoes);
					System.out.println("<br>");
				}
				break;
			case "menorouigual":
				if(Integer.valueOf(infecoes) <= Integer.valueOf((String) form_data.get("QTDInf"))) {
					mostraRegiao(regiao);
					System.out.println("Infecoes: " + infecoes);
					System.out.println("<br>");}
				break;
			case "maiorouigual":
				if(Integer.valueOf(infecoes) >= Integer.valueOf((String) form_data.get("QTDInf"))) {
					mostraRegiao(regiao);
					System.out.println("Infecoes: " + infecoes);
					System.out.println("<br>");
				}
				break;
			}
		}

		if (form_data.containsKey("intCB")) {
			switch ((String) form_data.get("tipoOperadorInt")) {
			case "maior":
				if(Integer.valueOf(internamentos) > Integer.valueOf((String) form_data.get("QTDInt"))) {
					mostraRegiao(regiao);
					System.out.println("Internamentos: " + internamentos);
					System.out.println("<br>");
				}
				break;
			case "menor":
				if(Integer.valueOf(internamentos) < Integer.valueOf((String) form_data.get("QTDInt"))) {
					mostraRegiao(regiao);
					System.out.println("Internamentos: " + internamentos);
					System.out.println("<br>");
				}
				break;
			case "igual":
				if(Integer.valueOf(internamentos) == Integer.valueOf((String) form_data.get("QTDInt"))) {
					mostraRegiao(regiao);
					System.out.println("Internamentos: " + internamentos);
					System.out.println("<br>");
				}
				break;
			case "diferente":
				if(Integer.valueOf(internamentos) != Integer.valueOf((String) form_data.get("QTDInt"))) {
					mostraRegiao(regiao);
					System.out.println("Internamentos: " + internamentos);
					System.out.println("<br>");
				}
				break;
			case "menorouigual":
				if(Integer.valueOf(internamentos) <= Integer.valueOf((String) form_data.get("QTDInt"))) {
					mostraRegiao(regiao);
					System.out.println("Internamentos: " + internamentos);
					System.out.println("<br>");
				}
				break;
			case "maiorouigual":
				if(Integer.valueOf(internamentos) >= Integer.valueOf((String) form_data.get("QTDInt"))) {
					mostraRegiao(regiao);
					System.out.println("Internamentos: " + internamentos);
				}
				break;
			}
		}
		if (form_data.containsKey("testesCB")) {
			switch ((String) form_data.get("tipoOperadorTestes")) {
			case "maior":
				if(Integer.valueOf(testes) > Integer.valueOf((String) form_data.get("QTDTestes"))) {
					mostraRegiao(regiao);
					System.out.println("Testes: " + testes);
					System.out.println("<br>");
				}
				break;
			case "menor":
				if(Integer.valueOf(testes) < Integer.valueOf((String) form_data.get("QTDTestes"))) {
					mostraRegiao(regiao);
					System.out.println("Testes: " + testes);
					System.out.println("<br>");
				}
				break;
			case "igual":
				if(Integer.valueOf(testes) == Integer.valueOf((String) form_data.get("QTDTestes"))) {
					mostraRegiao(regiao);
					System.out.println("Testes: " + testes);
					System.out.println("<br>");
				}
				break;
			case "diferente":
				if(Integer.valueOf(testes) != Integer.valueOf((String) form_data.get("QTDTestes"))) {
					mostraRegiao(regiao);
					System.out.println("Testes: " + testes);
					System.out.println("<br>");
				}
				break;
			case "menorouigual":
				if(Integer.valueOf(testes) <= Integer.valueOf((String) form_data.get("QTDTestes"))) {
					mostraRegiao(regiao);
					System.out.println("Testes: " + testes);
					System.out.println("<br>");
				}
				break;
			case "maiorouigual":
				if(Integer.valueOf(testes) >= Integer.valueOf((String) form_data.get("QTDTestes"))) {
					mostraRegiao(regiao);
					System.out.println("Testes: " + testes);
					System.out.println("<br>");
				}
				break;
			}
		}

	}



	/**
	 * Imprime no HTML o nome da regiao (antes do elemento a mostrar), se ainda nao tiver sido feito.
	 * @param Regiao do elemento a mostrar
	 */
	public static void mostraRegiao(Regiao regiao) {
		if(regiao.foiApresentado)
			return;
		System.out.println("<h2>" + regiao.getNome() + "</h2>");
		regiao.foiApresentado = true;
	}

}
