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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Node;

/**
 * A class for manipulating XML documents. For now, this is mainly a place keeper for more serious stuff.
 */
public class Manipulator {

    static List<Node> constructPath(Node n) {
        LinkedList<Node> path = new LinkedList<Node>();
        path.addFirst(n);
        Node parent;
        while ((parent = n.getParentNode()) != null) {
            path.addFirst(n = parent);
        }
        return path;
    }

    static Node lowestCommonAncesstor(Node node1, Node node2) {
        List<Node> path1 = constructPath(node1), path2 = constructPath(node2);
        Iterator<Node> i1 = path1.iterator(), i2 = path2.iterator();
        Node n1 = null, n2 = null;

        // Need the == for the null case:
        while ((n1 == n2 || n1.isSameNode(n2)) && i1.hasNext() && i2.hasNext()) {
            n1 = i1.next();
            n2 = i2.next();
        }

        return n1.getParentNode();
    }

    /**
     * @return <code>true</code> if passed <code>child</code> is really a child of <code>parent</code> or itself.
     */
    static boolean isChildOrSame(Node parent, Node child) {
        while (child != null) {
            if (child.isSameNode(parent))
                return true;
            else
                child = child.getParentNode();
        }
    
        return false;
    }
}
