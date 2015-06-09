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

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NimFileType extends LanguageFileType {
    public static final NimFileType INSTANCE = new NimFileType();

    private NimFileType() {
        super(NimLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Nim file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Nim language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "nim";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return NimIcons.NIM_ICON_16;
    }
}
