package ggit.status.modified;

import ggit.Config;
import ggit.status.StatusAction;
import ggit.views.StatusView;

import org.eclipse.jface.dialogs.MessageDialog;

public class CheckoutAction extends StatusAction{

	private final String filename;

	public CheckoutAction(String filename, StatusView view) {
		super(view);
		this.filename = filename;
		setText("Checkout");
	}

	@Override
	public void run() {
		if (MessageDialog.openConfirm(null, "Warning",
				"Changes in file will be lost")) {
			Config.execGit("checkout", "HEAD", filename);
		}
	}
}
