package ggit.status.modified;

import ggit.Config;
import ggit.status.FileAction;
import ggit.views.StatusView;

public class UnStageAction extends FileAction{


	public UnStageAction(String filename) {
		super(filename);
		setText("Unstage");
	}

	@Override
	public void run() {
		Config.execGit("reset","HEAD",getFileName());
	}
}
