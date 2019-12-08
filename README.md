# Rationale

Parse EDL (Edit decision list) files and print their content as CSV (Comma-Seperated Values) for further processing. 
The output will be separated using semicolon (";").

See also: https://en.wikipedia.org/wiki/Edit_decision_list

This is not a complete EDL parser. It's a tool which I quickly hacked on a sunday morning to get some content for
my purpose out of some EDL files. Use with caution. 

We will output the frame fractions for the date part as Excel-friendly time fractions. The fractions will be 
calculated based on a 25 fps format. You will have to adapt the code to fix this.

Example input file:

Example output:

# How to Build

This program was written in Java. To build the software, you
have to install on your machine:

    Java JDK 11
    Maven 3.6
    
It's Java, so you can run and build the software under Linux,
Mac OS and Windows. All you need is a "terminal" or "command line".    
    
Check your installations requirements with something like:

    thomas@Reliant-UX:~$ mvn -version
    Apache Maven 3.6.0
    Maven home: /usr/share/maven
    Java version: 11.0.4, vendor: Ubuntu, runtime: /usr/lib/jvm/java-11-openjdk-amd64
    Default locale: en_US, platform encoding: UTF-8
    OS name: "linux", version: "5.0.0-37-generic", arch: "amd64", family: "unix"
    thomas@Reliant-UX:~$ java -version
    openjdk version "11.0.4" 2019-07-16
    OpenJDK Runtime Environment (build 11.0.4+11-post-Ubuntu-1ubuntu218.04.3)
    OpenJDK 64-Bit Server VM (build 11.0.4+11-post-Ubuntu-1ubuntu218.04.3, mixed mode, sharing)
    
    
To build the software, go to the directory where this file is
located, and run:

    mvn clean compile assembly:single
    
The output is a file `target/edl-parser-1.0-SNAPSHOT-jar-with-dependencies.jar` which you can run.    
    
# How to Run

After you built the JAR file, just start it, and as a parameter
pass in the EDL file to parse. The CSV file will be printed
directly to System.out, so you might redirect this to a file.

Example:

    java -jar target/edl-parser-1.0-SNAPSHOT-jar-with-dependencies.jar /media/shared/MyFile.edl 