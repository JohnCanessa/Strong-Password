import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;


public class Solution {
	
	
    // Return the minimum number of characters to make the password strong
    static int minimumNumber(int n, String password) {
    	
    	// **** ****
    	final String 	numbers 			= "0123456789";
    	final String	lowerCase			= "abcdefghijklmnopqrstuvwxyz";
    	final String	upperCase 			= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	final String	specialCharacters 	= "!@#$%^&*()-+";
    	
    	// **** number of possible missing characters ****
    	int m 		= 4;
    	int mask	= 0x0f;
    	
    	// **** traverse the password ****
    	for (int i = 0; (i < password.length()) && (m > 0); i++) {
    		
    		// **** current character ****
    		char c = password.charAt(i);

    		// **** check in numbers (mask = 0x01) ****
        	if (	(((mask & 0x01) == 1) ? true : false) && 
        			(numbers.indexOf(c) != -1)) {
        		m--;
        		mask &= 0x0e;
        	}
        	
        	// **** check lower case letters (mask = 0x02) ****    		
        	if (	(((mask & 0x02) == 2) ? true : false) && 
        			(lowerCase.indexOf(c) != -1)) {
        		m--;
        		mask &= 0x0d;
        	}
        		
           	// **** check upper case letters (mask = 0x04) ****
        	if (	(((mask & 0x04) == 4) ? true : false) && 
        			(upperCase.indexOf(c) != -1)) {
        		m--;
        		mask &= 0x0b;
        	}

        	// **** check special characters (mask = 0x08) ****
        	if (	(((mask & 0x08) == 8) ? true : false) && 
        			(specialCharacters.indexOf(c) != -1)) {
        		m--;
        		mask &= 0x07;
        	}
    	}
    	
    	// **** check the password length ****
    	if ((n < 6) && 
    		((n + m) < 6))
    			m = 6 - n;

    	// **** missing count ****
    	return m;
    }

    
    private static final Scanner scanner = new Scanner(System.in);

    
    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String password = scanner.nextLine();

        int answer = minimumNumber(n, password);

        bufferedWriter.write(String.valueOf(answer));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
