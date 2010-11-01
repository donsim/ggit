package ggit.status.removed;

import ggit.Config;
import ggit.status.FileAction;

 class RemoveAction extends FileAction{


	public RemoveAction(String filename) {
		super(filename,"Stage");
	}


	@Override
	public void run() {
		Config.execGit("rm",getFileName());
	}


}
