/**
 *This is the Quantity class
 * @author Xirui Li(CS12fhm,A13691309)
 * @version 8 December 2017
 */
package hw8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.text.DecimalFormat;

public class Quantity {
	
    public double value;
    public Map<String,Integer> units;
    
    /**
     * A no-argument constructor that creates a default quantity of value 1 and no units.
     */
    public Quantity() {
		this.value = 1.0;
		this.units = new HashMap<String, Integer>();
	}
    
    /**
     * A 3-argument constructor  
     * @param a double (the numeric value);
     * @param a List<String> of the units in the numerator (i.e., with positive exponents);
     * @param a List<String> of the units in the denominator (i.e., the units with negative exponents).
     * @throws If either of the list arguments is null, this method should throw an IllegalArgumentException.
     */
	public Quantity(double value, List<String> numeUni, List<String> denoUni) throws IllegalArgumentException {
		// throw exception
		if( numeUni == null || denoUni == null){
	    	 throw new IllegalArgumentException();
	        }
		// set the value 
		this.value = value;
		this.units = new HashMap<String, Integer>();
		
		// set the numerator unit of the quantity
	    for(int i = 0; i < numeUni. size(); i++){
	    	String numeUnit = numeUni.get(i);
	    	if(this.units.containsKey(numeUnit)){
	    		this.units.put(numeUnit,units.get(numeUnit)+1); 
	    	}
	    	else{
	    		this.units.put(numeUnit,1);
	        }
	    }
	    // set the denominator unit of the quantity
		for(int j = 0; j < denoUni.size(); j++){
		    String denoUnit = denoUni.get(j);
		    if(this.units.containsKey(denoUnit)){
		        int currPower = this.units.get(denoUnit) - 1;
		        // check if the power of the unit is 0, if it so remove it
		        if(currPower == 0){
		        	this.units.remove(denoUnit);
		        }
		        else{
		    	units.put(denoUnit,currPower); 
		        }
		    }
		    else{
		    	units.put(denoUnit,-1);
		    }
	    }
 
	}
		
	/**
	 * A constructor that takes a single Quantity argument, and creates a deep copy.  
	 * @param the Quantity to copy 
	 */
	public Quantity(Quantity toCopy) {
		//assign the value
		this.value = toCopy.value;
	    // deepcopy the hashTable
		this.units = new HashMap<String, Integer>(toCopy.units);
	}
	
	/**
	 * A method toString() that returns the quantity as a String.
	 */
	public String toString()
	{
	  // XXX You will need to fix these lines to match your fields!
	  double valueOfTheQuantity = this.value;
	  Map<String,Integer> mapOfTheQuantity = this.units;
	 
	  // Ensure we get the units in order
	  TreeSet<String> orderedUnits = new TreeSet<String>(mapOfTheQuantity.keySet());
	    
	  StringBuffer unitsString = new StringBuffer();
	    
	  for (String key : orderedUnits) {
	    int expt = mapOfTheQuantity.get(key);
	    unitsString.append(" " + key);
	    if (expt != 1)
	         unitsString.append("^" + expt);
	  }
	    
	  // Used to convert doubles to a string with a 
	  //   fixed maximum number of decimal places.
	  DecimalFormat df = new DecimalFormat("0.0####");
	  // Put it all together and return.
	  return df.format(valueOfTheQuantity) + unitsString.toString();
	}
	
	/**
	 * A method mul that  multiplies this by the argument, and returns the result. 
	 * @param takes a single Quantity argument as multiplier
	 * @return The returned value should be a brand new Quantity object
	 * @throws an IllegalArgumentException if its argument is null
	 */
	public Quantity mul(Quantity toMulWith) throws IllegalArgumentException {
		//check exceptions
		if( toMulWith == null || toMulWith.value == 0){
	    	 throw new IllegalArgumentException();
	        }
		
		// Deepcopy the Multiplicand quantity
		Quantity result = new Quantity(this);
		
		//result value = Multiplicand's(this) value * Multiplier's (toMulWith's) value
		double newValue = toMulWith.value * this.value;
		result.value = newValue;
		
		//result value = Multiplicand's(this) units * Multiplier's (toMulWith's) units
		Set<String> toMulUnits = toMulWith.units.keySet();
		for(String divUnit : toMulUnits){
			int powerToMul = toMulWith.units.get(divUnit);
	
		    if(result.units.containsKey(divUnit)){
		    	// change the unit's power if
		    	int powerCurr = result.units.get(divUnit);
		    	int powerResult = powerCurr + powerToMul;
		    	//remove the unit if its power is 0
		    	if(powerResult == 0){
		    		result.units.remove(divUnit);
		    	}
		    	else{
		            result.units.put(divUnit, powerResult);
		    	}
		    }
		    else{
		    	// add the unit 
		    	result.units.put(divUnit,powerToMul);
		    }
		}

		return result;
	}
	
    /**
     * A method that takes a single Quantity argument, divides this by the argument, and returns the result.
     * @param toDivWith
     * @return a brand new Quantity object; neither this quantity nor the argument quantity should change
     * @throws IllegalArgumentException if its argument is null or if the value in the Quantity argument is zero
     */
	public Quantity div(Quantity toDivWith) throws IllegalArgumentException{
		if( toDivWith == null || toDivWith.value == 0){
	    	 throw new IllegalArgumentException();
	        }
		// Deepcopy the Dividend 
		Quantity result = new Quantity(this);
		
		//result value = divident's(this) value / divisor's (toMulWith's) value
		double newValue = this.value / toDivWith.value;
		result.value = newValue;

		//result value = divident's(this) units / divisor's (toMulWith's) units
		Set<String> toDivUnits = toDivWith.units.keySet();
		for(String divUnit : toDivUnits){
			int powerToDiv = toDivWith.units.get(divUnit);
		    if(result.units.containsKey(divUnit)){
		    	// if contains the unit adjust it
		    	int powerCurr = result.units.get(divUnit);
		    	int powerResult = powerCurr - powerToDiv;
		    	//remove the unit if its power is 0
		    	if(powerResult == 0){
		    		result.units.remove(divUnit);
		    	}
		    	else{
		            result.units.put(divUnit, powerResult);
		    	}
		    }
		    //add the units
		    else{
		    	int powerCurr = 0 - powerToDiv;
		    	result.units.put(divUnit,powerCurr);
		    }
		}
		return result;
	}

