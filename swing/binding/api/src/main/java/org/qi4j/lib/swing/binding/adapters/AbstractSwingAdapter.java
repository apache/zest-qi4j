/*
 * Copyright 2008 Lan Boon Ping. All rights Reserved.
 *
 * Licensed  under the  Apache License,  Version 2.0  (the "License");
 * you may not use  this file  except in  compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed  under the  License is distributed on an "AS IS" BASIS,
 * WITHOUT  WARRANTIES OR CONDITIONS  OF ANY KIND, either  express  or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.qi4j.lib.swing.binding.adapters;

import java.util.HashSet;
import java.util.Set;
import org.qi4j.lib.swing.binding.SwingAdapter;

public abstract class AbstractSwingAdapter implements SwingAdapter
{
    private Set<Capabilities> canHandle;

    public AbstractSwingAdapter()
    {
        canHandle = new HashSet<Capabilities>();

        canHandle( canHandle );
    }

    public final Set<Capabilities> canHandle()
    {
        return canHandle;
    }

    protected abstract void canHandle( Set<Capabilities> canHandle );

}
