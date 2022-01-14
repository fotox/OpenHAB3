# OpenHAB3 Rules
- [x] Check Door-Window-Contacts
- [x] Change Winddirection
- [x] Alarm system over Telegram
- [ ] Alexa say hello and goodbye
- [ ] Alexa how is the temperature
- [ ] Next day activities
- [ ] Switching of a boiler by photovolataik surplus

## File extension
The files were written in "pseudo java code", OpenHAB3 calls these files "rules". To use them in your own OpenHAB3 just change the extension .java to .rules.

## Check Door-Window-Contacts
Before starting the control, the [Alexa Binding](https://www.openhab.org/addons/bindings/amazonechocontrol/) must be installed and the thing **amazon account** must be added to the OpenHAB3. After that the inbox shows the found alexa devices. In this rule, the thing-item-binding of an alexa device, here marked as **Alexa_TTS** and a dynamic string-item, here marked as **CheckContactOutput**, is also required.

The rule first accesses the group of contacts and checks whether a member of this group has the status **OPEN**. If at least 1 contact has the status **OPEN**, the specified Alexa reports which contacts are open. In addition, a message with **Not everything is closed** appears in the Smart Home GUI.

## Alarm system over Telegram
Before starting up the alarm system, a custom chatbot must be created via the botfather from telegram. Then the [[Telegram Binding](https://www.openhab.org/addons/bindings/telegram/) must be installed and the thing **Telegram Bot** must be added to the OpenHAB3. The thing id created by this replaces the **< ID >** placeholder of the rule.

For more control a logical switch is needed, which was named **Alarm_ON_OFF** in the rule. This switches the alarm array on, but can also be additionally linked with a physical switch (thing-item-binding).

For the reply logic, two items must also be created via the **telegram bot** thing. The type here is the **Reply ID**, which is usually defined as **TelgramBot_ReplyID** and the type **Last Massage Text**, which is usually specified as **TelegramBot_LastMassage**.

The rule grabs all the lights in the apartment at startup and asks if any lights that are still on should be turned off. It then checks if all windows and doors are closed. If any lights are on, the chatbot asks if all remaining lights should be turned off. Open windows and doors are automatically reported and the alarm system is deactivated again. If a motion detector is triggered or a door or window contact is triggered when the alarm system is active, the alarm system automatically sends a message to the owner. 

When the alarm system is deactivated, it also asks if all lights in the living area should be switched on to automatically create a coming home atmosphere.