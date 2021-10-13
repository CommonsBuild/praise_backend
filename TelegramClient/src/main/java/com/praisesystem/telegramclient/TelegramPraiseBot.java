package com.praisesystem.telegramclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Date;

import javax.annotation.PostConstruct;


@Component
public class TelegramPraiseBot extends TelegramLongPollingBot {

    private static final Logger logger = LoggerFactory.getLogger(TelegramPraiseBot.class);

    @Value("${bot.token}")
    private String token;

    @Value("${bot.username}")
    private String username;

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    private String praise(Message message, List < MessageEntity > entities) {

        String username = message.getFrom().getUserName();
        int unix_time = message.getDate();
        //Date praise_time = new Date((long)unix_time*1000);
        String message_text = message.getText();

        String[] message_splitted = message_text.split("for", 2);

        List < String > list_of_praisees = getPraiseeList(message_splitted[0]);
        String praise_content = message_splitted[1];




        sendPraiseToBackend(username, list_of_praisees, praise_content, unix_time);

        String reply_text = "Praise dished! @" + username + " praised " + list_of_praisees.toString() + " for " + praise_content + " at " + unix_time;

        return reply_text;
    }

    private List < String > getPraiseeList(String msg) {
        String[] parse_msg = msg.replace(",", "").split(" ");
        List < String > users = new ArrayList < > (Arrays.asList(parse_msg));
        String buf = users.remove(0);
        return users;
    }

    private void sendPraiseToBackend(String username, List < String > list_of_praisees, String praise_content, int time) {
        //Send praise to database
        for (int i = 0; i < list_of_praisees.size(); i++){
            logger.info("\nSent praise: TIME: \"{}\" GIVER: \"{}\" RECEIVER: \"{}\" CONTENT: {}", time, username, list_of_praisees.get(i), praise_content);
        }
        
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {

            Message message = update.getMessage();

            if (message.hasEntities()) {
                List < MessageEntity > entities = message.getEntities();

                if (entities.get(0).getText().equals("/praise")) {
                    
                    String praiseOutput = praise(message, entities);

                    SendMessage response = new SendMessage();
                    Long chatId = message.getChatId();
                    response.setChatId(String.valueOf(chatId));
                    String text = praiseOutput;
                    response.setText(text);
                    try {
                        execute(response);
                        logger.info("Sent message \"{}\" to {}", text, chatId);
                    } catch (TelegramApiException e) {
                        logger.error("Failed to send message \"{}\" to {} due to error: {}", text, chatId, e.getMessage());
                    }
                }
            }
        }
    }

    @PostConstruct
    public void start() {
        logger.info("username: {}, token: {}", username, token);
    }

}