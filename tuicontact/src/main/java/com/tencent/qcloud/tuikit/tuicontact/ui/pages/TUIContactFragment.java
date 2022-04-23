package com.tencent.qcloud.tuikit.tuicontact.ui.pages;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.qcloud.tuicore.TUICore;
import com.tencent.qcloud.tuicore.component.action.PopActionClickListener;
import com.tencent.qcloud.tuicore.component.action.PopMenuAction;
import com.tencent.qcloud.tuicore.component.fragments.BaseFragment;
import com.tencent.qcloud.tuikit.tuicontact.TUIContactService;
import com.tencent.qcloud.tuikit.tuicontact.bean.ContactItemBean;
import com.tencent.qcloud.tuicore.component.menu.Menu;
import com.tencent.qcloud.tuikit.tuicontact.presenter.ContactPresenter;
import com.tencent.qcloud.tuikit.tuicontact.ui.view.ContactLayout;
import com.tencent.qcloud.tuikit.tuicontact.ui.view.ContactListView;
import com.tencent.qcloud.tuikit.tuicontact.R;
import com.tencent.qcloud.tuikit.tuicontact.TUIContactConstants;
import com.tencent.qcloud.tuikit.tuicontact.util.TUIContactLog;

import java.util.ArrayList;
import java.util.List;

//通讯录
public class TUIContactFragment extends BaseFragment {

    private static final String TAG = TUIContactFragment.class.getSimpleName();
    private ContactLayout mContactLayout;

    private ContactPresenter presenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.contact_fragment, container, false);
        initViews(baseView);

        return baseView;
    }

    private void initViews(View view) {
        // 从布局文件中获取通讯录面板
        mContactLayout = view.findViewById(R.id.contact_layout);

        presenter = new ContactPresenter();
        presenter.setFriendListListener();
        mContactLayout.setPresenter(presenter);
        mContactLayout.initDefault();

        mContactLayout.getContactListView().setOnItemClickListener(new ContactListView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, ContactItemBean contact) {
                if (position == 0) {
                    Intent intent = new Intent(TUIContactService.getAppContext(), NewFriendActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    TUIContactService.getAppContext().startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(TUIContactService.getAppContext(), GroupListActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    TUIContactService.getAppContext().startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(TUIContactService.getAppContext(), BlackListActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    TUIContactService.getAppContext().startActivity(intent);
                } else {
                    //通讯录 人点击   跳转个人信息
                    Intent intent = new Intent(TUIContactService.getAppContext(), FriendProfileActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(TUIContactConstants.ProfileType.CONTENT, contact);
                    TUIContactService.getAppContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        TUIContactLog.i(TAG, "onResume");
    }
}
