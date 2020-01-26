package emperor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import javax.imageio.ImageIO;

import alex.cache.loaders.OverlayDefinition;

import com.alex.loaders.images.IndexedColorImageFile;
import com.alex.store.Store;

/**
 * Packs the models.
 * @author Emperor
 *
 */
public final class ModelPacker {

	public static void main(String...args) throws Throwable {
		Store to = new Store("./498/");
		packDonatorIcons(to);
		//		packObjectDefinitions(from, to);
		//		packAnimations(from, to);
		//		List<Integer> anims = new ArrayList<>();
		//		for (int i = 0; i < 50_000; i++) {
		//			byte[] data = from.getIndexes()[16].getFile(i >>> 1998118472, i & 0xff);
		//			if (data == null) {
		//				continue;
		//			}
		//			ObjectDefinitions def = new ObjectDefinitions(i);
		//			def.initialize(from);
		//			if (def.animationId > -1) {
		//				if (!anims.contains(def.animationId)) {
		//					anims.add(def.animationId);
		//				}
		////				System.out.println(def.getName() + " anim: " + def.animationId + ", " + Arrays.toString(def.models));
		//			}
		//		}
		//		System.out.println(Arrays.toString(anims.toArray()));
		//		packAnimations(from, to);
	}

	static void packObjectDefinitions(Store from, Store to) {
		int[] defs = new int[] { 5461 };//5099, 5100, 5094, 5096, 5098, 5097, 5110, 5111};//5088, 5089, 5090 };
		for (int id : defs) {
			int archive = id >>> 1998118472;
			int file = id & 0xFF;
			byte[] bs = from.getIndexes()[16].getFile(archive, file);
			to.getIndexes()[16].putFile(archive, file, bs);
		}
	}

	static void editObjectDefinitions(int itemId, Store store, int opcode, Object value) {
		int archive = itemId >>> 1998118472;
		int file = itemId & 0xFF;
		byte[] bs = store.getIndexes()[16].getFile(archive, file);
		ByteBuffer buffer = ByteBuffer.allocate(bs.length + 128);
		for (int i = 0; i < bs.length - 1; i++) {
			buffer.put(bs[i]);
		}
		buffer.put((byte) opcode);
		if (value instanceof Byte) {
			buffer.put((Byte) value);
		}
		else if (value instanceof Short) {
			buffer.putShort((Short) value);
		}
		else if (value instanceof Integer) {
			buffer.putInt((Integer) value);
		}
		else if (value instanceof Long) {
			buffer.putLong((Long) value);
		}
		else if (value instanceof String) {
			buffer.put(((String) value).getBytes()).put((byte) 0);
		}
		else if (value instanceof Boolean) {
			buffer.put((byte) ((Boolean) value ? 1 : 0));
		}
		bs = new byte[buffer.remaining()];
		buffer.get(bs);
		store.getIndexes()[16].putFile(archive, file, bs);
	}

	static void packSprite(Store to) {
		int id = 423;
		IndexedColorImageFile f = null;
		try {
			f = new IndexedColorImageFile(to, id, 0);
			BufferedImage icon = ImageIO.read(new File("green.png"));
			f.replaceImage(icon, 3);
			//System.out.println("Added icon: "+f.addImage(icon, 0, 1)+".");
		} catch (IOException e) {
			e.printStackTrace();
		}
		to.getIndexes()[8].putFile(id, 0, f.encodeFile());
	}

