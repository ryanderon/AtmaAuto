package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Employee {
    @SerializedName("id_employee")
    @Expose
    private int idEmployee;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("salary")
    @Expose
    private int salary;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("branch")
    @Expose
    private String branch;
    @SerializedName("id_role")
    @Expose
    private int idRole;
    @SerializedName("id_branch")
    @Expose
    private int idBranch;

    /**
     * No args constructor for use in serialization
     *
     */
    public Employee() {
    }

    /**
     *
     * @param phoneNumber
     * @param address
     * @param name
     * @param idRole
     * @param branch
     * @param role
     * @param idBranch
     * @param salary
     * @param idEmployee
     */
    public Employee(int idEmployee, String name, String address, String phoneNumber, int salary, String role, String branch, int idRole, int idBranch) {
        super();
        this.idEmployee = idEmployee;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.role = role;
        this.branch = branch;
        this.idRole = idRole;
        this.idBranch = idBranch;
    }

    public Employee(int idEmployee, String name) {
        super();
        this.idEmployee = idEmployee;
        this.name = name;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public int getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(int idBranch) {
        this.idBranch = idBranch;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Employee){
            Employee c = (Employee) obj;
            if(c.getName().equals(name) && c.getIdEmployee()==idEmployee ) return true;
        }

        return false;
    }

}
