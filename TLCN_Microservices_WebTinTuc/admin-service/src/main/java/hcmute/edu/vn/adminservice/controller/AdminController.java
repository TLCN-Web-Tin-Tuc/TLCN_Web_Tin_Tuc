package hcmute.edu.vn.adminservice.controller;

import hcmute.edu.vn.adminservice.api.v1.data.DataReturnList;
import hcmute.edu.vn.adminservice.api.v1.data.DataReturnOne;
import hcmute.edu.vn.adminservice.api.v1.mapper.UserMapper;
import hcmute.edu.vn.adminservice.exception.NotFoundException;
import hcmute.edu.vn.adminservice.model.*;
import hcmute.edu.vn.adminservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/admin/")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private RoleService roleService;



    @Autowired
    private UserMapper userMapper;

    // retrieve user by id //
    @GetMapping("/users/search")
    public DataReturnOne<User> retrieveUserByIdOrEmail(@RequestParam(required = false) long id, @RequestParam(required = false) String email){
        DataReturnOne<User> dataReturnOne=new DataReturnOne<>();
        dataReturnOne.setSuccess("true");
        dataReturnOne.setMessage("success");
        dataReturnOne.setData(userService.retrieveUserByIdOrEmail(id,email));
        return dataReturnOne;
    }

    // retrieve all users
    @GetMapping("/users")
    public DataReturnList<User> retrieveAllUsers(){
        DataReturnList<User> dataReturnList = new DataReturnList<>();
        List<User> users = new ArrayList<User>();
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
    public DataReturnOne<User> updateUserRole(@PathVariable long uid, @PathVariable long rid){
        DataReturnOne<User> dataReturnOne=new DataReturnOne<>();
        dataReturnOne.setMessage("success");
        dataReturnOne.setSuccess("true");
        dataReturnOne.setData(userService.updateRoleForUser(uid,rid));
        return dataReturnOne;
    }

    // update status for user
    @GetMapping("/users/status/{uid}")
    public DataReturnOne<User> updateUserStatus(@PathVariable long uid){
        DataReturnOne<User> dataReturnOne=new DataReturnOne<>();
        dataReturnOne.setSuccess("true");
        dataReturnOne.setMessage("success");
        dataReturnOne.setData(userService.updateUserStatus(uid));
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

    @GetMapping("/roles")
    public DataReturnList<Role> retrieveAllRole()
    {
        DataReturnList<Role> dataReturnList = new DataReturnList<>();
        dataReturnList.setSuccess("true");
        dataReturnList.setMessage("success");
        dataReturnList.setData(roleService.retrieveAllRole());
        return dataReturnList;
    }

    @PostMapping("/roles/createrole")
    public DataReturnOne<Role> CreateRole(@RequestBody Role role)
    {
        Role role1 = roleService.getRepo().save(role);
        DataReturnOne<Role> dataReturnOne = new DataReturnOne<>();
        if(role1!=null){
            dataReturnOne.setData(role1);
            dataReturnOne.setSuccess("true");
            dataReturnOne.setMessage("Create Role success");
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Create Role Fail");
        }
        return dataReturnOne;
    }

    @PostMapping("/roles/updaterole")
    public DataReturnOne<Role> UpdateRole(@RequestBody Role role)
    {
        Role role1 = roleService.getRepo().save(role);
        DataReturnOne<Role> dataReturnOne = new DataReturnOne<>();
        if(role1!=null){
            dataReturnOne.setData(role1);
            dataReturnOne.setSuccess("true");
            dataReturnOne.setMessage("Update Role success");
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Update Role Fail");
        }
        return dataReturnOne;
    }

    @GetMapping("/roles/{rid}")
    public DataReturnOne<Role> FindRole(@PathVariable("rid") long id){
        DataReturnOne<Role> roleDataReturnOne=new DataReturnOne<>();
        try {
            Role role = roleService.retrieveRoleByRId(id);
            roleDataReturnOne.setSuccess("true");
            roleDataReturnOne.setMessage("success");
            roleDataReturnOne.setData(role);
        }catch (NotFoundException ex){
            roleDataReturnOne.setSuccess("false");
            roleDataReturnOne.setMessage("error");
            roleDataReturnOne.setData(null);
        }
        return roleDataReturnOne;
    }

    @PutMapping("/roles/updatestatus/{rid}")
    public DataReturnOne<Role> UpdateStatusRole(@PathVariable long rid)
    {
        Role role1 = roleService.retrieveRoleByRId(rid);
        DataReturnOne<Role> dataReturnOne = new DataReturnOne<>();
        if(role1!=null){
            if(role1.getStatus() == 1)
            {
                role1.setStatus(0);
            }
            else
            {
                role1.setStatus(1);
            }
            dataReturnOne.setData(roleService.getRepo().save(role1));
            dataReturnOne.setSuccess("true");
            dataReturnOne.setMessage("Set status Role Success");
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Set status Role Fail");
        }
        return dataReturnOne;
    }

    @PutMapping("/roles/updaterolecreate/{rid}")
    public DataReturnOne<Role> UpdateRoleCreate(@PathVariable long rid)
    {
        Role role1 = roleService.retrieveRoleByRId(rid);
        DataReturnOne<Role> dataReturnOne = new DataReturnOne<>();
        if(role1!=null){
            if(role1.getP_create() == true)
            {
                role1.setP_create(false);
            }
            else
            {
                role1.setP_create(true);
            }
            dataReturnOne.setData(roleService.getRepo().save(role1));
            dataReturnOne.setSuccess("true");
            dataReturnOne.setMessage("Set create for Role success");
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Set create for Role Fail");
        }
        return dataReturnOne;
    }

    @PutMapping("/roles/updateroleapprove/{rid}")
    public DataReturnOne<Role> UpdateRoleApprove(@PathVariable long rid)
    {
        Role role1 = roleService.retrieveRoleByRId(rid);
        DataReturnOne<Role> dataReturnOne = new DataReturnOne<>();
        if(role1!=null){
            if(role1.getP_approve() == true)
            {
                role1.setP_approve(false);
            }
            else
            {
                role1.setP_approve(true);
            }
            dataReturnOne.setData(roleService.getRepo().save(role1));
            dataReturnOne.setSuccess("true");
            dataReturnOne.setMessage("Set approve for Role success");
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Set approve for Role Fail");
        }
        return dataReturnOne;
    }

    @PutMapping("/roles/updateroleupdate/{rid}")
    public DataReturnOne<Role> UpdateRoleUpdate(@PathVariable long rid)
    {
        Role role1 = roleService.retrieveRoleByRId(rid);
        DataReturnOne<Role> dataReturnOne = new DataReturnOne<>();
        if(role1!=null){
            if(role1.getP_update() == true)
            {
                role1.setP_update(false);
            }
            else
            {
                role1.setP_update(true);
            }
            dataReturnOne.setData(roleService.getRepo().save(role1));
            dataReturnOne.setSuccess("true");
            dataReturnOne.setMessage("Set update for Role success");
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Set update for Role Fail");
        }
        return dataReturnOne;
    }

    @PutMapping("/roles/updateroledelete/{rid}")
    public DataReturnOne<Role> UpdateRoleDelete(@PathVariable long rid)
    {
        Role role1 = roleService.retrieveRoleByRId(rid);
        DataReturnOne<Role> dataReturnOne = new DataReturnOne<>();
        if(role1!=null){
            if(role1.getP_delete() == true)
            {
                role1.setP_delete(false);
            }
            else
            {
                role1.setP_delete(true);
            }
            dataReturnOne.setData(roleService.getRepo().save(role1));
            dataReturnOne.setSuccess("true");
            dataReturnOne.setMessage("Set delete for Role success");
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Set delete for Role Fail");
        }
        return dataReturnOne;
    }




}
