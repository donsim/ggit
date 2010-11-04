package ggit.status.modified;

import ggit.OpenInEditor;
import ggit.status.FileAction;
import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;

import java.util.Arrays;
import java.util.Collection;

public class ModifiedInIndexParser extends SimpleStatusParser {

	public ModifiedInIndexParser(){
		super("M ");
	}

	@Override
	protected FileItem createFileItem(final String filename) {
		return new FileItem(filename, "updated in index",getStatusChars() )
		{
			@Override
			public Collection<FileAction> availableActions() {
				return Arrays.asList(new FileAction[]{
					new OpenInEditor(filename),
					new UnStageAction(filename),
					new CheckoutAction(filename,true),
					new DiffAction(filename,true)
				}
				);
			}

			@Override
			public boolean isCommitable() {
				return true;
			}

			@Override
			protected String getImageFileName() {
				return "modified.png";
			}

		};
	}

}
