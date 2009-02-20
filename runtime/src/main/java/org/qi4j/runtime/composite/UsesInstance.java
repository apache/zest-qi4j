/*
 * Copyright (c) 2008, Rickard Öberg. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.qi4j.runtime.composite;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * JAVADOC
 */
public final class UsesInstance
{
    public static final UsesInstance NO_USES = new UsesInstance();

    private final Set<Object> uses = new HashSet<Object>();

    public void use( Object... objects )
    {
        uses.addAll( Arrays.asList( objects ) );
    }

    public Object useForType( Class<?> type )
    {
        for( Object use : uses )
        {
            if( use instanceof CompositeBuilderInstance )
            {
                CompositeBuilderInstance builder = (CompositeBuilderInstance) use;
                if( type.isAssignableFrom( builder.compositeType() ) )
                {
                    return builder.newInstance();
                }
            }

            if( type.isInstance( use ) )
            {
                return use;
            }
        }

        return null;
    }
}
