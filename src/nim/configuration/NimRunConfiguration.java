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

import com.intellij.execution.CommonProgramRunConfigurationParameters;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.*;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.util.xmlb.XmlSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jdom.Element;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class NimRunConfiguration extends ModuleBasedConfiguration<NimModuleBasedConfiguration>
        implements CommonProgramRunConfigurationParameters {

    private String platform;
    private String arch;
    private boolean isDebug = true;

    private String programParameters;
    private String workingDir = "";
    private Map<String, String> envs = new LinkedHashMap<String, String>();
    private boolean passParentEnvs;

    public NimRunConfiguration(Project project, ConfigurationFactory configurationFactory) {
        super(new NimModuleBasedConfiguration(project), configurationFactory);
    }

    @Override
    public Collection<Module> getValidModules() {
        Module[] modules = ModuleManager.getInstance(getProject()).getModules();
        return Arrays.asList(modules);
    }

    @NotNull
    @Override
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new NimRunSettingsEditor(getProject());
    }

    @Nullable
    @Override
    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment env) throws ExecutionException {
        CommandLineState state = new NimRunCommandLineState(getProject(), env, this);
        return state;
    }

    public void setPlatform(@Nullable String platform) { this.platform = platform; }
    public @Nullable String getPlatform() { return platform; }

    public void setArch(@Nullable String arch) { this.arch = arch; }
    public @Nullable String getArch() { return arch; }

    public void setIsDebug(boolean isDebug) { this.isDebug = isDebug; }
    public boolean getIsDebug() { return isDebug; }

    @Override
    public void setProgramParameters(@Nullable String value) {
        this.programParameters = value;
    }
    @Override
    public @Nullable String getProgramParameters() {
        return programParameters;
    }

    @Override
    public void setWorkingDirectory(@Nullable String value) {
        this.workingDir = value;
    }
    @Override
    public @Nullable String getWorkingDirectory() {
        return this.workingDir;
    }

    @Override
    public void setEnvs(@NotNull Map<String, String> envs) {
        this.envs.clear();
        this.envs.putAll(envs);
    }

    @NotNull
    @Override
    public Map<String, String> getEnvs() {
        return envs;
    }

    @Override
    public void setPassParentEnvs(boolean passParentEnvs) {
        this.passParentEnvs = passParentEnvs;
    }

    @Override
    public boolean isPassParentEnvs() {
        return passParentEnvs;
    }

    public void writeExternal(final Element element) throws WriteExternalException {
        super.writeExternal(element);
        writeModule(element);
        XmlSerializer.serializeInto(this, element);
    }

    public void readExternal(final Element element) throws InvalidDataException {
        super.readExternal(element);
        readModule(element);
        XmlSerializer.deserializeInto(this, element);
    }
}
