package hcmute.edu.vn.adminservice.controller;

import hcmute.edu.vn.adminservice.api.v1.data.DataReturnList;
import hcmute.edu.vn.adminservice.api.v1.data.DataReturnOne;
import hcmute.edu.vn.adminservice.api.v1.dto.UserDto;
import hcmute.edu.vn.adminservice.api.v1.mapper.UserMapper;
import hcmute.edu.vn.adminservice.exception.NotFoundException;
import hcmute.edu.vn.adminservice.model.Report;
import hcmute.edu.vn.adminservice.model.User;
import hcmute.edu.vn.adminservice.service.ReportService;
import hcmute.edu.vn.adminservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/admin/")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private ReportService reportService;

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
    public  DataReturnOne<Report> DeleteCategory(@RequestBody Report report){
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


}
