package ggit.items;

import java.util.Collection;

import org.eclipse.jface.action.Action;

/**
 * One file or directory for managed entry in git.
 * @author Donatas
 *
 */
public abstract class FileItem {

	private final String fileName;
	private final String status;


	public FileItem(String fileName, String status) {
		super();
		this.fileName = fileName;
		this.status = status;
	}

	public abstract Collection<Action> availableActions();

	public String getFileName() {
		return fileName;
	}

	public String getStatus() {
		return status;
	}

}
