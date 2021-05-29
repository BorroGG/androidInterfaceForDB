package com.example.testproject.db.entities;

import java.io.Serializable;

public class EntityForCriminal_caseAndJudge implements Serializable {
    public Integer id_criminal_case;
    public String date_of_excitement;
    public String date_of_filing;
    public Integer id_statement; /*REFERENCES statement(id_statement),*/
    public String id_organ_employee; /*REFERENCES organ_employee(id_organ_employee),*/
    public Integer id_court; /*REFERENCES court(id_court),*/
    public String id_judge;
}
