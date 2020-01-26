package emperor;

import java.nio.ByteBuffer;

public class LandMap {

	ByteBuffer buffer;
	Byte[][][] overlayOpcodes = new Byte[4][64][64];
	Byte[][][] overlays = new Byte[4][64][64];
	Byte[][][] underlays = new Byte[4][64][64];
	Byte[][][] defaultOpcodes = new Byte[4][64][64];
	Byte[][][] height = new Byte[4][64][64];
	
	public void addOverlay(int z, int x, int y, int overlay) {
		overlays[z][x][y] = (byte) overlay;
	}
	public void addUnderlay(int z, int x, int y, int underlay) {
		underlays[z][x][y] = (byte) underlay;
	}
	
	public byte[] generate() {
		ByteBuffer buffer = ByteBuffer.allocate(1 << 20);
		for (int z = 0; z < 4; z++) {
			for (int x = 0; x < 64; x++) {
				for (int y = 0; y < 64; y++) {
					Byte b = null;
					if ((b = defaultOpcodes[z][x][y]) != null) {
						buffer.put(b);
					}
					if ((b = underlays[z][x][y]) != null) {
						buffer.put(b);
					}
					if ((b = overlayOpcodes[z][x][y]) != null) {
						buffer.put(b);
						buffer.put(overlays[z][x][y]);
					}
					if ((b = height[z][x][y]) != null) {
						buffer.put((byte) 1);
						buffer.put(b);
					} else {
						buffer.put((byte) 0);
					}
				}
			}
		}
		while (this.buffer.hasRemaining()) {
			buffer.put(this.buffer.get());
		}
		buffer.flip();
		byte[] bs = new byte[buffer.remaining()];
		buffer.get(bs);
		return bs;
	}
	
	public void map(ByteBuffer buffer) {
		this.buffer = buffer;
		for (int z = 0; z < 4; z++) {
			for (int x = 0; x < 64; x++) {
				for (int y = 0; y < 64; y++) {
					while (true) {
						int opcode = buffer.get() & 0xFF;
						if (opcode == 0) {
							break;
						}
						if (opcode == 1) {
							height[z][x][y] = buffer.get();
							break;
						}
						if (opcode <= 49) {
							overlayOpcodes[z][x][y] = (byte) opcode;
							overlays[z][x][y] = buffer.get();
						} else if (opcode <= 81) {
							underlays[z][x][y] = (byte) opcode;
						} else {
							defaultOpcodes[z][x][y] = (byte) opcode;
						}
					}
				}
			}
		}
		System.out.println("Read landscape (remaining=" + buffer.remaining() + ").");
	}
}