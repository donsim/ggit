package ggit.status.unknown;

import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.jface.action.Action;

public class UnknownStatusParser extends SimpleStatusParser {

	public UnknownStatusParser() {
		super("??");
	}

	@Override
	protected FileItem createFileItem(final String filename) {
		return new FileItem(filename, "untracked", getStatusChars()) {

			@Override
			public Collection<Action> availableActions() {
				return Arrays.asList(new Action[] { new AddAction(filename) });
			}

		};
	}

}
