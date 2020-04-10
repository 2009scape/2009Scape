package emperor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;

import alex.cache.loaders.ObjectDefinitions;

import com.alex.store.Store;

public class DefDumper {

	public static void main(String...args) throws Throwable {
		Store store = new Store("./508/");
		BufferedWriter bw = new BufferedWriter(new FileWriter("./508_object_list.txt"));
		for (int i = 0; i < 100_000; i++) {
			ObjectDefinitions def = ObjectDefinitions.initialize(i, store);
			if (def == null) {
				continue;
			}
			bw.append("definition [id=" + i + ", options=" + Arrays.toString(def.options) + "]");
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
}