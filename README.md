
# SecretSanta
Java Application to create secret santa matches
## How to Setup a Development Environment

 1. Ensure that you have gradle and java in your build path
 2. From the root of the project run gradle eclipse
 3. Import the folder in eclipse
## How to generate Secret Santa matches
 1. The application needs a list of participants, the comma separated participants lists is expected to be present in a .properties file, under the keyname  **participants**
 2. The participants file can also be specified from command line.
 3. If no command line argument is passed it looks for a file **participants.properties** in the root folder
##  Sending Match emails
1. This is currently in progress, but the way things are written SMTP configuration is expected in smtp.properties.

