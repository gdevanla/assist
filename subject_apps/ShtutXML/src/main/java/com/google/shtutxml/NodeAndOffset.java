package com.google.shtutxml;

import org.w3c.dom.Node;

/**
 * A class to represent a location inside the an StrXML object
 */
public class NodeAndOffset {

    /**
     * Offset of the text when counting text only directly inside this node
     */
    final int textOffsetInsideNode;

    /**
     * Offset of the text when counting all the text in the document
     */
    final int textGlobalOffset;

    /**
     * Offset of the text since the beginning of this section in the StrXML document
     */
    final int textSectionOffset;

    final TextSection textSection;

    NodeAndOffset(int textOffsetInsideNode, int textGlobalOffset, int textSectionOffset, TextSection textSection) {
        this.textOffsetInsideNode = textOffsetInsideNode;
        this.textGlobalOffset = textGlobalOffset;
        this.textSectionOffset = textSectionOffset;
        this.textSection = textSection;
    }

    public int getTextGlobalOffset() {
        return textGlobalOffset;
    }

    public String getTextContent() {
        return textSection.getTextContent();
    }

    public Node getParentNode() {
        return textSection.getParentNode();
    }

    public Node getTextNode() {
        return textSection.textNode;
    }

    @Override
    public String toString() {
        return getParentNode() + " at " + textOffsetInsideNode;
    }
}