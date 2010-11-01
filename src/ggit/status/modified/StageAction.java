package ggit.status.modified;

import ggit.Config;
import ggit.status.StatusAction;
import ggit.views.StatusView;

public class StageAction extends StatusAction{

	private final String filename;


	public StageAction(String filename, StatusView view) {
		super(view);
		this.filename = filename;
		setText("Stage");
		setToolTipText(getText()+" "+filename);
	}


	@Override
	public void run() {
		Config.execGit("add",filename);
		refresh();
	}


}
