package cn.edu.bjtu.ebosscenario.domain;

import java.util.Date;
import java.util.List;

public class RecentScenario {
    private Date startDate;
    private Date endDate;
    private int count;
    private List<Scenario> details;

    public RecentScenario(Date startDate, Date endDate, List<Scenario> details) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.count = details.size();
        this.details = details;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Scenario> getDetails() {
        return details;
    }

    public void setDetails(List<Scenario> details) {
        this.details = details;
    }
}
