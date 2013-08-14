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

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * A class to represent a structured XML document, in the form that is needed by a WYSWYG editor. What it has is a list
 * of strings, each one associated with a {@link Node} which is it's direct parent.
 */
public class StrXML {

    private TextSection firstSection, lastSection;

    public StrXML(File file) throws ParserConfigurationException, SAXException, IOException {
        this(parse(file));
    }

    public StrXML(Document doc) {
        new DepthDocumentVisitor() {

            @Override
            protected void visitText(Node n, int depth) {
                // Skip text in attributes:
                if (n.getParentNode() != null && n.getParentNode().getNodeType() == Node.ATTRIBUTE_NODE) {
                    return;
                }

                add(n);
            }
        }.visit(doc, DepthDocumentVisitor.VisitOrder.DEPTH_FIRST);
    }

    private static Document parse(File file) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        return db.parse(file);
    }

    /**
     * For construction
     * 
     * @param node
     *            text node
     */
    void add(Node node) {
        TextSection newSection = new TextSection(lastSection, node);

        // The the last is also null
        if (firstSection == null) {
            firstSection = lastSection = newSection;
        } else {
            lastSection.insertAfterMe(newSection);
            lastSection = lastSection.next;
        }
    }

    public NodeAndOffset getOffset(int globalOffset) {
        int sectionOffset = globalOffset;
        int inNodeOffset = 0;
        TextSection where = null;

        for (TextSection section = this.firstSection; section != null; section = section.next) {
            if (sectionOffset < section.getTextContent().length()) {
                where = section;
                break;
            } else {
                sectionOffset -= section.getTextContent().length();
            }
        }

        for (TextSection section = this.firstSection; section != null; section = section.next) {
            if (section == where) {
                break;
            } else if (section.getParentNode() == where.getParentNode()
                    || section.getParentNode().isSameNode(where.getParentNode())) {
                inNodeOffset += section.getTextContent().length();
            }
        }

        return new NodeAndOffset(inNodeOffset + sectionOffset, globalOffset, sectionOffset, where);
    }

    /**
     * By given <code>node</code> returns its start and end offset in text stream.
     */
    public int[] getSpan(Node node) {
        int startOffset = 0;
        int endOffset = 0;

        TextSection section = this.firstSection;

        for (; section != null && !section.isChildOf(node); section = section.next) {
            startOffset += section.getTextContent().length();
        }

        if (section == null) {
            throw new IllegalArgumentException("Node " + node + " does not hold any text");
        }

        endOffset = startOffset;

        for (; section != null && section.isChildOf(node); section = section.next) {
            endOffset += section.getTextContent().length();
        }

        return new int[] { startOffset, endOffset };
    }

    /**
     * Return all the text of the given XML document, without any XML tags or other stuff. Just the text content.
     * 
     * @return A string with the XML's text content
     */
    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (TextSection section = this.firstSection; section != null; section = section.next) {
            sb.append(section.getTextContent());
        }
        return sb.toString();
    }

    private TextSection splitSection(NodeAndOffset nao) {
        TextSection next = nao.textSection.splitSection(nao.textSectionOffset);
        if (nao.textSection == lastSection) {
            lastSection = next;
        }
        return next;
    }

    private TextSection splitSectionUpTo(NodeAndOffset nao, Node lastSplitBeforHere) {
        TextSection next = nao.textSection.splitSectionUp(nao.textSectionOffset, lastSplitBeforHere);
        if (nao.textSection == lastSection) {
            lastSection = next;
        }
        return next;
    }

    private TextSection splitSectionUpToNotIncluding(NodeAndOffset nao, Node lastSplitOnChild) {
        TextSection next = nao.textSection.splitSectionUpNotIncluding(nao.textSectionOffset, lastSplitOnChild);
        if (nao.textSection == lastSection) {
            lastSection = next;
        }
        return next;
    }

    public void insertAndSplitInserted(Node n, NodeAndOffset start, NodeAndOffset end) {
        if (n.getOwnerDocument() != firstSection.getTextNode().getOwnerDocument()
                || start.getTextNode().getOwnerDocument() != firstSection.getTextNode().getOwnerDocument()
                || end.getTextNode().getOwnerDocument() != firstSection.getTextNode().getOwnerDocument()) {
            throw new IllegalArgumentException("Position is given from another document");
        }

        TextSection startSection = start.textSection, endSection = end.textSection;
        int startOffset = start.textOffsetInsideNode;
        int endOffset = end.textOffsetInsideNode;

        if (startSection == endSection) {
            TextSection startSplit = startSection.splitSection(startOffset);
            TextSection middleSplit = startSplit.splitSection(endOffset - startOffset);

            startSplit.wrapWith(n.cloneNode(false));

            if (endSection == lastSection) {
                lastSection = middleSplit;
            }
        } else {
            TextSection startSplit = startSection.splitSection(startOffset);

            startSplit.wrapWith(n);
            startSection = startSplit.next;

            while (startSection != endSection) {
                startSection.wrapWith(n.cloneNode(false));
                startSection = startSection.next;
            }

            startSplit = startSection.splitSection(endOffset);
            startSection.wrapWith(n.cloneNode(false));

            if (startSection == lastSection) {
                lastSection = startSplit;
            }
        }
    }

    public void insertAndSplitParent(Node n, NodeAndOffset start, NodeAndOffset end) {
        if (n.getOwnerDocument() != firstSection.getTextNode().getOwnerDocument()
                || start.getTextNode().getOwnerDocument() != firstSection.getTextNode().getOwnerDocument()
                || end.getTextNode().getOwnerDocument() != firstSection.getTextNode().getOwnerDocument()) {
            throw new IllegalArgumentException("Position is given from another document");
        }

        TextSection startSection = start.textSection, endSection = end.textSection;

        Node lca = Manipulator.lowestCommonAncesstor(startSection.getParentNode(), endSection.getParentNode());

        TextSection firstSection = splitSectionUpToNotIncluding(start, lca);
        end = getOffset(end.textGlobalOffset);
        TextSection afterLastSection = splitSectionUpToNotIncluding(end, lca);

        NodeList children = lca.getChildNodes();
        boolean foundFirst = false;
        boolean foundAfterLast = false;

        Node child = null;

        for (int i = 0; i < children.getLength(); i++) {
            child = children.item(i);
            if (!foundFirst)
                foundFirst = Manipulator.isChildOrSame(child, firstSection.textNode);
            else
                foundAfterLast = Manipulator.isChildOrSame(child, afterLastSection.textNode);

            if (foundAfterLast)
                break;
            else if (foundFirst) {
                n.appendChild(child);
                // We removed a child, so we must refresh the children list
                children = lca.getChildNodes();
                i--;
            }
        }

        if (foundAfterLast)
            lca.insertBefore(n, child);
        else
            lca.appendChild(n);

        if (endSection == lastSection)
            lastSection = afterLastSection;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (TextSection section = this.firstSection; section != null; section = section.next) {
            sb.append(section.getParentNode());
            sb.append(": ");
            sb.append(section.getTextContent());
            sb.append("\n");
        }

        return sb.toString();
    }
}
