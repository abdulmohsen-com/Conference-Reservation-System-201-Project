public class Date {
	
	private int day;
	private int month;
	private int year;
	
	
	public Date(int day, int month, int year) throws InvalidDateException{
			//Negatives + Zeros
		if(year < 0 || day <= 0 || month <= 0 ) {
			throw new InvalidDateException();
			//February (Leap Year)
		}else if(year % 4 == 0 && month == 2 && day > 29) {
			throw new InvalidDateException();
			//February (Leap Year)
		}else if(year % 4 != 0 && month == 2 && day > 28) {
			throw new InvalidDateException();
			//31 day months validated
		}else if((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day > 31) {
			throw new InvalidDateException();
			//30 day months validated
		}else if((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
			throw new InvalidDateException();
		}else {
			this.day = day;
			this.month = month;
			this.year = year;
		}
	}
	
	
	
	public int getDay() {
		return day;
	}



	public int getMonth() {
		return month;
	}



	public int getYear() {
		return year;
	}



	public boolean validate(Date fromDate, Date toDate) throws InvalidDateException{
		if(fromDate.getDay() == toDate.getDay() && fromDate.getMonth() == toDate.getMonth() && fromDate.getYear() == toDate.getYear()) {
			return true;
		}else {
			return false;
		}
	}
	
	public String toString() {
		return day + "/" + month + "/" + year;
	}
}
