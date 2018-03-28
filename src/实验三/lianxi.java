import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class lianxi {
    public static void main(String[] args) throws IOException {
    	    	//封装数据源
    	        BufferedReader br_a = new BufferedReader(new FileReader("answer.txt"));
    	        BufferedReader br_r = new BufferedReader(new FileReader("result.txt"));
    	       //封装目的地
    	        ArrayList<String> answer = new ArrayList<String>();
    	        ArrayList<String> result = new ArrayList<String>();
    	        //读取数据写到集合中
    	      //  System.out.print("*************************3");
    	        String line1 = null;
    	        String line2 = null;
    	       
    		    while((line1 = br_a.readLine())!= null){
    				answer.add(line1);
    			}
    			while((line2 = br_r.readLine())!= null){
    				//answer.add(line1);
    				result.add(line2);
    			}
    	        //释放资源
    			//br_a.close();
    			//br_r.close();	
    			//遍历进行比较
    			int i,j,count=0;
    			for(i=0;i<20;i++) {
    					String s1=answer.get(i);
    					String s2=result.get(i);
    					if(s1.equals(s2)) {
    						count++;
    				}
    			}
    			System.out.print(count);
    	    }
    	    
    	    
    		private Object line2(String readLine) {
    			// TODO Auto-generated method stub
    			return null;
    		}
    		private Object newFileReader1(String string) {
    			return null;
    		}
    }

