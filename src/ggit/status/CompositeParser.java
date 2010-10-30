package ggit.status;

import java.util.Arrays;
import java.util.Collection;

import ggit.status.added.AddedDeletedStatusParser;
import ggit.status.added.AddedModifiedStatusParser;
import ggit.status.added.AddedStatusParser;
import ggit.status.modified.ModifiedInIndexAndInWorkDirParser;
import ggit.status.modified.ModifiedInIndexParser;
import ggit.status.modified.ModifiedInWorkTreeStatusParser;
import ggit.status.removed.RemovedInWorkTreeStatusParser;
import ggit.status.unknown.UnknownStatusParser;


public class CompositeParser extends Parser {

	private Collection<Parser> parsers = Arrays.asList(
			new Parser[]{
					new UnknownStatusParser(),
					new RemovedInWorkTreeStatusParser(),
					new ModifiedInWorkTreeStatusParser(),
					new AddedStatusParser(),
					new AddedModifiedStatusParser(),
					new AddedDeletedStatusParser(),
					new ModifiedInIndexParser(),
					new ModifiedInIndexAndInWorkDirParser(),
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
