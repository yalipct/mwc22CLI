# MWC22CLI


## Description
Maven project developed in Eclipse IDE using PicoCLI, Hibernate and Jackson libraries.

### User stories / Goals

✅ Task 1 → Create a developer model that contains the following information: Name, Email, Category ( Front, Back, Mobile, Data), Telephone, Days to attend the mobile ( 28 Feb, 1 -3 March)

✅ Task 2 → Insert the .json data in a database (Link to the json in the statement)

✅ Task 3 → Generate a CLI command to display the MWC information (Days that will be available)

✅ Task 4 → Generate a CLI command that displays the list of developers

✅ Task 5 → Generate a CLI command to add a new developer and update the database

## Usage
```
java -jar mwc-cli.jar [COMANDS][ARGS]
```
**Read json file and add it to Database**
```
java -jar mwc-cli.jar read 
```
**List Lists all developers**
```
java -jar mwc-cli.jar 
```
**Shows the days that the MWC is available**
```
java -jar mwc-cli.jar info 
```
**Show the version and exit.**
```
-v, --version 
```
**Show the version and exit.**
```
-h, --help 
```
**Add a new developer to the list.**
```
-add, -create, -plus 
-n<name> -e<email> -c<category> -tel<phone> -d<date>
```

## Examples
