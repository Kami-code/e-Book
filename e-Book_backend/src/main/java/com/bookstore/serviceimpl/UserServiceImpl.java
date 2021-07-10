package com.bookstore.serviceimpl;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.OrderDao;
import com.bookstore.dao.UserDao;
import com.bookstore.dto.DataPage;
import com.bookstore.dto.UserDto;
import com.bookstore.dto.DataPage;
import com.bookstore.entity.User;
import com.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDto LogIn(String username, String password) throws Exception {
        UserDto userDto = userDao.getUserDtoByUsernameAndPassword(username, password);
        if (userDto == null) {
            throw new Exception("找不到用户！");
        }
        return userDto;
    }

    @Override
    public UserDto SignUp(String username, String password, String telnum, String email) throws Exception {
        UserDto userDto = userDao.getUserDtoByUsername(username);
        if (userDto != null) {
            throw new Exception("用户重复！");
        }
        User user = new User(username, password, email, telnum, 1, 0);
        userDto = userDao.saveByUser(user);
        if (userDto == null) {
            throw new Exception("未知错误！");
        }
        return userDto;
    }

    @Override
    public List<UserDto> GetAll () throws Exception {
        List<UserDto> userDtos = userDao.getAllUserDtos();
        return userDtos;
    }

    @Override
    public UserDto ChangeBlockedStatus(Long userid, int status) throws Exception{
        UserDto userDto = userDao.setBlockedStatus(userid, status);
        return userDto;
    }

    @Override
    public DataPage<User> GetUserPage(int pageNumber) {
        //查询当前表的所有记录数
        Long totalRecord = userDao.count();
        //调用有参构造函数，创建page对象
        DataPage<User> page = new DataPage<User>(totalRecord.intValue(), pageNumber);
        //第三步，查询分页列表数据并设置到page对象中
        if (pageNumber < 0 || page.getPageNumber() > page.getTotalPage()) {
            return page;
        }
        Page<User> userPage = userDao.getUserByPage(pageNumber,page.getPageSize());
        page.setData(userPage.toList());
        return page;
    }
}
