package emperor;

public class Landscape {

	byte[][][] flags = new byte[4][64][64];
	byte[][][] overlays = new byte[4][64][64];
	byte[][][] underlays = new byte[4][64][64];
	
	public void addOverlay(int z, int x, int y, int overlay) {
		overlays[z][x][y] = (byte) overlay;
	}
	public void addUnderlay(int z, int x, int y, int underlay) {
		underlays[z][x][y] = (byte) underlay;
	}
}