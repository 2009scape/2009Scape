package com.alex.loaders.images;

import java.awt.Image;
import java.awt.Toolkit;

import com.alex.store.Store;
import com.alex.utils.Constants;

public class LoaderImageArchive {

	private byte[] data;

	public LoaderImageArchive(byte[] data) {
		this.data = data;
	}

	public LoaderImageArchive(Store cache, int archiveId) {
		this(cache, Constants.LOADER_IMAGES_INDEX, archiveId, 0);
	}

	private LoaderImageArchive(Store cache, int idx, int archiveId, int fileId) {
		decodeArchive(cache, idx, archiveId, fileId);
	}

	private void decodeArchive(Store cache, int idx, int archiveId, int fileId) {
		byte[] data = cache.getIndexes()[idx].getFile(archiveId, fileId);
        if(data == null)
            return;
        this.data = data;
	}
	
	public Image getImage() {
		return Toolkit.getDefaultToolkit().createImage(data);
	}
	
	public byte[] getImageData() {
		return data;
	}

}
