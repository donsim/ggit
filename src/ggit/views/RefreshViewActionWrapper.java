package ggit.views;


import org.eclipse.jface.action.Action;

public class RefreshViewActionWrapper extends Action {

	private final Action old;
	private final StatusView view;

	public RefreshViewActionWrapper(Action old, StatusView view)
	{
		this.old = old;
		this.view = view;
		this.setText(old.getText());
		this.setDescription(old.getDescription());
		this.setToolTipText(old.getToolTipText());
		this.setImageDescriptor(old.getImageDescriptor());
	}

	@Override
	public void run() {
		old.run();
		view.refereshStatus();
	}

}
