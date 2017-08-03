package ${package}.client.common.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.format.DateTimeFormat;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by ${userName} on ${today}.
 */
public class DateUtils {
    public static final long MILLIS_PER_SECOND = DateTimeConstants.MILLIS_PER_SECOND;
    public static final long MILLIS_PER_MINUTE = DateTimeConstants.MILLIS_PER_MINUTE;
    public static final long MILLIS_PER_HOUR = DateTimeConstants.MILLIS_PER_HOUR;
    public static final long MILLIS_PER_DAY = DateTimeConstants.MILLIS_PER_DAY;
    public static final long MILLIS_PER_WEEK = DateTimeConstants.MILLIS_PER_WEEK;
    public static final int MONDAY = DateTimeConstants.MONDAY;
    public static final int TUESDAY = DateTimeConstants.TUESDAY;
    public static final int WEDNESDAY = DateTimeConstants.WEDNESDAY;
    public static final int THURSDAY = DateTimeConstants.THURSDAY;
    public static final int FRIDAY = DateTimeConstants.FRIDAY;
    public static final int SATURDAY = DateTimeConstants.SATURDAY;
    public static final int SUNDAY = DateTimeConstants.SUNDAY;

    public static long parseMillis(String dateTime, String pattern) {
        return DateTimeFormat.forPattern(pattern).parseMillis(dateTime);
    }

    public static String formatFull(long timeMillis) {
        return DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS").print(timeMillis);
    }

    public static String formatMillis(long timeMillis, String pattern) {
        return DateTimeFormat.forPattern(pattern).print(timeMillis);
    }

    public static long toEndOfDay(long time) {
        return formatDay(time, MILLIS_PER_DAY - 1);
    }

    public static long toEndOfDay(long time, long rawOffSet) {
        return formatDay(time, MILLIS_PER_DAY - 1, rawOffSet);
    }

    public static long toBeginOfDay(long time) {
        return formatDay(time);
    }

    public static long toBeginOfDay(long time, long rawOffSet) {
        return formatDay(time, 0, rawOffSet);
    }

    public static int dayOfWeek(long time) {
        return new DateTime(time).dayOfWeek().get();
    }

    public static int minuteOfHour(long time) {
        return new DateTime(time).minuteOfHour().get();
    }

    /**
     * monday
     *
     * @param time
     * @return
     */
    public static long toBeginOfWeek(long time) {
        return toBeginOfDay(toDayOfWeek(time, MONDAY));
    }

    /**
     * sunday
     *
     * @param time
     * @return
     */
    public static long toEndOfWeek(long time) {
        return toEndOfDay(toDayOfWeek(time, SUNDAY));
    }

    /**
     * @param time
     * @param dayOfWeek [1,7]
     * @return
     */
    public static long toDayOfWeek(long time, int dayOfWeek) {
        return plusDays(time, dayOfWeek - dayOfWeek(time));
    }

    public static int dayOfMonth(long time) {
        return new DateTime(time).dayOfMonth().get();
    }

    public static long toBeginOfMonth(long time) {
        return toBeginOfDay(toDayOfMonth(time, 1));
    }

    public static long toEndOfMonth(long time) {
        return plusMonths(toBeginOfMonth(time), 1) - 1;
    }

    public static long toDayOfMonth(long time, int dayOfMonth) {
        return plusDays(time, dayOfMonth - dayOfMonth(time));
    }

    public static long plusYears(long time, long year) {
        return new DateTime(time).plusYears((int) year).getMillis();
    }

    public static long plusMonths(long time, long month) {
        return new DateTime(time).plusMonths((int) month).getMillis();
    }

    public static long plusWeeks(long time, long weeks) {
        return plusX(time, weeks, MILLIS_PER_WEEK);
    }

    public static long plusDays(long time, long days) {
        return plusX(time, days, MILLIS_PER_DAY);
    }

    public static long plusHours(long time, long hours) {
        return plusX(time, hours, MILLIS_PER_HOUR);
    }

    public static long plusMinutes(long time, long minutes) {
        return plusX(time, minutes, MILLIS_PER_MINUTE);
    }

    public static long plusSeconds(long time, long seconds) {
        return plusX(time, seconds, MILLIS_PER_SECOND);
    }

    public static long plusX(long time, long x, long xUnit) {
        return time + x * xUnit;
    }

    public static long toMillis(long time, TimeUnit timeUnit) {
        return covertTime(time, timeUnit, TimeUnit.MILLISECONDS);
    }

    public static long formatDay(long time) {
        return formatDay(time, 0);
    }

    public static long formatDay(long time, long millis) {
        return formatDay(time, millis, TimeZone.getDefault().getRawOffset());
    }

    public static long formatDay(long time, long millis, long rawOffSet) {
        return covertTime(covertTime(time + rawOffSet, TimeUnit.MILLISECONDS, TimeUnit.DAYS), TimeUnit.DAYS, TimeUnit.MILLISECONDS) - rawOffSet + millis;
    }

    public static long covertTime(long time, TimeUnit fromTimeUnit, TimeUnit toTimeUnit) {
        return toTimeUnit.convert(time, fromTimeUnit);
    }

    public static long divisible(long time, long unit) {
        return divisible(time, unit, 0);
    }

    public static long divisible(long time, long unit, long plus) {
        return (time / unit + plus) * unit;
    }

    public static void main(String[] args) {
        // System.out.println(formatMillis(toBeginOfDay(System.currentTimeMillis()), "yyyy-MM-dd HH:mm:ss.SSS"));
        // System.out.println(formatMillis(toEndOfDay(System.currentTimeMillis()), "yyyy-MM-dd HH:mm:ss.SSS"));
        // System.out.println(formatMillis(toBeginOfWeek(System.currentTimeMillis()), "yyyy-MM-dd HH:mm:ss.SSS"));
        // System.out.println(formatMillis(toEndOfWeek(System.currentTimeMillis()), "yyyy-MM-dd HH:mm:ss.SSS"));
        // System.out.println(formatMillis(toBeginOfMonth(System.currentTimeMillis()), "yyyy-MM-dd HH:mm:ss.SSS"));
        // System.out.println(formatMillis(toEndOfMonth(System.currentTimeMillis()), "yyyy-MM-dd HH:mm:ss.SSS"));
        //
        // DateTime dateTime = new DateTime(System.currentTimeMillis());
        // System.out.println(dateTime.withMillisOfDay(0));
        // System.out.println(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS").print(dateTime.getMillis()));
        System.out.println(formatMillis(divisible(System.currentTimeMillis(), 5 * MILLIS_PER_MINUTE, 0), "yyyy-MM-dd HH:mm:ss.SSS"));
    }
}
