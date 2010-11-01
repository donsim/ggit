package ggit.status.added;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.jface.action.Action;

import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;
import ggit.status.StatusAction;
import ggit.status.modified.StageAction;
import ggit.views.StatusView;

public class AddedModifiedStatusParser extends SimpleStatusParser {

	public AddedModifiedStatusParser(){
		super("AM");
	}

	@Override
	protected FileItem createFileItem(final String filename) {
		return new FileItem(filename,"added to index, work tree changed since index", getStatusChars() )
		{

			@Override
			public Collection<StatusAction> availableActions(StatusView view) {
				return Arrays.asList(new StatusAction[]{new StageAction(filename,view)});
			}

			@Override
			public boolean isCommitable() {
				return false;
			}

		};
	}

}
