/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Employees;
import java.util.List;

/**
 *
 * @author wanchana
 */
public interface EmployeesSearchServices {
    List<Employees> search(String keyword);
}
