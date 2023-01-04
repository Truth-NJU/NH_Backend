package com.example.backendnh.MapperTest;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.backendnh.BackendNhApplication;
import com.example.backendnh.mapper.NhuserMapper;
import com.example.backendnh.po.Nhuser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = BackendNhApplication.class)
public class NhuserTest {

    @Autowired
    NhuserMapper nhuserMapper;

//    @Test
//    // @Transactional
//    public void addUser() {
//        Nhuser nhuser = new Nhuser(null, new Date(), "鼓楼校区", "tzh19850355091@163.com", "19850355091", "123456", "tzh", "1", "klein", "sad", "sad");
//        System.out.println(nhuserMapper.insert(nhuser));
//    }
//
//    @Test
//    public void deleteUser() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("user_name", "klein");
//        assert nhuserMapper.deleteByMap(map) == 1;
//    }
//
//
//    @Test
//    public void update() {
//        UpdateWrapper<Nhuser> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.eq("user_name", "klein");
//        Nhuser user = new Nhuser();
//        user.setUserMobile("13236082668");
//
//        assert nhuserMapper.update(user, updateWrapper) == 1;
//    }
//
//    @Test
//    public void search() {
//        Map<String, Object> searchMap = new HashMap<>();
//        searchMap.put("user_name", "klein");
//        Nhuser user = nhuserMapper.selectByMap(searchMap).get(0);
//        System.out.println(user.toString());
//        assert user.getUserEmail().equals("tzh19850355091@163.com");
//    }
}
