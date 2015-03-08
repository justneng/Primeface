/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wanchana
 */
public class SearchServicesUtils {

    private static Map<String, EmployeesSearchServices> searchServiceMap;

    private static synchronized void registerServices() {
        searchServiceMap = new HashMap<>();
        searchServiceMap.put("default", new EmployeesSearchServicesImplements());
        searchServiceMap.put("name", new EmployeesSearchByNameServicesImplements());
        searchServiceMap.put("email", new EmployeesSearchByEmailServiceImplements());
        searchServiceMap.put("phone", new EmployeesSearchByPhoneServiceImplements());
        searchServiceMap.put("salary", new EmployeesSearchBySalaryServiceImplements());
    }

    public static EmployeesSearchServices findServiceByName(String name) {
        if (searchServiceMap == null) {
            registerServices();
        }

        EmployeesSearchServices service = searchServiceMap.get(name);
        return service == null ? searchServiceMap.get("default") : service;
    }

}
