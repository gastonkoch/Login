package com.login.login.logic;

import com.login.login.logic.User;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-02T18:46:30", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Rol.class)
public class Rol_ { 

    public static volatile SingularAttribute<Rol, String> rolDescription;
    public static volatile SingularAttribute<Rol, Integer> id;
    public static volatile ListAttribute<Rol, User> listUser;
    public static volatile SingularAttribute<Rol, String> rolName;

}