package ggit.status.removed;

import java.util.Collection;

import org.eclipse.jface.action.Action;

import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;

public class RemovedInWorkTreeStatusParser extends SimpleStatusParser {

	public RemovedInWorkTreeStatusParser(){
		super(" D");
	}

	@Override
	protected FileItem createFileItem(String filename) {
		return new FileItem(filename,  "deleted in work tree",getStatusChars() )
		{
			@Override
			public Collection<Action> availableActions() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isCommitable() {
				// TODO Auto-generated method stub
				return false;
			}
		};
	}

}
