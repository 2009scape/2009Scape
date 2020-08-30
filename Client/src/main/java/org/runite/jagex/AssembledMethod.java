package org.runite.jagex;

final class AssembledMethod extends Node {

   // The ints are loaded in this order
   int numberOfIntsToCopy;
   int numberOfRSStringsToCopy;
   int numberOfIntArguments;
   int numberOfStringArguments;

   int[] assemblyInstructions;
   int[] instructionOperands;
   Class130[] aClass130Array3685;
   RSString[] stringInstructionOperands;
}
