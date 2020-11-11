package org.runite.client;

import org.rs09.client.Linkable;
import org.rs09.client.LinkableInt;
import org.rs09.client.data.HashTable;
import org.rs09.client.data.Queue;
import org.rs09.client.data.ReferenceCache;
import org.rs09.client.filestore.resources.configs.enums.EnumDefinition;
import org.rs09.client.filestore.resources.configs.enums.EnumDefinitionProvider;
import org.rs09.client.filestore.resources.configs.structs.StructDefinitionProvider;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;

final class CS2Script extends Linkable {

    static short aShort3052 = 205;
    static int anInt3101 = 0;
    static int[] anIntArray3228 = new int[]{7, 8, 9, 10, 11, 12, 13, 15};
    static short aShort3241 = 1;
    static int anInt1357 = 0;
    static short aShort1444 = 256;
    static RSInterface aClass11_1749;
    static boolean aBoolean2705 = true;
    static int anInt3775 = 0;
    RSInterface aClass11_2438;
   RSString aClass94_2439;
   static int anInt2440 = 0;
   int scrollbarScrollAmount;
   static ReferenceCache aReferenceCache_2442 = new ReferenceCache(50);
   int anInt2443;
   int inputTextCode;
   int interfaceButtons;
   boolean aBoolean2446;
   int worldSelectCursorPositionX;
   Object[] arguments;
   RSInterface aClass11_2449;
   static ReferenceCache aReferenceCache_2450 = new ReferenceCache(64);
   static int anInt2451 = -1;
   static byte[][][] aByteArrayArrayArray2452;
   static int anInt2453 = 127;


   static void sendRegistryRequest(int year, int country, int day, int month) {
      try {
    	//  System.out.println("CS2Script year=" + year + ", country=" + country + ", day=" + day + ", month=" + month + ", stage=" + stage + ", " + System.currentTimeMillis());
         Class3_Sub13_Sub1.outgoingBuffer.index = 0;
         Class3_Sub13_Sub1.outgoingBuffer.writeByte(147);//Handshake opcode
         Class3_Sub13_Sub1.outgoingBuffer.writeByte(day);
         Class3_Sub13_Sub1.outgoingBuffer.writeByte(month);
         Class3_Sub13_Sub1.outgoingBuffer.writeShort(year);
         Class3_Sub13_Sub1.outgoingBuffer.writeShort(country);
         Class132.anInt1734 = 0;
         GraphicDefinition.anInt548 = 0;
         Unsorted.registryStage = 1;
         Unsorted.anInt1711 = -3;
      } catch (RuntimeException var6) {
         throw ClientErrorException.clientError(var6, "jl.C(" + year + ',' + country + ',' + day + ',' + month + ',' + 1 + ')');
      }
   }

