package ggit.status.unknown;

import ggit.Config;
import ggit.status.StatusAction;
import ggit.views.StatusView;

public class AddAction extends StatusAction {

	private final String filename;

	public AddAction(String filename, StatusView view) {
		super(view);
		this.filename = filename;
		setText("Add");
	}

	@Override
	public void run() {
		Config.execGit("add",filename);
	}

}
