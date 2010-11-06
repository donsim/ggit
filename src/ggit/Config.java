package ggit;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class Config {

	private static final String CONSOLE_NAME = "GGit";

	public static String getGitExecutable()
	{
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		return  preferenceStore.getString(Activator.GIT_LOCATION);
		//return "C:\\git\\bin\\git.exe";
	}

	public static File getWorkDir() {

		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		String string = preferenceStore.getString(Activator.WORKDIR_LOCATION);
		return new File(string);
	}

	public static String execGit(String ...args)
	{
		String[] cmdLine =  new String[args.length+1];
		cmdLine[0]=getGitExecutable();
		System.arraycopy(args, 0, cmdLine, 1, args.length);
		Process exec;
		try {
			logToConsole( cmdLine );
			exec = Runtime.getRuntime().exec(cmdLine,null,getWorkDir());
			StringBuilder stringBuilder = new StringBuilder();
			for(;;)
			{
				int read = exec.getInputStream().read();
				if( read<0)
				{
					break;
				}
				stringBuilder.append((char)read);
			}
			int result;
			try {
				result = exec.waitFor();
				logToConsole(stringBuilder.toString());
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			// what to do with bad result ?
			if( result != 0){
				throw new RuntimeException("Process"+cmdLine[0]+" return not 0 "+result+stringBuilder.toString());
			}
			return stringBuilder.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


	   private static  void logToConsole(String... cmdLine) {
		   MessageConsole myConsole = findConsole(CONSOLE_NAME);
		   MessageConsoleStream out = myConsole.newMessageStream();
		   for (String string : cmdLine) {
			   out.print(string);
			   out.print(" ");
		   }
		   out.println();
	}

	private static MessageConsole findConsole(String name) {
		      ConsolePlugin plugin = ConsolePlugin.getDefault();
		      IConsoleManager conMan = plugin.getConsoleManager();
		      IConsole[] existing = conMan.getConsoles();
		      for (int i = 0; i < existing.length; i++)
		         if (name.equals(existing[i].getName()))
		            return (MessageConsole) existing[i];
		      //no console found, so create a new one
		      MessageConsole myConsole = new MessageConsole(name, null);
		      conMan.addConsoles(new IConsole[]{myConsole});
		      return myConsole;
		   }
}
