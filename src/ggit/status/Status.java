package ggit.status;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Status {

	private Collection<FileItem> items=Collections.emptyList();
	private List<String> unrecognized=Collections.emptyList();
	private final Parser parser = new CompositeParser();


	public Collection<FileItem> getItems() {
		return items;
	}

	public void setOutput(String result) {
		unrecognized = new ArrayList<String>();
		items = new ArrayList<FileItem>();
		StringTokenizer lineTokenizer = new StringTokenizer(result,"\n");
		while (lineTokenizer.hasMoreTokens() ){
			String line = lineTokenizer.nextToken();
			parseLine( line );
		}
	}

	private void parseLine(String line) {
		FileItem parsed = parser.parsed(line);
		if (parsed != null) {
			items.add(parsed);
		}else
		{
			unrecognized.add(line);
		}
	}

	public List<String> getUnrecognized() {
		return unrecognized;
	}

}
