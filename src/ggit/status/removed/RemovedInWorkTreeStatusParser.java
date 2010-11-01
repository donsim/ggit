package ggit.status.removed;

import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;
import ggit.status.StatusAction;
import ggit.status.modified.CheckoutAction;
import ggit.status.modified.StageAction;
import ggit.views.StatusView;

import java.util.Arrays;
import java.util.Collection;

public class RemovedInWorkTreeStatusParser extends SimpleStatusParser {

	public RemovedInWorkTreeStatusParser() {
		super(" D");
	}

	@Override
	protected FileItem createFileItem(final String filename) {
		return new FileItem(filename, "deleted in work tree", getStatusChars()) {
			@Override
			public Collection<StatusAction> availableActions(StatusView view) {
				return Arrays.asList(new StatusAction[] {
						new StageAction(filename, view),
						new CheckoutAction(filename, view) });
			}

			@Override
			public boolean isCommitable() {
				// TODO Auto-generated method stub
				return false;
			}
		};
	}

}
