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

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.CapturingProcessHandler;
import com.intellij.execution.process.ProcessOutput;

import java.io.File;
import java.nio.charset.Charset;

public class CommandLineUtils {
    public static ProcessOutput runCommand(String workDir, String command, String params) throws Exception {
        GeneralCommandLine cmd = new GeneralCommandLine();
        cmd.setWorkDirectory(workDir);
        cmd.setExePath(new File(workDir, command).getAbsolutePath());
        cmd.addParameters(params);

        ProcessOutput output = new CapturingProcessHandler(cmd.createProcess(),
                Charset.defaultCharset(), cmd.getCommandLineString()).runProcess();

        return output;
    }
}
