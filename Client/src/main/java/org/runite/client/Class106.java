package org.runite.client;

final class Class106 {

    static boolean aBoolean1441 = true;
    static int anInt1442 = 0;
    static int anInt1446 = 0;
    static boolean hasInternetExplorer6 = false;
    int anInt1447;
    int anInt1449;
    int anInt1450;

    static void method1642(RSString var1) {
        try {
            if (null != PacketParser.clanChatInformationArray) {

                long var3 = var1.toLong();
                int var2 = 0;
                if (var3 != 0L) {
                    while (PacketParser.clanChatInformationArray.length > var2 && var3 != PacketParser.clanChatInformationArray[var2].linkableKey) {
                        ++var2;
                    }

                    if (var2 < PacketParser.clanChatInformationArray.length && null != PacketParser.clanChatInformationArray[var2]) {
                        Network.outgoingBuffer.putOpcode(162);
                        Network.outgoingBuffer.writeLong(PacketParser.clanChatInformationArray[var2].linkableKey);
                    }
                }
            }
        } catch (RuntimeException var5) {
            throw ClientErrorException.clientError(var5, "od.C(" + 3803 + ',' + (var1 != null ? "{...}" : "null") + ')');
        }
    }

}
