import java.util.Scanner;
import java.util.Random;

class SensoryUnit{
	public int n;
	public int ar[][];
	public SensoryUnit(){
		System.out.println("Enter the number of rows in input data");
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
	}

	public SensoryUnit(int n){
		this.n = n;
		ar = new int[n][3];
	}

	public void getData(){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the data for sensory unit\n");
		for(int i=0; i<n; i++)
			for(int j=0; j<3; j++){
				ar[i][j] = in.nextInt();
			}

	}

	public void printData(){
		for(int i = 0; i < n; i++){
			for(int j = 0; j < 3; j++){
				System.out.print(ar[i][j] + " ");
			}
			System.out.println();
		}
	}
}

class Node{
	public int in; // represent input to node
	//public int out; // represent output to node
}


class Perceptron{
	Node X[]; // There are 2 nodes in hidden unit
	Node Y[]; // There are only one node in output unit
	int t = 0;
	double e , alpha = 0.2;
	double wt[] = new double[2];
	int iteration, max_iteration;


	public Perceptron(){
		// Set input weight randomly between 0.0 and 1.0
		Random r = new Random();
		wt[0] = r.nextDouble();
		wt[1] = r.nextDouble();
		
		iteration = 0;
		max_iteration = 10000;
		
    }

    public void trainPerceptron(SensoryUnit s){
    	Node X[] = new Node[2];
    	Node Y[] = new Node[1];
    	X[0] = new Node();
    	X[1] = new Node(); 
    	Y[0] = new Node();
   		 	
   		int flag;

    	do{
    		flag = 0;
    		for(int i = 0 ; i < s.n ; i++){
    			
    			X[0].in = s.ar[i][0];
    			X[1].in = s.ar[i][1];
    			

    			int y = 0;
    			for(int j = 0; j < 2; j++)
    				y += X[j].in * wt[j];

    			if(y > t)
    				Y[0].in = 1;
    			else
    				Y[0].in = 0;

    			if(Y[0].in == s.ar[i][2]){
    				e = 0;
    			}else if(Y[0].in < s.ar[i][2]){
    					e = 1;
    					flag = 1;
    			}
    			else{
    				e = -1;
    				flag = 1;
    			}

    			for(int k = 0; k < 2; k++){
    				wt[k] += alpha * e * X[k].in;
    			}	
    		}	
    		iteration++;    		
    	}while(flag == 1 );
    }

    public double testPerceptron(SensoryUnit s){
    	Node X[] = new Node[2];
    	Node Y[] = new Node[1];
    	X[0] = new Node();
    	X[1] = new Node(); 
    	Y[0] = new Node();
    	double correct = 0, total=0;
        	for(int i = 0 ; i < s.n ; i++){
    			X[0].in = s.ar[i][0];
    			X[1].in = s.ar[i][1];

    			int y = 0;
    			for(int j = 0; j < 2; j++)
    				y += X[j].in * wt[j];

    			if(y > t)
    				Y[0].in = 1;
    			else
    				Y[0].in = 0;

    			if(Y[0].in == s.ar[i][2]){
    				correct++;
    			}
    			total++;		
    	}
    	return correct/total;

	}
}


class Program1{
	public static void main(String args[]){
		
		SensoryUnit s1 = new SensoryUnit(4);
		s1.getData();
		s1.printData();
		Perceptron p = new Perceptron();
		p.trainPerceptron(s1);
		System.out.println("Our perceptron neural network has been trained");
		System.out.println("Enter the new data to test");
		SensoryUnit s2 = new SensoryUnit(2);
		s2.getData();
		s2.printData();
		System.out.println("Accuracy = " + p.testPerceptron(s2));


	}
}