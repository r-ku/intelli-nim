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

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import nim.NimIcons;

import javax.swing.*;

public class NimRunConfigurationType implements ConfigurationType {
    public static final String ID = "NimRunConfigurationType";
    private final ConfigurationFactory configurationFactory;

    public NimRunConfigurationType() {
        configurationFactory = new NimConfigurationFactory(this);
    }

    @Override
    public String getDisplayName() {
        return "Nim";
    }

    @Override
    public String getConfigurationTypeDescription() {
        return "Nim";
    }

    @Override
    public Icon getIcon() {
        return NimIcons.NIM_ICON_16;
    }

    @NotNull
    @Override
    public String getId() {
        return ID;
    }

    @Override
    public ConfigurationFactory[] getConfigurationFactories() {
        return new ConfigurationFactory[] { configurationFactory };
    }

    private class NimConfigurationFactory extends ConfigurationFactory {
        protected NimConfigurationFactory(@NotNull ConfigurationType type) {
            super(type);
        }

        @Override
        public RunConfiguration createTemplateConfiguration(Project project) {
            return new NimRunConfiguration(project, this);
        }
    }
}
