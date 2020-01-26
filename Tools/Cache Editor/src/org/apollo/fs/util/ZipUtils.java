package org.apollo.fs.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.zip.GZIPInputStream;

public class ZipUtils {

	/**
	 * Unzips a cache file.
	 * 
	 * @param file
	 *            The cache file.
	 * @return The unzipped byte buffer.
	 * @throws IOException
	 *             if an I/O error occurs.
	 */
	public static ByteBuffer unzip(ByteBuffer buffer) throws IOException {
		byte[] data = new byte[buffer.remaining()];
		buffer.get(data);
		InputStream is = new GZIPInputStream(new ByteArrayInputStream(data));
		byte[] out;
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				while (true) {
					byte[] buf = new byte[1024];
					int read = is.read(buf, 0, buf.length);
					if (read == -1) {
						break;
					}
					os.write(buf, 0, read);
				}
			} finally {
				os.close();
			}
			out = os.toByteArray();
		} finally {
			is.close();
		}
		ByteBuffer newBuf = ByteBuffer.allocate(out.length);
		newBuf.put(out);
		newBuf.flip();
		return newBuf;
	}
}

