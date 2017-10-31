package com.marilene.main;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.marilene.bots.MarileneBot;

public class Main {

	private Main() {
		//do NOT.
	}
	
	public static void main(String[] args) {
        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new MarileneBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
	}

}
