package ggit.status.unknown;

import java.util.Collection;

import org.eclipse.jface.action.Action;

import ggit.status.FileItem;
import ggit.status.SimpleStatusParser;

public class UnknownStatusParser extends SimpleStatusParser {

	public UnknownStatusParser(){
		super("??");
	}

	@Override
	protected FileItem createFileItem(String filename) {
		return new FileItem(filename,"untracked", getStatusChars() ){

			@Override
			public Collection<Action> availableActions() {
				// TODO Auto-generated method stub
				return null;
			}

		};
	}

}
