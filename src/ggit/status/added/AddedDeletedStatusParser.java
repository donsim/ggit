package ggit.status.added;

import java.util.Collection;

import org.eclipse.jface.action.Action;

import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;
import ggit.status.StatusAction;
import ggit.views.StatusView;

public class AddedDeletedStatusParser extends SimpleStatusParser {

	public AddedDeletedStatusParser(){
		super("AD");
	}

	@Override
	protected FileItem createFileItem(String filename) {
		return new FileItem(filename,"added to index, deleted in work tree", getStatusChars() )
		{

			@Override
			public Collection<StatusAction> availableActions(StatusView view) {
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
