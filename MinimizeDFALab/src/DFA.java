
import java.io.*;
import java.util.Scanner; 
class DFA {


	public static void main(String[] args) throws IOException {
		File file =	new File("test.txt"); 
		
			    Scanner sc = new Scanner(file);
			    
			    //Getting all Alphabet
			    
			    String line = sc.nextLine();
			    int k = 0;
				char [] Alph = new char[1+line.length()/2];
			    char[] str = line.toCharArray();
			    for (int i=0; i<line.length(); i++) {
				    if (i%2==0) {
				    	Alph[k] = str[i];
				    	k = k + 1;
				    }
			    }
		    	System.out.println("Alphabet is:");
		    	System.out.println(Alph);
			    
		    	//Getting all States
			    
		    	line = sc.nextLine();
			    str = line.toCharArray();
			    int n_states =0;
			    for (int i =0;i<str.length;i++)
			    	if (str[i]==' ') n_states++;
		    	int [] States = new int[n_states+1];
			    str = line.toCharArray();
			    int l_space = -1;
			    k=0;
			    int space = 0;
			    int g = 0;
			    for (int i=k+1; i<=line.length(); i++) {
			    	boolean flag = true;
			    	int t = i;
			    	while (flag&&t<line.length()) {
			    		if ((str[t] == ' ')||(t==line.length())) flag = false;
			    		else t = t+1;
			    	}
	    			space = t;
			    	int num_len = space-l_space-1;
			    	char [] ch_num = new char[num_len];
			    	int y = 0;
			    	for (int j = 1; j<=num_len; j++) {
			    		ch_num[y]=str[l_space+j];
			    		y++;
			    	}
			    	l_space=space;
			    	States[g] = Integer.parseInt(new String(ch_num));
			    	g = g + 1;	
			    	i = i + num_len;
			    }

		    	System.out.println("States are:");
		    	for (int j = 0; j<States.length; j++) System.out.println(States[j]);
			    
		    	//Getiing Start State
			    
		    	line = sc.nextLine();
			    str = line.toCharArray();
		    	int S_State = Integer.parseInt(new String(str));
		    	System.out.println("Start State is:");
		    	System.out.println(S_State);
		    	
		    	//Getting number of final states and final states
		    	
		    	line = sc.nextLine();
		    	int N_F_States;
		    	str = line.toCharArray();
		    	k = 0;
		    	while (true) {
		    		if (str[k] == ' ') break;
		    		else k = k + 1;
			    }
		    	char [] ch = new char[k];
		    	for (int i=0; i<k; i++) {
		    		ch[i] = str[i];
		    	}
		    	N_F_States = Integer.parseInt(String.valueOf(ch));
		    	System.out.println("Number of final States:");
		    	System.out.println(N_F_States);
		    	
		    	
		    	int [] F_States = new int[N_F_States];
			    str = line.toCharArray();
			    l_space = k;
			    space = 0;
			    g = 0;
			    for (int i=k+1; i<=line.length(); i++) {
			    	boolean flag = true;
			    	int t = i;
			    	while (flag&&t<line.length()) {
			    		if ((str[t] == ' ')||(t==line.length())) flag = false;
			    		else t = t+1;
			    	}
	    			space = t;
			    	int num_len = space-l_space-1;
			    	char [] ch_num = new char[num_len];
			    	int y = 0;
			    	for (int j = 1; j<=num_len; j++) {
			    		ch_num[y]=str[l_space+j];
			    		y++;
			    	}
			    	l_space=space;
			    	F_States[g] = Integer.parseInt(new String(ch_num));
			    	g = g + 1;	
			    	i = i + num_len;
			    }
		    	System.out.println("Final States are:");
		    	for (int i = 0; i<F_States.length; i++) System.out.println(F_States[i]);
			    
		    	//Getting transmission matrix
			    
		    	int n = States.length;
		    	char [][] TransmissionMatrix = new char [States.length][States.length];
		    	for(int i = 0; i < n ; i++) {
		    		for(int j = 0; j < n ; j++) {
		    			TransmissionMatrix[i][j] = '0';
		    		}
		    	}
		    	int row=0;
		    	char rule = '0';
		    	int col=0;
		    	while(sc.hasNextLine()) {
			    	line = sc.nextLine();
				    str = line.toCharArray();
				    l_space = -1;
				    k=0;
				    space = 0;
				    g = 0;
				    int p = 0;
				    for (int l=k+1; l<=line.length(); l++) {
				    	boolean flag = true;
				    	int t = l;
				    	while (flag&&t<line.length()) {
				    		if ((str[t] == ' ')||(t==line.length())) flag = false;
				    		else t = t+1;
				    	}
		    			space = t;
				    	int num_len = space-l_space-1;
				    	char [] ch_num = new char[num_len];
				    	int y = 0;
				    	for (int j = 1; j<=num_len; j++) {
				    		ch_num[y]=str[l_space+j];
				    		y++;
				    	}
				    	l_space=space;
				    	if (p == 2) {
					    	col = Integer.parseInt(new String(ch_num));
					    	g = g + 1;	
					    	l = l + num_len;
				    	}
				    	if (p == 1) {
					    	rule = ch_num[0];
					    	g = g + 1;	
					    	l = l + num_len;
					    	p++;
				    	}
				    	if (p == 0) {
					    	row = Integer.parseInt(new String(ch_num));
					    	g = g + 1;	
					    	l = l + num_len;
					    	p++;
				    	}
				    }
			    	TransmissionMatrix[row-1][col-1] = rule;
		    	}
		    	System.out.println("Transmission matrix is:");
		    	for(int i = 0; i < n ; i++) {
		    		for(int j = 0; j < n ; j++) {
		    			System.out.print(TransmissionMatrix[i][j]);
		    			System.out.print(' ');
		    		}
		    		System.out.println();
		    	}
		    	
		    	sc.close();
		    	
		    	// Finding Unreachable states
		    	
		    	boolean [] UnreachableStates = new boolean [States.length];
		    	for (int i = 0; i< States.length; i++)
		    		UnreachableStates[i] = false;
		    	for (int i = 1; i < States.length; i++) {
		    		boolean r = false;
		    		for(int j = 0; j < States.length; j++) 
		    			if(TransmissionMatrix[j][i]!='0') {
		    				r = true;
		    			}
		    		if (!r) UnreachableStates[i] = true;
		    	}
		    	
		    	// Deleting Unreachable States
		    	
		    	for (int i = 0; i < States.length; i++)
		    		if (UnreachableStates[i] == true) {
		    			for(int j = 0; j < States.length; j++)
		    				TransmissionMatrix[i][j] = '0';
		    		};
		    		
		    	// Print Unreachable States
	    		System.out.println("Unreachable States:");
	    		for (int i = 0; i < States.length; i++)
		    		if (UnreachableStates[i]) 
		    			System.out.println(States[i]);
	    		
		    	// Print Matrix without Unreachable States
		    	System.out.println("Transmission matrix without Unreachable is:");
		    	for(int i = 0; i < n ; i++) {
		    		for(int j = 0; j < n ; j++) {
		    			System.out.print(TransmissionMatrix[i][j]);
		    			System.out.print(' ');
		    		}
		    		System.out.println();
		    	}
		    	
		    	// Finding useless states
			    	boolean [] Useless_States = new boolean[States.length];
			    	boolean [] State_Changed = new boolean[States.length];
			    	for (int i = 0; i< States.length; i++)
			    		Useless_States[i] = true;
			    	for (int i = 0; i< States.length; i++)
			    		State_Changed[i] = false;
		    	for (int i = 0; i< F_States.length; i++) {
		    		Useless_States[F_States[i]-1] = false;
		    	}
		    	for (int l = 0; l < States.length - 1; l++)
		    	{
			    	for (int i = 0; i < States.length; i++) {
			    		if (Useless_States[i] == false && State_Changed[i] == false) {
			    			State_Changed[i] = true;
			    			for (int j = 0; j < States.length; j++) {
			    				 if (TransmissionMatrix[j][i] != '0') {
			    					 Useless_States[j] = false;
			    				 }
			    			}
			    		}
			    	}
		    	}
		    	for (int i = 0; i < Useless_States.length; i++)
		    		if (UnreachableStates[i]) Useless_States[i] = false;
	    		System.out.println("Useless States:");
	    		for (int i = 0; i < Useless_States.length; i++)
	    			if (Useless_States[i]) System.out.println(States[i]);
	    		
	    		// Deleting Useless States
		    	for (int i = 0; i < States.length; i++)
		    		if (Useless_States[i]) {
		    			for(int j = 0; j < States.length; j++) {
		    				TransmissionMatrix[i][j] = '0';
		    				TransmissionMatrix[j][i] = '0';
		    			}
		    		};
		    		
	    		//Print Matrix without Useless States
		    	System.out.println("Transmission matrix without useless States is:");
		    	for(int i = 0; i < n ; i++) {
		    		for(int j = 0; j < n ; j++) {
		    			System.out.print(TransmissionMatrix[i][j]);
		    			System.out.print(' ');
		    		}
		    		System.out.println();
		    	}
		    	
		    	//Equal States
		    	for(int i = n - 1; i >= 0 ; i--) {
		    		for(int j = 0; j < n ; j++) {
		    			for (int l = 0; l < n; l++) {	    				
		    						
		    				if (TransmissionMatrix[j][i] == TransmissionMatrix[l][i]&&TransmissionMatrix[j][i] != '0'&& j!=l) {
		    					for (int ii = 0; ii < n; ii++) {
		    						if (TransmissionMatrix[ii][j] != '0') {
		    							TransmissionMatrix[ii][l] = TransmissionMatrix[ii][j]; 
		    							TransmissionMatrix[ii][j] = '0';
		    						}
		    						if (TransmissionMatrix[j][ii] != '0') {
		    							TransmissionMatrix[l][ii] = TransmissionMatrix[j][ii];
		    							TransmissionMatrix[j][ii] = '0';
		    						}
		    					}
		    				}
		    			}
		    		}
		    	}
		    	
		    	//Print Minimized Matrix
		    	System.out.println("Minamized matrix is:");
		    	for(int i = 0; i < n ; i++) {
		    		for(int j = 0; j < n ; j++) {
		    			System.out.print(TransmissionMatrix[i][j]);
		    			System.out.print(' ');
		    		}
		    		System.out.println();
		    	}

		    	
		    	
		    	File out = new File("out.txt");
		    	BufferedWriter writer = new BufferedWriter(new FileWriter(out));
		    	for (char c : Alph)
		    		writer.write(c + " ");
		    	writer.newLine();
		    	
		    	for(int i = 0; i < n ; i++) {
		    		for(int j = 0; j < n ; j++) {
		    			if(TransmissionMatrix[i][j]!='0') 
		    				writer.write(States[i] + " ");		    			
		    		}
		    	}
		    	
				for (int i = 0; i<F_States.length; i++) 
					writer.write(F_States[i]+" ");
				
				writer.newLine();
				writer.write(N_F_States + " ");
				for (int i = 0; i<F_States.length; i++) 
					writer.write(F_States[i]+" ");
				writer.newLine();
				for(int i = 0; i < n ; i++) {
		    		for(int j = 0; j < n ; j++) {
		    			if(TransmissionMatrix[i][j]!='0') {
		    				writer.write(States[i] + " " + TransmissionMatrix[i][j] + " " + States[j] + "");
		    				writer.newLine();
		    				}		    			
		    		}
		    	}
				
		    	writer.close();
		    	
		    	
		    	} 
	
				
	
	}


