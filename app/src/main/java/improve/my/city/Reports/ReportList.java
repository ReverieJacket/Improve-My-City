package improve.my.city.Reports;

import java.util.ArrayList;

public class ReportList {
    private ArrayList <Report> reports;

    public ReportList() {
        this.reports = new ArrayList<Report>();
    }


    public void addReport(Report report){
        this.reports.add(report);
    }
    public Report getReport (int i){
        if(i < this.reports.size()){
            return this.reports.get(i);
        }
        return null;
    }

    public Report searchReport (String id){
        int i;
        for(i = 0; i < this.reports.size(); i++){
            if(this.reports.get(i).getId().equals(id)){
                return this.reports.get(i);
            }
        }
        return null;
    }

    public boolean reportExist(String id){
        int i;
        for(i = 0; i < this.reports.size(); i++){
            if(this.reports.get(i).getId()== id){
                return true;
            }
        }
        return false;
    }

    
    public String toString(int i){

        return this.getReport(i).toString();
    }
    public int getSize(){
        return this.reports.size();
    }
}

    

