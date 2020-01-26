package alex.cache;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;

public class SeekableFile {

	private byte aByteArray2745[];
	private byte aByteArray2748[];
	private long aLong2739;
	private long aLong2741;
	private long aLong2743;
	private long aLong2750;
	private int anInt2737;
	private int anInt2749;
	private FileOnDisk file;
	private long fileLength;
	private long position;

	public SeekableFile(FileOnDisk fileOnDisk, int maxFileLength,
			int unknownLength) throws IOException {
		anInt2737 = 0;
		aLong2741 = -1L;
		aLong2743 = -1L;
		file = fileOnDisk;
		fileLength = aLong2750 = fileOnDisk.getFileLength();
		aByteArray2748 = new byte[maxFileLength];
		aByteArray2745 = new byte[unknownLength];
		position = 0L;
	}

	final long getFileLength() {
		return fileLength;
	}

	private final File getWrappedFile() {
		return file.getWrappedFile();
	}

	private final void method2264() throws IOException {
		anInt2749 = 0;
		if (~position != ~aLong2739) {
			file.seek(position);
			aLong2739 = position;
		}
		aLong2741 = position;
		while (~anInt2749 > ~aByteArray2748.length) {
			int i = aByteArray2748.length - anInt2749;
			if (i > 0xbebc200) {
				i = 0xbebc200;
			}
			int j = file.read(aByteArray2748, anInt2749, i);
			if (j == -1) {
				break;
			}
			anInt2749 += j;
			aLong2739 += j;
		}
	}

	final void read(byte buffer[]) throws IOException {
		read(buffer, 0, buffer.length);
	}

	final void read(byte buffer[], int off, int len) throws IOException {
		try {
			if (~buffer.length > ~(off + len)) {
				throw new ArrayIndexOutOfBoundsException(-buffer.length + len
						+ off);
			}
			if (~aLong2743 != 0L
					&& position >= aLong2743
					&& ~(position + len) >= ~(aLong2743 + anInt2737)) {
				System.arraycopy(aByteArray2745, (int) (-aLong2743 + position),
						buffer, off, len);
				position += len;
				return;
			}
			long l = position;
			int k = off;
			int i1 = len;
			if (position >= aLong2741
					&& position < aLong2741 + anInt2749) {
				int j1 = (int) (anInt2749 + aLong2741 - position);
				if (j1 > len) {
					j1 = len;
				}
				System.arraycopy(aByteArray2748, (int) (-aLong2741 + position),
						buffer, off, j1);
				off += j1;
				position += j1;
				len -= j1;
			}
			if (aByteArray2748.length >= len) {
				if (len > 0) {
					method2264();
					int k1 = len;
					if (k1 > anInt2749) {
						k1 = anInt2749;
					}
					System.arraycopy(aByteArray2748, 0, buffer, off, k1);
					off += k1;
					position += k1;
					len -= k1;
				}
			} else {
				file.seek(position);
				aLong2739 = position;
				while (len > 0) {
					int l1 = file.read(buffer, off, len);
					if (l1 == -1) {
						break;
					}
					position += l1;
					len -= l1;
					aLong2739 += l1;
					off += l1;
				}
			}
			if (~aLong2743 != 0L) {
				if (aLong2743 > position && len > 0) {
					int i2 = (int) (-position + aLong2743) + off;
					if (len + off < i2) {
						i2 = off + len;
					}
					while (i2 > off) {
						len--;
						buffer[off++] = 0;
						position++;
					}
				}
				long l2 = -1L;
				long l3 = -1L;
				if (~l < ~aLong2743 || aLong2743 >= l + i1) {
					if (~aLong2743 >= ~l && l < aLong2743 + anInt2737) {
						l2 = l;
					}
				} else {
					l2 = aLong2743;
				}
				if (aLong2743 + anInt2737 <= l
						|| anInt2737 + aLong2743 > l + i1) {
					if (i1 + l > aLong2743
							&& ~(i1 + l) >= ~(aLong2743 + anInt2737)) {
						l3 = l + i1;
					}
				} else {
					l3 = aLong2743 + anInt2737;
				}
				if (l2 > -1L && ~l2 > ~l3) {
					int j2 = (int) (-l2 + l3);
					System.arraycopy(aByteArray2745, (int) (-aLong2743 + l2),
							buffer, k + (int) (-l + l2), j2);
					if (~position > ~l3) {
						len = (int) (len - (l3 - position));
						position = l3;
					}
				}
			}
		} catch (IOException ioexception) {
			aLong2739 = -1L;
			throw ioexception;
		}
		if (len > 0) {
			throw new EOFException();
		} else {
			return;
		}
	}

