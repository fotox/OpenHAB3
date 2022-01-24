# OpenHAB3
## Rules
- [x] Check Door-Window-Contacts
- [x] Change Winddirection
- [x] Alarm system over Telegram
- [x] Alexa say hello and goodbye
- [x] Next day activities
- [x] Alexa get the temperature
- [ ] Switching of a boiler by photovolataik surplus

### Informations for Alexa TTS
Before starting the control, the [Alexa Binding](https://www.openhab.org/addons/bindings/amazonechocontrol/) must be installed and the thing **amazon account** must be added to the OpenHAB3. After that the inbox shows the found alexa devices. In this rule, the thing-item-binding of an alexa device, here marked as **Alexa_TTS** and a dynamic string-item, here marked as **CheckContactOutput**, is also required.

### File extension
The files were written in "pseudo java code", OpenHAB3 calls these files "rules". To use them in your own OpenHAB3 just change the extension .java to .rules.

### Check Door-Window-Contacts
The rule first accesses the group of contacts and checks whether a member of this group has the status **OPEN**. If at least 1 contact has the status **OPEN**, the specified Alexa reports which contacts are open. In addition, a message with **Not everything is closed** appears in the Smart Home GUI.

### Alarm system over Telegram
Before starting up the alarm system, a custom chatbot must be created via the botfather from telegram. Then the [[Telegram Binding](https://www.openhab.org/addons/bindings/telegram/) must be installed and the thing **Telegram Bot** must be added to the OpenHAB3. The thing id created by this replaces the **< ID >** placeholder of the rule.

For more control a logical switch is needed, which was named **Alarm_ON_OFF** in the rule. This switches the alarm array on, but can also be additionally linked with a physical switch (thing-item-binding).

For the reply logic, two items must also be created via the **telegram bot** thing. The type here is the **Reply ID**, which is usually defined as **TelgramBot_ReplyID** and the type **Last Massage Text**, which is usually specified as **TelegramBot_LastMassage**.

The rule grabs all the lights in the apartment at startup and asks if any lights that are still on should be turned off. It then checks if all windows and doors are closed. If any lights are on, the chatbot asks if all remaining lights should be turned off. Open windows and doors are automatically reported and the alarm system is deactivated again. If a motion detector is triggered or a door or window contact is triggered when the alarm system is active, the alarm system automatically sends a message to the owner. 

When the alarm system is deactivated, it also asks if all lights in the living area should be switched on to automatically create a coming home atmosphere.

### Alexa say hello and goodbye
The rule checks whether the contact of the entrance door is triggered. If the motion detector in the hallway is not triggered, it is assumed that a person enters the house. Otherwise, the person will be said goodbye when leaving.

### Next day activities
OpenHAB3 uses the [iCalendar Binding](https://www.openhab.org/addons/bindings/icalendar/) to access the Google integrated garbage calendar, birthday calendar, holiday calendar and appointment calendar and mirrors them to OpenHAB3.

The rule checks now if it is someone's birthday today, if the garbage will be picked up tomorrow and if there are public holidays tomorrow. The rule can return this information to the user at a specific time (by triggering the switch "whats_up_today") and saves you searching through multiple calendars.

### Alexa get the temperature
The rule is activated by a voice input via Alexa. A new rule has been created in the Alexa app that sets a logical number to one, for example, when the voice command "Alexa, give me the temperature of the kitchen" is given.  This is set up as an item in OpenHAB3 and activates the rule after the number has been changed.

After the rule has been run, the switch is reset to zero and the rule ends after the second run.

## Python Exec for Rules
