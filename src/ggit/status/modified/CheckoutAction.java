package ggit.status.modified;

import ggit.Config;
import ggit.status.FileAction;

import org.eclipse.jface.dialogs.MessageDialog;

public class CheckoutAction extends FileAction {

	private final boolean againstHead;

	public CheckoutAction(String filename, boolean againstHead) {
		super(filename, "Checkout");
		this.againstHead = againstHead;
	}

	@Override
	public void run() {
		if (MessageDialog.openConfirm(null, "Warning",
				"Changes in file will be lost")) {
			if (againstHead) {
				Config.execGit("checkout", "HEAD", getFileName());
			} else {
				Config.execGit("checkout", "HEAD", getFileName());
			}
		}
	}
}
