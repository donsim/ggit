package ggit.status.modified;

import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;
import ggit.status.StatusAction;
import ggit.views.StatusView;

import java.util.Arrays;
import java.util.Collection;

public class ModifiedInIndexAndInWorkDirParser extends SimpleStatusParser {

	public ModifiedInIndexAndInWorkDirParser(){
		super("MM");
	}

	@Override
	protected FileItem createFileItem(final String filename) {
		return new FileItem(filename, " Changed but not updated",getStatusChars() )
		{
			@Override
			public Collection<StatusAction> availableActions(StatusView view) {
				return Arrays.asList(new StatusAction[]{
					new StageAction(filename,view)
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
