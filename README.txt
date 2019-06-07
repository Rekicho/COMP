**PROJECT TITLE: Compiler of the Java-- language to Java Bytecodes

**GROUP: 61

Bruno Miguel da Silva Barbosa Sousa, 201604145, GRADE: 15, CONTRIBUTION: 25%

Francisco Manuel Canelas Filipe, 201604601, GRADE: 15, CONTRIBUTION: 25%

Henrique Reis Sendim Rodrigues, 201606462, GRADE: 15, CONTRIBUTION: 25%

Maria Eduarda Santos Cunha, 201506524, GRADE: 15, CONTRIBUTION: 25%

GLOBAL Grade of the project: 15

** SUMMARY: (Describe what your tool does and its main features.)

The compiler takes a j-- file, checks if it is valid in j-- and generates valid Jasmin instructions from the class in that file. 
Jasmin then takes those instructions and generates JVM bytecodes.

Its main features are:

	- Parse a j-- file.

	- Syntatic analysis

	- AST generation

	- Symbol table generation

	- Semantic analysis

	- Code generation

** EXECUTE:

	From the project root, run:

	java -jar jmm.jar [-o] <input_file.jmm> //Generate .j file with jasmin instructions; -o: optional optimization

	java -jar jasmin.jar <input_file.j> //Generate .class with JVM bytecodes

	java <className> //Execute the code

**DEALING WITH SYNTACTIC ERRORS:

ERROR HANDLING:
	To deal with error handling we display the error and expected expression and exit the program.

ERROR RECOVERY:
	When recovering from errors in a 'while' statement the tool will only exit after 10 errors.
	Apart from this implementation of the 'while' statement, all other statements will end the program after the first error.

**SEMANTIC ANALYSIS:

The semantic analysis rules present in this tool are the following:

	- addition, subtraction, division, multiplication and less than operators must have both operands as integers.

	- Not operator ('!') must be followed by a boolean expression. 

	- When accessing arrays([...]) guarantee that the index is an integer. 

	- When using '.length'  check if attribute is an array.

	- Check if the number of parameters passed to function call is correct.

	- Check if the types of the parameters passed to a function call are correct.

	- Check if 'this' is being used in a static function.


**INTERMEDIATE REPRESENTATIONS (IRs): 

	The intermediate representations were not implemented in this tool.


**CODE GENERATION:

	Code generation is done at a node level. 
	When traversing the AST each node knows how to generate its code. Using this method, in a given node, the generation of code only returns the code 
	of that node and calls its child nodes to generate their code. 

	We also implemented a mechanism of loading constants so that the constants loaded are more time efficient.

	A possible problem of this implementation is that since we're generating code at a node level, we're not considering the possibility of using less 
	instructions by grouping nodes. This may lead to a less efficient program execution.


**OVERVIEW: 

	Our approach to the implementation was simply a regular approach of a Tree-like structure where each node knows what it is responsible to do.
	To do anything, we only need to traverse the tree and ask every node to do something. For example, in the code generation, each node knows what
	code it needs to generate, so it calls its children code and add its own.  

	The only algorithm used was tree traversal using depth-first search.

	No third party tools/software were used.

**OPTIMIZATIONS:

	*** -r:

		Not implemented, although it is calculated the defs of each function variable.

	*** -o:

		Constant Propagation: only done on variables = CONSTANT where the variable is defined only once, 
		so only done on: variable = INTEGERLITERAL, varaible = true, variable = false, variable = this.

		While template: Already used without -o option, the templated used is the following:

		while(<cond>){<body>}

				<cond>
				ifeq ENDWHILE
		WHILE:  <body>
				<cond>
				ifne WHILE

		This template was chosen because it removes one jump when the condition is false and has no goto instruction.

**TASK DISTRIBUTION:

	Every member colaborated in every task.

**PROS:

	- Mainly LL(1) parser, with only local lookahead(2).
	- AST printed to the console with a good formating.
	- Logic node separtion of AST
	- Symbol table printed to the console with a good formating.
	- Constant loading instructions optimized.
	- While loop optimized without -o option

**CONS:

	- Method overloading not implemented.
	- No -r optimization and simplified constant propagation.
	- Syntatic & semantic errors with little information.