/**
 *
 */
package ggit.views;

import java.util.List;

import ggit.Activator;
import ggit.Config;
import ggit.status.Status;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class RefreshStatusAction extends Action{
	private final class UpdateStatusRunnable implements Runnable {
		@Override
		public void run() {
			statusView.markAsOutdated();

			String result = Config.execGit("status", "-s");
			status.setOutput(result);
			List<String> unrecognized = status.getUnrecognized();
			if (unrecognized.size() > 0) {
				statusView.showMessage("unrecognized" + unrecognized);
			}

			statusView.refreshViewAssync();
		}
	}

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
		this.setImageDescriptor(Activator.getImageDescriptor("icons/refresh.png"));

	}

	public void run() {
		try {
			Runnable runnable = new UpdateStatusRunnable();
			new Thread(runnable).start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}