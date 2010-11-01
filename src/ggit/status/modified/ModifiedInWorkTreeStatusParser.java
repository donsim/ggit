package ggit.status.modified;

import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;
import ggit.status.StatusAction;
import ggit.views.StatusView;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.jface.action.Action;

public class ModifiedInWorkTreeStatusParser extends SimpleStatusParser {

	public ModifiedInWorkTreeStatusParser(){
		super(" M");
	}

	@Override
	protected FileItem createFileItem(final String filename) {
		return new FileItem(filename, "work tree changed since index",getStatusChars() )
		{
			@Override
			public Collection<StatusAction> availableActions(StatusView view) {
				return Arrays.asList(new StatusAction[]{
					new StageAction(filename,view),
					new DiffAction(filename,false,view)
				}
				);
			}

			@Override
			public boolean isCommitable() {
				return false;
			}

		};
	}

}
