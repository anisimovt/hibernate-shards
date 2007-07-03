/**
 * Copyright (C) 2007 Google Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.

 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
 */

package org.hibernate.shards.criteria;

import junit.framework.TestCase;

import org.hibernate.Criteria;
import org.hibernate.shards.defaultmock.CriteriaDefaultMock;

/**
 * @author maxr@google.com (Max Ross)
 */
public class CreateSubcriteriaEventTest extends TestCase {
  public void testOnEvent() {
    final Criteria[] calledWithCrit = {null};
    final SubcriteriaFactory[] calledWithFactory = {null};

    ShardedSubcriteriaImpl.SubcriteriaRegistrar provider = new ShardedSubcriteriaImpl.SubcriteriaRegistrar() {
      public void establishSubcriteria(
          Criteria parentCriteria,
          SubcriteriaFactory subcriteriaFactory) {
        calledWithCrit[0] = parentCriteria;
        calledWithFactory[0] = subcriteriaFactory;
      }
    };

    SubcriteriaFactory factory = new SubcriteriaFactoryDefaultMock();
    CreateSubcriteriaEvent cse = new CreateSubcriteriaEvent(factory, provider);
    Criteria crit = new CriteriaDefaultMock();
    cse.onEvent(crit);
    assertSame(crit, calledWithCrit[0]);
    assertSame(factory, calledWithFactory[0]);
  }
}
