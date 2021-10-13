Basic Telegram Praise Bot (work in progress)

Receceives praise in the format:

/praise @user1, @user2, @user3 for doing something awesome

and sends it to the backend in the following format

TIME: "[Unix Timecode]" GIVER: "@sender" RECEIVER: "@user1" CONTENT:  "doing something awesome"
TIME: "[Unix Timecode]" GIVER: "@sender" RECEIVER: "@user2" CONTENT:  "doing something awesome"
TIME: "[Unix Timecode]" GIVER: "@sender" RECEIVER: "@user3" CONTENT:  "doing something awesome"

To get a bot running you have to register a bot with the botfather (https://core.telegram.org/bots#6-botfather), create a  \src\main\resources\application.properties file and copy the token and name into it using the following format:
bot.token: 1234tokenNumber5678
bot.username: my_bot_name