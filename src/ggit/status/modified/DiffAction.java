package ggit.status.modified;

import ggit.Config;
import ggit.status.FileAction;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.eclipse.jface.dialogs.IconAndMessageDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class DiffAction extends FileAction {

	private final boolean againstHead;

	public DiffAction(String filename, boolean againstHead) {
		super(filename, "Diff");
		this.againstHead = againstHead;
	}

	@Override
	public void run() {
		final String execGit;
		if (againstHead) {
			execGit = Config.execGit("diff", "HEAD", getFileName());
		} else {
			execGit = Config.execGit("diff", getFileName());
		}
		// MessageDialog.openInformation(null, "Diff for "+getFileName(),
		// execGit);
		IconAndMessageDialog iconAndMessageDialog = new MessageDialog(null,
				"Diff for " + getFileName(), null, execGit, 0,
				new String[] { "OK" }, 0) {
			@Override
			protected Control createMessageArea(Composite composite) {
				StyledText text2 = new StyledText(composite, SWT.MULTI
						| SWT.WRAP);
				StringBuilder sb = new StringBuilder();
				ArrayList<StyleRange> styles = new ArrayList<StyleRange>();
				StringTokenizer stringTokenizer = new StringTokenizer(execGit,
						"\n\r", false);
				while (stringTokenizer.hasMoreTokens()) {
					String line = stringTokenizer.nextToken();
					if (line.startsWith("-")) {
						styles.add(new StyleRange(sb.length(), line.length(),
								composite.getShell().getDisplay()
										.getSystemColor(SWT.COLOR_RED), null));
					}
					if (line.startsWith("+")) {
						styles.add(new StyleRange(sb.length(), line.length(),
								composite.getShell().getDisplay()
										.getSystemColor(SWT.COLOR_GREEN), null));
					}
					sb.append(line);
					sb.append("\n");
				}

				text2.setText(sb.toString());
				text2.setStyleRanges(styles.toArray(new StyleRange[styles
						.size()]));
				return composite;
			}
		};
		iconAndMessageDialog.open();
	}

	public boolean refreshRequired() {
		return false;
	}
}
