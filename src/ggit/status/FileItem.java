package ggit.status;

import java.util.Collection;

/**
 * One file or directory for managed entry in git.
 * @author Donatas
 *
 */
public abstract class FileItem {

	private final String fileName;
	private final String description;
	private final String shortStatus;


	public FileItem(String fileName, String description, String shortStatus) {
		super();
		this.fileName = fileName;
		this.description = description;
		this.shortStatus = shortStatus;
	}

	public abstract Collection<FileAction> availableActions();

	public String getFileName() {
		return fileName;
	}

	public String getDescription() {
		return description;
	}

	public String getShortStatus() {
		return shortStatus;
	}

	public abstract boolean isCommitable();

}
