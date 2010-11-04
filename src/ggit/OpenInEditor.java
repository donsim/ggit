package ggit;

import ggit.status.FileAction;

import java.io.File;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public class OpenInEditor extends FileAction {


	public OpenInEditor(String filename) {
		super(filename, "Edit");
	}

	@Override
	public void run() {
		File fileToOpen = new File(Config.getWorkDir(),getFileName());
		if( fileToOpen.exists())
		{
	    IFileStore fileStore = EFS.getLocalFileSystem().getStore(fileToOpen.toURI());
	    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

	    try {
	        IDE.openEditorOnFileStore( page, fileStore );
	    } catch ( PartInitException e ) {
	        throw new RuntimeException(e);
	    }
		}

	}
}
