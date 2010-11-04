package ggit.status.modified;

import ggit.OpenInEditor;
import ggit.status.FileAction;
import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;

import java.util.Arrays;
import java.util.Collection;

public class ModifiedInWorkTreeStatusParser extends SimpleStatusParser {

	public ModifiedInWorkTreeStatusParser(){
		super(" M");
	}

	@Override
	protected FileItem createFileItem(final String filename) {
		return new FileItem(filename, "work tree changed since index",getStatusChars() )
		{
			@Override
			public Collection<FileAction> availableActions() {
				return Arrays.asList(new FileAction[]{
					new OpenInEditor(filename),
					new StageAction(filename),
					new DiffAction(filename,false),
					new CheckoutAction(filename,false)
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
