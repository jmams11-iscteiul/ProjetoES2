package Req4;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;


public class AccessInfo {

	private static final String REMOTE_URL = "https://github.com/vbasto-iscte/ESII1920.git";
	private static final String URL_GRAPH =	"http://visualdataweb.de/webvowl/#iri=https://github.com/vbasto-iscte/ESII1920/raw/master/covid19spreading.rdf";
	private static Repository repository;

	public static ArrayList<FileInfo> acederInfo() throws InvalidRemoteException, TransportException, GitAPIException, IOException {
		ArrayList<FileInfo> filesInfo= new ArrayList<>();
		iniciarRepositorio();
			Map<String, Ref> tags = repository.getTags();
			for (Map.Entry<String,Ref> entry : tags.entrySet()) {
				RevWalk rw = getCommits(entry.getKey(), repository);
				RevCommit rc = rw.parseCommit(entry.getValue().getObjectId());
					RevTree rt = rc.getTree();
					TreeWalk tw = createTreeWalk(rt);
					while(tw.next()){
						if(tw.isSubtree())
							tw.enterSubtree();
						else
							filesInfo.add(createFile(rc, entry.getKey()));
					}
			}
			return filesInfo;
		}

		private static TreeWalk createTreeWalk(RevTree rt)
				throws MissingObjectException, IncorrectObjectTypeException, CorruptObjectException, IOException {
			TreeWalk tw = new TreeWalk(repository);
			tw.setRecursive(false);
			tw.addTree(rt);
			tw.setFilter(PathFilter.create("covid19spreading.rdf"));
			return tw;

		}
	

private static FileInfo createFile(RevCommit rc, String  key)
		throws MissingObjectException, IncorrectObjectTypeException, CorruptObjectException, IOException {
	FileInfo fileInfo = new FileInfo();
	fileInfo.setDate(getFileDate(rc)); // data
	fileInfo.setFileName(getFileName(rc)); // nome do ficheiro
	fileInfo.setMessage(rc.getShortMessage());
	fileInfo.setName(key); // taName
	fileInfo.setLink(URL_GRAPH.replace("master", key)); //link
	return fileInfo;

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
		//clone do repositorio
		Git git;
		File file = new File("repository");
		if(!file.exists()){
			git = Git.cloneRepository().setURI(REMOTE_URL).setDirectory(file).call();
		}
		else
			git = Git.open(file);
		// fazer update (pull) do reposito	rio
		PullCommand pullCmd = git.pull();
		pullCmd.call();
		// abrir repositorio local com o git
		FileRepositoryBuilder repositoryBuilder = new FileRepositoryBuilder();
		repository = git.getRepository(); 
	}

}