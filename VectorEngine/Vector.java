import java.util.*;

public class Vector {

	private Long sum;
	private Long mode;
	private Long median;
	private Long minimum;
	private Long maximum;

	private final int length;
	private final long[] elements;

	// ===========================================================================
	// INITIALIZATION
	// ===========================================================================

	/**
	 * Constructs new vector with the given
	 * length and all elements set to zero.
	 */
	public Vector(int length) {
		
		this.sum = null;
		this.mode = null;
		this.median = null;
		this.minimum = null;
		this.maximum = null;
		
		this.length = length;
		this.elements = new long[length];
	}

	/**
	 * Returns new vector with elements generated at random up to 100.
	 * max,min,sum,mode,median
	 */
	public static Vector random(int length, long seed) {

		Vector vector = new Vector(length);
		Random random = new Random(seed);

		for (int i = 0; i < length; i++) {
			vector.elements[i] = (long) random.nextInt(101);
		}
		

		return vector;
	}
	//for(int i = 0; i < length; i++)

	/**
	 * Returns new vector with all elements set to given value.
	 */
	public static Vector uniform(int length, long value) {
		Vector uniform = new Vector(length);
		
		for(int i = 0; i < length ; i++){
			uniform.elements[i] = value;
		}
		uniform.mode = value;
		uniform.maximum = value;
		uniform.minimum = value;
		uniform.sum = length * value;
		uniform.median = value;
		return uniform;
		/*
			length 1, value 1 => [1]
			length 2, value 2 => [2 2]
			length 3, value 3 => [3 3 3]
			length 4, value 4 => [4 4 4 4]
		*/
	}

	/**
	 * Returns new vector with elements in sequence from given start and step.
	 */
	public static Vector sequence(int length, long start, long step) {
		
		Vector sequence = new Vector(length);
		sequence.elements[0] = start;
		for(int i = 1; i < length; i++){
			sequence.elements[i] = sequence.elements[i-1] + step;
		}
		//TODO TESTS
		if(step == 0){
			sequence.mode = start;
		}else{
			sequence.mode = -1L;
		}
		if(step < 0){
			sequence.maximum = sequence.elements[0];
			sequence.minimum = sequence.elements[length - 1];
		}else{
			sequence.maximum = sequence.elements[length - 1];
			sequence.minimum = sequence.elements[0];
		}
		long lastValue = start + (step *(length - 1));
		sequence.sum = length*(start + lastValue) / step;

		return sequence;

	}

