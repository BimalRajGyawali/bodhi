package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.models.DepartmentMember;

import java.util.List;
import java.util.Map;


public interface IListAllDepartmentsUseCase {
    Map<Department, DepartmentMember> execute();
}
