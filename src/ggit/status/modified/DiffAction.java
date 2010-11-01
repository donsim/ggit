package ggit.status.modified;

import ggit.Config;
import ggit.status.FileAction;

import org.eclipse.jface.dialogs.MessageDialog;

public class DiffAction extends FileAction {

	private final boolean againstHead;

	public DiffAction(String filename, boolean againstHead) {
		super(filename);
		this.againstHead = againstHead;
		setText("Diff");
	}

	@Override
	public void run() {
		String execGit;
		if (againstHead) {
			execGit = Config.execGit("diff","HEAD",getFileName());
		}else
		{
			execGit = Config.execGit("diff",getFileName());
		}
		MessageDialog.openInformation(null, "Diff for "+getFileName(), execGit);
	}
}
