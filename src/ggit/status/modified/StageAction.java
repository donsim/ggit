package ggit.status.modified;

import ggit.Config;
import ggit.status.FileAction;

public class StageAction extends FileAction{


	public StageAction(String filename) {
		super(filename);
		setText("Stage");
		setToolTipText(getText()+" "+filename);
	}


	@Override
	public void run() {
		Config.execGit("add",getFileName());
	}


}
