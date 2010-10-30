package ggit.status.items.removed;

import ggit.status.SimpleStatusParser;
import ggit.status.items.FileItem;

public class RemovedInWorkTreeStatusParser extends SimpleStatusParser {

	public RemovedInWorkTreeStatusParser(){
		super(" D");
	}

	@Override
	protected FileItem createFileItem(String filename) {
		return new RemovedInWorkTreeItem(filename, getStatusChars() );
	}

}
