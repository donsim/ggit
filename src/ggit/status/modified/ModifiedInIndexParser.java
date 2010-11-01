package ggit.status.modified;

import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;
import ggit.status.StatusAction;
import ggit.views.StatusView;

import java.util.Arrays;
import java.util.Collection;

public class ModifiedInIndexParser extends SimpleStatusParser {

	public ModifiedInIndexParser(){
		super("M ");
	}

	@Override
	protected FileItem createFileItem(final String filename) {
		return new FileItem(filename, "updated in index",getStatusChars() )
		{
			@Override
			public Collection<StatusAction> availableActions(StatusView view) {
				return Arrays.asList(new StatusAction[]{
					new UnStageAction(filename,view),
					new CheckoutAction(filename,view),
					new DiffAction(filename,true,view)
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
