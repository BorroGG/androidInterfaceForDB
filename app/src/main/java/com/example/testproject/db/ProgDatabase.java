package com.example.testproject.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Transaction;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.testproject.db.dao.AccusedDao;
import com.example.testproject.db.dao.Category_of_crimesDao;
import com.example.testproject.db.dao.Condition_of_committingDao;
import com.example.testproject.db.dao.CourtDao;
import com.example.testproject.db.dao.CrimeDao;
import com.example.testproject.db.dao.Criminal_caseDao;
import com.example.testproject.db.dao.CustomEntityDao;
import com.example.testproject.db.dao.Damage_categoryDao;
import com.example.testproject.db.dao.DepartmentDao;
import com.example.testproject.db.dao.Focus_categoryDao;
import com.example.testproject.db.dao.Group_of_persons_directoryDao;
import com.example.testproject.db.dao.JudgeDao;
import com.example.testproject.db.dao.LogDao;
import com.example.testproject.db.dao.OrganDao;
import com.example.testproject.db.dao.Organ_employeeDao;
import com.example.testproject.db.dao.Participants_in_crimeDao;
import com.example.testproject.db.dao.Qualification_of_crimeDao;
import com.example.testproject.db.dao.Reference_previously_convictedDao;
import com.example.testproject.db.dao.SentenceDao;
import com.example.testproject.db.dao.StatementDao;
import com.example.testproject.db.dao.VictimDao;
import com.example.testproject.db.entities.Accused;
import com.example.testproject.db.entities.Category_of_crimes;
import com.example.testproject.db.entities.Condition_of_committing;
import com.example.testproject.db.entities.Court;
import com.example.testproject.db.entities.Crime;
import com.example.testproject.db.entities.Criminal_case;
import com.example.testproject.db.entities.Damage_category;
import com.example.testproject.db.entities.Department;
import com.example.testproject.db.entities.Focus_category;
import com.example.testproject.db.entities.Group_of_persons_directory;
import com.example.testproject.db.entities.Judge;
import com.example.testproject.db.entities.Log;
import com.example.testproject.db.entities.Organ;
import com.example.testproject.db.entities.Organ_employee;
import com.example.testproject.db.entities.Participants_in_crime;
import com.example.testproject.db.entities.Qualification_of_crime;
import com.example.testproject.db.entities.Reference_previously_convicted;
import com.example.testproject.db.entities.Sentence;
import com.example.testproject.db.entities.Statement;
import com.example.testproject.db.entities.Victim;

@Database(entities = {Accused.class, Category_of_crimes.class, Condition_of_committing.class,
        Court.class, Crime.class, Criminal_case.class, Damage_category.class, Department.class,
        Focus_category.class, Group_of_persons_directory.class, Judge.class, Organ.class,
        Organ_employee.class, Participants_in_crime.class, Qualification_of_crime.class,
        Reference_previously_convicted.class, Sentence.class, Statement.class, Victim.class, Log.class}, version = 1)
public abstract class ProgDatabase extends RoomDatabase {

    public abstract AccusedDao accusedDao();
    public abstract Criminal_caseDao criminal_caseDao();
    public abstract Organ_employeeDao organ_employeeDao();
    public abstract SentenceDao sentenceDao();
    public abstract StatementDao statementDao();
    public abstract VictimDao victimDao();
    public abstract OrganDao organDao();
    public abstract Qualification_of_crimeDao qualification_of_crimeDao();
    public abstract Group_of_persons_directoryDao group_of_persons_directoryDao();
    public abstract Category_of_crimesDao category_of_crimesDao();
    public abstract Focus_categoryDao focus_categoryDao();
    public abstract Damage_categoryDao damage_categoryDao();
    public abstract Condition_of_committingDao condition_of_committingDao();
    public abstract Reference_previously_convictedDao reference_previously_convictedDao();
    public abstract CourtDao courtDao();
    public abstract JudgeDao judgeDao();
    public abstract CrimeDao crimeDao();
    public abstract Participants_in_crimeDao participants_in_crimeDao();
    public abstract DepartmentDao departmentDao();
    public abstract CustomEntityDao customEntityDao();
    public abstract LogDao logDao();

    private static volatile ProgDatabase INSTANCE;

