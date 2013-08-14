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

import org.w3c.dom.Node;

/**
 * A class for representing a Text-Node of a DOM (XML) document. This class also adds an additional doubly-linked-list
 * functionallity and it is used for creating a list of all the text nodes in the document
 */
public class TextSection {

    TextSection prev, next;

    final Node textNode;

    /**
     * Create a new text section. This method DOES NOT CONNECT the previous section with this one. For doing that, use
     * {@link InsertAfterMe}
     */
    public TextSection(TextSection prev, Node textNode) {
        this.prev = prev;

        this.next = null;
        this.textNode = textNode;
    }

    public Node getTextNode() {
        return textNode;
    }

    public String getTextContent() {
        return textNode.getTextContent();
    }

    public Node getParentNode() {
        return textNode.getParentNode();
    }

    /**
     * Check if this node is the last Text-Node inside it's parent. This method call assumes that all the TextSection
     * objects were initialized correctly, like StrXML does.
     */
    public boolean isNodeEnd() {
        if (next == null) {
            return true;
        }

        short position = getParentNode().compareDocumentPosition(next.getParentNode());

        if (position == Node.DOCUMENT_POSITION_CONTAINED_BY || position == Node.DOCUMENT_POSITION_DISCONNECTED
                || position == Node.DOCUMENT_POSITION_PRECEDING) {
            return true;
        } else if (position == Node.DOCUMENT_POSITION_CONTAINS) {
            return false;
        } else /* if (position == Node.DOCUMENT_POSITION_FOLLOWING || position == Node.DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC) */
        {
            throw new IllegalStateException("Bad position relation!");
        }
    }

    /**
     * Check if this node is the first Text-Node inside it's parent. This method call assumes that all the TextSection
     * objects were initialized correctly, like StrXML does.
     */
    public boolean isNodeBegin() {
        if (prev == null) {
            return true;
        } else {
            return prev.isNodeEnd();
        }
    }

    /**
     * Returns true if the text node associated with this section has the given node as parent.
     */
    public boolean isChildOf(Node parentNode) {
        Node parent = textNode.getParentNode();

        while (parent != null) {
            if (parent == parentNode) {
                return true;
            }

            parent = parent.getParentNode();
        }

        return false;
    }

    /**
     * Insert a {@link TextSection} object after the current one
     * 
     * @param newNext
     *            The {@link TextSection} to insert after the current one
     */
    public void insertAfterMe(TextSection newNext) {
        TextSection oldNext = this.next;

        this.next = newNext;
        newNext.prev = this;
        newNext.next = oldNext;

        if (oldNext != null) {
            oldNext.prev = newNext;
        }
    }

    /**
     * Split the current node in 2 pieces. The new node will be added directly after this one (and a pointer to it will
     * be returned). This should not be used directly! Use only through the interface provided by StrXML!!!
     * 
     * @param textOffset
     *            position where to split the text
     * @return the next node
     */
    TextSection splitSection(int textOffset) {
        // Create a new text node for the second half of the text
        Node nextTextNode = textNode.cloneNode(false);
        nextTextNode.setTextContent(getTextContent().substring(textOffset));

        // Now trim the text in the current node to the first half
        textNode.setNodeValue(getTextContent().substring(0, textOffset));

        // Finally, add that text node after the current one
        Node parent = getParentNode();
        parent.insertBefore(nextTextNode, textNode.getNextSibling());

        // Now, create an appropriate text section object
        TextSection theNext = new TextSection(this, nextTextNode);

        this.insertAfterMe(theNext);

        return theNext;
    }

    /**
     * Split the node which is the last child of <code>lastSplitOnChild</code>.
     * 
     * @param textOffset
     *            position where to split the text
     * @param lastSplitOnChild
     * @return the second part of the {@link TextSection} as created by the split
     */
    public TextSection splitSectionUpNotIncluding(int textOffset, Node lastSplitOnChild) {
        if (!Manipulator.isChildOrSame(lastSplitOnChild, textNode))
            throw new IllegalArgumentException("Not really a parent node!");

        Node childIter = lastSplitOnChild.getFirstChild();

        while (!Manipulator.isChildOrSame(childIter, textNode)) {
            childIter = childIter.getNextSibling();
        }

        return splitSectionUp(textOffset, childIter);
    }

    /**
     * Split the current node in 2 pieces, and also split all the parents up to a specified parent (including that
     * parent). The new text node will be added directly after this one (and a pointer to it will be returned).
     * 
     * @param textOffset
     *            position where to split the text
     * @return the next node
     */
    public TextSection splitSectionUp(int textOffset, Node lastSplitHere) {
        if (!Manipulator.isChildOrSame(lastSplitHere, textNode))
            throw new IllegalArgumentException("Not really a parent node!");

        Node cloneSRC = lastSplitHere.cloneNode(false);
        lastSplitHere.getParentNode().insertBefore(cloneSRC, lastSplitHere.getNextSibling());

        Node cloneIter = cloneSRC;
        Node sourceIter = lastSplitHere;

        while (sourceIter.hasChildNodes()) {
            Node childIter = sourceIter.getFirstChild(), nextChild;

            // Find the parent of the text node
            while (!Manipulator.isChildOrSame(childIter, textNode)) {
                childIter = childIter.getNextSibling();
            }

            // childIter is now the parent of the text node!

            // Add the parent to the clone
            cloneIter.appendChild(childIter.cloneNode(false));

            // Add everything after it to the "clone" and remove from the source
            while ((nextChild = childIter.getNextSibling()) != null) {
                cloneIter.appendChild(sourceIter.removeChild(nextChild));
            }

            // Now, we have the last child of the source node as a parent of
            // the text node, and same for the clone. So progress more downwards
            sourceIter = childIter;
            cloneIter = cloneIter.getFirstChild();
        }

        if (!sourceIter.isSameNode(textNode))
            throw new AssertionError();

        // When we finished splitting the parents, we should now be at the actual text nodes.
        // Note that none of the text sections was invalidated until now!
        TextSection theNext = new TextSection(this, cloneIter);
        cloneIter.setTextContent(cloneIter.getTextContent().substring(textOffset));
        sourceIter.setTextContent(sourceIter.getTextContent().substring(0, textOffset));

        this.insertAfterMe(theNext);

        return theNext;
    }

    private static void wrapWith(Node child, Node wrapper) {
        Node parent = child.getParentNode();
        parent.insertBefore(wrapper, child);
        parent.removeChild(child);
        wrapper.appendChild(child);
    }

    public void wrapWith(Node wrapper) {
        wrapWith(textNode, wrapper);
    }

    @Override
    public String toString() {
        return getTextContent() + " (child of " + getParentNode() + ")";
    }
}
