import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;
import junit.framework.*;
import static org.mockito.Mockito.*; 

class Visitor{         //class for visitor
	
	String type;
	
	Visitor(String type){    //constructor
		
		this.type = type;
	}
	
	Visitor(){}             //constructor
}


public class CitySim9002 {

	
	HashMap<String, List<String>> like;
	HashMap<String, List<String>> dislike;
	List<String> location;
	List<String> types;
	int seed;
	
	public void executeEntrance(){        
		
	 Scanner input = new Scanner(System.in);
	 String s = input.nextLine();
	 
	 try{
			int seed = Integer.parseInt(s);
			
			if(seed > 0)
				System.out.println("Welcome to CitySim! Your seed is" + " " + String.valueOf(seed) + ".");
			
			else{
				
				throw new Exception("Please enter one integer argument, seed");
		    }
		}
		
		catch(Exception e){
			
			System.out.println("Please enter one integer argument, seed");
			return;
		}

	
	  Visitor[] visitors = new Visitor[5];
	  
	  for(int i = 0; i < visitors.length; i++){
		  visitors[i] = new Visitor();
	  }
	  
	  
	   Random rd1 = new Random(seed);
		int i = 1;
		
		while(i <= 5){
			
		
		int num1 = rd1.nextInt(4);
		visitors[i - 1].type = types.get(num1);
		System.out.println("Visiter" + String.valueOf(i) +" " + "is a " + types.get(num1));
	
		int num2 = 0;
		Boolean first = true;
		Random rd2 = new Random(seed);
		
		while(true){
			
			num2 = rd2.nextInt(5);
			
		 if(first && num2 == 4){
			 
			num2 = rd2.nextInt(4);
		    first = false;
		 }
		 
		 if(num2 != 4){
			 
			 System.out.println("Visitor" + String.valueOf(i) +" "+ "is going to " + generateRan(num2) + "!");
			 System.out.println(visit(visitors, i, generateRan(num2)));
			 
		 }else{
			 
			 System.out.println("Visitor" + String.valueOf(i) +" " + generateRan(num2));
			 System.out.println("***");
			 break;
		 }
		 
	} 
		 i++;
  }//end while1
		
		return;
	}
	
	
	@Test
	// we need to make sure the excuteEntrance method behaves correctly.
	
	public void testExecuteEntrance(){
		
		CitySim9002 test = mock(CitySim9002.class);
		verify(test).executeEntrance();   
		
	}
	
	
	public  String generateRan(int n){
		
		switch(n){
		
		case 0 :
		  return location.get(0);
		  
		case 1:
		  return location.get(1);
		
		case 2:
		  return location.get(2);
		 
		case 3:
		  return location.get(3);
		
		case 4:
		  return "has left the city!";
		  
		default:
		  return "";
		}
		
	}
	
	@Test
	// First we need to guarantee all the input value is integer.
	//Then we should test whether it is the correct output for each input integer
	//If the integer is 4, then it should output"has left the city"
	//If the integer is between 0 - 3, then it should output the corresponding location
	//Other integers including the negative integers should output nothing ""
	
	 public void testGenerateRan(){
		
		when(generateRan(anyInt())).thenThrow(new InputMismatchException());  //stub test
						
		assertEquals(location.get(1),generateRan(1));
		assertEquals("has left the city",generateRan(4));
		assertEquals("",generateRan(1000));
		assertEquals("",generateRan(-5));
	}
	
	
	
	public String visit(Visitor[] visitor,int index, String location){
		
		
	
			if(like(visitor[index - 1],location))
				return  "visitor" + String.valueOf(index) + " did like " + location + ".";
						
			else if(dislike(visitor[index - 1],location))
			    return "visitor" + String.valueOf(index) + " did not like " + location + ".";
			
			
			return "";
		
		}
	
	
	@Test
	//We test whether or not the method 'visit' works well
	//If the location is contained in the Map 'dislike'  or Map 'like', then the corresponding string should be returned
	//If the index is OutOfBound, then it will output nothing.
	//If the location is not contained in the Map, then it will return nothing "";
	
	public void testVisit(){
		
		Visitor [] visitors = mock(Visitor[].class);
		
		   //doubles test
		int index = 1;
		visitors[index - 1] = mock(Visitor.class);
		visitors[index - 1].type = "Student";
		
		int index2 = 3;
		visitors[index2 - 1] = mock(Visitor.class);
		visitors[index2 - 1].type = "Blogger";
		
		int index3 = 6;
		visitors[index3 - 1] = mock(Visitor.class);
		visitors[index3 - 1].type = "Professor";
		
		
		
		assertEquals("", visit(visitors, -1, " "));
		assertEquals("", visit(visitors, 1, "Time Square"));
		when(visit(visitors,index,"Downtown")).thenReturn("Visitor1 did like Downtown."); //test stubs
		when(visit(visitors,index2, "The Cathedral of Learning")).thenReturn("Visitor3 did not like The Cathedral of Learning."); //test stubs
		when(visit(visitors,index3, "The Cathedral of Learning")).thenReturn(""); //test stubs
		
	}
	
	
	public boolean like(Visitor visiter, String location){
				
		
		if(like.containsKey(visiter.type)){
			
			if (like.get(visiter.type).contains(location))
				return true;
		}
		
		  return false;
		
		}
	
	
	@SuppressWarnings("deprecation")
	@Test          
	//we need to test whether the 'like' method works correctly;
	//the location need to be contained in the like map, then return true;
	//All the other conditions return false.
	
