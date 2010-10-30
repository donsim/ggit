package ggit.status.items.removed;

import java.util.Collection;

import org.eclipse.jface.action.Action;

import ggit.status.items.FileItem;

public class RemovedInWorkTreeItem extends FileItem {

	public RemovedInWorkTreeItem(String fileName, String shortStatus) {
		super(fileName, "deleted in work tree", shortStatus);
	}

	@Override
	public Collection<Action> availableActions() {
		// TODO Auto-generated method stub
		return null;
	}

}
