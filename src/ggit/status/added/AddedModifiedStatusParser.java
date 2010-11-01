package ggit.status.added;

import ggit.status.FileAction;
import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;
import ggit.status.modified.StageAction;

import java.util.Arrays;
import java.util.Collection;

public class AddedModifiedStatusParser extends SimpleStatusParser {

	public AddedModifiedStatusParser(){
		super("AM");
	}

	@Override
	protected FileItem createFileItem(final String filename) {
		return new FileItem(filename,"added to index, work tree changed since index", getStatusChars() )
		{

			@Override
			public Collection<FileAction> availableActions() {
				return Arrays.asList(new FileAction[]{new StageAction(filename)});
			}

			@Override
			public boolean isCommitable() {
				return false;
			}

		};
	}

}
