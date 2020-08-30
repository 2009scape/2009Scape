package org.runite.jagex;

final class AssembledScript extends Node {

   // The ints are loaded in this order
   int numberOfIntsToCopy;
   int numberOfRSStringsToCopy;
   int intHeapCounterIncrement;
   int stringHeapCounterOffset;

   int[] assemblyInstructions;
   int[] assemblyRAM;
   Class130[] aClass130Array3685;
   RSString[] storedStrings;
}
