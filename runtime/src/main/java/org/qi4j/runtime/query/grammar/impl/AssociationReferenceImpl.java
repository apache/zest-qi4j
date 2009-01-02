/*
 * Copyright 2008 Alin Dreghiciu.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.qi4j.runtime.query.grammar.impl;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.qi4j.api.entity.association.Association;
import org.qi4j.api.entity.association.Qualifier;
import org.qi4j.api.query.grammar.AssociationReference;
import org.qi4j.api.util.Classes;
import org.qi4j.runtime.query.QueryException;

/**
 * Default {@link AssociationReference}.
 *
 * @author Alin Dreghiciu
 * @since March 28, 2008
 */
public class AssociationReferenceImpl
    implements AssociationReference
{

    /**
     * Association name.
     */
    private final String name;
    /**
     * Interface that declared the association.
     */
    private final Class<?> declaringType;
    /**
     * Association accessor method.
     */
    private final Method accessor;

    /**
     * Association type.
     */
    private final Type type;

    /**
     * Traversed association.
     */
    private final AssociationReference traversed;

    /**
     * If an association role was traversed or not, and if so, whether the association or role was accessed
     */
    private final ReferenceType referenceType;

    /**
     * Constructor.
     *
     * @param accessor  method that acts as association
     * @param traversed traversed association
     */
    public AssociationReferenceImpl( final Method accessor,
                                     final AssociationReference traversed,
                                     final ReferenceType referenceType )
    {
        this.accessor = accessor;
        this.referenceType = referenceType;
        name = accessor.getName();
        declaringType = accessor.getDeclaringClass();
        Type returnType = accessor.getGenericReturnType();
        if( !( returnType instanceof ParameterizedType ) )
        {
            throw new QueryException( "Unsupported association type:" + returnType );
        }
        Type associationTypeAsType = ( (ParameterizedType) returnType ).getActualTypeArguments()[ 0 ];
        if( !( associationTypeAsType instanceof Class ) && !Classes.getRawClass( associationTypeAsType ).equals( Qualifier.class ) )
        {
            throw new QueryException( "Unsupported association type:" + associationTypeAsType );
        }
        type = associationTypeAsType;
        this.traversed = traversed;
    }

    /**
     * @see AssociationReference#associationName()
     */
    public String associationName()
    {
        return name;
    }

    /**
     * @see AssociationReference#associationDeclaringType()
     */
    public Class<?> associationDeclaringType()
    {
        return declaringType;
    }

    /**
     * @see AssociationReference#associationAccessor()
     */
    public Method associationAccessor()
    {
        return accessor;
    }

    /**
     * @see AssociationReference#associationType()
     */
    public Type associationType()
    {
        return type;
    }

    /**
     * @see AssociationReference#traversedAssociation()
     */
    public AssociationReference traversedAssociation()
    {
        return traversed;
    }

    public ReferenceType roleType()
    {
        return referenceType;
    }

    public AssociationReference withQualifier( ReferenceType type )
    {
        return new AssociationReferenceImpl( accessor, traversed, type );
    }

    /**
     * @see AssociationReference#eval(Object)
     */
    public Object eval( final Object target )
    {
        Object actual = target;
        if( traversedAssociation() != null )
        {
            actual = traversedAssociation().eval( target );
        }
        if( actual != null )
        {
            try
            {
                Association assoc = (Association) associationAccessor().invoke( actual );
                if( referenceType == ReferenceType.NONE )
                {
                    return assoc.get();
                }
                else if( referenceType == ReferenceType.ASSOCIATION )
                {
                    return ( (Qualifier) assoc.get() ).entity();
                }
                else if( referenceType == ReferenceType.ROLE )
                {
                    return ( (Qualifier) assoc.get() ).qualifier();
                }
            }
            catch( Exception e )
            {
                return null;
            }
        }
        return null;
    }

    @Override
    public String toString()
    {
        StringBuilder fragment = new StringBuilder();
        if( traversed != null )
        {
            fragment.append( traversed.toString() ).append( "." );
        }
        fragment.append( declaringType.getSimpleName() );
        fragment.append( ":" );
        fragment.append( name );
        return fragment.toString();
    }

}