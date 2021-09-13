/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.2.5248.dba0a5744 modeling language!*/

package com.blades.main;

import java.util.function.Supplier;

// line 11 "../../../suppliers.ump"
public class RogueSupplier implements Supplier<Rogue> {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public RogueSupplier() {
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public void delete() {
	}

	// line 15 "../../../suppliers.ump"
	public Rogue get() {
		Rogue rogue = new Rogue(Rogue.nextName());
		rogue.addAspect(Vice.next());
		return rogue;
	}
}