package ${package}.client.common.utils;

import lombok.Getter;

/**
 * Created by ${userName} on ${today}.
 */
public class NumberUtils {

    public static String toChinese(int number) {
        return toChinese(number, false);
    }

    public static String toChinese(int number, boolean capital) {
        return toChinese(String.valueOf(number), capital);
    }

    public static String toChinese(Integer number) {
        return toChinese(number, false);
    }

    public static String toChinese(Integer number, boolean capital) {
        if (number == null) {
            return null;
        }
        return toChinese(number.longValue(), capital);
    }

    public static String toChinese(long number) {
        return toChinese(number, false);
    }

    public static String toChinese(long number, boolean capital) {
        return toChinese(String.valueOf(number), capital);
    }

    public static String toChinese(Long number) {
        return toChinese(number, false);
    }

    public static String toChinese(Long number, boolean capital) {
        if (number == null) {
            return null;
        }
        return toChinese(number.longValue(), capital);
    }

    private static String toChinese(String number, boolean capital) {
        StringBuilder sb = new StringBuilder();
        Number chineseNumber = null;
        boolean isAppendZero = false;
        boolean isAppendUnit = false;
        for (int size = number.length() - 1, i = size; i >= 0; i--) {
            // 从右边开始转换
            chineseNumber = Number.get(number.charAt(size - i));
            if (Number.ZERO.equals(chineseNumber)) {
                // 需要追加汉字 0
                isAppendZero = true;
                isAppendUnit = TenThousandUnit.isAppendUnit(i);
                if (isAppendUnit) {
                    sb.append(TenThousandUnit.get(i).getChinese(capital));
                }
                continue;
            } else if (isAppendZero && !isAppendUnit) {
                sb.append(Number.ZERO.getChinese(capital));
            }
            isAppendZero = false;
            sb.append(chineseNumber.getChinese(capital)).append(TenUnit.get(i).getChinese(capital)).append(TenThousandUnit.get(i).getChinese(capital));
        }
        return sb.toString();
    }

    @Getter
    public enum Number {
        ZERO(0, "〇", "零"),
        ONE(1, "一", "壹"),
        TWO(2, "二", "贰"),
        THREE(3, "三", "叁"),
        FOUR(4, "四", "肆"),
        FIVE(5, "五", "伍"),
        SIX(6, "六", "陆"),
        SEVEN(7, "七", "柒"),
        EIGHT(8, "八", "捌"),
        NINE(9, "九", "玖");
        private final int number;
        private final String chinese;
        private final String capital;

        Number(int number, String chinese, String capital) {
            this.number = number;
            this.chinese = chinese;
            this.capital = capital;
        }

        public String getChinese(boolean capital) {
            return capital ? this.capital : this.chinese;
        }

        public static Number get(char c) {
            return get(c - '0');
        }

        public static Number get(int c) {
            if (c >= 0 && c <= 9) {
                return Number.values()[c];
            }
            throw new IllegalArgumentException("argument is invalid");
        }
    }

    @Getter
    private enum TenUnit {
        NULL(0, "", ""), TEN(1, "十", "拾"), HUNDRED(2, "百", "佰"), THOUSAND(3, "千", "仟");
        private static final int length = TenUnit.values().length;
        private final long min;
        private final int index;
        private final String chinese;
        private final String capital;

        TenUnit(int index, String chinese, String capital) {
            min = (long) Math.pow(10, index);
            this.index = index;
            this.chinese = chinese;
            this.capital = capital;
        }

        public String getChinese(boolean capital) {
            return capital ? this.capital : this.chinese;
        }

        public static TenUnit get(int index) {
            return TenUnit.values()[index % TenUnit.values().length];
        }
    }

    @Getter
    private enum TenThousandUnit {
        NULL(0, "", ""),
        TEN_THOUSAND(1, "万", "万"),
        ONE_HUNDRED_MILLION(2, "亿", "亿"),
        TRILLION(3, "兆", "兆"),
        TEN_THOUSAND_TRILLION(4, "京", "京");

        private static int length = TenThousandUnit.values().length;
        private final long min;
        private final int index;
        private final String chinese;
        private final String capital;

        TenThousandUnit(int index, String chinese, String capital) {
            min = (long) Math.pow(10000, index);
            this.index = index;
            this.chinese = chinese;
            this.capital = capital;
        }

        public String getChinese(boolean capital) {
            return capital ? this.capital : this.chinese;
        }

        public static boolean isAppendUnit(int index) {
            return index % TenUnit.length == 0;
        }

        public static TenThousandUnit get(int index) {
            if (isAppendUnit(index)) {
                return TenThousandUnit.values()[(index / TenUnit.length) % TenThousandUnit.length];
            }
            return NULL;
        }
    }

    public static void main(String[] args) {
        System.out.println(toChinese(Long.MAX_VALUE, true));
        System.out.println(toChinese(1001));
        System.out.println(toChinese(10202001));
        System.out.println(toChinese(10204234));
        System.out.println(toChinese(10234010));
        System.out.println(toChinese(1001340));
        System.out.println(toChinese(1000000));
        System.out.println(toChinese(1));
        System.out.println(toChinese(2));
        System.out.println(toChinese(3));
    }
}
