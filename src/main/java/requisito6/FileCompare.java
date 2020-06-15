package requisito6;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;


import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch.Diff;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;
/**
 * 
 * FileCompare compares the difference between the two last covid19spreading.rdf file versions that have tags associated
 * 
 * 
 * Date: Jun 10-2020
 * 
 * @author Filipe Cruz
 * @version 1.0
 * 
 * 
 *
 */
public class FileCompare {
	/**
	 * Represents the url of the git repository.
	 */
	private String remote_url;
	/**
	 * Represents the name of the file to compare.
	 */
	private String filename;
	/**
	 * Represents the git repository.
	 */
	private Git repository;
	/**
	 * Represents the String of the penultimate file with a tag associated.
	 */
	private String beforeChanges;
	/**
	 * Represents the String of the last file with a tag associated.
	 */
	private String afterChanges;
	/**
	 * Represents the String of the penultimate file with a tag associated, converted to HTML.
	 */
	private String rawVersion;
	/**
	 * Represents the String of the file differences between both files, converted to HTML.
	 */
	private String diffVersion;
	/**
	 * Clones the git repository to a local file and sets the repository.
	 */
	public void createTempRepo() throws IOException, GitAPIException {
		readConfig();
		File localPath = File.createTempFile("TestGitRepository", "");
		localPath.delete();
		repository = Git.cloneRepository()
				.setURI(remote_url)
				.setDirectory(localPath)
				.call();
	}
	/**
	 * Gets the last two tags from the repository.
	 */
	public List<String> getLastTwoTags() throws GitAPIException {
		List<Ref> list = repository.tagList().call();
		Iterable<RevCommit> commits = repository.log().call();
		Iterator<RevCommit> it = commits.iterator();
		int count = 0;
		List<String> ids = new ArrayList<String>();
		while(it.hasNext() && count < 2) {
			RevCommit commit1 = it.next();
			String commitId = commit1.getName();
			for(Ref tag : list) {
				ObjectId tagid = tag.getObjectId();
				if (tagid.getName().equals(commitId)) {
					ids.add(commitId);
					count++;
				}
			}
		}
		return ids;
	}
	/**
	 * Gets the last two file versions with tags associated from the repository in String format.
	 * @param ids is a List of String that contains the last two file versions.
	 */
	public void fileVersions(List<String> ids) throws MissingObjectException, IncorrectObjectTypeException, IOException {
		for(int i = 0; i < 2 ; i++) {
			RevWalk revWalk = new RevWalk(repository.getRepository());
			RevCommit commit = revWalk.parseCommit(ObjectId.fromString(ids.get(i)));
			RevTree tree = commit.getTree();
			TreeWalk treeWalk = new TreeWalk(repository.getRepository());
			treeWalk.addTree(tree);
			treeWalk.setRecursive(true);
			treeWalk.setFilter(PathFilter.create(filename));
			if (!treeWalk.next()) {
				throw new IllegalStateException("Did not find expected file 'covid19spreading.rdf'");
			}
			ObjectId objectId = treeWalk.getObjectId(0);
			ObjectLoader loader = repository.getRepository().open(objectId);		
			if(i == 0) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				loader.copyTo(baos);
				afterChanges = baos.toString();
			}
			if(i == 1) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				loader.copyTo(baos);
				beforeChanges = baos.toString();
			}
			revWalk.dispose();
		}
	}
	/**
	 * Checks the differences between both files in String format, does the highlighting and converts them to HTML. 
	 */
	public void fileDiff() {
		LinkedList<Diff> diffs;
		DiffMatchPatch diffMatchPatch = new DiffMatchPatch();
		diffs = diffMatchPatch.diffMain(beforeChanges, afterChanges);
		diffMatchPatch.diffCleanupSemantic(diffs);
		diffVersion = diffMatchPatch.diffPrettyHtml(diffs);
		List<Diff> diffs1;
		DiffMatchPatch diffMatchPatch1 = new DiffMatchPatch();
		diffs1 = diffMatchPatch1.diffMain(beforeChanges, beforeChanges);
		rawVersion = diffMatchPatch1.diffPrettyHtml(diffs1);
	}
	/**
	 * @return the String of the file highlighting the file differences between both versions, in a HTML report.
	 */
	public String getDiffVersion() {
		return diffVersion;
	}
	/**
	 * @return the String of the penultimate file, in a HTML report.
	 */
	public String getRawVersion() {
		return rawVersion;
	}
	/**
	 * @return the git repository.
	 */
	public Git getRepository() {
		return repository;
	}
	/**
	 * Makes all the process and prints the output.
	 */
	public void computeResults() {
		try {
			createTempRepo();
			List<String> tags = getLastTwoTags();
			fileVersions(tags);
			fileDiff();
			System.out.println(outputString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Reads the configuration file and sets up the variables.
	 */
	public void readConfig() {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("evodiffconfig.ini"));
			remote_url = properties.getProperty("REMOTE_URL");
			filename = properties.getProperty("FILENAME");
		} catch (IOException e) {
			System.out.println("evodiffconfig.ini file wasn't found.");
			return;
		}
	}
	/**
	 * @return the output String of the differences between versions in HTML format with the HTML templated generated by the class GenerateHTML
	 */
	public String outputString() {
		GenerateHTML generateHTML = new GenerateHTML();
		return generateHTML.printToString(getDiffVersion(), getRawVersion());
	}

}


