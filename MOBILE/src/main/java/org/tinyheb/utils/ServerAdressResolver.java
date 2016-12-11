package org.tinyheb.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerAdressResolver {

	private Pattern pattern;
	private Matcher matcher;

	private static final String IPADDRESS_PATTERN =
			"^(http://)?([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
					"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
					"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
					"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	public ServerAdressResolver(){
		pattern = Pattern.compile(IPADDRESS_PATTERN);
	}

	/**
	 * Validate ip address with regular expression
	 * @param ip ip address for validation
	 * @return true valid ip address, false invalid ip address
	 */
	public boolean validate(final String ip){
		matcher = pattern.matcher(ip);
		return matcher.matches();
	}
	
	public String getIP(final String adress) {
		matcher = pattern.matcher(adress);
		return matcher.group(1);
	}
}
