package ggit.status;

import ggit.status.items.FileItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class Status {

	private Collection<FileItem> items=Collections.emptyList();
	private final Parser parser = new CompositeParser();


	public Collection<FileItem> getItems() {
		return items;
	}

	public void setOutput(String result) {
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
		}
	}

}
