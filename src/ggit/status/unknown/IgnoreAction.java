package ggit.status.unknown;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import ggit.Config;
import ggit.status.FileAction;


public class IgnoreAction extends FileAction {

	public IgnoreAction(String filename) {
		super(filename, "Ignore");
	}

	@Override
	public void run() {
		// append filename to the ignore file.
		File file = new File(Config.getWorkDir(),".gitignore");
		try {
			PrintStream printStream = new PrintStream(new FileOutputStream(file,true));
			printStream.println(getFileName());
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
