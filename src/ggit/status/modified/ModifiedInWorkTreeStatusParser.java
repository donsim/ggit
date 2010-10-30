package ggit.status.modified;

import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.jface.action.Action;

public class ModifiedInWorkTreeStatusParser extends SimpleStatusParser {

	public ModifiedInWorkTreeStatusParser(){
		super(" M");
	}

	@Override
	protected FileItem createFileItem(final String filename) {
		return new FileItem(filename, "work tree changed since index",getStatusChars() )
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
