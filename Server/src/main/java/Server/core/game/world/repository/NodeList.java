package core.game.world.repository;

import core.game.node.Node;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * A class which represents a list of nodes.
 * @author Graham Edgecombe
 * @author Emperor
 * @param <E> The type of Node.
 */
public class NodeList<E extends Node> implements Collection<E>, Iterable<E> {

	/**
	 * Internal nodes array.
	 */
	private Node[] nodes;

	/**
	 * Current size.
	 */
	private int size = 0;

	/**
	 * Creates a Node list with the specified capacity.
	 * @param capacity The capacity.
	 */
	public NodeList(int capacity) {
		nodes = new Node[capacity + 1]; // do not use idx 0
	}

	/**
	 * Gets an Node.
	 * @param index The index.
	 * @return The Node.
	 * @throws IndexOutOufBoundException if the index is out of bounds.
	 */
	@SuppressWarnings("unchecked")
	public E get(int index) {
		synchronized (this) {
			if (index <= 0 || index >= nodes.length) {
				throw new IndexOutOfBoundsException();
			}
			return (E) nodes[index];
		}
	}

	/**
	 * Gets the index of an Node.
	 * @return The index in the list.
	 */
	public int indexOf(Node node) {
		return node.getIndex();
	}

	/**
	 * Gets the next free id.
	 * @return The next free id.
	 */
	private int getNextId() {
		for (int i = 1; i < nodes.length; i++) {
			if (nodes[i] == null) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean add(E arg0) {
		synchronized (this) {
			int id = getNextId();
			if (id == -1) {
				return false;
			}
			nodes[id] = arg0;
			arg0.setIndex(id);
			size++;
			return true;
		}
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		synchronized (this) {
			boolean changed = false;
			for (E node : arg0) {
				if (add(node)) {
					changed = true;
				}
			}
			return changed;
		}
	}

	@Override
	public void clear() {
		synchronized (this) {
			for (int i = 1; i < nodes.length; i++) {
				nodes[i] = null;
			}
			size = 0;
		}
	}

	@Override
	public boolean contains(Object arg0) {
		synchronized (this) {
			for (int i = 1; i < nodes.length; i++) {
				if (nodes[i] == arg0) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		boolean failed = false;
		for (Object o : arg0) {
			if (!contains(o)) {
				failed = true;
			}
		}
		return !failed;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new NodeListIterator<E>(this);
	}

	/**
	 * Removes the node on this index.
	 * @param index The index.
	 * @return {@code True} if a node got removed.
	 */
	public boolean remove(int index) {
		synchronized (this) {
			Node n = nodes[index];
			if (n != null) {
				size--;
				nodes[index] = null;
			}
			return n != null;
		}
	}

	@Override
	public boolean remove(Object arg0) {
		synchronized (this) {
			for (int i = 1; i < nodes.length; i++) {
				if (nodes[i] == arg0) {
					nodes[i] = null;
					size--;
					return true;
				}
			}
			return false;
		}
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		synchronized (this) {
			boolean changed = false;
			for (Object o : arg0) {
				if (remove(o)) {
					changed = true;
				}
			}
			return changed;
		}
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		synchronized (this) {
			boolean changed = false;
			for (int i = 1; i < nodes.length; i++) {
				if (nodes[i] != null) {
					if (!arg0.contains(nodes[i])) {
						nodes[i] = null;
						size--;
						changed = true;
					}
				}
			}
			return changed;
		}
	}

	@Override
	public int size() {
		return size;
	}

	public int length(){ return nodes.length;}

	@Override
	public Node[] toArray() {
		synchronized (this) {
			int size = size();
			Node[] array = new Node[size];
			int ptr = 0;
			for (int i = 1; i < nodes.length; i++) {
				if (nodes[i] != null) {
					array[ptr++] = nodes[i];
				}
			}
			return array;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] arg0) {
		Node[] arr = toArray();
		return (T[]) Arrays.copyOf(arr, arr.length, arg0.getClass());
	}

}
