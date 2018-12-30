package hcmute.edu.vn.adminservice.controller;

import hcmute.edu.vn.adminservice.api.v1.data.DataReturnList;
import hcmute.edu.vn.adminservice.api.v1.data.DataReturnOne;
import hcmute.edu.vn.adminservice.api.v1.dto.CatDto;
import hcmute.edu.vn.adminservice.api.v1.dto.UserDto;
import hcmute.edu.vn.adminservice.api.v1.mapper.CatMapper;
import hcmute.edu.vn.adminservice.api.v1.mapper.RoleMapper;
import hcmute.edu.vn.adminservice.api.v1.mapper.UserMapper;
import hcmute.edu.vn.adminservice.exception.NotFoundException;
import hcmute.edu.vn.adminservice.model.Report;
import hcmute.edu.vn.adminservice.model.Role;
import hcmute.edu.vn.adminservice.model.User;
import hcmute.edu.vn.adminservice.service.CatService;
import hcmute.edu.vn.adminservice.service.ReportService;
import hcmute.edu.vn.adminservice.service.RoleService;
import hcmute.edu.vn.adminservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CatService catService;

    @Autowired
    private CatMapper catMapper;

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
    public DataReturnList<UserDto> retrieveAllUsers(){
        DataReturnList<UserDto> dataReturnList = new DataReturnList<>();
        List<User> users = new ArrayList<User>();
        users = userService.retrieveAllUsers();
        if(users.isEmpty()){
            throw new NotFoundException("User not found!");
        }
        dataReturnList.setMessage("success !");
        dataReturnList.setData(users.stream().map(userMapper::userToUserDto).collect(Collectors.toList()));
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
    @GetMapping("/users/status/{uid}/{userUpdate}")
    public DataReturnOne<User> updateUserStatus(@PathVariable long uid, @PathVariable String userUpdate){
        DataReturnOne<User> dataReturnOne=new DataReturnOne<>();
        dataReturnOne.setSuccess("true");
        dataReturnOne.setMessage("success");
        dataReturnOne.setData(userService.updateUserStatus(uid, userUpdate));
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

    @GetMapping("/cat")
    public DataReturnList<CatDto> retrieveAllCat()
    {
        DataReturnList<CatDto> dataReturnList = new DataReturnList<>();
        dataReturnList.setSuccess("true");
        dataReturnList.setMessage("success");
        dataReturnList.setData(catService.retrieveAllCatChecked().stream().map(catMapper::listcatTolistCatDto)
                                .collect(Collectors.toList()));
        return dataReturnList;
    }

    @PostMapping("/roles/createrole/{userCreate}")
    public DataReturnOne<Role> CreateRole(@RequestBody Role role, @PathVariable String userCreate)
    {
        role.setDateCreated(new Date());
        role.setUserCreated(userCreate);
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

    @PostMapping("/roles/updaterole/{userUpdate}")
    public DataReturnOne<Role> UpdateRole(@RequestBody Role role, @PathVariable String userUpdate)
    {
        role.setUserUpdated(userUpdate);
        role.setDateUpdated(new Date());
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

//    @PutMapping("/roles/updatestatus/{rid}/{userUpdate}")
//    public DataReturnOne<Role> UpdateStatusRole(@PathVariable long rid, @PathVariable String userUpdate)
//    {
//        Role role1 = roleService.retrieveRoleByRId(rid);
//        DataReturnOne<Role> dataReturnOne = new DataReturnOne<>();
//        if(role1!=null){
//            if(role1.getStatus() == 1)
//            {
//                role1.setStatus(0);
//            }
//            else
//            {
//                role1.setStatus(1);
//            }
//            role1.setDateUpdated(new Date());
//            role1.setUserUpdated(userUpdate);
//            dataReturnOne.setData(roleService.getRepo().save(role1));
//            dataReturnOne.setSuccess("true");
//            dataReturnOne.setMessage("Set status Role Success");
//        }else{
//            dataReturnOne.setSuccess("false");
//            dataReturnOne.setData(null);
//            dataReturnOne.setMessage("Set status Role Fail");
//        }
//        return dataReturnOne;
//    }

    @PutMapping("/roles/updaterolecreate/{rid}/{userUpdate}")
    public DataReturnOne<Role> UpdateRoleCreate(@PathVariable long rid, @PathVariable String userUpdate)
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
            role1.setDateUpdated(new Date());
            role1.setUserUpdated(userUpdate);
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

    @PutMapping("/roles/updateroleapprove/{rid}/{userUpdate}")
    public DataReturnOne<Role> UpdateRoleApprove(@PathVariable long rid, @PathVariable String userUpdate)
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
            role1.setDateUpdated(new Date());
            role1.setUserUpdated(userUpdate);
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

    @PutMapping("/roles/updateroleupdate/{rid}/{userUpdate}")
    public DataReturnOne<Role> UpdateRoleUpdate(@PathVariable long rid,  @PathVariable String userUpdate)
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
            role1.setDateUpdated(new Date());
            role1.setUserUpdated(userUpdate);
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

    @PutMapping("/roles/updateroledelete/{rid}/{userUpdate}")
    public DataReturnOne<Role> UpdateRoleDelete(@PathVariable long rid, @PathVariable String userUpdate)
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
            role1.setDateUpdated(new Date());
            role1.setUserUpdated(userUpdate);
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

    @PutMapping("/roles/updateroleadmin/{rid}/{userUpdate}")
    public DataReturnOne<Role> UpdateRoleAdmin(@PathVariable long rid, @PathVariable String userUpdate)
    {
        Role role1 = roleService.retrieveRoleByRId(rid);
        DataReturnOne<Role> dataReturnOne = new DataReturnOne<>();
        if(role1!=null){
            if(role1.getP_admin() == true)
            {
                role1.setP_admin(false);
            }
            else
            {
                role1.setP_admin(true);
            }
            role1.setDateUpdated(new Date());
            role1.setUserUpdated(userUpdate);
            dataReturnOne.setData(roleService.getRepo().save(role1));
            dataReturnOne.setSuccess("true");
            dataReturnOne.setMessage("Set admin for Role success");
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Set admin for Role Fail");
        }
        return dataReturnOne;
    }




}