	private final void refresh() throws IOException {
		if (~aLong2743 != 0L) {
			if (aLong2739 != aLong2743) {
				file.seek(aLong2743);
				aLong2739 = aLong2743;
			}
			file.write(aByteArray2745, 0, anInt2737);
			aLong2739 += anInt2737;
			if (aLong2750 < aLong2739) {
				aLong2750 = aLong2739;
			}
			long l = -1L;
			long l1 = -1L;
			if (aLong2743 < aLong2741
					|| aLong2743 >= anInt2749 + aLong2741) {
				if (~aLong2741 <= ~aLong2743
						&& anInt2737 + aLong2743 > aLong2741) {
					l = aLong2741;
				}
			} else {
				l = aLong2743;
			}
			if (aLong2741 < anInt2737 + aLong2743
					&& aLong2741 + anInt2749 >= anInt2737
							+ aLong2743) {
				l1 = aLong2743 + anInt2737;
			} else if (~(anInt2749 + aLong2741) < ~aLong2743
					&& ~(anInt2749 + aLong2741) >= ~(aLong2743 + anInt2737)) {
				l1 = anInt2749 + aLong2741;
			}
			if (l > -1L && l1 > l) {
				int i = (int) (l1 - l);
				System.arraycopy(aByteArray2745, (int) (l - aLong2743),
						aByteArray2748, (int) (l - aLong2741), i);
			}
			aLong2743 = -1L;
			anInt2737 = 0;
		}
	}

	final void seek(long l) throws IOException {
		if (l < 0L) {
			throw new IOException("Invalid seek to " + l + " in file "
					+ getWrappedFile());
		}
		position = l;
	}

	final void write(byte buffer[], int off, int len) throws IOException {
		try {
			if (~(len + position) < ~fileLength) {
				fileLength = len + position;
			}
			if (~aLong2743 != 0L
					&& (position < aLong2743 || ~(anInt2737 + aLong2743) > ~position)) {
				refresh();
			}
			if (~aLong2743 != 0L
					&& ~(aLong2743 + aByteArray2745.length) > ~(position + len)) {
				int l = (int) (aByteArray2745.length - (position - aLong2743));
				System.arraycopy(buffer, off, aByteArray2745,
						(int) (position - aLong2743), l);
				off += l;
				len -= l;
				position += l;
				anInt2737 = aByteArray2745.length;
				refresh();
			}
			if (~len < ~aByteArray2745.length) {
				if (position != aLong2739) {
					file.seek(position);
					aLong2739 = position;
				}
				file.write(buffer, off, len);
				aLong2739 += len;
				if (aLong2739 > aLong2750) {
					aLong2750 = aLong2739;
				}
				long l1 = -1L;
				long l2 = -1L;
				if (~position <= ~aLong2741
						&& aLong2741 + anInt2749 > position) {
					l1 = position;
				} else if (position <= aLong2741
						&& ~(position + len) < ~aLong2741) {
					l1 = aLong2741;
				}
				if (~(len + position) < ~aLong2741
						&& position + len <= aLong2741
								+ anInt2749) {
					l2 = len + position;
				} else if (~position > ~(anInt2749 + aLong2741)
						&& position + len >= anInt2749
								+ aLong2741) {
					l2 = anInt2749 + aLong2741;
				}
				if (l1 > -1L && ~l2 < ~l1) {
					int i1 = (int) (-l1 + l2);
					System.arraycopy(buffer,
							(int) (-position + off + l1),
							aByteArray2748, (int) (-aLong2741 + l1), i1);
				}
				position += len;
				return;
			}
			if (len > 0) {
				if (aLong2743 == -1L) {
					aLong2743 = position;
				}
				System.arraycopy(buffer, off, aByteArray2745,
						(int) (-aLong2743 + position), len);
				position += len;
				if (~(long) anInt2737 > ~(position - aLong2743)) {
					anInt2737 = (int) (position - aLong2743);
				}
				return;
			}
		} catch (IOException ioexception) {
			aLong2739 = -1L;
			throw ioexception;
		}
	}
}
