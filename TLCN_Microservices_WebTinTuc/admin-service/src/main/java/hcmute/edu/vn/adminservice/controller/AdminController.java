package hcmute.edu.vn.adminservice.controller;

import hcmute.edu.vn.adminservice.api.v1.data.DataReturnList;
import hcmute.edu.vn.adminservice.api.v1.data.DataReturnOne;
import hcmute.edu.vn.adminservice.api.v1.dto.UserDto;
import hcmute.edu.vn.adminservice.api.v1.mapper.UserMapper;
import hcmute.edu.vn.adminservice.exception.NotFoundException;
import hcmute.edu.vn.adminservice.model.Assign_Permission;
import hcmute.edu.vn.adminservice.model.Permission;
import hcmute.edu.vn.adminservice.model.Report;
import hcmute.edu.vn.adminservice.model.User;
import hcmute.edu.vn.adminservice.service.Assign_Permission_Service;
import hcmute.edu.vn.adminservice.service.PermissionService;
import hcmute.edu.vn.adminservice.service.ReportService;
import hcmute.edu.vn.adminservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/admin/")
@CrossOrigin
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private Assign_Permission_Service assign_permission_service;

    @Autowired
    private UserMapper userMapper;

    // retrieve user by id //
    @GetMapping("/users/search")
    public DataReturnOne<UserDto> retrieveUserByIdOrEmail(@RequestParam(required = false) long id, @RequestParam(required = false) String email){
        DataReturnOne<UserDto> dataReturnOne=new DataReturnOne<>();
        dataReturnOne.setSuccess("true");
        dataReturnOne.setMessage("success");
        dataReturnOne.setData(userMapper.userToUserDto(userService.retrieveUserByIdOrEmail(id,email)));
        return dataReturnOne;
    }

    // retrieve all users
    @GetMapping("/users")
    public DataReturnList<User> retrieveAllUsers(){
        DataReturnList<User> dataReturnList=new DataReturnList<>();
        List<User> users=new ArrayList<User>();
        users = userService.retrieveAllUsers();
        if(users.isEmpty()){
            throw new NotFoundException("User not found!");
        }
        dataReturnList.setMessage("success !");
        dataReturnList.setData(users);
        return dataReturnList;
    }
    // update role for user
    @PutMapping("/users/role/{uid}/{rid}")
    public DataReturnOne<UserDto> updateUserRole(@PathVariable long uid, @PathVariable long rid){
        DataReturnOne<UserDto> dataReturnOne=new DataReturnOne<>();
        dataReturnOne.setMessage("success");
        dataReturnOne.setSuccess("true");
        dataReturnOne.setData(userMapper.userToUserDto(userService.updateRoleForUser(uid,rid)));
        return dataReturnOne;
    }

    // update status for user
    @PutMapping("/users/status/{uid}")
    public DataReturnOne<UserDto> updateUserStatus(@PathVariable long uid){
        DataReturnOne<UserDto> dataReturnOne=new DataReturnOne<>();
        dataReturnOne.setSuccess("true");
        dataReturnOne.setMessage("success");
        dataReturnOne.setData(userMapper.userToUserDto(userService.updateUserStatus(uid)));
        return dataReturnOne;
    }


    @GetMapping("/reports")
    public DataReturnList<Report> retrieveAllReport()
    {
        DataReturnList<Report> dataReturnList = new DataReturnList<>();
        dataReturnList.setSuccess("success");
        dataReturnList.setMessage("true");
        dataReturnList.setData(reportService.retrieveAllReport());
        return dataReturnList;
    }

    @DeleteMapping("/reports/delete")
    public  DataReturnOne<Report> DeleteReport(@RequestBody Report report){
        DataReturnOne<Report> dataReturnOne = new DataReturnOne<>();
        try{
            reportService.deleteReport(report.getId());
            dataReturnOne.setMessage("Delete Report Success");
            dataReturnOne.setData(null);
        }catch (Exception e){
            dataReturnOne.setSuccess("false");
            dataReturnOne.setMessage(e.getMessage());
            dataReturnOne.setData(null);
        }
        return dataReturnOne;
    }
    @GetMapping("/permissions")
    public DataReturnList<Permission> retrieveAllPermission()
    {
        DataReturnList<Permission> dataReturnList = new DataReturnList<>();
        dataReturnList.setSuccess("success");
        dataReturnList.setMessage("true");
        dataReturnList.setData(permissionService.retrieveAllPermission());
        return dataReturnList;
    }

    @DeleteMapping("/permission/delete")
    public  DataReturnOne<Permission> DeletePermission(@RequestBody Permission permission){
        DataReturnOne<Permission> dataReturnOne = new DataReturnOne<>();
        try{
            Permission permissionUpdate = permissionService.deletePermission(permission);
            dataReturnOne.setMessage("Delete Permission Success");
            dataReturnOne.setData(permissionUpdate);
        }catch (Exception e){
            dataReturnOne.setSuccess("false");
            dataReturnOne.setMessage(e.getMessage());
            dataReturnOne.setData(null);
        }
        return dataReturnOne;
    }

    @PutMapping("/permission/update")
    public  DataReturnOne<Permission> UpdatePermission(@RequestBody Permission permission){
        DataReturnOne<Permission> dataReturnOne = new DataReturnOne<>();
        try{
            Permission permissionUpdate = permissionService.updatePermission(permission);
            dataReturnOne.setMessage("Update Permission Success");
            dataReturnOne.setData(permissionUpdate);
        }catch (Exception e){
            dataReturnOne.setSuccess("false");
            dataReturnOne.setMessage(e.getMessage());
            dataReturnOne.setData(null);
        }
        return dataReturnOne;
    }

    @PostMapping("/permission/create")
    public DataReturnOne<Permission> createPermission(@RequestBody Permission permission){
        DataReturnOne<Permission> dataReturnOne = new DataReturnOne<>();
        try{
            Permission permissionCreate = permissionService.createPermission(permission);
            dataReturnOne.setMessage("Create Permission Success");
            dataReturnOne.setData(permissionCreate);
        }catch (Exception e){
            dataReturnOne.setSuccess("false");
            dataReturnOne.setMessage(e.getMessage());
            dataReturnOne.setData(null);
        }
        return dataReturnOne;
    }

    @GetMapping("/assign_permissions")
    public DataReturnList<Assign_Permission> retrieveAllAssignPermission()
    {
        DataReturnList<Assign_Permission> dataReturnList = new DataReturnList<>();
        dataReturnList.setSuccess("success");
        dataReturnList.setMessage("true");
        dataReturnList.setData(assign_permission_service.retrieveAllAssignPermission());
        return dataReturnList;
    }

    @DeleteMapping("/assign_permissions/delete")
    public  DataReturnOne<Assign_Permission> DeleteAssignPermission(@RequestBody Assign_Permission assign_permission){
        DataReturnOne<Assign_Permission> dataReturnOne = new DataReturnOne<>();
        try{
            assign_permission_service.deleteAssignPermission(assign_permission);
            dataReturnOne.setMessage("Delete Assign Permission Success");
            dataReturnOne.setData(null);
        }catch (Exception e){
            dataReturnOne.setSuccess("false");
            dataReturnOne.setMessage(e.getMessage());
            dataReturnOne.setData(null);
        }
        return dataReturnOne;
    }

    @PutMapping("/assign_permissions/update")
    public  DataReturnOne<Assign_Permission> UpdateAssignPermission(@RequestBody Assign_Permission assign_permission){
        DataReturnOne<Assign_Permission> dataReturnOne = new DataReturnOne<>();
        try{
            Assign_Permission assignPermissionUpdate = assign_permission_service.updateAssignPermission(assign_permission);
            dataReturnOne.setMessage("Update Assign Permission Success");
            dataReturnOne.setData(assignPermissionUpdate);
        }catch (Exception e){
            dataReturnOne.setSuccess("false");
            dataReturnOne.setMessage(e.getMessage());
            dataReturnOne.setData(null);
        }
        return dataReturnOne;
    }

    @PostMapping("/assign_permissions/create")
    public DataReturnOne<Assign_Permission> createAssignPermission(@RequestBody Assign_Permission assign_permission){
        DataReturnOne<Assign_Permission> dataReturnOne = new DataReturnOne<>();
        try{
            Assign_Permission assignPermissionCreate = assign_permission_service.createAssignPermission(assign_permission);
            dataReturnOne.setMessage("Create Permission Success");
            dataReturnOne.setData(assignPermissionCreate);
        }catch (Exception e){
            dataReturnOne.setSuccess("false");
            dataReturnOne.setMessage(e.getMessage());
            dataReturnOne.setData(null);
        }
        return dataReturnOne;
    }

}
