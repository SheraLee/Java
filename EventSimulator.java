/**
 * Xirui Li(CS12fhm,A13691309) Xinyang Yu(cs12fir,A14188323)
 */
package hw6;

import java.util.*;

/** A class to implement a discrete event simulation. 
 * TODO: Add your comments here
 */
public class EventSimulator
{
    private Queue<Event> eventQueue;  // The event queue
    private Queue<Customer> waitLine;  // The waiting customers
    private Random rand;  // The random number generator for the simulation
  
    /** Constructor.  Create a new event simulator with a fixed 
     * random see to start */
    public EventSimulator( int rseed )
    {
        eventQueue = new HeapPQ12<Event>();
        waitLine = new LinkedList<Customer>();
       // waitLine = new HeapPQ12<Customer>();
        if (rseed == -1) {
            rand = new Random();
        }
        else {
            rand = new Random(rseed);
        }
    }
    
    /* Algorithm by D. Knuth */
    private int getPoissonRandom(double mean) {
        double L = Math.exp(-mean);
        int k = 0;
        double p = 1.0;
        do {p = p * rand.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }   
  
    /** Get a random integer from a Poisson distribution with the specified mean */
    private int getRandomInt( int mean ) 
    {
        return getPoissonRandom((double)mean);
    }
  
    /** Create one new arrival event per customer 
     *  @param numCustomers The number of Customers in the simulation.
     *  @param arrivalMean The mean time between arrivals.
     *  @param serviceMean The mean service time per customer.
     *  @param fixed If fixed is true, there will be no randomness in times */
    public void initializeEvents( int numCustomers, int arrivalMean, 
                                  int serviceMean, boolean fixed )
    {
        // Seed the event queue with customer arrivals
        int clock = 0;
        int customerNum = 0;
        int arrivalDelay, serviceTime;
        for (int i = 0; i < numCustomers; i++) {
            if (fixed) {
                arrivalDelay = arrivalMean;
                serviceTime = serviceMean;
            }
            else {
                arrivalDelay = getRandomInt(arrivalMean);
                serviceTime = getRandomInt(serviceMean);
            }
            clock += arrivalDelay;
            Customer c = new Customer("Customer"+customerNum++, 
                                       clock, serviceTime);
            eventQueue.add(new Event(Event.ARRIVAL, clock, c));
        }
    }
  
  
    /** Print an event. Useful for seeing the events during simulation */
    public void printEvent(Event e)
    {
        System.out.print( e.getName() );
        if (e.getType() == Event.ARRIVAL) {
            System.out.print( " arrives at time " );
        }
        else if (e.getType() == Event.DEPARTURE) {
            System.out.print( " departs at time " ); 
        }
        System.out.println( e.getTime() );
    }
  
    /** Runs a simulation and print the statistics 
     *  TODO: Fill in code to run the simulation and collect statistics. */
    public void runSimulation() 
    {
        int totalCustomers = 0;
        int totalWaitTime = 0;
        int maxWaitTime = 0;
        int maxWaitQueueSize = 0;
        boolean busy = false;
        int clock = 0;
        
        //TODO: Code to run the simulation and collect statistics goes here.
        while(eventQueue.size() != 0){
        	if(waitLine.size() > maxWaitQueueSize){
        		maxWaitQueueSize = waitLine.size();
        	}
            Event next = eventQueue.poll();
            printEvent(next);//help
            clock = next. getTime();
            if (next.getType() == Event.ARRIVAL){
         	   if(!busy){
         		   busy = true;
         		   int cDepartureTime = clock + next.getCustomer().getServiceTime();
         		   eventQueue.add(new Event(Event.DEPARTURE, cDepartureTime, next.getCustomer()));//generate a departure event for this customer and add this departure event to the event queue
         		   totalCustomers++;
         		   int cWaitTime = cDepartureTime - next.getCustomer().getArrivalTime() - next.getCustomer().getServiceTime();
         		   //System.out.println(next.getName() + "wait: "+ cWaitTime);
         		   totalWaitTime += cWaitTime;
         		   if(cWaitTime > maxWaitTime){
         			  maxWaitTime = cWaitTime;
         		   }
         		 
         	   }
            	else{
            		waitLine.add(next.getCustomer());
            	}
            }
            else{
               if( waitLine.size() != 0){
            	   Customer nextC = waitLine.poll(); // remove next customer from wait queue
            	   int cDepartureTime = clock + nextC.getServiceTime();
            	   eventQueue.add(new Event(Event.DEPARTURE, cDepartureTime , nextC));// generate a departure event for this customer add this departure event to the event queue
            	   totalCustomers++;    
            	   int cWaitTime = cDepartureTime - nextC.getArrivalTime() - nextC.getServiceTime();
            	   //System.out.println(nextC.getName() + "wait: "+ cWaitTime);
         		   totalWaitTime += cWaitTime;
         		   if(cWaitTime > maxWaitTime){
         			  maxWaitTime = cWaitTime;
         		   }
               }
            	   
               else{
            	   busy = false; 	
               }
            }
        }
       // System.out.println(totalCustomers);//helper correct
        
      
        System.out.println("\tAverage wait time: " + 
                            ((float)totalWaitTime)/totalCustomers);
        System.out.println("\tMaximum wait time: " + maxWaitTime);
        System.out.println("\tMaximum queue length: " + maxWaitQueueSize);
    }
  
    /** Main method to set up and run the simulation */ 
    public static void main( String[] args )
    {
        if (args.length < 4) {
            System.out.print( "Usage: java EventSimulator numCustomers ");
            System.out.print( "meanTimeBetweenArrivals meanServiceTime ");
            System.out.println( "variableTimes? [randSeed]");
            return;
        }
        
        int numCustomers = Integer.parseInt(args[0]);
        int arrivalMean = Integer.parseInt(args[1]);
        int serviceMean = Integer.parseInt(args[2]);
        int variableInt = Integer.parseInt(args[3]);
        boolean variable = false;
        if (variableInt == 1) {
            variable = true;
        }
        int rseed = -1;
        if ( args.length >= 5 ) {
            rseed = Integer.parseInt(args[4]);
        }
        EventSimulator sim = new EventSimulator( rseed );
        sim.initializeEvents( numCustomers, arrivalMean, serviceMean, !variable );
        System.out.println("Running simulation with " + numCustomers + " customers");
        sim.runSimulation();
    }
}