package Req4;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
//http://github.com/vbasto-iscte/ESII1920
import java.util.Map;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.errors.CorruptObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.TreeWalk;


public class AccessInfo {

	private static final String REMOTE_URL = "https://github.com/vbasto-iscte/ESII1920.git";
	private static Repository repository;
	public static void main(String[] args) {
		try {
			iniciarRepositorio();
			// get tags
			Map<String, Ref> tags = repository.getTags();
			for (Map.Entry<String,Ref> entry : tags.entrySet()) {
				try( RevWalk revWalk = new RevWalk( repository ) ) {
					ObjectId commitId = repository.resolve( entry.getKey());
					revWalk.markStart( revWalk.parseCommit( commitId ) );
					for( RevCommit commit : revWalk ) {
						//get data do commit
						System.out.println();
						String dateAsText = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(commit.getCommitTime() * 1000L));
						String fileName = getFileName(commit);
						String link = "http://visualdataweb.de/webvowl/"+
						"#iri=https://github.com/vbastoiscte/ESII1920/raw/" + repository.getBranch()+"/" + fileName;
						//"raw/master/covid19spreading.rdf" 


						System.out.println( dateAsText );
						System.out.println(fileName);
						System.out.println(entry.getKey());
						System.out.println(commit.getShortMessage());
						System.out.println(link);
						System.out.println();

						break;
					}
				}
			}


			//---------------------file timestamp
			//---------------------file name
			//---------------------file tag 
			//---------------------tag description
			//spread visualisaation link



		} catch ( Exception e) {
			e.printStackTrace();
		}
	}

private static String getFileName(RevCommit commit) throws MissingObjectException, IncorrectObjectTypeException, CorruptObjectException, IOException{
	//nome do ficheiro
	String name = "";
	ObjectId treeId = commit.getTree();
	TreeWalk treeWalk = new TreeWalk(repository);
	treeWalk.reset(treeId);
	while (treeWalk.next()) {
		String path = treeWalk.getPathString();
		if(path.contains(".rdf"))
			name = path;
	}
	return name;
}


	private static void iniciarRepositorio() throws InvalidRemoteException, TransportException, GitAPIException, IOException{
		//clone do repositório
		Git git;
		File file = new File("repository");
		if(!file.exists()){
			git = Git.cloneRepository()
					.setURI(REMOTE_URL)
					.setDirectory(file)
					.call();
		}
		else
			git = Git.open(file);
		// fazer update (pull) do repositório
		PullCommand pullCmd = git.pull();
		pullCmd.call();
		// abrir repositório local com o git
		FileRepositoryBuilder repositoryBuilder = new FileRepositoryBuilder();
		repository = git.getRepository(); 
	}

}