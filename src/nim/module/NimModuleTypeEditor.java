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
import com.intellij.openapi.module.ModuleConfigurationEditor;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.roots.ui.configuration.ModuleConfigurationState;
import com.intellij.openapi.ui.ComboBox;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class NimModuleTypeEditor implements ModuleConfigurationEditor {
    private final Module module;
    private final NimModuleComponent component;

    private ComboBox comboModuleType;

    public NimModuleTypeEditor(ModuleConfigurationState state) {
        module = state.getRootModel().getModule();
        component = NimModuleComponent.getInstance(module);
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Module Type";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        panel.add(new JLabel("Module type:"));

        comboModuleType = new ComboBox(new String[] { "Application", "Console application", "Library" });
        comboModuleType.setSelectedIndex(moduleTypeToComboIndex(component.getModuleType()));
        panel.add(comboModuleType);

        return panel;
    }

    @Override
    public boolean isModified() {
        return !component.getModuleType().equals(comboIndexToModuleType(comboModuleType.getSelectedIndex()));
    }

    @Override
    public void apply() throws ConfigurationException {
        component.setModuleType(comboIndexToModuleType(comboModuleType.getSelectedIndex()));
    }

    @Override
    public void reset() {
        comboModuleType.setSelectedIndex(moduleTypeToComboIndex(component.getModuleType()));
    }

    @Override
    public void disposeUIResources() {

    }

    @Override
    public void saveData() {

    }

    @Override
    public void moduleStateChanged() {

    }

    private int moduleTypeToComboIndex(NimModuleComponent.ModuleType type) {
        switch (type) {
            case APPLICATION: return 0;
            case CONSOLE_APPLICATION: return 1;
            case LIBRARY: return 2;
            default: return 0;
        }
    }

    private NimModuleComponent.ModuleType comboIndexToModuleType(int index) {
        switch (index) {
            case 0: return NimModuleComponent.ModuleType.APPLICATION;
            case 1: return NimModuleComponent.ModuleType.CONSOLE_APPLICATION;
            case 2: return NimModuleComponent.ModuleType.LIBRARY;
            default: return NimModuleComponent.ModuleType.APPLICATION;
        }
    }
}
