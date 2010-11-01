package ggit.status;

import org.eclipse.jface.action.Action;

public class FileAction extends Action {

	protected final String fileName;

	public FileAction(String filename){
		fileName = filename;
	}

	public String getFileName() {
		return fileName;
	}

}
