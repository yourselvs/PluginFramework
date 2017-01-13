package yourselvs.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
	private static SimpleDateFormat longFormat;
	private static SimpleDateFormat shortFormat;
	
	/**
	 * Formats date and time to readable strings.
	 * 
	 * @author yourselvs
	 */
	public DateFormatter(){
		longFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss.SS");
		shortFormat = new SimpleDateFormat("mm:ss.SS");		
	}
	
	/**
	 * @return	A formatter that formats dates in a long string.
	 */
    public SimpleDateFormat getLongFormatter() {return longFormat;}
    
    /**
     * @return	A formatter that formats dates in a short string.
     */
    public SimpleDateFormat getShortFormatter() {return shortFormat;}
    
    /**
     * Finds the difference between two times.
     * @param date1	The first date to subtract.
     * @param date2	The second date to subtract
     * @return	date1 - date2
     */
    public Date subtractTime(Date date1, Date date2){
		long time1 = date1.getTime();
		long time2 = date2.getTime();

		return new Date(time1 - time2);
	}
}
