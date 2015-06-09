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
package nim;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class NimFile extends PsiFileBase {
    public NimFile(final FileViewProvider fileViewProvider) {
        super(fileViewProvider, NimLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return NimFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "NimFile:" + getName();
    }
}
