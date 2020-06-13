package Req4;

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
		} catch (GitAPIException | IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
