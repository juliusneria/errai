/*
 * Copyright 2013 JBoss, by Red Hat, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.errai.otec;

/**
 * @author Mike Brock
 * @author Christian Sadilek <csadilek@redhat.com>
 */
public class StringState implements State<String> {
  public StringBuilder buffer = new StringBuilder();
  public String stateId = "<initial>";

  public StringState(final String buffer) {
    this.buffer = new StringBuilder(buffer);
  }

  public void insert(final int pos, final char data) {
    if (pos == buffer.length()) {
      buffer.append(String.valueOf(data));
    }
    else {
      buffer.insert(pos, String.valueOf(data));
    }
    updateStateId();
  }

  private void updateStateId() {
    stateId = buffer.toString();
  }

  public void insert(final int pos, final String data) {
    if (pos == buffer.length()) {
      buffer.append(data);
    }
    else {
      buffer.insert(pos, data);
    }
    updateStateId();
  }

  public void delete(final int pos) {
    buffer.delete(pos, pos + 1);
    updateStateId();
  }

  public void delete(final int pos, final int length) {
    buffer.delete(pos, pos + length);
    updateStateId();
  }

  @Override
  public String get() {
    return buffer.toString();
  }

  @Override
  public State<String> snapshot() {
    return new StringState(buffer.toString());
  }

  @SuppressWarnings("RedundantStringToString")
  @Override
  public void syncStateFrom(final State<String> fromState) {
    if (fromState instanceof StringState) {
      buffer.delete(0, buffer.length());
      buffer.append(fromState.get().toString());
      updateStateId();
    }
    else {
      throw new RuntimeException("cannot sync state with non-StringState");
    }
  }

  @Override
  public String getStateId() {
    return stateId;
  }
}
