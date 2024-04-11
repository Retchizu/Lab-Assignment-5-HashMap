package hashmapassignment;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DepartmentDA {

    private HashMap<String, Employee> employeeHashMap;
    public DepartmentDA(){
        try{
            Scanner depFile = new Scanner(new FileReader("E:\\JavaForSchool\\ooplab\\src\\hashmapassignment\\dep.csv"));
            Integer key = 1;
            Double totalSalary = 0.0;
            employeeHashMap = new HashMap<>();
            while(depFile.hasNext()){
                String depFileLineData = depFile.nextLine();
                String[] splitDepFileData = depFileLineData.split(",");

                Department department = new Department();
                department.setDepCode(splitDepFileData[0].trim());
                department.setDepName(splitDepFileData[1].trim());

                Scanner depTempFile = new Scanner(new FileReader("E:\\JavaForSchool\\ooplab\\src\\hashmapassignment\\deptemp.csv"));

                while(depTempFile.hasNext()){
                    String depTempFileLineData = depTempFile.nextLine();
                    String[] splitDepTempFile = depTempFileLineData.split(",");

                    EmployeeDA employeeDA = new EmployeeDA(splitDepTempFile[1].trim());
                    if(splitDepTempFile[0].trim().equals(department.getDepCode())){
                        totalSalary += Double.parseDouble((splitDepTempFile[2].trim()));
                        employeeDA.getEmployee().setSalary(Double.parseDouble(splitDepTempFile[2].trim()));
                        Double parsedSalary = Double.parseDouble(splitDepTempFile[2].trim());
                    }
                  employeeHashMap.put(employeeDA.getEmployee().getEmpNo() + key, employeeDA.getEmployee());
                }
                department.setTotalSalary(totalSalary);
                department.setEmployeeMap(employeeHashMap);
                printDepartment(department);
            }




        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void printDepartment(Department department) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        System.out.println("Department Code: " + department.getDepCode());
        System.out.println("Department Name: " + department.getDepName());
        System.out.println("Department total Salary: " + df.format(department.getTotalSalary()));
        System.out.println();
        System.out.println("------------Details----------------");
        System.out.printf("%-10s %-20s %10s\n", "EmpNo", "EmployeeName", "Salary");
        for (Map.Entry<String, Employee> entryMap : department.getEmployeeMap().entrySet()) {
            Employee employee = entryMap.getValue();
            if(employee.getSalary() == null) continue;
            System.out.printf("%-10s %-20s %10s\n", entryMap.getKey(),
                    employee.getLastName() + ", " + employee.getFirstName(), df.format(employee.getSalary()));
        }
        System.out.println();
        System.out.println();
    }
}
