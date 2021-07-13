package group.bonjai.bodhi.controllers;

import group.bonjai.bodhi.exceptions.ConstraintViolation;
import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.models.DepartmentMember;
import group.bonjai.bodhi.repositories.DepartmentMemberRepository;
import group.bonjai.bodhi.requests.TransferADepartmentMemberRequest;
import group.bonjai.bodhi.responses.TransferADepartmentMemberResponse;
import group.bonjai.bodhi.usecases.TransferADepartmentUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TransferADepartmentMemberController {
    private final TransferADepartmentUseCase transferADepartmentUseCase;
    private final DepartmentMemberRepository departmentMemberRepository;


    public TransferADepartmentMemberController(TransferADepartmentUseCase transferADepartmentUseCase, DepartmentMemberRepository departmentMemberRepository) {
        this.transferADepartmentUseCase = transferADepartmentUseCase;
        this.departmentMemberRepository = departmentMemberRepository;
    }

    @PostMapping("/departmentmembers/transfers")
    public TransferADepartmentMemberResponse transfer(@Valid @RequestBody TransferADepartmentMemberRequest request)
    throws ResourceNotFound, ConstraintViolation {

        DepartmentMember departmentMember = transferADepartmentUseCase.execute(request.getDepartmentMemberId(),
                request.getNewDepartmentId(), request.getNewRole());

        return new TransferADepartmentMemberResponse(HttpStatus.OK, departmentMember);
    }

    @GetMapping("/departmentmembers")
    public List<DepartmentMember> getAll(){
        return departmentMemberRepository.findAll();
    }
}
