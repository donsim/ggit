package ggit.status.modified;

import ggit.Config;
import ggit.status.StatusAction;
import ggit.views.StatusView;

import org.eclipse.jface.dialogs.MessageDialog;

public class DiffAction extends StatusAction {

	private final String filename;
	private final boolean againstHead;

	public DiffAction(String filename, boolean againstHead,StatusView view) {
		super(view);
		this.filename = filename;
		this.againstHead = againstHead;
		setText("Diff");
	}

	@Override
	public void run() {
		String execGit;
		if (againstHead) {
			execGit = Config.execGit("diff","HEAD",filename);
		}else
		{
			execGit = Config.execGit("diff",filename);
		}
		MessageDialog.openInformation(null, "Diff for "+filename, execGit);
	}
}
