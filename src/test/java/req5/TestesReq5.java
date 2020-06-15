package req5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;


class TestesReq5 {
	

//-------- TESTES CovidQuery.java --------------------
	
	@Test
	void testDownloadFicheiro() throws Exception {
		CovidQuery cq = new CovidQuery();
		cq.downloadFicheiro();
		File f = new File("covid19spreading.rdf");
		assertTrue(f.exists() && !f.isDirectory());
	}

	@Test
	void testObterRegioes() throws Exception {
		CovidQuery cq = new CovidQuery();
		ArrayList<String> al = cq.obterRegioes();
		assertTrue(al.size() > 1);
	}

	@Test
	void testQuery() throws Exception {
		CovidQuery cq = new CovidQuery();
		String res = cq.query("//*[contains(@about,'Alentejo')]/Internamentos/text()");
		assertEquals(res, String.valueOf(50));
	}

//-------- TESTES Hello.java --------------------
	
	@SuppressWarnings({ "static-access", "unchecked" })
	@Test
	void testVerificarErrosInput() {
		Hello h = new Hello();
		h.form_data.put("testesCB", "testes1");
		h.form_data.put("QTDTestes", "");
		assertTrue(h.verificarErrosInput() == true);
	}
	
	@Test
	void testMostraRegiao() {
		Regiao reg = new Regiao("Teste");
		Hello.mostraRegiao(reg);
		assertEquals(true, reg.foiApresentado);
	}
	
}
