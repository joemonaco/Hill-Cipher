
import java.util.*;

public class HillCipher {

	
	private double[][] keyMatrix;
	private double[][] messageVector;
	private double[][] encryptedVector;
	private double[][] decryptKey;
	
	private int keyMatrixSize;
	private int vectorSize;
	
	public HillCipher() {
		
	}
	
	public HillCipher(String key, String message) {

		vectorSize = message.length();
		
		keyMatrix = createKeyMatrix(key);
		messageVector = createMessageVector(key, message);
		encryptedVector = getEncrypted(key);
		decryptKey = getDecryptKey();
		
	}
	
	public int getVectorSize() {
		return vectorSize;
	}
	
	public double[][] getKeyMatrix() {
		
		return keyMatrix;
	}
	
	public double[][] getMessageVector() {
		return messageVector;
	}
	
	public double[][] getEncryptedVector() {
		return encryptedVector;
	}
	
	public double[][] getDecryptedKey() {
		return decryptKey;
	}
	
	public int modInverse(int a, int m) 
    { 
        a = a % m; 
        for (int x = 1; x < m; x++) 
           if ((a * x) % m == 1) 
              return x; 
        return 1; 
    } 
	
	
	public double[][] getOriginal() {
		
		double[][] original = new double[vectorSize][1];
		
		double rowMultiplied = 0;
		
		for(int i = 0; i < vectorSize; i++) {
			for(int j = 0; j < vectorSize; j++) {
				rowMultiplied = rowMultiplied + (getDecryptKey()[i][j] * encryptedVector[j][0]);
			}
			original[i][0] = rowMultiplied % 26;
			rowMultiplied = 0;
		}
		
		return original;
	}	
	
	public double[][] getDecryptKey() {
		
		int det = (int)determinant(keyMatrix, vectorSize);
		
		double[][] tempMatrix = new double[vectorSize][vectorSize];
		
		for(int i = 0; i < vectorSize; i++) {
			for(int j =0; j < vectorSize; j++) {
				tempMatrix[i][j] = keyMatrix[i][j];
			}
		}
		
		double[][] inverseMat = invert(tempMatrix);
	
		double[][] detXinverse = new double[vectorSize][vectorSize];
		
		for(int i = 0; i < vectorSize; i++) {
			for(int j =0; j < vectorSize; j++) {
				detXinverse[i][j] = (det * inverseMat[i][j]);
			}
		}
		
		double[][] detXinverseXgcd = new double[vectorSize][vectorSize];
		int moddedNum;
		
		int remainder = (int)det % 26;
		if (remainder < 0)
		{
		    remainder += 26;
		}
		
		moddedNum = remainder;
		int gcd = modInverse(det, 26);
		
		for(int i = 0; i < vectorSize; i++) {
			for(int j =0; j < vectorSize; j++) {
				
				double numForMatrix = moddedNum * detXinverse[i][j];
				double truncated = (int) numForMatrix;
				
				if(numForMatrix < 0) {
					if(truncated - numForMatrix > 0.98){
						numForMatrix = numForMatrix - 1;
						
					}
				}
				else {
					if(numForMatrix - truncated > 0.98) {
						numForMatrix = numForMatrix + 1;
					}
				}
				detXinverseXgcd[i][j] =  (int) numForMatrix;
			}
		}
		
		double[][] finalInverse = new double[vectorSize][vectorSize];
		
		
		for(int i = 0; i < vectorSize; i++) {
			for(int j =0; j < vectorSize; j++) {
				
				int r = (int)detXinverseXgcd[i][j] % 26;
				if (r < 0)
				{
				    r += 26;
				}
				
				
				finalInverse[i][j] =  r;
			}
		}
		
		return finalInverse;
	}
	
	
	
	public double[][] createMessageVector(String key, String message) {
//		int size = (int) (Math.sqrt(key.length()));
		
		double[][] messageToReturn = new double[vectorSize][1];
		
		ArrayList<Integer> numLetters = new ArrayList<Integer>();
		
		for (char letter: message.toCharArray()) {
			numLetters.add((int)letter % 65);
		}
		
		int arrayCount = 0;
		for(int i = 0; i < vectorSize; i++) {
			for(int j = 0; j < 1; j++) {
				messageToReturn[i][j] = numLetters.get(arrayCount);
				arrayCount++;
			}
		}
		return messageToReturn;
	}
	
