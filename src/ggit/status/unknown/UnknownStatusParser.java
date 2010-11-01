package ggit.status.unknown;

import ggit.status.FileAction;
import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;

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
			public Collection<FileAction> availableActions() {
				return Arrays.asList(new FileAction[] { new AddAction(filename),
						new IgnoreAction(filename),
						new RemoveFileAction(filename)});
			}

			@Override
			public boolean isCommitable() {
				return false;
			}

		};
	}

}
