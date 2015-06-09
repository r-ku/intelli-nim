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

import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.openapi.project.Project;

public class NimModuleBasedConfiguration extends RunConfigurationModule {
    public NimModuleBasedConfiguration(Project project) {
        super(project);
    }
}
