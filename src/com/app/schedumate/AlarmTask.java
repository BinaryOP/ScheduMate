package com.app.schedumate;
import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
 
public class AlarmTask implements Runnable{
    private final Calendar date;
    private final AlarmManager am;
    private final Context context;
 
    public AlarmTask(Context context, Calendar date) {
        this.context = context;
        this.am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        this.date = date;
    }
    
    @Override
    public void run() {
        
        Intent i = new Intent(context, NotifyReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 
        SystemClock.elapsedRealtime(), 1, pi);
        
        am.set(AlarmManager.RTC, date.getTimeInMillis(), pi);
    }
}