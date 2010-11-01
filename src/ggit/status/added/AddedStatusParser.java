package ggit.status.added;

import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;
import ggit.status.StatusAction;
import ggit.status.modified.UnStageAction;
import ggit.views.StatusView;

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
			public Collection<StatusAction> availableActions(StatusView view) {
				return Arrays.asList(new StatusAction[]{
					new UnStageAction(filename,view)
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
