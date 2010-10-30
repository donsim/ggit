/**
 *
 */
package ggit.status;

import ggit.status.items.FileItem;

abstract public class SimpleStatusParser extends Parser
{
	final String statusChars;

	public SimpleStatusParser(String statusChars) {
		this.statusChars = statusChars;
	}

	@Override
	FileItem parsed(String line) {
		if( line.startsWith(statusChars))
		{
			return createFileItem( line.substring(statusChars.length()+1));
		}
		return null;
	}

	protected abstract FileItem createFileItem(String substring) ;

	public String getStatusChars() {
		return statusChars;
	}
}