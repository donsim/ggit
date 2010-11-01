package ggit.status.removed;

import ggit.status.FileAction;
import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;
import ggit.status.modified.CheckoutAction;
import ggit.status.modified.StageAction;

import java.util.Arrays;
import java.util.Collection;

public class RemovedInIndexStatusParser extends SimpleStatusParser {

	public RemovedInIndexStatusParser() {
		super("D ");
	}

	@Override
	protected FileItem createFileItem(final String filename) {
		return new FileItem(filename, "deleted in index", getStatusChars()) {
			@Override
			public Collection<FileAction> availableActions() {
				return Arrays.asList(new FileAction[] {
						new StageAction(filename),
						new CheckoutAction(filename) });
			}

			@Override
			public boolean isCommitable() {
				return true;
			}
		};
	}

}
