/**
 *
 */
package ggit.status;

import java.util.List;

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
		this.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_DEF_VIEW));

	}

	public void run() {
		try {
			String result = Config.execGit("status", "-s");
			status.setOutput(result);
			List<String> unrecognized = status.getUnrecognized();
			if (unrecognized.size() > 0) {
				this.statusView.showMessage("unrecognized" + unrecognized);
			}
			statusView.refreshView();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}