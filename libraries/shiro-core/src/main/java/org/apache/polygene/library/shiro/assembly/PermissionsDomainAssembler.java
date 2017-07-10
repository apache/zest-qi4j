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
package org.apache.polygene.library.shiro.assembly;

import org.apache.polygene.bootstrap.Assemblers;
import org.apache.polygene.bootstrap.ModuleAssembly;
import org.apache.polygene.library.shiro.domain.permissions.Role;
import org.apache.polygene.library.shiro.domain.permissions.RoleAssignee;
import org.apache.polygene.library.shiro.domain.permissions.RoleAssignment;
import org.apache.polygene.library.shiro.domain.permissions.RoleFactory;

public class PermissionsDomainAssembler
    extends Assemblers.Visibility<PermissionsDomainAssembler>
{
    @Override
    public void assemble( ModuleAssembly module )
    {
        super.assemble( module );
        module.entities( Role.class, RoleAssignment.class, RoleAssignee.class ).
            visibleIn( visibility() );
        module.services( RoleFactory.class ).
            visibleIn( visibility() );
    }
}
