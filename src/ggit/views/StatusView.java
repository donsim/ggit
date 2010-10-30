package ggit.views;


import ggit.status.CompositeAction;
import ggit.status.FileItem;
import ggit.status.RefreshStatusAction;
import ggit.status.Status;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class StatusView extends ViewPart {
	private TableViewer viewer;
	private Action refreshAction;
	private Action action2;
	private Action doubleClickAction;
	private Status repositoryStatus=new Status();

	/*
	 * The content provider class is responsible for
	 * providing objects to the view. It can wrap
	 * existing objects in adapters or simply return
	 * objects as-is. These objects may be sensitive
	 * to the current input of the view, or ignore
	 * it and always show the same content
	 * (like Task List, for example).
	 */

	class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}
		public void dispose() {
		}
		public Object[] getElements(Object parent) {
			Collection<FileItem> items = repositoryStatus.getItems();
			if( !items.isEmpty() )
			{
				return items.toArray();
			}
			return new Object[0];
		}
	}
	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			if (obj instanceof FileItem) {
				FileItem fi = (FileItem) obj;
				if( index==0)
				{
					return fi.getShortStatus();
				}
				if (index==1) {
					return fi.getFileName();
				}
				return fi.getDescription();
			}
			return getText(obj);
		}
		public Image getColumnImage(Object obj, int index) {
			if( index==0)
			{
				return getImage(obj);
			}
			return null;
		}
		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().
					getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}
	class NameSorter extends ViewerSorter {
	}

	/**
	 * The constructor.
	 */
	public StatusView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		createTable(parent);
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private void createTable(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL|SWT.FULL_SELECTION);

		// setup columns

		TableViewerColumn statusColumn = new TableViewerColumn(viewer,SWT.NONE);
		statusColumn.getColumn().setText("Status");
		statusColumn.getColumn().setResizable(true);
		statusColumn.getColumn().setWidth(50);

		TableViewerColumn fileColumn = new TableViewerColumn(viewer,SWT.NONE);
		fileColumn.getColumn().setText("File");
		fileColumn.getColumn().setResizable(true);
		fileColumn.getColumn().setWidth(300);

		TableViewerColumn descriptionColumn = new TableViewerColumn(viewer,SWT.NONE);
		descriptionColumn.getColumn().setText("File");
		descriptionColumn.getColumn().setResizable(true);
		descriptionColumn.getColumn().setWidth(300);

		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setSorter(new NameSorter());
		viewer.setInput(getViewSite());
		viewer.getTable().setLinesVisible(true);
		viewer.getTable().setHeaderVisible(true);

	}

	public void refreshView()
	{
		viewer.refresh();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				StatusView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(refreshAction);
		manager.add(new Separator());
		manager.add(action2);
	}

	private void fillContextMenu(IMenuManager manager) {
		int[] selection = viewer.getTable().getSelectionIndices();
		if( selection!=null&&selection.length>0)
		{
			ArrayList<Action> all = new ArrayList<Action>();
			for (int i : selection) {
				FileItem  fi = (FileItem) viewer.getElementAt(i);
				Collection<Action> availableActions = fi.availableActions();
				if(availableActions!=null)
				{
					all.addAll(availableActions);
				}
			}
			Collection<Action> joinActions = CompositeAction.joinActions(all);
			for (Action action : joinActions) {
				manager.add(action);
			}

		}else
		{
			manager.add(refreshAction);
			manager.add(action2);
		}
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(refreshAction);
		manager.add(action2);
	}

	private void makeActions() {
		refreshAction = new RefreshStatusAction(this,repositoryStatus);

		action2 = new Action() {
			public void run() {
				showMessage("Action 2 executed");
			}
		};
		action2.setText("Action 2");
		action2.setToolTipText("Action 2 tooltip");
		action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				showMessage("Double-click detected on "+obj.toString());
			}
		};
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}
	public void showMessage(String message) {
		MessageDialog.openInformation(
			viewer.getControl().getShell(),
			"GGit Status View",
			message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}