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
  
  import javax.annotation.PostConstruct;
  
  //Standard Spring component annotation
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


//     def praise(update, context):
//       username = update.message.from_user.username 
//       time = update.message.date
//       message_text = update.message.text
      
//       list_of_praisees, praise_content = parsePraise(message_text)
  
//       sendPraiseToBackend(username, list_of_praisees, praise_content, time)
  
//       update.message.reply_text(f"Praise dished! {username} praised {list_of_praisees} for {praise_content} at {time}")
  
  
//   def parsePraise(message):
//       msg = message.split(" for ", 1)
  
//       users = msg[0].replace(',',"").split()[1:]
  
      
//       return users, msg[1]
      
//   def sendPraiseToBackend(username, list_of_praisees, praise_content, time):
//       for name in list_of_praisees:
//           print("SENDING PRAISE:")
//           print("\tGiver: " + username)
//           print("\tReceiver:" + name)
//           print("\tComment: " + praise_content)
//           print("\tTime: " + time.strftime("%m/%d/%Y, %H:%M:%S") )
      
      @Override
      public void onUpdateReceived(Update update) {
          if (update.hasMessage()) {

              Message message = update.getMessage();
              List<MessageEntity> entities = message.getEntities();
              System.out.println(entities.toString());
              SendMessage response = new SendMessage();
              Long chatId = message.getChatId();
              response.setChatId(String.valueOf(chatId));
              String text = message.getText();
              response.setText(text);
              try {
                  execute(response);
                  logger.info("Sent message \"{}\" to {}", text, chatId);
              } catch (TelegramApiException e) {
                  logger.error("Failed to send message \"{}\" to {} due to error: {}", text, chatId, e.getMessage());
              }
          }
      }
  
      @PostConstruct
      public void start() {
          logger.info("username: {}, token: {}", username, token);
      }
  
  }