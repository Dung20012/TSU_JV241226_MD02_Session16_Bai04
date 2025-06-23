package BTVN.Exericse02;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        try (
                Connection conn = DriverManager.getConnection(Database.URL, Database.USER, Database.PASSWORD);
                CallableStatement stmt = conn.prepareCall("{CALL get_all_students()}")
        ) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("student_id"),
                        rs.getString("full_name"),
                        rs.getString("date_of_birth")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách: " + e.getMessage());
        }
        return list;
    }

    public void addStudent(Student s) {
        try (
                Connection conn = DriverManager.getConnection(Database.URL, Database.USER, Database.PASSWORD);
                CallableStatement stmt = conn.prepareCall("{CALL add_student(?, ?)}")
        ) {
            stmt.setString(1, s.getFullName());
            stmt.setString(2, s.getDateOfBirth());
            stmt.execute();
            System.out.println("Thêm thành công.");
        } catch (SQLException e) {
            System.out.println("Lỗi thêm: " + e.getMessage());
        }
    }

    public void updateStudent(Student s) {
        try (
                Connection conn = DriverManager.getConnection(Database.URL, Database.USER, Database.PASSWORD);
                CallableStatement stmt = conn.prepareCall("{CALL update_student(?, ?, ?)}")
        ) {
            stmt.setInt(1, s.getId());
            stmt.setString(2, s.getFullName());
            stmt.setString(3, s.getDateOfBirth());
            stmt.execute();
            System.out.println("Cập nhật thành công.");
        } catch (SQLException e) {
            System.out.println("Lỗi cập nhật: " + e.getMessage());
        }
    }

    public void deleteStudent(int id) {
        try (
                Connection conn = DriverManager.getConnection(Database.URL, Database.USER, Database.PASSWORD);
                CallableStatement stmt = conn.prepareCall("{CALL delete_student(?)}")
        ) {
            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("Xóa thành công.");
        } catch (SQLException e) {
            System.out.println("Lỗi xóa: " + e.getMessage());
        }
    }

    public List<Student> searchStudent(String name) {
        List<Student> list = new ArrayList<>();
        try (
                Connection conn = DriverManager.getConnection(Database.URL, Database.USER, Database.PASSWORD);
                CallableStatement stmt = conn.prepareCall("{CALL search_student(?)}")
        ) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("student_id"),
                        rs.getString("full_name"),
                        rs.getString("date_of_birth")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm: " + e.getMessage());
        }
        return list;
    }
}
