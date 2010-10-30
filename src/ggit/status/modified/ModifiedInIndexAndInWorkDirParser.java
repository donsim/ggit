package ggit.status.modified;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.jface.action.Action;

import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;

public class ModifiedInIndexAndInWorkDirParser extends SimpleStatusParser {

	public ModifiedInIndexAndInWorkDirParser(){
		super("MM");
	}

	@Override
	protected FileItem createFileItem(final String filename) {
		return new FileItem(filename, " Changed but not updated",getStatusChars() )
		{
			@Override
			public Collection<Action> availableActions() {
				return Arrays.asList(new Action[]{
					new StageAction(filename)
				}
				);
			}

			@Override
			public boolean isCommitable() {
				return false;
			}

		};
	}

}
