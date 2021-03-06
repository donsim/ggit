/**
 *
 */
package ggit.status;


/**
 * @author Donatas
 * Parsers one line got from "git status -s" and returns {@link FileItem}
 */
abstract class Parser
{

	/**
	 * @param line
	 * @return created File Item
	 */
	abstract FileItem parsed(String line);
}