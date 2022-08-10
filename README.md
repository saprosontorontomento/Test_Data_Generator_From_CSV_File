# Test_Data_Generator_From_CSV_File
## CREATE BufferedReader on 3 tables
```
BufferedReader csvReaderOne = new BufferedReader(new FileReader(FILE_NAME_ONE));
BufferedReader csvReaderTwo = new BufferedReader(new FileReader(FILE_NAME_TWO));
BufferedReader csvReaderThree = new BufferedReader(new FileReader(FILE_NAME_THREE));
```
## Skip the headers when reading a CSV file
```
boolean firstLineOne = true;
boolean firstLineTwo = true;
boolean firstLineThree = true;
String row; // read line buffer

while ((row = csvReaderOne.readLine()) != null) {
    if (firstLineOne) {
        firstLineOne = false;
        continue;
    }

}

```
