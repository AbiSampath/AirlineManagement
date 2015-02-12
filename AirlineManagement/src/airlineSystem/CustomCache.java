package airlineSystem;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

import beans.FlightDetails;

public class CustomCache {
	private static final int CACHESIZE = 5050;

	Map<String, String> flightKeyMap = Collections
			.synchronizedMap(new LinkedHashMap<String, String>(200));

	Map<String, FlightDetails> flightCache = Collections
			.synchronizedMap(new LinkedHashMap<String, FlightDetails>(CACHESIZE));

	public Map<String, String> getFlightKeyMap() {
		return flightKeyMap;
	}

	public void setFlightKeyMap(Map<String, String> flightKeyMap) {
		this.flightKeyMap = flightKeyMap;
	}

	public Map<String, FlightDetails> getFlightCache() {
		return flightCache;
	}

	public void setFlightCache(Map<String, FlightDetails> flightCache) {
		this.flightCache = flightCache;
	}

	public FlightDetails[] getFlightFromCache(String flightKey) {
		FlightDetails[] flightArray = null;
		String flightIDs = flightKeyMap.get(flightKey);

		if (flightIDs == null) {
			return flightArray;
		}

		StringTokenizer tokenizer = new StringTokenizer(flightIDs, ",");

		int flightCount = tokenizer.countTokens();
		flightArray = new FlightDetails[flightCount];
		int index = 0;

		while (tokenizer.hasMoreTokens()) {
			FlightDetails flight = flightCache.get(tokenizer.nextToken());

			if (flight != null) {
				flightArray[index++] = flight;
			}
		}

		return flightArray;
	}

	public FlightDetails[] getAllFlights() {
		Iterator<FlightDetails> iter = flightCache.values().iterator();

		FlightDetails[] flights = new FlightDetails[flightCache.values().size()];
		int count = 0;

		while (iter.hasNext()) {
			flights[count++] = iter.next();
		}

		return flights;
	}
}
