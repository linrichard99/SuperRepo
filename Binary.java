/*
  Richard Lin
  APCS pd10
  hw45 -- Come Together
  2015-12-09
*/

public class Binary implements Comparable{

    private int _decNum;
    private String _binNum;


    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _binNum to "0"
      =====================================*/
    public Binary() { 
        _decNum = 0;
	_binNum = "0";
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _binNum to equiv string of bits
      =====================================*/
    public Binary( int n ) {
	if( n >= 0 ) {
	    _decNum = n;
	    _binNum = decToBin( n );
	}
    }

    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative binary number
      post: sets _binNum to input, _decNum to decimal equiv
      =====================================*/
    public Binary( String s ) {
    	_binNum = s;
    	_decNum = binToDec( s );
    }


    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() {
	return _binNum;
    }


    /*=====================================
      String decToBin(int) -- converts base-10 input to binary
      pre:  n >= 0
      post: returns String of bits
      eg  decToBin(0) -> "0"
      decToBin(1) -> "1"
      decToBin(2) -> "2"
      decToBin(3) -> "3"
      decToBin(14) -> "E"
      =====================================*/
    public static String decToBin( int n ) {
	String btemp = ""; // a var for nonstatic _binNum
	int holder = n;
	while( n > 0 ) {
		btemp = ( n % 2 ) + btemp;
		n = n / 2;
	}
	if( holder > 0 ) {
	    return btemp;
	}
	else if( holder == 0) {
	    return "0";
	}
	else {
	    return "Input has to be greater than or equal to 0.";
	}	
    }


    /*=====================================
      String decToBinR(int) -- converts base-10 input to binary, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  decToBinR(0) -> "0"
      decToBinR(1) -> "1"
      decToBinR(2) -> "2"
      decToBinR(3) -> "3"
      decToBinR(14) -> "E"
      =====================================*/
    public static String decToBinR( int n ) { 
	String retVal = "";
	if( n == 0 ) {
	    return "0";
	}
	else if( n > 0 ) {
	    retVal = decToBinR( n / 2 ) + ( n % 2 );
	    return retVal.substring(1,retVal.length());
	}
	else {
	    return "Input has to be greater than or equal to 0.";
	}
	
    }
    
    /*=====================================
      String binToDec(String) -- converts base-10 input to binary
      pre:  s represents non-negative binaryimal number
      post: returns decimal equivalent as int
      eg  
      binToDec("0") -> 0
      binToDec("1") -> 1
      binToDec("2") -> 2
      binToDec("3") -> 3
      binToDec("E") -> 14
      =====================================*/
    public static int binToDec( String s ) {
	int dtemp = 0;
	int place = s.length() - 1;
	for( int i = 0; i < s.length(); i++ ) {
	    dtemp += Math.pow(2, place) * Integer.parseInt(s.substring(i,i+1));
	    place--;
	}
	return dtemp;
    }


    /*=====================================
      String binToDecR(String) -- converts base-10 input to binary, recursively
      pre:  s represents non-negative binary number
      post: returns decimal equivalent as int
      eg  
      binToDecR("0") -> 0
      binToDecR("1") -> 1
      binToDecR("2") -> 2
      binToDecR("3") -> 3
      binToDecR("E") -> 14
      =====================================*/
    public static int binToDecR( String s ) {
	if( !s.equals("") ) {
	    return binToDecR( s.substring(1) ) + (int)( Integer.parseInt( s.substring(0,1) ) * Math.pow(2.0,( s.length() - 1.0 )));
	}
	else {
	    return 0;
	}
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Binary
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal binary values
      =============================================*/
    public boolean equals( Object other ) {
	if( other instanceof Binary ) {
	    return ( ( this == (Binary)other ) || ( this._binNum.equals(((Binary)other)._binNum ) ));
	}
	else {
	    throw new ClassCastException("\nERROR! equals() input not a binary");
	}
    }


    /*=============================================
      int compareTo(Object) -- tells which of two Binary objects is greater
      pre:  other is instance of class Binary
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo( Object other ) {
	if( other instanceof Binary ) {
	    if( this._decNum < ((Binary)other)._decNum )
		return -1;
	    else if( this._decNum == ((Binary)other)._decNum )
		return 0;
	    else {
		return 1;
	    }
	}
	else {
	    throw new ClassCastException("\nERROR! compareTo() input not a binary");
	}
    }


    //main method for testing
    public static void main( String[] args ) {


	System.out.println();
	System.out.println( "Testing ..." );

	Binary b1 = new Binary(5);
	Binary b2 = new Binary(5);
	Binary b3 = b1;
	Binary b4 = new Binary(7);

	System.out.println( b1 ); //101
	System.out.println( b2 ); //101
	System.out.println( b3 ); //101
	System.out.println( b4 ); //111
	
	System.out.println(binToDec(b1.toString())); //6
	System.out.println(binToDecR("0")); //0

	System.out.println( "\n==..." );
	System.out.println( b1 == b2 ); //should be false
	System.out.println( b1 == b3 ); //should be true

	System.out.println( "\n.equals()..." );
	System.out.println( b1.equals(b2) ); //should be true
	System.out.println( b1.equals(b3) ); //should be true
	System.out.println( b3.equals(b1) ); //should be true
	System.out.println( b4.equals(b2) ); //should be false
	System.out.println( b1.equals(b4) ); //should be false

	System.out.println( "\n.compareTo..." );
	System.out.println( b1.compareTo(b2) ); //should be 0
	System.out.println( b1.compareTo(b3) ); //should be 0
	System.out.println( b1.compareTo(b4) ); //should be pos
	System.out.println( b4.compareTo(b1) ); //should be neg
	/*=========================================
	  =========================================*/
    }//end main()

} //end class
