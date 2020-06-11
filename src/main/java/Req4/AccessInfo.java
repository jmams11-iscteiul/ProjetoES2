package Req4;

//http://github.com/vbasto-iscte/ESII1920

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class AccessInfo {

	public static void main(String[] args) {

		Repository repository;
		try {
			FileRepositoryBuilder builder = new FileRepositoryBuilder();
			repository = builder.setGitDir(new File("http://github.com/vbasto-iscte/ESII1920")).readEnvironment()
					.findGitDir().build();

			List<Ref> call = new Git(repository).tagList().call();
			for (Ref ref : call) {
				System.out.println("Tag: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());

				LogCommand log = new Git(repository).log();

				Ref peeledRef = repository.peel(ref);
				if (peeledRef.getPeeledObjectId() != null) {
					log.add(peeledRef.getPeeledObjectId());
				} else {
					log.add(ref.getObjectId());
				}

				Iterable<RevCommit> logs = log.call();
				for (RevCommit rev : logs) {
					System.out.println(
							"Commit: " + rev /* + ", name: " + rev.getName() + ", id: " + rev.getId().getName() */);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}
}
