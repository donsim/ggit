package ggit.status.modified;

import ggit.status.FileAction;
import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;

import java.util.Arrays;
import java.util.Collection;

public class ModifiedInIndexAndInWorkDirParser extends SimpleStatusParser {

	public ModifiedInIndexAndInWorkDirParser(){
		super("MM");
	}

	@Override
	protected FileItem createFileItem(final String filename) {
		return new FileItem(filename, " Changed but not updated",getStatusChars() )
		{
			@Override
			public Collection<FileAction> availableActions() {
				return Arrays.asList(new FileAction[]{
					new StageAction(filename)
				}
				);
			}

			@Override
			public boolean isCommitable() {
				return false;
			}

			@Override
			protected String getImageFileName() {
				return "modified.png";
			}

		};
	}

}
