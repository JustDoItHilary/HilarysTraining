package com.example.testmvprxjava;

import java.util.List;

/**
 * Created by Administrator on 2016/8/3.
 *
 */
public interface UserView {

    public void setUser(List<User> user);
    public void setUserObject(User user);
    void setUserList(List<User> users);

    void serListUseConcat(Integer integer);
}
