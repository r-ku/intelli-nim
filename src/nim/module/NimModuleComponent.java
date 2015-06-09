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

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleComponent;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.JDOMExternalizable;
import com.intellij.openapi.util.WriteExternalException;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NimModuleComponent implements ModuleComponent, JDOMExternalizable {
    public enum ModuleType {
        APPLICATION,
        CONSOLE_APPLICATION,
        LIBRARY
    }

    private static final String MODULE_TYPE_ATTR = "module_type";

    private final Module module;

    private ModuleType moduleType = ModuleType.APPLICATION;

    @Nullable
    public static NimModuleComponent getInstance(Module module) {
        return module.getComponent(NimModuleComponent.class);
    }

    public NimModuleComponent(Module module) {
        this.module = module;
    }

    public void initComponent() {
    }

    public void disposeComponent() {
    }

    @NotNull
    public String getComponentName() {
        return "NimModuleComponent";
    }

    public void projectOpened() {
        // called when project is opened
    }

    public void projectClosed() {
        // called when project is being closed
    }

    public void moduleAdded() {
        // Invoked when the module corresponding to this component instance has been completely
        // loaded and added to the project.
    }

    public void readExternal(Element element) throws InvalidDataException {
        moduleType = ModuleType.valueOf(element.getAttributeValue(MODULE_TYPE_ATTR));
    }

    public void writeExternal(Element element) throws WriteExternalException {
        element.setAttribute(MODULE_TYPE_ATTR, moduleType.name());
    }

    public ModuleType getModuleType() {
        return moduleType;
    }

    public void setModuleType(ModuleType moduleType) {
        this.moduleType = moduleType;
    }
}
