package alex.util;

public class LookupTable {

	private int identTable[];

	public LookupTable(int ai[]) {
		int i;
		for (i = 1; (ai.length >> 1) + ai.length >= i; i <<= 1) {
		}
		identTable = new int[i + i];
		for (int j = 0; i + i > j; j++) {
			identTable[j] = -1;
		}

		for (int k = 0; ai.length > k; k++) {
			int l;
			for (l = -1 + i & ai[k]; ~identTable[l + l + 1] != 0; l = 1 + l
					& -1 + i) {
			}
			identTable[l + l] = ai[k];
			identTable[1 + l + l] = k;
		}

	}

	public final int lookupIdentifier(int i) {
		int k = (identTable.length >> 1) - 1;
		int l = i & k;
		do {
			int i1 = identTable[1 + l + l];
			if (i1 == -1) {
				return -1;
			}
			if (i == identTable[l + l]) {
				return i1;
			}
			l = l + 1 & k;
		} while (true);
	}
}
