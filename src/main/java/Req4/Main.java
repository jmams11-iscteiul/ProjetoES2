package Req4;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.jgit.api.errors.GitAPIException;

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
