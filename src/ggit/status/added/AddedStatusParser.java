package ggit.status.added;

import java.util.Collection;

import org.eclipse.jface.action.Action;

import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;

public class AddedStatusParser extends SimpleStatusParser {

	public AddedStatusParser(){
		super("A ");
	}

	@Override
	protected FileItem createFileItem(String filename) {
		return new FileItem(filename,"added to index", getStatusChars() )
		{

			@Override
			public Collection<Action> availableActions() {
				// TODO Auto-generated method stub
				return null;
			}

		};
	}

}
