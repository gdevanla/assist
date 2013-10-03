/*
 * Copyright 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.test.metric.cpp;

import com.google.test.metric.ParameterInfo;
import com.google.test.metric.Visibility;
import com.google.test.metric.cpp.dom.FunctionDefinition;
import com.google.test.metric.cpp.dom.Node;

import java.util.ArrayList;
import java.util.List;

class FunctionDefinitionBuilder extends DefaultBuilder {

  private final Node parent;
  private Node node;
  private final int line;
  private final List<ParameterInfo> parameters = new ArrayList<ParameterInfo>();
  private final Visibility visibility;

  public FunctionDefinitionBuilder(Node parent, int line) {
    this.parent = parent;
    this.line = line;
    this.visibility = Visibility.PUBLIC;
  }

  public FunctionDefinitionBuilder(Node parent, int line, Visibility visibility) {
    this.parent = parent;
    this.line = line;
    this.visibility = visibility;
  }

  @Override
  public void functionDirectDeclarator(String name) {
    node = new FunctionDefinition(name, line, parameters, visibility);
    parent.addChild(node);
  }

  @Override
  public void endFunctionDefinition() {
    finished();
  }

  @Override
  public void beginParameterDeclaration() {
    pushBuilder(new ParameterDeclarationBuilder(parameters));
  }

  @Override
  public void directDeclarator(String id) {
  }

  @Override
  public void simpleTypeSpecifier(List<String> sts) {
  }

  @Override
  public void beginCompoundStatement() {
    pushBuilder(new StatementBuilder(node));
  }
}
