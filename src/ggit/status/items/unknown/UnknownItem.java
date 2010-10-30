package ggit.status.items.unknown;

import java.util.Collection;

import org.eclipse.jface.action.Action;

import ggit.status.items.FileItem;

 class UnknownItem extends FileItem {

	public UnknownItem(String fileName, String shortStatus) {
		super(fileName, "untracked", shortStatus);
	}

	@Override
	public Collection<Action> availableActions() {
		// TODO Auto-generated method stub
		return null;
	}

}
