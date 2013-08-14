/*
 * This file is a part of ShtutXML
 * (c) Barak Itkin <lightningismyname at gmail dot com>, 2011
 *
 * ShtutXML is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.google.shtutxml;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * An example {@link DepthDocumentVisitor} that can be used for printing an XML document structure. It ignored most XML
 * properties that are not simple text and elements. This class should obviously be used with <code>DEPTH_FIRST</code>
 * {@link VisitOrder} in order for printing to actually make sense.
 */
public class DocumentPrinter extends DepthDocumentVisitor {

    public DocumentPrinter() {
    }

    String mkIndent(int depth) {
        if (depth == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < depth - 1; i++) {
            result.append("| ");
        }
        return result.append("|-").toString();
    }

    @Override
    protected void visitDocument(Node n, int depth) {
        System.out.println(mkIndent(depth) + "DOCUMENT ROOT");
    }

    @Override
    protected void visitElement(Node n, int depth) {
        String suffix = n.hasChildNodes() ? "" : "/";

        StringBuilder attrs = new StringBuilder(" ");
        if (n.hasAttributes()) {
            NamedNodeMap nm = n.getAttributes();
            for (int i = 0; i < nm.getLength(); i++) {
                attrs.append(nm.item(i).getNodeName());
                attrs.append("=\"");
                attrs.append(nm.item(i).getNodeName());
                attrs.append("\" ");
            }
        }

        System.out.println(mkIndent(depth) + "<" + n.getNodeName() + attrs.toString() + suffix + ">");
    }

    @Override
    protected void visitText(Node n, int depth) {
        System.out.println(mkIndent(depth) + "Text: " + n.getTextContent());
    }

    @Override
    protected void visitComment(Node n, int depth) {
        System.out.println(mkIndent(depth) + "Comment: " + n.getTextContent());
    }
}
