package ggit.status.added;

import java.util.Collection;

import org.eclipse.jface.action.Action;

import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;

public class AddedModifiedStatusParser extends SimpleStatusParser {

	public AddedModifiedStatusParser(){
		super("AM");
	}

	@Override
	protected FileItem createFileItem(String filename) {
		return new FileItem(filename,"added to index, work tree changed since index", getStatusChars() )
		{

			@Override
			public Collection<Action> availableActions() {
				// TODO Auto-generated method stub
				return null;
			}

		};
	}

}
