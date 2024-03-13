package com.employee.information.management.data.employee.dao.impl;

import com.employee.information.management.app.model.Employee;
import com.employee.information.management.data.connection.ConnectionHelper;
import com.employee.information.management.data.employee.dao.EmployeeDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    Connection c = ConnectionHelper.getConnection();
    private String GET_ALL_EMPLOYEE_STATEMENT;
    private String GET_EMPLOYEE_BY_EMPLOYEE_NO_STATEMENT;


    @Override
    public List<Employee> getAllEmployee()  {
        try {
            PreparedStatement stmt = c.prepareStatement(GET_ALL_EMPLOYEE_STATEMENT);
            ResultSet rs = stmt.executeQuery();
            List<Employee> employees = new ArrayList<>();

            while(rs.next()) {
                employees.add(setEmployee(rs));
            }
            return employees;
        } catch (Exception e) {
        }
        return null;
    }


    @Override
    public Employee getEmployeeByNo(String employee_no) throws RuntimeException {
        try {
            PreparedStatement stmt = c.prepareStatement(GET_EMPLOYEE_BY_EMPLOYEE_NO_STATEMENT);
            stmt.setString(1, employee_no);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                return setEmployee(rs);
            }

        } catch (Exception ignored) {

        }
        return null;
    }
    /**
     * Helper method to create an Employee object from a ResultSet.
     */
    public Employee setEmployee(ResultSet rs) {
        try{
            Employee employee = new Employee();
            employee.setLastName(rs.getString("last_name"));
            employee.setFirstName(rs.getString("first_name"));
            employee.setMiddleName(rs.getString("middle_name"));
            employee.setPositionInRc(rs.getString("position_in_rc"));
            employee.setDateEmployed(rs.getString("date_employed"));
            employee.setBirthdate(rs.getTimestamp("birthdate"));
            employee.setBirthplace(rs.getString("birthplace"));
            employee.setSex(rs.getString("sex"));
            employee.setCivilStatus(rs.getString("civil_status"));
            employee.setCitizenship(rs.getString("citizenship"));
            employee.setReligion(rs.getString("religion"));
            employee.setHeight(rs.getDouble("height"));
            employee.setWeight(rs.getDouble("weight"));
            employee.setEmail(rs.getString("email"));
            employee.setSssNo(rs.getString("sss_no"));
            employee.setTinNo(rs.getString("tin_no"));
            employee.setPagibigNo(rs.getString("pagibig_no"));
            employee.setEmployeeNo(rs.getString("employee_no"));
            return employee;
        }catch(Exception ignored){

        }
        return null;
    }

    //update employee
    @Override
    public Employee getEmployeeByNo(String employeeNo)  {
        String sql = "SELECT * FROM employee WHERE employee_no = ?";
        try (Connection con = ConnectionHelper.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, employeeNo);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String lastName = rs.getString("last_name");
                    String firstName = rs.getString("first_name");
                    String middleName = rs.getString("middle_name");
                    String positionInRc = rs.getString("position_in_rc");
                    String dateEmployed = rs.getString("date_employed");
                    String birthDate = rs.getString("birthdate");
                    String birthPlace = rs.getString("birthplace");
                    String sex = rs.getString("sex");
                    String civilStatus = rs.getString("civil_status");
                    String citizenship = rs.getString("citizenship");
                    String religion = rs.getString("religion");
                    double height = rs.getDouble("height");
                    double weight = rs.getDouble("weight");
                    String email = rs.getString("email");
                    String sssNo = rs.getString("sss_no");
                    String tinNo = rs.getString("tin_no");
                    String pagibigNo = rs.getString("pagibig_no");
                    String employee_no = rs.getString("employee_no");
                    return new Employee(lastName, firstName, middleName, positionInRc, dateEmployed, birthDate, birthPlace, sex, civilStatus, citizenship, religion, height, weight, email, sssNo, tinNo, pagibigNo, employee_no);
                } else {
                    System.err.println("No employee found with employee no: " + employeeNo);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error retrieving employee with employee no " + employeeNo + ": " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean updateEmployee(Employee employee)  {
        String sql = "UPDATE employee SET last_name = ?, first_name = ?, middle_name = ?, position_in_rc = ?, date_employed = ?, birthdate = ?, birthplace = ?, sex = ?, civil_status = ?, citizenship = ?, religion = ?, height = ?, weight = ?, email = ?, sss_no = ?, tin_no = ?, pagibig_no = ? WHERE employee_no = ?";
        try  (Connection con = ConnectionHelper.getConnection();
              PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, employee.getLastName());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getMiddleName());
            statement.setString(4, employee.getPositionInRc());
            statement.setString(5, employee.getDateEmployed());
            statement.setString(6, employee.getBirthdate());
            statement.setString(7, employee.getBirthplace());
            statement.setString(8, employee.getSex());
            statement.setString(9, employee.getCivilStatus());
            statement.setString(10, employee.getCitizenship());
            statement.setString(11, employee.getReligion());
            statement.setDouble(12, employee.getHeight());
            statement.setDouble(13, employee.getWeight());
            statement.setString(14, employee.getEmail());
            statement.setString(15, employee.getSssNo());
            statement.setString(16, employee.getTinNo());
            statement.setString(17, employee.getPagibigNo());
            statement.setString(18, employee.getEmployeeNo());
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.err.println("Error updating employee with no. " + employee.getEmployeeNo() + ": " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}