	/**
	 * Returns whether the number is semiprime.
	 */
	public static boolean isPQ(long number) {
		if(number < 4){
			return false;
		}
		for(int i = 2; i * i <= number; i++){
			if(isPrime(i) == true){
				if(number % i == 0 && isPrime(number/i)){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns new vector with elements generated from the
	 * pq number sequence starting from the specified value.
	 */
	public static Vector pq(int length, long start) {
		Vector pq = new Vector(length);
		for(int j = 0; j < length; j++){
			while(isPQ(start) == false){
					start++;
			}
			pq.elements[j] = start;
			start++;
		}
		pq.maximum = pq.elements[length - 1];
		pq.minimum = pq.elements[0];
		//pq.median = 	Try afterwards
		pq.mode = -1L;
		return pq;

		/*
			TODO

			length 4, start 1  => [4 6 9 10]
			length 4, start 4  => [4 6 9 10]
			length 4, start -1 => [4 6 9 10]
			length 4, start 42 => [46 49 51 55]
		*/

	}

	/**
	 * Returns whether the number is prime.
	 */
	public static boolean isPrime(long number) {
		/*No positive divisors other than 1 and itself
		https://en.wikipedia.org/wiki/AKS_primality_test for faster method(TODO)
		*/
		if(number <= 1 ){
			return false;
		}
		if(number == 2){
			return true;
		}
		if(number % 2 == 0){
			return false;
		}
		for(int i = 3; i <= Math.sqrt(number); i+=2){
			if(number % i == 0){
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns new vector with elements generated from the
	 * prime number sequence starting from the specified value.malacca straits
	 */
	public static Vector prime(int length, long start) {
		Vector prime = new Vector(length);
		
		for(int i = 0; i < prime.length; i++){
			while(isPrime(start) == false){
				start++;
			}
			prime.elements[i] = start;
			start++;
		}
		prime.maximum = prime.elements[length - 1];
		prime.minimum = prime.elements[0];
		prime.mode = -1L;
		return prime;
	}
		/*
			TODO

			length 4, start 1  => [2 3 5 7]
			length 4, start 2  => [2 3 5 7]
			length 4, start -1 => [2 3 5 7]
			length 4, start 42 => [43 47 53 59]
		*/


	/**
	 * Returns whether the number is abundant.
	 */
	public static boolean isAbundant(long number) {
		//When the sum of its proper divisors(positive and excluding the number itself) exceeds the number itself eg.12: (1 + 4 + 3 + 6 + 2) = 16 > 12
		//the first abundant number is 12
		if(number < 945 && number % 2 == 1 || number < 12){
			return false;
		}
		long sum = 1;
		for(int i = 2; i < number; i++){
			if(number % i == 0){
				sum = sum + i;
			} 
		}
		if(sum > number){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Returns new vector with elements generated from the
	 * abundant number sequence starting from the specified value.
	 */
	public static Vector abundant(int length, long start) {
		Vector ab = new Vector(length);
		for(int i = 0; i < length; i++){
			while(isAbundant(start) == false){
				start++;
			}
			ab.elements[i] = start;
			start++;
		}
		ab.maximum = ab.elements[length - 1];
		ab.minimum = ab.elements[0];
		//ab.median  Test afterwards
		ab.mode = -1L;
		return ab;

		/*
			TODO

			length 4, start 0  => [12 18 20 24]
			length 4, start 12 => [12 18 20 24]
			length 4, start -1 => [12 18 20 24]
			length 4, start 42 => [42 48 54 56]
		*/

	}

	/**
	 * Returns whether the number is composite.
	 */
	public static boolean isComposite(long number) {
		if(number <= 1){
			return false;
		}
		//else if()

		/*
			TODO
		*/

		return true;
	}

	/**
	 * Returns new vector with elements generated from the
	 * composite number sequence starting from the specified value.
	 */
	public static Vector composite(int length, long start) {
		Vector composite = new Vector(length);
		if(start < 4){
			start = 4;
		}
		for(int i = 0 ; i < length; i++){
			while(!(isPrime(start)) == false){
			start++;
			}
			composite.elements[i] = start;
			start++;
		}
		composite.maximum = composite.elements[length - 1];
		composite.minimum = composite.elements[0];
		//composite.median
		composite.mode = -1L;
		return composite;
		/*
			TODO

			length 4, start 0  => [4 6 8 9] 
			length 4, start 4  => [4 6 8 9] 
			length 4, start -1 => [4 6 8 9] 
			length 4, start 42 => [42 44 45 46] 
		*/
	}

	// ===========================================================================
	// VECTOR OPERATIONS
	// ===========================================================================

	/**
	 * Returns new vector that is a copy of the current vector.
	 */
	public Vector cloned() {
		Vector cloned = new Vector(length);
		for(int i = 0; i < cloned.length; i++){
			cloned.elements[i] = elements[i];
		}
		cloned.maximum = this.maximum;
		cloned.minimum = this.minimum;
		cloned.sum = this.sum;
		cloned.median = this.median;
		cloned.mode = this.mode;
		return cloned;

	}

	/**
	 * Returns new vector with elements ordered from smallest to largest.
	 */
	public Vector sorted() {
		Vector sorted = this.cloned();
		Arrays.sort(sorted.elements);
		sorted.maximum = this.maximum;
		sorted.minimum = this.minimum;
		sorted.mode = this.mode;
		sorted.sum = this.sum;
		sorted.median = this.median;
		
		return sorted;
		

		/*
			TODO

			[1 1 1 1] => [1 1 1 1]
			[1 2 3 4] => [1 2 3 4]
			[4 3 2 1] => [1 2 3 4]
		*/

	}

	/**
	 * Returns new vector with elements ordered in reverse.
	 */
	public Vector reversed() {
		Vector reversed = new Vector(length);
		for(int j = reversed.length - 1; j >= 0; j--){
			reversed.elements[j] = elements[j];
		}
		for(long i = 0; i < reversed.length / 2; i++){
		    long temp = reversed.elements[(int) i];
		    reversed.elements[(int) i] = reversed.elements[(int) (reversed.elements.length - i - 1)];
		    reversed.elements[(int) (reversed.elements.length - i - 1)] = temp;
		}
		//TODO tests
		reversed.maximum = this.maximum;
		reversed.minimum = this.minimum;
		reversed.mode = this.mode;
		reversed.sum = this.sum;
		if(length % 2 == 0){
			reversed.median = elements[length/2];
		}else{
			reversed.median = elements[(length - 1) / 2];
		}	
		return reversed;
		/*
			TODO

			[1 1 1 1] => [1 1 1 1]
			[1 2 3 4] => [4 3 2 1]
			[4 3 2 1] => [1 2 3 4]
		*/
	}

	/**
	 * Returns new vector with elements shifted right by a given number of positions.
	 */
	public Vector shifted(int amount) {
		Vector shift = new Vector(length);
		for(int i = 0; i < elements.length; i++){
			shift.elements[i] = elements[i];
		}
		if(amount == elements.length || amount == 0){
			return shift;//works until here
		}
		else if(amount > elements.length){
			amount = amount - this.length;
		}
		for(int j = 0; j <= elements.length - 1 - amount; j++){
				shift.elements[j + amount] = elements[j];
		}
		int size = elements.length - 1;
		for(int x = amount - 1; x >= 0; x--){
			shift.elements[x] = this.elements[size];
			size--;
		}
		shift.maximum = this.maximum;
		shift.minimum = this.minimum;
		shift.mode = this.mode;
		shift.sum = this.sum;
		shift.median = this.median;
		return shift;

		/*
			TODO

			[1 2 3 4] 0 => [1 2 3 4]
			[1 2 3 4] 1 => [4 1 2 3]
			[1 2 3 4] 2 => [3 4 1 2]
			[1 2 3 4] 3 => [2 3 4 1]
			[1 2 3 4] 4 => [1 2 3 4]
			[1 2 3 4] 5 => [4 1 2 3]
		*/
	}

	/**
	 * Returns new vector, adding scalar to each element.
	 */
	public Vector scalarAdd(long scalar) {
		Vector scalarAdd = new Vector(length);
		for(int i = 0; i < elements.length; i++){
			scalarAdd.elements[i] = elements[i];
		}
		for(int j = 0; j < elements.length; j++){
			scalarAdd.elements[j] = scalarAdd.elements[j] + scalar;
		}
		//TODO Tests
		//scalarAdd.maximum = this.maximum + scalar;
		//scalarAdd.minimum = this.minimum + scalar;
		//scalarAdd.mode = this.mode + scalar;
		//scalarAdd.sum = this.sum + (length * scalar);
		//scalarAdd.median = this.median + scalar;
		return scalarAdd;
		

		/*
			TODO

			[1 1 1 1] + 1  => [2 2 2 2]
			[1 2 3 4] + 4  => [5 6 7 8]
			[2 2 2 2] + -1 => [1 1 1 1]
		*/
	}

	/**
	 * Returns new vector, multiplying scalar to each element.
	 */
	public Vector scalarMultiply(long scalar) {
		Vector scalarMul = new Vector(length);
		for(int i = 0; i < elements.length; i++){
			scalarMul.elements[i] = elements[i];
		}
		for(int j = 0; j < elements.length; j++){
			scalarMul.elements[j] = scalarMul.elements[j] * scalar;
		}
		return scalarMul;

		/*
			TODO

			[1 2 3 4] x 0  => [0 0 0 0]
			[1 2 3 4] x 1  => [1 2 3 4]
			[1 2 3 4] x 2  => [2 4 6 8]
			[1 2 3 4] x 10 => [10 20 30 40]
			[1 2 3 4] x -1 => [-1 -2 -3 -4]
		*/
	}

	/**
	 * Returns new vector, adding elements with the same index.
	 */
	public Vector vectorAdd(Vector other) {
		Vector vectorAdd = new Vector(length);
		for(int i = 0; i < elements.length; i++){
			vectorAdd.elements[i] = elements[i];
		}
		for(int j = 0; j < elements.length; j++){
			vectorAdd.elements[j] = vectorAdd.elements[j] + other.elements[j];
		}
		
		return vectorAdd;

		/*
			TODO

			[1 2 3 4] + [0 0 0 0]     => [1 2 3 4]
			[1 2 3 4] + [4 4 4 4]     => [5 6 7 8]
			[1 2 3 4] + [1 2 3 4]     => [2 4 6 8]
			[2 2 2 2] + [-1 -1 -1 -1] => [1 1 1 1]
		*/
	}

	/**
	 * Returns new vector, multiplying elements with the same index.
	 */
	public Vector vectorMultiply(Vector other) {
		Vector vectorMul = new Vector(length);
		for(int i = 0; i < elements.length; i++){
			vectorMul.elements[i] = elements[i];
		}
		for(int j = 0; j < elements.length; j++){
			vectorMul.elements[j] = vectorMul.elements[j] * other.elements[j];
		}		
		return vectorMul;
		/*
			TODO

			[1 2 3 4] x [0 0 0 0]     => [0 0 0 0]
			[1 2 3 4] x [1 1 1 1]     => [1 2 3 4]
			[1 2 3 4] x [1 2 3 4]     => [1 4 9 16]
			[2 2 2 2] x [-1 -1 -1 -1] => [-2 -2 -2 -2]
		*/
	}

	// ===========================================================================
	// VECTOR COMPUTATIONS
	// ===========================================================================

	/**
	 * Returns the sum of all elements.
	 */
	public Long getSum() {
		long sum = 0;
		for(int  i = 0; i < this.elements.length; i++){
			sum = sum + this.elements[i];
		}

		/*
			TODO

			[0 0 0 0] => 0
			[1 1 1 1] => 4
			[1 2 3 4] => 10
		*/

		// Calculate and store the sum when unknown
		if (this.sum == null) {
			// TODO
		}
		return sum;

	}

	/**
	 * Returns the most frequently occuring element
	 * or -1 if there is no such unique element.
	 */
		
	public Long getMode() {
		Vector sorted = this.cloned();
		if (this.mode != null){
			return this.mode;
		}	
		Arrays.sort(sorted.elements);
		long count = 0;
		long maxCount = count;
		for(int i = 1; i < sorted.length; i++){
			count = 0;
			int j = i;
			while(sorted.elements[i - 1] == sorted.elements[j]){
				count++;
				j++;
			}
			if(maxCount < count){
				maxCount = count;
				sorted.mode = sorted.elements[i-1];
				this.mode = sorted.mode;
			}
			else{
				continue;
			}
			if(maxCount == count && elements[i] != sorted.mode){
				sorted.mode = -1L;
			}
			 /*for(int j = 0; j < this.length; j++){ //try using while afterwards, much faster than nested 'for'
					if(i == j){
						continue;
					}
					else if(elements[i] == elements[j]){
						count++;	
						if(count > maxCount){
							maxCount = count;
							this.mode = elements[i];
						}
						else if(maxCount == count && elements[i] != this.mode ){
							this.mode = -1L;
						}
					}
				}*/
		}
		if(maxCount == 0 ){
			sorted.mode = -1L;
			this.mode = sorted.mode;
		}
		return this.mode;
	}
		/*
			TODO

			[1]       => 1
			[2 2]     => 2
			[2 4 4]   => 4
			[1 2 3 4] => -1
		*/

	/**
	 * Returns the upper median.
	 */
	public Long getMedian() {
		Vector median = this.sorted();
		if(this.length % 2 == 0){ // even number of length
			this.median = median.elements[(this.length) / 2];
		}
		else{
			this.median = median.elements[(this.length - 1) / 2];
		}
		

		/*
			TODO

			[1] => 1
			[1 2] => 2
			[1 2 3] => 2
			[1 1 1 1] => 1
		*/

		return this.median;
	}

	/**
	 * Returns the smallest value in the vector.
	 */
	public Long getMinimum() {
		Arrays.sort(elements);
		this.minimum = elements[0];

		/*
			TODO

			[1 1 1 1] => 1
			[1 2 3 4] => 1
			[4 3 2 1] => 1
		*/

		return this.minimum;
	}

	/**
	 * Returns the largest value in the vector.
	 */
	public Long getMaximum() {
		Arrays.sort(elements);
		this.maximum = elements[this.length - 1];

		/*
			TODO

			[1 1 1 1] => 1
			[1 2 3 4] => 4
			[4 3 2 1] => 4
		*/

		return this.maximum;
	}

	/**
	 * Returns the frequency of the value in the vector.
	 */
	public long getFrequency(long value) {
		long count = 0;
		for(int i = 0; i < this.length; i++){
			if(value != elements[i]){
				continue;
			}else{
				count++;
			}
		}
		if(count > 0){
			return count;
		}
		
		return 0;
		/*
			TODO

			[1 2 3 4] 0 => 0
			[1 2 3 4] 1 => 1
			[1 1 1 1] 1 => 4
		*/

	}

	// ===========================================================================
	// DISPLAY OPERATIONS
	// ===========================================================================

	/**
	 * Displays the vector.
	 */
	public void display() {
		for(int i = 0; i < elements.length - 1; i++){
			System.out.print(elements[i] + " ");
		}
		System.out.print(elements[elements.length-1]);
		System.out.println("");

		/*
			Display each element in the vector seperated by a space.
		*/
	}

	/**
	 * Displays the element at the specified index.
	 */
	public void displayElement(int index) {
		System.out.println(elements[index]);

	}

	// ===========================================================================
	// ACCESSOR METHODS
	// ===========================================================================

	/**
	 * Returns the vector length.
	 */
	public int getLength() {

		return this.length;
	}

	/**
	 * Returns the vector elements.
	 */
	public long[] getElements() {

		return this.elements;
	}
}
/*2nd method
Vector sort = this.cloned();
		long fq = 0;
		sort.mode = sort.elements[0];
		long maxFq = fq;
		boolean singleMode = false;
		
		Arrays.sort(sort.elements);
		
		for(int i = 1; i < sort.length; i++){
			fq=0;
			while(sort.elements[i-1] == sort.elements[i]){
				fq++;
				i++;			
			}
			if(fq > maxFq){
				maxFq = fq;
				sort.mode = elements[i];
				singleMode = true;		
				System.out.println("Maxfq updated as " + elements[i] + "max fq is " + maxFq);
			}
			if(fq == maxFq && elements[i] != sort.mode){
				System.out.println("More than one mode, this one is " + elements[i]);
				singleMode = false;
			}
			}
			if(singleMode == false){
				sort.mode=  -1L;
		}
			
		return sort.mode;*/

