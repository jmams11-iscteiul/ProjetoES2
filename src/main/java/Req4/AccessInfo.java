package Req4;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.eclipse.jgit.transport.TransportHttp.AcceptEncoding;
import org.eclipse.jgit.treewalk.TreeWalk;


public class AccessInfo {

	private static final String REMOTE_URL = "https://github.com/vbasto-iscte/ESII1920.git";
	private static Repository repository;
	public static void main(String[] args) throws MissingObjectException, IncorrectObjectTypeException, IOException {
			AccessInfo accessinfo= new AccessInfo();
			try {
				ArrayList<Tag> tag = accessinfo.acederInfo();
			} catch (InvalidRemoteException e) {
				e.printStackTrace();
			} catch (TransportException e) {
				e.printStackTrace();
			} catch (GitAPIException e) {
				e.printStackTrace();
			}
	}

	public static ArrayList<Tag> acederInfo() throws InvalidRemoteException, TransportException, GitAPIException, IOException {
		ArrayList<Tag> filesInfo= new ArrayList<>();
		iniciarRepositorio();
			// get tags
			Map<String, Ref> tags = repository.getTags();
			for (Map.Entry<String,Ref> entry : tags.entrySet()) {
				RevWalk rw = getCommits(entry.getKey(), repository);
				for (RevCommit commit : rw) {
				Tag fileInfo = new Tag();
				// ---------------------file timestamp
		// ---------------------file name
		// ---------------------file tag
		// ---------------------tag description
		// spread visualisaation link
				fileInfo.setDate(getFileDate(commit));
				fileInfo.setFileName(getFileName(commit));
				fileInfo.setMessage(commit.getShortMessage());
				fileInfo.setName(entry.getKey());
				fileInfo.setLink("http://www.google.pt");
				String link = "http://visualdataweb.de/webvowl/" + "#iri=https://github.com/vbastoiscte/ESII1920/raw/"
						+ repository.getBranch() + "/" + //fileName;
				// "raw/master/covid19spreading.rdf"
				filesInfo.add(fileInfo);
				break;
			}
		}
		return filesInfo;
	}







	private static RevWalk getCommits(String tag,
			Repository repository)
			throws MissingObjectException, IncorrectObjectTypeException, IOException {
		try( RevWalk revWalk = new RevWalk( repository ) ) {
			ObjectId commitId = repository.resolve(tag);
			revWalk.markStart( revWalk.parseCommit(commitId ) );
			return revWalk;
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



private static String getFileDate(RevCommit commit){
	String dateAsText = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Date(commit.getCommitTime() * 1000L));
	return dateAsText;

}





	private static void iniciarRepositorio() throws InvalidRemoteException, TransportException, GitAPIException, IOException{
		//clone do repositório
		Git git;
		File file = new File("repository");
		if(!file.exists()){
			git = Git.cloneRepository().setURI(REMOTE_URL).setDirectory(file).call();
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