/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.editor.model;

import com.alex.store.Index;
import com.alex.store.Store;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Travis
 */
public class ModelDumper {

    private static Store STORE;

    public static void main(String[] args) throws IOException {
        try {
            STORE = new Store("C:/Users/Travis/Documents/rscd/data/");
        } catch (Exception e) {
        }
        Index index = STORE.getIndexes()[7];
        System.out.println(index.getLastArchiveId());
        for (int i = 0; i < index.getLastArchiveId(); i++) {
            byte[] data = index.getFile(i);
            if (data == null) {
                continue;
            }
            //if(!(data[data.length + -1] == -1 && data[-2 + data.length] == -1))
            //if((data[-1 + data.length] ^ 0xffffffff) != 0 || data[-2 + data.length] != -1)
            //System.out.println(i);
            writeFile(data, "C:/Users/Travis/Documents/781 Models/" + i + ".dat");
        }

    }

    public static void writeFile(byte[] data, String fileName) throws IOException {
        OutputStream out = new FileOutputStream(fileName);
        out.write(data);
        out.close();
    }
}