   static Class79 method378(int var0, byte var1) {
      try {
         Class79 var2 = (Class79) aReferenceCache_2450.get(var0);
         if(var2 == null) {
            if(var1 < 126) {
               return (Class79)null;
            } else {
               byte[] var3 = Class101.aClass153_1420.getFile(Class140_Sub7.method2032(var0), var0 & 1023);
               var2 = new Class79();
               if(var3 != null) {
                  var2.method1387(new DataBuffer(var3));
               }

               aReferenceCache_2450.put(var2, (long)var0);
               return var2;
            }
         } else {
            return var2;
         }
      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "jl.A(" + var0 + ',' + var1 + ')');
      }
   }

   static void method379() {
      try {
         int var2 = Class146.anInt1904 * 128 - -64;
         int var1 = 128 * Unsorted.anInt30 + 64;
         int var3 = Class121.method1736(WorldListCountry.localPlane, 1, var1, var2) - Class3_Sub13_Sub34.anInt3414;
         if(100 <= Unsorted.anInt3631) {
            NPC.anInt3995 = 64 + Unsorted.anInt30 * 128;
            Class77.anInt1111 = 64 + Class146.anInt1904 * 128;
            Class7.anInt2162 = Class121.method1736(WorldListCountry.localPlane, 1024 + -1023, NPC.anInt3995, Class77.anInt1111) + -Class3_Sub13_Sub34.anInt3414;
         } else {
            if(NPC.anInt3995 < var1) {
               NPC.anInt3995 += Class163_Sub2_Sub1.anInt4021 + Unsorted.anInt3631 * (-NPC.anInt3995 + var1) / 1000;
               if(var1 < NPC.anInt3995) {
                  NPC.anInt3995 = var1;
               }
            }

            if(var3 > Class7.anInt2162) {
               Class7.anInt2162 += (-Class7.anInt2162 + var3) * Unsorted.anInt3631 / 1000 + Class163_Sub2_Sub1.anInt4021;
               if(Class7.anInt2162 > var3) {
                  Class7.anInt2162 = var3;
               }
            }

            if(var1 < NPC.anInt3995) {
               NPC.anInt3995 -= Class163_Sub2_Sub1.anInt4021 + (NPC.anInt3995 + -var1) * Unsorted.anInt3631 / 1000;
               if(NPC.anInt3995 < var1) {
                  NPC.anInt3995 = var1;
               }
            }

            if(Class77.anInt1111 < var2) {
               Class77.anInt1111 += Class163_Sub2_Sub1.anInt4021 + Unsorted.anInt3631 * (var2 - Class77.anInt1111) / 1000;
               if(Class77.anInt1111 > var2) {
                  Class77.anInt1111 = var2;
               }
            }

            if(var3 < Class7.anInt2162) {
               Class7.anInt2162 -= (Class7.anInt2162 - var3) * Unsorted.anInt3631 / 1000 + Class163_Sub2_Sub1.anInt4021;
               if(Class7.anInt2162 < var3) {
                  Class7.anInt2162 = var3;
               }
            }

            if(var2 < Class77.anInt1111) {
               Class77.anInt1111 -= Class163_Sub2_Sub1.anInt4021 - -((-var2 + Class77.anInt1111) * Unsorted.anInt3631 / 1000);
               if(Class77.anInt1111 < var2) {
                  Class77.anInt1111 = var2;
               }
            }
         }

         var2 = Class157.anInt1996 * 128 - -64;
         var1 = MouseListeningClass.anInt1923 * 128 + 64;
         var3 = Class121.method1736(WorldListCountry.localPlane, 1, var1, var2) + -GraphicDefinition.anInt529;
         int var5 = var3 + -Class7.anInt2162;
         int var6 = -Class77.anInt1111 + var2;
         int var4 = -NPC.anInt3995 + var1;
         int var7 = (int)Math.sqrt(var4 * var4 + var6 * var6);
         int var8 = (int)(325.949D * Math.atan2(var5, var7)) & 2047;
         if(128 > var8) {
            var8 = 128;
         }

         if(var8 > 383) {
            var8 = 383;
         }

         int var9 = (int)(-325.949D * Math.atan2((double)var4, (double)var6)) & 2047;
         if(var8 > Class139.anInt1823) {
            Class139.anInt1823 += Class75.anInt1105 + Class163_Sub2_Sub1.anInt4014 * (-Class139.anInt1823 + var8) / 1000;
            if(Class139.anInt1823 > var8) {
               Class139.anInt1823 = var8;
            }
         }

         if(Class139.anInt1823 > var8) {
            Class139.anInt1823 -= (Class139.anInt1823 - var8) * Class163_Sub2_Sub1.anInt4014 / 1000 + Class75.anInt1105;
            if(var8 > Class139.anInt1823) {
               Class139.anInt1823 = var8;
            }
         }

         int var10 = -Class3_Sub13_Sub25.anInt3315 + var9;
         if(var10 > 1024) {
            var10 -= 2048;
         }

         if(-1024 > var10) {
            var10 += 2048;
         }

         if(var10 > 0) {
            Class3_Sub13_Sub25.anInt3315 += var10 * Class163_Sub2_Sub1.anInt4014 / 1000 + Class75.anInt1105;
            Class3_Sub13_Sub25.anInt3315 &= 2047;
         }

         if(var10 < 0) {
            Class3_Sub13_Sub25.anInt3315 -= Class163_Sub2_Sub1.anInt4014 * -var10 / 1000 + Class75.anInt1105;
            Class3_Sub13_Sub25.anInt3315 &= 2047;
         }

         int var11 = -Class3_Sub13_Sub25.anInt3315 + var9;
         if(1024 < var11) {
            var11 -= 2048;
         }

         if(var11 < -1024) {
            var11 += 2048;
         }

         if(var11 < 0 && var10 > 0 || var11 > 0 && var10 < 0) {
            Class3_Sub13_Sub25.anInt3315 = var9;
         }

      } catch (RuntimeException var12) {
         throw ClientErrorException.clientError(var12, "jl.B(" + 1024 + ')');
      }
   }

   /*
    * This is probably the MOST important function in the entire
    * client. It basically runs a script, which is:
    * a bunch of args, and a special integer pointing to the script entry method (script.arguments[0])
    * This integer refers to one of the game's methods which it loads from various files. (TODO Figure out how this is handled)
    *
    * All scripts are compiled into a pseudo assembly language. This language consists of
    * a bunch of opcodes, int and string operands for each opcode, an int and string stack
    *
    * There is also ram (for saving variables) which persists as you go in and out of methods.
    *
    * Opcodes refer to instructions which are executed sequentially, except for when a branch opcode is encountered.
    * opcode bound ram can be assigned to anything but can only be read from the current opcode.
    *
    * Opcode bound ram will often refer to a location in non-opcode bound ram as a way to access arbitrary values.
    *
    *
    * This is my best understanding of this function. MOST of the above text is PROBABLY a lie.
    * Poke around with it yourself and see what you discover - Your friendly neighborhood moth
    *
    */
   static void runAssembledScript(int maxIterations, CS2Script script)
   {
      try {
            Object[] aobj = script.arguments;
            int j = ((Integer) aobj[0]).intValue();
            //System.out.println("CS opcode: " + j);
            AssembledMethod currentMethod = ItemDefinition.getMethodByID(j);
            if (null == currentMethod)
               return;
            ItemDefinition.scriptHeapCounter = 0;
            int sStackCounter = 0;
            int iStackCounter = 0;
            int programCounter = -1;
            int[] instructionOperands = currentMethod.instructionOperands;
            int[] instructions = currentMethod.assemblyInstructions;
            /*
             * Scan method arguments. args can either be
             * custom strings, custom ints, or integer opcodes (0x80000001-0x80000009) which represent
             * various ints found elsewhere in the code for things like keyboard input.
             *
             * OPCODES 35 and 40 act on the string args
             * OPCODES 33 and 40 act on the int args
             *
             * These arguments are tied to the specific method you are calling, just like arguments
             * in a real programming language. In this case imagine this as arguments that you pass
             * to the main() function of java.
             */

            ItemDefinition.intArguments = new int[currentMethod.numberOfIntsToCopy];
            ItemDefinition.stringArguments = new RSString[currentMethod.numberOfRSStringsToCopy];
            int stringArgIter = 0;
            int intArgIter = 0;
            for (int i2 = 1; aobj.length > i2; i2++) {
               if (aobj[i2] instanceof Integer) {
                  int k2 = ((Integer) aobj[i2]).intValue();
                  boolean printK2 = false;
                  if (k2 == 0x80000001)
                        k2 = script.worldSelectCursorPositionX; // Why does this matter?
                  if (k2 == 0x80000002)
                        k2 = script.scrollbarScrollAmount;
                  if (k2 == 0x80000003)
                        k2 = null == script.aClass11_2449 ? -1 : script.aClass11_2449.componentHash;
                  if (k2 == 0x80000004)
                        k2 = script.interfaceButtons;
                  if (k2 == 0x80000005)
                        k2 = null == script.aClass11_2449 ? -1 : script.aClass11_2449.anInt191;
                  if (k2 == 0x80000006)
                        k2 = null == script.aClass11_2438 ? -1 : script.aClass11_2438.componentHash;
                  if (k2 == 0x80000007)
                        k2 = script.aClass11_2438 != null ? script.aClass11_2438.anInt191 : -1;
                  if (k2 == 0x80000008)
                        k2 = script.inputTextCode;
                  if (k2 == 0x80000009)
                        k2 = script.anInt2443;
                  ItemDefinition.intArguments[intArgIter++] = k2;
                  continue;
               }
               if (!(aobj[i2] instanceof RSString))
                  continue;
               RSString class94 = (RSString) aobj[i2];
               if (class94.equalsString(TextCore.aClass94_209))
                  class94 = script.aClass94_2439;
               ItemDefinition.stringArguments[stringArgIter++] = class94;
               //	System.out.println("Item Definition line 168 " + class94.toString());
            }

            int j2 = 0;
            label0:
            do {
               j2++;
               if (maxIterations < j2)
                  throw new RuntimeException("Script exceeded max iterations");
               int opcode = instructions[++programCounter];
               //System.out.println("Instruction: " + programCounter + ". opcode is: " + opcode);
               if (opcode < 100) {
                  if (opcode == CS2AsmOpcodes.PUSH_INT.getOp()) {
                        ItemDefinition.intsStack[iStackCounter++] = instructionOperands[programCounter];
                        continue;
                  }
                  if (opcode == CS2AsmOpcodes.PUSH_INT_FROM_RAM.getOp()) {
                        int l2 = instructionOperands[programCounter];
                        ItemDefinition.intsStack[iStackCounter++] = ItemDefinition.ram[l2];
                        continue;
                  }
                  if (opcode == CS2AsmOpcodes.POP_INT_TO_RAM.getOp()) {
                        int i3 = instructionOperands[programCounter];
                        AtmosphereParser.method1428(i3, ItemDefinition.intsStack[--iStackCounter]);
                        continue;
                  }
                  if (opcode == CS2AsmOpcodes.PUSH_STR.getOp()) {
                        ItemDefinition.stringsStack[sStackCounter++] = currentMethod.stringInstructionOperands[programCounter];
                        continue;
                  }
                  if (opcode == CS2AsmOpcodes.JUMP.getOp()) {
                        programCounter += instructionOperands[programCounter];
                        continue;
                  }
                  if (opcode == CS2AsmOpcodes.BRANCH_NOT_EQUAL.getOp()) {
                        iStackCounter -= 2;
                        if (ItemDefinition.intsStack[iStackCounter] != ItemDefinition.intsStack[1 + iStackCounter])
                           programCounter += instructionOperands[programCounter];
                        continue;
                  }
                  if (opcode == CS2AsmOpcodes.BRANCH_EQUAL.getOp()) {
                        iStackCounter -= 2;
                        if (ItemDefinition.intsStack[iStackCounter] == ItemDefinition.intsStack[iStackCounter + 1])
                           programCounter += instructionOperands[programCounter];
                        continue;
                  }
                  if (opcode == CS2AsmOpcodes.BRANCH_GREATER_THAN.getOp()) {
                        iStackCounter -= 2;
                        if (ItemDefinition.intsStack[iStackCounter + 1] > ItemDefinition.intsStack[iStackCounter])
                           programCounter += instructionOperands[programCounter];
                        continue;
                  }
                  if (opcode == CS2AsmOpcodes.BRANCH_LESS_THAN.getOp()) {
                        iStackCounter -= 2;
                        if (ItemDefinition.intsStack[iStackCounter + 1] < ItemDefinition.intsStack[iStackCounter])
                           programCounter += instructionOperands[programCounter];
                        continue;
                  }
                  if (opcode == CS2AsmOpcodes.RETURN.getOp()) {
                        if (ItemDefinition.scriptHeapCounter == 0)
                           return;
                        AssembledMethodContainer assembledMethodContainer = ItemDefinition.methodStack[--ItemDefinition.scriptHeapCounter];
                        currentMethod = assembledMethodContainer.assembledMethod;
                        instructions = currentMethod.assemblyInstructions;
                        programCounter = assembledMethodContainer.currentProgramCounter;
                        ItemDefinition.intArguments = assembledMethodContainer.intArguments;
                        ItemDefinition.stringArguments = assembledMethodContainer.stringArguments;
                        instructionOperands = currentMethod.instructionOperands;
                        continue;
                  }
                  if (opcode == 25) {
                        int j3 = instructionOperands[programCounter];
                        ItemDefinition.intsStack[iStackCounter++] = NPCDefinition.method1484(j3);
                        continue;
                  }
                  if (opcode == 27) {
                        int k3 = instructionOperands[programCounter];
                        Class3_Sub13_Sub29.method306(k3, ItemDefinition.intsStack[--iStackCounter]);
                        continue;
                  }
                  if (opcode == CS2AsmOpcodes.BRANCH_GREATER_OR_EQUAL.getOp()) {
                        iStackCounter -= 2;
                        if (ItemDefinition.intsStack[1 + iStackCounter] >= ItemDefinition.intsStack[iStackCounter])
                           programCounter += instructionOperands[programCounter];
                        continue;
                  }
                  if (opcode == CS2AsmOpcodes.BRANCH_LESS_OR_EQUAL.getOp()) {
                        iStackCounter -= 2;
                        if (ItemDefinition.intsStack[1 + iStackCounter] <= ItemDefinition.intsStack[iStackCounter])
                           programCounter += instructionOperands[programCounter];
                        continue;
                  }
                  if (opcode == CS2AsmOpcodes.PUSH_INT_FROM_ARGS.getOp()) {
                        ItemDefinition.intsStack[iStackCounter++] = ItemDefinition.intArguments[instructionOperands[programCounter]];
                        continue;
                  }
                  if (opcode == CS2AsmOpcodes.POP_INT_TO_ARGS.getOp()) {
                        ItemDefinition.intArguments[instructionOperands[programCounter]] = ItemDefinition.intsStack[--iStackCounter];
                        continue;
                  }
                  if (opcode == CS2AsmOpcodes.PUSH_STRING_FROM_ARGS.getOp()) {
                        ItemDefinition.stringsStack[sStackCounter++] = ItemDefinition.stringArguments[instructionOperands[programCounter]];
                        continue;
                  }
                  if (opcode == CS2AsmOpcodes.POP_STRING_TO_ARGS.getOp()) {
                        ItemDefinition.stringArguments[instructionOperands[programCounter]] = ItemDefinition.stringsStack[--sStackCounter];
                        continue;
                  }
                  if (opcode == 37) {
                        int l3 = instructionOperands[programCounter];
                        sStackCounter -= l3;
                        RSString class94_2 = Class67.method1261(sStackCounter, l3, ItemDefinition.stringsStack);
                        ItemDefinition.stringsStack[sStackCounter++] = class94_2;
                        continue;
                  }
                  if (opcode == CS2AsmOpcodes.POP_INT.getOp()) {
                        iStackCounter--;
                        continue;
                  }
                  if (opcode == CS2AsmOpcodes.POP_STRING.getOp()) {
                        sStackCounter--;
                        continue;
                  }
                  if (opcode == CS2AsmOpcodes.CALL.getOp()) {
                        int op = instructionOperands[programCounter];
                        AssembledMethod assembledMethod_1 = ItemDefinition.getMethodByID(op);
                        int[] ai2 = new int[assembledMethod_1.numberOfIntsToCopy];
                        RSString[] aclass94 = new RSString[assembledMethod_1.numberOfRSStringsToCopy];
                        if (assembledMethod_1.numberOfIntArguments >= 0)
                           System.arraycopy(ItemDefinition.intsStack, (iStackCounter - assembledMethod_1.numberOfIntArguments), ai2, 0, assembledMethod_1.numberOfIntArguments);

                        for (int i76 = 0; assembledMethod_1.numberOfStringArguments > i76; i76++)
                           aclass94[i76] = ItemDefinition.stringsStack[i76 + -assembledMethod_1.numberOfStringArguments + sStackCounter];

                        iStackCounter -= assembledMethod_1.numberOfIntArguments;
                        sStackCounter -= assembledMethod_1.numberOfStringArguments;
                        AssembledMethodContainer assembledMethodContainer_1 = new AssembledMethodContainer();
                        assembledMethodContainer_1.stringArguments = ItemDefinition.stringArguments;
                        assembledMethodContainer_1.intArguments = ItemDefinition.intArguments;
                        assembledMethodContainer_1.currentProgramCounter = programCounter;
                        assembledMethodContainer_1.assembledMethod = currentMethod;
                        if (ItemDefinition.methodStack.length <= ItemDefinition.scriptHeapCounter)
                           throw new RuntimeException();
                        currentMethod = assembledMethod_1;
                        programCounter = -1;
                        ItemDefinition.methodStack[ItemDefinition.scriptHeapCounter++] = assembledMethodContainer_1;
                        ItemDefinition.intArguments = ai2;
                        instructionOperands = currentMethod.instructionOperands;
                        instructions = currentMethod.assemblyInstructions;
                        ItemDefinition.stringArguments = aclass94;
                        continue;
                  }
                  if (42 == opcode) {
                        ItemDefinition.intsStack[iStackCounter++] = NPCDefinition.anIntArray1277[instructionOperands[programCounter]];
                        continue;
                  }
                  if (opcode == 43) {
                        int j4 = instructionOperands[programCounter];
                        NPCDefinition.anIntArray1277[j4] = ItemDefinition.intsStack[--iStackCounter];
                        PacketParser.method825(j4);
                        continue;
                  }
                  if (opcode == CS2AsmOpcodes.ALLOCATE_PAGED_RAM.getOp()) {
                        int k4 = instructionOperands[programCounter] >> 16; // Get upper 16 bits of operand.
                        int bytesWritten = ItemDefinition.intsStack[--iStackCounter]; // pop stack
                        int k5 = 0xffff & instructionOperands[programCounter]; // Get lower 16 bits of operand to use as byte
                        if (bytesWritten < 0 || bytesWritten > 5000)
                           throw new RuntimeException();
                        ItemDefinition.pagedRamPageSize[k4] = bytesWritten;
                        byte byte2 = -1;
                        if (k5 == 105)
                           byte2 = 0;
                        for (int i = 0; i < bytesWritten; i++) {
                           ItemDefinition.pagedRam[k4][i] = byte2;
                        }
                        continue;
                  }
                  if (opcode == CS2AsmOpcodes.REPLACE_STACK_PAGED_RAM.getOp()) {
                        int l4 = instructionOperands[programCounter];
                        int l5 = ItemDefinition.intsStack[--iStackCounter];
                        if (l5 < 0 || l5 >= ItemDefinition.pagedRamPageSize[l4])
                           throw new RuntimeException();
                        ItemDefinition.intsStack[iStackCounter++] = ItemDefinition.pagedRam[l4][l5];
                        continue;
                  }
                  if (opcode == CS2AsmOpcodes.POP_TO_PAGED_RAM.getOp()) {
                        int i5 = instructionOperands[programCounter];
                        iStackCounter -= 2;
                        int i6 = ItemDefinition.intsStack[iStackCounter];
                        if (i6 < 0 || ItemDefinition.pagedRamPageSize[i5] <= i6)
                           throw new RuntimeException();
                        ItemDefinition.pagedRam[i5][i6] = ItemDefinition.intsStack[1 + iStackCounter];
                        continue;
                  }
                  if (47 == opcode) {
                        RSString class94_1 = Class132.aClass94Array1739[instructionOperands[programCounter]];
                        if (null == class94_1)
                           class94_1 = TextCore.aClass94_2928;
                        ItemDefinition.stringsStack[sStackCounter++] = class94_1;
                        continue;
                  }
                  if (opcode == 48) {
                        int j5 = instructionOperands[programCounter];
                        Class132.aClass94Array1739[j5] = ItemDefinition.stringsStack[--sStackCounter];
                        Class49.method1126(j5);
                        continue;
                  }
                  if (opcode == 51) {
                        HashTable hashTable = currentMethod.switchHashTable[instructionOperands[programCounter]];
                        LinkableInt linkableInt = (LinkableInt) hashTable.get(ItemDefinition.intsStack[--iStackCounter]);
                        if (null != linkableInt)
                           programCounter += linkableInt.value;
                        continue;
                  }
               }
               boolean flag;
               flag = 1 == instructionOperands[programCounter];
               if (opcode < 300) {
                  if (opcode == 100) {
                        iStackCounter -= 3;
                        int j6 = ItemDefinition.intsStack[iStackCounter];
                        int i44 = ItemDefinition.intsStack[1 + iStackCounter];
                        int k66 = ItemDefinition.intsStack[2 + iStackCounter];
                        if (i44 == 0)
                           throw new RuntimeException();
                        RSInterface class11_21 = Class7.getRSInterface(j6);
                        if (null == class11_21.aClass11Array262)
                           class11_21.aClass11Array262 = new RSInterface[k66 + 1];
                        if (k66 >= class11_21.aClass11Array262.length) {
                           RSInterface[] aclass11 = new RSInterface[k66 + 1];
                           if (class11_21.aClass11Array262.length >= 0)
                              System.arraycopy(class11_21.aClass11Array262, 0, aclass11, 0, class11_21.aClass11Array262.length);

                           class11_21.aClass11Array262 = aclass11;
                        }
                        if (0 < k66 && class11_21.aClass11Array262[-1 + k66] == null)
                           throw new RuntimeException("Gap at:" + (-1 + k66));
                        RSInterface class11_23 = new RSInterface();
                        class11_23.usingScripts = true;
                        class11_23.anInt191 = k66;
                        class11_23.parentId = class11_23.componentHash = class11_21.componentHash;
                        class11_23.type = i44;
                        class11_21.aClass11Array262[k66] = class11_23;
                        if (flag)
                           Class164.aClass11_2055 = class11_23;
                        else
                           aClass11_1749 = class11_23;
                        Class20.method909(class11_21);
                        continue;
                  }
                  if (opcode == 101) {
                        RSInterface class11 = flag ? Class164.aClass11_2055 : aClass11_1749;
                        if (class11.anInt191 == -1)
                           if (!flag)
                              throw new RuntimeException("Tried to cc_delete static active-component!");
                           else
                              throw new RuntimeException("Tried to .cc_delete static .active-component!");
                        RSInterface class11_17 = Class7.getRSInterface(class11.componentHash);
                        class11_17.aClass11Array262[class11.anInt191] = null;
                        Class20.method909(class11_17);
                        continue;
                  }
                  if (opcode == 102) {
                        RSInterface class11_1 = Class7.getRSInterface(ItemDefinition.intsStack[--iStackCounter]);
                        class11_1.aClass11Array262 = null;
                        Class20.method909(class11_1);
                        continue;
                  }
                  if (opcode == 200) {
                        iStackCounter -= 2;
                        int k6 = ItemDefinition.intsStack[iStackCounter];
                        int j44 = ItemDefinition.intsStack[iStackCounter - -1];
                        RSInterface class11_19 = AbstractSprite.method638(k6, j44);
                        if (null == class11_19 || j44 == -1) {
                           ItemDefinition.intsStack[iStackCounter++] = 0;
                        } else {
                           ItemDefinition.intsStack[iStackCounter++] = 1;
                           if (!flag)
                              aClass11_1749 = class11_19;
                           else
                              Class164.aClass11_2055 = class11_19;
                        }
                        continue;
                  }
                  if (opcode != 201)
                        break;
                  int l6 = ItemDefinition.intsStack[--iStackCounter];
                  RSInterface class11_18 = Class7.getRSInterface(l6);
                  if (null == class11_18) {
                        ItemDefinition.intsStack[iStackCounter++] = 0;
                  } else {
                        ItemDefinition.intsStack[iStackCounter++] = 1;
                        if (flag)
                           Class164.aClass11_2055 = class11_18;
                        else
                           aClass11_1749 = class11_18;
                  }
                  continue;
               }
               if (500 <= opcode) {
                  if (1000 <= opcode && opcode < 1100 || 2000 <= opcode && opcode < 2100) {
                        RSInterface class11_2;
                        if (opcode < 2000) {
                           class11_2 = flag ? Class164.aClass11_2055 : aClass11_1749;
                        } else {
                           class11_2 = Class7.getRSInterface(ItemDefinition.intsStack[--iStackCounter]);
                           opcode -= 1000;
                        }
                        if (opcode == 1000) {
                           iStackCounter -= 4;
                           class11_2.defX = ItemDefinition.intsStack[iStackCounter];
                           class11_2.defY = ItemDefinition.intsStack[iStackCounter + 1];
                           int l66 = ItemDefinition.intsStack[3 + iStackCounter];
                           if (l66 < 0)
                              l66 = 0;
                           else if (l66 > 5)
                              l66 = 5;
                           int k44 = ItemDefinition.intsStack[iStackCounter + 2];
                           if (k44 >= 0) {
                              if (k44 > 5)
                                    k44 = 5;
                           } else {
                              k44 = 0;
                           }
                           class11_2.verticalPos = (byte) l66;
                           class11_2.horizontalPos = (byte) k44;
                           Class20.method909(class11_2);
                           Class3_Sub13_Sub12.method225(class11_2);
                           if (class11_2.anInt191 == -1)
                              Class168.method2280(class11_2.componentHash);
                           continue;
                        }
                        if (opcode == 1001) {
                           iStackCounter -= 4;
                           class11_2.defWidth = ItemDefinition.intsStack[iStackCounter];
                           class11_2.defHeight = ItemDefinition.intsStack[1 + iStackCounter];
                           class11_2.anInt184 = 0;
                           class11_2.anInt312 = 0;
                           int l44 = ItemDefinition.intsStack[iStackCounter + 2];
                           int i67 = ItemDefinition.intsStack[3 + iStackCounter];
                           if (i67 >= 0) {
                              if (i67 > 4)
                                    i67 = 4;
                           } else {
                              i67 = 0;
                           }
                           class11_2.verticalResize = (byte) i67;
                           if (l44 < 0)
                              l44 = 0;
                           else if (l44 > 4)
                              l44 = 4;
                           class11_2.horizontalResize = (byte) l44;
                           Class20.method909(class11_2);
                           Class3_Sub13_Sub12.method225(class11_2);
                           if (class11_2.type == 0)
                              Unsorted.method2104(class11_2, false, 32);
                           continue;
                        }
                        if (opcode == 1003) {
                           boolean flag3 = ItemDefinition.intsStack[--iStackCounter] == 1;
                           if (flag3 == (!class11_2.hidden)) {
                              class11_2.hidden = flag3;
                              Class20.method909(class11_2);
                           }
                           if (-1 == class11_2.anInt191)
                              Unsorted.method569(class11_2.componentHash);
                           continue;
                        }
                        if (opcode == 1004) {
                           iStackCounter -= 2;
                           class11_2.anInt216 = ItemDefinition.intsStack[iStackCounter];
                           class11_2.anInt160 = ItemDefinition.intsStack[iStackCounter - -1];
                           Class20.method909(class11_2);
                           Class3_Sub13_Sub12.method225(class11_2);
                           if (class11_2.type == 0)
                              Unsorted.method2104(class11_2, false, -127);
                           continue;
                        }
                        if (opcode != 1005)
                           break;
                        class11_2.aBoolean219 = ItemDefinition.intsStack[--iStackCounter] == 1;
                        continue;
                  }
                  if ((opcode < 1100 || 1200 <= opcode) && (opcode < 2100 || 2200 <= opcode)) {
                        if ((opcode < 1200 || 1300 <= opcode) && (2200 > opcode || opcode >= 2300)) {
                           if (opcode >= 1300 && opcode < 1400 || opcode >= 2300 && opcode < 2400) {
                              RSInterface class11_3;
                              if (2000 <= opcode) {
                                    class11_3 = Class7.getRSInterface(ItemDefinition.intsStack[--iStackCounter]);
                                    opcode -= 1000;
                              } else {
                                    class11_3 = flag ? Class164.aClass11_2055 : aClass11_1749;
                              }
                              if (opcode == 1300) {
                                    int i45 = ItemDefinition.intsStack[--iStackCounter] + -1;
                                    if (0 > i45 || i45 > 9)
                                       sStackCounter--;
                                    else
                                       class11_3.method857(ItemDefinition.stringsStack[--sStackCounter], i45);
                                    continue;
                              }
                              if (opcode == 1301) {
                                    iStackCounter -= 2;
                                    int j67 = ItemDefinition.intsStack[1 + iStackCounter];
                                    int j45 = ItemDefinition.intsStack[iStackCounter];
                                    class11_3.aClass11_302 = AbstractSprite.method638(j45, j67);
                                    continue;
                              }
                              if (opcode == 1302) {
                                    class11_3.aBoolean200 = ItemDefinition.intsStack[--iStackCounter] == 1;
                                    continue;
                              }
                              if (opcode == 1303) {
                                    class11_3.anInt214 = ItemDefinition.intsStack[--iStackCounter];
                                    continue;
                              }
                              if (opcode == 1304) {
                                    class11_3.anInt179 = ItemDefinition.intsStack[--iStackCounter];
                                    continue;
                              }
                              if (1305 == opcode) {
                                    class11_3.aClass94_277 = ItemDefinition.stringsStack[--sStackCounter];
                                    continue;
                              }
                              if (opcode == 1306) {
                                    class11_3.aClass94_245 = ItemDefinition.stringsStack[--sStackCounter];
                                    continue;
                              }
                              if (opcode == 1307) {
                                    class11_3.aClass94Array171 = null;
                                    continue;
                              }
                              if (opcode == 1308) {
                                    class11_3.anInt238 = ItemDefinition.intsStack[--iStackCounter];
                                    class11_3.anInt266 = ItemDefinition.intsStack[--iStackCounter];
                                    continue;
                              }
                              if (1309 != opcode)
                                    break;
                              int k45 = ItemDefinition.intsStack[--iStackCounter];
                              int k67 = ItemDefinition.intsStack[--iStackCounter];
                              if (k67 >= 1 && k67 <= 10)
                                    class11_3.method854(k67 + -1, k45);
                              continue;
                           }
                           if ((opcode < 1400 || opcode >= 1500) && (2400 > opcode || opcode >= 2500)) {
                              if (1600 > opcode) {
                                    RSInterface class11_4 = flag ? Class164.aClass11_2055 : aClass11_1749;
                                    if (opcode == 1500) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_4.anInt306;
                                       continue;
                                    }
                                    if (opcode == 1501) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_4.anInt210;
                                       continue;
                                    }
                                    if (opcode == 1502) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_4.width;
                                       continue;
                                    }
                                    if (opcode == 1503) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_4.height;
                                       continue;
                                    }
                                    if (opcode == 1504) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_4.hidden ? 1 : 0;
                                       continue;
                                    }
                                    if (opcode != 1505)
                                       break;
                                    ItemDefinition.intsStack[iStackCounter++] = class11_4.parentId;
                                    continue;
                              }
                              if (opcode < 1700) {
                                    RSInterface class11_5 = flag ? Class164.aClass11_2055 : aClass11_1749;
                                    if (opcode == 1600) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_5.anInt247;
                                       continue;
                                    }
                                    if (opcode == 1601) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_5.anInt208;
                                       continue;
                                    }
                                    if (opcode == 1602) {
                                       ItemDefinition.stringsStack[sStackCounter++] = class11_5.text;
                                       continue;
                                    }
                                    if (opcode == 1603) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_5.anInt240;
                                       continue;
                                    }
                                    if (opcode == 1604) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_5.anInt252;
                                       continue;
                                    }
                                    if (opcode == 1605) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_5.anInt164;
                                       continue;
                                    }
                                    if (opcode == 1606) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_5.anInt182;
                                       continue;
                                    }
                                    if (1607 == opcode) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_5.anInt280;
                                       continue;
                                    }
                                    if (opcode == 1608) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_5.anInt308;
                                       continue;
                                    }
                                    if (opcode == 1609) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_5.anInt223;
                                       continue;
                                    }
                                    if (1610 == opcode) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_5.anInt258;
                                       continue;
                                    }
                                    if (opcode == 1611) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_5.anInt264;
                                       continue;
                                    }
                                    if (opcode != 1612)
                                       break;
                                    ItemDefinition.intsStack[iStackCounter++] = class11_5.spriteArchiveId;
                                    continue;
                              }
                              if (opcode >= 1800) {
                                    if (opcode < 1900) {
                                       RSInterface class11_6 = flag ? Class164.aClass11_2055 : aClass11_1749;
                                       if (1800 == opcode) {
                                          ItemDefinition.intsStack[iStackCounter++] = Client.method44(class11_6).method101();
                                          continue;
                                       }
                                       if (1801 == opcode) {
                                          int l45 = ItemDefinition.intsStack[--iStackCounter];
                                          l45--;
                                          if (null == class11_6.aClass94Array171 || class11_6.aClass94Array171.length <= l45 || null == class11_6.aClass94Array171[l45])
                                                ItemDefinition.stringsStack[sStackCounter++] = TextCore.aClass94_2331;
                                          else
                                                ItemDefinition.stringsStack[sStackCounter++] = class11_6.aClass94Array171[l45];
                                          continue;
                                       }
                                       if (opcode != 1802)
                                          break;
                                       if (null != class11_6.aClass94_277)
                                          ItemDefinition.stringsStack[sStackCounter++] = class11_6.aClass94_277;
                                       else
                                          ItemDefinition.stringsStack[sStackCounter++] = TextCore.aClass94_2331;
                                       continue;
                                    }
                                    if (2600 > opcode) {
                                       RSInterface class11_7 = Class7.getRSInterface(ItemDefinition.intsStack[--iStackCounter]);
                                       if (opcode == 2500) {
                                          ItemDefinition.intsStack[iStackCounter++] = class11_7.anInt306;
                                          continue;
                                       }
                                       if (opcode == 2501) {
                                          ItemDefinition.intsStack[iStackCounter++] = class11_7.anInt210;
                                          continue;
                                       }
                                       if (2502 == opcode) {
                                          ItemDefinition.intsStack[iStackCounter++] = class11_7.width;
                                          continue;
                                       }
                                       if (opcode == 2503) {
                                          ItemDefinition.intsStack[iStackCounter++] = class11_7.height;
                                          continue;
                                       }
                                       if (2504 == opcode) {
                                          ItemDefinition.intsStack[iStackCounter++] = class11_7.hidden ? 1 : 0;
                                          continue;
                                       }
                                       if (opcode != 2505)
                                          break;
                                       ItemDefinition.intsStack[iStackCounter++] = class11_7.parentId;
                                       continue;
                                    }
                                    if (opcode >= 2700) {
                                       if (2800 <= opcode) {
                                          if (opcode >= 2900) {
                                                if (opcode < 3200) {
                                                   if (opcode == 3100) {
                                                      RSString class94_3 = ItemDefinition.stringsStack[--sStackCounter];
                                                      Class3_Sub30_Sub1.addChatMessage(TextCore.aClass94_2331, 0, class94_3, -1);
                                                      continue;
                                                   }
                                                   if (opcode == 3101) {
                                                      iStackCounter -= 2;
                                                      WorldMap.method628(ItemDefinition.intsStack[iStackCounter - -1], ItemDefinition.intsStack[iStackCounter], Class102.player);
                                                      continue;
                                                   }
                                                   if (opcode == 3103) {
                                                      Class3_Sub13_Sub19.method264((byte) 87);
                                                      continue;
                                                   }
                                                   if (opcode == 3104) {
                                                      RSString class94_4 = ItemDefinition.stringsStack[--sStackCounter];
                                                      int i46 = 0;
                                                      if (class94_4.isInteger())
                                                            i46 = class94_4.parseInt();
                                                      Class3_Sub13_Sub1.outgoingBuffer.putOpcode(23);
                                                      Class3_Sub13_Sub1.outgoingBuffer.writeInt(i46);
                                                      continue;
                                                   }
                                                   if (opcode == 3105) {
                                                      RSString class94_5 = ItemDefinition.stringsStack[--sStackCounter];
                                                      Class3_Sub13_Sub1.outgoingBuffer.putOpcode(244);
                                                      Class3_Sub13_Sub1.outgoingBuffer.writeLong(class94_5.toLong());
                                                      continue;
                                                   }
                                                   if (opcode == 3106) {
                                                      RSString class94_6 = ItemDefinition.stringsStack[--sStackCounter];
                                                      Class3_Sub13_Sub1.outgoingBuffer.putOpcode(65);
                                                      Class3_Sub13_Sub1.outgoingBuffer.writeByte(1 + class94_6.length());
                                                      Class3_Sub13_Sub1.outgoingBuffer.writeString(class94_6);
                                                      continue;
                                                   }
                                                   if (opcode == 3107) {
                                                      int i7 = ItemDefinition.intsStack[--iStackCounter];
                                                      RSString class94_44 = ItemDefinition.stringsStack[--sStackCounter];
                                                      Class166.method2258(i7, class94_44);
                                                      continue;
                                                   }
                                                   if (opcode == 3108) {
                                                      iStackCounter -= 3;
                                                      int j46 = ItemDefinition.intsStack[iStackCounter - -1];
                                                      int j7 = ItemDefinition.intsStack[iStackCounter];
                                                      int l67 = ItemDefinition.intsStack[2 + iStackCounter];
                                                      RSInterface class11_22 = Class7.getRSInterface(l67);
                                                      Class3_Sub28_Sub6.a(j46, j7, 115, class11_22);
                                                      continue;
                                                   }
                                                   if (opcode == 3109) {
                                                      iStackCounter -= 2;
                                                      int k7 = ItemDefinition.intsStack[iStackCounter];
                                                      RSInterface class11_20 = flag ? Class164.aClass11_2055 : aClass11_1749;
                                                      int k46 = ItemDefinition.intsStack[1 + iStackCounter];
                                                      Class3_Sub28_Sub6.a(k46, k7, 79, class11_20);
                                                      continue;
                                                   }
                                                   if (opcode != 3110)
                                                      break;
                                                   int l7 = ItemDefinition.intsStack[--iStackCounter];
                                                   Class3_Sub13_Sub1.outgoingBuffer.putOpcode(111);
                                                   Class3_Sub13_Sub1.outgoingBuffer.writeShort(l7);
                                                   continue;
                                                }
                                                if (opcode < 3300) {
                                                   if (opcode == 3200) {
                                                      iStackCounter -= 3;
                                                      Class3_Sub13_Sub6.method199(ItemDefinition.intsStack[iStackCounter - -1], ItemDefinition.intsStack[iStackCounter], ItemDefinition.intsStack[iStackCounter + 2]);
                                                      continue;
                                                   }
                                                   if (opcode == 3201) {
                                                      AtmosphereParser.method1427(ItemDefinition.intsStack[--iStackCounter]);
                                                      continue;
                                                   }
                                                   if (opcode != 3202)
                                                      break;
                                                   iStackCounter -= 2;
                                                   Class167.method2266(ItemDefinition.intsStack[1 + iStackCounter], ItemDefinition.intsStack[iStackCounter]);
                                                   continue;
                                                }
                                                if (opcode < 3400) {
                                                   if (opcode == 3300) {
                                                      ItemDefinition.intsStack[iStackCounter++] = Class44.anInt719;
                                                      continue;
                                                   }
                                                   if (opcode == 3301) {
                                                      iStackCounter -= 2;
                                                      int i8 = ItemDefinition.intsStack[iStackCounter];
                                                      int l46 = ItemDefinition.intsStack[1 + iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = RSInterface.method861(i8, 89, l46);
                                                      continue;
                                                   }
                                                   if (opcode == 3302) {
                                                      iStackCounter -= 2;
                                                      int i47 = ItemDefinition.intsStack[iStackCounter + 1];
                                                      int j8 = ItemDefinition.intsStack[iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = Class12.method872(j8, i47);
                                                      continue;
                                                   }
                                                   if (3303 == opcode) {
                                                      iStackCounter -= 2;
                                                      int j47 = ItemDefinition.intsStack[iStackCounter - -1];
                                                      int k8 = ItemDefinition.intsStack[iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = Class167.method2268((byte) -107, k8, j47);
                                                      continue;
                                                   }
                                                   if (3304 == opcode) {
                                                      int l8 = ItemDefinition.intsStack[--iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = Unsorted.method2069(l8).size;
                                                      continue;
                                                   }
                                                   if (opcode == 3305) { //Skill update listener (mostly spams health value)
                                                      int i9 = ItemDefinition.intsStack[--iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = Class3_Sub13_Sub15.anIntArray3185[i9];
                                                      continue;
                                                   }
                                                   if (opcode == 3306) { //Another Skill update listener (spams 10? Possible TOTAL hp)
                                                      int j9 = ItemDefinition.intsStack[--iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = Class3_Sub20.anIntArray2480[j9];
                                                      continue;
                                                   }
                                                   if (3307 == opcode) { //Hover tooltip for Skill Interface, total xp for selected skill
                                                      int k9 = ItemDefinition.intsStack[--iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = Class133.anIntArray1743[k9];
                                                      continue;
                                                   }
                                                   if (opcode == 3308) {
                                                      int l9 = WorldListCountry.localPlane;
                                                      int k47 = Class131.anInt1716 + (Class102.player.anInt2819 >> 7);
                                                      int i68 = (Class102.player.anInt2829 >> 7) - -Class82.anInt1152;
                                                      ItemDefinition.intsStack[iStackCounter++] = (l9 << 28) - (-(k47 << 14) - i68);
                                                      continue;
                                                   }
                                                   if (opcode == 3309) {
                                                      int i10 = ItemDefinition.intsStack[--iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = Unsorted.bitwiseAnd(16383, i10 >> 14);
                                                      continue;
                                                   }
                                                   if (3310 == opcode) {
                                                      int j10 = ItemDefinition.intsStack[--iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = j10 >> 28;
                                                      continue;
                                                   }
                                                   if (opcode == 3311) {
                                                      int k10 = ItemDefinition.intsStack[--iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = Unsorted.bitwiseAnd(k10, 16383);
                                                      continue;
                                                   }
                                                   if (opcode == 3312) {
                                                      ItemDefinition.intsStack[iStackCounter++] = Unsorted.isMember ? 1 : 0;
                                                      continue;
                                                   }
                                                   if (3313 == opcode) {
                                                      iStackCounter -= 2;
                                                      int l10 = 32768 + ItemDefinition.intsStack[iStackCounter];
                                                      int l47 = ItemDefinition.intsStack[iStackCounter - -1];
                                                      ItemDefinition.intsStack[iStackCounter++] = RSInterface.method861(l10, 118, l47);
                                                      continue;
                                                   }
                                                   if (3314 == opcode) {
                                                      iStackCounter -= 2;
                                                      int i11 = ItemDefinition.intsStack[iStackCounter] - -32768;
                                                      int i48 = ItemDefinition.intsStack[1 + iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = Class12.method872(i11, i48);
                                                      continue;
                                                   }
                                                   if (3315 == opcode) {
                                                      iStackCounter -= 2;
                                                      int j11 = 32768 + ItemDefinition.intsStack[iStackCounter];
                                                      int j48 = ItemDefinition.intsStack[1 + iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = Class167.method2268((byte) -52, j11, j48);
                                                      continue;
                                                   }
                                                   if (opcode == 3316) {
                                                      if (Class3_Sub13_Sub26.rights < 2)
                                                            ItemDefinition.intsStack[iStackCounter++] = 0;
                                                      else
                                                            ItemDefinition.intsStack[iStackCounter++] = Class3_Sub13_Sub26.rights;
                                                      continue;
                                                   }
                                                   if (opcode == 3317) {
                                                      ItemDefinition.intsStack[iStackCounter++] = Class38_Sub1.anInt2617;
                                                      continue;
                                                   }
                                                   if (3318 == opcode) {
                                                      ItemDefinition.intsStack[iStackCounter++] = anInt2451;
                                                      continue;
                                                   }
                                                   if (3321 == opcode) {
                                                      ItemDefinition.intsStack[iStackCounter++] = Unsorted.anInt136;
                                                      continue;
                                                   }
                                                   if (opcode == 3322) {
                                                      ItemDefinition.intsStack[iStackCounter++] = MouseListeningClass.anInt1925;
                                                      continue;
                                                   }
                                                   if (3323 == opcode) {
                                                      if (anInt3775 >= 5 && anInt3775 <= 9)
                                                            ItemDefinition.intsStack[iStackCounter++] = 1;
                                                      else
                                                            ItemDefinition.intsStack[iStackCounter++] = 0;
                                                      continue;
                                                   }
                                                   if (opcode == 3324) {
                                                      if (anInt3775 < 5 || anInt3775 > 9)
                                                            ItemDefinition.intsStack[iStackCounter++] = 0;
                                                      else
                                                            ItemDefinition.intsStack[iStackCounter++] = anInt3775;
                                                      continue;
                                                   }
                                                   if (3325 == opcode) {
                                                      ItemDefinition.intsStack[iStackCounter++] = Class3_Sub13_Sub29.disableGEBoxes ? 1 : 0;
                                                      continue;
                                                   }
                                                   if (3326 == opcode) {
                                                      ItemDefinition.intsStack[iStackCounter++] = Class102.player.COMBAT_LEVEL;
                                                      continue;
                                                   }
                                                   if (3327 == opcode) {
                                                      ItemDefinition.intsStack[iStackCounter++] = Class102.player.class52.aBoolean864 ? 1 : 0;
                                                      continue;
                                                   }
                                                   if (3328 == opcode) {
                                                      ItemDefinition.intsStack[iStackCounter++] = !Class3_Sub15.aBoolean2433 || Class121.aBoolean1641 ? 0 : 1;
                                                      continue;
                                                   }
                                                   if (3329 == opcode) {
                                                      ItemDefinition.intsStack[iStackCounter++] = Class3_Sub13_Sub14.aBoolean3166 ? 1 : 0;
                                                      continue;
                                                   }
                                                   if (opcode == 3330) {
                                                      int k11 = ItemDefinition.intsStack[--iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = WorldListEntry.method1079(k11);
                                                      continue;
                                                   }
                                                   if (opcode == 3331) {
                                                      iStackCounter -= 2;
                                                      int k48 = ItemDefinition.intsStack[1 + iStackCounter];
                                                      int l11 = ItemDefinition.intsStack[iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = method1643(false, l11, k48);
                                                      continue;
                                                   }
                                                   if (3332 == opcode) {
                                                      iStackCounter -= 2;
                                                      int i12 = ItemDefinition.intsStack[iStackCounter];
                                                      int l48 = ItemDefinition.intsStack[iStackCounter + 1];
                                                      ItemDefinition.intsStack[iStackCounter++] = method1643(true, i12, l48);
                                                      continue;
                                                   }
                                                   if (3333 == opcode) {
                                                      ItemDefinition.intsStack[iStackCounter++] = Class7.anInt2161;
                                                      continue;
                                                   }
                                                   if (3335 == opcode) {
                                                      ItemDefinition.intsStack[iStackCounter++] = Class3_Sub20.language;
                                                      continue;
                                                   }
                                                   if (opcode == 3336) {
                                                      iStackCounter -= 4;
                                                      int i49 = ItemDefinition.intsStack[iStackCounter - -1];
                                                      int j12 = ItemDefinition.intsStack[iStackCounter];
                                                      j12 += i49 << 14;
                                                      int k76 = ItemDefinition.intsStack[3 + iStackCounter];
                                                      int j68 = ItemDefinition.intsStack[2 + iStackCounter];
                                                      j12 += j68 << 28;
                                                      j12 += k76;
                                                      ItemDefinition.intsStack[iStackCounter++] = j12;
                                                      continue;
                                                   }
                                                   if (opcode != 3337)
                                                      break;
                                                   ItemDefinition.intsStack[iStackCounter++] = Class3_Sub26.anInt2554;
                                                   continue;
                                                }
                                                if (opcode < 3500) {
                                                   if (opcode == 3400) {
                                                      iStackCounter -= 2;
                                                      int k12 = ItemDefinition.intsStack[iStackCounter];
                                                      int j49 = ItemDefinition.intsStack[1 + iStackCounter];
                                                      EnumDefinition enumDefinition_1 = EnumDefinitionProvider.provide(k12);
                                                      ItemDefinition.stringsStack[sStackCounter++] = enumDefinition_1.getString(j49);
                                                      continue;
                                                   }
                                                   if (3408 == opcode) {
                                                      iStackCounter -= 4;
                                                      int l12 = ItemDefinition.intsStack[iStackCounter];
                                                      int k49 = ItemDefinition.intsStack[1 + iStackCounter];
                                                      int l76 = ItemDefinition.intsStack[3 + iStackCounter];
                                                      int k68 = ItemDefinition.intsStack[iStackCounter - -2];
                                                      EnumDefinition enumDefinition_4 = EnumDefinitionProvider.provide(k68);
                                                      if (enumDefinition_4.getKeyType() != l12 || k49 != enumDefinition_4.getValueType())
                                                            throw new RuntimeException("C3408-1");
                                                      if (k49 != 115)
                                                            ItemDefinition.intsStack[iStackCounter++] = enumDefinition_4.getInt(l76);
                                                      else
                                                            ItemDefinition.stringsStack[sStackCounter++] = enumDefinition_4.getString(l76);
                                                      continue;
                                                   }
                                                   if (opcode == 3409) {
                                                      iStackCounter -= 3;
                                                      int l49 = ItemDefinition.intsStack[iStackCounter - -1];
                                                      int l68 = ItemDefinition.intsStack[iStackCounter + 2];
                                                      int i13 = ItemDefinition.intsStack[iStackCounter];
                                                      if (l49 == -1)
                                                            throw new RuntimeException("C3409-2");
                                                      EnumDefinition class3_sub28_sub13_3 = EnumDefinitionProvider.provide(l49);
                                                      if (i13 != class3_sub28_sub13_3.getValueType())
                                                            throw new RuntimeException("C3409-1");
                                                      ItemDefinition.intsStack[iStackCounter++] = class3_sub28_sub13_3.containsValue(l68) ? 1 : 0;
                                                      continue;
                                                   }
                                                   if (opcode == 3410) {
                                                      int j13 = ItemDefinition.intsStack[--iStackCounter];
                                                      RSString class94_45 = ItemDefinition.stringsStack[--sStackCounter];
                                                      if (j13 == -1)
                                                            throw new RuntimeException("C3410-2");
                                                      EnumDefinition enumDefinition_2 = EnumDefinitionProvider.provide(j13);
                                                      if (enumDefinition_2.getValueType() != 115)
                                                            throw new RuntimeException("C3410-1");
                                                      ItemDefinition.intsStack[iStackCounter++] = enumDefinition_2.containsValue(class94_45) ? 1 : 0;
                                                      continue;
                                                   }
                                                   if (opcode != 3411)
                                                      break;
                                                   int k13 = ItemDefinition.intsStack[--iStackCounter];
                                                   EnumDefinition enumDefinition = EnumDefinitionProvider.provide(k13);
                                                   ItemDefinition.intsStack[iStackCounter++] = enumDefinition.getValues().size();
                                                   continue;
                                                }
                                                if (3700 > opcode) {
                                                   if (3600 == opcode) {
                                                      if (anInt1357 == 0)
                                                            ItemDefinition.intsStack[iStackCounter++] = -2;
                                                      else if (anInt1357 != 1)
                                                            ItemDefinition.intsStack[iStackCounter++] = Class8.anInt104;
                                                      else
                                                            ItemDefinition.intsStack[iStackCounter++] = -1;
                                                      continue;
                                                   }
                                                   if (3601 == opcode) {
                                                      int l13 = ItemDefinition.intsStack[--iStackCounter];
                                                      if (anInt1357 != 2 || Class8.anInt104 <= l13)
                                                            ItemDefinition.stringsStack[sStackCounter++] = TextCore.aClass94_2331;
                                                      else
                                                            ItemDefinition.stringsStack[sStackCounter++] = Class70.aClass94Array1046[l13];
                                                      continue;
                                                   }
                                                   if (opcode == 3602) {
                                                      int i14 = ItemDefinition.intsStack[--iStackCounter];
                                                      if (anInt1357 != 2 || i14 >= Class8.anInt104)
                                                            ItemDefinition.intsStack[iStackCounter++] = 0;
                                                      else
                                                            ItemDefinition.intsStack[iStackCounter++] = Unsorted.anIntArray882[i14];
                                                      continue;
                                                   }
                                                   if (opcode == 3603) {
                                                      int j14 = ItemDefinition.intsStack[--iStackCounter];
                                                      if (2 == anInt1357 && Class8.anInt104 > j14)
                                                            ItemDefinition.intsStack[iStackCounter++] = Class57.anIntArray904[j14];
                                                      else
                                                            ItemDefinition.intsStack[iStackCounter++] = 0;
                                                      continue;
                                                   }
                                                   if (3604 == opcode) {
                                                      int i50 = ItemDefinition.intsStack[--iStackCounter];
                                                      RSString class94_7 = ItemDefinition.stringsStack[--sStackCounter];
                                                      Class100.method1605(class94_7, i50);
                                                      continue;
                                                   }
                                                   if (opcode == 3605) {
                                                      RSString class94_8 = ItemDefinition.stringsStack[--sStackCounter];
                                                      Class163_Sub3.method2229(class94_8.toLong());
                                                      continue;
                                                   }
                                                   if (opcode == 3606) {
                                                      RSString class94_9 = ItemDefinition.stringsStack[--sStackCounter];
                                                      Class3_Sub13_Sub27.method297(class94_9.toLong(), 1);
                                                      continue;
                                                   }
                                                   if (opcode == 3607) {
                                                      RSString class94_10 = ItemDefinition.stringsStack[--sStackCounter];
                                                      Class81.friendsIgnoreListAlerts(class94_10.toLong());
                                                      continue;
                                                   }
                                                   if (opcode == 3608) {
                                                      RSString class94_11 = ItemDefinition.stringsStack[--sStackCounter];
                                                      Class3_Sub13_Sub10.method212(class94_11.toLong());
                                                      continue;
                                                   }
                                                   if (opcode == 3609) {
                                                      RSString class94_12 = ItemDefinition.stringsStack[--sStackCounter];
                                                      if (class94_12.startsWith(TextCore.aClass94_2323) || class94_12.startsWith(TextCore.aClass94_3190))
                                                            class94_12 = class94_12.substring(7);
                                                      ItemDefinition.intsStack[iStackCounter++] = ItemDefinition.method1176(class94_12) ? 1 : 0;
                                                      continue;
                                                   }
                                                   if (opcode == 3610) {
                                                      int k14 = ItemDefinition.intsStack[--iStackCounter];
                                                      if (anInt1357 == 2 && Class8.anInt104 > k14)
                                                            ItemDefinition.stringsStack[sStackCounter++] = Unsorted.aClass94Array2566[k14];
                                                      else
                                                            ItemDefinition.stringsStack[sStackCounter++] = TextCore.aClass94_2331;
                                                      continue;
                                                   }
                                                   if (opcode == 3611) {
                                                      if (RSInterface.aClass94_251 != null)
                                                            ItemDefinition.stringsStack[sStackCounter++] = RSInterface.aClass94_251.method1545();
                                                      else
                                                            ItemDefinition.stringsStack[sStackCounter++] = TextCore.aClass94_2331;
                                                      continue;
                                                   }
                                                   if (opcode == 3612) {
                                                      if (null != RSInterface.aClass94_251)
                                                            ItemDefinition.intsStack[iStackCounter++] = Unsorted.clanSize;
                                                      else
                                                            ItemDefinition.intsStack[iStackCounter++] = 0;
                                                      continue;
                                                   }
                                                   if (opcode == 3613) {
                                                      int l14 = ItemDefinition.intsStack[--iStackCounter];
                                                      if (RSInterface.aClass94_251 == null || l14 >= Unsorted.clanSize)
                                                            ItemDefinition.stringsStack[sStackCounter++] = TextCore.aClass94_2331;
                                                      else
                                                            ItemDefinition.stringsStack[sStackCounter++] = PacketParser.aClass3_Sub19Array3694[l14].aClass94_2476.method1545();
                                                      continue;
                                                   }
                                                   if (opcode == 3614) {
                                                      int i15 = ItemDefinition.intsStack[--iStackCounter];
                                                      if (RSInterface.aClass94_251 == null || i15 >= Unsorted.clanSize)
                                                            ItemDefinition.intsStack[iStackCounter++] = 0;
                                                      else
                                                            ItemDefinition.intsStack[iStackCounter++] = PacketParser.aClass3_Sub19Array3694[i15].anInt2478;
                                                      continue;
                                                   }
                                                   if (3615 == opcode) {
                                                      int j15 = ItemDefinition.intsStack[--iStackCounter];
                                                      if (null == RSInterface.aClass94_251 || j15 >= Unsorted.clanSize)
                                                            ItemDefinition.intsStack[iStackCounter++] = 0;
                                                      else
                                                            ItemDefinition.intsStack[iStackCounter++] = PacketParser.aClass3_Sub19Array3694[j15].aByte2472;
                                                      continue;
                                                   }
                                                   if (3616 == opcode) {
                                                      ItemDefinition.intsStack[iStackCounter++] = Player.aByte3953;
                                                      continue;
                                                   }
                                                   if (opcode == 3617) {
                                                      RSString class94_13 = ItemDefinition.stringsStack[--sStackCounter];
                                                      Class106.method1642(class94_13);
                                                      continue;
                                                   }
                                                   if (opcode == 3618) {
                                                      ItemDefinition.intsStack[iStackCounter++] = Class91.aByte1308;
                                                      continue;
                                                   }
                                                   if (opcode == 3619) {
                                                      RSString class94_14 = ItemDefinition.stringsStack[--sStackCounter];
                                                      Class3_Sub22.method400(class94_14.toLong());
                                                      continue;
                                                   }
                                                   if (opcode == 3620) {
                                                      Class77.method1368();
                                                      continue;
                                                   }
                                                   if (opcode == 3621) {
                                                      if (anInt1357 == 0)
                                                            ItemDefinition.intsStack[iStackCounter++] = -1;
                                                      else
                                                            ItemDefinition.intsStack[iStackCounter++] = Class3_Sub28_Sub5.anInt3591;
                                                      continue;
                                                   }
                                                   if (3622 == opcode) {
                                                      int k15 = ItemDefinition.intsStack[--iStackCounter];
                                                      if (anInt1357 == 0 || Class3_Sub28_Sub5.anInt3591 <= k15)
                                                            ItemDefinition.stringsStack[sStackCounter++] = TextCore.aClass94_2331;
                                                      else
                                                            ItemDefinition.stringsStack[sStackCounter++] = Unsorted.method1052(Class114.ignores[k15]).method1545();
                                                      continue;
                                                   }
                                                   if (3623 == opcode) {
                                                      RSString class94_15 = ItemDefinition.stringsStack[--sStackCounter];
                                                      if (class94_15.startsWith(TextCore.aClass94_2323) || class94_15.startsWith(TextCore.aClass94_3190))
                                                            class94_15 = class94_15.substring(7);
                                                      ItemDefinition.intsStack[iStackCounter++] = Class3_Sub24_Sub3.method467(class94_15) ? 1 : 0;
                                                      continue;
                                                   }
                                                   if (opcode == 3624) {
                                                      int l15 = ItemDefinition.intsStack[--iStackCounter];
                                                      if (null != PacketParser.aClass3_Sub19Array3694 && l15 < Unsorted.clanSize && PacketParser.aClass3_Sub19Array3694[l15].aClass94_2476.equalsStringIgnoreCase(Class102.player.displayName))
                                                            ItemDefinition.intsStack[iStackCounter++] = 1;
                                                      else
                                                            ItemDefinition.intsStack[iStackCounter++] = 0;
                                                      continue;
                                                   }
                                                   if (opcode == 3625) {
                                                      if (Class161.aClass94_2035 == null)
                                                            ItemDefinition.stringsStack[sStackCounter++] = TextCore.aClass94_2331;
                                                      else
                                                            ItemDefinition.stringsStack[sStackCounter++] = Class161.aClass94_2035.method1545();
                                                      continue;
                                                   }
                                                   if (3626 == opcode) {
                                                      int i16 = ItemDefinition.intsStack[--iStackCounter];
                                                      if (RSInterface.aClass94_251 == null || i16 >= Unsorted.clanSize)
                                                            ItemDefinition.stringsStack[sStackCounter++] = TextCore.aClass94_2331;
                                                      else
                                                            ItemDefinition.stringsStack[sStackCounter++] = PacketParser.aClass3_Sub19Array3694[i16].aClass94_2473;
                                                      continue;
                                                   }
                                                   if (opcode == 3627) {
                                                      int j16 = ItemDefinition.intsStack[--iStackCounter];
                                                      if (anInt1357 != 2 || 0 > j16 || Class8.anInt104 <= j16)
                                                            ItemDefinition.intsStack[iStackCounter++] = 0;
                                                      else
                                                            ItemDefinition.intsStack[iStackCounter++] = Unsorted.aBooleanArray73[j16] ? 1 : 0;
                                                      continue;
                                                   }
                                                   if (opcode == 3628) {
                                                      RSString class94_16 = ItemDefinition.stringsStack[--sStackCounter];
                                                      if (class94_16.startsWith(TextCore.aClass94_2323) || class94_16.startsWith(TextCore.aClass94_3190))
                                                            class94_16 = class94_16.substring(7);
                                                      ItemDefinition.intsStack[iStackCounter++] = PacketParser.method826(class94_16, -1);
                                                      continue;
                                                   }
                                                   if (opcode != 3629)
                                                      break;
                                                   ItemDefinition.intsStack[iStackCounter++] = Class3_Sub31.countryId;
                                                   continue;
                                                }
                                                if (opcode < 4000) {
                                                   if (opcode == 3903) {
                                                      int k16 = ItemDefinition.intsStack[--iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = Class3_Sub13_Sub33.aClass133Array3393[k16].method1805();
                                                      continue;
                                                   }
                                                   if (opcode == 3904) {
                                                      int l16 = ItemDefinition.intsStack[--iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = Class3_Sub13_Sub33.aClass133Array3393[l16].anInt1752;
                                                      continue;
                                                   }
                                                   if (opcode == 3905) {
                                                      int i17 = ItemDefinition.intsStack[--iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = Class3_Sub13_Sub33.aClass133Array3393[i17].anInt1757;
                                                      continue;
                                                   }
                                                   if (opcode == 3906) {
                                                      int j17 = ItemDefinition.intsStack[--iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = Class3_Sub13_Sub33.aClass133Array3393[j17].anInt1747;
                                                      continue;
                                                   }
                                                   if (opcode == 3907) {
                                                      int k17 = ItemDefinition.intsStack[--iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = Class3_Sub13_Sub33.aClass133Array3393[k17].anInt1746;
                                                      continue;
                                                   }
                                                   if (3908 == opcode) {
                                                      int l17 = ItemDefinition.intsStack[--iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = Class3_Sub13_Sub33.aClass133Array3393[l17].anInt1750;
                                                      continue;
                                                   }
                                                   if (3910 == opcode) {
                                                      int i18 = ItemDefinition.intsStack[--iStackCounter];
                                                      int j50 = Class3_Sub13_Sub33.aClass133Array3393[i18].method1804();
                                                      ItemDefinition.intsStack[iStackCounter++] = j50 == 0 ? 1 : 0;
                                                      continue;
                                                   }
                                                   if (3911 == opcode) {
                                                      int j18 = ItemDefinition.intsStack[--iStackCounter];
                                                      int k50 = Class3_Sub13_Sub33.aClass133Array3393[j18].method1804();
                                                      ItemDefinition.intsStack[iStackCounter++] = k50 != 2 ? 0 : 1;
                                                      continue;
                                                   }
                                                   if (opcode == 3912) {
                                                      int k18 = ItemDefinition.intsStack[--iStackCounter];
                                                      int l50 = Class3_Sub13_Sub33.aClass133Array3393[k18].method1804();
                                                      ItemDefinition.intsStack[iStackCounter++] = l50 == 5 ? 1 : 0;
                                                      continue;
                                                   }
                                                   if (opcode != 3913)
                                                      break;
                                                   int l18 = ItemDefinition.intsStack[--iStackCounter];
                                                   int i51 = Class3_Sub13_Sub33.aClass133Array3393[l18].method1804();
                                                   ItemDefinition.intsStack[iStackCounter++] = 1 == i51 ? 1 : 0;
                                                   continue;
                                                }
                                                if (opcode < 4100) {
                                                   if (opcode == 4000) {
                                                      iStackCounter -= 2;
                                                      int i19 = ItemDefinition.intsStack[iStackCounter];
                                                      int j51 = ItemDefinition.intsStack[iStackCounter - -1];
                                                      ItemDefinition.intsStack[iStackCounter++] = j51 + i19;
                                                      continue;
                                                   }
                                                   if (opcode == 4001) {
                                                      iStackCounter -= 2;
                                                      int j19 = ItemDefinition.intsStack[iStackCounter];
                                                      int k51 = ItemDefinition.intsStack[1 + iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = -k51 + j19;
                                                      continue;
                                                   }
                                                   if (4002 == opcode) {
                                                      iStackCounter -= 2;
                                                      int k19 = ItemDefinition.intsStack[iStackCounter];
                                                      int l51 = ItemDefinition.intsStack[1 + iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = l51 * k19;
                                                      continue;
                                                   }
                                                   if (4003 == opcode) {
                                                      iStackCounter -= 2;
                                                      int l19 = ItemDefinition.intsStack[iStackCounter];
                                                      int i52 = ItemDefinition.intsStack[iStackCounter - -1];
                                                      ItemDefinition.intsStack[iStackCounter++] = l19 / i52;
                                                      continue;
                                                   }
                                                   if (opcode == 4004) {
                                                      int i20 = ItemDefinition.intsStack[--iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = (int) ((double) i20 * Math.random());
                                                      continue;
                                                   }
                                                   if (4005 == opcode) {
                                                      int j20 = ItemDefinition.intsStack[--iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = (int) (Math.random() * (double) (1 + j20));
                                                      continue;
                                                   }
                                                   if (4006 == opcode) {
                                                      iStackCounter -= 5;
                                                      int k20 = ItemDefinition.intsStack[iStackCounter];
                                                      int j52 = ItemDefinition.intsStack[iStackCounter - -1];
                                                      int i77 = ItemDefinition.intsStack[iStackCounter - -3];
                                                      int i69 = ItemDefinition.intsStack[2 + iStackCounter];
                                                      int j79 = ItemDefinition.intsStack[iStackCounter + 4];
                                                      ItemDefinition.intsStack[iStackCounter++] = ((-k20 + j52) * (j79 + -i69)) / (-i69 + i77) + k20;
                                                      continue;
                                                   }
                                                   if (opcode == 4007) {
                                                      iStackCounter -= 2;
                                                      long l20 = ItemDefinition.intsStack[iStackCounter];
                                                      long l69 = ItemDefinition.intsStack[iStackCounter + 1];
                                                      ItemDefinition.intsStack[iStackCounter++] = (int) ((l20 * l69) / 100L + l20);
                                                      continue;
                                                   }
                                                   if (opcode == 4008) {
                                                      iStackCounter -= 2;
                                                      int i21 = ItemDefinition.intsStack[iStackCounter];
                                                      int k52 = ItemDefinition.intsStack[1 + iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = Class3_Sub13_Sub29.bitwiseOr(i21, 1 << k52);
                                                      continue;
                                                   }
                                                   if (4009 == opcode) {
                                                      iStackCounter -= 2;
                                                      int j21 = ItemDefinition.intsStack[iStackCounter];
                                                      int l52 = ItemDefinition.intsStack[1 + iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = Unsorted.bitwiseAnd(-1 - (1 << l52), j21);
                                                      continue;
                                                   }
                                                   if (opcode == 4010) {
                                                      iStackCounter -= 2;
                                                      int k21 = ItemDefinition.intsStack[iStackCounter];
                                                      int i53 = ItemDefinition.intsStack[iStackCounter - -1];
                                                      ItemDefinition.intsStack[iStackCounter++] = Unsorted.bitwiseAnd(k21, 1 << i53) != 0 ? 1 : 0;
                                                      continue;
                                                   }
                                                   if (opcode == 4011) {
                                                      iStackCounter -= 2;
                                                      int j53 = ItemDefinition.intsStack[iStackCounter - -1];
                                                      int l21 = ItemDefinition.intsStack[iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = l21 % j53;
                                                      continue;
                                                   }
                                                   if (opcode == 4012) {
                                                      iStackCounter -= 2;
                                                      int k53 = ItemDefinition.intsStack[iStackCounter + 1];
                                                      int i22 = ItemDefinition.intsStack[iStackCounter];
                                                      if (0 != i22)
                                                            ItemDefinition.intsStack[iStackCounter++] = (int) Math.pow(i22, k53);
                                                      else
                                                            ItemDefinition.intsStack[iStackCounter++] = 0;
                                                      continue;
                                                   }
                                                   if (opcode == 4013) {
                                                      iStackCounter -= 2;
                                                      int l53 = ItemDefinition.intsStack[iStackCounter - -1];
                                                      int j22 = ItemDefinition.intsStack[iStackCounter];
                                                      if (j22 == 0) {
                                                            ItemDefinition.intsStack[iStackCounter++] = 0;
                                                      } else if (l53 == 0)
                                                            ItemDefinition.intsStack[iStackCounter++] = 0x7fffffff;
                                                      else
                                                            ItemDefinition.intsStack[iStackCounter++] = (int) Math.pow(j22, 1.0D / (double) l53);
                                                      continue;
                                                   }
                                                   if (opcode == 4014) {
                                                      iStackCounter -= 2;
                                                      int i54 = ItemDefinition.intsStack[iStackCounter + 1];
                                                      int k22 = ItemDefinition.intsStack[iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = Unsorted.bitwiseAnd(i54, k22);
                                                      continue;
                                                   }
                                                   if (opcode == 4015) {
                                                      iStackCounter -= 2;
                                                      int l22 = ItemDefinition.intsStack[iStackCounter];
                                                      int j54 = ItemDefinition.intsStack[iStackCounter + 1];
                                                      ItemDefinition.intsStack[iStackCounter++] = Class3_Sub13_Sub29.bitwiseOr(l22, j54);
                                                      continue;
                                                   }
                                                   if (opcode == 4016) {
                                                      iStackCounter -= 2;
                                                      int i23 = ItemDefinition.intsStack[iStackCounter];
                                                      int k54 = ItemDefinition.intsStack[1 + iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = k54 <= i23 ? k54 : i23;
                                                      continue;
                                                   }
                                                   if (opcode == 4017) {
                                                      iStackCounter -= 2;
                                                      int l54 = ItemDefinition.intsStack[1 + iStackCounter];
                                                      int j23 = ItemDefinition.intsStack[iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = j23 > l54 ? j23 : l54;
                                                      continue;
                                                   }
                                                   if (opcode != 4018)
                                                      break;
                                                   iStackCounter -= 3;
                                                   long l23 = ItemDefinition.intsStack[iStackCounter];
                                                   long l70 = ItemDefinition.intsStack[iStackCounter + 1];
                                                   long l79 = ItemDefinition.intsStack[2 + iStackCounter];
                                                   ItemDefinition.intsStack[iStackCounter++] = (int) ((l23 * l79) / l70);
                                                   continue;
                                                }
                                                if (4200 <= opcode) {
                                                   if (opcode >= 4300) {
                                                      if (opcode < 4400) {
                                                            if (4300 != opcode)
                                                               break;
                                                            iStackCounter -= 2;
                                                            int k23 = ItemDefinition.intsStack[iStackCounter];
                                                            int i55 = ItemDefinition.intsStack[1 + iStackCounter];
                                                            Class3_Sub28_Sub9 class3_sub28_sub9 = Class61.method1210(i55);
                                                            if (!class3_sub28_sub9.method585())
                                                               ItemDefinition.intsStack[iStackCounter++] = Unsorted.method522(k23).method1475(i55, class3_sub28_sub9.anInt3614);
                                                            else
                                                               ItemDefinition.stringsStack[sStackCounter++] = Unsorted.method522(k23).method1477(i55, class3_sub28_sub9.aClass94_3619);
                                                            continue;
                                                      }
                                                      if (opcode >= 4500) {
                                                            if (opcode >= 4600) {
                                                               if (opcode < 5100) {
                                                                  if (opcode == 5000) {
                                                                        ItemDefinition.intsStack[iStackCounter++] = anInt3101;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5001) {
                                                                        iStackCounter -= 3;
                                                                        anInt3101 = ItemDefinition.intsStack[iStackCounter];
                                                                        Class24.anInt467 = ItemDefinition.intsStack[1 + iStackCounter];
                                                                        Class45.anInt734 = ItemDefinition.intsStack[2 + iStackCounter];
                                                                        Class3_Sub13_Sub1.outgoingBuffer.putOpcode(157);
                                                                        Class3_Sub13_Sub1.outgoingBuffer.writeByte(anInt3101);
                                                                        Class3_Sub13_Sub1.outgoingBuffer.writeByte(Class24.anInt467);
                                                                        Class3_Sub13_Sub1.outgoingBuffer.writeByte(Class45.anInt734);
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5002) {
                                                                        RSString class94_17 = ItemDefinition.stringsStack[--sStackCounter];
                                                                        iStackCounter -= 2;
                                                                        int j55 = ItemDefinition.intsStack[iStackCounter];
                                                                        int j69 = ItemDefinition.intsStack[1 + iStackCounter];
                                                                        Class3_Sub13_Sub1.outgoingBuffer.putOpcode(99);
                                                                        Class3_Sub13_Sub1.outgoingBuffer.writeLong(class94_17.toLong());
                                                                        Class3_Sub13_Sub1.outgoingBuffer.writeByte(j55 - 1);
                                                                        Class3_Sub13_Sub1.outgoingBuffer.writeByte(j69);
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5003) {
                                                                        RSString class94_46 = null;
                                                                        int i24 = ItemDefinition.intsStack[--iStackCounter];
                                                                        if (i24 < 100)
                                                                           class94_46 = LinkableRSString.aClass94Array2580[i24];
                                                                        if (class94_46 == null)
                                                                           class94_46 = TextCore.aClass94_2331;
                                                                        ItemDefinition.stringsStack[sStackCounter++] = class94_46;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5004) {
                                                                        int j24 = ItemDefinition.intsStack[--iStackCounter];
                                                                        int k55 = -1;
                                                                        if (j24 < 100 && null != LinkableRSString.aClass94Array2580[j24])
                                                                           k55 = Class3_Sub28_Sub12.anIntArray3082[j24];
                                                                        ItemDefinition.intsStack[iStackCounter++] = k55;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5005) {
                                                                        ItemDefinition.intsStack[iStackCounter++] = Class24.anInt467;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5008) {//Used for a lot of things involving :: || More prefixes can be added by using || and listing said added way, ie ;; can be used instead of ::
                                                                        RSString class94_18 = ItemDefinition.stringsStack[--sStackCounter];
                                                                        if (class94_18.startsWith(TextCore.aClass94_132) || class94_18.startsWith(RSString.parse(";;")))
                                                                           ClientCommands.ClientCommands(class94_18);
                                                                        else if (Class3_Sub13_Sub26.rights != 0 || (!Class3_Sub15.aBoolean2433 || Class121.aBoolean1641) && !Class3_Sub13_Sub14.aBoolean3166) {
                                                                           RSString class94_47 = class94_18.toLowercase();
                                                                           byte byte3 = 0;
                                                                           if (class94_47.startsWith(TextCore.TextColorYellow)) {
                                                                              byte3 = 0;
                                                                              class94_18 = class94_18.substring(TextCore.TextColorYellow.length());
                                                                           } else if (class94_47.startsWith(TextCore.TextColorRed)) {
                                                                              class94_18 = class94_18.substring(TextCore.TextColorRed.length());
                                                                              byte3 = 1;
                                                                           } else if (class94_47.startsWith(TextCore.TextColorGreen)) {
                                                                              class94_18 = class94_18.substring(TextCore.TextColorGreen.length());
                                                                              byte3 = 2;
                                                                           } else if (class94_47.startsWith(TextCore.TextColorCyan)) {
                                                                              byte3 = 3;
                                                                              class94_18 = class94_18.substring(TextCore.TextColorCyan.length());
                                                                           } else if (class94_47.startsWith(TextCore.TextColorPurple)) {
                                                                              class94_18 = class94_18.substring(TextCore.TextColorPurple.length());
                                                                              byte3 = 4;
                                                                           } else if (class94_47.startsWith(TextCore.TextColorWhite)) {
                                                                              class94_18 = class94_18.substring(TextCore.TextColorWhite.length());
                                                                              byte3 = 5;
                                                                           } else if (class94_47.startsWith(TextCore.TextFlashOne)) {
                                                                              byte3 = 6;
                                                                              class94_18 = class94_18.substring(TextCore.TextFlashOne.length());
                                                                           } else if (class94_47.startsWith(TextCore.TextFlashTwo)) {
                                                                              byte3 = 7;
                                                                              class94_18 = class94_18.substring(TextCore.TextFlashTwo.length());
                                                                           } else if (class94_47.startsWith(TextCore.TextFlashThree)) {
                                                                              class94_18 = class94_18.substring(TextCore.TextFlashThree.length());
                                                                              byte3 = 8;
                                                                           } else if (class94_47.startsWith(TextCore.TextGlowOne)) {
                                                                              byte3 = 9;
                                                                              class94_18 = class94_18.substring(TextCore.TextGlowOne.length());
                                                                           } else if (class94_47.startsWith(TextCore.TextGlowTwo)) {
                                                                              byte3 = 10;
                                                                              class94_18 = class94_18.substring(TextCore.TextGlowTwo.length());
                                                                           } else if (class94_47.startsWith(TextCore.TextGlowThree)) {
                                                                              class94_18 = class94_18.substring(TextCore.TextGlowThree.length());
                                                                              byte3 = 11;
                                                                           } else if (0 != Class3_Sub20.language)
                                                                              if (class94_47.startsWith(TextCore.TextColorYellow)) {
                                                                                    byte3 = 0;
                                                                                    class94_18 = class94_18.substring(TextCore.TextColorYellow.length());
                                                                              } else if (class94_47.startsWith(TextCore.TextColorRed)) {
                                                                                    class94_18 = class94_18.substring(TextCore.TextColorRed.length());
                                                                                    byte3 = 1;
                                                                              } else if (class94_47.startsWith(TextCore.TextColorGreen)) {
                                                                                    class94_18 = class94_18.substring(TextCore.TextColorGreen.length());
                                                                                    byte3 = 2;
                                                                              } else if (class94_47.startsWith(TextCore.TextColorCyan)) {
                                                                                    class94_18 = class94_18.substring(TextCore.TextColorCyan.length());
                                                                                    byte3 = 3;
                                                                              } else if (class94_47.startsWith(TextCore.TextColorPurple)) {
                                                                                    class94_18 = class94_18.substring(TextCore.TextColorPurple.length());
                                                                                    byte3 = 4;
                                                                              } else if (class94_47.startsWith(TextCore.TextColorWhite)) {
                                                                                    byte3 = 5;
                                                                                    class94_18 = class94_18.substring(TextCore.TextColorWhite.length());
                                                                              } else if (class94_47.startsWith(TextCore.TextFlashOne)) {
                                                                                    class94_18 = class94_18.substring(TextCore.TextFlashOne.length());
                                                                                    byte3 = 6;
                                                                              } else if (class94_47.startsWith(TextCore.TextFlashTwo)) {
                                                                                    byte3 = 7;
                                                                                    class94_18 = class94_18.substring(TextCore.TextFlashTwo.length());
                                                                              } else if (class94_47.startsWith(TextCore.TextFlashThree)) {
                                                                                    byte3 = 8;
                                                                                    class94_18 = class94_18.substring(TextCore.TextFlashThree.length());
                                                                              } else if (class94_47.startsWith(TextCore.TextGlowOne)) {
                                                                                    byte3 = 9;
                                                                                    class94_18 = class94_18.substring(TextCore.TextGlowOne.length());
                                                                              } else if (class94_47.startsWith(TextCore.TextGlowTwo)) {
                                                                                    class94_18 = class94_18.substring(TextCore.TextGlowTwo.length());
                                                                                    byte3 = 10;
                                                                              } else if (class94_47.startsWith(TextCore.TextGlowThree)) {
                                                                                    class94_18 = class94_18.substring(TextCore.TextGlowThree.length());
                                                                                    byte3 = 11;
                                                                              }
                                                                           byte byte4 = 0;
                                                                           class94_47 = class94_18.toLowercase();
                                                                           if (class94_47.startsWith(TextCore.TextWave)) {
                                                                              class94_18 = class94_18.substring(TextCore.TextWave.length());
                                                                              byte4 = 1;
                                                                           } else if (class94_47.startsWith(TextCore.TextWaveTwo)) {
                                                                              byte4 = 2;
                                                                              class94_18 = class94_18.substring(TextCore.TextWaveTwo.length());
                                                                           } else if (class94_47.startsWith(TextCore.TextShake)) {
                                                                              class94_18 = class94_18.substring(TextCore.TextShake.length());
                                                                              byte4 = 3;
                                                                           } else if (class94_47.startsWith(TextCore.HasScroll)) {
                                                                              byte4 = 4;
                                                                              class94_18 = class94_18.substring(TextCore.HasScroll.length());
                                                                           } else if (class94_47.startsWith(TextCore.TextSlide)) {
                                                                              byte4 = 5;
                                                                              class94_18 = class94_18.substring(TextCore.TextSlide.length());
                                                                           } else if (0 != Class3_Sub20.language)
                                                                              if (class94_47.startsWith(TextCore.TextWave)) {
                                                                                    class94_18 = class94_18.substring(TextCore.TextWave.length());
                                                                                    byte4 = 1;
                                                                              } else if (class94_47.startsWith(TextCore.TextWaveTwo)) {
                                                                                    byte4 = 2;
                                                                                    class94_18 = class94_18.substring(TextCore.TextWaveTwo.length());
                                                                              } else if (class94_47.startsWith(TextCore.TextShake)) {
                                                                                    byte4 = 3;
                                                                                    class94_18 = class94_18.substring(TextCore.TextShake.length());
                                                                              } else if (class94_47.startsWith(TextCore.HasScroll)) {
                                                                                    byte4 = 4;
                                                                                    class94_18 = class94_18.substring(TextCore.HasScroll.length());
                                                                              } else if (class94_47.startsWith(TextCore.TextSlide)) {
                                                                                    class94_18 = class94_18.substring(TextCore.TextSlide.length());
                                                                                    byte4 = 5;
                                                                              }
                                                                           Class3_Sub13_Sub1.outgoingBuffer.putOpcode(237);
                                                                           Class3_Sub13_Sub1.outgoingBuffer.writeByte(0);
                                                                           int k79 = Class3_Sub13_Sub1.outgoingBuffer.index;
                                                                           Class3_Sub13_Sub1.outgoingBuffer.writeByte(byte3);
                                                                           Class3_Sub13_Sub1.outgoingBuffer.writeByte(byte4);
                                                                           Class85.method1423(Class3_Sub13_Sub1.outgoingBuffer, class94_18);
                                                                           Class3_Sub13_Sub1.outgoingBuffer.method769(-k79 + Class3_Sub13_Sub1.outgoingBuffer.index);
                                                                        }
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5009) {
                                                                        sStackCounter -= 2;
                                                                        RSString class94_48 = ItemDefinition.stringsStack[sStackCounter + 1];
                                                                        RSString class94_19 = ItemDefinition.stringsStack[sStackCounter];
                                                                        if (Class3_Sub13_Sub26.rights != 0 || (!Class3_Sub15.aBoolean2433 || Class121.aBoolean1641) && !Class3_Sub13_Sub14.aBoolean3166) {
                                                                           Class3_Sub13_Sub1.outgoingBuffer.putOpcode(201);
                                                                           Class3_Sub13_Sub1.outgoingBuffer.writeByte(0);
                                                                           int k69 = Class3_Sub13_Sub1.outgoingBuffer.index;
                                                                           Class3_Sub13_Sub1.outgoingBuffer.writeLong(class94_19.toLong());
                                                                           Class85.method1423(Class3_Sub13_Sub1.outgoingBuffer, class94_48);
                                                                           Class3_Sub13_Sub1.outgoingBuffer.method769(Class3_Sub13_Sub1.outgoingBuffer.index - k69);
                                                                        }
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5010) {
                                                                        int k24 = ItemDefinition.intsStack[--iStackCounter];
                                                                        RSString class94_49 = null;
                                                                        if (k24 < 100)
                                                                           class94_49 = Class3_Sub28_Sub12.aClass94Array3226[k24];
                                                                        if (null == class94_49)
                                                                           class94_49 = TextCore.aClass94_2331;
                                                                        ItemDefinition.stringsStack[sStackCounter++] = class94_49;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5011) {
                                                                        int l24 = ItemDefinition.intsStack[--iStackCounter];
                                                                        RSString class94_50 = null;
                                                                        if (l24 < 100)
                                                                           class94_50 = Class163_Sub3.aClass94Array3003[l24];
                                                                        if (class94_50 == null)
                                                                           class94_50 = TextCore.aClass94_2331;
                                                                        ItemDefinition.stringsStack[sStackCounter++] = class94_50;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5012) {
                                                                        int i25 = ItemDefinition.intsStack[--iStackCounter];
                                                                        int l55 = -1;
                                                                        if (i25 < 100)
                                                                           l55 = Class3_Sub28_Sub12.anIntArray1835[i25];
                                                                        ItemDefinition.intsStack[iStackCounter++] = l55;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5015) {
                                                                        RSString class94_20;
                                                                        if (Class102.player == null || null == Class102.player.displayName)
                                                                           class94_20 = Class131.username;
                                                                        else
                                                                           class94_20 = Class102.player.getName();
                                                                        ItemDefinition.stringsStack[sStackCounter++] = class94_20;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5016) {
                                                                        ItemDefinition.intsStack[iStackCounter++] = Class45.anInt734;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5017) {
                                                                        ItemDefinition.intsStack[iStackCounter++] = Class3_Sub13_Sub9.anInt3114;
                                                                        continue;
                                                                  }
                                                                  if (5050 == opcode) {
                                                                        int j25 = ItemDefinition.intsStack[--iStackCounter];
                                                                        ItemDefinition.stringsStack[sStackCounter++] = QuickChat.getQuickChatMessage(j25).quickChatMenu;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5051) {
                                                                        int k25 = ItemDefinition.intsStack[--iStackCounter];
                                                                        Class3_Sub28_Sub1 class3_sub28_sub1 = QuickChat.getQuickChatMessage(k25);
                                                                        if (class3_sub28_sub1.anIntArray3534 != null)
                                                                           ItemDefinition.intsStack[iStackCounter++] = class3_sub28_sub1.anIntArray3534.length;
                                                                        else
                                                                           ItemDefinition.intsStack[iStackCounter++] = 0;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5052) {
                                                                        iStackCounter -= 2;
                                                                        int l25 = ItemDefinition.intsStack[iStackCounter];
                                                                        int i56 = ItemDefinition.intsStack[iStackCounter - -1];
                                                                        Class3_Sub28_Sub1 class3_sub28_sub1_2 = QuickChat.getQuickChatMessage(l25);
                                                                        int j77 = class3_sub28_sub1_2.anIntArray3534[i56];
                                                                        ItemDefinition.intsStack[iStackCounter++] = j77;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5053) {
                                                                        int i26 = ItemDefinition.intsStack[--iStackCounter];
                                                                        Class3_Sub28_Sub1 class3_sub28_sub1_1 = QuickChat.getQuickChatMessage(i26);
                                                                        if (class3_sub28_sub1_1.anIntArray3540 != null)
                                                                           ItemDefinition.intsStack[iStackCounter++] = class3_sub28_sub1_1.anIntArray3540.length;
                                                                        else
                                                                           ItemDefinition.intsStack[iStackCounter++] = 0;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5054) {
                                                                        iStackCounter -= 2;
                                                                        int j56 = ItemDefinition.intsStack[1 + iStackCounter];
                                                                        int j26 = ItemDefinition.intsStack[iStackCounter];
                                                                        ItemDefinition.intsStack[iStackCounter++] = QuickChat.getQuickChatMessage(j26).anIntArray3540[j56];
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5055) {
                                                                        int k26 = ItemDefinition.intsStack[--iStackCounter];
                                                                        ItemDefinition.stringsStack[sStackCounter++] = LinkableRSString.method733(k26).method554();
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5056) {
                                                                        int l26 = ItemDefinition.intsStack[--iStackCounter];
                                                                        Class3_Sub28_Sub4 class3_sub28_sub4 = LinkableRSString.method733(l26);
                                                                        if (null != class3_sub28_sub4.anIntArray3567)
                                                                           ItemDefinition.intsStack[iStackCounter++] = class3_sub28_sub4.anIntArray3567.length;
                                                                        else
                                                                           ItemDefinition.intsStack[iStackCounter++] = 0;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5057) {
                                                                        iStackCounter -= 2;
                                                                        int k56 = ItemDefinition.intsStack[1 + iStackCounter];
                                                                        int i27 = ItemDefinition.intsStack[iStackCounter];
                                                                        ItemDefinition.intsStack[iStackCounter++] = LinkableRSString.method733(i27).anIntArray3567[k56];
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5058) {
                                                                        Class70.aClass10_1056 = new Class10();
                                                                        Class70.aClass10_1056.anInt149 = ItemDefinition.intsStack[--iStackCounter];
                                                                        Class70.aClass10_1056.aClass3_Sub28_Sub4_151 = LinkableRSString.method733(Class70.aClass10_1056.anInt149);
                                                                        Class70.aClass10_1056.anIntArray153 = new int[Class70.aClass10_1056.aClass3_Sub28_Sub4_151.method552()];
                                                                        continue;
                                                                  }
                                                                  if (5059 == opcode) {
                                                                        Class3_Sub13_Sub1.outgoingBuffer.putOpcode(167);
                                                                        Class3_Sub13_Sub1.outgoingBuffer.writeByte(0);
                                                                        int j27 = Class3_Sub13_Sub1.outgoingBuffer.index;
                                                                        Class3_Sub13_Sub1.outgoingBuffer.writeByte(0);
                                                                        Class3_Sub13_Sub1.outgoingBuffer.writeShort(Class70.aClass10_1056.anInt149);
                                                                        Class70.aClass10_1056.aClass3_Sub28_Sub4_151.method545(Class3_Sub13_Sub1.outgoingBuffer, Class70.aClass10_1056.anIntArray153);
                                                                        Class3_Sub13_Sub1.outgoingBuffer.method769(-j27 + Class3_Sub13_Sub1.outgoingBuffer.index);
                                                                        continue;
                                                                  }
                                                                  if (5060 == opcode) {
                                                                        RSString class94_21 = ItemDefinition.stringsStack[--sStackCounter];
                                                                        Class3_Sub13_Sub1.outgoingBuffer.putOpcode(178);
                                                                        Class3_Sub13_Sub1.outgoingBuffer.writeByte(0);
                                                                        int l56 = Class3_Sub13_Sub1.outgoingBuffer.index;
                                                                        Class3_Sub13_Sub1.outgoingBuffer.writeLong(class94_21.toLong());
                                                                        Class3_Sub13_Sub1.outgoingBuffer.writeShort(Class70.aClass10_1056.anInt149);
                                                                        Class70.aClass10_1056.aClass3_Sub28_Sub4_151.method545(Class3_Sub13_Sub1.outgoingBuffer, Class70.aClass10_1056.anIntArray153);
                                                                        Class3_Sub13_Sub1.outgoingBuffer.method769(Class3_Sub13_Sub1.outgoingBuffer.index + -l56);
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5061) {
                                                                        Class3_Sub13_Sub1.outgoingBuffer.putOpcode(167);
                                                                        Class3_Sub13_Sub1.outgoingBuffer.writeByte(0);
                                                                        int k27 = Class3_Sub13_Sub1.outgoingBuffer.index;
                                                                        Class3_Sub13_Sub1.outgoingBuffer.writeByte(1);
                                                                        Class3_Sub13_Sub1.outgoingBuffer.writeShort(Class70.aClass10_1056.anInt149);
                                                                        Class70.aClass10_1056.aClass3_Sub28_Sub4_151.method545(Class3_Sub13_Sub1.outgoingBuffer, Class70.aClass10_1056.anIntArray153);
                                                                        Class3_Sub13_Sub1.outgoingBuffer.method769(-k27 + Class3_Sub13_Sub1.outgoingBuffer.index);
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5062) {
                                                                        iStackCounter -= 2;
                                                                        int i57 = ItemDefinition.intsStack[1 + iStackCounter];
                                                                        int l27 = ItemDefinition.intsStack[iStackCounter];
                                                                        ItemDefinition.intsStack[iStackCounter++] = QuickChat.getQuickChatMessage(l27).anIntArray3535[i57];
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5063) {
                                                                        iStackCounter -= 2;
                                                                        int j57 = ItemDefinition.intsStack[iStackCounter - -1];
                                                                        int i28 = ItemDefinition.intsStack[iStackCounter];
                                                                        ItemDefinition.intsStack[iStackCounter++] = QuickChat.getQuickChatMessage(i28).anIntArray3533[j57];
                                                                        continue;
                                                                  }
                                                                  if (5064 == opcode) {
                                                                        iStackCounter -= 2;
                                                                        int k57 = ItemDefinition.intsStack[1 + iStackCounter];
                                                                        int j28 = ItemDefinition.intsStack[iStackCounter];
                                                                        if (k57 != -1)
                                                                           ItemDefinition.intsStack[iStackCounter++] = QuickChat.getQuickChatMessage(j28).method529(k57);
                                                                        else
                                                                           ItemDefinition.intsStack[iStackCounter++] = -1;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5065) {
                                                                        iStackCounter -= 2;
                                                                        int k28 = ItemDefinition.intsStack[iStackCounter];
                                                                        int l57 = ItemDefinition.intsStack[iStackCounter + 1];
                                                                        if (l57 != -1)
                                                                           ItemDefinition.intsStack[iStackCounter++] = QuickChat.getQuickChatMessage(k28).method526(l57);
                                                                        else
                                                                           ItemDefinition.intsStack[iStackCounter++] = -1;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5066) {
                                                                        int l28 = ItemDefinition.intsStack[--iStackCounter];
                                                                        ItemDefinition.intsStack[iStackCounter++] = LinkableRSString.method733(l28).method552();
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5067) {
                                                                        iStackCounter -= 2;
                                                                        int i58 = ItemDefinition.intsStack[iStackCounter + 1];
                                                                        int i29 = ItemDefinition.intsStack[iStackCounter];
                                                                        int i70 = LinkableRSString.method733(i29).method550(49, i58);
                                                                        ItemDefinition.intsStack[iStackCounter++] = i70;
                                                                        continue;
                                                                  }
                                                                  if (5068 == opcode) {
                                                                        iStackCounter -= 2;
                                                                        int j29 = ItemDefinition.intsStack[iStackCounter];
                                                                        int j58 = ItemDefinition.intsStack[1 + iStackCounter];
                                                                        Class70.aClass10_1056.anIntArray153[j29] = j58;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5069) {
                                                                        iStackCounter -= 2;
                                                                        int k29 = ItemDefinition.intsStack[iStackCounter];
                                                                        int k58 = ItemDefinition.intsStack[iStackCounter + 1];
                                                                        Class70.aClass10_1056.anIntArray153[k29] = k58;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5070) {
                                                                        iStackCounter -= 3;
                                                                        int l29 = ItemDefinition.intsStack[iStackCounter];
                                                                        int j70 = ItemDefinition.intsStack[iStackCounter - -2];
                                                                        int l58 = ItemDefinition.intsStack[iStackCounter + 1];
                                                                        Class3_Sub28_Sub4 class3_sub28_sub4_1 = LinkableRSString.method733(l29);
                                                                        if (0 != class3_sub28_sub4_1.method550(73, l58))
                                                                           throw new RuntimeException("bad command");
                                                                        ItemDefinition.intsStack[iStackCounter++] = class3_sub28_sub4_1.method549(j70, l58);
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5071) {
                                                                        RSString class94_22 = ItemDefinition.stringsStack[--sStackCounter];
                                                                        boolean flag4 = 1 == ItemDefinition.intsStack[--iStackCounter];
                                                                        Class3_Sub28_Sub3.method541(flag4, class94_22);
                                                                        ItemDefinition.intsStack[iStackCounter++] = Unsorted.anInt952;
                                                                        continue;
                                                                  }
                                                                  if (5072 == opcode) {
                                                                        if (Class99.aShortArray1398 == null || Unsorted.anInt952 <= Class140_Sub4.anInt2756)
                                                                           ItemDefinition.intsStack[iStackCounter++] = -1;
                                                                        else
                                                                           ItemDefinition.intsStack[iStackCounter++] = Unsorted.bitwiseAnd(Class99.aShortArray1398[Class140_Sub4.anInt2756++], 65535);
                                                                        continue;
                                                                  }
                                                                  if (opcode != 5073)
                                                                        break;
                                                                  Class140_Sub4.anInt2756 = 0;
                                                                  continue;
                                                               }
                                                               if (5200 > opcode) {
                                                                  if (5100 == opcode) {
                                                                        if (!ObjectDefinition.aBooleanArray1490[86])
                                                                           ItemDefinition.intsStack[iStackCounter++] = 0;
                                                                        else
                                                                           ItemDefinition.intsStack[iStackCounter++] = 1;
                                                                        continue;
                                                                  }
                                                                  if (5101 == opcode) {
                                                                        if (ObjectDefinition.aBooleanArray1490[82])
                                                                           ItemDefinition.intsStack[iStackCounter++] = 1;
                                                                        else
                                                                           ItemDefinition.intsStack[iStackCounter++] = 0;
                                                                        continue;
                                                                  }
                                                                  if (5102 != opcode)
                                                                        break;
                                                                  if (ObjectDefinition.aBooleanArray1490[81])
                                                                        ItemDefinition.intsStack[iStackCounter++] = 1;
                                                                  else
                                                                        ItemDefinition.intsStack[iStackCounter++] = 0;
                                                                  continue;
                                                               }
                                                               if (opcode < 5300) {
                                                                  if (opcode == 5200) {
                                                                        NPCDefinition.method1479(ItemDefinition.intsStack[--iStackCounter]);
                                                                        continue;
                                                                  }
                                                                  if (5201 == opcode) {
                                                                        ItemDefinition.intsStack[iStackCounter++] = Class3_Sub28_Sub8.method571();
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5202) {
                                                                        Class3_Sub24_Sub4.method503(ItemDefinition.intsStack[--iStackCounter]);
                                                                        continue;
                                                                  }
                                                                  if (5203 == opcode) {
                                                                        Unsorted.method84(ItemDefinition.stringsStack[--sStackCounter], -801);
                                                                        continue;
                                                                  }
                                                                  if (5204 == opcode) {
                                                                        ItemDefinition.stringsStack[sStackCounter - 1] = GameShell.method27(ItemDefinition.stringsStack[sStackCounter - 1]);
                                                                        continue;
                                                                  }
                                                                  if (5205 == opcode) {
                                                                        Class3_Sub10.method138(ItemDefinition.stringsStack[--sStackCounter]);
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5206) {
                                                                        int i30 = ItemDefinition.intsStack[--iStackCounter];
                                                                        Class3_Sub28_Sub3 class3_sub28_sub3_4 = Unsorted.method884(0x3fff & i30 >> 14, (byte) 111, 0x3fff & i30);
                                                                        if (class3_sub28_sub3_4 != null)
                                                                           ItemDefinition.stringsStack[sStackCounter++] = class3_sub28_sub3_4.aClass94_3561;
                                                                        else
                                                                           ItemDefinition.stringsStack[sStackCounter++] = TextCore.aClass94_2331;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5207) {
                                                                        Class3_Sub28_Sub3 class3_sub28_sub3 = Class3_Sub15.method371(ItemDefinition.stringsStack[--sStackCounter]);
                                                                        if (null != class3_sub28_sub3 && class3_sub28_sub3.aClass94_3554 != null)
                                                                           ItemDefinition.stringsStack[sStackCounter++] = class3_sub28_sub3.aClass94_3554;
                                                                        else
                                                                           ItemDefinition.stringsStack[sStackCounter++] = TextCore.aClass94_2331;
                                                                        continue;
                                                                  }
                                                                  if (5208 == opcode) {
                                                                        ItemDefinition.intsStack[iStackCounter++] = Class49.anInt817;
                                                                        ItemDefinition.intsStack[iStackCounter++] = Class17.anInt410;
                                                                        continue;
                                                                  }
                                                                  if (5209 == opcode) {
                                                                        ItemDefinition.intsStack[iStackCounter++] = Class3_Sub13_Sub21.anInt3256 + Class3_Sub28_Sub1.anInt3536;
                                                                        ItemDefinition.intsStack[iStackCounter++] = Unsorted.anInt65 + -Class3_Sub4.anInt2251 + (-1 + Class108.anInt1460);
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5210) {
                                                                        Class3_Sub28_Sub3 class3_sub28_sub3_1 = Unsorted.method520((byte) -82);
                                                                        if (class3_sub28_sub3_1 == null) {
                                                                           ItemDefinition.intsStack[iStackCounter++] = 0;
                                                                           ItemDefinition.intsStack[iStackCounter++] = 0;
                                                                        } else {
                                                                           ItemDefinition.intsStack[iStackCounter++] = class3_sub28_sub3_1.anInt3558 * 64;
                                                                           ItemDefinition.intsStack[iStackCounter++] = 64 * class3_sub28_sub3_1.anInt3556;
                                                                        }
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5211) {
                                                                        Class3_Sub28_Sub3 class3_sub28_sub3_2 = Unsorted.method520((byte) -121);
                                                                        if (class3_sub28_sub3_2 == null) {
                                                                           ItemDefinition.intsStack[iStackCounter++] = 0;
                                                                           ItemDefinition.intsStack[iStackCounter++] = 0;
                                                                        } else {
                                                                           ItemDefinition.intsStack[iStackCounter++] = class3_sub28_sub3_2.anInt3559 - class3_sub28_sub3_2.anInt3555;
                                                                           ItemDefinition.intsStack[iStackCounter++] = -class3_sub28_sub3_2.anInt3562 + class3_sub28_sub3_2.anInt3549;
                                                                        }
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5212) {
                                                                        int j30 = Class67.method1258((byte) -53);
                                                                        int k70 = 0;
                                                                        RSString class94_51;
                                                                        if (j30 == -1) {
                                                                           class94_51 = TextCore.aClass94_2331;
                                                                        } else {
                                                                           class94_51 = Class119.aClass131_1624.aClass94Array1721[j30];
                                                                           k70 = Class119.aClass131_1624.method1791(j30, 8);
                                                                        }
                                                                        class94_51 = class94_51.method1560(TextCore.aClass94_2765, TextCore.aClass94_2168);
                                                                        ItemDefinition.stringsStack[sStackCounter++] = class94_51;
                                                                        ItemDefinition.intsStack[iStackCounter++] = k70;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5213) {
                                                                        int i71 = 0;
                                                                        int k30 = Class3_Sub13_Sub17.method251();
                                                                        RSString class94_52;
                                                                        if (k30 == -1) {
                                                                           class94_52 = TextCore.aClass94_2331;
                                                                        } else {
                                                                           class94_52 = Class119.aClass131_1624.aClass94Array1721[k30];
                                                                           i71 = Class119.aClass131_1624.method1791(k30, 8);
                                                                        }
                                                                        class94_52 = class94_52.method1560(TextCore.aClass94_2765, TextCore.aClass94_2168);
                                                                        ItemDefinition.stringsStack[sStackCounter++] = class94_52;
                                                                        ItemDefinition.intsStack[iStackCounter++] = i71;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5214) {
                                                                        int l30 = ItemDefinition.intsStack[--iStackCounter];
                                                                        Unsorted.method565(0x3fff & l30 >> 14, 0x3fff & l30);
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5215) {
                                                                        int i31 = ItemDefinition.intsStack[--iStackCounter];
                                                                        RSString class94_53 = ItemDefinition.stringsStack[--sStackCounter];
                                                                        boolean flag10 = false;
                                                                        Queue class13 = method1664(0x3fff & i31 >> 14, 0x3fff & i31);
                                                                        Class3_Sub28_Sub3 class3_sub28_sub3_5 = (Class3_Sub28_Sub3) class13.getFront();
                                                                        do {
                                                                           if (class3_sub28_sub3_5 == null)
                                                                              break;
                                                                           if (class3_sub28_sub3_5.aClass94_3561.equalsStringIgnoreCase(class94_53)) {
                                                                              flag10 = true;
                                                                              break;
                                                                           }
                                                                           class3_sub28_sub3_5 = (Class3_Sub28_Sub3) class13.next();
                                                                        } while (true);
                                                                        if (!flag10)
                                                                           ItemDefinition.intsStack[iStackCounter++] = 0;
                                                                        else
                                                                           ItemDefinition.intsStack[iStackCounter++] = 1;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5216) {
                                                                        int j31 = ItemDefinition.intsStack[--iStackCounter];
                                                                        Class3_Sub13_Sub36.method344(j31, 4);
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5217) {
                                                                        int k31 = ItemDefinition.intsStack[--iStackCounter];
                                                                        if (!Class3_Sub10.method140(k31))
                                                                           ItemDefinition.intsStack[iStackCounter++] = 0;
                                                                        else
                                                                           ItemDefinition.intsStack[iStackCounter++] = 1;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5218) {
                                                                        Class3_Sub28_Sub3 class3_sub28_sub3_3 = Unsorted.method520((byte) -124);
                                                                        if (null != class3_sub28_sub3_3)
                                                                           ItemDefinition.intsStack[iStackCounter++] = class3_sub28_sub3_3.anInt3563;
                                                                        else
                                                                           ItemDefinition.intsStack[iStackCounter++] = -1;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5219) {
                                                                        Class21.method915(ItemDefinition.stringsStack[--sStackCounter]);
                                                                        continue;
                                                                  }
                                                                  if (opcode != 5220)
                                                                        break;
                                                                  ItemDefinition.intsStack[iStackCounter++] = WorldMap.anInt2737 != 100 ? 0 : 1;
                                                                  continue;
                                                               }
                                                               if (opcode < 5400) {
                                                                  if (opcode == 5300) {
                                                                        iStackCounter -= 2;
                                                                        int i59 = ItemDefinition.intsStack[1 + iStackCounter];
                                                                        int l31 = ItemDefinition.intsStack[iStackCounter];
                                                                        GameObject.graphicsSettings(false, 3, l31, i59);
                                                                        ItemDefinition.intsStack[iStackCounter++] = null != Class3_Sub13_Sub10.aFrame3121 ? 1 : 0;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5301) {
                                                                        if (null != Class3_Sub13_Sub10.aFrame3121)
                                                                           GameObject.graphicsSettings(false, Unsorted.anInt2577, -1, -1);
                                                                        continue;
                                                                  }
                                                                  if (5302 == opcode) {
                                                                        Class106[] aclass106 = Unsorted.method88();
                                                                        ItemDefinition.intsStack[iStackCounter++] = aclass106.length;
                                                                        continue;
                                                                  }
                                                                  if (5303 == opcode) {
                                                                        int i32 = ItemDefinition.intsStack[--iStackCounter];
                                                                        Class106[] aclass106_1 = Unsorted.method88();
                                                                        ItemDefinition.intsStack[iStackCounter++] = aclass106_1[i32].anInt1447;
                                                                        ItemDefinition.intsStack[iStackCounter++] = aclass106_1[i32].anInt1449;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5305) {
                                                                        int j59 = Unsorted.anInt3071;
                                                                        int j32 = Class3_Sub13.anInt2378;
                                                                        int j71 = -1;
                                                                        Class106[] aclass106_2 = Unsorted.method88();
                                                                        int i80 = 0;
                                                                        do {
                                                                           if (aclass106_2.length <= i80)
                                                                              break;
                                                                           Class106 class106 = aclass106_2[i80];
                                                                           if (j32 == class106.anInt1447 && class106.anInt1449 == j59) {
                                                                              j71 = i80;
                                                                              break;
                                                                           }
                                                                           i80++;
                                                                        } while (true);
                                                                        ItemDefinition.intsStack[iStackCounter++] = j71;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5306) {
                                                                        ItemDefinition.intsStack[iStackCounter++] = Class83.method1411(0);
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5307) {
                                                                        int k32 = ItemDefinition.intsStack[--iStackCounter];
                                                                        if (k32 < 0 || k32 > 2)
                                                                           k32 = 0;
                                                                        GameObject.graphicsSettings(false, k32, -1, -1);
                                                                        continue;
                                                                  }
                                                                  if (5308 == opcode) {
                                                                        ItemDefinition.intsStack[iStackCounter++] = Unsorted.anInt2577;
                                                                        continue;
                                                                  }
                                                                  if (5309 != opcode)
                                                                        break;
                                                                  int l32 = ItemDefinition.intsStack[--iStackCounter];
                                                                  if (l32 < 0 || l32 > 2)
                                                                        l32 = 0;
                                                                  Unsorted.anInt2577 = l32;
                                                                  Class119.method1730(Class38.aClass87_665);
                                                                  continue;
                                                               }
                                                               if (5500 > opcode) {
                                                                  if (opcode == 5400) {
                                                                        sStackCounter -= 2;
                                                                        RSString class94_23 = ItemDefinition.stringsStack[sStackCounter];
                                                                        RSString class94_54 = ItemDefinition.stringsStack[sStackCounter - -1];
                                                                        int k71 = ItemDefinition.intsStack[--iStackCounter];
                                                                        Class3_Sub13_Sub1.outgoingBuffer.putOpcode(117);
                                                                        Class3_Sub13_Sub1.outgoingBuffer.writeByte(Class3_Sub13_Sub33.method326((byte) 39, class94_23) - (-Class3_Sub13_Sub33.method326((byte) 102, class94_54) + -1));
                                                                        Class3_Sub13_Sub1.outgoingBuffer.writeString(class94_23);
                                                                        Class3_Sub13_Sub1.outgoingBuffer.writeString(class94_54);
                                                                        Class3_Sub13_Sub1.outgoingBuffer.writeByte(k71);
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5401) {
                                                                        iStackCounter -= 2;
                                                                        Class3_Sub13_Sub38.aShortArray3455[ItemDefinition.intsStack[iStackCounter]] = (short) Class56.method1186(ItemDefinition.intsStack[iStackCounter + 1]);
                                                                        GameShell.method28();
                                                                        Unsorted.method746((byte) -29);
                                                                        Class167.method2265();
                                                                        WorldListEntry.method1076();
                                                                        Unsorted.method1093(false);
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5405) {
                                                                        iStackCounter -= 2;
                                                                        int i33 = ItemDefinition.intsStack[iStackCounter];
                                                                        int k59 = ItemDefinition.intsStack[1 + iStackCounter];
                                                                        if (i33 >= 0 && i33 < 2)
                                                                           Class58.anIntArrayArrayArray911[i33] = new int[k59 << 1][4];
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5406) {
                                                                        iStackCounter -= 7;
                                                                        int j33 = ItemDefinition.intsStack[iStackCounter];
                                                                        int l59 = ItemDefinition.intsStack[1 + iStackCounter] << 1;
                                                                        int k77 = ItemDefinition.intsStack[iStackCounter - -3];
                                                                        int l71 = ItemDefinition.intsStack[2 + iStackCounter];
                                                                        int j80 = ItemDefinition.intsStack[4 + iStackCounter];
                                                                        int j82 = ItemDefinition.intsStack[6 + iStackCounter];
                                                                        int l81 = ItemDefinition.intsStack[5 + iStackCounter];
                                                                        if (j33 >= 0 && j33 < 2 && null != Class58.anIntArrayArrayArray911[j33] && l59 >= 0 && Class58.anIntArrayArrayArray911[j33].length > l59) {
                                                                           Class58.anIntArrayArrayArray911[j33][l59] = (new int[]{
                                                                                    (Unsorted.bitwiseAnd(0xfffc3b9, l71) >> 14) * 128, k77, 128 * Unsorted.bitwiseAnd(l71, 16383), j82
                                                                           });
                                                                           Class58.anIntArrayArrayArray911[j33][l59 + 1] = (new int[]{
                                                                                    128 * (Unsorted.bitwiseAnd(j80, 0xfffed27) >> 14), l81, 128 * Unsorted.bitwiseAnd(j80, 16383)
                                                                           });
                                                                        }
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5407) {
                                                                        int k33 = Class58.anIntArrayArrayArray911[ItemDefinition.intsStack[--iStackCounter]].length >> 1;
                                                                        ItemDefinition.intsStack[iStackCounter++] = k33;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5411) {
                                                                        if (Class3_Sub13_Sub10.aFrame3121 != null)
                                                                           GameObject.graphicsSettings(false, Unsorted.anInt2577, -1, -1);
                                                                        if (null == GameShell.frame)
                                                                           Class99.method1596(RSInterface.method856(), (byte) 126, false);
                                                                        else
                                                                           System.exit(0);
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5419) {
                                                                        RSString class94_24 = TextCore.aClass94_2331;
                                                                        if (null != Class136.aClass64_1778) {
                                                                           class94_24 = Class108.method1653(Class136.aClass64_1778.anInt979);
                                                                           if (Class136.aClass64_1778.anObject974 != null) {
                                                                              byte[] abyte0 = null;
                                                                              abyte0 = ((String) Class136.aClass64_1778.anObject974).getBytes(StandardCharsets.ISO_8859_1);
                                                                              class94_24 = Class3_Sub13_Sub3.method178(abyte0, abyte0.length, 0);
                                                                           }
                                                                        }
                                                                        ItemDefinition.stringsStack[sStackCounter++] = class94_24;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5420) {
                                                                        ItemDefinition.intsStack[iStackCounter++] = Signlink.anInt1214 != 3 ? 0 : 1;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5421) {
                                                                        if (null != Class3_Sub13_Sub10.aFrame3121)
                                                                           GameObject.graphicsSettings(false, Unsorted.anInt2577, -1, -1);
                                                                        boolean flag5 = 1 == ItemDefinition.intsStack[--iStackCounter];
                                                                        RSString class94_25 = ItemDefinition.stringsStack[--sStackCounter];
                                                                        RSString class94_64 = RSString.stringCombiner(new RSString[]{
                                                                              RSInterface.method856(), class94_25
                                                                        });
                                                                        if (null == GameShell.frame && (!flag5 || Signlink.anInt1214 == 3 || !Signlink.osName.startsWith("win") || Class106.hasInternetExplorer6)) {
                                                                           Class99.method1596(class94_64, (byte) 127, flag5);
                                                                        } else {
                                                                           Unsorted.aBoolean2154 = flag5;
                                                                           Class3_Sub13_Sub24.aClass94_3295 = class94_64;
                                                                           Class15.aClass64_351 = Class38.aClass87_665.method1452(new String(class94_64.method1568(), StandardCharsets.ISO_8859_1), true);
                                                                        }
                                                                        continue;
                                                                  }
                                                                  if (5422 == opcode) {
                                                                        int i72 = ItemDefinition.intsStack[--iStackCounter];
                                                                        sStackCounter -= 2;
                                                                        RSString class94_55 = ItemDefinition.stringsStack[1 + sStackCounter];
                                                                        RSString class94_26 = ItemDefinition.stringsStack[sStackCounter];
                                                                        if (class94_26.length() > 0) {
                                                                           if (null == Class3_Sub30_Sub1.aClass94Array3802)
                                                                              Class3_Sub30_Sub1.aClass94Array3802 = new RSString[Class3_Sub13_Sub18.anIntArray3218[Class158.anInt2014]];
                                                                           Class3_Sub30_Sub1.aClass94Array3802[i72] = class94_26;
                                                                        }
                                                                        if (class94_55.length() > 0) {
                                                                           if (Unsorted.aClass94Array45 == null)
                                                                              Unsorted.aClass94Array45 = new RSString[Class3_Sub13_Sub18.anIntArray3218[Class158.anInt2014]];
                                                                           Unsorted.aClass94Array45[i72] = class94_55;
                                                                        }
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5423) {
                                                                        continue;
                                                                  }
                                                                  if (5424 == opcode) {
                                                                        iStackCounter -= 11;
                                                                        Class3_Sub28_Sub6.anInt3600 = ItemDefinition.intsStack[iStackCounter];
                                                                        Unsorted.anInt963 = ItemDefinition.intsStack[iStackCounter - -1];
                                                                        MouseListeningClass.anInt1926 = ItemDefinition.intsStack[iStackCounter + 2];
                                                                        Class136.anInt1771 = ItemDefinition.intsStack[3 + iStackCounter];
                                                                        WorldListEntry.archiveID = ItemDefinition.intsStack[4 + iStackCounter];
                                                                        WorldListEntry.anInt1400 = ItemDefinition.intsStack[5 + iStackCounter];
                                                                        WorldListEntry.anInt739 = ItemDefinition.intsStack[6 + iStackCounter];
                                                                        WorldListEntry.anInt1126 = ItemDefinition.intsStack[7 + iStackCounter];
                                                                        WorldListEntry.anInt2937 = ItemDefinition.intsStack[8 + iStackCounter];
                                                                        WorldListEntry.anInt3351 = ItemDefinition.intsStack[iStackCounter + 9];
                                                                        Class154.anInt1957 = ItemDefinition.intsStack[10 + iStackCounter];
                                                                        CacheIndex.spritesIndex.retrieveSpriteFile(WorldListEntry.archiveID);
                                                                        CacheIndex.spritesIndex.retrieveSpriteFile(WorldListEntry.anInt1400);
                                                                        CacheIndex.spritesIndex.retrieveSpriteFile(WorldListEntry.anInt739);
                                                                        CacheIndex.spritesIndex.retrieveSpriteFile(WorldListEntry.anInt1126);
                                                                        CacheIndex.spritesIndex.retrieveSpriteFile(WorldListEntry.anInt2937);
                                                                        Unsorted.aBoolean1951 = true;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5425) {
                                                                        Class3_Sub13.method165();
                                                                        Unsorted.aBoolean1951 = false;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5426) {
                                                                        Class161.anInt2027 = ItemDefinition.intsStack[--iStackCounter];
                                                                        continue;
                                                                  }
                                                                  if (opcode != 5427)
                                                                        break;
                                                                  iStackCounter -= 2;
                                                                  Class99.anInt1403 = ItemDefinition.intsStack[iStackCounter];
                                                                  Class131.anInt1719 = ItemDefinition.intsStack[iStackCounter + 1];
                                                                  continue;
                                                               }
                                                               if (5600 > opcode) {
                                                                  if (5500 == opcode) {
                                                                        iStackCounter -= 4;
                                                                        int l33 = ItemDefinition.intsStack[iStackCounter];
                                                                        int l77 = ItemDefinition.intsStack[iStackCounter - -3];
                                                                        int j72 = ItemDefinition.intsStack[iStackCounter - -2];
                                                                        int i60 = ItemDefinition.intsStack[iStackCounter + 1];
                                                                        Class3_Sub20.method390(false, j72, i60, l77, (byte) -128, -Class82.anInt1152 + (0x3fff & l33), ((0xffffe30 & l33) >> 14) - Class131.anInt1716);
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5501) {
                                                                        iStackCounter -= 4;
                                                                        int j60 = ItemDefinition.intsStack[1 + iStackCounter];
                                                                        int i34 = ItemDefinition.intsStack[iStackCounter];
                                                                        int i78 = ItemDefinition.intsStack[iStackCounter - -3];
                                                                        int k72 = ItemDefinition.intsStack[iStackCounter + 2];
                                                                        Class164_Sub1.method2238(j60, (0x3fff & i34) - Class82.anInt1152, k72, -Class131.anInt1716 + ((0xffff221 & i34) >> 14), i78);
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5502) {
                                                                        iStackCounter -= 6;
                                                                        int j34 = ItemDefinition.intsStack[iStackCounter];
                                                                        if (j34 >= 2)
                                                                           throw new RuntimeException();
                                                                        NPCDefinition.anInt1252 = j34;
                                                                        int k60 = ItemDefinition.intsStack[iStackCounter - -1];
                                                                        if (1 + k60 >= Class58.anIntArrayArrayArray911[NPCDefinition.anInt1252].length >> 1)
                                                                           throw new RuntimeException();
                                                                        Unsorted.anInt1081 = k60;
                                                                        Class163_Sub2_Sub1.anInt4020 = 0;
                                                                        Class134.anInt1759 = ItemDefinition.intsStack[iStackCounter + 2];
                                                                        Class3_Sub13.anInt2383 = ItemDefinition.intsStack[iStackCounter + 3];
                                                                        int l72 = ItemDefinition.intsStack[iStackCounter + 4];
                                                                        if (2 <= l72)
                                                                           throw new RuntimeException();
                                                                        Class3_Sub28_Sub6.anInt2293 = l72;
                                                                        int j78 = ItemDefinition.intsStack[5 + iStackCounter];
                                                                        if (Class58.anIntArrayArrayArray911[Class3_Sub28_Sub6.anInt2293].length >> 1 <= 1 + j78)
                                                                           throw new RuntimeException();
                                                                        Class39.anInt670 = j78;
                                                                        Class133.anInt1753 = 3;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5503) {
                                                                        Class3_Sub28_Sub5.method560();
                                                                        continue;
                                                                  }
                                                                  if (5504 == opcode) {
                                                                        iStackCounter -= 2;
                                                                        Unsorted.anInt2309 = ItemDefinition.intsStack[iStackCounter];
                                                                        GraphicDefinition.CAMERA_DIRECTION = ItemDefinition.intsStack[iStackCounter + 1];
                                                                        if (Class133.anInt1753 == 2) {
                                                                           Class3_Sub13_Sub25.anInt3315 = GraphicDefinition.CAMERA_DIRECTION;
                                                                           Class139.anInt1823 = Unsorted.anInt2309;
                                                                        }
                                                                        Unsorted.method1098((byte) -74);
                                                                        continue;
                                                                  }
                                                                  if (opcode == 5505) {
                                                                        ItemDefinition.intsStack[iStackCounter++] = Unsorted.anInt2309;
                                                                        continue;
                                                                  }
                                                                  if (5506 != opcode)
                                                                        break;
                                                                  ItemDefinition.intsStack[iStackCounter++] = GraphicDefinition.CAMERA_DIRECTION;
                                                                  continue;
                                                               }
                                                               if (opcode >= 5700) {
                                                                  if (6100 > opcode) {
                                                                        if (opcode == 6001) {
                                                                           int k34 = ItemDefinition.intsStack[--iStackCounter];
                                                                           if (k34 < 1)
                                                                              k34 = 1;
                                                                           if (k34 > 4)
                                                                              k34 = 4;
                                                                           Unsorted.anInt3625 = k34;
                                                                           if (!HDToolKit.highDetail || !Class106.aBoolean1441) {
                                                                              if (Unsorted.anInt3625 == 1)
                                                                                    Class51.method1137(0.9F);
                                                                              if (Unsorted.anInt3625 == 2)
                                                                                    Class51.method1137(0.8F);
                                                                              if (3 == Unsorted.anInt3625)
                                                                                    Class51.method1137(0.7F);
                                                                              if (Unsorted.anInt3625 == 4)
                                                                                    Class51.method1137(0.6F);
                                                                           }
                                                                           if (HDToolKit.highDetail) {
                                                                              Class3_Sub13_Sub14.method236();
                                                                              if (!Class106.aBoolean1441)
                                                                                    Class84.method1417();
                                                                           }
                                                                           Unsorted.method746((byte) -29);
                                                                           Class119.method1730(Class38.aClass87_665);
                                                                           aBoolean2705 = false;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6002) {
                                                                           Class25.method957(1 == ItemDefinition.intsStack[--iStackCounter]);
                                                                           Class3_Sub10.method139(66);
                                                                           Class84.method1417();
                                                                           Unsorted.method792();
                                                                           Class119.method1730(Class38.aClass87_665);
                                                                           aBoolean2705 = false;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6003) {
                                                                           Unsorted.aBoolean3604 = ItemDefinition.intsStack[--iStackCounter] == 1;
                                                                           Unsorted.method792();
                                                                           Class119.method1730(Class38.aClass87_665);
                                                                           aBoolean2705 = false;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6005) {
                                                                           KeyboardListener.aBoolean1905 = ItemDefinition.intsStack[--iStackCounter] == 1;
                                                                           Class84.method1417();
                                                                           Class119.method1730(Class38.aClass87_665);
                                                                           aBoolean2705 = false;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6006) {
                                                                           Class25.aBoolean488 = ItemDefinition.intsStack[--iStackCounter] == 1;
                                                                           ((Class102) Class51.anInterface2_838).method1616(!Class25.aBoolean488);
                                                                           Class119.method1730(Class38.aClass87_665);
                                                                           aBoolean2705 = false;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6007) {
                                                                           RSInterface.aBoolean236 = ItemDefinition.intsStack[--iStackCounter] == 1;
                                                                           Class119.method1730(Class38.aClass87_665);
                                                                           aBoolean2705 = false;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6008) {
                                                                           WorldListEntry.aBoolean2623 = ItemDefinition.intsStack[--iStackCounter] == 1;
                                                                           Class119.method1730(Class38.aClass87_665);
                                                                           aBoolean2705 = false;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6009) {
                                                                           Class3_Sub13_Sub22.aBoolean3275 = ItemDefinition.intsStack[--iStackCounter] == 1;
                                                                           Class119.method1730(Class38.aClass87_665);
                                                                           aBoolean2705 = false;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6010) {
                                                                           Class140_Sub6.aBoolean2910 = 1 == ItemDefinition.intsStack[--iStackCounter];
                                                                           Class119.method1730(Class38.aClass87_665);
                                                                           aBoolean2705 = false;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6011) {
                                                                           int l34 = ItemDefinition.intsStack[--iStackCounter];
                                                                           if (l34 < 0 || l34 > 2)
                                                                              l34 = 0;
                                                                           Unsorted.anInt1137 = l34;
                                                                           Class119.method1730(Class38.aClass87_665);
                                                                           aBoolean2705 = false;
                                                                           continue;
                                                                        }
                                                                        if (6012 == opcode) {
                                                                           if (HDToolKit.highDetail)
                                                                              Class3_Sub28_Sub4.method551(0, 0);
                                                                           Class106.aBoolean1441 = ItemDefinition.intsStack[--iStackCounter] == 1;
                                                                           if (HDToolKit.highDetail && Class106.aBoolean1441) {
                                                                              Class51.method1137(0.7F);
                                                                           } else {
                                                                              if (Unsorted.anInt3625 == 1)
                                                                                    Class51.method1137(0.9F);
                                                                              if (Unsorted.anInt3625 == 2)
                                                                                    Class51.method1137(0.8F);
                                                                              if (Unsorted.anInt3625 == 3)
                                                                                    Class51.method1137(0.7F);
                                                                              if (Unsorted.anInt3625 == 4)
                                                                                    Class51.method1137(0.6F);
                                                                           }
                                                                           Class84.method1417();
                                                                           Class119.method1730(Class38.aClass87_665);
                                                                           aBoolean2705 = false;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6014) {
                                                                           Class128.aBoolean1685 = ItemDefinition.intsStack[--iStackCounter] == 1;
                                                                           if (HDToolKit.highDetail)
                                                                              Class84.method1417();
                                                                           Class119.method1730(Class38.aClass87_665);
                                                                           aBoolean2705 = false;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6015) {
                                                                           Class38.aBoolean661 = ItemDefinition.intsStack[--iStackCounter] == 1;
                                                                           if (HDToolKit.highDetail)
                                                                              Class3_Sub13_Sub14.method236();
                                                                           Class119.method1730(Class38.aClass87_665);
                                                                           aBoolean2705 = false;
                                                                           continue;
                                                                        }
                                                                        if (6016 == opcode) {
                                                                           int i35 = ItemDefinition.intsStack[--iStackCounter];
                                                                           if (HDToolKit.highDetail)
                                                                              Class3_Sub28_Sub5.forceReplaceCanvasEnable = true;
                                                                           if (0 > i35 || i35 > 2)
                                                                              i35 = 0;
                                                                           Unsorted.anInt3671 = i35;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6017) {
                                                                           Class3_Sub13_Sub15.aBoolean3184 = ItemDefinition.intsStack[--iStackCounter] == 1;
                                                                           GameShell.method34();
                                                                           Class119.method1730(Class38.aClass87_665);
                                                                           aBoolean2705 = false;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6018) {
                                                                           int j35 = ItemDefinition.intsStack[--iStackCounter];
                                                                           if (j35 < 0)
                                                                              j35 = 0;
                                                                           if (j35 > 127)
                                                                              j35 = 127;
                                                                           anInt2453 = j35;
                                                                           Class119.method1730(Class38.aClass87_665);
                                                                           aBoolean2705 = false;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6019) {
                                                                           int k35 = ItemDefinition.intsStack[--iStackCounter];
                                                                           if (k35 < 0)
                                                                              k35 = 0;
                                                                           if (k35 > 255)
                                                                              k35 = 255;
                                                                           if (Unsorted.anInt120 != k35) {
                                                                              if (Unsorted.anInt120 == 0 && Class129.anInt1691 != -1) {
                                                                                    Class70.method1285(CacheIndex.musicIndex, Class129.anInt1691, k35);
                                                                                    Class83.aBoolean1158 = false;
                                                                              } else if (k35 == 0) {
                                                                                    GameObject.method1870();
                                                                                    Class83.aBoolean1158 = false;
                                                                              } else {
                                                                                    LinkableRSString.method736(k35, 115);
                                                                              }
                                                                              Unsorted.anInt120 = k35;
                                                                           }
                                                                           Class119.method1730(Class38.aClass87_665);
                                                                           aBoolean2705 = false;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6020) {
                                                                           int l35 = ItemDefinition.intsStack[--iStackCounter];
                                                                           if (l35 < 0)
                                                                              l35 = 0;
                                                                           if (127 < l35)
                                                                              l35 = 127;
                                                                           Sprites.anInt340 = l35;
                                                                           Class119.method1730(Class38.aClass87_665);
                                                                           aBoolean2705 = false;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6021) {
                                                                           Unsorted.aBoolean1084 = ItemDefinition.intsStack[--iStackCounter] == 1;
                                                                           Unsorted.method792();
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6023) {
                                                                           int i36 = ItemDefinition.intsStack[--iStackCounter];
                                                                           if (0 > i36)
                                                                              i36 = 0;
                                                                           if (i36 > 2)
                                                                              i36 = 2;
                                                                           boolean flag6 = false;
                                                                           if (96 > Class3_Sub24_Sub3.anInt3492) {
                                                                              flag6 = true;
                                                                              i36 = 0;
                                                                           }
                                                                           Class127_Sub1.method1758(i36);
                                                                           Class119.method1730(Class38.aClass87_665);
                                                                           aBoolean2705 = false;
                                                                           ItemDefinition.intsStack[iStackCounter++] = flag6 ? 0 : 1;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6024) {
                                                                           int j36 = ItemDefinition.intsStack[--iStackCounter];
                                                                           if (j36 < 0 || 2 < j36)
                                                                              j36 = 0;
                                                                           Class3_Sub28_Sub9.anInt3622 = j36;
                                                                           Class119.method1730(Class38.aClass87_665);
                                                                           continue;
                                                                        }
                                                                        if (opcode != 6028)
                                                                           break;
                                                                        Class163_Sub3.aBoolean3004 = ItemDefinition.intsStack[--iStackCounter] != 0;
                                                                        Class119.method1730(Class38.aClass87_665);
                                                                        continue;
                                                                  }
                                                                  if (opcode < 6200) {
                                                                        if (opcode == 6101) {
                                                                           ItemDefinition.intsStack[iStackCounter++] = Unsorted.anInt3625;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6102) {
                                                                           ItemDefinition.intsStack[iStackCounter++] = NPC.method1986(109) ? 1 : 0;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6103) {
                                                                           ItemDefinition.intsStack[iStackCounter++] = Unsorted.aBoolean3604 ? 1 : 0;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6105) {
                                                                           ItemDefinition.intsStack[iStackCounter++] = KeyboardListener.aBoolean1905 ? 1 : 0;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6106) {
                                                                           ItemDefinition.intsStack[iStackCounter++] = Class25.aBoolean488 ? 1 : 0;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6107) {
                                                                           ItemDefinition.intsStack[iStackCounter++] = RSInterface.aBoolean236 ? 1 : 0;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6108) {
                                                                           ItemDefinition.intsStack[iStackCounter++] = WorldListEntry.aBoolean2623 ? 1 : 0;
                                                                           continue;
                                                                        }
                                                                        if (6109 == opcode) {
                                                                           ItemDefinition.intsStack[iStackCounter++] = Class3_Sub13_Sub22.aBoolean3275 ? 1 : 0;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6110) {
                                                                           ItemDefinition.intsStack[iStackCounter++] = Class140_Sub6.aBoolean2910 ? 1 : 0;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6111) {
                                                                           ItemDefinition.intsStack[iStackCounter++] = Unsorted.anInt1137;
                                                                           continue;
                                                                        }
                                                                        if (6112 == opcode) {
                                                                           ItemDefinition.intsStack[iStackCounter++] = Class106.aBoolean1441 ? 1 : 0;
                                                                           continue;
                                                                        }
                                                                        if (6114 == opcode) {
                                                                           ItemDefinition.intsStack[iStackCounter++] = Class128.aBoolean1685 ? 1 : 0;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6115) {
                                                                           ItemDefinition.intsStack[iStackCounter++] = Class38.aBoolean661 ? 1 : 0;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6116) {
                                                                           ItemDefinition.intsStack[iStackCounter++] = Unsorted.anInt3671;
                                                                           continue;
                                                                        }
                                                                        if (6117 == opcode) {
                                                                           ItemDefinition.intsStack[iStackCounter++] = Class3_Sub13_Sub15.aBoolean3184 ? 1 : 0;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6118) {
                                                                           ItemDefinition.intsStack[iStackCounter++] = anInt2453;
                                                                           continue;
                                                                        }
                                                                        if (6119 == opcode) {
                                                                           ItemDefinition.intsStack[iStackCounter++] = Unsorted.anInt120;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6120) {
                                                                           ItemDefinition.intsStack[iStackCounter++] = Sprites.anInt340;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6121) {
                                                                           if (HDToolKit.highDetail)
                                                                              ItemDefinition.intsStack[iStackCounter++] = HDToolKit.supportMultisample ? 1 : 0;
                                                                           else
                                                                              ItemDefinition.intsStack[iStackCounter++] = 0;
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6123) {
                                                                           ItemDefinition.intsStack[iStackCounter++] = Class127_Sub1.method1757();
                                                                           continue;
                                                                        }
                                                                        if (opcode == 6124) {
                                                                           ItemDefinition.intsStack[iStackCounter++] = Class3_Sub28_Sub9.anInt3622;
                                                                           continue;
                                                                        }
                                                                        if (opcode != 6128)
                                                                           break;
                                                                        ItemDefinition.intsStack[iStackCounter++] = Class163_Sub3.aBoolean3004 ? 1 : 0;
                                                                        continue;
                                                                  }
                                                                  if (opcode >= 6300) {
                                                                        if (opcode < 6400) {
                                                                           if (opcode == 6300) {
                                                                              ItemDefinition.intsStack[iStackCounter++] = (int) (TimeUtils.time() / 60000L);
                                                                              continue;
                                                                           }
                                                                           if (opcode == 6301) {
                                                                              ItemDefinition.intsStack[iStackCounter++] = -11745 + (int) (TimeUtils.time() / 0x5265c00L);
                                                                              continue;
                                                                           }
                                                                           if (opcode == 6302) {
                                                                              iStackCounter -= 3;
                                                                              int i73 = ItemDefinition.intsStack[iStackCounter + 2];
                                                                              int l60 = ItemDefinition.intsStack[iStackCounter - -1];
                                                                              int k36 = ItemDefinition.intsStack[iStackCounter];
                                                                              Class3_Sub28_Sub9.aCalendar3616.clear();
                                                                              Class3_Sub28_Sub9.aCalendar3616.set(Calendar.HOUR_OF_DAY, 12);
                                                                              Class3_Sub28_Sub9.aCalendar3616.set(i73, l60, k36);
                                                                              ItemDefinition.intsStack[iStackCounter++] = -11745 + (int) (Class3_Sub28_Sub9.aCalendar3616.getTime().getTime() / 0x5265c00L);
                                                                              continue;
                                                                           }
                                                                           if (6303 == opcode) {
                                                                              Class3_Sub28_Sub9.aCalendar3616.clear();
                                                                              Class3_Sub28_Sub9.aCalendar3616.setTime(new Date(TimeUtils.time()));
                                                                              ItemDefinition.intsStack[iStackCounter++] = Class3_Sub28_Sub9.aCalendar3616.get(Calendar.YEAR);
                                                                              continue;
                                                                           }
                                                                           if (opcode != 6304)
                                                                              break;
                                                                           boolean flag7 = true;
                                                                           int l36 = ItemDefinition.intsStack[--iStackCounter];
                                                                           if (l36 >= 0) {
                                                                              if (l36 >= 1582) {
                                                                                    if (l36 % 4 == 0) {
                                                                                       if (l36 % 100 != 0)
                                                                                          flag7 = true;
                                                                                       else if (0 != l36 % 400)
                                                                                          flag7 = false;
                                                                                    } else {
                                                                                       flag7 = false;
                                                                                    }
                                                                              } else {
                                                                                    flag7 = l36 % 4 == 0;
                                                                              }
                                                                           } else {
                                                                              flag7 = (1 + l36) % 4 == 0;
                                                                           }
                                                                           ItemDefinition.intsStack[iStackCounter++] = flag7 ? 1 : 0;
                                                                           continue;
                                                                        }
                                                                        if (opcode >= 6500) {
                                                                           if (opcode < 6600) {
                                                                              if (opcode == 6500) {
                                                                                    if (Class143.loadingStage != 10 || Class163_Sub1_Sub1.anInt3375 != 0 || 0 != LoginHandler.loginStage || 0 != Unsorted.registryStage)
                                                                                       ItemDefinition.intsStack[iStackCounter++] = 1;
                                                                                    else
                                                                                       ItemDefinition.intsStack[iStackCounter++] = Class121.method1735() == -1 ? 0 : 1;
                                                                                    continue;
                                                                              }
                                                                              if (opcode == 6501) {
                                                                                    WorldListEntry worldEntry = Class140_Sub2.method1953();
                                                                                    if (worldEntry == null) {
                                                                                       ItemDefinition.intsStack[iStackCounter++] = -1;
                                                                                       ItemDefinition.intsStack[iStackCounter++] = 0;
                                                                                       ItemDefinition.stringsStack[sStackCounter++] = TextCore.aClass94_2331;
                                                                                       ItemDefinition.intsStack[iStackCounter++] = 0;
                                                                                       ItemDefinition.stringsStack[sStackCounter++] = TextCore.aClass94_2331;
                                                                                       ItemDefinition.intsStack[iStackCounter++] = 0;
                                                                                    } else {
                                                                                       ItemDefinition.intsStack[iStackCounter++] = worldEntry.worldId;
                                                                                       ItemDefinition.intsStack[iStackCounter++] = worldEntry.settings;
                                                                                       ItemDefinition.stringsStack[sStackCounter++] = worldEntry.activity;
                                                                                       WorldListCountry class26 = worldEntry.method1078(60);
                                                                                       ItemDefinition.intsStack[iStackCounter++] = class26.flagId;
                                                                                       ItemDefinition.stringsStack[sStackCounter++] = class26.name;
                                                                                       ItemDefinition.intsStack[iStackCounter++] = worldEntry.anInt722;
                                                                                    }
                                                                                    continue;
                                                                              }
                                                                              if (opcode == 6502) {
                                                                                    WorldListEntry class44_sub1_1 = ItemDefinition.method1107(5422);
                                                                                    if (null == class44_sub1_1) {
                                                                                       ItemDefinition.intsStack[iStackCounter++] = -1;
                                                                                       ItemDefinition.intsStack[iStackCounter++] = 0;
                                                                                       ItemDefinition.stringsStack[sStackCounter++] = TextCore.aClass94_2331;
                                                                                       ItemDefinition.intsStack[iStackCounter++] = 0;
                                                                                       ItemDefinition.stringsStack[sStackCounter++] = TextCore.aClass94_2331;
                                                                                       ItemDefinition.intsStack[iStackCounter++] = 0;
                                                                                    } else {
                                                                                       ItemDefinition.intsStack[iStackCounter++] = class44_sub1_1.worldId;
                                                                                       ItemDefinition.intsStack[iStackCounter++] = class44_sub1_1.settings;
                                                                                       ItemDefinition.stringsStack[sStackCounter++] = class44_sub1_1.activity;
                                                                                       WorldListCountry class26_1 = class44_sub1_1.method1078(70);
                                                                                       ItemDefinition.intsStack[iStackCounter++] = class26_1.flagId;
                                                                                       ItemDefinition.stringsStack[sStackCounter++] = class26_1.name;
                                                                                       ItemDefinition.intsStack[iStackCounter++] = class44_sub1_1.anInt722;
                                                                                    }
                                                                                    continue;
                                                                              }
                                                                              if (opcode == 6503) {
                                                                                    int i37 = ItemDefinition.intsStack[--iStackCounter];
                                                                                    if (Class143.loadingStage != 10 || Class163_Sub1_Sub1.anInt3375 != 0 || LoginHandler.loginStage != 0 || Unsorted.registryStage != 0)
                                                                                       ItemDefinition.intsStack[iStackCounter++] = 0;
                                                                                    else
                                                                                       ItemDefinition.intsStack[iStackCounter++] = WaterfallShader.method1627(i37, (byte) -7) ? 1 : 0;
                                                                                    continue;
                                                                              }
                                                                              if (opcode == 6504) {
                                                                                    Unsorted.anInt2148 = ItemDefinition.intsStack[--iStackCounter];
                                                                                    Class119.method1730(Class38.aClass87_665);
                                                                                    continue;
                                                                              }
                                                                              if (6505 == opcode) {
                                                                                    ItemDefinition.intsStack[iStackCounter++] = Unsorted.anInt2148;
                                                                                    continue;
                                                                              }
                                                                              if (opcode == 6506) {
                                                                                    int j37 = ItemDefinition.intsStack[--iStackCounter];
                                                                                    WorldListEntry class44_sub1_2 = Class3_Sub8.getWorld(120, j37);
                                                                                    if (class44_sub1_2 == null) {
                                                                                       ItemDefinition.intsStack[iStackCounter++] = -1;
                                                                                       ItemDefinition.stringsStack[sStackCounter++] = TextCore.aClass94_2331;
                                                                                       ItemDefinition.intsStack[iStackCounter++] = 0;
                                                                                       ItemDefinition.stringsStack[sStackCounter++] = TextCore.aClass94_2331;
                                                                                       ItemDefinition.intsStack[iStackCounter++] = 0;
                                                                                    } else {
                                                                                       ItemDefinition.intsStack[iStackCounter++] = class44_sub1_2.settings;
                                                                                       ItemDefinition.stringsStack[sStackCounter++] = class44_sub1_2.activity;
                                                                                       WorldListCountry class26_2 = class44_sub1_2.method1078(-87);
                                                                                       ItemDefinition.intsStack[iStackCounter++] = class26_2.flagId;
                                                                                       ItemDefinition.stringsStack[sStackCounter++] = class26_2.name;
                                                                                       ItemDefinition.intsStack[iStackCounter++] = class44_sub1_2.anInt722;
                                                                                    }
                                                                                    continue;
                                                                              }
                                                                              if (opcode != 6507)
                                                                                    break;
                                                                              iStackCounter -= 4;
                                                                              int j73 = ItemDefinition.intsStack[iStackCounter + 2];
                                                                              int k37 = ItemDefinition.intsStack[iStackCounter];
                                                                              boolean flag11 = ItemDefinition.intsStack[iStackCounter - -3] == 1;
                                                                              boolean flag8 = ItemDefinition.intsStack[1 + iStackCounter] == 1;
                                                                              Class134.method1808(j73, flag8, k37, flag11);
                                                                              continue;
                                                                           }
                                                                           if (opcode >= 6700)
                                                                              break;
                                                                           if (6600 == opcode) {
                                                                              Class15.aBoolean346 = ItemDefinition.intsStack[--iStackCounter] == 1;
                                                                              Class119.method1730(Class38.aClass87_665);
                                                                              continue;
                                                                           }
                                                                           if (opcode != 6601)
                                                                              break;
                                                                           ItemDefinition.intsStack[iStackCounter++] = Class15.aBoolean346 ? 1 : 0;
                                                                           continue;
                                                                        }
                                                                        if (6405 == opcode) {
                                                                           ItemDefinition.intsStack[iStackCounter++] = Unsorted.method1088(false) ? 1 : 0;
                                                                           continue;
                                                                        }
                                                                        if (opcode != 6406)
                                                                           break;
                                                                        ItemDefinition.intsStack[iStackCounter++] = Class159.method2194() ? 1 : 0;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 6200) {
                                                                        iStackCounter -= 2;
                                                                        aShort1444 = (short) ItemDefinition.intsStack[iStackCounter];
                                                                        if (0 >= aShort1444)
                                                                           aShort1444 = 256;
                                                                        aShort3052 = (short) ItemDefinition.intsStack[1 + iStackCounter];
                                                                        if (aShort3052 <= 0)
                                                                           aShort3052 = 205;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 6201) {
                                                                        iStackCounter -= 2;
                                                                        Unsorted.aShort46 = (short) ItemDefinition.intsStack[iStackCounter];
                                                                        if (Unsorted.aShort46 <= 0)
                                                                           Unsorted.aShort46 = 256;
                                                                        ObjectDefinition.aShort1535 = (short) ItemDefinition.intsStack[1 + iStackCounter];
                                                                        if (ObjectDefinition.aShort1535 <= 0)
                                                                           ObjectDefinition.aShort1535 = 320;
                                                                        continue;
                                                                  }
                                                                  if (opcode == 6202) {
                                                                        iStackCounter -= 4;
                                                                        aShort3241 = (short) ItemDefinition.intsStack[iStackCounter];
                                                                        if (aShort3241 <= 0)
                                                                           aShort3241 = 1;
                                                                        PacketParser.aShort83 = (short) ItemDefinition.intsStack[1 + iStackCounter];
                                                                        if (PacketParser.aShort83 > 0) {
                                                                           if (aShort3241 > PacketParser.aShort83)
                                                                              PacketParser.aShort83 = aShort3241;
                                                                        } else {
                                                                           PacketParser.aShort83 = 32767;
                                                                        }
                                                                        ItemDefinition.aShort505 = (short) ItemDefinition.intsStack[2 + iStackCounter];
                                                                        if (ItemDefinition.aShort505 <= 0)
                                                                           ItemDefinition.aShort505 = 1;
                                                                        Class3_Sub13_Sub23_Sub1.aShort4038 = (short) ItemDefinition.intsStack[iStackCounter - -3];
                                                                        if (Class3_Sub13_Sub23_Sub1.aShort4038 > 0) {
                                                                           if (ItemDefinition.aShort505 > Class3_Sub13_Sub23_Sub1.aShort4038)
                                                                              Class3_Sub13_Sub23_Sub1.aShort4038 = ItemDefinition.aShort505;
                                                                        } else {
                                                                           Class3_Sub13_Sub23_Sub1.aShort4038 = 32767;
                                                                        }
                                                                        continue;
                                                                  }
                                                                  if (opcode == 6203) {
                                                                        Class65.method1239(Class168.aClass11_2091.width, 81, 0, Class168.aClass11_2091.height, 0, false);
                                                                        ItemDefinition.intsStack[iStackCounter++] = Class96.anInt1358;
                                                                        ItemDefinition.intsStack[iStackCounter++] = Unsorted.anInt31;
                                                                        continue;
                                                                  }
                                                                  if (6204 == opcode) {
                                                                        ItemDefinition.intsStack[iStackCounter++] = Unsorted.aShort46;
                                                                        ItemDefinition.intsStack[iStackCounter++] = ObjectDefinition.aShort1535;
                                                                        continue;
                                                                  }
                                                                  if (opcode != 6205)
                                                                        break;
                                                                  ItemDefinition.intsStack[iStackCounter++] = aShort1444;
                                                                  ItemDefinition.intsStack[iStackCounter++] = aShort3052;
                                                                  continue;
                                                               }
                                                               if (opcode == 5600) {
                                                                  sStackCounter -= 2;
                                                                  RSString class94_27 = ItemDefinition.stringsStack[sStackCounter];
                                                                  RSString class94_56 = ItemDefinition.stringsStack[sStackCounter + 1];
                                                                  int k73 = ItemDefinition.intsStack[--iStackCounter];
                                                                  if (Class143.loadingStage == 10 && Class163_Sub1_Sub1.anInt3375 == 0 && LoginHandler.loginStage == 0 && Unsorted.registryStage == 0 && Class43.anInt692 == 0)
                                                                        Class131.method1793(class94_27, class94_56, k73);
                                                                  continue;
                                                               }
                                                               if (opcode == 5601) {
                                                                  Class110.method1681(-1);
                                                                  continue;
                                                               }
                                                               if (opcode == 5602) {
                                                                  if (0 == LoginHandler.loginStage)
                                                                        Class158.anInt2005 = -2;
                                                                  continue;
                                                               }
                                                               if (opcode == 5603) {
                                                                  iStackCounter -= 4;
                                                                  if (Class143.loadingStage == 10 && 0 == Class163_Sub1_Sub1.anInt3375 && LoginHandler.loginStage == 0 && Unsorted.registryStage == 0 && Class43.anInt692 == 0)
                                                                        sendRegistryRequest(ItemDefinition.intsStack[iStackCounter - -2], ItemDefinition.intsStack[iStackCounter + 3], ItemDefinition.intsStack[iStackCounter], ItemDefinition.intsStack[iStackCounter + 1]);
                                                                  continue;
                                                               }
                                                               if (opcode == 5604) {
                                                                  sStackCounter--;
                                                                  if (Class143.loadingStage == 10 && Class163_Sub1_Sub1.anInt3375 == 0 && LoginHandler.loginStage == 0 && Unsorted.registryStage == 0 && Class43.anInt692 == 0) {
                                                                        Class40.method1041(ItemDefinition.stringsStack[sStackCounter].toLong(), ItemDefinition.stringsStack[sStackCounter]);
                                                                        continue;
                                                                  }
                                                               }
                                                               if (opcode == 5605) {
                                                                  iStackCounter -= 4;
                                                                  sStackCounter -= 2;
                                                                  if (Class143.loadingStage == 10 && 0 == Class163_Sub1_Sub1.anInt3375 && LoginHandler.loginStage == 0 && Unsorted.registryStage == 0 && Class43.anInt692 == 0)
                                                                        Class3_Sub28_Sub6.a(ItemDefinition.intsStack[iStackCounter], ItemDefinition.intsStack[iStackCounter - -3], ItemDefinition.intsStack[1 + iStackCounter], ItemDefinition.stringsStack[1 + sStackCounter], ItemDefinition.stringsStack[sStackCounter].toLong(), ItemDefinition.intsStack[2 + iStackCounter], ItemDefinition.stringsStack[sStackCounter]);
                                                                  continue;
                                                               }
                                                               if (opcode == 5606) {
                                                                  if (Unsorted.registryStage == 0)
                                                                        Unsorted.anInt1711 = -2;
                                                                  continue;
                                                               }
                                                               if (opcode == 5607) {
                                                                  ItemDefinition.intsStack[iStackCounter++] = Class158.anInt2005;
                                                                  continue;
                                                               }
                                                               if (opcode == 5608) {
                                                                  ItemDefinition.intsStack[iStackCounter++] = Class3_Sub13_Sub34.anInt3413;
                                                                  continue;
                                                               }
                                                               if (5609 == opcode) {
                                                                  ItemDefinition.intsStack[iStackCounter++] = Unsorted.anInt1711;
                                                                  continue;
                                                               }
                                                               if (opcode == 5610) {
                                                                  for (int l37 = 0; l37 < 5; l37++)
                                                                        ItemDefinition.stringsStack[sStackCounter++] = Class3_Sub13_Sub33.aClass94Array3391.length <= l37 ? TextCore.aClass94_2331 : Class3_Sub13_Sub33.aClass94Array3391[l37].method1545();

                                                                  Class3_Sub13_Sub33.aClass94Array3391 = null;
                                                                  continue;
                                                               }
                                                               if (opcode != 5611)
                                                                  break;
                                                               ItemDefinition.intsStack[iStackCounter++] = Class3_Sub26.anInt2561;
                                                               continue;
                                                            }
                                                            if (4500 != opcode)
                                                               break;
                                                            iStackCounter -= 2;
                                                            int i38 = ItemDefinition.intsStack[iStackCounter];
                                                            int i61 = ItemDefinition.intsStack[iStackCounter - -1];
                                                            Class3_Sub28_Sub9 class3_sub28_sub9_1 = Class61.method1210(i61);
                                                            if (!class3_sub28_sub9_1.method585())
                                                               ItemDefinition.intsStack[iStackCounter++] = StructDefinitionProvider.provide(i38).getInt(i61, class3_sub28_sub9_1.anInt3614);
                                                            else
                                                               ItemDefinition.stringsStack[sStackCounter++] = StructDefinitionProvider.provide(i38).getString(i61, class3_sub28_sub9_1.aClass94_3619);
                                                            continue;
                                                      }
                                                      if (opcode != 4400)
                                                            break;
                                                      iStackCounter -= 2;
                                                      int j61 = ItemDefinition.intsStack[iStackCounter - -1];
                                                      int j38 = ItemDefinition.intsStack[iStackCounter];
                                                      Class3_Sub28_Sub9 class3_sub28_sub9_2 = Class61.method1210(j61);
                                                      if (!class3_sub28_sub9_2.method585())
                                                            ItemDefinition.intsStack[iStackCounter++] = ObjectDefinition.getObjectDefinition(j38).method1691(class3_sub28_sub9_2.anInt3614, j61, (byte) 105);
                                                      else
                                                            ItemDefinition.stringsStack[sStackCounter++] = ObjectDefinition.getObjectDefinition(j38).method1698(class3_sub28_sub9_2.aClass94_3619, j61);
                                                      continue;
                                                   }
                                                   if (opcode == 4200) {
                                                      int k38 = ItemDefinition.intsStack[--iStackCounter];
                                                      ItemDefinition.stringsStack[sStackCounter++] = Class38.getItemDefinition(k38).name;
                                                      continue;
                                                   }
                                                   if (opcode == 4201) {
                                                      iStackCounter -= 2;
                                                      int l38 = ItemDefinition.intsStack[iStackCounter];
                                                      int k61 = ItemDefinition.intsStack[iStackCounter - -1];
                                                      ItemDefinition class48_2 = Class38.getItemDefinition(l38);
                                                      if (k61 < 1 || k61 > 5 || class48_2.groundOptions[-1 + k61] == null)
                                                            ItemDefinition.stringsStack[sStackCounter++] = TextCore.aClass94_2331;
                                                      else
                                                            ItemDefinition.stringsStack[sStackCounter++] = class48_2.groundOptions[k61 - 1];
                                                      continue;
                                                   }
                                                   if (opcode == 4202) {
                                                      iStackCounter -= 2;
                                                      int i39 = ItemDefinition.intsStack[iStackCounter];
                                                      int l61 = ItemDefinition.intsStack[iStackCounter + 1];
                                                      ItemDefinition class48_3 = Class38.getItemDefinition(i39);
                                                      if (l61 >= 1 && l61 <= 5 && null != class48_3.inventoryOptions[l61 + -1]) {
                                                            ItemDefinition.stringsStack[sStackCounter++] = class48_3.inventoryOptions[-1 + l61];
                                                      } else {
                                                            ItemDefinition.stringsStack[sStackCounter++] = TextCore.aClass94_2331;
                                                      }
                                                      continue;
                                                   }
                                                   if (opcode == 4203) {
                                                      int j39 = ItemDefinition.intsStack[--iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = Class38.getItemDefinition(j39).value;
                                                      continue;
                                                   }
                                                   if (opcode == 4204) {
                                                      int k39 = ItemDefinition.intsStack[--iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = Class38.getItemDefinition(k39).stackingType == 1 ? 1 : 0;
                                                      continue;
                                                   }
                                                   if (4205 == opcode) {
                                                      int l39 = ItemDefinition.intsStack[--iStackCounter];
                                                      ItemDefinition class48 = Class38.getItemDefinition(l39);
                                                      if (-1 == class48.anInt791 && class48.anInt789 >= 0)
                                                            ItemDefinition.intsStack[iStackCounter++] = class48.anInt789;
                                                      else
                                                            ItemDefinition.intsStack[iStackCounter++] = l39;
                                                      continue;
                                                   }
                                                   if (opcode == 4206) {
                                                      int i40 = ItemDefinition.intsStack[--iStackCounter];
                                                      ItemDefinition class48_1 = Class38.getItemDefinition(i40);
                                                      if (0 <= class48_1.anInt791 && class48_1.anInt789 >= 0)
                                                            ItemDefinition.intsStack[iStackCounter++] = class48_1.anInt789;
                                                      else
                                                            ItemDefinition.intsStack[iStackCounter++] = i40;
                                                      continue;
                                                   }
                                                   if (opcode == 4207) {
                                                      int j40 = ItemDefinition.intsStack[--iStackCounter];
                                                      ItemDefinition.intsStack[iStackCounter++] = Class38.getItemDefinition(j40).membersItem ? 1 : 0;
                                                      continue;
                                                   }
                                                   if (opcode == 4208) {
                                                      iStackCounter -= 2;
                                                      int k40 = ItemDefinition.intsStack[iStackCounter];
                                                      int i62 = ItemDefinition.intsStack[iStackCounter - -1];
                                                      Class3_Sub28_Sub9 class3_sub28_sub9_3 = Class61.method1210(i62);
                                                      if (class3_sub28_sub9_3.method585())
                                                            ItemDefinition.stringsStack[sStackCounter++] = Class38.getItemDefinition(k40).method1105(class3_sub28_sub9_3.aClass94_3619, i62);
                                                      else
                                                            ItemDefinition.intsStack[iStackCounter++] = Class38.getItemDefinition(k40).method1115(class3_sub28_sub9_3.anInt3614, -119, i62);
                                                      continue;
                                                   }
                                                   if (4210 == opcode) {
                                                      RSString class94_28 = ItemDefinition.stringsStack[--sStackCounter];
                                                      int j62 = ItemDefinition.intsStack[--iStackCounter];
                                                      NPCDefinition.method1480(j62 == 1, class94_28);
                                                      ItemDefinition.intsStack[iStackCounter++] = Unsorted.anInt952;
                                                      continue;
                                                   }
                                                   if (opcode == 4211) {
                                                      if (null == Class99.aShortArray1398 || Class140_Sub4.anInt2756 >= Unsorted.anInt952)
                                                            ItemDefinition.intsStack[iStackCounter++] = -1;
                                                      else
                                                            ItemDefinition.intsStack[iStackCounter++] = Unsorted.bitwiseAnd(Class99.aShortArray1398[Class140_Sub4.anInt2756++], 65535);
                                                      continue;
                                                   }
                                                   if (4212 != opcode)
                                                      break;
                                                   Class140_Sub4.anInt2756 = 0;
                                                   continue;
                                                }
                                                if (4100 == opcode) {
                                                   RSString class94_29 = ItemDefinition.stringsStack[--sStackCounter];
                                                   int k62 = ItemDefinition.intsStack[--iStackCounter];
                                                   ItemDefinition.stringsStack[sStackCounter++] = RSString.stringCombiner(new RSString[]{
                                                            class94_29, RSString.stringAnimator(k62)
                                                   });
                                                   continue;
                                                }
                                                if (opcode == 4101) {
                                                   sStackCounter -= 2;
                                                   RSString class94_57 = ItemDefinition.stringsStack[sStackCounter + 1];
                                                   RSString class94_30 = ItemDefinition.stringsStack[sStackCounter];
                                                   ItemDefinition.stringsStack[sStackCounter++] = RSString.stringCombiner(new RSString[]{
                                                            class94_30, class94_57
                                                   });
                                                   continue;
                                                }
                                                if (4102 == opcode) {
                                                   RSString class94_31 = ItemDefinition.stringsStack[--sStackCounter];
                                                   int l62 = ItemDefinition.intsStack[--iStackCounter];
                                                   ItemDefinition.stringsStack[sStackCounter++] = RSString.stringCombiner(new RSString[]{
                                                            class94_31, Class61.method1218(l62)
                                                   });
                                                   continue;
                                                }
                                                if (opcode == 4103) {
                                                   RSString class94_32 = ItemDefinition.stringsStack[--sStackCounter];
                                                   ItemDefinition.stringsStack[sStackCounter++] = class94_32.toLowercase();
                                                   continue;
                                                }
                                                if (4104 == opcode) {
                                                   int l40 = ItemDefinition.intsStack[--iStackCounter];
                                                   long l63 = 0xec44e2dc00L + (long) l40 * 0x5265c00L;
                                                   Class3_Sub28_Sub9.aCalendar3616.setTime(new Date(l63));
                                                   int k78 = Class3_Sub28_Sub9.aCalendar3616.get(Calendar.DATE);
                                                   int k80 = Class3_Sub28_Sub9.aCalendar3616.get(Calendar.MONTH);
                                                   int i82 = Class3_Sub28_Sub9.aCalendar3616.get(Calendar.YEAR);
                                                   ItemDefinition.stringsStack[sStackCounter++] = RSString.stringCombiner(new RSString[]{
                                                            RSString.stringAnimator(k78), TextCore.aClass94_1326, TextCore.MonthsOfTheYear[k80], TextCore.aClass94_1326, RSString.stringAnimator(i82)
                                                   });
                                                   continue;
                                                }
                                                if (4105 == opcode) {
                                                   sStackCounter -= 2;
                                                   RSString class94_58 = ItemDefinition.stringsStack[sStackCounter + 1];
                                                   RSString class94_33 = ItemDefinition.stringsStack[sStackCounter];
                                                   if (Class102.player.class52 == null || !Class102.player.class52.aBoolean864)
                                                      ItemDefinition.stringsStack[sStackCounter++] = class94_33;
                                                   else
                                                      ItemDefinition.stringsStack[sStackCounter++] = class94_58;
                                                   continue;
                                                }
                                                if (opcode == 4106) {
                                                   int i41 = ItemDefinition.intsStack[--iStackCounter];
                                                   ItemDefinition.stringsStack[sStackCounter++] = RSString.stringAnimator(i41);
                                                   continue;
                                                }
                                                if (opcode == 4107) {
                                                   sStackCounter -= 2;
                                                   ItemDefinition.intsStack[iStackCounter++] = ItemDefinition.stringsStack[sStackCounter].method1546(ItemDefinition.stringsStack[sStackCounter - -1]);
                                                   continue;
                                                }
                                                if (4108 == opcode) {
                                                   RSString class94_34 = ItemDefinition.stringsStack[--sStackCounter];
                                                   iStackCounter -= 2;
                                                   int l73 = ItemDefinition.intsStack[iStackCounter - -1];
                                                   int i63 = ItemDefinition.intsStack[iStackCounter];
                                                   ItemDefinition.intsStack[iStackCounter++] = AtmosphereParser.method1430(l73).method684(class94_34, i63);
                                                   continue;
                                                }
                                                if (opcode == 4109) {
                                                   iStackCounter -= 2;
                                                   RSString class94_35 = ItemDefinition.stringsStack[--sStackCounter];
                                                   int i74 = ItemDefinition.intsStack[1 + iStackCounter];
                                                   int j63 = ItemDefinition.intsStack[iStackCounter];
                                                   ItemDefinition.intsStack[iStackCounter++] = AtmosphereParser.method1430(i74).method680(class94_35, j63);
                                                   continue;
                                                }
                                                if (opcode == 4110) {
                                                   sStackCounter -= 2;
                                                   RSString class94_36 = ItemDefinition.stringsStack[sStackCounter];
                                                   RSString class94_59 = ItemDefinition.stringsStack[sStackCounter - -1];
                                                   if (1 == ItemDefinition.intsStack[--iStackCounter])
                                                      ItemDefinition.stringsStack[sStackCounter++] = class94_36;
                                                   else
                                                      ItemDefinition.stringsStack[sStackCounter++] = class94_59;
                                                   continue;
                                                }
                                                if (4111 == opcode) {
                                                   RSString class94_37 = ItemDefinition.stringsStack[--sStackCounter];
                                                   ItemDefinition.stringsStack[sStackCounter++] = Class3_Sub28_Sub17.method686(class94_37);
                                                   continue;
                                                }
                                                if (4112 == opcode) {
                                                   RSString class94_38 = ItemDefinition.stringsStack[--sStackCounter];
                                                   int k63 = ItemDefinition.intsStack[--iStackCounter];
                                                   if (k63 == -1)
                                                      throw new RuntimeException("null char");
                                                   ItemDefinition.stringsStack[sStackCounter++] = class94_38.method1548(k63);
                                                   continue;
                                                }
                                                if (opcode == 4113) {
                                                   int j41 = ItemDefinition.intsStack[--iStackCounter];
                                                   ItemDefinition.intsStack[iStackCounter++] = Class164_Sub2.method2248(j41) ? 1 : 0;
                                                   continue;
                                                }
                                                if (opcode == 4114) {
                                                   int k41 = ItemDefinition.intsStack[--iStackCounter];
                                                   ItemDefinition.intsStack[iStackCounter++] = Class44.method1066(k41) ? 1 : 0;
                                                   continue;
                                                }
                                                if (opcode == 4115) {
                                                   int l41 = ItemDefinition.intsStack[--iStackCounter];
                                                   ItemDefinition.intsStack[iStackCounter++] = Class3_Sub24_Sub4.method487(l41, (byte) -85) ? 1 : 0;
                                                   continue;
                                                }
                                                if (4116 == opcode) {
                                                   int i42 = ItemDefinition.intsStack[--iStackCounter];
                                                   ItemDefinition.intsStack[iStackCounter++] = Class3_Sub28_Sub3.method544(i42) ? 1 : 0;
                                                   continue;
                                                }
                                                if (opcode == 4117) {
                                                   RSString class94_39 = ItemDefinition.stringsStack[--sStackCounter];
                                                   if (class94_39 != null)
                                                      ItemDefinition.intsStack[iStackCounter++] = class94_39.length();
                                                   else
                                                      ItemDefinition.intsStack[iStackCounter++] = 0;
                                                   continue;
                                                }
                                                if (opcode == 4118) {
                                                   iStackCounter -= 2;
                                                   RSString class94_40 = ItemDefinition.stringsStack[--sStackCounter];
                                                   int i64 = ItemDefinition.intsStack[iStackCounter];
                                                   int j74 = ItemDefinition.intsStack[1 + iStackCounter];
                                                   ItemDefinition.stringsStack[sStackCounter++] = class94_40.substring(i64, j74, 0);
                                                   continue;
                                                }
                                                if (opcode == 4119) {
                                                   RSString class94_41 = ItemDefinition.stringsStack[--sStackCounter];
                                                   RSString class94_60 = Unsorted.emptyString(class94_41.length());
                                                   boolean flag9 = false;
                                                   for (int l78 = 0; class94_41.length() > l78; l78++) {
                                                      int l80 = class94_41.charAt(l78, (byte) -40);
                                                      if (l80 == 60) {
                                                            flag9 = true;
                                                            continue;
                                                      }
                                                      if (l80 == 62) {
                                                            flag9 = false;
                                                      } else if (!flag9)
                                                            class94_60.appendCharacter(l80);
                                                   }

                                                   class94_60.method1576();
                                                   ItemDefinition.stringsStack[sStackCounter++] = class94_60;
                                                   continue;
                                                }
                                                if (opcode == 4120) {
                                                   iStackCounter -= 2;
                                                   RSString class94_42 = ItemDefinition.stringsStack[--sStackCounter];
                                                   int j64 = ItemDefinition.intsStack[iStackCounter];
                                                   int k74 = ItemDefinition.intsStack[1 + iStackCounter];
                                                   ItemDefinition.intsStack[iStackCounter++] = class94_42.method1555(j64, k74);
                                                   continue;
                                                }
                                                if (opcode == 4121) {
                                                   sStackCounter -= 2;
                                                   RSString class94_43 = ItemDefinition.stringsStack[sStackCounter];
                                                   RSString class94_61 = ItemDefinition.stringsStack[1 + sStackCounter];
                                                   int l74 = ItemDefinition.intsStack[--iStackCounter];
                                                   ItemDefinition.intsStack[iStackCounter++] = class94_43.method1566(class94_61, l74);
                                                   continue;
                                                }
                                                if (opcode == 4122) {
                                                   int j42 = ItemDefinition.intsStack[--iStackCounter];
                                                   ItemDefinition.intsStack[iStackCounter++] = Class3_Sub13_Sub34.method332(2, j42);
                                                   continue;
                                                }
                                                if (opcode == 4123) {
                                                   int k42 = ItemDefinition.intsStack[--iStackCounter];
                                                   ItemDefinition.intsStack[iStackCounter++] = ClientErrorException.method2287(k42, (byte) 59);
                                                   continue;
                                                }
                                                if (opcode != 4124)
                                                   break;
                                                boolean flag1 = ItemDefinition.intsStack[--iStackCounter] != 0;
                                                int k64 = ItemDefinition.intsStack[--iStackCounter];
                                                ItemDefinition.stringsStack[sStackCounter++] = Class3_Sub23.method407(Class3_Sub20.language, flag1, 0, k64);
                                                continue;
                                          }
                                          RSInterface class11_8 = Class7.getRSInterface(ItemDefinition.intsStack[--iStackCounter]);
                                          if (opcode == 2800) {
                                                ItemDefinition.intsStack[iStackCounter++] = Client.method44(class11_8).method101();
                                                continue;
                                          }
                                          if (opcode == 2801) {
                                                int l64 = ItemDefinition.intsStack[--iStackCounter];
                                                l64--;
                                                if (class11_8.aClass94Array171 != null && class11_8.aClass94Array171.length > l64 && null != class11_8.aClass94Array171[l64])
                                                   ItemDefinition.stringsStack[sStackCounter++] = class11_8.aClass94Array171[l64];
                                                else
                                                   ItemDefinition.stringsStack[sStackCounter++] = TextCore.aClass94_2331;
                                                continue;
                                          }
                                          if (opcode != 2802)
                                                break;
                                          if (class11_8.aClass94_277 != null)
                                                ItemDefinition.stringsStack[sStackCounter++] = class11_8.aClass94_277;
                                          else
                                                ItemDefinition.stringsStack[sStackCounter++] = TextCore.aClass94_2331;
                                          continue;
                                       }
                                       if (opcode == 2700) {
                                          RSInterface class11_9 = Class7.getRSInterface(ItemDefinition.intsStack[--iStackCounter]);
                                          ItemDefinition.intsStack[iStackCounter++] = class11_9.anInt192;
                                          continue;
                                       }
                                       if (opcode == 2701) {
                                          RSInterface class11_10 = Class7.getRSInterface(ItemDefinition.intsStack[--iStackCounter]);
                                          if (-1 != class11_10.anInt192)
                                                ItemDefinition.intsStack[iStackCounter++] = class11_10.anInt271;
                                          else
                                                ItemDefinition.intsStack[iStackCounter++] = 0;
                                          continue;
                                       }
                                       if (opcode == 2702) {
                                          int l42 = ItemDefinition.intsStack[--iStackCounter];
                                          Class3_Sub31 class3_sub31 = (Class3_Sub31) Class3_Sub13_Sub17.aHashTable_3208.get(l42);
                                          if (class3_sub31 == null)
                                                ItemDefinition.intsStack[iStackCounter++] = 0;
                                          else
                                                ItemDefinition.intsStack[iStackCounter++] = 1;
                                          continue;
                                       }
                                       if (opcode == 2703) {
                                          RSInterface class11_11 = Class7.getRSInterface(ItemDefinition.intsStack[--iStackCounter]);
                                          if (null == class11_11.aClass11Array262) {
                                                ItemDefinition.intsStack[iStackCounter++] = 0;
                                          } else {
                                                int i65 = class11_11.aClass11Array262.length;
                                                int i75 = 0;
                                                do {
                                                   if (class11_11.aClass11Array262.length <= i75)
                                                      break;
                                                   if (null == class11_11.aClass11Array262[i75]) {
                                                      i65 = i75;
                                                      break;
                                                   }
                                                   i75++;
                                                } while (true);
                                                ItemDefinition.intsStack[iStackCounter++] = i65;
                                          }
                                          continue;
                                       }
                                       if (opcode != 2704 && 2705 != opcode)
                                          break;
                                       iStackCounter -= 2;
                                       int i43 = ItemDefinition.intsStack[iStackCounter];
                                       int j65 = ItemDefinition.intsStack[iStackCounter + 1];
                                       Class3_Sub31 class3_sub31_1 = (Class3_Sub31) Class3_Sub13_Sub17.aHashTable_3208.get(i43);
                                       if (class3_sub31_1 == null || class3_sub31_1.anInt2602 != j65)
                                          ItemDefinition.intsStack[iStackCounter++] = 0;
                                       else
                                          ItemDefinition.intsStack[iStackCounter++] = 1;
                                       continue;
                                    }
                                    RSInterface class11_12 = Class7.getRSInterface(ItemDefinition.intsStack[--iStackCounter]);
                                    if (2600 == opcode) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_12.anInt247;
                                       continue;
                                    }
                                    if (opcode == 2601) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_12.anInt208;
                                       continue;
                                    }
                                    if (opcode == 2602) {
                                       ItemDefinition.stringsStack[sStackCounter++] = class11_12.text;
                                       continue;
                                    }
                                    if (opcode == 2603) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_12.anInt240;
                                       continue;
                                    }
                                    if (opcode == 2604) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_12.anInt252;
                                       continue;
                                    }
                                    if (opcode == 2605) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_12.anInt164;
                                       continue;
                                    }
                                    if (opcode == 2606) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_12.anInt182;
                                       continue;
                                    }
                                    if (opcode == 2607) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_12.anInt280;
                                       continue;
                                    }
                                    if (2608 == opcode) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_12.anInt308;
                                       continue;
                                    }
                                    if (opcode == 2609) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_12.anInt223;
                                       continue;
                                    }
                                    if (opcode == 2610) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_12.anInt258;
                                       continue;
                                    }
                                    if (opcode == 2611) {
                                       ItemDefinition.intsStack[iStackCounter++] = class11_12.anInt264;
                                       continue;
                                    }
                                    if (2612 != opcode)
                                       break;
                                    ItemDefinition.intsStack[iStackCounter++] = class11_12.spriteArchiveId;
                                    continue;
                              }
                              RSInterface class11_13 = flag ? Class164.aClass11_2055 : aClass11_1749;
                              if (opcode == 1700) {
                                    ItemDefinition.intsStack[iStackCounter++] = class11_13.anInt192;
                                    continue;
                              }
                              if (1701 == opcode) {
                                    if (class11_13.anInt192 == -1)
                                       ItemDefinition.intsStack[iStackCounter++] = 0;
                                    else
                                       ItemDefinition.intsStack[iStackCounter++] = class11_13.anInt271;
                                    continue;
                              }
                              if (opcode != 1702)
                                    break;
                              ItemDefinition.intsStack[iStackCounter++] = class11_13.anInt191;
                           } else {
                              RSInterface class11_14;
                              if (opcode < 2000) {
                                    class11_14 = flag ? Class164.aClass11_2055 : aClass11_1749;
                              } else {
                                    opcode -= 1000;
                                    class11_14 = Class7.getRSInterface(ItemDefinition.intsStack[--iStackCounter]);
                              }
                              int[] ai3 = null;
                              RSString class94_62 = ItemDefinition.stringsStack[--sStackCounter];
                              if (class94_62.length() > 0 && class94_62.charAt(class94_62.length() + -1, (byte) -96) == 89) {
                                    int i79 = ItemDefinition.intsStack[--iStackCounter];
                                    if (i79 > 0) {
                                       ai3 = new int[i79];
                                       while (i79-- > 0)
                                          ai3[i79] = ItemDefinition.intsStack[--iStackCounter];
                                    }
                                    class94_62 = class94_62.substring(0, class94_62.length() - 1, 0);
                              }
                              Object[] aobj1 = new Object[class94_62.length() - -1];
                              for (int i81 = -1 + aobj1.length; 1 <= i81; i81--)
                                    if (115 != class94_62.charAt(i81 + -1, (byte) -43))
                                       aobj1[i81] = new Integer(ItemDefinition.intsStack[--iStackCounter]);
                                    else
                                       aobj1[i81] = ItemDefinition.stringsStack[--sStackCounter];

                              int j81 = ItemDefinition.intsStack[--iStackCounter];
                              if (j81 == -1)
                                    aobj1 = null;
                              else
                                    aobj1[0] = new Integer(j81);
                              class11_14.aBoolean195 = true;
                              if (1400 == opcode)
                                    class11_14.anObjectArray165 = aobj1;
                              else if (opcode == 1401)
                                    class11_14.anObjectArray180 = aobj1;
                              else if (opcode == 1402) {
                                    class11_14.anObjectArray239 = aobj1;
                              } else if (opcode == 1403) {
                                    class11_14.anObjectArray248 = aobj1;
                              } else if (opcode == 1404) {
                                    class11_14.anObjectArray281 = aobj1;
                              } else if (1405 == opcode)
                                    class11_14.anObjectArray295 = aobj1;
                              else if (1406 == opcode)
                                    class11_14.anObjectArray303 = aobj1;
                              else if (1407 == opcode) {
                                    class11_14.anIntArray286 = ai3;
                                    class11_14.anObjectArray282 = aobj1;
                              } else if (opcode == 1408)
                                    class11_14.anObjectArray269 = aobj1;
                              else if (opcode == 1409) {
                                    class11_14.anObjectArray314 = aobj1;
                              } else if (1410 == opcode) {
                                    class11_14.anObjectArray229 = aobj1;
                              } else if (opcode == 1411) {
                                    class11_14.anObjectArray170 = aobj1;
                              } else if (opcode == 1412)
                                    class11_14.anObjectArray276 = aobj1;
                              else if (opcode == 1414) {
                                    class11_14.anIntArray175 = ai3;
                                    class11_14.anObjectArray174 = aobj1;
                              } else if (1415 == opcode) {
                                    class11_14.anIntArray274 = ai3;
                                    class11_14.anObjectArray158 = aobj1;
                              } else if (1416 == opcode)
                                    class11_14.anObjectArray203 = aobj1;
                              else if (1417 == opcode) {
                                    class11_14.anObjectArray183 = aobj1;
                              } else if (opcode == 1418)
                                    class11_14.anObjectArray256 = aobj1;
                              else if (opcode == 1419)
                                    class11_14.anObjectArray220 = aobj1;
                              else if (opcode == 1420)
                                    class11_14.anObjectArray156 = aobj1;
                              else if (opcode == 1421) {
                                    class11_14.anObjectArray313 = aobj1;
                              } else if (1422 == opcode)
                                    class11_14.anObjectArray315 = aobj1;
                              else if (1423 == opcode) {
                                    class11_14.anObjectArray206 = aobj1;
                              } else if (opcode == 1424)
                                    class11_14.anObjectArray176 = aobj1;
                              else if (opcode == 1425)
                                    class11_14.anObjectArray268 = aobj1;
                              else if (opcode == 1426) {
                                    class11_14.anObjectArray217 = aobj1;
                              } else if (1427 == opcode) {
                                    class11_14.anObjectArray235 = aobj1;
                              } else if (opcode == 1428) {
                                    class11_14.anObjectArray161 = aobj1;
                                    class11_14.anIntArray211 = ai3;
                              } else if (opcode == 1429) {
                                    class11_14.anIntArray185 = ai3;
                                    class11_14.anObjectArray221 = aobj1;
                              }
                           }
                           continue;
                        }
                        RSInterface class11_15;
                        if (opcode < 2000) {
                           class11_15 = flag ? Class164.aClass11_2055 : aClass11_1749;
                        } else {
                           class11_15 = Class7.getRSInterface(ItemDefinition.intsStack[--iStackCounter]);
                           opcode -= 1000;
                        }
                        Class20.method909(class11_15);
                        if (opcode == 1200 || 1205 == opcode) {
                           iStackCounter -= 2;
                           int j75 = ItemDefinition.intsStack[1 + iStackCounter];
                           int k65 = ItemDefinition.intsStack[iStackCounter];
                           if (-1 == class11_15.anInt191) {
                              MouseListeningClass.method2092(class11_15.componentHash);
                              Class3_Sub13_Sub19.method265(class11_15.componentHash);
                              Class107.method1649(class11_15.componentHash, -101);
                           }
                           if (-1 == k65) {
                              class11_15.itemId = -1;
                              class11_15.modelType = 1;
                              class11_15.anInt192 = -1;
                           } else {
                              class11_15.anInt192 = k65;
                              class11_15.anInt271 = j75;
                              ItemDefinition class48_4 = Class38.getItemDefinition(k65);
                              class11_15.anInt280 = class48_4.anInt768;
                              class11_15.anInt258 = class48_4.anInt792;
                              class11_15.anInt182 = class48_4.anInt786;
                              class11_15.anInt264 = class48_4.anInt754;
                              class11_15.anInt308 = class48_4.anInt799;
                              class11_15.anInt164 = class48_4.anInt810;
                              if (class11_15.anInt184 > 0)
                                    class11_15.anInt164 = (class11_15.anInt164 * 32) / class11_15.anInt184;
                              else if (class11_15.defWidth > 0)
                                    class11_15.anInt164 = (class11_15.anInt164 * 32) / class11_15.defWidth;
                              class11_15.aBoolean227 = 1205 != opcode;
                           }
                           continue;
                        }
                        if (opcode == 1201) {
                           class11_15.modelType = 2;
                           class11_15.itemId = ItemDefinition.intsStack[--iStackCounter];
                           if (class11_15.anInt191 == -1)
                              Class162.method2206(class11_15.componentHash);
                           continue;
                        }
                        if (opcode == 1202) {
                           class11_15.modelType = 3;
                           class11_15.itemId = Class102.player.class52.method1163();
                           if (class11_15.anInt191 == -1)
                              Class162.method2206(class11_15.componentHash);
                           continue;
                        }
                        if (1203 == opcode) {
                           class11_15.modelType = 6;
                           class11_15.itemId = ItemDefinition.intsStack[--iStackCounter];
                           if (class11_15.anInt191 == -1)
                              Class162.method2206(class11_15.componentHash);
                           continue;
                        }
                        if (opcode != 1204)
                           break;
                        class11_15.modelType = 5;
                        class11_15.itemId = ItemDefinition.intsStack[--iStackCounter];
                        if (class11_15.anInt191 == -1)
                           Class162.method2206(class11_15.componentHash);
                        continue;
                  }
                  RSInterface class11_16;
                  if (opcode < 2000) {
                        class11_16 = flag ? Class164.aClass11_2055 : aClass11_1749;
                  } else {
                        opcode -= 1000;
                        class11_16 = Class7.getRSInterface(ItemDefinition.intsStack[--iStackCounter]);
                  }
                  if (opcode == 1100) {
                        iStackCounter -= 2;
                        class11_16.anInt247 = ItemDefinition.intsStack[iStackCounter];
                        if (class11_16.anInt240 + -class11_16.width < class11_16.anInt247)
                           class11_16.anInt247 = class11_16.anInt240 + -class11_16.width;
                        if (class11_16.anInt247 < 0)
                           class11_16.anInt247 = 0;
                        class11_16.anInt208 = ItemDefinition.intsStack[iStackCounter + 1];
                        if (class11_16.anInt208 > class11_16.anInt252 + -class11_16.height)
                           class11_16.anInt208 = class11_16.anInt252 + -class11_16.height;
                        if (class11_16.anInt208 < 0)
                           class11_16.anInt208 = 0;
                        Class20.method909(class11_16);
                        if (-1 == class11_16.anInt191)
                           Class67.method1259(class11_16.componentHash);
                        continue;
                  }
                  if (1101 == opcode) {
                        class11_16.anInt218 = ItemDefinition.intsStack[--iStackCounter];
                        Class20.method909(class11_16);
                        if (class11_16.anInt191 == -1)
                           Unsorted.method56(class11_16.componentHash);
                        continue;
                  }
                  if (opcode == 1102) {
                        class11_16.aBoolean226 = ItemDefinition.intsStack[--iStackCounter] == 1;
                        Class20.method909(class11_16);
                        continue;
                  }
                  if (1103 == opcode) {
                        class11_16.anInt223 = ItemDefinition.intsStack[--iStackCounter];
                        Class20.method909(class11_16);
                        continue;
                  }
                  if (opcode == 1104) {
                        class11_16.anInt250 = ItemDefinition.intsStack[--iStackCounter];
                        Class20.method909(class11_16);
                        continue;
                  }
                  if (opcode == 1105) {
                        class11_16.spriteArchiveId = ItemDefinition.intsStack[--iStackCounter];
                        Class20.method909(class11_16);
                        continue;
                  }
                  if (1106 == opcode) {
                        class11_16.anInt301 = ItemDefinition.intsStack[--iStackCounter];
                        Class20.method909(class11_16);
                        continue;
                  }
                  if (1107 == opcode) {
                        class11_16.aBoolean186 = ItemDefinition.intsStack[--iStackCounter] == 1;
                        Class20.method909(class11_16);
                        continue;
                  }
                  if (opcode == 1108) {
                        class11_16.modelType = 1;
                        class11_16.itemId = ItemDefinition.intsStack[--iStackCounter];
                        Class20.method909(class11_16);
                        if (class11_16.anInt191 == -1)
                           Class162.method2206(class11_16.componentHash);
                        continue;
                  }
                  if (opcode == 1109) {
                        iStackCounter -= 6;
                        class11_16.anInt258 = ItemDefinition.intsStack[iStackCounter];
                        class11_16.anInt264 = ItemDefinition.intsStack[iStackCounter + 1];
                        class11_16.anInt182 = ItemDefinition.intsStack[2 + iStackCounter];
                        class11_16.anInt308 = ItemDefinition.intsStack[iStackCounter - -3];
                        class11_16.anInt280 = ItemDefinition.intsStack[iStackCounter - -4];
                        class11_16.anInt164 = ItemDefinition.intsStack[5 + iStackCounter];
                        Class20.method909(class11_16);
                        if (class11_16.anInt191 == -1) {
                           Class3_Sub13_Sub19.method265(class11_16.componentHash);
                           Class107.method1649(class11_16.componentHash, -106);
                        }
                        continue;
                  }
                  if (opcode == 1110) {
                        int l65 = ItemDefinition.intsStack[--iStackCounter];
                        if (class11_16.animationId != l65) {
                           class11_16.animationId = l65;
                           class11_16.anInt283 = 0;
                           class11_16.anInt267 = 0;
                           class11_16.anInt260 = 1;
                           Class20.method909(class11_16);
                        }
                        if (class11_16.anInt191 == -1)
                           Class108.method1657(class11_16.componentHash);
                        continue;
                  }
                  if (opcode == 1111) {
                        class11_16.aBoolean181 = 1 == ItemDefinition.intsStack[--iStackCounter];
                        Class20.method909(class11_16);
                        continue;
                  }
                  if (1112 == opcode) {
                        RSString newText = ItemDefinition.stringsStack[--sStackCounter];
                        if (!newText.equalsString(class11_16.text)) {
                           class11_16.text = newText;
                           Class20.method909(class11_16);
                        }
                        if (class11_16.anInt191 == -1)
                           Unsorted.method1516(class11_16.componentHash, 91);
                        continue;
                  }
                  if (opcode == 1113) {
                        class11_16.anInt270 = ItemDefinition.intsStack[--iStackCounter];
                        Class20.method909(class11_16);
                        continue;
                  }
                  if (opcode == 1114) {
                        iStackCounter -= 3;
                        class11_16.anInt194 = ItemDefinition.intsStack[iStackCounter];
                        class11_16.anInt225 = ItemDefinition.intsStack[1 + iStackCounter];
                        class11_16.anInt205 = ItemDefinition.intsStack[2 + iStackCounter];
                        Class20.method909(class11_16);
                        continue;
                  }
                  if (1115 == opcode) {
                        class11_16.aBoolean215 = 1 == ItemDefinition.intsStack[--iStackCounter];
                        Class20.method909(class11_16);
                        continue;
                  }
                  if (opcode == 1116) {
                        class11_16.anInt288 = ItemDefinition.intsStack[--iStackCounter];
                        Class20.method909(class11_16);
                        continue;
                  }
                  if (opcode == 1117) {
                        class11_16.anInt287 = ItemDefinition.intsStack[--iStackCounter];
                        Class20.method909(class11_16);
                        continue;
                  }
                  if (opcode == 1118) {
                        class11_16.aBoolean178 = ItemDefinition.intsStack[--iStackCounter] == 1;
                        Class20.method909(class11_16);
                        continue;
                  }
                  if (opcode == 1119) {
                        class11_16.aBoolean199 = ItemDefinition.intsStack[--iStackCounter] == 1;
                        Class20.method909(class11_16);
                        continue;
                  }
                  if (opcode == 1120) {
                        iStackCounter -= 2;
                        class11_16.anInt240 = ItemDefinition.intsStack[iStackCounter];
                        class11_16.anInt252 = ItemDefinition.intsStack[1 + iStackCounter];
                        Class20.method909(class11_16);
                        if (class11_16.type == 0)
                           Unsorted.method2104(class11_16, false, -116);
                        continue;
                  }
                  if (opcode == 1121) {
                        iStackCounter -= 2;
                        class11_16.aShort293 = (short) ItemDefinition.intsStack[iStackCounter];
                        class11_16.aShort169 = (short) ItemDefinition.intsStack[iStackCounter + 1];
                        Class20.method909(class11_16);
                        continue;
                  }
                  if (1122 == opcode) {
                        class11_16.aBoolean157 = ItemDefinition.intsStack[--iStackCounter] == 1;
                        Class20.method909(class11_16);
                        continue;
                  }
                  if (opcode != 1123)
                        break;
                  class11_16.anInt164 = ItemDefinition.intsStack[--iStackCounter];
                  Class20.method909(class11_16);
                  if (class11_16.anInt191 == -1)
                        Class3_Sub13_Sub19.method265(class11_16.componentHash);
                  continue;
               }
               if (opcode == 403) {
                  iStackCounter -= 2;
                  int i66 = ItemDefinition.intsStack[iStackCounter + 1];
                  int j43 = ItemDefinition.intsStack[iStackCounter];
                  int k75 = 0;
                  while (Class3_Sub26.anIntArray2559.length > k75) {
                        if (j43 == Class3_Sub26.anIntArray2559[k75]) {
                           Class102.player.class52.method1164(k75, i66);
                           continue label0;
                        }
                        k75++;
                  }
                  k75 = 0;
                  do {
                        if (anIntArray3228.length <= k75)
                           continue label0;
                        if (anIntArray3228[k75] == j43) {
                           Class102.player.class52.method1164(k75, i66);
                           continue label0;
                        }
                        k75++;
                  } while (true);
               }
               if (404 == opcode) {
                  iStackCounter -= 2;
                  int k43 = ItemDefinition.intsStack[iStackCounter];
                  int j66 = ItemDefinition.intsStack[1 + iStackCounter];
                  Class102.player.class52.method1162(k43, j66);
                  continue;
               }
               if (opcode != 410)
                  break;
               try {
                  boolean flag2 = 0 != ItemDefinition.intsStack[--iStackCounter];
                  Class102.player.class52.method1159(flag2);
               } catch (Exception e) {
                  e.printStackTrace();
               }
            } while (true);
            throw new IllegalStateException();
      } catch (Exception e) {
            System.out.println("Exception in item defs " + e);
      }
   }


    static Queue method1664(int var0, int var1) {
       try {
          Queue var3 = new Queue();

          for(Class3_Sub28_Sub3 var4 = (Class3_Sub28_Sub3)Class134.aClass61_1758.method1222(); var4 != null; var4 = (Class3_Sub28_Sub3)Class134.aClass61_1758.method1221()) {
             if(var4.aBoolean3553 && var4.method537(var1, var0)) {
                var3.offer(var4);
             }
          }

          return var3;
       } catch (RuntimeException var5) {
          throw ClientErrorException.clientError(var5, "ok.B(" + var0 + ',' + var1 + ',' + (byte) -118 + ')');
       }
    }

    static int method1643(boolean var1, int var2, int var3) {
       try {

           Class3_Sub25 var4 = (Class3_Sub25)Class3_Sub2.aHashTable_2220.get((long)var2);
          if(null == var4) {
             return 0;
          } else {
             int var5 = 0;

             for(int var6 = 0; var6 < var4.anIntArray2547.length; ++var6) {
                if(var4.anIntArray2547[var6] >= 0 && Class3_Sub13_Sub23.itemDefinitionSize > var4.anIntArray2547[var6]) {
                   ItemDefinition var7 = Class38.getItemDefinition(var4.anIntArray2547[var6]);
                   if(null != var7.aHashTable_798) {
                      LinkableInt var8 = (LinkableInt)var7.aHashTable_798.get((long)var3);
                      if(null != var8) {
                         if(var1) {
                            var5 += var4.anIntArray2551[var6] * var8.value;
                         } else {
                            var5 += var8.value;
                         }
                      }
                   }
                }
             }

             return var5;
          }
       } catch (RuntimeException var9) {
          throw ClientErrorException.clientError(var9, "od.B(" + 10131 + ',' + var1 + ',' + var2 + ',' + var3 + ')');
       }
    }
}
