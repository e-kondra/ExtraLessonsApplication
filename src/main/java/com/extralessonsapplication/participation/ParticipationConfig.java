package com.extralessonsapplication.participation;


import com.extralessonsapplication.lesson.LessonEntity;
import com.extralessonsapplication.lesson.LessonRepository;
import com.extralessonsapplication.student.StudentEntity;
import com.extralessonsapplication.student.StudentRepository;


import java.util.List;


public class ParticipationConfig {

    public void participationCommandLineRunner(ParticipationRepository repository,
            StudentRepository studentRepository, LessonRepository lessonRepository){
            StudentEntity student1 = studentRepository.findById(1L).orElse(null);
            StudentEntity student2 = studentRepository.findById(2L).orElse(null);
            StudentEntity student3 = studentRepository.findById(3L).orElse(null);
            StudentEntity student4 = studentRepository.findById(4L).orElse(null);
            StudentEntity student5 = studentRepository.findById(5L).orElse(null);
            StudentEntity student6 = studentRepository.findById(6L).orElse(null);
            StudentEntity student7 = studentRepository.findById(7L).orElse(null);
            StudentEntity student8 = studentRepository.findById(8L).orElse(null);
            StudentEntity student9 = studentRepository.findById(9L).orElse(null);
            StudentEntity student10 = studentRepository.findById(10L).orElse(null);
            StudentEntity student11 = studentRepository.findById(11L).orElse(null);
            StudentEntity student12 = studentRepository.findById(12L).orElse(null);
            StudentEntity student13 = studentRepository.findById(13L).orElse(null);

            LessonEntity lesson1 = lessonRepository.findById(1L).orElse(null);
            LessonEntity lesson2 = lessonRepository.findById(2L).orElse(null);
            LessonEntity lesson3 = lessonRepository.findById(3L).orElse(null);
            LessonEntity lesson4 = lessonRepository.findById(4L).orElse(null);
            LessonEntity lesson5 = lessonRepository.findById(5L).orElse(null);
            LessonEntity lesson6 = lessonRepository.findById(6L).orElse(null);
            LessonEntity lesson7 = lessonRepository.findById(7L).orElse(null);
            LessonEntity lesson8 = lessonRepository.findById(8L).orElse(null);
            LessonEntity lesson9 = lessonRepository.findById(9L).orElse(null);
            LessonEntity lesson10 = lessonRepository.findById(10L).orElse(null);
            LessonEntity lesson11 = lessonRepository.findById(11L).orElse(null);
            LessonEntity lesson12 = lessonRepository.findById(12L).orElse(null);
            LessonEntity lesson13 = lessonRepository.findById(13L).orElse(null);
            LessonEntity lesson14 = lessonRepository.findById(14L).orElse(null);
            LessonEntity lesson15 = lessonRepository.findById(15L).orElse(null);
            LessonEntity lesson16 = lessonRepository.findById(16L).orElse(null);



            Participation participation1 = new Participation(1L, student1, lesson1, true);
            Participation participation2 = new Participation(2L, student2, lesson1, true);
            Participation participation3 = new Participation(3L, student3, lesson1, true);
            Participation participation4 = new Participation(4L, student4, lesson1, true);
            Participation participation5 = new Participation(5L, student5, lesson1, true);
            Participation participation6 = new Participation(6L, student6, lesson1, true);
            Participation participation7 = new Participation(7L, student7, lesson1, true);
            Participation participation8 = new Participation(8L, student8, lesson1, true);
            Participation participation9 = new Participation(9L, student9, lesson9, true);
            Participation participation10 = new Participation(10L, student10, lesson9, true);
            Participation participation11 = new Participation(11L, student11, lesson9, true);
            Participation participation12 = new Participation(12L, student12, lesson9, true);
            Participation participation13 = new Participation(13L, student13, lesson9, true);

            Participation participation14 = new Participation(14L, student1, lesson2, true);
            Participation participation15 = new Participation(15L, student2, lesson2, true);
            Participation participation16 = new Participation(16L, student3, lesson2, false);
            Participation participation17 = new Participation(17L, student4, lesson2, true);
            Participation participation18 = new Participation(18L, student5, lesson2, true);
            Participation participation19 = new Participation(19L, student6, lesson2, true);
            Participation participation20 = new Participation(20L, student7, lesson2, true);
            Participation participation21 = new Participation(21L, student8, lesson2, true);
            Participation participation22 = new Participation(22L, student9, lesson10, true);
            Participation participation23 = new Participation(23L, student10, lesson10, true);
            Participation participation24 = new Participation(24L, student11, lesson10, true);
            Participation participation25 = new Participation(25L, student12, lesson10, true);
            Participation participation26 = new Participation(26L, student13, lesson10, true);

            Participation participation27 = new Participation(27L, student1, lesson3, true);
            Participation participation28 = new Participation(28L, student2, lesson3, true);
            Participation participation29 = new Participation(29L, student3, lesson3, true);
            Participation participation30 = new Participation(30L, student4, lesson3, true);
            Participation participation31 = new Participation(31L, student5, lesson3, true);
            Participation participation32 = new Participation(32L, student6, lesson3, true);
            Participation participation33 = new Participation(33L, student7, lesson3, true);
            Participation participation34 = new Participation(34L, student8, lesson3, true);
            Participation participation35 = new Participation(35L, student9, lesson11, true);
            Participation participation36 = new Participation(36L, student10, lesson11, true);
            Participation participation37 = new Participation(37L, student11, lesson11, true);
            Participation participation38 = new Participation(38L, student12, lesson11, true);
            Participation participation39 = new Participation(39L, student13, lesson11, true);

            Participation participation40 = new Participation(40L, student1, lesson4, true);
            Participation participation41 = new Participation(41L, student2, lesson4, true);
            Participation participation42 = new Participation(42L, student3, lesson4, true);
            Participation participation43 = new Participation(43L, student4, lesson4, true);
            Participation participation44 = new Participation(44L, student5, lesson4, true);
            Participation participation45 = new Participation(45L, student6, lesson4, true);
            Participation participation46 = new Participation(46L, student7, lesson4, true);
            Participation participation47 = new Participation(47L, student8, lesson4, true);
            Participation participation48 = new Participation(48L, student9, lesson12, true);
            Participation participation49 = new Participation(49L, student10, lesson12, true);
            Participation participation50 = new Participation(50L, student11, lesson12, true);
            Participation participation51 = new Participation(51L, student12, lesson12, true);
            Participation participation52 = new Participation(52L, student13, lesson12, true);

            Participation participation53 = new Participation(53L, student1, lesson5, true);
            Participation participation54 = new Participation(54L, student2, lesson5, true);
            Participation participation55 = new Participation(55L, student3, lesson5, true);
            Participation participation56 = new Participation(56L, student4, lesson5, true);
            Participation participation57 = new Participation(57L, student5, lesson5, true);
            Participation participation58 = new Participation(58L, student6, lesson5, true);
            Participation participation59 = new Participation(59L, student7, lesson5, true);
            Participation participation60 = new Participation(60L, student8, lesson5, true);
            Participation participation61 = new Participation(61L, student9, lesson13, true);
            Participation participation62 = new Participation(62L, student10, lesson13, false);
            Participation participation63 = new Participation(63L, student11, lesson13, true);
            Participation participation64 = new Participation(64L, student12, lesson13, true);
            Participation participation65 = new Participation(65L, student13, lesson13, true);

            Participation participation66 = new Participation(66L, student1, lesson6, false);
            Participation participation67 = new Participation(67L, student2, lesson6, true);
            Participation participation68 = new Participation(68L, student3, lesson6, true);
            Participation participation69 = new Participation(69L, student4, lesson6, true);
            Participation participation70 = new Participation(70L, student5, lesson6, true);
            Participation participation71 = new Participation(71L, student6, lesson6, true);
            Participation participation72 = new Participation(72L, student7, lesson6, true);
            Participation participation73 = new Participation(73L, student8, lesson6, true);
            Participation participation74 = new Participation(74L, student9, lesson14, true);
            Participation participation75 = new Participation(75L, student10, lesson14, false);
            Participation participation76 = new Participation(76L, student11, lesson14, true);
            Participation participation77 = new Participation(77L, student12, lesson14, true);
            Participation participation78 = new Participation(78L, student13, lesson14, true);

            Participation participation79 = new Participation(79L, student1, lesson7, true);
            Participation participation80 = new Participation(80L, student2, lesson7, true);
            Participation participation81 = new Participation(81L, student3, lesson7, true);
            Participation participation82 = new Participation(82L,student4, lesson7, true);
            Participation participation83 = new Participation(83L, student5, lesson7, true);
            Participation participation84 = new Participation(84L, student6, lesson7, true);
            Participation participation85 = new Participation(85L,student7, lesson7, true);
            Participation participation86 = new Participation(86L,student8, lesson7, true);
            Participation participation87 = new Participation(87L, student9, lesson15, true);
            Participation participation88 = new Participation(88L, student10, lesson15, false);
            Participation participation89 = new Participation(89L, student11, lesson15, true);
            Participation participation90 = new Participation(90L, student12, lesson15, true);
            Participation participation91 = new Participation(91L, student13, lesson15, true);

            Participation participation92 = new Participation(92L, student1, lesson8, true);
            Participation participation93 = new Participation(93L, student2, lesson8, true);
            Participation participation94 = new Participation(94L, student3, lesson8, true);
            Participation participation95 = new Participation(95L, student4, lesson8, true);
            Participation participation96 = new Participation(96L, student5, lesson8, false);
            Participation participation97 = new Participation(97L, student6, lesson8, true);
            Participation participation98 = new Participation(98L, student7, lesson8, true);
            Participation participation99 = new Participation(99L, student8, lesson8, true);
            Participation participation100 = new Participation(100L, student9, lesson16, true);
            Participation participation101 = new Participation(101L, student10, lesson16, false);
            Participation participation102 = new Participation(102L, student11, lesson16, true);
            Participation participation103 = new Participation(103L, student12, lesson16, true);
            Participation participation104 = new Participation(104L, student13, lesson16, true);


            repository.saveAll(
                    List.of(
                            participation1,participation2,participation3,participation4,participation5, participation6,
                            participation7,participation8,participation9,participation10,participation11,
                            participation12,participation13,participation14,participation15,participation16,
                            participation17,participation18,participation19,participation20,participation21,
                            participation22,participation23,participation24,participation25,participation26,
                            participation27,participation28,participation29,participation30,participation31,
                            participation32,participation33,participation34,participation35,participation36,
                            participation37,participation38,participation39,participation40,participation41,
                            participation42,participation43,participation44,participation45,participation46,
                            participation47,participation48,participation49,participation50,participation51,
                            participation52,participation53,participation54,participation55,participation56,
                            participation57,participation58, participation59,participation60,participation61,
                            participation62,participation63,participation64,participation65,participation66,
                            participation67,participation68,participation69,participation70,participation71,
                            participation72,participation73,participation74,participation75,participation76,
                            participation77,participation78,participation79,participation80,participation81,
                            participation81,participation82,participation83,participation84,participation85,
                            participation86,participation87 , participation88,participation89,participation90,
                            participation91,participation92,participation93,participation94,participation95,
                            participation96,participation97, participation98,participation99,participation100,
                            participation101,participation102,participation103,participation104
                            ));
        };

    }
