package me.shouheng.omnilist.model;

import android.content.Context;

import java.util.Calendar;
import java.util.Date;

import me.shouheng.omnilist.PalmApp;
import me.shouheng.omnilist.R;
import me.shouheng.omnilist.model.enums.AlarmType;
import me.shouheng.omnilist.model.enums.ModelType;
import me.shouheng.omnilist.model.tools.DaysOfMonth;
import me.shouheng.omnilist.model.tools.DaysOfWeek;
import me.shouheng.omnilist.provider.annotation.Column;
import me.shouheng.omnilist.provider.annotation.Table;
import me.shouheng.omnilist.provider.schema.AlarmSchema;
import me.shouheng.omnilist.utils.TimeUtils;


/**
 * Created by wangshouheng on 2017/4/18. */
@Table(name = AlarmSchema.TABLE_NAME)
public class Alarm extends Model {

    @Column(name = AlarmSchema.MODEL_CODE)
    private long modelCode;

    @Column(name = AlarmSchema.MODEL_TYPE)
    private ModelType modelType;

    @Column(name = AlarmSchema.ALARM_TYPE)
    private AlarmType alarmType;

    @Column(name = AlarmSchema.HOUR)
    private int hour;

    @Column(name = AlarmSchema.MINUTE)
    private int minute;

    @Column(name = AlarmSchema.DAYS_OF_WEEK)
    private DaysOfWeek daysOfWeek;

    @Column(name = AlarmSchema.DAYS_OF_MONTH)
    private DaysOfMonth daysOfMonth;

    @Column(name = AlarmSchema.START_DATE)
    private Date startDate;

    @Column(name = AlarmSchema.END_DATE)
    private Date endDate;

    @Column(name = AlarmSchema.NEXT_TIME)
    private Calendar nextTime;

    @Column(name = AlarmSchema.ENABLED)
    private boolean enabled;

    @Override
    public long getCode() {
        return (int) super.getCode();
    }

    @Override
    public void setCode(long code) {
        super.setCode((int) code);
    }

    public long getModelCode() {
        return modelCode;
    }

    public void setModelCode(long modelCode) {
        this.modelCode = modelCode;
    }

    public ModelType getModelType() {
        return modelType;
    }

    public void setModelType(ModelType modelType) {
        this.modelType = modelType;
    }

    public AlarmType getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(AlarmType alarmType) {
        this.alarmType = alarmType;
    }

    public DaysOfWeek getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(DaysOfWeek daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public Calendar getNextTime() {
        return nextTime;
    }

    public void setNextTime(Calendar nextTime) {
        this.nextTime = nextTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public DaysOfMonth getDaysOfMonth() {
        return daysOfMonth;
    }

    public void setDaysOfMonth(DaysOfMonth daysOfMonth) {
        this.daysOfMonth = daysOfMonth;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "modelCode=" + modelCode +
                ", modelType=" + (modelType == null ? null : modelType.name()) +
                ", alarmType=" + (alarmType == null ? null : alarmType.name()) +
                ", hour=" + hour +
                ", minute=" + minute +
                ", daysOfWeek=" + daysOfWeek +
                ", daysOfMonth=" + daysOfMonth +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", nextTime=" + nextTime +
                ", enabled=" + enabled +
                "} " + super.toString();
    }

    public String toChinese(){
        return "闹钟类型：【" + (alarmType == null ? "" : alarmType.name()) + "】" +
                "\n实体类型：【" + (modelType == null ? "" : modelType.name()) +"】"  +
                "\n实体编号：【" + modelCode + "】" +
                "\n周次重复：【" + daysOfWeek.toString(PalmApp.getContext(), true) + "】" +
                "\n响铃时间：【" + TimeUtils.shortTime(hour, minute) + "】" +
                "\n日期设置：【" + TimeUtils.getShortDate(PalmApp.getContext(), endDate) + "】" +
                "\n下次响铃：【" + TimeUtils.shortDateTime(nextTime.getTime()) + "】" +
                "\n最后更新：【" + TimeUtils.shortDateTime(getLastModifiedTime())+  "】" +
                "\n起止日期：【" + TimeUtils.getShortDate(PalmApp.getContext(), startDate) + "-" + TimeUtils.getShortDate(PalmApp.getContext(), endDate) + "】" + "\n";
    }

    public String getAlarmInfo(Context context){
        switch (alarmType){
            case SPECIFIED_DATE:
                return TimeUtils.getShortDate(context, endDate)  + " " + TimeUtils.shortTime(hour, minute);
            case WEEK_REPEAT:
                return daysOfWeek.toString(context, true)  + " " + TimeUtils.shortTime(hour, minute) + " "
                        + context.getString(R.string.until) + " " + TimeUtils.getShortDate(context, endDate);
            default:
                return null;
        }
    }
}
