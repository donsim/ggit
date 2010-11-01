package ggit.status;

import ggit.views.StatusView;

import org.eclipse.jface.action.Action;

public class StatusAction extends Action {

	private final StatusView view;

	public StatusAction(StatusView view){
		this.view = view;
	}

	protected void refresh() {
		view.refereshStatus();
	}

}
