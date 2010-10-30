package ggit.status.modified;

import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.jface.action.Action;

public class ModifiedInIndexParser extends SimpleStatusParser {

	public ModifiedInIndexParser(){
		super("M ");
	}

	@Override
	protected FileItem createFileItem(final String filename) {
		return new FileItem(filename, "updated in index",getStatusChars() )
		{
			@Override
			public Collection<Action> availableActions() {
				return Arrays.asList(new Action[]{
					new UnStageAction(filename),
					new CheckoutAction(filename),
					new DiffAction(filename,true)
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
