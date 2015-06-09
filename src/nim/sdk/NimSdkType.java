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
package nim.sdk;

import com.intellij.openapi.projectRoots.*;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import nim.NimIcons;
import nim.compiler.NimCommandLineUtils;

import javax.swing.*;

public class NimSdkType extends SdkType {
    private NimSdkData _sdkData;

    public static NimSdkType getInstance() {
        return SdkType.findInstance(NimSdkType.class);
    }

    public NimSdkType() {
        super("NimSdk");
    }

    @Nullable
    @Override
    public String suggestHomePath() {
        return null;
    }

    @Override
    public boolean isValidSdkHome(String path) {
        NimSdkData data = NimCommandLineUtils.getSdkData(path);
        if (data != null) {
            return true;
        }

        return false;
    }

    @Override
    public String suggestSdkName(String currentSdkName, String sdkHome) {
        return "Nim SDK";
    }

    @Override
    public String getVersionString(@NotNull Sdk sdk) {
        return getVersionString(sdk.getHomePath());
    }

    @Nullable
    @Override
    public String getVersionString(String sdkHome) {
        NimSdkData data = NimCommandLineUtils.getSdkData(sdkHome);
        if (data != null) {
            return data.NIM_VERSION;
        }
        return "";
    }

    @Override
    public String getPresentableName() {
        return "Nim SDK";
    }

    @Nullable
    @Override
    public AdditionalDataConfigurable createAdditionalDataConfigurable(SdkModel sdkModel, SdkModificator sdkModificator) {
        return null;
    }

    @Override
    public void setupSdkPaths(@NotNull Sdk sdk) {
    }

    @Override
    public SdkAdditionalData loadAdditionalData(Element additional) {
        return null;
    }

    @Override
    public void saveAdditionalData(@NotNull SdkAdditionalData additionalData, @NotNull Element additional) {
    }

    @Override
    public Icon getIcon() {
        return NimIcons.NIM_ICON_16;
    }

    @Override
    public Icon getIconForAddAction() {
        return getIcon();
    }
}
