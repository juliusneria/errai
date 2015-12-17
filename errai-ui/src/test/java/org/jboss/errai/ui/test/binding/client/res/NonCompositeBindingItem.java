/**
 * Copyright (C) 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.errai.ui.test.binding.client.res;

import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.databinding.client.api.InitialState;
import org.jboss.errai.ui.shared.api.annotations.AutoBound;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.jboss.errai.ui.test.common.client.TestModel;

import com.google.gwt.user.client.ui.TextBox;

@Templated("BindingTemplate.html")
public class NonCompositeBindingItem implements BindingItem {

  @Inject @Bound @DataField
  private TextBox name;

  @Inject @AutoBound
  private DataBinder<TestModel> binder;

  private int num;

  @Override
  public TextBox getTextBox() {
    return name;
  }

  @Override
  public TestModel getModel() {
    return binder.getModel();
  }

  @Override
  public void setModel(TestModel model) {
    binder.setModel(model, InitialState.FROM_MODEL);
  }

  @Override
  @PreDestroy
  public void testDestroy() {
    num++;
  }

  @Override
  public int getNum() {
    return num;
  }
}