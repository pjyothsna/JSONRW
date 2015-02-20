# JSONRW
json read and write

Problem Statement: 
Read in a list of internet memes from a json file on the classpath (you choose the memes!). 
Create one method which takes the list of memes and sorts them by name. 
Create a second method which associates a "lulz" score (from 1-10) with each meme 
and writes the updated values to the same json file.

Solution:

1. Get Absolute path
2. Read Json and convert into Java Object and get respective Map
3. Sort the Key values and print in log
4. Prepare the LulzScore based on memes Key
5. Load both hash maps into memelulz object and writing back to JSON

Use case 1: getting input json from class path
  1. Copy the sample memes.json into classes direcotry
  2. java -jar 'Path of the jar file' 'package.classname' 'name.json' 1

Usecase 2: setting system variables java -D  
  java -D'name.json'='Path of the JSON' -jar 'Path of the jar file' 'package.classname' 'name.json' 2

Expected Input:
{"memesMap":{"A":"helloA","C":"Hello C","B":"Hello B"}}
Expected Output
  1. File Path = C:\user\git\memes\src\main\resources\memes.json
  2. Memes Json read {{A=helloA, C=Hello C, B=Hello B}}
  3. Sorted Keys [A, B, C]
  4. 
    lulz score: memeName = A and lulzScore= -8
    lulz score: memeName = C and lulzScore= -6
    lulz score: memeName = B and lulzScore= -7

  5. final output: 
    {"lulzMap":{"A":-8,"B":-7,"C":-6},"memesMap":{"A":"helloA","C":"Hello C","B":"Hello B"}}

  Here we are assuming that input file will be used by other systems hence existing structure should not be changed
  to minimize the regression issues. So we have appended/prepended with new json object. so that exisiting jsonobject.get("memes") won't fail.


Note: log4j.properities need to modify to set up the log path and Unit test cases needs to be added
 
