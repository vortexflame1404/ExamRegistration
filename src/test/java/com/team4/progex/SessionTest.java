package com.team4.progex;

import org.json.JSONArray;
import org.junit.Test;

import java.sql.SQLException;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SessionTest extends ConnectDatabaseTest{
//    @Before
//    public void before(){
//        loadFixtures(new String[] {"session.sql","module.sql",""});
//    }

    @Test
    public void testAddNewStudentFail(){
        String[][] expected = new String[][] {
                new String[] {"F0001","123","3652412"},
                new String[] {"hihi","hahhaha","hikari"},
                new String[] {"thach","thang","thao"},
                new String[] {"le ngoc","vu viet","hoang anh hien"}
        };
        for (int i = 0; i < expected[0].length; i++) {
            boolean exceptionThrown = false;
            try {
                Methods.insert_student(
                        expected[0][i],
                        expected[1][i],
                        expected[2][i],
                        expected[3][i],
                        expected[4][i]);
            } catch (Exception e) {
                exceptionThrown = true;
            }
            assertTrue(exceptionThrown);
        }
    }

    @Test
    public void testModulesOfSemesterSuccess() throws SQLException, ParseException {
        String[] moduleName = new String[] {
                ("Math"),
                ("Math")
        };

        String[] moduleCode = new String[] {
                ("M1ALG"),
                ("M2MAT")
        };

        String[] semID = new String[] {
                ("2"),
                ("2")
        };

        JSONArray res = Methods.list_module_student_has_enrolled_in(2);
        for (int i = 0; i < moduleCode.length; i++) {
            String resModuleName = ((JSONArray) res.get(i)).getString(2);
            assertEquals(moduleName[i], resModuleName);

            String resModuleCode = ((JSONArray) res.get(i)).getString(4);
            assertEquals(moduleCode[i], resModuleCode);

            String resSemID = ((JSONArray) res.get(i)).getString(1);
            assertEquals(semID[i], resSemID);
        }
    }


}
