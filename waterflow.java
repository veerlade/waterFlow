
import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
class Nodev 
{
    int pathcost;
    String name;
    List<String[]> time = new ArrayList<String[]>();
    public Nodev(int p, String s)
    {
        pathcost=p;
        name=s;
    }
    public void update(int p,String s)
    {
       pathcost=p;
        name=s; 
    }
    public void addtime(String t[])
    {
        time.add(t);
    }
    public boolean timecheck(int t, String a)
    {
        int i=0;
        for(i=0;i<time.size();i++)
        {
            if(time.get(i)[24].equals(a))
            break;
        }
        if(time.get(i)[t%24]=="1"&& i<time.size())
        return true;
        return false;
    }
    /*public int[] gettime(String a)
    {
        String[] t=new String[25];
        int search=(int)a.charAt(0);
        for(int k=0;k<integerList.size();k++)
        {
            t=integerList.get(k);
            if(t[24]==search)
            return t;
        }
        return t;
    }*/
    @Override
    public String toString()
    {
        String r=name+" , "+pathcost+" , "+time.size()+" , ";
        for(int i=0;i<time.size();i++)
        {
            r=r+(Arrays.toString(time.get(i))+" - ");
            
        }
        
        return r;
    }
    public String getinfo()
    {
        return name+" , "+pathcost;
    }
    
