package ggit.status.added;

import ggit.status.FileAction;
import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;
import ggit.status.modified.UnStageAction;

import java.util.Arrays;
import java.util.Collection;

public class AddedStatusParser extends SimpleStatusParser {

	public AddedStatusParser(){
		super("A ");
	}

	@Override
	protected FileItem createFileItem(final String filename) {
		return new FileItem(filename,"added to index", getStatusChars() )
		{

			@Override
			public Collection<FileAction> availableActions() {
				return Arrays.asList(new FileAction[]{
					new UnStageAction(filename)
				}
				);
			}

			@Override
			public boolean isCommitable() {
				return true;
			}

		};
	}

}
