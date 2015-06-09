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
package nim.module;

import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import org.jetbrains.annotations.NotNull;
import nim.NimIcons;

import javax.swing.*;

public class NimModuleType extends ModuleType<NimModuleBuilder> {
    public static final String MODULE_TYPE_ID = "NIM_MODULE";

    public static ModuleType getInstance() {
        return (NimModuleType) ModuleTypeManager.getInstance().findByID(MODULE_TYPE_ID);
    }

    public NimModuleType() {
        super(MODULE_TYPE_ID);
    }

    @NotNull
    @Override
    public NimModuleBuilder createModuleBuilder() {
        return new NimModuleBuilder();
    }

    @NotNull
    @Override
    public String getName() {
        return "Nim Module";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "A module supporting Nim development.";
    }

    @Override
    public Icon getBigIcon() {
        return NimIcons.NIM_ICON_24;
    }

    @Override
    public Icon getNodeIcon(@Deprecated boolean b) {
        return NimIcons.NIM_ICON_16;
    }
}
