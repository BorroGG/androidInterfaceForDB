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
import com.example.testproject.db.dao.Damage_categoryDao;
import com.example.testproject.db.dao.DepartmentDao;
import com.example.testproject.db.dao.Focus_categoryDao;
import com.example.testproject.db.dao.Group_of_persons_directoryDao;
import com.example.testproject.db.dao.JudgeDao;
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
        Reference_previously_convicted.class, Sentence.class, Statement.class, Victim.class}, version = 1)
public abstract class CursachDatabase extends RoomDatabase {

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

    private static volatile CursachDatabase INSTANCE;

    public static CursachDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (CursachDatabase.class) {
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
                                    CursachDatabase.class, "cursach_db3")
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
                    "('Смирнов', 'Виктор', 'Иванович', '1', '1987-08-13', '4215098764', 'Россия', 'Наемный рабочий'," +
                    "'Охранник', '79152762244')," +
                    "('Эмскиха', 'Татьяна', 'Анатольевна', '2', '1982-05-19', '4560356412', 'Россия', 'Собственник (владелец предприятия, землевладелец)'," +
                    "'Сфера строительства', '79849969051')," +
                    "('Кумирова', 'Зоя', 'Венедиктовна', '2', '1995-09-02', '4958550474', 'Россия', 'Наемный рабочий'," +
                    "'Бухгалтер', '79612305687')," +
                    "('Дубин', 'Арсений', 'Федорович', '1', '1961-04-25', '4049287072', 'Россия', 'Пенсионер по выслуге лет'," +
                    "'Федеральная служба судебных приставов (ФССП)', '79669617928')," +
                    "('Адаксин', 'Юлиан', 'Севастьянович', '1', '1980-04-14', '4253181718', 'Россия', 'Лицо, занимающееся мелкооптовой торговлей'," +
                    "'Сфера торговли', '79202681964')," +
                    "('Корнейчук', 'Алена', 'Феоктистовна', '2', '1970-05-03', '4095378092', 'Россия', 'Служащий'," +
                    "'Министерство юстиции', '79711747098')," +
                    "('Елешева', 'Дарья', 'Адамовна', '2', '1973-02-11', '4240870382', 'Россия', 'Работник культуры и искусства'," +
                    "'Сфера культуры и искусства', '79617758261')," +
                    "('Привалов', 'Ефрем', 'Михаилович', '1', '1950-01-08', '4751324871', 'Нигерия', 'Пенсионер по старости'," +
                    "'Фермер', '79409489066')");
            db.execSQL("INSERT INTO Organ(title_organ) VALUES" +
                    "('Министерство внутренних дел Российской Федерации')," +
                    "('Прокуратура Российской Федерации')," +
                    "('Следственный комитет Российской Федерации')," +
                    "('Федеральная служба безопасности Российской Федерации')");
            db.execSQL("INSERT INTO Department(title_department) VALUES" +
                    "('Отдел МВД России по району Замоскворечье города Москвы')," +
                    "('Отдел МВД России по Тверскому району г. Москвы')," +
                    "('Прокуратура ЦАО г. Москвы')," +
                    "('Управление экономической безопасности и противодействия коррупции ГУ МВД России по г. Москве')," +
                    "('Следственный отдел по Басманному району')");
            db.execSQL("INSERT INTO Organ_employee VALUES" +
                    "('001101', 'Добролюбов', 'Тимофей', 'Степанович', 'Дежурный в ДЧ', 'Старший лейтенант', '4956879398', 1, 1, '$2y$10$u.hYaT8j30aqBZeOATdvy.U90HSxetSs0O.MNbu3Fjfi/0E6ZQ0I6', 'dobrolyubov_timofei')," +
                    "('001102', 'Язов', 'Ефим', 'Федорович', 'Дежурный в ДЧ', 'Старший лейтенант', '4958843741', 1, 1, '$2y$10$GaAogRQtMA4fPdhObYqW/uvEACK2b4zNa2bc0rW/zIMarCKVuOCDW', 'yazov_efim')," +
                    "('002201', 'Будаев', 'Василий', 'Прокопьевич', 'Дежурный в ДЧ', 'Старший лейтенант', '4956195717', 1, 2, '$2y$10$y6klNnimJtVq52SaP4twE.2ckNHGEYZBCwIeeP4tArPs41ssU9hpi', 'budaev_vasilii')," +
                    "('002202', 'Дьяконов', 'Филипп', 'Валерьевич', 'Дежурный в ДЧ', 'Старший лейтенант', '4954796390', 1, 2, '$2y$10$NMsOApYqn34Ek5inc2jv4O0N7psf4zfJSX4/qQDYecTnRpCpY8oHu', 'dyakonov_filipp')," +
                    "('003301', 'Чупракова', 'Любовь', 'Алексеевна', 'Прокурор', 'Генерал-лейтенант юстиции', '4956574461', 2, 3, '$2y$10$cOiD6niljyoijY8rvCc7Huq9Ia11rFaA0Gwq82VVwWkcGMja3Cz46', 'chuprakova_love')," +
                    "('004401', 'Теплухин', 'Павел', 'Игнатьевич', 'Оперуполномоченный', 'Капитан внутренней службы', '4957714440', 1, 4, '$2y$10$uaRQAl8weA4dbAHwJmWMduLOG5T6S1HBQKYO.wIsbO48ZwPSmGsYm', 'teplukhin_pavel')," +
                    "('005501', 'Райков', 'Александр', 'Егорович', 'Следователь', 'Подполковник юстиции', '4958118915', 3, 5, '$2y$10$ZYRwoSza5nI4ybgdcO6ca.QWPoDMRcytA23G/IC3fkEaTljRQwEAa', 'raikov_aleksander')," +
                    "('777777', 'Admin', 'Admin', 'Admin', 'Admin', 'Admin', 'Admin', 0, 0, '$2y$10$kJPQdmJ32Mm5t37njmCWh.DZJCa/fUouUfx0T2ir1uOszLN1nOGx2 ', 'kotik_igor')");
            db.execSQL("INSERT INTO Statement VALUES" +
                    "(101, '2021-03-03', 'Рост: 170-175, среднего телосложения, волосы светлые, на вид 40-45 лет. Губы тонкие, на верхней челюсти золотой зуб.', 1, '001101')," +
                    "(102, '2021-03-10', 'Рост: 180-185, худощавого телосложения, волосы русые, подбородок квадратной формы. На правой ноге на голени большой синяк.', 2, '002201')," +
                    "(103, '2021-03-15', 'Рост: 172-177, среднего телосложения, волосы на голове короткие, на вид 50 лет. Лоб узкий, брови горизонтальные.', 3, '001102')," +
                    "(104, '2021-03-21', 'Рост: 150-155, плотного телосложения, волосы светлые, на вид 40 лет. На правой руке обручальное кольцо, ногти короткие.', 4, '002202')," +
                    "(105, '2021-03-21', 'Рост: 165-170, плотного телосложения, на вид 55 лет. На лице был наклеен пластырь телесного цвета.', 5, '002202')," +
                    "(106, '2021-03-26', 'Рост: невысокий, на вид 35 лет. Татуировка на левой руке от плеча до кисти в виде дракона.', 6, '001102')," +
                    "(107, '2021-04-01', 'Рост: 175-180, модельного телосложения, на вид 35 лет. Три серьги в ухе, голубые глаза, пухлые губы.', 7, '001101')," +
                    "(108, '2021-04-05', 'Рост: 165-168, среднего телосложения, на вид 60-70 лет. Правый глаз искусственный (стеклянный).', 8, '002201')");
        db.execSQL("INSERT INTO Qualification_of_crime(article, sign, part, item) VALUES" +
                "('111', '', '2', 'д')," +
                "('158', '', '2', 'г')," +
                "('168', '', '2', '')," +
                "('159', '1', '1', '')," +
                "('239', '', '1', '')," +
                "('264', '', '2', 'а')," +
                "('240', '', '2', 'а')," +
                "('228', '1', '2', 'б')");
        db.execSQL("INSERT INTO Group_of_persons_directory VALUES" +
                "('0300', 'Группа лиц')," +
                "('0400', 'Группа лиц по предварительному сговору')," +
                "('0200', 'Организованная группа')," +
                "('0100', 'Преступное сообщество (организация)')," +
                "('0050', 'Группа лиц без гражданства')," +
                "('0040', 'Группа иностранцев')," +
                "('0020', 'Группа несовершенолетних')," +
                "('0001', 'Смешанная группа')," +
                "('0000', 'Без группы')");
        db.execSQL("INSERT INTO Category_of_crimes VALUES" +
                "('1', 'Тяжкое')," +
                "('2', 'Небольшой тяжести')," +
                "('3', 'Средней тяжести')," +
                "('4', 'Особо тяжкое')");
        db.execSQL("INSERT INTO Focus_category VALUES" +
                "('1', 'Общеуголовное')," +
                "('2', 'Экономическое')," +
                "('3', 'Налоговое')");
        db.execSQL("INSERT INTO Damage_category VALUES" +
                "('1', 'Значительный')," +
                "('2', 'Крупный')," +
                "('3', 'Особо крупный')");
        db.execSQL("INSERT INTO Condition_of_committing VALUES" +
                "('0', 'Трезвое состояние')," +
                "('1', 'Алкогольное опьянение')," +
                "('2', 'Наркотическое опьянение')," +
                "('3', 'Токсическое опьянение')," +
                "('4', 'Хронический алкоголизм')," +
                "('5', 'Хроническая наркозависимость')," +
                "('6', 'Хроническая токсикомания')");
        db.execSQL("INSERT INTO Reference_previously_convicted VALUES" +
                "('00', 'Ранее не судим')," +
                "('01', 'За аналогичное преступление')," +
                "('02', 'За хищение')," +
                "('03', 'За вымогательство')," +
                "('04', 'За незаконное предпринимательство')," +
                "('05', 'За незаконную банковскую деятельность')," +
                "('06', 'За приобретение имущества')," +
                "('07', 'За сбыт имущества')," +
                "('08', 'Изготовление поддельных денег и ценных бумаг')," +
                "('09', 'Уклонение от уплаты налога')");
            db.execSQL("INSERT INTO Accused(lastname, firstname, middle_name, gender, birthday, passport, citizenship, social_status, official_position, phone) VALUES" +
                    "('Лисицын', 'Кирилл', 'Семенович', '1', '1976-03-09', '4438300865', 'Россия', 'Иждивенец'," +
                    "'Иные', '79146714773')," +
                    "('Вольваков', 'Герман', 'Семенович', '1', '1962-08-13', '4136261373', 'Россия', 'Наемный рабочий'," +
                    "'Кассир', '79155378657')," +
                    "('Аглиуллин', 'Максим', 'Лаврентиич', '1', '1969-08-23', '4681350579', 'Белоруссия', 'Лицо без постоянного источника дохода', 'Колхозник', '79889669745')," +
                    "('Ломтева', 'Вера', 'Яковна', '2', '1981-07-10', '4412970191', 'Россия', 'Рантье'," +
                    "'Предприниматель без образования юридического лица', '79974877120')," +
                    "('Добрынин', 'Василий', 'Даниилович', '1', '1968-01-17', '4819282942', 'Россия', 'Наемный рабочий'," +
                    "'Консультант, советник', '79749086538')," +
                    "('Кумиров', 'Герасим', 'Леонидович', '1', '1988-09-10', '4679851105', 'Россия', 'Обучающиеся в негосударственном образовательном учреждении'," +
                    "'Экономист', '79973308216')," +
                    "('Лаврова', 'Оксана', 'Михайловна', '2', '1978-06-04', '4846501465', 'Россия', 'Представитель научной или творческой интеллигенции'," +
                    "'Сфера науки', '79596129650')," +
                    "('Водянов', 'Павел', 'Тимофеевич', '1', '1960-08-12', '4490860113', 'Росиия', 'Наемный рабочий'," +
                    "'Водитель грузового транспорта', '79653275695')");
            db.execSQL("INSERT INTO Court(title, address) VALUES" +
                    "('Арбитражный Суд города Москвы', '115191, г.Москва, Большая Тульская улица, дом 17')," +
                    "('Басманный районный суд ЦАО города Москвы', '107078, г.Москва, Каланчёвская улица, дом 11, строение 1')," +
                    "('Таганский районный суд ЦАО города Москвы', '109147, г.Москва, Марксистский переулок, дом 1/32')");
            db.execSQL("INSERT INTO Judge VALUES" +
                    "('102101', 'Худякова', 'Елизавета', 'Николаевна', '$2y$10$HZ26VSOm3AuNt9nLhQ0ij.v1vxT21COuMLxBHO3AuJsXPHS/h89iK', 'khudyakova_elizaveta')," +
                    "('102201', 'Новицкий', 'Федор', 'Афанасьевич', '$2y$10$pzaJYbt4IIy3tk63BxIfr.3KWxv0zVo18IkhWIX/Dsgao2HWtsq/q', 'novitsky_fedor')," +
                    "('112101', 'Рудникова', 'Марианна', 'Максимовна', '$2y$10$wSzXJPFD.Y6cm/Nx6SEBg.9Ol.NQ3i07oPk5xM4FCuOnYp3YMAwgm', 'rudnikova_marianna')," +
                    "('142303', 'Безукладников', 'Максим', 'Антонович', '$2y$10$f9a8Z0gjEcs1kipOLxthtuoBTENsqRUrp7KBW2r6A7o1GcUPmQvI2', 'bezukladnikov_maxim')");
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
                "('501', '2021-04-25 16:03:20', 'Наказывается лишением свободы на срок до 10 лет.', '102101')," +
                "('502', '2021-04-29 11:04:12', 'Наказывается исправительными работами на срок до двух лет.', '102201')," +
                "('503', '2021-04-30 09:34:21', 'Наказывается штрафом осужденного за период до одного года.', '112101')," +
                "('504', '2021-05-18 10:11:43', 'Осуждается арестом на срок до четырех месяцев.', '142303')," +
                "('505', '2021-05-19 17:54:32', 'Наказывается ограничением свободы на срок до четырех лет.', '102101')," +
                "('506', '2021-05-27 12:34:23', 'Наказывается принудительными работами на срок до пяти лет с лишением права занимать определенные должности.', '102201')," +
                "('507', '2021-06-01 10:23:10', 'Наказываются лишением свободы на срок до шести лет.', '112101')," +
                "('508', '2021-06-02 09:56:43', 'Наказывается лишением свободы на срок до семи лет со штрафом в размере до пятисот тысяч рублей.', '142303')");
        });
        thread.start();
    }
}
