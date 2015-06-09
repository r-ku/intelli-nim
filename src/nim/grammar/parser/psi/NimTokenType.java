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
package nim.grammar.parser.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import nim.NimLanguage;

public class NimTokenType extends IElementType {
    public NimTokenType(@NotNull @NonNls String debugName) {
        super(debugName, NimLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "NimTokenType." + super.toString();
    }
}
