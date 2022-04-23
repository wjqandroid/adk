package com.visionvera.psychologist.account_module.contracts;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.mvp.view.IBaseRetrofitView;
import com.visionvera.psychologist.account_module.beans.AccountLoginRequestBean;
import com.visionvera.psychologist.account_module.beans.AccountLoginResponseBean;
import com.visionvera.psychologist.account_module.beans.AccountDentifyingCodeRequest;
import com.visionvera.psychologist.account_module.beans.AccountRegisterRequest;
import com.visionvera.psychologist.account_module.beans.PhoneLoginRequest;
import com.visionvera.psychologist.account_module.beans.PhoneLoginResponseBean;

public interface IContract {

    interface AccountLoginActivity {
        interface View extends IBaseRetrofitView {
            void onLogin(AccountLoginResponseBean responseBean, ResultType resultType, String errorMsg);
        }

        interface Presenter {
            void login(AccountLoginRequestBean requestBean, LifecycleProvider lifecycleProvider);
        }
    }


    interface AccountRegisterActivity{
        interface AccountRegisterView extends IBaseRetrofitView{
            void onSmsCode(BaseResponseBean2 response,ResultType resultType, String errorMsg);

            void onRegister(BaseResponseBean2 response,ResultType resultType, String errorMsg);
        }
        interface AccountRegisterBasePresenter{
            void getSmsCode(AccountDentifyingCodeRequest registerRequest, LifecycleProvider lifecycleProvider);

            void getRegister(AccountRegisterRequest accountRegisterRequest,LifecycleProvider lifecycleProvider);
        }
    }

    interface PhoneLoginActivity{
        interface PhoneLoginView extends IBaseRetrofitView{
            void onSmsCode(BaseResponseBean2 response,ResultType resultType, String errorMsg);

            void onPhoneLogin(PhoneLoginResponseBean response, ResultType resultType, String errorMsg);
        }
        interface PhoneLoginBasePresenter{
            void getSmsCode(AccountDentifyingCodeRequest registerRequest, LifecycleProvider lifecycleProvider);

            void getPhoneLogin(PhoneLoginRequest request, LifecycleProvider lifecycleProvider);
        }

    }

}
