/**
 *
 */
package ggit.status;


import ggit.Config;
import ggit.views.StatusView;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class RefreshStatusAction extends Action {
	private final StatusView statusView;
	private final Status status;

	/**
	 * @param statusView
	 */
	public RefreshStatusAction(StatusView statusView, Status status) {
		this.statusView = statusView;
		this.status = status;
		this.setText("Refresh");
		this.setToolTipText("Refresh staus of repository");
		this.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_DEF_VIEW));

	}

	public void run() {
		this.statusView.showMessage("Refresh action");
		try {
			String result = Config.execGit("status", "-s");
			this.statusView.showMessage("result"+result);
			status.setOutput(result);
			statusView.refreshView();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}