package ggit.status.modified;

import ggit.Config;
import ggit.status.StatusAction;
import ggit.views.StatusView;

public class UnStageAction extends StatusAction{

	private final String filename;

	public UnStageAction(String filename, StatusView view) {
		super(view);
		this.filename = filename;
		setText("Unstage");
	}

	@Override
	public void run() {
		Config.execGit("reset","HEAD",filename);
	}
}
