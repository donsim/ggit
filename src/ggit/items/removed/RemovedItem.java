package ggit.items.removed;

import java.util.Collection;

import org.eclipse.jface.action.Action;

import ggit.items.FileItem;

public class RemovedItem extends FileItem {

	public RemovedItem(String fileName, String status) {
		super(fileName, "Removed");
	}

	@Override
	public Collection<Action> availableActions() {
		// TODO Auto-generated method stub
		return null;
	}

}
