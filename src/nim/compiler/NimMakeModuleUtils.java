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
package nim.compiler;

import com.intellij.execution.RunManager;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.compiler.CompileContext;
import com.intellij.openapi.compiler.CompilerMessageCategory;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ModuleRootManager;
import nim.configuration.NimRunConfiguration;
import nim.module.NimModuleComponent;

import java.io.File;

public class NimMakeModuleUtils {
    public static boolean makeModule(CompileContext context, Module module) {
        NimModuleComponent moduleComponent = NimModuleComponent.getInstance(module);
        NimModuleComponent.ModuleType moduleType = moduleComponent.getModuleType();

        ModuleRootManager moduleRootManager = ModuleRootManager.getInstance(module);
        Sdk sdk = moduleRootManager.getSdk();
        if (sdk == null) {
            context.addMessage(CompilerMessageCategory.ERROR, "Nim SDK must be selected.", null, -1, -1);
            return false;
        }

        RunnerAndConfigurationSettings selectedConfiguration = RunManager.getInstance(module.getProject()).getSelectedConfiguration();
        if (selectedConfiguration == null) {
            context.addMessage(CompilerMessageCategory.ERROR, "Nim run configuration must be selected.", null, -1, -1);
            return false;
        }
        RunConfiguration runConfiguration = selectedConfiguration.getConfiguration();
        if (runConfiguration == null) {
            context.addMessage(CompilerMessageCategory.ERROR, "Nim run configuration must be selected.", null, -1, -1);
            return false;
        }
        if (!(runConfiguration instanceof NimRunConfiguration)) {
            context.addMessage(CompilerMessageCategory.ERROR, "Selected run configuration must be Nim run configuration.", null, -1, -1);
            return false;
        }
        NimRunConfiguration nimRunConfiguration = (NimRunConfiguration)runConfiguration;

        return makeModule(context, module, moduleType, module.getModuleFilePath(), sdk, nimRunConfiguration);
    }

    private static boolean makeModule(CompileContext context,
                                      Module module,
                                      NimModuleComponent.ModuleType moduleType,
                                      String modulePath,
                                      Sdk sdk,
                                      NimRunConfiguration runConfiguration)
    {
        String sourceBaseDir = getSourceBaseDirFromModulePath(modulePath);

        return true;
    }

    private static String getSourceBaseDirFromModulePath(String modulePath) {
        File moduleFile = new File(modulePath);
        File parentFile = new File(moduleFile.getParentFile(), "src");
        return parentFile.toString();
    }
}