    //@Override
    //public int compare(Nodev car1,Nodev car2) 
    //{
      //  return Integer.valueOf(car1.pathcost).compareTo(Integer.valueOf(car2.pathcost));          
    //}
    

}
public class waterFlow 
{
    static Nodev getnode(String a, Nodev ap[])
    {
        Nodev tp= new Nodev(0,"0");
        for(int i=0;i<ap.length;i++)
        {
            if(a.equals(ap[i].name))
            return ap[i];
        }
        return tp;
    }
    static Nodev getfinalnode(Nodev ap[],String dnode[])
    {
        Boolean t=true;
        int max=-1;
        Nodev newbie=new Nodev(0,"0");
        for(int i=0;i<dnode.length;i++)
        {
            for(int k=0;k<ap.length;k++)
            {
                if(dnode[i]== ap[k].name)
                {
                    if(max==-1)
                    max=ap[k].pathcost;
                    else
                    {
                        if(max==ap[k].pathcost)
                        {
                            int j=ap[k].name.compareTo(newbie.name);
                            if(j<0)
                            t=true; 
                            else
                                t=false;
                        }
                        if(ap[k].pathcost!=-1)
                        max=(max>ap[k].pathcost) ? ap[k].pathcost : max;
                        else;
                    }
                    if(max==ap[k].pathcost && t)
                        newbie=ap[k];
                }
                t=true;
            }
        }
        return newbie;
        
    }
    static String getfirst(String a)
    {
        int b=a.indexOf("-");
        if(b==-1)
        return a;
        else
        return a.substring(0,b);
    }
    static int namematch(String a,String b[])
    {
         for(int i=0;i<b.length;i++)
         {
           if(a.equals(b[i]))
           return i;
         }
         return -1;
    }
    static boolean destination(String a, String b[])
    {
        for(int i=0;i<b.length;i++)
         {
           if(a.equals(b[i]))
           return true;
         }
         return false;
    }
    static String[] settime(String pipe)
    {
        String time[]=new String[25];
        String pipes[]=pipe.split(" ");
        int i=Integer.parseInt(pipes[3]);
        
        int temp=4;
        if(i==0)
        {
            for(int k=0;k<time.length;k++)
            {
                if(time[k]==null)
                time[k]="1";
            }
            time[24]=pipes[1];
            return time;
        }
        else
        for(int j=0;j<i;j++)
        {
            int ptr=pipes[temp].indexOf("-");
            int t1=(Integer.parseInt(pipes[temp].substring(0,ptr)));
            int t2=(Integer.parseInt(pipes[temp++].substring(ptr+1)));
            while(t2>=t1)
            {
                time[(t1)%24]="0";
                t1++;
            }
            time[24]=pipes[1];
        }
        for(int k=0;k<time.length;k++)
        {
            if(time[k]==null)
                time[k]="1";
        }
        return time;
    }
    static int count(String a,char b)
    {
      int counter=0;
      for(int i=0;i<a.length();i++)
      {
          if(a.charAt(i)==b)
              counter++;
      }
      return counter;
    }
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        File f = new File("output.txt");
        FileOutputStream fos = new FileOutputStream(f);
        PrintWriter pw = new PrintWriter(fos);
        //Scanner s = new Scanner(new File("filepath"));
//ArrayList<String> list = new ArrayList<String>();
//while (s.hasNext()){
  //  list.add(s.next());
        File inFile=new File(args[1]);
        BufferedReader br = null;
        List<String> input = new ArrayList<String>();
        try 
        {
            br = new BufferedReader(new FileReader(inFile));
            
            	while(true)
            	{
            		String s=br.readLine();
            		if(s!=null)
            		input.add(s);
            		else 
            		break;
            	}
            	//int i=0;
                //while ((input.add(br.readLine()) != null)
                //i++;
            	//if(br.readLine()!=null)
            	//input.add(br.readLine());
            	//else
            	//break;
            
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            try 
            {
            if (br != null)br.close();
            } 
            catch (IOException ex) 
            {
            ex.printStackTrace();
            }
        }
        //for(int j=0;j<input.length && input[j]!=null;j++)
        //{
            //System.out.println(input[j]);
        //}
        for(int tp=0;tp<input.size();tp++)
        {
        	//System.out.println(input.get(tp));
        }
        int finalcount=Integer.parseInt(input.get(0));
        int cnt=0;
        while(finalcount>0)
        {
        cnt++;
        String algoname=input.get(cnt++);
        //System.out.println(algoname);
        String snodef[]=input.get(cnt++).split(" ");
        String snode=snodef[0];
        //System.out.println(snode+",");
        String dnode[]=input.get(cnt++).split(" ");
        //System.out.println(Arrays.toString(dnode));
        String mnode[]=null;
        if(!("".equals(input.get(cnt))))
        {
             mnode=input.get(cnt++).split(" ");
             //System.out.println(Arrays.toString(mnode));
        }
        else;
        int pipes;
        //System.out.println(input.get(cnt));
        if(!("".equals(input.get(cnt))))
        {
          String popo[]=input.get(cnt).split(" ");
           pipes=Integer.parseInt(popo[0]);  
        }
        else
        {
          String popo[]=input.get(++cnt).split(" ");
         pipes=Integer.parseInt(popo[0]);
        }
          cnt++;
        int length;
        //System.out.println(input.get(cnt-2));
        if("".equals(input.get(cnt-2)))
        {
             length=1+dnode.length;
        }
        else
        length=1+mnode.length+dnode.length;
        //System.out.println(length);
        String name[]=new String[length];
        int p=0;
        int visited[]=new int[length];
        name[p++]=snode;
        if(mnode!=null)
        {
            for(int i1=0;i1<mnode.length;i1++)
            {
                name[p]=mnode[i1];
                System.out.println(name[p++]);
                
                
            }
        }
        for(int i1=0;i1<dnode.length;i1++)
        {
            if(p<length)
            {
            name[p++]=dnode[i1];
            }
        }
        //System.out.println(Arrays.toString(name));
        int arr[][]=new int[length][length];
        for(int k=0;k<length;k++)
        {
            for(int l=0;l<length;l++)
            {
                arr[k][l]=-1;
            }
        }
        String time[]=null;
        String pipe[]=null;
        for(int i=0;i<pipes;i++)
        {
            //System.out.println(cnt);
            pipe=input.get(cnt++).split(" ");
            //System.out.println(pipe[0]+" , "+pipe[1]);
            int a=namematch(pipe[0],name);
            int b=namematch(pipe[1],name);
            //System.out.println(a+" "+b);
            if("BFS".equals(algoname)||"DFS".equals(algoname))
                arr[a][b]=1;
            else
            {
                //System.out.println(pipe[2]);
                arr[a][b]=Integer.parseInt(pipe[2]);
            }
        }
        for (int[] arr1 : arr) 
        {
            //System.out.println(Arrays.toString(arr1));
        }
        int stime=Integer.parseInt(input.get(cnt++));
        if("BFS".equals(algoname))
        {
        	
            try
            {
            String a;
            List<String> myList = new ArrayList<String>();
            List<String> sortList= new ArrayList<String>();
            int count1=0;
            myList.add(name[0]);
            int up=namematch(getfirst(name[0]),name);
            visited[up]=1;
            //System.out.println(myList.get(0));
            while(!myList.isEmpty())
            {
                if (destination(getfirst(myList.get(0)),dnode))
                {
                    for(int k=0;k<myList.size();k++)
                    {
                        //System.out.println(myList.get(k));
                    }
                    
                    count1 = count(myList.get(0),"-".charAt(0));
                    //System.out.println(myList.get(0)+" Solution Reached "+count1);
                    break;
                }
                else
                {
                    for(int k=0;k<myList.size();k++)
                    {
                        //System.out.println(myList.get(k));
                    }
                    a=myList.remove(0);
                    int i=namematch(getfirst(a),name);
                    visited[i]=1;
                    for(int j=0;j<length;j++)
                    {
                        int yp=namematch(name[j],name);
                        if(arr[i][j]==1 && visited[yp]!=1)
                        {
                           sortList.add(name[j]+"-"+a);
                            
                            
                        }
                    }
                    Collections.sort(sortList);
                    for(int k=0;k<sortList.size();k++)
                    {
                        myList.add(sortList.get(k));
                    }
                    sortList.clear();
                    
                }
            }
            //System.out.println(Arrays.toString(visited));
            
            if(!myList.isEmpty())
            {
                count1+=stime;
                String solution=getfirst(myList.get(0))+" "+count1%24;
                pw.write(solution+System.getProperty("line.separator"));
                pw.flush();
                
            }
            else
                pw.write("None"+System.getProperty("line.separator"));
            	pw.flush();
            }
            catch(Exception e)
            {
            	pw.write("None"+System.getProperty("line.separator"));
            	pw.flush();
            }
        	
        }
            
            

        
        else if("DFS".equals(algoname))
        {
        	//try
        	//{
        	String a;
            List<String> myList = new ArrayList<String>();
            List<String> sortList= new ArrayList<String>();
            int count1=0;
            myList.add(name[0]);
            
            
            System.out.println(myList.get(0));
            while(!myList.isEmpty())
            {
                if (destination(getfirst(myList.get(0)),dnode))
                {
                    for(int k=0;k<myList.size();k++)
                    {
                        //System.out.println(myList.get(k));
                    }
                    
                    count1 = count(myList.get(0),"-".charAt(0));
                    //System.out.println(myList.get(0)+" Solution Reached "+count1);
                    break;
                }
                else
                {
                    for(int k=0;k<myList.size();k++)
                    {
                        //System.out.println(myList.get(k));
                    }
                    a=myList.remove(0);
                    int i=namematch(getfirst(a),name);
                    visited[i]=1;
                    for(int j=0;j<length;j++)
                    {
                        int yp=namematch(name[j],name);
                        if(arr[i][j]==1 && visited[yp]!=1)
                        {
                           sortList.add(name[j]+"-"+a);
                            //System.out.println("test");
                            
                        }
                    }
                    Collections.sort(sortList);
                    for(int k=0;k<sortList.size();k++)
                    {
                        //System.out.println(sortList.get(k));
                    }
                    
                    for(int k=sortList.size()-1;k>=0;k--)
                    {
                        myList.add(0,sortList.get(k));
                        //System.out.println("test1");
                        
                    }
                    for(int k=0;k<myList.size();k++)
                    {
                        //System.out.println(myList.get(k));
                    }
                    sortList.clear();
                    
                }
            }
            //System.out.println(Arrays.toString(visited));
            if(!myList.isEmpty())
            {
                count1+=stime;
                String solution=getfirst(myList.get(0))+" "+count1%24;
                pw.write(solution+System.getProperty("line.separator"));
                pw.flush();
            }
            else
                pw.write("None"+System.getProperty("line.separator"));
            	pw.flush();
        	//}
        	//catch(Exception e)
        	//{
        		//pw.write("None"+System.getProperty("line.separator"));
        	//}
            
        }
        else if("UCS".equals(algoname))
        {
        	try
        	{
            PriorityQueue<Nodev> myList = new PriorityQueue<Nodev>(new Comparator<Nodev>()
            {
               public int compare(Nodev car1,Nodev car2) 
                {
                    if(Integer.valueOf(car1.pathcost).compareTo(Integer.valueOf(car2.pathcost))!=0)
                    return Integer.valueOf(car1.pathcost).compareTo(Integer.valueOf(car2.pathcost));
                    else
                    return car1.name.compareTo(car2.name);
                } 
            });
            List<String> sortList= new ArrayList<String>();
            Nodev op[]=new Nodev[name.length];
            for(int ik=0;ik<name.length;ik++)
            {
                op[ik]=new Nodev(-1,name[ik]);
                //System.out.println(op[ik].getinfo());
            }
            int tmp=cnt-1-pipes;
            //System.out.println(tmp);
            for(int i=tmp;i<tmp+pipes;i++)
            {
                String a[]=settime(input.get(i));
                getnode(input.get(i).substring(0,input.get(i).indexOf(" ")),op).addtime(a);
            }
            for(int i=0;i<op.length;i++)
            {
              //System.out.println(op[i]);
            }
            int count1=0;
            int cost[]=new int[length];
            op[0].update(0,name[0]);
            myList.add(op[0]);
            //System.out.println(myList.peek().toString());
            while(!myList.isEmpty())
            {
                Nodev po=myList.remove();
                int up=namematch(getfirst(po.name),name);
                visited[up]=1;
                //System.out.println(po.name);
                int marker=namematch(getfirst(po.name),name);
                for(int i=0;i<arr.length;i++)
                {
                  if(arr[marker][i]!=-1 && visited[i]!=1)
                  {
                      //System.out.println(arr[marker][i]+po.pathcost+" - "+name[i]+"-"+po.name);
                      Nodev tomp=getnode(name[i],op);
                      
                      if(tomp.pathcost==-1 && po.timecheck(po.pathcost+stime,tomp.name))
                      {
                          tomp.update(arr[marker][i]+po.pathcost, name[i]);
                          myList.add(tomp);
                          
                      }
                      else if(tomp.pathcost>arr[marker][i]+po.pathcost && po.timecheck(po.pathcost+stime,tomp.name))
                      tomp.update(arr[marker][i]+po.pathcost, name[i]);
                      
                      
                      
                      for(int k=0;k<myList.size();k++)
                    {
                        //System.out.println(myList.toString());
                    }
                  }
                }
                
            }
            
            //System.out.println(Arrays.toString(visited));
            if(myList.isEmpty())
            {
                Nodev fnal=getfinalnode(op,dnode);
                if(fnal.pathcost!=-1)
                {pw.write(fnal.name+" "+(stime+fnal.pathcost)%24+System.getProperty("line.separator"));
                pw.flush();}
                else
                {pw.write("None"+System.getProperty("line.separator"));
                pw.flush();}
            }
            else
                pw.write("None"+System.getProperty("line.separator"));
            	pw.flush();
          
        	}
        	catch(Exception e)
        	{
        		pw.write("None"+System.getProperty("line.separator"));
        		pw.flush();
        	}
        }
        else
        {
             
            pw.write("None"+System.getProperty("line.separator"));
            pw.flush();
            
        }
        finalcount--;
        
        }
    //pw.flush();
    fos.close();
    pw.close();
    //pw.
    }
}
