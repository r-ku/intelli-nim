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
package nim.editor;

import com.intellij.lang.CodeDocumentationAwareCommenter;
import com.intellij.psi.PsiComment;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.Nullable;
import nim.grammar.parser.psi.NimTypes;

public class NimCommenter implements CodeDocumentationAwareCommenter {
    public String getLineCommentPrefix() {
        return "#";
    }

    public String getBlockCommentPrefix() {
        return null;//"/*";
    }

    public String getBlockCommentSuffix() {
        return null;//"*/";
    }

    public String getCommentedBlockCommentPrefix() {
        return null;
    }

    public String getCommentedBlockCommentSuffix() {
        return null;
    }

    @Nullable
    public IElementType getLineCommentTokenType() {
        return NimTypes.COMMENT;
    }

    @Nullable
    public IElementType getBlockCommentTokenType() {
        return null;//NimTypes.BLOCK_COMMENT;
    }

    public String getDocumentationCommentPrefix() {
        return "##";
    }

    public String getDocumentationCommentLinePrefix() {
        return "##";
    }

    public String getDocumentationCommentSuffix() {
        return "";
    }

    public boolean isDocumentationComment(final PsiComment element) {
        return false;

        // TODO: add doc comments support
        /*return element.getTokenType() == NimTypes.LINE_DOC_COMMENT ||
                element.getTokenType() == NimTypes.BLOCK_DOC_COMMENT;*/
    }

    @Nullable
    public IElementType getDocumentationCommentTokenType() {
        return null;

        // TODO: add doc comments support
        //return NimTypes.LINE_DOC_COMMENT;
    }
}
