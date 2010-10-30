package ggit.status.items.modified;

import java.util.Collection;

import org.eclipse.jface.action.Action;

import ggit.status.items.FileItem;

public class ModifiedInWorkTreeItem extends FileItem {

	public ModifiedInWorkTreeItem(String fileName, String shortStatus) {
		super(fileName, "work tree changed since index", shortStatus);
	}

	@Override
	public Collection<Action> availableActions() {
		// TODO Auto-generated method stub
		return null;
	}

}
