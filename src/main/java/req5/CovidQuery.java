package req5;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class CovidQuery {

	public static final String FILE_LINK = "https://raw.githubusercontent.com/vbasto-iscte/ESII1920/master/covid19spreading.rdf";

	private static File ficheiro;


	public static void main(String[] args) throws Exception {
		CovidQuery cq = new CovidQuery();
		cq.query("/RDF/NamedIndividual/@*");
	}

	public void downloadFicheiro() throws IOException {
		URL linkFicheiro = new URL(FILE_LINK);
		ReadableByteChannel rbc = Channels.newChannel(linkFicheiro.openStream());

		try (FileOutputStream fos = new FileOutputStream("covid19spreading.rdf")) {
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		}
		ficheiro = new File("covid19spreading.rdf");
	}
	
	
	/**
	 * 
	 * @return uma lista de nomes das regioes disponiveis no rdf (sem as que estao sem dados)
	 * @throws Exception
	 */
	public ArrayList<String> obterRegioes() throws Exception {
		downloadFicheiro();
		FileInputStream fileIS = new FileInputStream(ficheiro);
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document xmlDocument = builder.parse(fileIS);	
		xmlDocument.getDocumentElement().normalize();
		String query = "/RDF/NamedIndividual/@*";
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		XPathExpression expr = xpath.compile(query);         
		NodeList nl = (NodeList) expr.evaluate(xmlDocument, XPathConstants.NODESET);
		ArrayList<String> regioes = new ArrayList<String>();
		for (int i = 0; i < nl.getLength(); i++) {
			regioes.add((StringUtils.substringAfter(nl.item(i).getNodeValue(), "#")));
		}
		
		//remove as regioes sem dados
		Iterator<String> itr = regioes.iterator();
		while (itr.hasNext()) {
			String regiao = itr.next();
			String infecoes = query("//*[contains(@about,'" + regiao +"')]/Infecoes/text()");
			String testes = query("//*[contains(@about,'" + regiao +"')]/Testes/text()");
			String internamentos = query("//*[contains(@about,'" + regiao +"')]/Internamentos/text()");

			if(infecoes.isEmpty() && testes.isEmpty() && internamentos.isEmpty())
					itr.remove();			
		}
		return regioes;
	}
	
	public ArrayList<Regiao> getDadosRegioes() throws Exception {
		ArrayList<String> listaR = obterRegioes();
		ArrayList<Regiao> dados = new ArrayList<Regiao>();
		for (String regiao: listaR) {
			String infecoes = query("//*[contains(@about,'" + regiao +"')]/Infecoes/text()");
			String testes = query("//*[contains(@about,'" + regiao +"')]/Testes/text()");
			String internamentos = query("//*[contains(@about,'" + regiao +"')]/Internamentos/text()");
			dados.add(new Regiao(regiao, infecoes, internamentos, testes));
		}
		return dados;
	}

	
	
	
	/*
	Query para obter o numero de testes feitos no Algarve: "//*[contains(@about,'Algarve')]/Testes/text()"
	Query para obter o numero de infecoes no Algarve: "//*[contains(@about,'Algarve')]/Infecoes/text()"
	Query para obter o numero de internamentos no Algarve: "//*[contains(@about,'Algarve')]/Internamentos/text()"
	*/
	public String query(String queryC) throws Exception {
		downloadFicheiro();
		FileInputStream fileIS = new FileInputStream(ficheiro);
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document xmlDocument = builder.parse(fileIS);	
		xmlDocument.getDocumentElement().normalize();

		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		
		XPathExpression expr = xpath.compile(queryC);
		return (String) expr.evaluate(xmlDocument, XPathConstants.STRING);
	}
}
