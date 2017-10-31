package com.marilene.bots;

import java.util.Calendar;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.methods.send.SendVoice;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.marilene.constants.MarileneBotConstants;
import com.marilene.constants.ReactionConstants;
import com.marilene.utils.RNGHandler;
import com.marilene.utils.ReplyDispatcher;

public class MarileneBot extends TelegramLongPollingBot {

	@Override
	public void onUpdateReceived(Update update) {
		if(update != null) {
			if(update.hasMessage()) {
				boolean actionPerformed = false;

				Long chatId = update.getMessage().getChatId();
				User userFrom = update.getMessage().getFrom();
				String from = userFrom.getFirstName() + " '" + userFrom.getUserName() + "'" + ((userFrom.getLastName() != null) ? (" " + userFrom.getLastName()) : "") ;

				if(userFrom.getUserName().equals("Mizaroth") && update.getMessage().getChat().getTitle() == null) {
					if(update.getMessage().hasPhoto()) {
						SendPhoto sp = new SendPhoto();
						sp.setPhoto(update.getMessage().getPhoto().get(0).getFileId());
						sp.setCaption("File ID: " + update.getMessage().getPhoto().get(0).getFileId());
						sp.setChatId(chatId);
						try {
							sendPhoto(sp);
							actionPerformed = true;
						} catch (TelegramApiException e) {
							e.printStackTrace();
						}
					}

					if(update.getMessage().getVoice() != null) {
						SendVoice sv = new SendVoice();
						sv.setVoice(update.getMessage().getVoice().getFileId());
						sv.setCaption("File ID: " + update.getMessage().getVoice().getFileId());
						sv.setChatId(chatId);
						try {
							sendVoice(sv);
							actionPerformed = true;
						} catch (TelegramApiException e) {
							e.printStackTrace();
						}
					}
				}


				//2% of sending hilarious audio
				if(RNGHandler.procByPercentage(2)) {
					SendVoice sv = new SendVoice();
					sv.setChatId(chatId);
					sv.setVoice(ReplyDispatcher.reply(ReactionConstants.VOICE_RECORDINGS));
					try {
						sendVoice(sv);
						actionPerformed = true;
					} catch (TelegramApiException e) {
						e.printStackTrace();
					}
				}

				//2% of linking random reddit italy/yt anime link
				//TODO !! TO IMPLEMENT !!

				String message = update.getMessage().getText();

				if(message != null) {
					String messageCapitalized = message.toUpperCase();

					//2% of mOcKiNg YoUr RePlY
					if(RNGHandler.procByPercentage(2)) {
						SendMessage sm = new SendMessage(chatId, ReplyDispatcher.mockReply(message));
						try {
							execute(sm);
							actionPerformed = true;
						} catch (TelegramApiException e) {
							e.printStackTrace();
						}
					}

					if(messageCapitalized.contains(ReactionConstants.BARZOTTA) || messageCapitalized.contains(ReactionConstants.BARZOTTO)) {
						SendMessage sm = new SendMessage(chatId, ReplyDispatcher.reply(ReactionConstants.BARZOTT_REPLY));
						try {
							execute(sm);
							actionPerformed = true;
						} catch (TelegramApiException e) {
							e.printStackTrace();
						}
					} else if(messageCapitalized.contains(ReactionConstants.ANIME) || messageCapitalized.contains(ReactionConstants.LOLI)) {
						SendMessage sm = new SendMessage(chatId, ReplyDispatcher.reply(ReactionConstants.ANIME_REPLY));
						try {
							execute(sm);
							actionPerformed = true;
						} catch(TelegramApiException e) {
							e.printStackTrace();
						}
					} else if(messageCapitalized.contains(ReactionConstants.MARILENE)) {
						SendMessage sm = new SendMessage(chatId, ReplyDispatcher.reply(ReactionConstants.MARILENE_REPLY));
						try {
							execute(sm);
							actionPerformed = true;
						} catch(TelegramApiException e) {
							e.printStackTrace();
						}
					}
				}
				
				if(actionPerformed) {
					System.out.println("Triggered by: " + from + " | Chat: " + ((update.getMessage().getChat().getTitle() != null) ? update.getMessage().getChat().getTitle() : "Private Chat" ) + " @ " + Calendar.getInstance().getTime().toString());
				}

			}
		}
	}

	@Override
	public String getBotUsername() {
		return MarileneBotConstants.USERNAME;
	}

	@Override
	public String getBotToken() {
		return MarileneBotConstants.TOKEN;
	}

}
