/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */
package org.apache.polygene.library.constraints;

import org.apache.commons.validator.routines.UrlValidator;
import org.apache.polygene.api.constraint.Constraint;
import org.apache.polygene.library.constraints.annotation.HostPort;

/**
 * Implement @HostPort constraint.
 */
public class HostPortConstraint
    implements Constraint<HostPort, String>
{
    private static final UrlValidator VALIDATOR = new UrlValidator( new String[]{ "http" } );

    @Override
    public boolean isValid( HostPort annotation, String value )
    {
        if( value.startsWith( "localhost" ) )
        {
            value = "localhost.my" + value.substring( 9 );
        }
        return VALIDATOR.isValid( "http://" + value );
    }
}
