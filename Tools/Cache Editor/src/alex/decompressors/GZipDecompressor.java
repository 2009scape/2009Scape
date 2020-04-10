package alex.decompressors;

import java.util.zip.Inflater;

import alex.io.Stream;

public class GZipDecompressor {

	private static final Inflater inflater = new Inflater(true);

	public static final void decompress(Stream stream, byte output[]) {
		if (~stream.payload[stream.offset] != -32
				|| stream.payload[stream.offset + 1] != -117) {
			throw new RuntimeException("Invalid GZIP header!");
		}
		try {
			inflater.setInput(stream.payload, stream.offset + 10,
					-stream.offset - 18 + stream.payload.length);
			inflater.inflate(output);
		} catch (Exception _ex) {
			inflater.reset();
			throw new RuntimeException("Invalid GZIP compressed data!");
		}
		inflater.reset();
	}
}
