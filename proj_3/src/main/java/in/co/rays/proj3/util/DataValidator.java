package in.co.rays.proj3.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class validates input data
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public class DataValidator {
	/**
	 * Checks if value is Null
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNull(String val) {
		if (val == null || val.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is NOT Null
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNotNull(String val) {
		return !isNull(val);
	}

	/**
	 * Checks if value is an Integer
	 * 
	 * @param val
	 * @return
	 */

	public static boolean isInteger(String val) {

		if (isNotNull(val)) {
			try {
				int i = Integer.parseInt(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is Long
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isLong(String val) {
		if (isNotNull(val)) {
			try {
				long i = Long.parseLong(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is valid Email ID
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isEmail(String val) {

		String emailreg = "^[A-Za-z0-9](\\.?|_?[A-Za-z0-9]){5,}@(YAHOO|GMAIL|yahoo|gmail|rocketmail)(\\.COM|\\.com|\\.in|\\.IN)$";

		if (isNotNull(val)) {
			try {
				return val.matches(emailreg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is Date
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isDate(String val) {

		Date d = null;
		if (isNotNull(val)) {
			d = DataUtility.getDate(val);
		}
		return d != null;
	}

	/**
	 * Checks if value is Mobile
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isMobile(String val) {
		String namereg = "^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[6789]\\d{9}$";
		if (isNotNull(val)) {
			try {
				return val.matches(namereg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is valid Name
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isName(String val) {
		val = val.toLowerCase();
		String namereg = "[a-zA-Z ]+$";

		if (isNotNull(val)) {
			try {
				return val.matches(namereg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is valid Password
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isPassword(String val) {
		String namereg = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z])(?=.*[@|_|$]).*$";

		if (isNotNull(val)) {
			try {
				return val.matches(namereg);
			} catch (Exception e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is valid 12Hr Time
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isTime(String val) {
		String namereg = "(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)";

		if (isNotNull(val)) {
			try {
				return val.matches(namereg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks Age
	 * 
	 * @param val
	 * @return
	 */
	public static int getAge(String val) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String currentdate = sdf.format(d);
		val = val.replace("/", "");
		val = val.substring(4, 8);
		int age = 0;

		try {
			age = Integer.parseInt(currentdate) - Integer.parseInt(val);
			return age;
		} catch (NumberFormatException e) {
			return age;
		}

	}

	/**
	 * Checks if value is valid Roll Number
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isRollNo(String val) {
		String namereg = "^[0-9]{4}([a-z]{2}|[A-Z]{2})[0-9]{2}$";

		if (isNotNull(val)) {
			try {
				return val.matches(namereg);
			} catch (Exception e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value of first letter is capital
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isFirstLetterCapital(String val) {
		String namereg = "^[A-Z][w+( +\\w+)A-Z|a-z0-9_-]{0,19}$";

		if (isNotNull(val)) {
			try {
				return val.matches(namereg);
			} catch (Exception e) {
				return false;
			}

		} else {
			return false;
		}
	}

	public static boolean isSelect(String selected) {
		if (selected.equalsIgnoreCase("Select")) {
			return true;
		} else {
			return false;
		}
	}

}
