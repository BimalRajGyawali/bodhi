package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.models.Department;

import java.util.List;


public interface IListAllDepartmentsUseCase {
    List<Department> execute();
}
