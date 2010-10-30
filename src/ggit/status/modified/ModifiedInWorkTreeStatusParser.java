package ggit.status.modified;

import java.util.Collection;

import org.eclipse.jface.action.Action;

import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;

public class ModifiedInWorkTreeStatusParser extends SimpleStatusParser {

	public ModifiedInWorkTreeStatusParser(){
		super(" M");
	}

	@Override
	protected FileItem createFileItem(String filename) {
		return new FileItem(filename, "work tree changed since index",getStatusChars() )
		{
			@Override
			public Collection<Action> availableActions() {
				// TODO Auto-generated method stub
				return null;
			}

		};
	}

}
