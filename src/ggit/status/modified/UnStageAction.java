package ggit.status.modified;

import ggit.Config;
import ggit.status.FileAction;

public class UnStageAction extends FileAction{


	public UnStageAction(String filename) {
		super(filename, "Unstage");
	}

	@Override
	public void run() {
		Config.execGit("reset","HEAD",getFileName());
	}
}
