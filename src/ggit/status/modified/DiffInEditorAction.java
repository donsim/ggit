package ggit.status.modified;

import ggit.Config;
import ggit.status.FileAction;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class DiffInEditorAction extends FileAction {

	private final boolean againstHead;

	public DiffInEditorAction(String filename, boolean againstHead) {
		super(filename, "Diff Structure");
		this.againstHead = againstHead;
	}

	@Override
	public void run() {
		final String execGit;
		if (againstHead) {
			execGit = Config.execGit("diff", "HEAD","-b", getFileName());
		} else {
			execGit = Config.execGit("diff", "-b",getFileName());
		}
		// MessageDialog.openInformation(null, "Diff for "+getFileName(),
		// execGit);

		Dialog dialog = new Dialog((Shell)null) {
			{
			setShellStyle(getShellStyle() | SWT.RESIZE);
			}

			 protected void configureShell(Shell shell) {
			      super.configureShell(shell);
			      shell.setText("Diff for " + getFileName());
			   }

			@Override
			protected Control createContents(Composite composite) {

				GridData styledTextLData = new GridData();
                styledTextLData.grabExcessVerticalSpace = true;
                styledTextLData.grabExcessHorizontalSpace = true;
                styledTextLData.horizontalAlignment = GridData.FILL;
                styledTextLData.verticalAlignment = GridData.FILL;

                StyledText text2 = new StyledText(composite,
SWT.READ_ONLY | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
                text2.setLayoutData(styledTextLData);

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
		dialog.open();

//		IconAndMessageDialog iconAndMessageDialog = new MessageDialog(null,
//				"Diff for " + getFileName(), null, execGit, 0,
//				new String[] { "OK" }, 0) {
//			@Override
//			protected Control createMessageArea(Composite composite) {
//
//				//ScrolledComposite scrolledComposite = new ScrolledComposite(composite, SWT.H_SCROLL | SWT.V_SCROLL);
//				StyledText text2 = new StyledText(composite, SWT.MULTI
//						| SWT.WRAP);
//				StringBuilder sb = new StringBuilder();
//				ArrayList<StyleRange> styles = new ArrayList<StyleRange>();
//				StringTokenizer stringTokenizer = new StringTokenizer(execGit,
//						"\n\r", false);
//				while (stringTokenizer.hasMoreTokens()) {
//					String line = stringTokenizer.nextToken();
//					if (line.startsWith("-")) {
//						styles.add(new StyleRange(sb.length(), line.length(),
//								composite.getShell().getDisplay()
//										.getSystemColor(SWT.COLOR_RED), null));
//					}
//					if (line.startsWith("+")) {
//						styles.add(new StyleRange(sb.length(), line.length(),
//								composite.getShell().getDisplay()
//										.getSystemColor(SWT.COLOR_GREEN), null));
//					}
//					sb.append(line);
//					sb.append("\n");
//				}
//
//				text2.setText(sb.toString());
//				text2.setStyleRanges(styles.toArray(new StyleRange[styles
//						.size()]));
//				return composite;
//			}
//		};
//
//		iconAndMessageDialog.open();
	}

	public boolean refreshRequired() {
		return false;
	}
}
