package com.alex.loaders.interfaces;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.ReplicateScaleFilter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;

import com.alex.io.InputStream;
import com.alex.store.Store;
import com.alex.utils.Utils;

public class Interface {

	public int id;
	public Store cache;
	public IComponent[] components;
	public JComponent[] jcomponents;


	public static void main(String[] args) throws IOException, Throwable {
		Store rscache = new Store("./498/");
		if (true) {
			Interface inter = new Interface(25, rscache);
			for (int i = 0; i < inter.components.length; i++) {
				if (inter.components[i] != null) {
					inter.components[i].debug();
					System.out.println("----------------------------------------");
				}
			}
			return;
		}
		@SuppressWarnings("unused")
		BufferedWriter bw = new BufferedWriter(new FileWriter("498_interface_configs.txt"));
		for (int i = 0; i < 750; i++) {
			try {
				Interface inter = new Interface(i, rscache);
				if (inter.components == null) {
					continue;
				}
				int child = 0;
				Map<Integer, List<Integer>> childConfigs = new HashMap<>();
				for (IComponent c : inter.components) {
					if (c == null) {
						continue;
					}
					List<Integer> configs = new ArrayList<>();
					childConfigs.put(child, configs);
					if (c.childDataBuffers != null) {
						if (c.childDataBuffers[0][0] == 5) {
							int id = c.childDataBuffers[0][1];
							if (!configs.contains(id)) {
								configs.add(id);
							}
						}
						for (int j = 0; j < c.childDataBuffers.length; j++) {
							c.setConfigs(configs, j, rscache);
						}
					}
					child++;
				}
				for (int c : childConfigs.keySet()) {
					List<Integer> configs = childConfigs.get(c);
					if (configs.isEmpty()) {
						continue;
					}
					String data = "Interface " + i + " child " + c + " config: ";
					for (int j = 0; j < configs.size(); j++) {
						if (j != 0) {
							data += ", ";
						}
						int id = configs.get(j);
						data += "[" + (id & 0xFFFF) + ", " + (id >> 16) + "]";
					}
					bw.append(data);
					bw.newLine();
				}
			} catch(Throwable e) {
				e.printStackTrace();
			}
		}
		bw.flush();
		bw.close();
	}

	public Interface(int id, Store cache) {
		this(id,cache,true);
	}
	public Interface(int id, Store cache, boolean load) {
		this.id = id;
		this.cache = cache;
		if(load)
			getComponents();
	}

	public void draw(JComponent parent) {

	}

	public Image resizeImage(Image image, int width, int height, Component c) {
		ImageFilter replicate = new ReplicateScaleFilter(width, height);
		ImageProducer prod = new FilteredImageSource(image.getSource(),replicate);
		return c.createImage(prod);
	}


	public void getComponents() {
		if (Utils.getInterfaceDefinitionsSize(cache) <= id) {
//			throw new RuntimeException("Invalid interface id.");
			return;
		}
		components = new IComponent[Utils.getInterfaceDefinitionsComponentsSize(cache, id)];
		for(int componentId = 0; componentId < components.length; componentId++) {
			components[componentId] = new IComponent();
			components[componentId].hash = id << 16 | componentId;
			byte[] data = cache.getIndexes()[3].getFile(id, componentId);
			if (data == null)
				throw new RuntimeException("Interface "+id+", component "+componentId+" data is null.");
			if (data[0] != -1)
				components[componentId].decodeNoscriptsFormat(new InputStream(data));
			else
				components[componentId].decodeScriptsFormat(new InputStream(data));
		}
	}
}
