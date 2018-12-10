
# SecretSanta
Java Application to create secret santa matches
## How to Setup a Development Environment

 1. Ensure that you have gradle and java in your build path
 2. From the root of the project run gradle eclipse
 3. Import the folder in eclipse
## Running on command line
1. Ensure that **participants.properties** is present in the project root
2. From the project root, run **gradle run**
## How to generate Secret Santa matches
 1. The application needs a list of participants, the comma separated participants lists is expected to be present in a .properties file, under the keyname  **participants**
 2. The participants file can also be specified from command line.
 3. If no command line argument is passed it looks for a file **participants.properties** in the root folder
##  Sending Match emails
1. This is currently in progress, but the way things are written SMTP configuration is expected in smtp.properties.

## To Do Items
1. Logging is broken
2. JUnits are not written
3. Updating read me with steps to invoke the matcher from the distribution files

## Sample contents of participants.properties
participants=email1@somedomain.com,email2@somedomain2.com,ashish@example.com,dummy@dummy.com

## Sample contents of smtp.properties
smtpPort=587

smtpHost=smtp.gmail.com

smtpUsername=yourgmailusername@gmail.com

mailText=Your match is %s

smtpPassword=YourPassword

subject=Secret Santa is Here!


## Gmail Troubleshooting
I have multifactor enabled for my google account, and also by default gmail does not let you send mail with your regular login credentials. So i generated an application password from gmail account settings. At the time of writing this document, the app password can be generated from this link https://myaccount.google.com/apppasswords

## Questions?
Please raise an issue :D Happy to help!
