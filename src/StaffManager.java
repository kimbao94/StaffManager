import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StaffManager {
    public static void main(String[] args) {
        List<Staff> staffList = new ArrayList<>();
        boolean check = true;
        double totalSalary = 0;

        staffList.add(new StaffFullTime(1,"Son",25, "0952334333","son@gmail.com",10000000));
        staffList.add(new StaffFullTime(2,"Ngoc",26, "0952786878","ngoc@gmail.com",11000000));
        staffList.add(new StaffFullTime(3,"Cong",27, "0945424533","cong@gmail.com",12000000));
        staffList.add(new StaffPartTime(4,"Dung",28, "0955453453","dung@gmail.com"));
        staffList.add(new StaffPartTime(5,"Khien",29, "0945475525","khien@gmail.com"));
        staffList.add(new StaffPartTime(6,"thanh",30, "095374878633","thanh@gmail.com"));
        StaffManager staffManager = new StaffManager();
        do{
            System.out.printf("Nhập vào lựa chọn :%n" +
                    "1. Hiển thị toàn bộ danh sách. %n" +
                    "2. Thêm mới nhân viên. %n" +
                    "3. Nhập tiền thưởng, tiền phạt, số giờ làm cho nhân viên %n" +
                    "4. Hiển thị danh sách nhân viên Fulltime có mức lương thấp hơn mức lương trung bình. %n" +
                    "5. Hiển thị tổng lương phải trả cho tất cả nhân viên Parttime. %n" +
                    "6. Hiển thị danh sách nhân viên Fulltime theo số lương tăng dần. %n" +
                    "7. Thoát. %n"
            );
            Scanner scanner = new Scanner(System.in);
            int selection = scanner.nextInt();
            switch (selection){
                case 1 :
                    for(Staff staff : staffList){
                        System.out.println(staff.toString());
                    }
                    break;
                case 2 :
                    staffManager.addStaff(staffList);
                    break;
                case 3 :
                    totalSalary = staffManager.calculatorSalary(staffList);
                    break;
                case 4 :
                    if (totalSalary != 0){
                        staffManager.underAverageList(staffList,totalSalary);
                    }else {
                        System.out.println("Hãy nhập tiền thưởng, tiền phạt, số giờ làm cho nhân viên trước.");
                    }
                    break;
                case 5 :
                    double totalSalaryPartTime = staffManager.totalSalaryPartTime(staffList);
                    if (totalSalaryPartTime != 0){
                        System.out.print("Tổng lương phải trả cho tất cả nhân viên Parttime là : ");
                        System.out.printf("%1$,.0f", totalSalaryPartTime);
                        System.out.print(" Đồng");
                        System.out.println();
                    }else {
                        System.out.println("Hãy nhập tiền thưởng, tiền phạt, số giờ làm cho nhân viên trước.");
                    }

                    break;
                case 6:
                    List<StaffFullTime> fullTimeList = staffManager.sortFullTimeForSalary(staffList);
                    for (StaffFullTime employeeFullTime : fullTimeList){
                        System.out.println(employeeFullTime.toString());
                    }
                    break;
                case 7 :
                    check = false;
                    break;
            }
        }while (check);

    }

    public void addStaff(List<Staff> staffList){
        String name;
        int age;
        String phone;
        String email;
        int type;
        double salary;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Thêm nhân viên FullTime nhập số 1, nhân viên PartTime nhập số 2.");
        type = scanner.nextInt();
        System.out.println("Nhập vào tên nhân viên :");
        scanner.nextLine();
        name = scanner.nextLine();
        System.out.println("Nhập vào tuổi :");
        age = scanner.nextInt();
        System.out.println("Nhập vào số điện thoại :");
        scanner.nextLine();
        phone = scanner.nextLine();
        System.out.println("Nhập vào email :");
        email = scanner.nextLine();
        System.out.println("Nhập vào lương :");
        salary = scanner.nextDouble();
        if (type == 1){
            Staff staff = new StaffFullTime(staffList.size()+1,name,age,phone,email,salary);
            staffList.add(staff);
            System.out.println("Thêm thành công.");
        }else if (type == 2){
            Staff staff = new StaffPartTime(staffList.size()+1,name,age,phone,email);
            staffList.add(staff);
            System.out.println("Thêm thành công.");
        }else {
            System.out.println("không thành công");
        }

    }

    public double calculatorSalary(List<Staff> staffList){
        Scanner scanner = new Scanner(System.in);
        double totalSalary = 0;
        for (Staff staff : staffList){
            if (staff instanceof StaffFullTime){
                double bonus;
                double forfeit;
                System.out.println("Nhập vào tiền thưởng của nhân viên "+ staff.getId() + " : ");
                bonus = scanner.nextDouble();
                ((StaffFullTime) staff).setBonus(bonus);
                System.out.println("Nhập vào tiền phạt của nhân viên "+ staff.getId() + " : ");
                forfeit = scanner.nextDouble();
                ((StaffFullTime) staff).setForfeit(forfeit);
                ((StaffFullTime) staff).setSalary(((StaffFullTime) staff).getSalary()-(((StaffFullTime) staff).getBonus()-((StaffFullTime) staff).getForfeit()));
                totalSalary += ((StaffFullTime) staff).getSalary();
            }else if (staff instanceof StaffPartTime){
                int workTime;
                System.out.println("Nhập vào số giờ làm của nhân viên "+ staff.getId() + " : ");
                workTime = scanner.nextInt();
                ((StaffPartTime) staff).setWorkTime(workTime);
                totalSalary += ((StaffPartTime) staff).getRealSalary();
            }
        }
        return totalSalary;
    }
    public void underAverageList(List<Staff> staffList, double totalSalary) {
        for (Staff staff : staffList) {
            if (staff instanceof StaffFullTime) {
                if (((StaffFullTime) staff).getSalary() < totalSalary/ staffList.size()) {
                    System.out.println(staff.toString());
                }
            }
        }
    }
    public double totalSalaryPartTime(List<Staff> staffList){
        double totalSalaryPartTime = 0;
        for (Staff staff : staffList){
            if (staff instanceof StaffPartTime){
                totalSalaryPartTime += ((StaffPartTime) staff).getRealSalary();
            }
        }
        return totalSalaryPartTime;
    }
    public List<StaffFullTime> sortFullTimeForSalary(List<Staff> staffList){
        List<StaffFullTime> fullTimeList = new ArrayList<>();
        for (Staff staff : staffList){
            if (staff instanceof StaffFullTime){
                fullTimeList.add((StaffFullTime) staff);
            }
        }
        StaffFullTime swap;
        for (int j = 0; j < fullTimeList.size()-1; j++){
            for (int i = 0; i < fullTimeList.size()-1-j; i++){
                if (fullTimeList.get(i).getSalary() > fullTimeList.get(i+1).getSalary()){
                    swap = fullTimeList.get(i);
                    fullTimeList.set(i,fullTimeList.get(i+1));
                    fullTimeList.set(i+1,swap);
                }
            }
        }
        return fullTimeList;
    }
}