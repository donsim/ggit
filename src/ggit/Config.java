package ggit;

import java.io.File;
import java.io.IOException;

public class Config {

	public static String getGitExecutable()
	{
		return "C:\\git\\bin\\git.exe";

	}

	public static File getWorkDir() {
		return new File("F:\\work\\ggit");
	}

	public static String execGit(String ...args) throws IOException, InterruptedException
	{
		String[] cmdLine =  new String[args.length+1];
		cmdLine[0]=getGitExecutable();
		System.arraycopy(args, 0, cmdLine, 1, args.length);
		Process exec = Runtime.getRuntime().exec(cmdLine,null,getWorkDir());
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
		int result = exec.waitFor();
		// what to do with bad result ?
		if( result != 0){
			throw new RuntimeException("Process"+cmdLine[0]+" return not 0"+result);
		}
		return stringBuilder.toString();
	}
}
