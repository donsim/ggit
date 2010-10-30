package ggit.status.added;

import java.util.Collection;

import org.eclipse.jface.action.Action;

import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;

public class AddedDeletedStatusParser extends SimpleStatusParser {

	public AddedDeletedStatusParser(){
		super("AD");
	}

	@Override
	protected FileItem createFileItem(String filename) {
		return new FileItem(filename,"added to index, deleted in work tree", getStatusChars() )
		{

			@Override
			public Collection<Action> availableActions() {
				// TODO Auto-generated method stub
				return null;
			}

		};
	}

}
