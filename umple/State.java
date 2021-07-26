/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.2.5248.dba0a5744 modeling language!*/

package com.blades.goap;
import java.util.*;
import java.util.function.Predicate;

/**
 * 
 * STATE
 * 
 */
// line 31 "../../../goap.ump"
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
  public static final String DO_ACTIVITY = "does activity";
  public static final String SHAKE_HOLD = "has hold";
  public static final String STAKE_CLAIM = "has claim";
  public static final String ADVANCE = "does advance";
  public static final String EXPOSE = "does expose";

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //State Attributes
  private String name;
  private Predicate predicate;
  private float cost;

  //State Associations
  private List<Action> roots;
  private List<Action> results;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public State(String aName, Predicate aPredicate)
  {
    name = aName;
    predicate = aPredicate;
    cost = 1f;
    roots = new ArrayList<Action>();
    results = new ArrayList<Action>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPredicate(Predicate aPredicate)
  {
    boolean wasSet = false;
    predicate = aPredicate;
    wasSet = true;
    return wasSet;
  }

  public boolean setCost(float aCost)
  {
    boolean wasSet = false;
    cost = aCost;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public Predicate getPredicate()
  {
    return predicate;
  }

  public float getCost()
  {
    return cost;
  }
  /* Code from template association_GetMany */
  public Action getRoot(int index)
  {
    Action aRoot = roots.get(index);
    return aRoot;
  }

  public List<Action> getRoots()
  {
    List<Action> newRoots = Collections.unmodifiableList(roots);
    return newRoots;
  }

  public int numberOfRoots()
  {
    int number = roots.size();
    return number;
  }

  public boolean hasRoots()
  {
    boolean has = roots.size() > 0;
    return has;
  }

  public int indexOfRoot(Action aRoot)
  {
    int index = roots.indexOf(aRoot);
    return index;
  }
  /* Code from template association_GetMany */
  public Action getResult(int index)
  {
    Action aResult = results.get(index);
    return aResult;
  }

  public List<Action> getResults()
  {
    List<Action> newResults = Collections.unmodifiableList(results);
    return newResults;
  }

  public int numberOfResults()
  {
    int number = results.size();
    return number;
  }

  public boolean hasResults()
  {
    boolean has = results.size() > 0;
    return has;
  }

  public int indexOfResult(Action aResult)
  {
    int index = results.indexOf(aResult);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRoots()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addRoot(Action aRoot)
  {
    boolean wasAdded = false;
    if (roots.contains(aRoot)) { return false; }
    roots.add(aRoot);
    if (aRoot.indexOfCondition(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aRoot.addCondition(this);
      if (!wasAdded)
      {
        roots.remove(aRoot);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeRoot(Action aRoot)
  {
    boolean wasRemoved = false;
    if (!roots.contains(aRoot))
    {
      return wasRemoved;
    }

    int oldIndex = roots.indexOf(aRoot);
    roots.remove(oldIndex);
    if (aRoot.indexOfCondition(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aRoot.removeCondition(this);
      if (!wasRemoved)
      {
        roots.add(oldIndex,aRoot);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRootAt(Action aRoot, int index)
  {  
    boolean wasAdded = false;
    if(addRoot(aRoot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRoots()) { index = numberOfRoots() - 1; }
      roots.remove(aRoot);
      roots.add(index, aRoot);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRootAt(Action aRoot, int index)
  {
    boolean wasAdded = false;
    if(roots.contains(aRoot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRoots()) { index = numberOfRoots() - 1; }
      roots.remove(aRoot);
      roots.add(index, aRoot);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRootAt(aRoot, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfResults()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addResult(Action aResult)
  {
    boolean wasAdded = false;
    if (results.contains(aResult)) { return false; }
    results.add(aResult);
    if (aResult.indexOfConsequence(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aResult.addConsequence(this);
      if (!wasAdded)
      {
        results.remove(aResult);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeResult(Action aResult)
  {
    boolean wasRemoved = false;
    if (!results.contains(aResult))
    {
      return wasRemoved;
    }

    int oldIndex = results.indexOf(aResult);
    results.remove(oldIndex);
    if (aResult.indexOfConsequence(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aResult.removeConsequence(this);
      if (!wasRemoved)
      {
        results.add(oldIndex,aResult);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addResultAt(Action aResult, int index)
  {  
    boolean wasAdded = false;
    if(addResult(aResult))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfResults()) { index = numberOfResults() - 1; }
      results.remove(aResult);
      results.add(index, aResult);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveResultAt(Action aResult, int index)
  {
    boolean wasAdded = false;
    if(results.contains(aResult))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfResults()) { index = numberOfResults() - 1; }
      results.remove(aResult);
      results.add(index, aResult);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addResultAt(aResult, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Action> copyOfRoots = new ArrayList<Action>(roots);
    roots.clear();
    for(Action aRoot : copyOfRoots)
    {
      aRoot.removeCondition(this);
    }
    ArrayList<Action> copyOfResults = new ArrayList<Action>(results);
    results.clear();
    for(Action aResult : copyOfResults)
    {
      aResult.removeConsequence(this);
    }
  }

  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "cost" + ":" + getCost()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "predicate" + "=" + (getPredicate() != null ? !getPredicate().equals(this)  ? getPredicate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}