/*
 * Copyright (c) 2008, Niclas Hedhman. All Rights Reserved.
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
package org.qi4j.quikit.pages;

import org.apache.wicket.markup.html.WebPage;
import org.qi4j.injection.scope.Structure;
import org.qi4j.object.ObjectBuilder;
import org.qi4j.object.ObjectBuilderFactory;
import org.qi4j.quikit.panels.EntityFormEditPanel;

public class EntityFormEditPage extends WebPage
{
    private static final long serialVersionUID = 1L;

    private static final String WICKET_ID_EDIT_PANEL = "edit-panel";

    public EntityFormEditPage( @Structure ObjectBuilderFactory objectBuilderFactory )
    {
        ObjectBuilder<EntityFormEditPanel> builder = objectBuilderFactory.newObjectBuilder( EntityFormEditPanel.class );
        builder.use( WICKET_ID_EDIT_PANEL );
        EntityFormEditPanel panel = builder.newInstance();
        add( panel );
    }
}
