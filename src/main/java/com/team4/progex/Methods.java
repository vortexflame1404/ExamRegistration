package com.team4.progex;

import org.json.JSONArray;

import java.sql.*;

public class Methods {
    //connect 1 time
    private static PreparedStatement getPreparedStatement(String statement) throws SQLException {
        String host = "jdbc:mysql://localhost:3306/dbexreg";
        String username = "root";
        String password = "thang";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Cannot find MysSQL driver class");
            e.printStackTrace();
            System.exit(1);
        }

        Connection connection = DriverManager.getConnection(host, username, password);
        return connection.prepareStatement(statement);
    }

    public static JSONArray list_module() {
        String query = "{call list_module}";
        ResultSet resultSet;
        try (PreparedStatement statement = getPreparedStatement(query)) {
            resultSet = statement.executeQuery();
            JSONArray output = JsonConverter.convertAll(resultSet);
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONArray view_list_register(int exam_id) {
        String query = "{call view_list_register(?)}";
        ResultSet resultSet;
        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setInt(1, exam_id);
            resultSet = statement.executeQuery();
            JSONArray output = JsonConverter.convertAll(resultSet);
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONArray view_course_list() {
        String query = "{call view_course_list}";
        ResultSet resultSet;
        try (PreparedStatement statement = getPreparedStatement(query)) {
            resultSet = statement.executeQuery();
            JSONArray output = JsonConverter.convertAll(resultSet);
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void unsign_attendance(int student_id, int ses_id) {
        String query = "{call unsign_attendance(?,?)}";

        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setInt(1, student_id);
            statement.setInt(2, ses_id);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void unregister_exam(int student_id, int exam_id, String date_exam) {
        String query = "{call unregister_exam(?,?,?)}";

        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setInt(3, student_id);
            statement.setInt(2, exam_id);
            statement.setDate(1, java.sql.Date.valueOf(date_exam));
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void unenroll_a_student_from_a_module(int student_id, int module_id) {
        String query = "{call unenroll_a_student_from_a_module(?,?)}";
        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setInt(1, student_id);
            statement.setInt(2, module_id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sign_attendance(String si_date, int student_id, int ses_id) {
        String query = "{call sign_attendance(?, ?, ?)}";

        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setDate(1, java.sql.Date.valueOf(si_date));
            statement.setInt(2, student_id);
            statement.setInt(3, ses_id);

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void register_exam(String re_date, String date_exam, int student_id, int exam_id) {
        String query = "{call register_exam(?, ?, ?, ?)}";

        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setDate(1, java.sql.Date.valueOf(re_date));
            statement.setDate(2, java.sql.Date.valueOf(date_exam));
            statement.setInt(3, exam_id);
            statement.setInt(4, student_id);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void not_let_a_lecturer_teach_a_module(int module_id, int lec_code) {
        String query = "{call not_let_a_lecturer_teach_a_module(?,?)}";

        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setInt(1, module_id);
            statement.setInt(2, lec_code);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void modify_student(int student_id, String code, String pass, String fname, String lname, String acc_login) {
        String query = "{modify_student(?,?,?,?,?,?)}";
        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setInt(1, student_id);
            statement.setString(2, code);
            statement.setString(3, pass);
            statement.setString(4, fname);
            statement.setString(5, lname);
            statement.setString(6, acc_login);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

///////////////////////////////////////////////////////////////////////////NEXTTTT////

    public static void modify_module(int module_id, String m_name, String code, int semester_id) {
        String query = "{call modify_module(?,?,?,?)}";

        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setInt(1, module_id);
            statement.setString(2, m_name);
            statement.setString(3, code);
            statement.setInt(4, semester_id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void modify_lecturer(int lec_id, String pass, String fname, String lname, String acc_login) {
        String query = "{modify_lecturer(?,?,?,?,?)}";

        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setInt(1, lec_id);
            statement.setString(2, pass);
            statement.setString(3, fname);
            statement.setString(4, lname);
            statement.setString(5, acc_login);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void modify_exam(int exam_id, String e_date, int module_id, String deadline) {
        String query = "{modify_exam(?,?,?,?)}";

        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setInt(1, exam_id);
            statement.setDate(2, java.sql.Date.valueOf(e_date));
            statement.setInt(3, module_id);
            statement.setDate(4, java.sql.Date.valueOf(deadline));
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JSONArray list_sessions_in_module(int module_id) {
        String query = "{call list_sessions_in_module(?)}";
        ResultSet resultSet;
        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setInt(1, module_id);
            resultSet = statement.executeQuery();
            JSONArray output = JsonConverter.convertAll(resultSet);
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONArray list_module_student_has_enrolled_in(int student_id) {
        String query = "{call list_module_student_has_enrolled_in(?)}";
        ResultSet resultSet;
        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setInt(1, student_id);
            resultSet = statement.executeQuery();
            JSONArray output = JsonConverter.convertAll(resultSet);
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONArray list_exam_that_student_has_registered(int student_id) {
        String query = "{call list_exam_that_student_has_registered(?)}";
        ResultSet resultSet;
        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setInt(1, student_id);
            resultSet = statement.executeQuery();
            JSONArray output = JsonConverter.convertAll(resultSet);
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONArray list_exam_from_module(int module_id) {
        String query = "{call list_exam_from_module(?)}";
        ResultSet resultSet;
        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setInt(1, module_id);
            resultSet = statement.executeQuery();
            JSONArray output = JsonConverter.convertAll(resultSet);
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONArray list_account() {
        String query = "{call list_account}";
        ResultSet resultSet;
        try (PreparedStatement statement = getPreparedStatement(query)) {
            resultSet = statement.executeQuery();
            JSONArray output = JsonConverter.convertAll(resultSet);
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void let_a_lecturer_teach_a_module(int module_id, int lec_code) {
        String query = "{call let_a_lecturer_teach_a_module(?,?)}";

        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setInt(1, module_id);
            statement.setInt(2, lec_code);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void insert_student(String pass, String fname, String lname, String acc_login, String my_code) {
        String query = "{call insert_student(?,?,?,?,?)}";
        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setString(1, pass);
            statement.setString(2, fname);
            statement.setString(3, lname);
            statement.setString(4, acc_login);
            statement.setString(5, my_code);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insert_lecturer(String pass, String fname, String lname, String acc_login) {
        String query = "{insert_lecturer(?,?,?,?)}";
        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setString(1, pass);
            statement.setString(2, fname);
            statement.setString(3, lname);
            statement.setString(4, acc_login);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void insert_semester(String s_date, String e_exam) {
        String query = "{call insert_semester(?, ?)}";

        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setDate(1, java.sql.Date.valueOf(s_date));
            statement.setDate(2, java.sql.Date.valueOf(e_exam));
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insert_module(String m_name, String code, int semester_id) {
        String query = "{call insert_module(?,?,?)}";
        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setString(1, m_name);
            statement.setString(2, code);
            statement.setInt(3, semester_id);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void insert_exam(String e_date, int module_id, String deadline) {
        String query = "{call insert_exam(?,?,?)}";
        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setDate(1, java.sql.Date.valueOf(e_date));
            statement.setInt(2, module_id);
            statement.setDate(3, java.sql.Date.valueOf(deadline));
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void insert_a_session_into_a_module(int module_id, String s_date, String s_time, String e_time) {
        String query = "{call insert_a_session_into_a_module(?,?,?,?)}";

        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setInt(1, module_id);
            statement.setDate(2, java.sql.Date.valueOf(s_date));
            statement.setTime(3, java.sql.Time.valueOf(s_time));
            statement.setTime(4, java.sql.Time.valueOf(e_time));
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void enroll_a_student_in_a_module(int module_id, int student_id) {
        String query = "{call enroll_a_student_in_a_module(?,?)}";
        ResultSet resultSet;
        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setInt(1, module_id);
            statement.setInt(2, student_id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void delete_student(int student_id) {
        String query = "{call delete_student(?)}";

        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setInt(1, student_id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void delete_lecturer(int lec_id) {
        String query = "{call delete_lecturer(?)}";

        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setInt(1, lec_id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete_a_session_into_a_module(int module_id, String s_date, String s_time, String e_time) {
        String query = "{call delete_a_session_into_a_module(?,?,?,?)}";

        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setInt(1, module_id);
            statement.setDate(2, java.sql.Date.valueOf(s_date));
            statement.setTime(3, java.sql.Time.valueOf(s_time));
            statement.setTime(4, java.sql.Time.valueOf(e_time));
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void cancel_module(int module_id) {
        String query = "{call cancel_module(?)}";

        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setInt(1, module_id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void cancel_exam(int exam_id) {
        String query = "{call cancel_module(?)}";
        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setInt(1, exam_id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static JSONArray list_account_by_id(int acc_id) {
        String query = "{call list_account_by_id(?)}";
        ResultSet resultSet;
        try (PreparedStatement statement = getPreparedStatement(query)) {
            statement.setInt(1, acc_id);
            resultSet = statement.executeQuery();
            JSONArray output = JsonConverter.convertAll(resultSet);
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


