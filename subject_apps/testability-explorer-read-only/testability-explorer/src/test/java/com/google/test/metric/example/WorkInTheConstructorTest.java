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
package com.google.test.metric.example;

import com.google.test.metric.AutoFieldClearTestCase;
import com.google.test.metric.ClassCost;
import com.google.test.metric.ClassRepository;
import com.google.test.metric.JavaClassRepository;
import com.google.test.metric.MetricComputer;
import com.google.test.metric.example.ExpensiveConstructor.Cost2ToConstruct;
import com.google.test.metric.example.ExpensiveConstructor.ObjectInstantiationWorkInTheConstructor;
import com.google.test.metric.example.ExpensiveConstructor.StaticWorkInTheConstructor;
import com.google.test.metric.testing.MetricComputerBuilder;
import com.google.test.metric.testing.MetricComputerJavaDecorator;

public class WorkInTheConstructorTest extends AutoFieldClearTestCase {

  MetricComputerJavaDecorator decoratedComputer;

  @Override
  protected void setUp() throws Exception {
    ClassRepository repo = new JavaClassRepository();
    MetricComputer toDecorate = new MetricComputerBuilder().withClassRepository(repo).build();
    decoratedComputer = new MetricComputerJavaDecorator(toDecorate, repo);
  }

  public void testCostToConstructClassCost() throws Exception {
    ClassCost classCost = decoratedComputer.compute(Cost2ToConstruct.class);
    assertEquals(2, classCost.getHighestMethodComplexityCost());
    assertEquals(0, classCost.getHighestMethodGlobalCost());
    assertEquals(0, classCost.getTotalGlobalCost());
    assertEquals(2, classCost.getTotalComplexityCost());
  }

  public void testStaticWorkInTheConstructorClassCost() throws Exception {
    ClassCost classCost = decoratedComputer.compute(StaticWorkInTheConstructor.class);
    assertEquals(2, classCost.getHighestMethodComplexityCost());
    assertEquals(0, classCost.getHighestMethodGlobalCost());
    assertEquals(0, classCost.getTotalGlobalCost());
    assertEquals(2, classCost.getTotalComplexityCost());
  }

  public void testObjectInstantiationWorkInTheConstructorClassCost() throws Exception {
    ClassCost classCost = decoratedComputer.compute(ObjectInstantiationWorkInTheConstructor.class);
    assertEquals(2, classCost.getHighestMethodComplexityCost());
    assertEquals(0, classCost.getHighestMethodGlobalCost());
    assertEquals(0, classCost.getTotalGlobalCost());
    assertEquals(2, classCost.getTotalComplexityCost());
  }

}
