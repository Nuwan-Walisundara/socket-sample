# socket-sample
sample project to understand the java sockets. This consists of client component,common component,server component.

required  JRE 1.8

Instructions to run
====================
1. Using terminal chenge into project root
2. run mvn clean install
3. Download sample mail server [link]( http://nilhcem.com/FakeSMTP/ )
4. The email server can be start using following:
   *  java -jar fakeSMTP.jar -s -b -p 2525 -a 127.0.0.1
5. Locate  server-0.0.1-SNAPSHOT.jar at server/target
6. Start the socket server as fallows :
   *   "java -jar server/target/server-0.0.1-SNAPSHOT.jar server/server.config"
   *  addtitanal param server.config file define the configurations for the server env.you can edit the setings as needed.
7. Locate the socket client at client-0.0.1-SNAPSHOT.jar at client/target
   *  java -jar client-0.0.1-SNAPSHOT.jar client.config
   *  Addtitanal param client.config file define the configurations for the client env.you can edit the setings as needed.
8. Check console log of client and server for progress
