package hashmapassignment;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class EmployeeDA {

    Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }



    public EmployeeDA(String empNo) {
        try{
            Scanner employeeFile = new Scanner(new FileReader("E:\\JavaForSchool\\ooplab\\src\\hashmapassignment\\emp.csv"));

            while(employeeFile.hasNext()){
                String employeeFileLineData = employeeFile.nextLine();
                String[] splitEmployeeFile = employeeFileLineData.split(",");

                if(splitEmployeeFile[0].trim().equals(empNo)){
                    employee = new Employee();
                    employee.setEmpNo(splitEmployeeFile[0].trim());
                    employee.setLastName(splitEmployeeFile[1].trim());
                    employee.setFirstName(splitEmployeeFile[2].trim());
                }
            }

            employeeFile.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