	/**
	 * A method pow that raises this to the given power,this quantity should not change! 
	 * @param a single int argument  (positive, negative, or zero!)
	 * @returnThe result should be a brand new Quantity object
	 */
	public Quantity pow(int power) {
		// Deepcopy this quantity
		Quantity result = new Quantity(this);
		
		// //if power = 0, the result = 1;
		if(power == 0){
		result.value = 1;
		result.units = new HashMap<String, Integer>(); 
		}
		
		//if power=n > 0, mul (n-1)times
		if(power > 0){
			for (int i = 1; i < power; i++){
				result = result.mul(this);
			}
		}
		
		//if power=n < 0, div (n-1)times
		if(power < 0){
			for (int i = 1; i > power; i--){
				result = result.div(this);
			}
	    }
		return result;
	}
    
	/**
	 * A method add that takes a single Quantity argument, adds this to it
	 * @param toAddWith a single Quantity argument
	 * @return the result
	 * @throws IllegalArgumentException argument is null or if the two Quantity objects do not have the same units
	 */
	public Quantity add (Quantity toAddWith)throws IllegalArgumentException{
		//check exceptions
		if( toAddWith == null || !this.units.equals(toAddWith.units)){
	    	 throw new IllegalArgumentException();
	        }
		//Deep copy this quantity
		Quantity result = new Quantity(this);
		
		//Change the value
		result.value = this.value + toAddWith.value;
	    return result;
	}
    
	/**
	 * A method sub that takes a single Quantity argument, subtracts it from this, and returns the result.
	 * @param toSubWith a single Quantity argument
	 * @return returns the result
	 * @throws IllegalArgumentException if its argument is null or if the two Quantity objects do not have the same units
	 */
	public Quantity sub(Quantity toSubWith)throws IllegalArgumentException{
		//check exceptions
		if( toSubWith == null || !this.units.equals(toSubWith.units)){
	    	 throw new IllegalArgumentException();
	        }
		
		//Deep copy this quantity
		Quantity result = new Quantity(this);
		
		//Change the value
		result.value = this.value - toSubWith.value;
	    return result;
	}

	/**
	 * A method negate that takes no arguments and returns a new Quantity which is the negation of this Quantity.
	 * @return a new Quantity which is the negation of this Quantity.
	 */
	public Quantity negate() {
		//deepcopy this quantity
		Quantity result = new Quantity(this);
		
		//times the quantity by -1
		result.value = -1 * result.value;
		return result;
	}
    
	/**
	 *Normalize a non-basic unit with 
	 * base case: unit is not contain in the database
	 * Recursive case: get quantity q from the db, return q.normalize() 
	 * @param needChange
	 * @param db
	 * @return
	 */
	public static Quantity normalizedUnit(String unitToNoramlized, Map<String, Quantity> dataBase) {
    	List<String> emp = new ArrayList<String>();  
    	//Construct a quantity with value 1 of the unit we are checking
		Quantity result = new Quantity(1.0, Arrays.asList(unitToNoramlized), emp);
		// If it's not in the simplest units recursively call normalize
		if(dataBase.containsKey(unitToNoramlized)){
			result = dataBase.get(unitToNoramlized); 
			return result.normalize(dataBase); 
		}
		//base case: unit is not contain in the database
		return result;
	}
	
    /**
     * Normalize the quantity to basic units
     * value * normalized units (split the units)
     * @param the database store all the units
     * @return
     */
	public Quantity normalize(Map<String, Quantity> dataBase) {
		//deep copy this quantity
		Quantity result = new Quantity(this);
		// get all the units we need to normalize
		Set<String> unitSet = this.units.keySet();
		for(String unitToCheck : unitSet){
			//get the power of the unit
			int Power = this.units.get(unitToCheck);
			//find the corresponding value and unit in basic form of the unit we are checking
			Quantity uniConverted = Quantity.normalizedUnit(unitToCheck, dataBase);
			//power up 
			Quantity uniConvertedPowered = uniConverted.pow(Power);
			//remove the old unit
			result.units.remove(unitToCheck);
			//multiply in the new units
			result = result.mul(uniConvertedPowered);
		}
		return result;
	}

    @Override
    // use the string to do the hashcode
    public int hashCode(){
		return this.toString().hashCode(); 	
    }

    @Override
    /**
     * A boolean method equals that takes any single Object 
     * @returns true if and only if that object is a Quantity whose units are exactly the same as the calling object and whose value is the same when rounded to five places after the decimal point.
     */
    public boolean equals(Object Q) throws IllegalArgumentException{
    	boolean equal = false;
    	
    	//check exceptions
    	if(Q == null){
    		throw new IllegalArgumentException();
    	}
    	
    	// check by the string
    	Quantity toCheck = (Quantity)Q;
    	if(Q instanceof Quantity){
    	      if(toCheck.toString().equals(this.toString())){
    	    	  equal = true;
    	      }
    	      else{
    	    	  equal = false;
    	      }
    	}
    	return equal;
    		
    }

}
