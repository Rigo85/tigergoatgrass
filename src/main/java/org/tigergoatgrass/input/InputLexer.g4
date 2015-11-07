/**
 * Author Rigoberto Leander Salgado Reyes <rlsalgado2006@gmail.com>
 *
 * Copyright 2015 by Rigoberto Leander Salgado Reyes.
 * <p>
 * This program is licensed to you under the terms of version 3 of the
 * GNU Affero General Public License. This program is distributed WITHOUT
 * ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
 * AGPL (http:www.gnu.org/licenses/agpl-3.0.txt) for more details.
 */

lexer grammar Inputlexer;

OPENROW
    :   '['
    ;

CLOSEROW
    :   ']'
    ;

SEPARATOR
    :   ','
    ;

NEWLINE
    :   ('\r'? '\n')+
    ;

AFFINITY
    :   ('O' | 'x' | 'X' | 'o' | '0')
    ;

NAME
    :   ('a'..'z' | 'A'..'Z')+
    ;

WS
    : [ \t]+ -> skip
    ;

INT
    :   [0-9]+
    ;
