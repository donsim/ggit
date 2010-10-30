package ggit.status.items.modified;

import ggit.status.SimpleStatusParser;
import ggit.status.items.FileItem;

public class ModifiedInWorkTreeStatusParser extends SimpleStatusParser {

	public ModifiedInWorkTreeStatusParser(){
		super(" M");
	}

	@Override
	protected FileItem createFileItem(String filename) {
		return new ModifiedInWorkTreeItem(filename, getStatusChars() );
	}

}
