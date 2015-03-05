/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Employees;
import Service.EmployeesSearchServices;
import Service.SearchServicesUtils;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;



/**
 *
 * @author wanchana
 */
@ManagedBean
@ViewScoped
public class EmployeesControl implements Serializable{

    private List<Employees> employees;
    private String query;
    private String searchBy;

    private Employees employee;

    @PostConstruct
    public void PostConstrut(){
        onSearch();
    }

    public void onSearch() {
        EmployeesSearchServices service = SearchServicesUtils.findServiceByName(searchBy);
        employees = service.search(query);
    }

    public void onClear() {
        query = null;
        searchBy = null;
        onSearch();
    }

    public void onDelete() {
        System.out.println("delelte id = " + employee.getId());
        notifyMessage();
    }

    public void notifyMessage() {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(
                                FacesMessage.SEVERITY_INFO,
                                "Delete Employee",
                                "success (id " + employee.getId() + ")"
                        ));

    }

    public List<Employees> getEmployees() {
        if (employees == null) {
            employees = new LinkedList<>();
        }

        return employees;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    private Object request(String param) {
        return FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get(param);
    }

    public void onSelect() {
        Integer id = Integer.valueOf((String) request("employeeId"));
        Employees emp = new Employees();
        emp.setId(id);
        int index = getEmployees().indexOf(emp);
        employee = getEmployees().get(index);
    }

    public Employees getEmployee() {
        if (employee == null) {
            employee = new Employees();
        }

        return employee;
    }
}
