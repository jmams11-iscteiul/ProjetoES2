package HelloWorld;

/**
 * Hello world! Let´s make some calculations!
 *
 */
public class MathCalc {
	
	/**
	 * Function to calculate compound interest given an initial value,
	 * interest rate, and number of days to calculate
	 * 
	 * @param initialValue value to start investing
	 * @param interestRate expected interest rate
	 * @param numOfDays	how many times the money is being reinvested
	 * @return the total amount of money with the compound interes
	 */
	public static double compound(double initialValue, double interestRate, double numOfDays){
        return initialValue * Math.pow((1 + interestRate), numOfDays);
    }
    
    /**
     * Function to sum to numbers
     * 
     * @param a
     * @param b
     * @return the sum of the given numbers
     */
    public static int sum(int a, int b) {
    	return a + b;
    }
    
    /**
     * Function to subtract two numbers
     * 
     * @param a
     * @param b
     * @return the subtraction of a with b
     */
    public static int subtract(int a, int b) {
    	return a - b;
    }
    
    /**
     * Function to calculate the multiplication of two numbers
     * 
     * @param a
     * @param b
     * @return the multiplication of the given numbers
     */
    public static int multiply(int a, int b) {
    	return a * b;
    }
    
    /**
     * Function to calculate the division of two numbers
     * 
     * @param a
     * @param b
     * @return the division of a by b
     */
    public static int divide(int a, int b) {
    	return a / b;
    }
    
	public static void main(String[] args) {
        System.out.println("Hello World, I'm a wild girl");
    }
}
