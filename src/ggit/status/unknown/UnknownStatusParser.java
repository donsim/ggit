package ggit.status.unknown;

import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;
import ggit.status.StatusAction;
import ggit.views.StatusView;

import java.util.Arrays;
import java.util.Collection;

public class UnknownStatusParser extends SimpleStatusParser {

	public UnknownStatusParser() {
		super("??");
	}

	@Override
	protected FileItem createFileItem(final String filename) {
		return new FileItem(filename, "untracked", getStatusChars()) {

			@Override
			public Collection<StatusAction> availableActions(StatusView view) {
				return Arrays.asList(new StatusAction[] { new AddAction(filename,view) });
			}

			@Override
			public boolean isCommitable() {
				// TODO Auto-generated method stub
				return false;
			}

		};
	}

}
