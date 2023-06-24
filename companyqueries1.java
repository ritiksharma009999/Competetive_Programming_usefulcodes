
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class companyqueries1{
    public static int [] tree;
    public static int [][]table;
    static int N;
    static int max=19;

    public static void  build(int max){
        table=new int[max][N+1];

        for(int i=2;i<=N;i++){
            table[0][i]=tree[i];
        }
        for(int i=1;i<max;i++){
            for(int j=2;j<=N;j++){
                
                table[i][j]=table[i-1][table[i-1][j]];
            }
        }

    }
     

    public static int query(int a,int al){

       
      
        for(int i=0;i<max;i++ ){
            if(((1<<i)&al)>0){
              
                a=table[i][a];
                

            }
        }

        return a;

    }
    public static void main(String args[]) throws Exception{
        BufferedReader scn = new BufferedReader(new InputStreamReader(System.in));
        
        String arr[]=scn.readLine().split(" ");
        int n=Integer.parseInt(arr[0]);
        N=n;
        int t=Integer.parseInt(arr[1]);
      tree=new int [n+1];
      

       
        String trarr[]=scn.readLine().split(" ");
         tree[1]=0;
         for(int i=2;i<tree.length;i++){
             tree[i]=Integer.parseInt(trarr[i-2]);
         }
         //System.out.println(Arrays.toString(tree));

        build(max);
         
        for(int i=0;i<t;i++){
       
            String arr1[]=scn.readLine().split(" "); 
            int an=query(Integer.parseInt(arr1[0]),Integer.parseInt(arr1[1]));
            if(an==0)
            System.out.println(-1);
            else
            System.out.println(an);

        }

 





    }
}


