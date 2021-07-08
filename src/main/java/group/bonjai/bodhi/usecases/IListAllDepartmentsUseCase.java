package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.models.Teacher;

import java.util.List;
import java.util.Map;


public interface IListAllDepartmentsUseCase {
    Map<Department, List<Teacher>> execute();
}
