package alex.cache;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileOnDisk {

	private RandomAccessFile file;
	private long length;
	private long position;
	private File wrappedFile;

	public FileOnDisk(File wrappedFile) throws IOException {
		this.wrappedFile = wrappedFile;
		file = new RandomAccessFile(wrappedFile, "rw");
		length = getFileLength();
	}

	public final void close() throws IOException {
		if (file != null) {
			file.close();
			file = null;
		}
	}

	@Override
	protected final void finalize() throws Throwable {
		if (file != null) {
			System.out
					.println("Warning! fileondisk "
							+ wrappedFile
							+ " not closed correctly using close(). Auto-closing instead. ");
			close();
		}
	}

	public final long getFileLength() throws IOException {
		return file.length();
	}

	public final File getWrappedFile() {
		return wrappedFile;
	}

	public final int read(byte buffer[], int off, int len) throws IOException {
		int k = file.read(buffer, off, len);
		if (k > 0) {
			position += k;
		}
		return k;
	}

	public final void seek(long l) throws IOException {
		file.seek(l);
		position = l;
	}

	public final void write(byte buffer[], int off, int len) throws IOException {
		//we gonna write so size wil get bigger
		/*if (length < len + position) {
			file.seek(length);
			file.write(1);
			throw new EOFException();
		}*/
		file.write(buffer, off, len);
		position += len;
	}
}
