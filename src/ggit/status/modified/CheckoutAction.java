package ggit.status.modified;

import ggit.Config;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;

public class CheckoutAction extends Action {

	private final String filename;

	public CheckoutAction(String filename) {
		this.filename = filename;
		setText("Checkout");
	}

	@Override
	public void run() {
		if( MessageDialog.openConfirm(null, "Warning", "Changes in file will be lost") )
		{
			Config.execGit("checkout","HEAD",filename);
		}
	}
}
