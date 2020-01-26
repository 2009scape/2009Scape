package org.apollo.fs.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

/**
 * A utility class for performing compression/uncompression.
 * @author Graham
 */
public final class CompressionUtil {

	/**
	 * Ungzips the compressed array and places the results into the uncompressed array.
	 * @param compressed The compressed array.
	 * @param uncompressed The uncompressed array.
	 * @throws IOException if an I/O error occurs.
	 */
	public static void ungzip(byte[] compressed, byte[] uncompressed) throws IOException {
		DataInputStream is = new DataInputStream(new GZIPInputStream(new ByteArrayInputStream(compressed)));
		try {
			is.readFully(uncompressed);
		} finally {
			is.close();
		}
	}

	/**
	 * Unbzip2s the compressed array and places the result into the uncompressed array.
	 * @param compressed The compressed array.
	 * @param uncompressed The uncompressed array.
	 * @throws IOException if an I/O error occurs.
	 */
	public static void unbzip2(byte[] compressed, byte[] uncompressed) throws IOException {
		byte[] newCompressed = new byte[compressed.length + 4];
		newCompressed[0] = 'B';
		newCompressed[1] = 'Z';
		newCompressed[2] = 'h';
		newCompressed[3] = '1';
		System.arraycopy(compressed, 0, newCompressed, 4, compressed.length);

		DataInputStream is = new DataInputStream(new BZip2CompressorInputStream(new ByteArrayInputStream(newCompressed)));
		try {
			is.readFully(uncompressed);
		} finally {
			is.close();
		}
	}

	/**
	 * Gzips the specified array.
	 * @param bytes The uncompressed array.
	 * @return The compressed array.
	 * @throws IOException if an I/O error occurs.
	 */
	public static byte[] gzip(byte[] bytes) throws IOException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		DeflaterOutputStream os = new GZIPOutputStream(bout);
		try {
			os.write(bytes);
			os.finish();
			return bout.toByteArray();
		} finally {
			os.close();
		}
	}

	/**
	 * Bzip2s the specified array.
	 * @param bytes The uncompressed array.
	 * @return The compressed array.
	 * @throws IOException if an I/O error occurs.
	 */
	public static byte[] bzip2(byte[] bytes) throws IOException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		BZip2CompressorOutputStream os = new BZip2CompressorOutputStream(bout, 1);
		try {
			os.write(bytes);
			os.finish();
			byte[] compressed = bout.toByteArray();
			byte[] newCompressed = new byte[compressed.length - 4];
			System.arraycopy(compressed, 4, newCompressed, 0, newCompressed.length);
			return newCompressed;
		} finally {
			os.close();
		}
	}

	/**
	 * Default private constructor to prevent instantiation.
	 */
	private CompressionUtil() {

	}

}
