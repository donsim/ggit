package ggit.status;

import org.eclipse.jface.action.Action;

public class FileAction extends Action {

	protected final String fileName;

	public FileAction(String filename, String text){
		fileName = filename;
		this.setText(text);
	}

	public String getFileName() {
		return fileName;
	}
	
	public boolean refreshRequired(){
		return true;
	}

}
