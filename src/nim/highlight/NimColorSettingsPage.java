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
package nim.highlight;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import nim.NimIcons;

import javax.swing.*;
import java.util.Map;

public class NimColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Bad Character", NimSyntaxHighlighter.ColorGroups.BAD_CHARACTER),
            new AttributesDescriptor("Block Comment", NimSyntaxHighlighter.ColorGroups.BLOCK_COMMENT),
            new AttributesDescriptor("Line Comment", NimSyntaxHighlighter.ColorGroups.LINE_COMMENT),
            new AttributesDescriptor("Keyword", NimSyntaxHighlighter.ColorGroups.KEYWORDS),
            new AttributesDescriptor("Identifier", NimSyntaxHighlighter.ColorGroups.IDENTIFIER),
            new AttributesDescriptor("Operation", NimSyntaxHighlighter.ColorGroups.OPERATIONS),
            new AttributesDescriptor("Number", NimSyntaxHighlighter.ColorGroups.NUMBER),
            new AttributesDescriptor("String", NimSyntaxHighlighter.ColorGroups.STRING),
            new AttributesDescriptor("Comma", NimSyntaxHighlighter.ColorGroups.COMMA),
            new AttributesDescriptor("Dot", NimSyntaxHighlighter.ColorGroups.DOT),
            new AttributesDescriptor("Semicolon", NimSyntaxHighlighter.ColorGroups.SEMICOLON),
            new AttributesDescriptor("Brace", NimSyntaxHighlighter.ColorGroups.BRACES),
            new AttributesDescriptor("Bracket", NimSyntaxHighlighter.ColorGroups.BRACKETS),
            new AttributesDescriptor("Parenthesis", NimSyntaxHighlighter.ColorGroups.PARENTHESES),
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return NimIcons.NIM_ICON_16;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new NimSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "using Gtk;\n" +
                "\n" +
                "/*\n" +
                "    New class. Block comment.\n" +
                "*/\n" +
                "public class SyncSample : Window {\n" +
                "\n" +
                "    private SpinButton spin_box;\n" +
                "    private Scale slider, array[20];\n" +
                "\n" +
                "    public SyncSample ( ) {\n" +
                "        this.title = \"Enter your age\";\n" +
                "        this.window_position = WindowPosition.CENTER;\n" +
                "        this.destroy.connect(Gtk.main_quit);\n" +
                "        set_default_size(300, 20); // line comment\n" +
                "\n" +
                "\n" +
                "        spin_box = new SpinButton.with_range (0, (130 * 2 + 1) % 55, 1);\n" +
                "        slider = new Scale.with_range (Orientation.HORIZONTAL, 0, 130, 1);\n" +
                "        spin_box.adjustment.value_changed.connect (() => {\n" +
                "            slider.adjustment.value = spin_box.adjustment.value;\n" +
                "        });\n" +
                "    }\n" +
                "}";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Nim";
    }
}
