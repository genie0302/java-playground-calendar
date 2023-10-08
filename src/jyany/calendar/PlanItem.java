package jyany.calendar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlanItem {
    private final LocalDate date;
    private final List<String> planList;

    public static LocalDate getDateFromString (String strDate){
        return LocalDate.parse(strDate);
    }
    public PlanItem (String strDate, String plan){
        this.date = getDateFromString(strDate);
        this.planList = new ArrayList<>();
        addPlan(plan);
    }
    public void addPlan(String plan){
        planList.add(plan);
    }
    public void showPlan(){
        System.out.println(planList.size() + "개의 일정이 있습니다.");
        for (int i = 0; i < planList.size(); i++){
            System.out.println(i+1 + ". " + planList.get(i));
        }
    }
}
