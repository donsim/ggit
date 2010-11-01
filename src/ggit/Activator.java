package ggit;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "ggit";

	//The identifiers for the preferences
	public static final String GIT_LOCATION = "Git Location";
	public static final String WORKDIR_LOCATION = "WorkDir";

	//The default values for the preferences
	public static final String DEFAULT_GIT_LOCATION  = "C:\\git\\bin\\git.exe";
	public static final String DEFAULT_WORKDIR_LOCATION = "f:\\work\\ggit";





	// The shared instance
	private static Activator plugin;

	private static Map<String, Image> imageMap=new HashMap<String, Image>();

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	/**
	 * Initializes a preference store with default preference values
	 * for this plug-in.
	 */
	protected void initializeDefaultPreferences(IPreferenceStore store) {
		store.setDefault(GIT_LOCATION, DEFAULT_GIT_LOCATION);
		store.setDefault(WORKDIR_LOCATION, DEFAULT_WORKDIR_LOCATION);
	}

	public static Image getImage(String name)
	{
		if( imageMap.containsKey(name ))
		{
			return imageMap.get(name);
		}
		Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
	    IPath path = new Path("icons/"+name);
	    URL[] findEntries = FileLocator.findEntries(bundle, path);
	    ImageDescriptor desc = ImageDescriptor.createFromURL(findEntries[0]);
	    Image createImage = desc.createImage();
	    imageMap.put(name, createImage);
		return createImage;
	}

}
