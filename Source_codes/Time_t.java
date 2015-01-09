
public class Time_t {

	int hr,mn,se,temp2;
	String[] temp;
	
	public Time_t(String in){
		
		temp = in.split(":");
		
		this.hr = Integer.parseInt(temp[0]);
		this.mn = Integer.parseInt(temp[1]);
		this.se = Integer.parseInt(temp[2]);
		
	}
	
	public boolean equals(Time_t a){
		
		int ah,am,as;
		
		ah = a.hr;  
		am = a.mn; 
		as = a.se; 
		
		if( (ah == this.hr) && (am == this.mn) && (as == this.se)){
			return true;
		}else{
			return false;
		}
		
		
	}
	
	public Time_t add(Time_t addTime){
		
		int temp_m = 0,temp_h = 0;
		
		Time_t retTime = new Time_t("0:0:0");
		
		//seconds
		temp2 = this.se + addTime.se;
		
		if(temp2 >= 60){
			
			temp_m = temp2 / 60;
			retTime.se = temp2 % 60;
					
		}else{
			
			retTime.se = temp2;
			
		}
		
		//mnts
		
		temp2 = this.mn + temp_m + addTime.mn;
		
		if(temp2 >= 60){
			
			temp_h = temp2 / 60;
			retTime.mn = temp2 % 60;
					
		}else{
			
			retTime.mn = temp2;
			
		}
		//hrs
		
		temp2 = this.hr + temp_h + addTime.hr;
		
		if(temp2 >= 24){
			
			retTime.hr = temp2 % 24;
					
		}else{
			
			retTime.hr = temp2;
			
		}
		return retTime;
		
		 
	}
	
public void addto(Time_t addTime){
		
		int temp_m = 0,temp_h = 0;
		
		//seconds
		temp2 = this.se + addTime.se;
		
		if(temp2 >= 60){
			
			temp_m = temp2 / 60;
			this.se = temp2 % 60;
					
		}else{
			
			this.se = temp2;
			
		}
		
		//mnts
		
		temp2 = this.mn + temp_m + addTime.mn;
		
		if(temp2 >= 60){
			
			temp_h = temp2 / 60;
			this.mn = temp2 % 60;
					
		}else{
			
			this.mn = temp2;
			
		}
		//hrs
		
		temp2 = this.hr + temp_h + addTime.hr;
		
		if(temp2 >= 24){
			
			this.hr = temp2 % 24;
					
		}else{
			
			this.hr = temp2;
			
		}
		
		 
	}
}
