package ggit.status.items.unknown;

import ggit.status.SimpleStatusParser;
import ggit.status.items.FileItem;

public class UnknownStatusParser extends SimpleStatusParser {

	public UnknownStatusParser(){
		super("??");
	}

	@Override
	protected FileItem createFileItem(String filename) {
		return new UnknownItem(filename, getStatusChars() );
	}

}
