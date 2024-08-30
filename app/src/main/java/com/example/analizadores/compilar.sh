#! /bin/bash
echo "STARTING COMPILING"
java -jar jflex-full-1.9.1.jar Lexer.lex
java -jar java-cup-11b.jar -parser Parser -symbols sym parser.cup
