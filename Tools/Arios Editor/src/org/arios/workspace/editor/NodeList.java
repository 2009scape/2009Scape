package org.arios.workspace.editor;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.arios.workspace.WorkSpace;
import org.arios.workspace.node.Node;

/**
 * A list of nodes to search.
 * @author Vexia
 *
 */
public class NodeList extends JList<Node<?>> {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 102017980938742769L;
	
	/**
	 * The node list model.
	 */
	private final DefaultListModel<Node<?>> model = new DefaultListModel<Node<?>>();
	
	/**
	 * The node list renderer.
	 */
	private final NodeListRenderer renderer = new NodeListRenderer();

	/**
	 * Constructs a new {@Code NodeList} {@Code Object}
	 */
	public NodeList() {
		super();
		setModel(model);
		setCellRenderer(renderer);
		addListSelectionListener(new ListSelectionHandler());
	}
	
	/**
	 * Gets the default model.
	 * @return the model.
	 */
	public DefaultListModel<Node<?>> getDefaultModel() {
		return model;
	}

	/**
	 * Renderes nodes on a list.
	 * @author Vexia
	 *
	 */
	public class NodeListRenderer implements ListCellRenderer<Node<?>> {
		
		/**
		 * The default renderer.
		 */
		protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

		@Override
		public Component getListCellRendererComponent(JList<? extends Node<?>> list, Node<?> value, int index, boolean isSelected, boolean cellHasFocus) {
			JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			renderer.setText(value.toString());
			return renderer;
		}
		
	}
	
	/**
	 * Handles the list selection.
	 * @author Vexia
	 * 
	 */
	public class ListSelectionHandler implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			boolean adjust = e.getValueIsAdjusting();
			if (!adjust) {
				@SuppressWarnings("unchecked")
				JList<Node<?>> list = (JList<Node<?>>) e.getSource();
				int selections[] = list.getSelectedIndices();
				@SuppressWarnings("deprecation")
				Object selectionValues[] = list.getSelectedValues();
				for (int i = 0, n = selections.length; i < n; i++) {
					if (!WorkSpace.getWorkSpace().getEditor().edit((Node<?>) selectionValues[i])) {
						break;
					}
				}
			}
		}
	}
 }
