package com.rppowell.annotatedfillo.testworkbook001;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.rppowell.annotatedfillo.AnnotatedFilloFactory;
import com.rppowell.annotatedfillo.FilloColumn;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;

public class AnnotatedFilloTestSheetOne {
    @FilloColumn
    public String TestSheetOneColumnOne;

    @FilloColumn
    public String TestSheetOneColumnTwo;

    @FilloColumn
    public String TestSheetOneColumnThree;

    @FilloColumn (columnName="TestSheetOneColumnFour BooleanDropdownValues")
    public String TestSheetOneColumnFour;

    public String toString() {
        return "AnnotatedFilloTestSheetOne(" +
                "TestSheetOneColumnOne='"+ TestSheetOneColumnOne + "', " +
                "TestSheetOneColumnTwo='"+ TestSheetOneColumnTwo + "', " +
                "TestSheetOneColumnThree='"+ TestSheetOneColumnThree + "', " +
                "TestSheetOneColumnFour='"+ TestSheetOneColumnFour + "')";
    }

    @Test
    public void testFilloTestSheetOne() throws FilloException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String xlsxFilename = "src/test/resources/TestWorkbook001.xlsx";
        System.out.println("xlsx=" + xlsxFilename);

        Fillo fillo=new Fillo();
        Connection connection=fillo.getConnection(xlsxFilename);
        Recordset recordset=connection.executeQuery("Select * From TestSheetOne");
        while(recordset.next()) {
            AnnotatedFilloTestSheetOne annotatedFilloTestSheetOne = (AnnotatedFilloTestSheetOne) AnnotatedFilloFactory.extractClassFromRecordset(recordset, AnnotatedFilloTestSheetOne.class);
            System.out.println(annotatedFilloTestSheetOne.toString());
        }
        recordset.close();
        connection.close();
    }
}
