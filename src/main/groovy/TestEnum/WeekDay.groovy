package TestEnum

enum WeekDay {
    Monday("Today is Monday"),Tuesday("today is Tuesday"), Wednesday("today is Wednesday"),
    Thursday("today is thursday"),
    Friday("today is friday"), Saturday("today is saturday"),
    Sunday("today is sunday")
    final String isDay
    WeekDay(String isDay){
        this.isDay=isDay
    }

}