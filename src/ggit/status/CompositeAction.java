package ggit.status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.jface.action.Action;

public class CompositeAction  extends Action{


	private Collection<Action> actions=new ArrayList<Action>();

	public CompositeAction(Action action) {
		setText(action.getText()+"*");
		addAction(action);
	}

	@Override
	public String getToolTipText() {
		StringBuilder sb = new StringBuilder();
		for (Action action : actions) {
			sb.append(action.getToolTipText()+"\n");
		}
		return sb.toString();
	}

	@Override
	public void run() {
		for (Action action : actions) {
			action.run();
		}
	}

	private void addAction(Action action) {
		actions.add(action);
		setText(action.getText()+"*"+actions.size());
	}

	public static Collection<Action> joinActions(Collection<Action> actions)
	{
		Map<String, Action> byName = new LinkedHashMap<String, Action>();
		for (Action action : actions) {
			if (byName.containsKey(action.getText())) {
				Action withSameName = byName.get(action.getText());
				CompositeAction compositeAction;
				if( (withSameName instanceof CompositeAction)) {
					compositeAction = (CompositeAction) withSameName;
				} else {
					compositeAction = new CompositeAction(withSameName);
				}
				compositeAction.addAction(action);
				byName.put(action.getText(), compositeAction);
			}else
			{
				byName.put(action.getText(), action);
			}
		}
		return byName.values();
	}
}
