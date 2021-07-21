/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.2.5248.dba0a5744 modeling language!*/

package com.blades.goap;

/**
 * 
 * STATE
 * 
 */
// line 57 "../../../goap.ump"
public class State
{

  //------------------------
  // STATIC VARIABLES
  //------------------------


  /**
   * there isn't an effective English verb for "being somewhere" that doesn't collide with the word "being" which is already in use; I tried put/putting and decided to use place/placing to reflect that it relates to "being placed"
   */
  public static final String BEING = "is";
  public static final String GETTING = "gets";
  public static final String DOING = "does";
  public static final String PUTTING = "puts";

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //State Attributes
  private float cost;

  //State Associations
  private String predicate;
  private Boolean value;

  //Helper Variables
  private int cachedHashCode;
  private Boolean canSetPredicate;
  private Boolean canSetValue;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public State(String aPredicate, Boolean aValue)
  {
    cachedHashCode = -1;
    canSetPredicate = true;
    canSetValue = true;
    cost = 1f;
    if (!setPredicate(aPredicate))
    {
      throw new RuntimeException("Unable to create State due to aPredicate. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setValue(aValue))
    {
      throw new RuntimeException("Unable to create State due to aValue. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Boolean setCost(float aCost)
  {
    Boolean wasSet = false;
    cost = aCost;
    wasSet = true;
    return wasSet;
  }

  public float getCost()
  {
    return cost;
  }
  /* Code from template association_GetOne */
  public String getPredicate()
  {
    return predicate;
  }
  /* Code from template association_GetOne */
  public Boolean getValue()
  {
    return value;
  }
  /* Code from template association_SetUnidirectionalOne */
  public Boolean setPredicate(String aNewPredicate)
  {
    Boolean wasSet = false;
    if (!canSetPredicate) { return false; }
    if (aNewPredicate != null)
    {
      predicate = aNewPredicate;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public Boolean setValue(Boolean aNewValue)
  {
    Boolean wasSet = false;
    if (!canSetValue) { return false; }
    if (aNewValue != null)
    {
      value = aNewValue;
      wasSet = true;
    }
    return wasSet;
  }

  public boolean equals(Object obj)
  {
    if (obj == null) { return false; }
    if (!getClass().equals(obj.getClass())) { return false; }

    State compareTo = (State)obj;
  
    if (getPredicate() == null && compareTo.getPredicate() != null)
    {
      return false;
    }
    else if (getPredicate() != null && !getPredicate().equals(compareTo.getPredicate()))
    {
      return false;
    }

    if (getValue() == null && compareTo.getValue() != null)
    {
      return false;
    }
    else if (getValue() != null && !getValue().equals(compareTo.getValue()))
    {
      return false;
    }

    return true;
  }

  public int hashCode()
  {
    if (cachedHashCode != -1)
    {
      return cachedHashCode;
    }
    cachedHashCode = 17;
    if (getPredicate() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getPredicate().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }
    if (getValue() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getValue().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    canSetPredicate = false;
    canSetValue = false;
    return cachedHashCode;
  }

  public void delete()
  {
    predicate = null;
    value = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "cost" + ":" + getCost()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "predicate = "+(getPredicate()!=null?Integer.toHexString(System.identityHashCode(getPredicate())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "value = "+(getValue()!=null?Integer.toHexString(System.identityHashCode(getValue())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 65 ../../../goap.ump
  Boolean is() { return predicate.startsWith(BEING); }
	Boolean gets() { return predicate.startsWith(GETTING); }
	Boolean does() { return predicate.startsWith(DOING); }
	Boolean puts() { return predicate.startsWith(PUTTING); }
  
}