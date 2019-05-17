package com.mkyong.rest;
 
import java.util.List;
 
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
import org.o7planning.restfulcrud.dao.EmployeeDAO;
import org.o7planning.restfulcrud.model.Employee;
 
@Path("/employees")
public class EmployeeService {
 
    // URI:
    // /contextPath/servletPath/employees
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
    public List<Employee> getEmployees_JSON() {
        List<Employee> listOfCountries = EmployeeDAO.getAllEmployees();
        return listOfCountries;
    }
 
    // URI:
    // /contextPath/servletPath/employees/{empNo}
    @GET
    @Path("/{empNo}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
    public Employee getEmployee(@PathParam("empNo") String empNo) {
        return EmployeeDAO.getEmployee(empNo);
    }
 
    // URI:
    // /contextPath/servletPath/employees
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
    public Employee addEmployee(Employee emp) {
        return EmployeeDAO.addEmployee(emp);
    }
 
    // URI:
    // /contextPath/servletPath/employees
    @PUT
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
    public Employee updateEmployee(Employee emp) {
        return EmployeeDAO.updateEmployee(emp);
    }
 
    @DELETE
    @Path("/{empNo}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
    public void deleteEmployee(@PathParam("empNo") String empNo) {
        EmployeeDAO.deleteEmployee(empNo);
    }
 
}