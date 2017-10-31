package com.marilene.utils;

import java.util.Random;

public class ReplyDispatcher {

	private static final Random RNG = new Random();
	
	public static String reply(String reply) {
		return reply;
	}
	
	public static String reply(String[] replies) {
		if(replies != null)
			return replies[RNG.nextInt(replies.length)];
		
		return null;
	}
	
	public static String mockReply(String reply) {
		StringBuilder mockReply = new StringBuilder();
		
		int coinToss;
		for(Character c : reply.toCharArray()) {
			coinToss = RNG.nextInt(2);
			
			if(coinToss == 0) {
				c = Character.toUpperCase(c);
			} else {
				c = Character.toLowerCase(c);
			}
			
			mockReply.append(c);
		}
		
		return mockReply.toString();
	}
	
}
