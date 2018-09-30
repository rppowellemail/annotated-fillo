# Annotated Fillo

Java annotations to help extract data to class from spreadsheets using the fillo library.

## Annotated Fillo Example

Given a spreadsheet with the following header-column layout:


| TestSheetOneColumnOne | TestSheetOneColumnTwo | TestSheetOneColumnThree | TestSheetOneColumnFour BooleanDropdownValues |
| --------------------- | --------------------- | ----------------------- |:--------------------------------------------:|
| valueone              | foo                   | 1                       | TRUE                                         |
| valuetwo              | bar                   | 2                       | FALSE                                        |
| ...                   | ...                   | ...                     | ...                                          |


The intention is to annotate Java class public instance variables and extract row entries.

The following example shows annotating the class and extracting the spreadsheet:

    public class AnnotatedFilloTestSheetOne {
        @FilloColumn
        public String TestSheetOneColumnOne;
    
        @FilloColumn
        public String TestSheetOneColumnTwo;
    
        @FilloColumn
        public String TestSheetOneColumnThree;
    
        @FilloColumn (columnName="TestSheetOneColumnFour BooleanDropdownValues")
        public String TestSheetOneColumnFour;
    
    }

Extracting the class from Fillo recordset:

    ...
        Fillo fillo=new Fillo();
        Connection connection=fillo.getConnection(xlsxFilename);
        Recordset recordset=connection.executeQuery("Select * From TestSheetOne");
        while(recordset.next()) {
            AnnotatedFilloTestSheetOne annotatedFilloTestSheetOne = (AnnotatedFilloTestSheetOne) AnnotatedFilloFactory.extract(recordset, AnnotatedFilloTestSheetOne.class);
            System.out.println(annotatedFilloTestSheetOne.toString());
        }
        recordset.close();
        connection.close();
    ...


Values are set in the created class:

    ...
    AnnotatedFilloTestSheetOne(TestSheetOneColumnOne='valueone', TestSheetOneColumnTwo='foo', TestSheetOneColumnThree='1', TestSheetOneColumnFour='true')
    AnnotatedFilloTestSheetOne(TestSheetOneColumnOne='valuetwo', TestSheetOneColumnTwo='bar', TestSheetOneColumnThree='2', TestSheetOneColumnFour='false')
    ...


# Dependencies / References

 * Fillo ( https://codoid.com/fillo/ )
