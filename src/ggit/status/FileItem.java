package ggit.status;

import ggit.Activator;

import java.net.URL;
import java.util.Collection;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;

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

	public Image getImage() {
		Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
        IPath path = new Path("icons/" +
        		getImageFileName());
        URL[] findEntries = FileLocator.findEntries(bundle, path);
        ImageDescriptor desc = ImageDescriptor.createFromURL(findEntries[0]);
        return desc.createImage();
	}

	protected String getImageFileName() {
		return "empty.png";
	}

}
