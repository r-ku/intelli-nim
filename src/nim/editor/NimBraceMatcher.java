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
package nim.editor;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import nim.grammar.parser.psi.NimTypes;

public class NimBraceMatcher implements PairedBraceMatcher {
    private static BracePair[] ourBracePairs =
            {
                    new BracePair(NimTypes.BRACKETDOTLE, NimTypes.BRACKETDOTRI, true),
                    new BracePair(NimTypes.BRACKETLE, NimTypes.BRACKETRI, false),
                    new BracePair(NimTypes.PARLE, NimTypes.PARRI, false),
                    new BracePair(NimTypes.PARDOTLE, NimTypes.PARDOTRI, false)
            };

    @Override
    public BracePair[] getPairs() {
        return ourBracePairs;
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        return true;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}
