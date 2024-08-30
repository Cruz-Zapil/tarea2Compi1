package com.cunoc.practica1.backEnd.AFND;

import java_cup.runtime.Symbol;
import com.cunoc.practica1.backEnd.report.Errores;
import com.cunoc.practica1.frontEnd.paneles.panelReporte.PanelReporte;

%%
%public
%class Lexer
%cup
%unicode
%ignorecase

digit=[0-9]


%init{ 
    yyline = 1; 
    yychar = 0; 
    
%init} 
 

%{

    StringBuffer string = new StringBuffer();
    int longitudToken=0;

    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }

%}

%%

{digit}+("."{digit}+)? {   longitudToken = yytext().length();
    yychar+=longitudToken; return new Symbol(sym.NUMERO, yyline, (int)yychar, Double.valueOf(yytext())); }

"," {yychar+=1; return new Symbol(sym.COMA,yyline, (int)yychar, yytext()); }
"(" {yychar+=1; return new Symbol(sym.LPAREN,yyline, (int)yychar,yytext()); }
")" {yychar+=1; return new Symbol(sym.RPAREN,yyline, (int)yychar, yytext()); }
"+" {yychar+=1; return new Symbol(sym.PLUS, yyline, (int)yychar,yytext()); }
"-" {yychar+=1; return new Symbol(sym.MINUS,yyline, (int)yychar, yytext()); }
"*" {yychar+=1; return new Symbol(sym.TIMES, yyline, (int)yychar,yytext()); }
"/" {yychar+=1; return new Symbol(sym.DIVIDE, yyline, (int)yychar,yytext()); }
[\n\r] {yychar=1; yyline++;}
" " {yychar+=1; /* ignore */}


// Código en tu lexer
[^] { 
    yychar+=1;
    // Agregar el error léxico a la lista

    PanelReporte.agregarError(new Errores("Léxico", yytext() ,"Caracter inválido: " + yytext(), yyline, (int)yychar));
}