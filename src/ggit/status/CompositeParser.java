package ggit.status;

import java.util.Arrays;
import java.util.Collection;

import ggit.status.items.FileItem;
import ggit.status.items.modified.ModifiedInWorkTreeStatusParser;
import ggit.status.items.removed.RemovedInWorkTreeStatusParser;
import ggit.status.items.unknown.UnknownStatusParser;


public class CompositeParser extends Parser {

	private Collection<Parser> parsers = Arrays.asList(
			new Parser[]{
					new UnknownStatusParser(),
					new RemovedInWorkTreeStatusParser(),
					new ModifiedInWorkTreeStatusParser(),
				}
	);

	@Override
	FileItem parsed(String line) {
		for (Parser parser : parsers) {
			FileItem parsed = parser.parsed(line);
			if( parsed!=null)
			{
				return parsed;
			}
		}
		return null;
	}

}
