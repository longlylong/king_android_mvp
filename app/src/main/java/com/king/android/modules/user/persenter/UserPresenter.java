package com.king.android.modules.user.persenter;

import com.king.android.account.Account;
import com.king.android.account.AccountManager;
import com.king.android.modules.user.api.UserApi;
import com.king.android.modules.user.dto.LoginInfoDto;
import com.king.android.modules.user.dto.LoginVo;
import com.king.android.modules.user.view.UserView;
import com.simga.library.base.BasePresenter;
import com.simga.library.http.bean.BaseResult;

import retrofit2.Call;

public class UserPresenter extends BasePresenter<UserView> {

    public void userLogin(String phone, String password) {
        postJson(new HttpListener<LoginInfoDto>() {

            @Override
            public Call<BaseResult<LoginInfoDto>> getApi() {
                return getProtocol(UserApi.class).loginSys(new LoginVo(phone, password));
            }

            @Override
            public void onHttpSuccess(LoginInfoDto loginInfoDto) {
                Account account = AccountManager.getInstance().getAccount();
                account.setToken(loginInfoDto.getToken());

                AccountManager.getInstance().saveAccount(account);
                mView.loginSuccess(loginInfoDto);
            }
        });
    }


}
