package com.example.root.jobify.Services;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.root.jobify.R;
import com.example.root.jobify.Services.Auth.UserAuthService;
import com.example.root.jobify.Views.ChatPage.ChatActivity;
import com.example.root.jobify.Views.GenericContentListPage.ContentListFragment;
import com.example.root.jobify.Views.LogInCompletition.LogInCompletitionActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by root on 15/12/16.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {


    private static final String TAG = "NOTIFICATION";
    public static final String NOTIFICATION_USERNAME_KEY = "username";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if(UserAuthService.getInstance().getToken()!=null){

            // Check if message contains a data payload.
            if (remoteMessage.getData().size() > 0 && remoteMessage.getData().containsKey(NOTIFICATION_USERNAME_KEY)) {
                Log.d(TAG, "Message data payload: " + remoteMessage.getData());
                sendNotification(remoteMessage.getData().get("name"),remoteMessage.getData().get("message"),remoteMessage.getData().get(NOTIFICATION_USERNAME_KEY));
            }

        } else {
            this.startActivity(new Intent(this, LogInCompletitionActivity.class));
        }

    }

    private void sendNotification(String title,String message,String username) {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(ContentListFragment.CONTENT_ID,username);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_discuss)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
