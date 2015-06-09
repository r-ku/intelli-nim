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

import com.intellij.ide.util.projectWizard.JavaModuleBuilder;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.projectRoots.SdkTypeId;
import nim.sdk.NimSdkType;

public class NimModuleBuilder extends JavaModuleBuilder {
    public NimModuleBuilder() {
    }

    @Override
    public ModuleType getModuleType() {
        return NimModuleType.getInstance();
    }

    @Override
    public boolean isSuitableSdkType(SdkTypeId sdkTypeId) {
        return sdkTypeId instanceof NimSdkType;
    }
}
