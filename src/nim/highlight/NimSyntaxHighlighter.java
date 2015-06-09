/*
 *    Intellij platform plugin which adds support Nim programming language
 *    Copyright (C) 2015  intelli-nim developers
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 */
package nim.highlight;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import nim.grammar._NimGrammarLexer;
import nim.grammar.parser.psi.NimTypes;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class NimSyntaxHighlighter extends SyntaxHighlighterBase {
    private static final Map<IElementType, TextAttributesKey> keys;

    public static class TokenGroups {
        public static final TokenSet KEYWORDS = TokenSet.create(NimTypes.ADDR, NimTypes.AND, NimTypes.AS,
                NimTypes.ASM, NimTypes.ATOMIC, NimTypes.BIND, NimTypes.BLOCK, NimTypes.BREAK, NimTypes.CASE,
                NimTypes.CAST, NimTypes.CONST, NimTypes.CONTINUE, NimTypes.CONVERTER, NimTypes.DEFER,
                NimTypes.DISCARD, NimTypes.DISTINCT, NimTypes.DIV, NimTypes.DO, NimTypes.ELIF,
                NimTypes.ELSE, NimTypes.END, NimTypes.ENUM, NimTypes.EXCEPT, NimTypes.EXPORT,
                NimTypes.FINALLY, NimTypes.FOR, NimTypes.FROM, NimTypes.FUNC, NimTypes.GENERIC, NimTypes.IF,
                NimTypes.IMPORT, NimTypes.IN, NimTypes.INCLUDE, NimTypes.INTERFACE, NimTypes.IS,
                NimTypes.ISNOT, NimTypes.ITERATOR, NimTypes.LET, NimTypes.MACRO, NimTypes.METHOD,
                NimTypes.MIXIN, NimTypes.MOD, NimTypes.NIL, NimTypes.NOT, NimTypes.NOTIN, NimTypes.OBJECT,
                NimTypes.OF, NimTypes.OR, NimTypes.OUT, NimTypes.PROC, NimTypes.PTR, NimTypes.RAISE,
                NimTypes.REF, NimTypes.RETURN, NimTypes.SHL, NimTypes.SHR, NimTypes.STATIC,
                NimTypes.TEMPLATE, NimTypes.TRY, NimTypes.TUPLE, NimTypes.TYPE, NimTypes.USING,
                NimTypes.VAR, NimTypes.WHEN, NimTypes.WHILE, NimTypes.WITH,NimTypes.WITHOUT, NimTypes.XOR,
                NimTypes.YIELD);

        public static final TokenSet NUMBERS = TokenSet.create(NimTypes.HEX_LIT, NimTypes.DEC_LIT, NimTypes.OCT_LIT,
                NimTypes.BIN_LIT, NimTypes.INTLIT, NimTypes.INT8LIT, NimTypes.INT16LIT, NimTypes.INT32LIT,
                NimTypes.INT64LIT, NimTypes.UINTLIT, NimTypes.UINT8LIT, NimTypes.UINT16LIT,
                NimTypes.UINT32LIT, NimTypes.UINT64LIT, NimTypes.EXPONENT, NimTypes.FLOATLIT,
                NimTypes.FLOAT32LIT, NimTypes.FLOAT64LIT, NimTypes.FLOAT128LIT);

        public static final TokenSet STRINGS = TokenSet.create(NimTypes.STRLIT, NimTypes.RSTRLIT,
                NimTypes.TRIPLESTRLIT);

        public static final TokenSet OPERATIONS = TokenSet.create(NimTypes.OPR);
    }

    public static class ColorGroups {
        public static final TextAttributesKey BAD_CHARACTER = TextAttributesKey.createTextAttributesKey(
                "NIM.BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

        public static final TextAttributesKey LINE_COMMENT = TextAttributesKey.createTextAttributesKey(
                "NIM.LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);

        public static final TextAttributesKey BLOCK_COMMENT = TextAttributesKey.createTextAttributesKey(
                "NIM.BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);

        public static final TextAttributesKey COMMA = TextAttributesKey.createTextAttributesKey(
                "NIM.COMMA", DefaultLanguageHighlighterColors.COMMA);

        public static final TextAttributesKey DOT = TextAttributesKey.createTextAttributesKey(
                "NIM.DOT", DefaultLanguageHighlighterColors.DOT);

        public static final TextAttributesKey SEMICOLON = TextAttributesKey.createTextAttributesKey(
                "NIM.SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON);

        public static final TextAttributesKey IDENTIFIER = TextAttributesKey.createTextAttributesKey(
                "NIM.IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);

        public static final TextAttributesKey KEYWORDS = TextAttributesKey.createTextAttributesKey(
                "NIM.KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);

        public static final TextAttributesKey NUMBER = TextAttributesKey.createTextAttributesKey(
                "NIM.NUMBER", DefaultLanguageHighlighterColors.NUMBER);

        public static final TextAttributesKey STRING = TextAttributesKey.createTextAttributesKey(
                "NIM.STRING", DefaultLanguageHighlighterColors.STRING);

        public static final TextAttributesKey OPERATIONS = TextAttributesKey.createTextAttributesKey(
                "NIM.OPERATION", DefaultLanguageHighlighterColors.OPERATION_SIGN);

        public static final TextAttributesKey BRACKETS = TextAttributesKey.createTextAttributesKey(
                "NIM.BRACKET", DefaultLanguageHighlighterColors.BRACKETS);

        public static final TextAttributesKey PARENTHESES = TextAttributesKey.createTextAttributesKey(
                "NIM.PARENTHESIS", DefaultLanguageHighlighterColors.PARENTHESES);

        public static final TextAttributesKey BRACES = TextAttributesKey.createTextAttributesKey(
                "NIM.BRACE", DefaultLanguageHighlighterColors.BRACES);
    }

    static {
        keys = new HashMap<>();

        keys.put(TokenType.BAD_CHARACTER, ColorGroups.BAD_CHARACTER);
        keys.put(NimTypes.COMMENT, ColorGroups.LINE_COMMENT);
//        keys.put(NimTypes.BLOCK_COMMENT, ColorGroups.BLOCK_COMMENT);
        keys.put(NimTypes.COMMA, ColorGroups.COMMA);
        keys.put(NimTypes.DOT, ColorGroups.DOT);
        keys.put(NimTypes.SEMICOLON, ColorGroups.SEMICOLON);
//        keys.put(NimTypes., ColorGroups.IDENTIFIER);
        keys.put(NimTypes.BRACKETLE, ColorGroups.BRACKETS);
        keys.put(NimTypes.BRACKETRI, ColorGroups.BRACKETS);
        keys.put(NimTypes.PARLE, ColorGroups.PARENTHESES);
        keys.put(NimTypes.PARRI, ColorGroups.PARENTHESES);

        SyntaxHighlighterBase.fillMap(keys, TokenGroups.KEYWORDS, ColorGroups.KEYWORDS);
        SyntaxHighlighterBase.fillMap(keys, TokenGroups.NUMBERS, ColorGroups.NUMBER);
        SyntaxHighlighterBase.fillMap(keys, TokenGroups.STRINGS, ColorGroups.STRING);
        SyntaxHighlighterBase.fillMap(keys, TokenGroups.OPERATIONS, ColorGroups.OPERATIONS);
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new FlexAdapter(new _NimGrammarLexer((Reader) null));
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(final IElementType iElementType) {
        return pack(keys.get(iElementType));
    }
}
