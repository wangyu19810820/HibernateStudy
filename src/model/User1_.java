package model;

import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel(User1.class)
public abstract class User1_ {
	public static volatile SingularAttribute<User1, Integer> id;
	public static volatile SingularAttribute<User1, String> username;
	public static volatile SingularAttribute<User1, String> password;
	public static volatile SingularAttribute<User1, String> birthday;
}
