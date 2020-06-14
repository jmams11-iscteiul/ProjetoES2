package Req4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.jgit.api.errors.GitAPIException;

/**
 * @author ricardo
 *class geral
 *unica que deve ser corrida
 *invoca a AccessInfo para obter  a informacao das tags e ficheiros
 *invoca a WriteTable para criar a tabela em html
 */
public class Main {
	public static void main(String[] args) {
		try {
			ArrayList<FileInfo> infos = AccessInfo.acederInfo();
			WriteTable.draw(infos);
			try (BufferedReader br = new BufferedReader(new FileReader("spreadTable.html"))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                System.out.println(line);
	            }
	        }
		} catch (GitAPIException | IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
