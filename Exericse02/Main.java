package BTVN.Exericse02;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final StudentDAO dao = new StudentDAO();
    public static void main(String[] args) {
        while (true){
            System.out.println("MENU:");
            System.out.println("1. Hiển thị danh sách sinh viên");
            System.out.println("2. Thêm mới sinh viên");
            System.out.println("3. Sửa sinh viên");
            System.out.println("4. Xóa sinh viên");
            System.out.println("5. Tìm kiếm sinh viên");
            System.out.println("6. Thoát");
            System.out.println("Lựa chọn của bạn:");
            int choice = sc.nextInt();

            switch (choice){
                case 1:
                    showAll();
                    break;
                case 2:
                    add();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    search();
                    break;
                case 6:
                    System.out.println("Thoát");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Lựa chọn không hợp lệ");
            }
        }
    }
    private static void showAll() {
        List<Student> list = dao.getAllStudents();
        if (list.isEmpty()) System.out.println("Không có sinh viên.");
        else list.forEach(s -> System.out.printf("ID: %d | Tên: %s | Ngày sinh: %s\n", s.getId(), s.getFullName(), s.getDateOfBirth()));
    }

    private static void add() {
        String name;
        String dob;

        // Nhập tên
        while (true) {
            System.out.print("Nhập tên: ");
            name = sc.nextLine().trim();
            if (!name.isEmpty()){
                break;
            };
            System.out.println("Tên không được để trống. Vui lòng nhập lại.");
        }

        // Nhập ngày sinh
        while (true) {
            System.out.print("Nhập ngày sinh (yyyy-MM-dd): ");
            dob = sc.nextLine().trim();
            if (dob.isEmpty()) {
                System.out.println("Ngày sinh không được để trống.");
                continue;
            }
            if (!dob.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                System.out.println("Ngày sinh không đúng định dạng. Vui lòng nhập đúng định dạng yyyy-MM-dd.");
                continue;
            }
            break;
        }

        dao.addStudent(new Student(0, name, dob));
    }


    private static void update() {
        int id;

        // Nhập ID sinh viên cần sửa
        while (true) {
            System.out.print("Nhập ID sinh viên cần sửa: ");
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("ID không được để trống.");
                continue;
            }
            try {
                id = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số nguyên hợp lệ.");
            }
        }

        // Nhập tên mới
        String name;
        while (true) {
            System.out.print("Tên mới: ");
            name = sc.nextLine().trim();
            if (!name.isEmpty()) break;
            System.out.println("Tên không được để trống.");
        }

        // Nhập ngày sinh mới
        String dob;
        while (true) {
            System.out.print("Ngày sinh mới (yyyy-MM-dd): ");
            dob = sc.nextLine().trim();
            if (dob.isEmpty()) {
                System.out.println("Ngày sinh không được để trống.");
                continue;
            }
            if (!dob.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                System.out.println("Sai định dạng. Nhập đúng dạng yyyy-MM-dd.");
                continue;
            }
            break;
        }

        dao.updateStudent(new Student(id, name, dob));
    }

    private static void delete() {
        int id;

        // Nhập ID cần xóa
        while (true) {
            System.out.print("Nhập ID cần xóa: ");
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("ID không được để trống.");
                continue;
            }
            try {
                id = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số nguyên hợp lệ.");
            }
        }

        dao.deleteStudent(id);
    }

    private static void search() {
        String keyword;

        while (true) {
            System.out.print("Nhập từ khóa tên sinh viên cần tìm: ");
            keyword = sc.nextLine().trim();
            if (!keyword.isEmpty()) break;
            System.out.println("Từ khóa không được để trống.");
        }

        List<Student> results = dao.searchStudent(keyword);
        if (results.isEmpty()) {
            System.out.println("Không tìm thấy sinh viên nào phù hợp.");
        } else {
            results.forEach(s -> System.out.printf("ID: %d | Tên: %s | Ngày sinh: %s%n",
                    s.getId(), s.getFullName(), s.getDateOfBirth()));
        }
    }

}
