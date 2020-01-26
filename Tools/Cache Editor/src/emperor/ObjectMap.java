package emperor;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.alex.io.InputStream;
import com.alex.io.OutputStream;

/**
 * Represents an object map.
 * @author Emperor
 *
 */
public final class ObjectMap {

	private List<GameObject> objects = new ArrayList<>();
	
	public void add(int id, int x, int y, int z, int type, int rotation) {
		objects.add(new GameObject(id, x, y, z, type, rotation));
	}
	
	public GameObject get(GameObject object) {
		return get(object.id, object.loc.x, object.loc.y, object.loc.z, object.type, object.rotation);
	}
	
	public GameObject get(int id, int x, int y, int z, int type, int rotation) {
		for (GameObject object : objects) {
			Location loc = object.loc;
			if (object.id == id && loc.x == x && loc.y == y && loc.z == z && object.type == type && object.rotation == rotation) {
				return object;
			}
		}
		return null;
	}
	
	public List<GameObject> getObjects() {
		return objects;
	}
	
	public static void compare(ObjectMap map, ObjectMap m) {
		if (map.objects.size() != m.objects.size()) {
			System.err.println("Mismatch [s1=" + map.objects.size() + ", s2=" + m.objects.size() + "]!");
			return;
		}
		Queue<GameObject> queue1 = new PriorityQueue<>(map.objects);
		Queue<GameObject> queue2 = new PriorityQueue<>(m.objects);
		while (!queue1.isEmpty()) {
			int id = queue1.peek().id;
			int id1 = queue2.peek().id;
			if (id != id1) {
				System.err.println("Object id mismatch [o1=" + id + ", o2=" + id1 + "]!");
				return;
			}
			Queue<QueueEntry> entry = new PriorityQueue<>();
			Queue<QueueEntry> entry1 = new PriorityQueue<>();
			while (!queue1.isEmpty() && (queue1.peek().id == id)) {
				entry.add(new QueueEntry(queue1.poll()));
			}
			while (!queue2.isEmpty() && (queue2.peek().id == id)) {
				entry1.add(new QueueEntry(queue2.poll()));
			}
			if (entry.size() != entry1.size()) {
				System.err.println("Entry mismatch [s1=" + entry.size() + ", s2=" + entry1.size() + "]!");
				return;
			}
			while (!entry.isEmpty()) {
				GameObject object = entry.poll().object;
				GameObject object1 = entry1.poll().object;
				if (object.loc.getHash() != object1.loc.getHash()) {
					System.err.println("Location mismatch " + id + "!");
					return;
				}
				if (object.rotation != object1.rotation) {
					System.err.println("Rotation mismatch " + id + "!");
					return;
				}
				if (object.type != object1.type) {
					System.err.println("Type mismatch " + id + "!");
					return;
				}
			}
		}
		System.out.println("Matching object maps [s1=" + map.objects.size() + ", s2=" + m.objects.size() + "]!");
	}
	
	public void map(InputStream stream) {
		int objectId = -1;
		for (;;) {
			int offset = stream.readSmart2();
			if (offset == 0) {
				break;
			}
			objectId += offset;
			int location = 0;
			for (;;) {
				offset = stream.readUnsignedSmart();
				if (offset == 0) {
					break;
				}
				location += offset - 1;
				int y = location & 0x3f;
				int x = location >> 6 & 0x3f;
				int configuration = stream.readUnsignedByte();
				int rotation = configuration & 0x3;
				int type = configuration >> 2;
				int z = location >> 12;
				if (x >= 0 && y >= 0 && x < 64 && y < 64) {
					add(objectId, x, y, z, type, rotation);
				} else {
					System.out.println("Object out of bounds: " + objectId + " - " + x + ", " + y + ", " + z);
				}
			}
		}
	}
	
	public byte[] generate() {
		OutputStream stream = new OutputStream();
		PriorityQueue<GameObject> queue = new PriorityQueue<>(objects);
		int offset = -1;
		while (!queue.isEmpty()) {
			int id = queue.peek().id;
			Queue<QueueEntry> entry = new PriorityQueue<>();
			while (!queue.isEmpty() && (queue.peek().id == id)) {
				entry.add(new QueueEntry(queue.poll()));
			}
			stream.writeSmart2(id - offset);
			int location = 0;
			while (!entry.isEmpty()) {
				GameObject object = entry.poll().object;
				stream.writeSmart(1 + (object.loc.getHash() - location));
				stream.writeByte(object.rotation | object.type << 2);
				location = object.loc.getHash();
			}
			stream.writeSmart(0);
			offset = id;
		}
		stream.writeSmart2(0);
		byte[] bs = new byte[stream.getOffset()];
		for (int i = 0; i < stream.getOffset(); i++) {
			bs[i] = stream.getBuffer()[i];
		}
		return bs;
	}
	
	public static class GameObject implements Comparable<GameObject> {
		int id;
		Location loc;
		int type;
		int rotation;
		
		public GameObject(int id, int x, int y, int z, int type, int rotation) {
			this.id = id;
			this.loc = new Location(x, y, z);
			this.type = type;
			this.rotation = rotation;
		}
		
		public GameObject getLocal() {
			return new GameObject(id, loc.getRegionX(), loc.getRegionY(), loc.z, type, rotation);
		}

		@Override
		public int compareTo(GameObject o) {
			return id - o.id;
		}
		
		@Override
		public String toString() {
			return id + ", " + type + ", " + rotation;
		}
	}
	
	public static class QueueEntry implements Comparable<QueueEntry> {
		GameObject object;
		public QueueEntry(GameObject object) {
			this.object = object;
		}

		@Override
		public int compareTo(QueueEntry o) {
			return object.loc.getHash() - o.object.loc.getHash();
		}
	}
	public static class Location {
		int x;
		int y;
		int z;
		public Location(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		public int getRegionX() {
			return x - ((x >> 6) << 6);
		}
		
		public int getRegionY() {
			return y - ((y >> 6) << 6);
		}
		public int getHash() {
			return z << 12 | x << 6 | y;
		}
	}
}