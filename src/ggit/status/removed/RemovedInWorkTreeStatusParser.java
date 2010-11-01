package ggit.status.removed;

import ggit.status.FileAction;
import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;
import ggit.status.modified.CheckoutAction;

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
			public Collection<FileAction> availableActions() {
				return Arrays.asList(new FileAction[] {
						new RemoveAction(filename),
						new CheckoutAction(filename) });
			}

			@Override
			public boolean isCommitable() {
				// TODO Auto-generated method stub
				return false;
			}
		};
	}

}
