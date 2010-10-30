package ggit.status.modified;

import org.eclipse.jface.action.Action;

public class UnStageAction extends Action {

	private final String filename;

	public UnStageAction(String filename) {
		this.filename = filename;
		setText("Unstage");
	}

}
