package ggit.status.removed;

import ggit.Config;
import ggit.status.FileAction;

 class RemoveAction extends FileAction{


	public RemoveAction(String filename) {
		super(filename);
		setText("Stage");
		setToolTipText(getText()+" "+filename);
	}


	@Override
	public void run() {
		Config.execGit("rm",getFileName());
	}


}
