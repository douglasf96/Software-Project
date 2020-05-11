package application;

import java.util.ArrayList;

/**
 * @author Jiexin.Luo
 * @date {2020/5/5}
 */
public class Testoutput {
	
	public ArrayList<Record> snapshot = null;
    public ArrayList<ArrayList<Record>> fullrecords = new ArrayList<>();
	
   public Testoutput(){
      
       snapshot = new ArrayList<>();
       snapshot.add(new Record("car","1","appear",1,1));
       snapshot.add(new Record("car","2","appear",2,2));
       fullrecords.add(snapshot);
       snapshot = new ArrayList<>();
       snapshot.add(new Record("car","2","move",1,2));
       snapshot.add(new Record("car","1","meet","car","2"));
       snapshot.add(new Record("car","1","stop"));
       snapshot.add(new Record("car","2","pass"));
       fullrecords.add(snapshot);
       snapshot = new ArrayList<>();
       snapshot.add(new Record("car","1","move",2,2));
       fullrecords.add(snapshot);
       snapshot = new ArrayList<>();
       snapshot.add(new Record("car","1","exit",2,2));
       snapshot.add(new Record("car","2","move",2,2));
       fullrecords.add(snapshot);
       snapshot = new ArrayList<>();
       snapshot.add(new Record("car","2","exit",2,2));
       fullrecords.add(snapshot);
       for(int i = 0;i<fullrecords.size();i++){
           System.out.println("round "+(i+1)+":");
           for(Record record:fullrecords.get(i)){
               System.out.println(record);
           }
       }
   }
}
