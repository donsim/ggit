package ggit.preferences;

import ggit.Activator;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class PreferencesPage extends FieldEditorPreferencePage
implements IWorkbenchPreferencePage{
	public void createFieldEditors() {
		addField(new FileFieldEditor(Activator.GIT_LOCATION,"Git executable", getFieldEditorParent()));
		addField(new DirectoryFieldEditor(Activator.WORKDIR_LOCATION,"Working Dir", getFieldEditorParent()));
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}


}
