package ggit.status.modified;

import ggit.Config;
import ggit.status.FileAction;

import org.eclipse.jface.dialogs.MessageDialog;

public class CheckoutAction extends FileAction{

	public CheckoutAction(String filename) {
		super(filename);
		setText("Checkout");
	}

	@Override
	public void run() {
		if (MessageDialog.openConfirm(null, "Warning",
				"Changes in file will be lost")) {
			Config.execGit("checkout", "HEAD", getFileName());
		}
	}
}
