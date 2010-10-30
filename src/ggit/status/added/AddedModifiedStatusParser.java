package ggit.status.added;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.jface.action.Action;

import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;
import ggit.status.modified.StageAction;

public class AddedModifiedStatusParser extends SimpleStatusParser {

	public AddedModifiedStatusParser(){
		super("AM");
	}

	@Override
	protected FileItem createFileItem(final String filename) {
		return new FileItem(filename,"added to index, work tree changed since index", getStatusChars() )
		{

			@Override
			public Collection<Action> availableActions() {
				return Arrays.asList(new Action[]{new StageAction(filename)});
			}

		};
	}

}