	public double[][] createKeyMatrix(String key){
				
		double[][] keyToReturn = new double[vectorSize][vectorSize];
		
		ArrayList<Integer> numLetters = new ArrayList<Integer>();
		
		for (char letter: key.toCharArray()) {
			numLetters.add((int)letter % 65);
		}
		
		int arrayCount = 0;
		for(int i = 0; i < vectorSize; i++) {
			for(int j = 0; j < vectorSize; j++) {
				keyToReturn[i][j] = numLetters.get(arrayCount);
				arrayCount++;
			}
		}
		
		return keyToReturn;
	}
	
	public double[][] getEncrypted(String key) {
		
		double[][] encrypted = new double[vectorSize][1];
		
		double rowMultiplied = 0;
		
		for(int i = 0; i < vectorSize; i++) {
			for(int j = 0; j < vectorSize; j++) {
				rowMultiplied = rowMultiplied + (keyMatrix[i][j] * messageVector[j][0]);
			}
			encrypted[i][0] = rowMultiplied % 26;
			rowMultiplied = 0;
		}
		
		return encrypted;
	}
	
	
	public double determinant(double matrix[][],int n)
    {
        double det=0;
        if(n == 1)
        {
            det = matrix[0][0];
        }
        else if (n == 2)
        {
            det = matrix[0][0]*matrix[1][1] - matrix[1][0]*matrix[0][1];
        }
        else
        {
            det=0;
            for(int j1=0;j1<n;j1++)
            {
                double[][] newMatrix = new double[n-1][];
                for(int k=0;k<(n-1);k++)
                {
                	newMatrix[k] = new double[n-1];
                }
                for(int i=1;i<n;i++)
                {
                    int j2=0;
                    for(int j=0;j<n;j++)
                    {
                        if(j == j1)
                            continue;
                        newMatrix[i-1][j2] = matrix[i][j];
                        j2++;
                    }
                }
                det += Math.pow(-1.0,1.0+j1+1.0)* matrix[0][j1] * determinant(newMatrix,n-1);
            }
        }
        return det;
    }
	
	public double[][] invert(double matrix[][]) 
    {
        int n = matrix.length;
        double matrixB[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i=0; i<n; ++i) 
            b[i][i] = 1;
 
 // Transform the matrix into an upper triangle
        gaussian(matrix, index);
 
 // Update the matrix b[i][j] with the ratios stored
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k]
                    	    -= matrix[index[j]][i]*b[index[i]][k];
 
 // Perform backward substitutions
        for (int i=0; i<n; ++i) 
        {
            matrixB[n-1][i] = b[index[n-1]][i]/matrix[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j) 
            {
                matrixB[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k) 
                {
                    matrixB[j][i] -= matrix[index[j]][k]*matrixB[k][i];
                }
                matrixB[j][i] /= matrix[index[j]][j];
            }
        }
        return matrixB;
    }
 
    public void gaussian(double matrix[][], int index[]) 
    {
        int n = index.length;
        double c[] = new double[n];
 
        for (int i=0; i<n; ++i) 
            index[i] = i;
 
        for (int i=0; i<n; ++i) 
        {
            double c1 = 0;
            for (int j=0; j<n; ++j) 
            {
                double c0 = Math.abs(matrix[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }
 
        int k = 0;
        for (int j=0; j<n-1; ++j) 
        {
            double pi1 = 0;
            for (int i=j; i<n; ++i) 
            {
                double pi0 = Math.abs(matrix[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) 
                {
                    pi1 = pi0;
                    k = i;
                }
            }
            
            int temp = index[j];
            index[j] = index[k];
            index[k] = temp;
            
            for (int i=j+1; i<n; ++i) 	
            {
                double num = matrix[index[i]][j]/matrix[index[j]][j];
 
                matrix[index[i]][j] = num;
 
                for (int l=j+1; l<n; ++l)
                    matrix[index[i]][l] -= num*matrix[index[j]][l];
            }
        }

    }
}
