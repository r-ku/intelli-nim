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
package nim.configuration;

import com.intellij.execution.ui.CommonProgramParametersPanel;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.ui.ListCellRendererWrapper;
import com.intellij.ui.PanelWithAnchor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import nim.compiler.NimCommandLineUtils;
import nim.module.NimModuleComponent;
import nim.module.NimModuleType;
import nim.sdk.NimSdkData;
import nim.sdk.NimSdkPlatformData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NimRunSettingsEditor extends SettingsEditor<NimRunConfiguration> implements PanelWithAnchor {
    private final Project project;

    private CommonProgramParametersPanel parametersPanel;
    private JPanel pnlMain;
    private JComboBox cmbModule;
    private JComboBox cmbPlatform;
    private JRadioButton rbRelease;
    private JRadioButton rbDebug;

    private JComponent anchor;

    public NimRunSettingsEditor(Project project) {
        this.project = project;

        cmbModule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parametersPanel.setModuleContext((Module)cmbModule.getSelectedItem());
            }
        });
        cmbModule.setRenderer(new ListCellRendererWrapper() {
            @Override
            public void customize(JList list, Object value, int index, boolean selected, boolean hasFocus) {
                if (value instanceof Module) {
                    final Module module = (Module)value;
                    setText(module.getName());
                }
            }
        });

        cmbPlatform.setRenderer(new ListCellRendererWrapper() {
            @Override
            public void customize(JList list, Object value, int index, boolean selected, boolean hasFocus) {
                if (value instanceof NimSdkPlatformData) {
                    final NimSdkPlatformData platformData = (NimSdkPlatformData)value;
                    setText(platformData.PLATFORM + " / " + platformData.ARCH);
                }
            }
        });
    }

    @Override
    protected void resetEditorFrom(NimRunConfiguration configuration) {
        parametersPanel.reset(configuration);

        Module selectedModule = configuration.getConfigurationModule().getModule();
        cmbModule.removeAll();
        final Module[] modules = ModuleManager.getInstance(configuration.getProject()).getModules();
        for (final Module module : modules) {
            if (ModuleType.get(module) == NimModuleType.getInstance()) {
                NimModuleComponent component = NimModuleComponent.getInstance(module);
                if (component.getModuleType() == NimModuleComponent.ModuleType.APPLICATION ||
                    component.getModuleType() == NimModuleComponent.ModuleType.CONSOLE_APPLICATION) {
                    cmbModule.addItem(module);
                }
            }
        }
        cmbModule.setSelectedItem(selectedModule);

        NimSdkPlatformData selectedPlatformData = null;
        String homePath = ModuleRootManager.getInstance(selectedModule).getSdk().getHomePath();
        NimSdkData sdkData = NimCommandLineUtils.getSdkData(homePath);
        cmbPlatform.removeAll();
        if (sdkData != null)
        {
            for (NimSdkPlatformData platformData : sdkData.platforms) {
                cmbPlatform.addItem(platformData);

                if (platformData.PLATFORM.equals(configuration.getPlatform()) &&
                    platformData.ARCH.equals(configuration.getArch())) {
                    selectedPlatformData = platformData;
                }
            }
        }
        cmbPlatform.setSelectedItem(selectedPlatformData);

        rbRelease.setSelected(!configuration.getIsDebug());
        rbDebug.setSelected(configuration.getIsDebug());
    }

    @Override
    protected void applyEditorTo(NimRunConfiguration configuration) throws ConfigurationException {
        parametersPanel.applyTo(configuration);
        configuration.setModule((Module)cmbModule.getSelectedItem());
        configuration.setPlatform(((NimSdkPlatformData)cmbPlatform.getSelectedItem()).PLATFORM);
        configuration.setArch(((NimSdkPlatformData)cmbPlatform.getSelectedItem()).ARCH);
        configuration.setIsDebug(rbDebug.isSelected());
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return pnlMain;
    }

    @Override
    protected void disposeEditor() {
        super.disposeEditor();
        pnlMain.setVisible(false);
    }

    @Override
    public JComponent getAnchor() {
        return anchor;
    }

    @Override
    public void setAnchor(@Nullable JComponent anchor) {
        this.anchor = anchor;
        parametersPanel.setAnchor(anchor);
    }

    private void createUIComponents() {
    }
}
