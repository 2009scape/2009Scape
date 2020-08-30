package org.runite.jagex;

public enum CS2AsmOpcodes {
    // Copies the operand specified by the instruction to the int stack
    PUSH_INT(0),
    // Copies the int from ram onto the int stack specified by the operand
    PUSH_INT_FROM_RAM(1),
    // Copies an int from the stack onto ram, using the ram address specified by the operand
    POP_INT_TO_RAM(2),
    // Copies the operand specified by the instruction to the string stack
    PUSH_STR(3),
    // Jump to the relative location given by the operand. EG an op of 4 will jump ahead 4 instructions.
    JUMP(6),
    // Jumps to the relative location given by the operand IFF the last two values pushed on the int stack are NOT equal
    // THIS ALSO POPS THE STACK TWICE
    BRANCH_NOT_EQUAL(7),
    // Jumps to the relative location given by the operand IFF the last two values on the int stack are equal
    // THIS ALSO POPS THE STACK TWICE
    BRANCH_EQUAL(8),
    // Jumps to the relative location given by operand IFF the most recent value on the int stack > the second most recent one
    // THIS ALSO POPS THE STACK TWICE
    BRANCH_GREATER_THAN(9),
    // See above but in reverse
    BRANCH_LESS_THAN(10),
    // Returns from the current method, completely analogous to returning from a method in any other programming language.
    // It places you back on the previous method and resumes execution right after the call.
    // IT DOES NOT TOUCH THE INT OR STRING STACK OR THE RAM.
    RETURN(21),
    // TODO: Opcode 25, opcode 27. I suspect these are tied together

    // See 9 and 10
    BRANCH_GREATER_OR_EQUAL(31),
    BRANCH_LESS_OR_EQUAL(32),

    // Copies the int from the int args array onto the stack specified by the operand.
    // eg if the operand is 2 it copies the second int arg onto the stack
    PUSH_INT_FROM_ARGS(33),
    // Copies an int from the stack onto the int args array. It copies it onto the argument array index specified
    // by the operand.
    POP_INT_TO_ARGS(34),
    // See above but for the string array and string args.
    PUSH_STRING_FROM_ARGS(35),
    POP_STRING_TO_ARGS(36),
    // TODO: Opcode 37

    // Pops int/string from the stack without using it. Effectively throwing it away.
    POP_INT(38),
    POP_STRING(39),

    // Calls the method specified by the operand. The method's arguments are LOADED from the int and string stack
    // If it has 4 int arguments, for instance, it will pop the int stack four times and assign these values in
    // REVERSE order. That is, the first popped value becomes the LAST argument. Number of arguments are given in
    // AssembledMethod.NumberOf[X]Arguments
    // This effectively "resets" the program counter. It DOES NOT RESET THE INT OR STRING STACK OR THE RAM.
    // Also It adds the previous method to the method stack. SEE RETURN(21)
    CALL(40),

    // TODO: Opcodes 42 and 43. 42 saves to a special different kind of ram that only 43 can load from. 43
    // does some weird shit with this number

    // allocates one of the five paged ram pages as selected by the top 16 bits of the operand.
    // If the bottom 16 bits equal 105, this will be allocated as all zeroes.
    // Otherwise it will be allocated with all -1s. Why?? Jagex is fucking stupid idk.
    // THE NUMBER OF INTS ALLOCATED IS BASED ON THE VALUE ON INT STACK.
    // POPS THE INT STACK
    ALLOCATE_PAGED_RAM(44),

    // Replaces the value at the end of the stack with the value in the ram page specified by the operand
    // and the page entry specified by the last value of the int stack
    REPLACE_STACK_PAGED_RAM(45),

    // Copies an int from the int stack to paged ram. The page is given by the operand and the
    // page entry is given by the NEXT value on the int stack.
    // Thus this pops the int stack TWICE.
    POP_TO_PAGED_RAM(46);


    private int op;

    public int getOp() {
        return this.op;
    }

    private CS2AsmOpcodes(int op) {
        this.op = op;
    }
}
