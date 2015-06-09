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

import com.intellij.openapi.compiler.CompileContext;
import com.intellij.openapi.compiler.CompileScope;
import com.intellij.openapi.compiler.CompileTask;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import nim.module.NimModuleType;

import java.util.ArrayList;
import java.util.List;

public class NimCompileTask implements CompileTask {

    @Override
    public boolean execute(CompileContext context) {
        List<Module> modules = getModulesToCompile(context.getCompileScope());
        for (Module module : modules) {
            if (!NimMakeModuleUtils.makeModule(context, module)) {
                return false;
            }
        }

        return true;
    }

    private static List<Module> getModulesToCompile(CompileScope scope) {
        final List<Module> result = new ArrayList<>();
        for (final Module module : scope.getAffectedModules()) {
            if (ModuleType.get(module) != NimModuleType.getInstance()) continue;
            result.add(module);
        }
        return result;
    }
}
