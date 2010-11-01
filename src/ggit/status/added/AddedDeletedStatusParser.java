package ggit.status.added;

import ggit.status.FileAction;
import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;

import java.util.Collection;

public class AddedDeletedStatusParser extends SimpleStatusParser {

	public AddedDeletedStatusParser(){
		super("AD");
	}

	@Override
	protected FileItem createFileItem(String filename) {
		return new FileItem(filename,"added to index, deleted in work tree", getStatusChars() )
		{

			@Override
			public Collection<FileAction> availableActions() {
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