	public void testLike(){
		
		Visitor visitor1 = mock(Visitor.class); //test doubles
		Visitor visitor2 = mock(Visitor.class); //test doubles
		Visitor visitor3 = mock(Visitor.class); //test doubles
		Visitor visitor4 = mock(Visitor.class); //test doubles
		
		visitor2.type = "Professor";
		visitor3.type = "Student";
		visitor4.type = "Blogger";
		
		when(like(visitor1,anyString())).thenThrow(new InputMismatchException()); //test stubs
		when(like(visitor2,anyString())).thenThrow(new InputMismatchException()); //test stubs
		when(like(visitor3,anyString())).thenThrow(new InputMismatchException()); //test stubs
		when(like(visitor4,anyString())).thenThrow(new InputMismatchException()); //test stubs
	
		
		Assert.assertFalse(visitor2.equals(visitor1));
		Assert.assertEquals(false, like(visitor1," "));
		Assert.assertEquals(false, like(visitor2,"Museum"));
		Assert.assertEquals(true,  like(visitor3,"Squirrel Hill"));
		Assert.assertEquals(false, like(visitor4, "The Cathedral of Learning"));
		
		
	}
	
	
	
	
	public boolean dislike(Visitor visiter, String location){
		
		if(dislike.containsKey(visiter.type)){
			
			if(dislike.get(visiter.type).contains(location))
				return true;
			
		}
		
		return false;
	}
	
 @SuppressWarnings("deprecation")
 @Test                          //test doubles
	
    //we need to test whether the 'dislike' method works correctly;
	//the location need to be contained in the dislike map, then return true;
	//All the other conditions return false.
 
    public void testDislike(){
	 
		Visitor visitor1 = mock(Visitor.class); //test doubles
		Visitor visitor2 = mock(Visitor.class); //test doubles
		Visitor visitor3 = mock(Visitor.class); //test doubles
		Visitor visitor4 = mock(Visitor.class); //test doubles
		Visitor visitor5 = mock(Visitor.class); //test doubles
		
		when(dislike(visitor1,anyString())).thenThrow(new InputMismatchException()); //test stubs
		when(dislike(visitor2,anyString())).thenThrow(new InputMismatchException()); //test stubs
		when(dislike(visitor3,anyString())).thenThrow(new InputMismatchException()); //test stubs
		when(dislike(visitor4,anyString())).thenThrow(new InputMismatchException()); //test stubs
		when(dislike(visitor5,anyString())).thenThrow(new InputMismatchException()); //test stubs
		
		visitor1.type = "Business Person";
		visitor2.type = "Professor";
		visitor3.type = "Student";
		visitor4.type = "Blogger";
		visitor5.type = "Business Person";
	    
	        Assert.assertFalse(visitor5.equals(visitor1));
		Assert.assertEquals(false, dislike(visitor1," "));
		Assert.assertEquals(false, dislike(visitor2,"Gym"));
		Assert.assertEquals(true, dislike(visitor3,"The Cathedral of Learning"));
		Assert.assertEquals(true, dislike(visitor4, "The Cathedral of Learning"));
		Assert.assertEquals(true, dislike(visitor5, "The Point"));
	        Assert.assertEquals(false, dislike(visitor2, "Downtown"));
 }
  
  
	
	
	public static void main(String args[]){
	
	  CitySim9002 test = new CitySim9002();
	    
	    test.like = new HashMap<>();
	    test.dislike = new HashMap<>();
	    
	   List<String> stu = new ArrayList<>();
	   stu.add("Squirrel Hill");
	   stu.add("Downtown");
	   stu.add("The Point");
	   test.like.put("Student",stu);
	   
	   List<String> busip = new ArrayList<>();
	   busip.add("Squirrel Hill");
	   busip.add("Downtown");
	   test.like.put("Business Person",busip);
	   
	   List<String> prof = new ArrayList<>();
	   prof.add("The Cathedral of Learning");
	   prof.add("Squirrel Hill");
	   prof.add("The Point");
	   prof.add("Downtown");
	   test.like.put("Professor", prof);
	   
	   List<String> stu2 = new ArrayList<>();
	   stu2.add("The Cathedral of Learning");
	   test.dislike.put("Student",stu2);
	   
	   List<String> busip2 = new ArrayList<>();
	   busip2.add("The Cathedral of Learning");
	   busip2.add("The Point");
	   test.dislike.put("Business Person", busip2);
	   
	   List<String> blggr = new ArrayList<>();
	   blggr.add("The Cathedral of Learning");
	   blggr.add("Squirrel Hill");
	   blggr.add("The Point");
	   blggr.add("Downtown");
	   test.dislike.put("Blogger",blggr);
	   
	   test.location = new ArrayList<>();
	   test.types = new ArrayList<>();
	   
	   test.location.add("The Cathedral of Learning");
	   test.location.add("Squirrel Hill");
	   test.location.add("The Point");
	   test.location.add("Downtown");
	   
	   test.types.add("Student");
	   test.types.add("Professor");
	   test.types.add("Business Person");
	   test.types.add("Blogger");
	   
	    test.executeEntrance();
	}
}
