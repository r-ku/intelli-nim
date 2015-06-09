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

import com.intellij.execution.process.ProcessOutput;
import nim.sdk.NimSdkData;

import java.io.File;
import java.util.List;

public class NimCommandLineUtils {
    public static NimSdkData getSdkData(String folder) {
        final NimSdkData sdkData = new NimSdkData();
        File fileSDK = new File(folder);
        sdkData.SDK_PATH = fileSDK.getAbsolutePath();
        sdkData.NIM_BIN_PATH = new File(fileSDK, "/bin").getAbsolutePath();
        sdkData.NIM_LIB_PATH = new File(fileSDK, "/lib").getAbsolutePath();

        if ( ! new File(sdkData.NIM_BIN_PATH + "/nim").exists())
            return null;

        if ( ! new File(sdkData.NIM_LIB_PATH + "/system.nim").exists()) {
            sdkData.NIM_LIB_PATH = sdkData.NIM_LIB_PATH + "/nim";
            if ( ! new File(sdkData.NIM_LIB_PATH + "/system.nim").exists()) {
                return null;
            }
        }
        sdkData.NIM_VERSION = getNimVersion(sdkData.NIM_BIN_PATH);
        if (sdkData.NIM_VERSION == null) {
            return null;
        }

        File[] files = fileSDK.listFiles();
        if (files == null)
            return null;

//        for (File f : files) {
//            NimSdkPlatformData platformData = getPlatformData(f);
//            if (platformData != null) {
//                sdkData.platforms.add(platformData);
//            }
//        }

        return sdkData;
    }

    private static String getNimVersion(String binPath) {
        try {
            ProcessOutput out = CommandLineUtils.runCommand(binPath, "nim", "--version");
            List<String> stdoutLines = out.getStdoutLines();
            final String marker = "Nim Compiler Version ";
            for (String line : stdoutLines) {
                if (line.startsWith(marker)) {
                    return line.substring(marker.length(), line.indexOf(" ", marker.length() + 1));
                }
            }
        } catch (Exception e) {
            return null;
        }

        return null;
    }

//    private static NimSdkPlatformData getPlatformData(File path) {
//        if (!path.isDirectory()) {
//            return null;
//        }
//
//        String parts[] = path.getName().split("_");
//        if (parts == null || parts.length != 2) {
//            return null;
//        }
//
//        NimSdkPlatformData data = new NimSdkPlatformData();
//        data.PLATFORM = parts[0];
//        data.ARCH = parts[1];
//        data.GCC_BIN_PATH = new File(path, "mingw/bin").getAbsolutePath();
//        data.GCC_VERSION = getGccVersion(data.GCC_BIN_PATH);
//        if (data.GCC_VERSION == null) {
//            return null;
//        }
//
//        return data;
//    }
//
//    private static String getGccVersion(String binPath) {
//        try {
//            ProcessOutput out = CommandLineUtils.runCommand(binPath, "gcc", "--version");
//            List<String> linesOut = out.getStdoutLines();
//            if (linesOut != null && linesOut.size() > 0) {
//                String line = linesOut.get(0);
//                if (line.startsWith("gcc ")) {
//                    return line.substring("gcc ".length());
//                }
//            }
//        } catch (Exception ex) {
//            return null;
//        }
//
//        return null;
//    }
}
