package com.rppowell.annotatedfillo.testworkbook001;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import org.testng.annotations.Test;

public class UnannotatedFilloTestSheetOne {
    public String TestSheetOneColumnOne;
    public String TestSheetOneColumnTwo;
    public String TestSheetOneColumnThree;
    public String TestSheetOneColumnFour;

    public static String TestSheetOneColumnOne_ColumnName = "TestSheetOneColumnOne";
    public static String TestSheetOneColumnTwo_ColumnName = "TestSheetOneColumnOne";
    public static String TestSheetOneColumnThree_ColumnName = "TestSheetOneColumnOne";
    public static String TestSheetOneColumnFour_ColumnName = "TestSheetOneColumnFour BooleanDropdownValues";

    public void setFromRecordset(Recordset recordset) throws FilloException {
        TestSheetOneColumnOne = recordset.getField(TestSheetOneColumnOne_ColumnName);
        TestSheetOneColumnTwo = recordset.getField(TestSheetOneColumnTwo_ColumnName);
        TestSheetOneColumnThree = recordset.getField(TestSheetOneColumnThree_ColumnName);
        TestSheetOneColumnFour = recordset.getField(TestSheetOneColumnFour_ColumnName);
    }

    public String toString() {
        return "UnannotatedFilloTestSheetOne(" +
                "TestSheetOneColumnOne='"+ TestSheetOneColumnOne + "', " +
                "TestSheetOneColumnTwo='"+ TestSheetOneColumnTwo + "', " +
                "TestSheetOneColumnThree='"+ TestSheetOneColumnThree + "', " +
                "TestSheetOneColumnFour='"+ TestSheetOneColumnFour + "')";
    }

    @Test
    public void testFilloTestSheetOne() throws FilloException {
        String xlsxFilename = "src/test/resources/TestWorkbook001.xlsx";
        System.out.println("xlsx=" + xlsxFilename);

        Fillo fillo=new Fillo();
        Connection connection=fillo.getConnection(xlsxFilename);
        String strQuery="Select * from TestSheetOne where TestSheetOneColumnOne='valueone'";
        Recordset recordset=connection.executeQuery(strQuery);

        while(recordset.next()){
            for (String strColumn : recordset.getFieldNames()) {
                System.out.println(strColumn + ":" + recordset.getField(strColumn));
            }
        }
        recordset.close();
        connection.close();
    }

    @Test
    public void testFilloTestSheetTwo() throws FilloException {
        String xlsxFilename = "src/test/resources/TestWorkbook001.xlsx";
        UnannotatedFilloTestSheetOne unannotatedFilloTestSheetOne;

        Fillo fillo=new Fillo();
        Connection connection=fillo.getConnection(xlsxFilename);
        Recordset recordset=connection.executeQuery("Select * From TestSheetOne");
        while(recordset.next()) {
            unannotatedFilloTestSheetOne = new UnannotatedFilloTestSheetOne();
            unannotatedFilloTestSheetOne.setFromRecordset(recordset);
            System.out.println(unannotatedFilloTestSheetOne.toString());
        }
        recordset.close();
        connection.close();
    }
}
