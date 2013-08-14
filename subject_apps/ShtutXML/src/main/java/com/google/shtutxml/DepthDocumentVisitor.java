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

import java.util.LinkedList;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A class for defining a visitor over an (XML) document. This is a common base class which has a default implementation
 * for the behaviour when encountering each type of node (the default is "do nothing"). This class also contains
 * functionality to visit the entire document with no recursion. Classes that extend this class can override the
 * behaviour for different types of nodes. Note they MUST NOT try to visit the child nodes themselves! The
 * {@link #visit(org.w3c.dom.Node, VisitOrder)} method will call the visiting of the children automatically. The depth
 * parameter in all the visit functions says how deep is the current node compared to the root node given to the
 * visitor. 0 = the root node, and it increases by 1 for each level we "sink" (for each child we enter).
 */
public class DepthDocumentVisitor {

    /**
     * A class for defining the visiting order inside a Tree Graph
     */
    public static enum VisitOrder {

        /** Depth-First Search (DFS) */
        DEPTH_FIRST,
        /** Breadth-First Search (BFS) */
        BREADTH_FIRST
    }

    /**
     * Initialize the visiting of the document, from the root and to all of it's descendants. The order of the visiting
     * is configurable.
     * 
     * @param root
     *            The node that from it and downwards the document will be visited
     * @param order
     *            The visiting order
     */
    public final void visit(Node root, VisitOrder order) {

        LinkedList<Node> toVisit = new LinkedList<Node>();
        LinkedList<Integer> toVisitDepth = new LinkedList<Integer>();

        toVisit.add(root);
        toVisitDepth.add(Integer.valueOf(0));

        while (!toVisit.isEmpty()) {
            Node n = toVisit.removeFirst();
            int depth = toVisitDepth.removeFirst().intValue();

            decideVisit(n, depth);

            if (n.hasAttributes()) {
                NamedNodeMap nm = n.getAttributes();
                for (int i = nm.getLength() - 1; i >= 0; --i) {
                    toVisit.addFirst(nm.item(i));
                    toVisitDepth.addFirst(Integer.valueOf(depth + 1));
                }
            }

            if (n.hasChildNodes()) {
                NodeList nl = n.getChildNodes();

                if (order == VisitOrder.DEPTH_FIRST) {
                    for (int i = nl.getLength() - 1; i >= 0; --i) {
                        toVisit.addFirst(nl.item(i));
                        toVisitDepth.addFirst(Integer.valueOf(depth + 1));
                    }
                } else if (order == VisitOrder.BREADTH_FIRST) {
                    for (int i = 0; i < nl.getLength(); ++i) {
                        toVisit.addLast(nl.item(i));
                        toVisitDepth.addLast(Integer.valueOf(depth + 1));
                    }
                }
            }
        }
    }

    /**
     * Given a {@link Node}, this method will call the appropriate method of the visitor on it
     * 
     * @param n
     *            The node to visit
     * @param depth
     *            The current depth
     */
    private void decideVisit(Node n, int depth) {
        short type = n.getNodeType();

        if (type == Node.ATTRIBUTE_NODE) {
            visitAttr(n, depth);
        } else if (type == Node.CDATA_SECTION_NODE) {
            visitCDATA(n, depth);
        } else if (type == Node.COMMENT_NODE) {
            visitComment(n, depth);
        } else if (type == Node.DOCUMENT_NODE) {
            visitDocument(n, depth);
        } else if (type == Node.DOCUMENT_FRAGMENT_NODE) {
            visitDocumentFragment(n, depth);
        } else if (type == Node.DOCUMENT_TYPE_NODE) {
            visitDocumentType(n, depth);
        } else if (type == Node.ELEMENT_NODE) {
            visitElement(n, depth);
        } else if (type == Node.ENTITY_NODE) {
            visitEntity(n, depth);
        } else if (type == Node.ENTITY_REFERENCE_NODE) {
            visitEntityReference(n, depth);
        } else if (type == Node.NOTATION_NODE) {
            visitNotation(n, depth);
        } else if (type == Node.PROCESSING_INSTRUCTION_NODE) {
            visitProcessingInstruction(n, depth);
        } else if (type == Node.TEXT_NODE) {
            visitText(n, depth);
        } else {
            assert false : "Unknown type for org.w3c.node";
        }
    }

    protected void visitAttr(Node n, int depth) {
    }

    protected void visitCDATA(Node n, int depth) {
    }

    protected void visitComment(Node n, int depth) {
    }

    protected void visitDocument(Node n, int depth) {
    }

    protected void visitDocumentFragment(Node n, int depth) {
    }

    protected void visitDocumentType(Node n, int depth) {
    }

    protected void visitElement(Node n, int depth) {
    }

    protected void visitEntity(Node n, int depth) {
    }

    protected void visitEntityReference(Node n, int depth) {
    }

    protected void visitNotation(Node n, int depth) {
    }

    protected void visitProcessingInstruction(Node n, int depth) {
    }

    protected void visitText(Node n, int depth) {
    }
}