	static void packDonatorIcons(Store to) {
		int id = 423;
		File[] files = new File("donator_icons").listFiles();
		IndexedColorImageFile f = null;
		int index = 0;
		for (File file : files) {
			try {
				f = new IndexedColorImageFile(to, id, 0);
				BufferedImage icon = ImageIO.read(file);
				if (index == 0) {
					f.replaceImage(icon, 3);
					System.out.println("Replaced icon - " + 3);
				} else {
					System.out.println("Added icon: "+f.addImage(icon, 0, 1)+".");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			to.getIndexes()[8].putFile(id, 0, f.encodeFile());
			index++;
		}
	}

	static void packAnimations(Store from, Store to) {
		int [] anims = new int[] {4856};//3206, 498, 499, 500, 501, 481, 467, 526, 527, 907, 505, 524, 449, 523, 2709, 1726, 480, 488, 479, 469, 475, 476, 473, 1071, 493, 494, 504, 471, 468, 470, 332, 333, 492, 1731, 472, 491, 503, 522, 456, 464, 2714, 9101, 502, 525, 6023, 6561, 477, 478, 1223, 446, 6913, 912, 917, 474, 1051, 1049, 4860, 1052, 1073, 9123, 3106, 1072, 1096, 1098, 1097, 1103, 1104, 1108, 1112, 1127, 1138, 1211, 1212, 1216, 1233, 1234, 1235, 1231, 1260, 1261, 1293, 1294, 1295, 1296, 1297, 1298, 1299, 1300, 1301, 1302, 1303, 1304, 8618, 1334, 1433, 1347, 1349, 1348, 1362, 1355, 1416, 1398, 1411, 1430, 1431, 1532, 1533, 1600, 1631, 1629, 1632, 1630, 1636, 1657, 1641, 1642, 1643, 1729, 1733, 1727, 1017, 1734, 1730, 1732, 1747, 1812, 1845, 1846, 1847, 1869, 1868, 1875, 1881, 1908, 1909, 1923, 1915, 1943, 1940, 1936, 1937, 1938, 1939, 1944, 1998, 1999, 2056, 2054, 2091, 2131, 2133, 2136, 2135, 2137, 2178, 2260, 2174, 2173, 2199, 2198, 2196, 2201, 2203, 2204, 2209, 2210, 2212, 5855, 2313, 2331, 2346, 2359, 2360, 2349, 2350, 2348, 2379, 2440, 2439, 2437, 2451, 2564, 4291, 3743, 2598, 4123, 2600, 2641, 2657, 2699, 2708, 2734, 2746, 2747, 2742, 2743, 2744, 2768, 447, 2807, 2870, 2871, 2878, 2883, 2897, 2899, 2901, 2898, 2900, 2905, 2997, 3022, 3029, 3028, 3030, 3038, 3070, 3099, 3100, 3095, 3105, 3107, 3101, 3097, 3104, 3113, 3174, 3173, 3180, 4354, 3217, 3218, 3219, 3172, 3230, 3231, 3237, 3246, 3247, 3264, 6094, 3304, 3305, 3306, 3343, 3347, 3511, 7263, 3349, 3351, 3352, 3408, 3405, 3406, 3407, 3438, 3439, 3440, 3445, 3472, 3528, 3534, 3529, 3530, 3531, 3532, 3478, 3542, 3558, 4477, 4564, 3586, 3573, 3577, 3117, 3118, 5483, 166, 286, 145, 3707, 6218, 6496, 1338, 9241, 3587, 3582, 3647, 3720, 3644, 3646, 3648, 3578, 3579, 3580, 3581, 3615, 3616, 3742, 3835, 3843, 3927, 3932, 3939, 3940, 3943, 3944, 3976, 3998, 4005, 4006, 4015, 4013, 4014, 4022, 3699, 3698, 3700, 4133, 4132, 6477, 4126, 4157, 4161, 4163, 4220, 4217, 4218, 4239, 4260, 4241, 4240, 4242, 4274, 4284, 4308, 4309, 4323, 4324, 4325, 4338, 4339, 4336, 4335, 459, 4357, 4355, 4356, 4358, 4393, 4392, 4408, 4394, 4395, 4396, 4397, 4398, 4377, 4359, 4361, 4360, 4363, 4364, 4399, 4431, 4535, 4565, 4566, 4567, 4568, 4569, 4577, 4557, 4559, 4560, 4563, 4561, 4562, 4572, 4571, 4595, 4599, 4621, 4622, 4627, 4628, 4746, 4747, 4778, 4744, 4745, 4743, 4781, 4798, 4783, 4894, 4879, 4880, 4881, 4895, 4896, 4899, 4883, 4897, 4898, 4900, 4901, 5012, 5044, 5073, 5058, 5141, 5109, 5170, 5169, 5173, 5174, 5175, 5179, 5180, 5176, 5177, 5178, 5197, 5193, 5195, 5196, 5194, 5203, 5220, 5219, 5222, 5221, 5235, 5237, 5239, 5260, 5261, 5267, 5269, 5268, 5271, 5270, 5278, 5308, 5295, 5296, 5297, 5350, 5351, 5360, 5068, 5430, 5415, 5422, 5423, 5429, 5431, 5432, 5603, 5599, 5601, 5600, 5598, 5605, 5631, 5604, 5564, 5740, 5742, 5737, 5745, 5743, 5744, 5738, 5728, 5730, 5729, 5739, 5741, 5734, 5771, 5772, 5768, 5797, 5798, 5828, 5829, 5830, 5844, 5824, 5825, 5847, 5900, 5901, 5906, 5874, 5857, 5909, 5975, 5976, 5977, 5983, 5984, 5985, 5974, 6015, 6069, 6037, 6038, 6036, 6035, 6034, 6029, 6031, 6032, 6027, 6028, 6024, 6025, 6026, 6039, 6123, 6211, 6161, 6162, 6163, 6164, 6165, 6166, 6167, 6168, 6170, 6196, 6269, 6274, 6481, 4130, 4128, 4131, 4129, 6491, 4127, 4125, 6495, 6493, 6494, 6466, 6467, 6492, 4124, 6426, 6461, 6453, 6439, 6522, 6497, 6499, 6500, 6509, 6506, 6523, 6623, 6624, 6625, 6626, 6627, 6597, 6598, 6635, 6637, 6636, 6638, 6639, 6645, 6646, 6652, 6653, 6656, 6737, 6732, 6731, 6733, 6734, 6735, 6736, 6854, 6853, 6873, 6874, 6875, 6912, 6914, 6915, 4782, 6925, 6900, 6901, 6902, 6903, 6917, 6898, 6890, 6891, 6892, 6893, 6894, 6895, 6931, 6932, 6982, 6995, 6996, 7007, 7066, 7067, 7087, 7097, 7118, 7115, 7117, 7120, 7138, 7144, 7146, 7152, 7225, 7226, 7245, 7252, 7231, 7291, 7286, 7283, 7284, 7285, 7352, 7354, 7353, 7361, 7346, 7357, 7356, 7358, 7360, 7375, 7373, 7378, 7379, 7380, 7381, 7544, 7546, 7552, 7577, 7603, 7601, 7580, 7602, 7600, 8526, 8510, 8663, 8664, 8666, 8665, 8653, 8624, 8646, 8654, 8647, 8667, 2418, 8708, 8714, 8735, 7158, 808, 8881, 8845, 8857, 8894, 8892, 8897, 8967, 8968, 8969, 8970, 8972, 8971, 9005, 9011, 9007, 9010, 9008, 9090, 9088, 9089, 9085, 9083, 9084, 9033, 9035, 9036, 9041, 9135, 9122, 4290, 4295, 4296, 9137, 9143, 9144, 9150, 9146, 4297, 9154, 9199, 9303, 9348, 9329, 9330, 9347};
		for (int i : anims) {
			byte[] a = from.getIndexes()[20].getFile(i >>> 7, i & 0x7F);
			if (a == null) {
				continue;
			}
			//			i = 10222;//from.getIndexes()[20].getLastArchiveId() + 1;
			System.out.println("Packed animation " + i + " - " + to.getIndexes()[20].putFile(i >>> 7, i & 0x7F, a));
		}
	}

	static void packTextures(Store from, Store to) {
		for (int i = 0; i < from.getIndexes()[9].getValidFilesCount(0); i++) {
			byte[] bs = from.getIndexes()[9].getFile(0, i);
			if (bs == null || bs.length < 1) {
				System.out.println("Missing texture id " + i);
				continue;
			}
			System.out.println("Packing texture id " + i + ": " + to.getIndexes()[9].putFile(0, i, bs));//+ (i < 200 ? Arrays.toString(bs) : null));//to.getIndexes()[6].putFile(0, i, bs));
		}
	}

	/**
	 * Packs the overlays.
	 * @param from The cache to get the data from.
	 * @param to The cache to store the data.
	 */
	static void packOverlays(Store from, Store to) {
		System.out.println("Start");
		//		int changeOverlay = 135;
		//		int newOverlay = 135;
		//		System.out.println("Success = " + to.getIndexes()[2].putFile(4, changeOverlay, from.getIndexes()[2].getFile(4, newOverlay)));
		for (int id = 0; id < to.getIndexes()[2].getValidFilesCount(4); id++) {
			byte[] bs = to.getIndexes()[2].getFile(4, id);
			if (bs == null || bs.length < 1) {
				continue;
			}
			OverlayDefinition def = OverlayDefinition.forId(to, id);
			if (def.getTextureId() > 0) {
				System.out.println("Packed overlay definition " + id + " - texture=" + def.getTextureId() + "!");
				//				boolean success = to.getIndexes()[2].putFile(4, id, from.getIndexes()[2].getFile(4, id));
				//				System.out.println("Packed overlay definition " + id + " - success=" + success);
			}
		}
	}

	static void packModels(Store from, Store to) {
		int[] models = new int[] {16400};
		for (int model : models) {
			byte[] a = from.getIndexes()[7].getFile(model);
			if (a == null) {
				continue;
			}
			System.out.println(Arrays.toString(a) + "");
			to.getIndexes()[7].putFile(1046, 0, a);
			break;
		}
	}

	static void packMusic(Store from, Store to) throws Throwable {
		for (int i = 0; i < to.getIndexes()[6].getValidArchivesCount(); i++) {
			byte[] bs = to.getIndexes()[6].getFile(i);
			if (bs == null || bs.length < 1) {
				continue;
			}
			System.out.println("Packing music id " + i + ": ");// + to.getIndexes()[6].putArchive(i, from));//.putArchive(2, , from));
		}
	}
}