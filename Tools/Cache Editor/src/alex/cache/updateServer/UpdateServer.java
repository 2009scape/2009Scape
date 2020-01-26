package alex.cache.updateServer;

import alex.io.Stream;
import alex.util.Methods;

public class UpdateServer {

	public static byte[] getReadyForSendFile(int idxid, int fileid, int compression, byte[] data) {
		Stream stream = new Stream(data.length+100);
		stream.putByte(idxid);
		stream.putShort(fileid);
		byte[] compressedData = Methods.packContainer(compression, data);
		for(int index = 0; index < compressedData.length; index++)
			stream.putByte(compressedData[index]);
		byte[] file = new byte[stream.offset];
		stream.offset = 0;
		stream.getBytes(file, 0, file.length);
		return file;
	}
}
