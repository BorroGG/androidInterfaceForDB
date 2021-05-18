package com.example.testproject.db.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.testproject.db.entities.CustomEntity;

import java.util.List;

@Dao
public interface CustomEntityDao {

    @Transaction
    @Query("SELECT cc.id_criminal_case AS cc_id_criminal_case, cc.date_of_excitement AS cc_date_of_excitement," +
            "          cc.date_of_filing AS cc_date_of_filing, oe.lastname AS oe_lastname, oe.firstname AS oe_firstname," +
            "          oe.middle_name AS oe_middle_name, oe.rank AS oe_rank, a.lastname AS a_lastname," +
            "          a.firstname AS a_firstname, a.middle_name AS a_middle_name, qoc.article AS qoc_article," +
            "          qoc.sign AS qoc_sign, qoc.part AS qoc_part, qoc.item AS qoc_item, co.title AS co_title," +
            "          j.lastname AS j_lastname, j.firstname AS j_firstname, j.middle_name AS j_middle_name," +
            "          s.date_of_issue AS s_date_of_issue, s.content AS s_content, j.id_judge AS j_id_judge, c.date_and_time_of_crime AS c_date_and_time_of_crime" +
            "   FROM accused a" +
            "       JOIN participants_in_crime pic ON a.id_accused = pic.id_accused" +
            "       JOIN crime c ON pic.id_crime = c.id_crime" +
            "       JOIN criminal_case cc ON cc.id_criminal_case = c.id_criminal_case" +
            "       JOIN judge j ON cc.id_judge = j.id_judge" +
            "       JOIN sentence s ON j.id_judge = s.id_judge" +
            "       JOIN organ_employee oe ON cc.id_organ_employee = oe.id_organ_employee" +
            "       JOIN qualification_of_crime qoc ON c.id_qualification_of_crime = qoc.id_qualification_of_crime" +
            "       JOIN court co ON cc.id_court = co.id_court" +
            "   WHERE c.id_criminal_case = id_sentence")
    List<CustomEntity> loadCustomEntityNamesForUs();
}
