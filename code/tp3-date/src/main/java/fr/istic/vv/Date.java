package fr.istic.vv;

class Date implements Comparable<Date> {

    int day;
    int month;
    int year;

    public Date(int day, int month, int year) throws InvalidDate {
        if(!isValidDate(day, month, year))
            throw new InvalidDate("Not a valid date");

        this.day = day;
        this.month = month;
        this.year = year;
    }
    public Date(Date d){
        day = d.day;
        month = d.month;
        year = d.year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        if(month < 1 || month > 12)
            return false;
        if(day < 1 || day > getLastDayOfMonth(month, year))
            return false;
        return true;
    }

    public static boolean isLeapYear(int year) {
        return year%4 == 0 && year%100 != 0 || year%400 == 0;
    }

    public static int getLastDayOfMonth(int month, int year){
        if(month == 2)
            return isLeapYear(year) ? 29 : 28;
        return (month%2 == 1) ^ (month > 7) ? 31 : 30;
    }

    public Date nextDate() {
        Date d = new Date(this);
        d.day++;

        if(d.day > getLastDayOfMonth(d.month, d.year)){
            d.month++;
            d.day = 1;
        }
        if(d.month > 12){
            d.year++;
            d.month = 1;
        }

        assert(isValidDate(d.day, d.month, d.year));

        return d;
    }

    public Date previousDate() {
        Date d = new Date(this);
        d.day--;

        if(d.day < 1){
            d.month--;
        }
        if(d.month < 1){
            d.year--;
            d.month = 12;
        }
        if(d.day < 1)
            d.day = getLastDayOfMonth(d.month, d.year);

        assert(isValidDate(d.day, d.month, d.year));
        return d;
    }

    public int compareTo(Date other) {
        if(year == other.year){
            if(month == other.month){
                if(day == other.day){
                    return 0;
                }else if(day < other.day)
                    return -1;
                return 1;
            }else if(month < other.month)
                return -1;
            return 1;
        }else if(year < other.year)
            return -1;
        return 1;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        if(day < 10)
            s.append('0');
        s.append(day);
        s.append('/');
        if(month < 10)
            s.append('0');
        s.append(month);
        s.append('/');
        if(year < 10)
            s.append('0');
        s.append(year);

        return s.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Date && compareTo((Date)obj) == 0;
    }
}