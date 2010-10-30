package ggit.status.added;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.jface.action.Action;

import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;
import ggit.status.modified.UnStageAction;

public class AddedStatusParser extends SimpleStatusParser {

	public AddedStatusParser(){
		super("A ");
	}

	@Override
	protected FileItem createFileItem(final String filename) {
		return new FileItem(filename,"added to index", getStatusChars() )
		{

			@Override
			public Collection<Action> availableActions() {
				return Arrays.asList(new Action[]{
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