    public static ProgDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ProgDatabase.class) {
                if (INSTANCE == null) {
                    RoomDatabase.Callback rdc = new RoomDatabase.Callback() {
                        public void onCreate (SupportSQLiteDatabase db) {
                            insertStartData(db);
                        }
                        public void onOpen (SupportSQLiteDatabase db) {

                        }
                    };

                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(),
                                    ProgDatabase.class, "db10")
                            .addCallback(rdc)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    @Transaction
    private static void insertStartData(SupportSQLiteDatabase db){
        Thread thread = new Thread(() -> {
            db.execSQL("INSERT INTO Victim(lastname, firstname, middle_name, gender, birthday, passport, citizenship, social_status, official_position, phone) VALUES" +
                    "('??????????????', '????????????', '????????????????', '1', '1987-08-13', '4215098764', '????????????', '?????????????? ??????????????'," +
                    "'????????????????', '79152762244')," +
                    "('??????????????', '??????????????', '??????????????????????', '2', '1982-05-19', '4560356412', '????????????', '?????????????????????? (???????????????? ??????????????????????, ??????????????????????????)'," +
                    "'?????????? ??????????????????????????', '79849969051')," +
                    "('????????????????', '??????', '????????????????????????', '2', '1995-09-02', '4958550474', '????????????', '?????????????? ??????????????'," +
                    "'??????????????????', '79612305687')," +
                    "('??????????', '??????????????', '??????????????????', '1', '1961-04-25', '4049287072', '????????????', '?????????????????? ???? ?????????????? ??????'," +
                    "'?????????????????????? ???????????? ???????????????? ?????????????????? (????????)', '79669617928')," +
                    "('??????????????', '??????????', '??????????????????????????', '1', '1980-04-14', '4253181718', '????????????', '????????, ???????????????????????? ???????????????????????? ??????????????????'," +
                    "'?????????? ????????????????', '79202681964')," +
                    "('??????????????????', '??????????', '????????????????????????', '2', '1970-05-03', '4095378092', '????????????', '????????????????'," +
                    "'???????????????????????? ??????????????', '79711747098')," +
                    "('??????????????', '??????????', '????????????????', '2', '1973-02-11', '4240870382', '????????????', '???????????????? ???????????????? ?? ??????????????????'," +
                    "'?????????? ???????????????? ?? ??????????????????', '79617758261')," +
                    "('????????????????', '??????????', '????????????????????', '1', '1950-01-08', '4751324871', '??????????????', '?????????????????? ???? ????????????????'," +
                    "'????????????', '79409489066')");
            db.execSQL("INSERT INTO Organ(title_organ) VALUES" +
                    "('???????????????????????? ???????????????????? ?????? ???????????????????? ??????????????????')," +
                    "('?????????????????????? ???????????????????? ??????????????????')," +
                    "('???????????????????????? ?????????????? ???????????????????? ??????????????????')," +
                    "('?????????????????????? ???????????? ???????????????????????? ???????????????????? ??????????????????')");
            db.execSQL("INSERT INTO Department(title_department) VALUES" +
                    "('?????????? ?????? ???????????? ???? ???????????? ?????????????????????????? ???????????? ????????????')," +
                    "('?????????? ?????? ???????????? ???? ?????????????????? ???????????? ??. ????????????')," +
                    "('?????????????????????? ?????? ??. ????????????')," +
                    "('???????????????????? ?????????????????????????? ???????????????????????? ?? ?????????????????????????????? ?????????????????? ???? ?????? ???????????? ???? ??. ????????????')," +
                    "('???????????????????????? ?????????? ???? ???????????????????? ????????????')");
            db.execSQL("INSERT INTO Organ_employee VALUES" +
                    "('001101', '????????????????????', '??????????????', '????????????????????', '???????????????? ?? ????', '?????????????? ??????????????????', '4956879398', 1, 1, '$2a$10$zfTN6pyA0MJaoCy/IgqtK./W581zhb.5v7wgEpBJhd5FpJGU6cWBm', 'dobrolyubov_timofei')," +
                    "('001102', '????????', '????????', '??????????????????', '???????????????? ?? ????', '?????????????? ??????????????????', '4958843741', 1, 1, '$2a$10$l1RlNJ8fytpZKFLrKYSXQOnvJgB4vpw4.WQJlVsUnErzu5zemC56u', 'yazov_efim')," +
                    "('002201', '????????????', '??????????????', '??????????????????????', '???????????????? ?? ????', '?????????????? ??????????????????', '4956195717', 1, 2, '$2a$10$F.5CRdkx9CkDqb8COYZXc./4m5i/yyQm46fJGyT41bazM2khPGLou', 'budaev_vasilii')," +
                    "('002202', '????????????????', '????????????', '????????????????????', '???????????????? ?? ????', '?????????????? ??????????????????', '4954796390', 1, 2, '$2a$10$vFW40cRtlD128GEut3A3v.T/NGpgRTMpIET/9Gdlf7M4Mijj1Vwei', 'dyakonov_filipp')," +
                    "('003301', '??????????????????', '????????????', '????????????????????', '????????????????', '??????????????-?????????????????? ??????????????', '4956574461', 2, 3, '$2a$10$lOahUgBJo63.elDoJEO8GOixY9Dql.EgIsZ2XhXHYbXV8X4jepKCu', 'chuprakova_love')," +
                    "('004401', '????????????????', '??????????', '????????????????????', '????????????????????????????????????', '?????????????? ???????????????????? ????????????', '4957714440', 1, 4, '$2a$10$i/DjDHVQPCm1ZREkT38IE.PaHBj.r1b6PEFUpYtwAJypb9KBFvQK.', 'teplukhin_pavel')," +
                    "('005501', '????????????', '??????????????????', '????????????????', '??????????????????????', '???????????????????????? ??????????????', '4958118915', 3, 5, '$2a$10$Clv.XnsAl6CPRIM1ZsBOVeUysTbxpjFOpYkxnS8ukoyF88zAHf2ZG', 'raikov_aleksander')," +
                    "('777', '??????', '??????????', '??????????????????????????', '??????????????????????????', '??????????????????????????', '79099384712', 0, 0, '$2a$10$J/x2SyXnqNBXHl7XbDivNee0SkE6nAvoUKH679QUcTuyiXmIz2F0G', 'kotik_igor')");
            db.execSQL("INSERT INTO Statement VALUES" +
                    "(101, '2021-03-03', '????????: 170-175, ???????????????? ????????????????????????, ???????????? ??????????????, ???? ?????? 40-45 ??????. ???????? ????????????, ???? ?????????????? ?????????????? ?????????????? ??????.', 1, '001101')," +
                    "(102, '2021-03-10', '????????: 180-185, ???????????????????? ????????????????????????, ???????????? ??????????, ???????????????????? ???????????????????? ??????????. ???? ???????????? ???????? ???? ???????????? ?????????????? ??????????.', 2, '002201')," +
                    "(103, '2021-03-15', '????????: 172-177, ???????????????? ????????????????????????, ???????????? ???? ???????????? ????????????????, ???? ?????? 50 ??????. ?????? ??????????, ?????????? ????????????????????????????.', 3, '001102')," +
                    "(104, '2021-03-21', '????????: 150-155, ???????????????? ????????????????????????, ???????????? ??????????????, ???? ?????? 40 ??????. ???? ???????????? ???????? ?????????????????????? ????????????, ?????????? ????????????????.', 4, '002202')," +
                    "(105, '2021-03-21', '????????: 165-170, ???????????????? ????????????????????????, ???? ?????? 55 ??????. ???? ???????? ?????? ?????????????? ???????????????? ?????????????????? ??????????.', 5, '002202')," +
                    "(106, '2021-03-26', '????????: ??????????????????, ???? ?????? 35 ??????. ???????????????????? ???? ?????????? ???????? ???? ?????????? ???? ?????????? ?? ???????? ??????????????.', 6, '001102')," +
                    "(107, '2021-04-01', '????????: 175-180, ???????????????????? ????????????????????????, ???? ?????? 35 ??????. ?????? ???????????? ?? ??????, ?????????????? ??????????, ???????????? ????????.', 7, '001101')," +
                    "(108, '2021-04-05', '????????: 165-168, ???????????????? ????????????????????????, ???? ?????? 60-70 ??????. ???????????? ???????? ?????????????????????????? (????????????????????).', 8, '002201')");
        db.execSQL("INSERT INTO Qualification_of_crime(article, sign, part, item) VALUES" +
                "('111', '', '2', '??')," +
                "('158', '', '2', '??')," +
                "('168', '', '2', '')," +
                "('159', '1', '1', '')," +
                "('239', '', '1', '')," +
                "('264', '', '2', '??')," +
                "('240', '', '2', '??')," +
                "('228', '1', '2', '??')");
        db.execSQL("INSERT INTO Group_of_persons_directory VALUES" +
                "('0300', '???????????? ??????')," +
                "('0400', '???????????? ?????? ???? ???????????????????????????????? ??????????????')," +
                "('0200', '???????????????????????????? ????????????')," +
                "('0100', '???????????????????? ???????????????????? (??????????????????????)')," +
                "('0050', '???????????? ?????? ?????? ??????????????????????')," +
                "('0040', '???????????? ??????????????????????')," +
                "('0020', '???????????? ??????????????????????????????????')," +
                "('0001', '?????????????????? ????????????')," +
                "('0000', '?????? ????????????')");
        db.execSQL("INSERT INTO Category_of_crimes VALUES" +
                "('1', '????????????')," +
                "('2', '?????????????????? ??????????????')," +
                "('3', '?????????????? ??????????????')," +
                "('4', '?????????? ????????????')");
        db.execSQL("INSERT INTO Focus_category VALUES" +
                "('1', '??????????????????????????')," +
                "('2', '??????????????????????????')," +
                "('3', '??????????????????')");
        db.execSQL("INSERT INTO Damage_category VALUES" +
                "('1', '????????????????????????')," +
                "('2', '??????????????')," +
                "('3', '?????????? ??????????????')");
        db.execSQL("INSERT INTO Condition_of_committing VALUES" +
                "('0', '?????????????? ??????????????????')," +
                "('1', '?????????????????????? ??????????????????')," +
                "('2', '?????????????????????????? ??????????????????')," +
                "('3', '?????????????????????? ??????????????????')," +
                "('4', '?????????????????????? ????????????????????')," +
                "('5', '?????????????????????? ????????????????????????????????')," +
                "('6', '?????????????????????? ????????????????????????')");
        db.execSQL("INSERT INTO Reference_previously_convicted VALUES" +
                "('00', '?????????? ???? ??????????')," +
                "('01', '???? ?????????????????????? ????????????????????????')," +
                "('02', '???? ??????????????')," +
                "('03', '???? ????????????????????????????')," +
                "('04', '???? ???????????????????? ??????????????????????????????????????')," +
                "('05', '???? ???????????????????? ???????????????????? ????????????????????????')," +
                "('06', '???? ???????????????????????? ??????????????????')," +
                "('07', '???? ???????? ??????????????????')," +
                "('08', '???????????????????????? ???????????????????? ?????????? ?? ???????????? ??????????')," +
                "('09', '?????????????????? ???? ???????????? ????????????')");
            db.execSQL("INSERT INTO Accused(lastname, firstname, middle_name, gender, birthday, passport, citizenship, social_status, official_position, phone) VALUES" +
                    "('??????????????', '????????????', '??????????????????', '1', '1976-03-09', '4438300865', '????????????', '??????????????????'," +
                    "'????????', '79146714773')," +
                    "('??????????????????', '????????????', '??????????????????', '1', '1962-08-13', '4136261373', '????????????', '?????????????? ??????????????'," +
                    "'????????????', '79155378657')," +
                    "('??????????????????', '????????????', '????????????????????', '1', '1969-08-23', '4681350579', '????????????????????', '???????? ?????? ?????????????????????? ?????????????????? ????????????', '??????????????????', '79889669745')," +
                    "('??????????????', '????????', '????????????', '2', '1981-07-10', '4412970191', '????????????', '????????????'," +
                    "'?????????????????????????????? ?????? ?????????????????????? ???????????????????????? ????????', '79974877120')," +
                    "('????????????????', '??????????????', '????????????????????', '1', '1968-01-17', '4819282942', '????????????', '?????????????? ??????????????'," +
                    "'??????????????????????, ????????????????', '79749086538')," +
                    "('??????????????', '??????????????', '????????????????????', '1', '1988-09-10', '4679851105', '????????????', '?????????????????????? ?? ?????????????????????????????????? ?????????????????????????????? ????????????????????'," +
                    "'??????????????????', '79973308216')," +
                    "('??????????????', '????????????', '????????????????????', '2', '1978-06-04', '4846501465', '????????????', '?????????????????????????? ?????????????? ?????? ???????????????????? ??????????????????????????'," +
                    "'?????????? ??????????', '79596129650')," +
                    "('??????????????', '??????????', '????????????????????', '1', '1960-08-12', '4490860113', '????????????', '?????????????? ??????????????'," +
                    "'???????????????? ?????????????????? ????????????????????', '79653275695')");
            db.execSQL("INSERT INTO Court(title, address) VALUES" +
                    "('?????????????????????? ?????? ???????????? ????????????', '115191, ??.????????????, ?????????????? ???????????????? ??????????, ?????? 17')," +
                    "('?????????????????? ???????????????? ?????? ?????? ???????????? ????????????', '107078, ??.????????????, ???????????????????????? ??????????, ?????? 11, ???????????????? 1')," +
                    "('?????????????????? ???????????????? ?????? ?????? ???????????? ????????????', '109147, ??.????????????, ???????????????????????? ????????????????, ?????? 1/32')");
            db.execSQL("INSERT INTO Judge VALUES" +
                    "('102101', '????????????????', '??????????????????', '????????????????????', '$2a$10$gNDZQo/s4JgSp9t1f9hyNusZcA7TGQiLf651nCSJx/uChRekAEUHa', 'khudyakova_elizaveta')," +
                    "('102201', '????????????????', '??????????', '??????????????????????', '$2a$10$m8fMoYIUV0Og137q9JZDtOpmUdc0h064OuYCFrxbfW0L3B7ssqGWW', 'novitsky_fedor')," +
                    "('112101', '??????????????????', '????????????????', '????????????????????', '$2a$10$AwQbAUwNcBzAgnxVMFUXmeVg.wHj5/MZAPO22UkkX1uyadcBFVuHW', 'rudnikova_marianna')," +
                    "('142303', '??????????????????????????', '????????????', '??????????????????', '$2a$10$mPqlPrugxWLA/myP4xadYeZrNdMkrFrwtbYjlKyxTX2.yE08PU4XW', 'bezukladnikov_maxim')");
        db.execSQL("INSERT INTO Criminal_case VALUES" +
                "(501, '2021-03-25', '2021-05-09', 101, '005501', 2, '102101')," +
                "(502, '2021-03-30', '2021-05-13', 102, '005501', 3, '102201')," +
                "(503, '2021-04-05', '2021-05-14', 103, '005501', 3, '112101')," +
                "(504, '2021-04-18', '2021-06-01', 104, '003301', 1, '142303')," +
                "(505, '2021-04-19', '2021-06-02', 105, '004401', 2, '102101')," +
                "(506, '2021-04-27', '2021-06-10', 106, '005501', 2, '102201')," +
                "(507, '2021-05-12', '2021-06-15', 107, '004401', 3, '112101')," +
                "(508, '2021-05-15', '2021-06-16', 108, '004401', 2, '142303')");
        db.execSQL("INSERT INTO Crime(number_of_criminal_case, date_and_time_of_crime, id_criminal_case, " +
                "id_qualification_of_crime, id_type_of_group, id_category_of_crimes, id_focus_category, id_damage_category) VALUES" +
                "('1', '2021-03-03 19:06:11', 501, 1, '0000', '1', '1', '1')," +
                "('1', '2021-03-10 15:17:20', 502, 2, '0000', '2', '1', '1')," +
                "('1', '2021-03-15 04:34:54', 503, 3, '0000', '3', '1', '3')," +
                "('1', '2021-03-18 16:15:08', 504, 4, '0000', '3', '2', '2')," +
                "('1', '2021-03-21 11:04:12', 505, 5, '0400', '2', '1', '1')," +
                "('1', '2021-03-26 21:47:01', 506, 6, '0000', '1', '1', '2')," +
                "('1', '2021-04-01 23:43:39', 507, 7, '0001', '2', '1', '1')," +
                "('1', '2021-04-03 18:08:29', 508, 8, '0200', '1', '1', '2')");
        db.execSQL("INSERT INTO Participants_in_crime VALUES" +
                "(1, 1, '00', '3')," +
                "(2, 2, '08', '0')," +
                "(3, 3, '00', '1')," +
                "(4, 4, '04', '0')," +
                "(5, 5, '00', '0')," +
                "(6, 6, '00', '1')," +
                "(7, 7, '00', '0')," +
                "(8, 8, '01', '5')");
        db.execSQL("INSERT INTO Sentence VALUES" +
                "(501, '2021-04-25 16:03:20', '???????????????????????? ???????????????? ?????????????? ???? ???????? ???? 10 ??????.', '102101')," +
                "(502, '2021-04-29 11:04:12', '???????????????????????? ?????????????????????????????? ???????????????? ???? ???????? ???? ???????? ??????.', '102201')," +
                "(503, '2021-04-30 09:34:21', '???????????????????????? ?????????????? ?????????????????????? ???? ???????????? ???? ???????????? ????????.', '112101')," +
                "(504, '2021-05-18 10:11:43', '???????????????????? ?????????????? ???? ???????? ???? ?????????????? ??????????????.', '142303')," +
                "(505, '2021-05-19 17:54:32', '???????????????????????? ???????????????????????? ?????????????? ???? ???????? ???? ?????????????? ??????.', '102101')," +
                "(506, '2021-05-27 12:34:23', '???????????????????????? ?????????????????????????????? ???????????????? ???? ???????? ???? ???????? ?????? ?? ???????????????? ?????????? ???????????????? ???????????????????????? ??????????????????.', '102201')," +
                "(507, '2021-06-01 10:23:10', '???????????????????????? ???????????????? ?????????????? ???? ???????? ???? ?????????? ??????.', '112101')," +
                "(508, '2021-06-02 09:56:43', '???????????????????????? ???????????????? ?????????????? ???? ???????? ???? ???????? ?????? ???? ?????????????? ?? ?????????????? ???? ?????????????? ?????????? ????????????.', '142303')");
        });
        thread.start();
    }
}
