package it.antoniomallia.diennea.db_benchmark.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RandomEmail {

	private enum TLDs {
		IT, COM, NET, ORG
	};

	private enum Hosts {
		GOOGLE, YAHOO, HOTMAIL, EMAIL
	};

	public static String generateEmailAddress() {
		final SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();
		sb.append(new BigInteger(130, random).toString(32))
				.append("@")
				.append(Hosts.values()[random.nextInt(Hosts.values().length)]
						.name().toLowerCase())
				.append(".")
				.append(TLDs.values()[random.nextInt(TLDs.values().length)]
						.name().toLowerCase());

		return sb.toString();

	}
}
